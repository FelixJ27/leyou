package com.leyou.item.service.impl;

import com.leyou.item.dao.TbCategoryMapper;
import com.leyou.item.pojo.TbCategory;
import com.leyou.item.service.TbCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Felix J
 * @Description
 * @Date 2020/1/14 17:48
 * 面向对象面向卿，不负代码不负君
 */
@Service
public class TbCategoryServiceImpl implements TbCategoryService {
    @Resource
    private TbCategoryMapper tbCategoryMapper;

    @Override
    public List<TbCategory> queryCategoryListByPid(Long pid) {
        TbCategory category = new TbCategory();
        category.setParentId(pid);
        List<TbCategory> categoryList = tbCategoryMapper.select(category);
        return null;
    }
}
