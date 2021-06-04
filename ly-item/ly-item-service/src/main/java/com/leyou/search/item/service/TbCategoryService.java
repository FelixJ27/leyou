package com.leyou.search.item.service;

import com.leyou.search.item.pojo.TbCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Felix J
 * @Description
 * @Date 2020/1/14 17:16
 * 面向对象面向卿，不负代码不负君
 */
@Service
public interface TbCategoryService {

    List<TbCategory> queryCategoryListByPid(Long pid);

    /**
     * @description: 通过id集合查询分类
     * @auther: Felix
     */
    List<TbCategory> selectByIdList(List<Long> ids);
}
