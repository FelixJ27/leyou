package com.leyou.item.service;

import com.leyou.common.enums.PageEnum;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.TbBrand;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @Author Felix
 * @Description 品牌种类service
 * @Date 2020/3/2 14:41
 * 面向对象面向卿，不负代码不负君
 */
public interface TbBrandService {
    /**
     * 分页查询品牌
     */
    PageResult<TbBrand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key);

    /**
     * 新增品牌
     * @param tbBrand
     * @param cids
     */
    void saveBrand(TbBrand tbBrand, List<Long> cids);

    /**
     * 通过cid查询品牌
     * @param cid
     * @return
     */
    List<TbBrand> queryByCategoryId(Long cid);

    int deleteByPrimaryKey(Long id);

    int insert(TbBrand record);

    int insertSelective(TbBrand record);

    TbBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbBrand record);

    int updateByPrimaryKey(TbBrand record);
}
