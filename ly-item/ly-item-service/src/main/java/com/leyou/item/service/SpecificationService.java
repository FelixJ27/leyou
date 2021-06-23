package com.leyou.item.service;

import com.leyou.item.pojo.TbSpecGroup;
import com.leyou.item.pojo.TbSpecParam;
import com.leyou.item.pojo.TbSpuDetail;

import java.util.List;

/**
 * @Author Felix
 * @Description 规格参数service
 */
public interface SpecificationService {
    /**
     * 查询规格组
     *
     * @param cid
     * @return
     */
    List<TbSpecGroup> queryGroupByCid(Long cid);

    /**
     * 查询规格参数组下的参数名
     *
     * @param gid
     * @return
     */
    List<TbSpecParam> querySpecParamList(Long gid, Long cid, Boolean searching);

    /**
     * 新增规格参数组下参数名
     *
     * @param specParam
     * @return
     */
    void addSpecParam(TbSpecParam specParam);

    /**
     * 跟新规格参数组下参数名
     *
     * @param specParam
     * @return
     */
    void updateSpecParam(TbSpecParam specParam);

    /**
     * 删除规格参数组下参数名
     *
     * @param id
     * @return
     */
    void deleteSpecParam(Long id);

    /**
     * @Author Felix
     * @Description 更新specDetail
     * @Param
     * @Return
     */
    void updateSpecDetail();

    String conversion(String genericSpecOrigin);

    List<TbSpecGroup> queryListByCid(Long cid);
}
