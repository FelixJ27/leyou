package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.dao.TbCategoryMapper;
import com.leyou.item.dao.TbSpuDetailMapper;
import com.leyou.item.dao.TbSpuMapper;
import com.leyou.item.pojo.TbCategory;
import com.leyou.item.pojo.TbSpu;
import com.leyou.item.pojo.vo.SpuVo;
import com.leyou.item.service.GoodsService;
import com.leyou.item.service.TbCategoryService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Felix
 * @Description 商品ServiceImpl
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private TbSpuMapper spuMapper;
    @Autowired
    private TbSpuDetailMapper spuDetailMapper;
    @Autowired
    private TbCategoryService categoryService;

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

        PageInfo<SpuVo> pageInfo = new PageInfo<>();
        return new  PageResult<>(pageInfo.getTotal(), spuVoList);
    }

    private void handleCategory(List<SpuVo> spuList) {
        for (SpuVo spuVo : spuList) {
            List<TbCategory> categorieList = categoryService.selectByIdList(Arrays.asList(spuVo.getCid1(), spuVo.getCid2(), spuVo.getCid3()));
            List<String> nameList = categorieList.stream().map(TbCategory::getName).collect(Collectors.toList());
            spuVo.setCName(StringUtils.join(nameList, "/"));
        }
    }
}
