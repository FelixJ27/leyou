package com.leyou.search.item.service.impl;

import com.leyou.search.common.enums.ExceptionEnum;
import com.leyou.search.common.exception.LyException;
import com.leyou.search.item.dao.TbSpecGroupMapper;
import com.leyou.search.item.dao.TbSpecParamMapper;
import com.leyou.search.item.pojo.TbSpecGroup;
import com.leyou.search.item.pojo.TbSpecParam;
import com.leyou.search.item.service.SpecificationService;
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
    public List<TbSpecParam> querySpecParamList(Long gid, Long cid) {
        TbSpecParam specParam = new TbSpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        List<TbSpecParam> tbSpecParams = tbSpecParamMapper.querySpecParamList(specParam);
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
