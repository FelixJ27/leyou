package com.leyou.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.leyou.client.CategoryClient;
import com.leyou.item.pojo.*;
import com.leyou.pojo.Goods;
import com.leyou.client.BrandClient;
import com.leyou.client.GoodsClient;
import com.leyou.client.SpecificationClient;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Felix
 * @Description: 查询service
 */
@Service
public class SearchService {

    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    private BrandClient brandClient;
    @Autowired
    private SpecificationClient specificationClient;

    public Goods buildGoods(TbSpu spu) {
        //查询分类
        List<TbCategory> categoryList = categoryClient.queryCategoryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
        if (CollectionUtils.isEmpty(categoryList)) {
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        List<String> names = categoryList.stream().map(TbCategory::getName).collect(Collectors.toList());
        //查询品牌
        TbBrand brand = brandClient.queryBrandById(spu.getId());
        if (brand == null) {
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        //搜索字段
        String all = spu.getTitle() + StringUtils.join(names, " ");
        //查询sku
        List<TbSku> skuList = goodsClient.querySkuBySpuId(spu.getId());
        if (CollectionUtils.isEmpty(skuList)) {
            throw new LyException(ExceptionEnum.GOODS_SKU_NOT_FOUND);
        }
        //处理sku
        List<Map<String, Object>> skus = new ArrayList<>();
        //价格集合
        Set<Long> priceSet = new HashSet<>();
        for (TbSku sku : skuList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", sku.getId());
            map.put("title", sku.getTitle());
            map.put("price", sku.getPrice());
            map.put("image", StringUtils.substringBefore(sku.getImages(), ","));
            skus.add(map);
            priceSet.add(sku.getPrice());
        }

        //查询规格参数
        List<TbSpecParam> specParams = specificationClient.queryParamList(null, spu.getCid3());
        if (CollectionUtils.isEmpty(specParams)) {
            throw new LyException(ExceptionEnum.SPEC_PARAM_NOT_FOUND);
        }
        //查询商品详情
        TbSpuDetail spuDetail = goodsClient.queryDetailById(spu.getId());
        //获取通用规格参数
        Map<String, String> genericSpec = JsonUtils.parseMap(spuDetail.getSpecifications(), String.class, String.class);

        //获取特有规格参数
        Map<Long, List<String>> specialSpec = JsonUtils.nativeRead(spuDetail.getSpecialSpec(), new TypeReference<Map<Long, List<String>>>() {
        });

        //规格参数,k是规格参数的名字，v是规格参数的值
        Map<String, Object> specs = new HashMap<>();
        for (TbSpecParam param : specParams) {
            String key = param.getName();
            Object value = "";
            //判断是否是通用规格
            if (param.getGeneric()) {
                value = genericSpec.get(param.getId());
                //判断是否是数值类型
                if (param.getNumeric()) {
                    //处理成段
                     param.getSegments();
                }
            } else {
                value = specialSpec.get(param.getId());
            }
            specs.put(key, value);
        }

        Goods goods = new Goods();
        goods.setId(spu.getId());
        goods.setBrandId(spu.getBrandId());
        goods.setCid1(spu.getCid1());
        goods.setCid2(spu.getCid2());
        goods.setCid3(spu.getCid3());
        goods.setCreateTime(spu.getCreateTime());
        goods.setSubTitle(spu.getSubTitle());
        goods.setAll(all);//搜索字段，标题、分类、品牌、规格
        goods.setPrice(priceSet);//所有sku的价格
        goods.setSkus(JsonUtils.serialize(skus)); //所有sku集合的json格式
        goods.setSpecs(null);//TODO 所有可搜索的规格参数
        return null;
    }

    private String chooseSegment(String value, TbSpecParam p) {
        double val = NumberUtils.toDouble(value);
        String result = "其他";
        //保存数值段
        for (String segment : p.getSegments().split(",")) {
            String[] segs = segment.split("-");
            //获取数值范围
            double begin = NumberUtils.toDouble(segs[0]);
            double end = Double.MAX_VALUE;
            if (segs.length == 2) {
                end = NumberUtils.toDouble(segs[1]);
            }
            //判断是否在范围内
            if (val >= begin && val < end) {
                if (segs.length == 1) {
                    result = segs[0] + p.getUnit();
                }
            }
        }
        return null;
    }
}
