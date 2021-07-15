package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.dto.CartDTO;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.*;
import com.leyou.item.pojo.vo.SpuVo;
import com.leyou.item.service.GoodsService;
import com.leyou.item.service.TbCategoryService;
import com.leyou.item.dao.TbSkuMapper;
import com.leyou.item.dao.TbSpuDetailMapper;
import com.leyou.item.dao.TbSpuMapper;
import com.leyou.item.dao.TbStockMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Felix
 * @Description 商品ServiceImpl
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private TbSpuMapper spuMapper;
    @Autowired
    private TbSpuDetailMapper spuDetailMapper;
    @Autowired
    private TbCategoryService categoryService;
    @Autowired
    private TbSkuMapper skuMapper;
    @Autowired
    private TbStockMapper stockMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * @description: 分页模查商品及种类、品牌
     * @auther: Felix
     */
    @Override
    public PageResult<SpuVo> querySpuByPage(Integer page, String title, Boolean saleable, Integer rows) {
        PageHelper.startPage(page, rows);
        List<TbSpu> spuList = spuMapper.findByPage(title);
        if (CollectionUtils.isEmpty(spuList)) {
            throw new LyException(ExceptionEnum.GOODS_NOT_FOUND);
        }
        List<SpuVo> spuVoList = new ArrayList<>();
        for (TbSpu spu : spuList) {
            SpuVo spuVo = new SpuVo();
            spuVo.setId(spu.getId());
            spuVo.setTitle(spu.getTitle());
            spuVo.setSubTitle(spu.getSubTitle());
            spuVo.setCid1(spu.getCid1());
            spuVo.setCid2(spu.getCid2());
            spuVo.setCid3(spu.getCid3());
            spuVo.setSaleable(spu.getSaleable());
            spuVo.setValid(spu.getValid());
            spuVo.setBrandId(spu.getBrandId());
            spuVo.setCreateTime(spu.getCreateTime());
            spuVo.setLastUpdateTime(spu.getLastUpdateTime());
            spuVo.setBName(spu.getBrand().getName());
            spuVoList.add(spuVo);
        }

        //处理商品分类名称
        handleCategory(spuVoList);

        //PageInfo的泛型为sql查出的集合的泛型
        PageInfo<TbSpu> pageInfo = new PageInfo<>(spuList);
        return new PageResult<>(pageInfo.getTotal(), spuVoList);
    }

    @Override
    @Transactional
    public void saveGoods(TbSpu spu) {
        //新增spu
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        spu.setSaleable(true);
        spu.setValid(false);
        int count = spuMapper.insertSelective(spu);
        if (count != 1) {
            throw new LyException(ExceptionEnum.GOODS_SAVE_ERROR);
        }
        //新增detail
        TbSpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spu.getId());
        spuDetailMapper.insertSelective(spuDetail);
        //新增sku和stock
        saveSkuAndStock(spu);

        amqpTemplate.convertAndSend("item.insert", spu.getId());
    }

    /**
     * @Author Felix
     * @Description 新增sku和stock
     * @Param
     * @Return
     */
    private void saveSkuAndStock(TbSpu spu) {
        //新增sku
        int count;
        List<TbSku> skus = spu.getSkus();
        List<TbStock> stockList = new ArrayList<>();
        for (TbSku sku : skus) {
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            sku.setSpuId(spu.getId());

            count = skuMapper.insertSelective(sku);
            if (count != 1) {
                throw new LyException(ExceptionEnum.GOODS_SAVE_ERROR);
            }


            //新增库存
            TbStock stock = new TbStock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockList.add(stock);
        }

        //count = stockMapper.insertSelective(stock);
        count = stockMapper.batchInsert(stockList);
        if (count != 1) {
            throw new LyException(ExceptionEnum.GOODS_SAVE_ERROR);
        }
    }

    /**
     * @Author Felix
     * @Description 查询详情
     */
    @Override
    public TbSpuDetail queryDetailById(Long spuId) {
        TbSpuDetail detail = spuDetailMapper.selectByPrimaryKey(spuId);
        if (detail == null) {
            throw new LyException(ExceptionEnum.GOODS_DETAIL_NOT_FOUND);
        }
        return detail;
    }

    /**
     * @Author Felix
     * @Description 根据spu查询所有sku
     * @Param
     * @Return
     */
    @Override
    public List<TbSku> querySkuBySpuId(Long id) {
        //查sku
        List<TbSku> skus = skuMapper.querySkuBySpuId(id);
        if (CollectionUtils.isEmpty(skus)) {
            throw new LyException(ExceptionEnum.GOODS_SKU_NOT_FOUND);
        }
        //查库存
        List<Long> ids = skus.stream().map(TbSku::getId).collect(Collectors.toList());
       loadStockInSku(ids, skus);
        return skus;
    }

    @Override
    public TbSpu querySpuById(Long id) {
        //查询spu
        TbSpu spu = spuMapper.selectByPrimaryKey(id);
        if (spu == null) {
            throw new LyException(ExceptionEnum.GOODS_NOT_FOUND);
        }

        //查询sku
        spu.setSkus(querySkuBySpuId(id));
        //查询detail
        spu.setSpuDetail(queryDetailById(id));
        return spu;
    }

    /**
     * @Author Felix
     * @Description 商品修改
     * @Param
     * @Return
     */
    @Override
    @Transactional
    public void updateGoods(TbSpu spu) {
        List<TbSku> skuList = skuMapper.querySkuBySpuId(spu.getId());
        if (!CollectionUtils.isEmpty(skuList)) {
            //删除库存
            List<Long> ids = skuList.stream().map(TbSku::getId).collect(Collectors.toList());
            int count = skuMapper.deleteByIds(ids);
            if (count != 1) {
                throw new LyException(ExceptionEnum.DELETE_GOODS_SKU_FAIL);
            }
            //删除库存
            int count2 = stockMapper.deleteByIds(ids);
            if (count2 != 1) {
                throw new LyException(ExceptionEnum.DELETE_STOCK_FAIL);
            }
        }
        //修改spu
        spu.setLastUpdateTime(new Date());
        int count = spuMapper.updateByPrimaryKeySelective(spu);
        if (count != 1) {
            throw new LyException(ExceptionEnum.GOODS_UPDATE_ERROR);
        }
        //修改detail
        TbSpuDetail spuDetail = spu.getSpuDetail();
        spuDetailMapper.updateByPrimaryKeySelective(spuDetail);
        //新增sku和stock
        saveSkuAndStock(spu);
        //发送mq
        amqpTemplate.convertAndSend("item.update", spu.getId());
    }

    @Override
    public List<TbSku> querySkuByIds(List<Long> ids) {
        List<TbSku> skus = skuMapper.querySkuByIds(ids);
        if (CollectionUtils.isEmpty(skus)) {
            throw new LyException(ExceptionEnum.GOODS_SKU_NOT_FOUND);
        }
        loadStockInSku(ids,skus);
        return skus;
    }

    /**
     * @Author Felix
     * @Description 减库存
     */
    @Transactional
    @Override
    public void decreaseStock(List<CartDTO> cartDTOS) {
        for (CartDTO cartDTO : cartDTOS) {
            int flag = stockMapper.decreaseStock(cartDTO.getSkuId(), cartDTO.getNum());
            if(flag != 1) {
                throw new LyException(ExceptionEnum.STOCK_NOT_ENOUGH);
            }
        }
    }

    public void loadStockInSku(List<Long> ids, List<TbSku> skus) {
        //查库存
        List<TbStock> stocks = stockMapper.selectByIdList(ids);
        if (CollectionUtils.isEmpty(stocks)) {
            throw new LyException(ExceptionEnum.GOODS_STOCK_NOT_FOUND);
        }

        Map<Long, Integer> stockMap = stocks.stream().
                collect(Collectors.toMap(TbStock::getSkuId, TbStock::getStock));
        skus.forEach(s -> s.setStock(stockMap.get(s.getId())));
    }

    /**
     * @description: 处理商品分类名称
     * @auther: Felix
     */
    private void handleCategory(List<SpuVo> spuList) {
        for (SpuVo spuVo : spuList) {
            List<TbCategory> categoryList = categoryService.selectByIdList(Arrays.asList(spuVo.getCid1(), spuVo.getCid2(), spuVo.getCid3()));
            List<String> nameList = categoryList.stream().map(TbCategory::getName).collect(Collectors.toList());
            spuVo.setCName(StringUtils.join(nameList, "/"));
        }
    }


}
