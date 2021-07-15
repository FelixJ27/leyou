package com.leyou.order.service.impl;

import com.leyou.common.dto.CartDTO;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.IdWorker;
import com.leyou.item.pojo.TbSku;
import com.leyou.order.client.AddressClient;
import com.leyou.order.client.GoodsClient;
import com.leyou.order.dao.OrderDetailMapper;
import com.leyou.order.dao.OrderMapper;
import com.leyou.order.dao.OrderStatusMapper;
import com.leyou.order.dto.AddressDTO;
import com.leyou.order.dto.OrderDTO;
import com.leyou.order.enums.OrderStatusEnum;
import com.leyou.order.interceptor.UserInterceptor;
import com.leyou.order.pojo.Order;
import com.leyou.order.pojo.OrderDetail;
import com.leyou.order.pojo.OrderStatus;
import com.leyou.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Felix
 * @Description:
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private GoodsClient goodsClient;

    @Transactional
    @Override
    public Long createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        //订单编号，基本信息
        long orderId = idWorker.nextId();
        order.setOrderId(orderId);
        order.setCreateTime(new Date());
        order.setPaymentType(orderDTO.getPaymentType());
        //获取用户信息
        UserInfo user = UserInterceptor.getUser();
        order.setUserId(user.getId());
        order.setBuyerNick(user.getUsername());
        order.setBuyerRate(false);
        //收货人信息
        AddressDTO addr = AddressClient.findById(orderDTO.getAddressId());
        order.setReceiver(addr.getName());
        order.setReceiverAddress(addr.getAddress());
        order.setReceiverCity(addr.getCity());
        order.setReceiverDistrict(addr.getDistrict());
        order.setReceiverState(addr.getState());
        order.setReceiverMobile(addr.getPhone());
        order.setReceiverZip(addr.getZipCode());
        //金额
        Map<Long, Integer> numMap = orderDTO.getCarts().stream()
                .collect(Collectors.toMap(CartDTO::getSkuId, CartDTO::getNum));
        Set<Long> ids = numMap.keySet();
        List<TbSku> skus = goodsClient.querySkuByIds(new ArrayList<>(ids));

        List<OrderDetail> orderDetails = new ArrayList<>();

        long totalPay = 0L;
        for (TbSku sku : skus) {
            totalPay += sku.getPrice() * numMap.get(sku.getId());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setImage(StringUtils.substringBefore(sku.getImages(), ","));
            orderDetail.setNum(numMap.get(sku.getId()));
            orderDetail.setOrderId(orderId);
            orderDetail.setOwnSpec(sku.getOwnSpec());
            orderDetail.setPrice(sku.getPrice());
            orderDetail.setSkuId(sku.getId());
            orderDetail.setTitle(sku.getTitle());
            orderDetails.add(orderDetail);
        }
        order.setTotalPay(totalPay);
        //实付金额 = 总金额 + 邮费 - 优惠金额
        order.setActualPay(totalPay + order.getPostFee() - 0);
        int flag = orderMapper.insertSelective(order);
        if (flag != 1) {
            log.error("[创建订单]创建订单失败，orderId：{}", orderId);
            throw new LyException(ExceptionEnum.INSERT_ORDER_ERROR);
        }
        flag = orderDetailMapper.insertListSelective(orderDetails);
        if (flag != 1) {
            log.error("[创建订单]创建订单失败，orderId：{}", orderId);
            throw new LyException(ExceptionEnum.INSERT_ORDER_DETAIL_ERROR);
        }

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setStatus(OrderStatusEnum.UN_PAY.value());
        flag = orderStatusMapper.insertSelective(orderStatus);
        if (flag != 1) {
            log.error("[创建订单]创建订单失败，orderId：{}", orderId);
            throw new LyException(ExceptionEnum.INSERT_ORDER_STATUS_ERROR);
        }

        //减库存
        List<CartDTO> carts = orderDTO.getCarts();
        goodsClient.decreaseStock(carts);
        return orderId;
    }

    @Override
    public Order queryOrderById(Long id) {
        Order order = orderMapper.selectByOrderId(id);
        if (order == null) {
            throw new LyException(ExceptionEnum.ORDER_NOT_FOUND);
        }
        List<OrderDetail> details = order.getOrderDetails();
        if (CollectionUtils.isEmpty(details)) {
            throw new LyException(ExceptionEnum.ORDER_DETAIL_NOT_FOUND);
        }
        OrderStatus orderStatus = orderStatusMapper.selectByPrimaryKey(id);
        if (orderStatus == null) {
            throw new LyException(ExceptionEnum.ORDER_STATUS_NOT_FOUND);
        }
        order.setOrderStatus(orderStatus);
        return order;
    }
}
