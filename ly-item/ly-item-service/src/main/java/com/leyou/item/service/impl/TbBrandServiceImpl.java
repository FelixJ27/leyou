package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.dao.TbBrandMapper;
import com.leyou.item.pojo.TbBrand;
import com.leyou.item.service.TbBrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Felix
 * @Description
 * @Date 2020/3/2 14:42
 * 面向对象面向卿，不负代码不负君
 */
@Service
public class TbBrandServiceImpl implements TbBrandService {
    @Resource
    private TbBrandMapper tbBrandMapper;

    /**
     * @description: 分页查询品牌
     * @auther: Felix
     * @date: 2020/3/2 15:13
     */
    @Override
    public PageResult<TbBrand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        PageHelper.startPage(page, rows);
        TbBrand tbBrand = new TbBrand();
        tbBrand.setName(key);
        List<TbBrand> tbBrandList = tbBrandMapper.queryBrandByPage(tbBrand);
        if (CollectionUtils.isEmpty(tbBrandList)) {
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        PageInfo<TbBrand> pageInfo = new PageInfo<>(tbBrandList);
        return new PageResult<>(pageInfo.getTotal(), tbBrandList);
    }

    /**
     * @description: 新增品牌
     * @auther: Felix
     * @date: 2020/3/2 17:53
     */
    @Override
    @Transactional
    public void saveBrand(TbBrand tbBrand, List<Long> cids) {
        int flag = tbBrandMapper.insertSelective(tbBrand);
        if (flag != 1) {
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }

        for (Long cid : cids) {
            flag = tbBrandMapper.insertCategoryBrand(cid, tbBrand.getId());
            if (flag != 1) {
                throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
            }
        }
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(TbBrand record) {
        return 0;
    }

    @Override
    public int insertSelective(TbBrand record) {
        return 0;
    }

    @Override
    public TbBrand selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(TbBrand record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TbBrand record) {
        return 0;
    }
}
