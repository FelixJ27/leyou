package com.leyou.search.item.service.impl;

import com.leyou.search.common.enums.ExceptionEnum;
import com.leyou.search.common.exception.LyException;
import com.leyou.search.item.dao.TbCategoryMapper;
import com.leyou.search.item.pojo.TbCategory;
import com.leyou.search.item.service.TbCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author Felix J
 * @Description
 * @Date 2020/1/14 17:48
 * 面向对象面向卿，不负代码不负君
 */
@Service
public class TbCategoryServiceImpl implements TbCategoryService {
    @Autowired
    private TbCategoryMapper tbCategoryMapper;

    @Override
    public List<TbCategory> queryCategoryListByPid(Long pid) {
        TbCategory category = new TbCategory();
        category.setParentId(pid);
        //List<TbCategory> categoryList = tbCategoryMapper.select(category);
        List<TbCategory> categoryList = tbCategoryMapper.select(category);
        if (CollectionUtils.isEmpty(categoryList)) {
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return categoryList;
    }

    /**
     * @description: 通过id集合查询分类
     * @auther: Felix
     */
    @Override
    public List<TbCategory> selectByIdList(List<Long> ids) {
        List<TbCategory> categoryList = tbCategoryMapper.selectByIdList(ids);
        if (CollectionUtils.isEmpty(categoryList)) {
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return categoryList;
    }
}
