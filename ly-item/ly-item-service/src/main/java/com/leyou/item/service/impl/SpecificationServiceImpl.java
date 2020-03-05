package com.leyou.item.service.impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.dao.TbSpecGroupMapper;
import com.leyou.item.pojo.TbSpecGroup;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Override
    public List<TbSpecGroup> queryGroupByCid(Long cid) {
        List<TbSpecGroup> tbSpecGroups = tbSpecGroupMapper.queryGroupByCid(cid);
        if (CollectionUtils.isEmpty(tbSpecGroups)) {
            throw new LyException(ExceptionEnum.SPEC_GROUP_NOT_FOUND);
        }
        return tbSpecGroups;
    }
}
