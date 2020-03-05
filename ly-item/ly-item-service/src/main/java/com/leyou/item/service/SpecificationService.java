package com.leyou.item.service;

import com.leyou.item.pojo.TbSpecGroup;

import java.util.List;

/**
 * @Author Felix
 * @Description 规格参数service
 */
public interface SpecificationService {
    /**
     * 查询规格参数
     * @param cid
     * @return
     */
    List<TbSpecGroup> queryGroupByCid(Long cid);
}
