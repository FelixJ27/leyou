package com.leyou.item.service.impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.dao.TbSpecGroupMapper;
import com.leyou.item.dao.TbSpecParamMapper;
import com.leyou.item.pojo.TbSpecGroup;
import com.leyou.item.pojo.TbSpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author Felix
 * @Description 规格参数service
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private TbSpecGroupMapper tbSpecGroupMapper;

    @Autowired
    private TbSpecParamMapper tbSpecParamMapper;

    @Override
    public List<TbSpecGroup> queryGroupByCid(Long cid) {
        List<TbSpecGroup> tbSpecGroups = tbSpecGroupMapper.queryGroupByCid(cid);
        if (CollectionUtils.isEmpty(tbSpecGroups)) {
            throw new LyException(ExceptionEnum.SPEC_GROUP_NOT_FOUND);
        }
        return tbSpecGroups;
    }

    @Override
    public List<TbSpecParam> queryParamByGid(Long gid) {
        List<TbSpecParam> tbSpecParams = tbSpecParamMapper.queryParamByGid(gid);
        if (CollectionUtils.isEmpty(tbSpecParams)) {
            throw new LyException(ExceptionEnum.SPEC_PARAM_NOT_FOUND);
        }
        return tbSpecParams;
    }

    @Override
    @Transactional
    public void addSpecParam(TbSpecParam specParam) {
        int flag = tbSpecParamMapper.insertSelective(specParam);
        if (flag != 1) {
            throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
        }
    }

    @Override
    @Transactional
    public void updateSpecParam(TbSpecParam specParam) {
        int flag = tbSpecParamMapper.updateByPrimaryKeySelective(specParam);
        if (flag != 1) {
            throw new LyException(ExceptionEnum.UPDATE_SPEC_PARAM_FAIL);
        }
    }

    @Override
    @Transactional
    public void deleteSpecParam(Long id) {
        int flag = tbSpecParamMapper.deleteByPrimaryKey(id);
        if (flag != 1) {
            throw new LyException(ExceptionEnum.DELETE_SPEC_PARAM_FAIL);
        }
    }

}
