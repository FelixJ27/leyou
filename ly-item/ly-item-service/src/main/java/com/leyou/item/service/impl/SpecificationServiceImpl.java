package com.leyou.item.service.impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.JsonUtils;
import com.leyou.item.dao.TbSpecGroupMapper;
import com.leyou.item.dao.TbSpecParamMapper;
import com.leyou.item.dao.TbSpuDetailMapper;
import com.leyou.item.pojo.TbSpecGroup;
import com.leyou.item.pojo.TbSpecParam;
import com.leyou.item.pojo.TbSpuDetail;
import com.leyou.item.pojo.dto.SpecialSpecSerialize;
import com.leyou.item.pojo.dto.SpuDetailDTO;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private TbSpuDetailMapper spuDetailMapper;

    @Override
    public List<TbSpecGroup> queryGroupByCid(Long cid) {
        List<TbSpecGroup> tbSpecGroups = tbSpecGroupMapper.queryGroupByCid(cid);
        if (CollectionUtils.isEmpty(tbSpecGroups)) {
            throw new LyException(ExceptionEnum.SPEC_GROUP_NOT_FOUND);
        }
        return tbSpecGroups;
    }

    @Override
    public List<TbSpecParam> querySpecParamList(Long gid, Long cid, Boolean searching) {
        TbSpecParam specParam = new TbSpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setSearching(searching);
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

    /**
     * @Author Felix
     * @Description 更新旧genericSpec
     * @Param
     * @Return
     */
    @Transactional
    public void updateSpecDetail() {
        List<TbSpuDetail> spuDetailList = spuDetailMapper.selectAll();

        for (TbSpuDetail spuDetail : spuDetailList) {
            String json = spuDetail.getGenericSpec();
            String now = conversion(json);
            spuDetail.setGenericSpec(now);
            int count = spuDetailMapper.updateByPrimaryKeySelective(spuDetail);
            if (count != 1) {
                System.out.println("错误");
            }
        }
    }

    /**
     * @Author Felix
     * @Description 修改旧genericSpec
     * @Param
     * @Return
     */
    @Transactional
    public String conversion(String genericSpecOrigin) {
        List spuDetailDTOList = JsonUtils.parse(genericSpecOrigin, List.class);

        String g = "{";
        for (Object o : spuDetailDTOList) {
            LinkedHashMap<String, List> spuDetailDTO = (LinkedHashMap<String, List>) o;
            List params = spuDetailDTO.get("params");
            for (Object obj : params) {
                LinkedHashMap<String, Object> pk = (LinkedHashMap<String, Object>) obj;
                String k = (String) pk.get("k");
                //System.out.println("k = " + k);
                List<Long> ids = tbSpecParamMapper.selectIdByName(k);
                if (CollectionUtils.isEmpty(ids)) {
                    continue;
                }
                Long id = ids.get(0);
                if (id == 4 || id == 11 || id == 12 || id == 13 || id > 18) {
                    continue;
                }
                Object v = pk.get("v");
                Boolean numerical = (Boolean) pk.get("numerical");
                if (numerical != null && numerical) {
                    g = g + "\"" + id + "\":" + v + ",";
                } else {
                    g = g + "\"" + id + "\":" + "\"" + v + "\",";
                }
            }
        }

        g = g.substring(0, g.lastIndexOf(",")) + "}";
        return g;
    }

    /**
     * @Author Felix
     * @Description 更新旧SpecialSpec
     * @Param
     * @Return
     */
    @Transactional
    public void updateSpecialSpec() {
        List<TbSpuDetail> spuDetailList = spuDetailMapper.selectAll();

        for (TbSpuDetail spuDetail : spuDetailList) {
            String json = spuDetail.getSpecialSpec();
            String serialize = transferSpecialSpec(json);
            spuDetail.setSpecialSpec(serialize);
            int count = spuDetailMapper.updateByPrimaryKeySelective(spuDetail);
            if (count != 1) {
                System.out.println("错误");
            }
        }
    }

    /**
     * @Author Felix
     * @Description 修改旧SpecialSpec
     * @Param
     * @Return
     */
    @Transactional
    public String transferSpecialSpec(String specialSpecOrigin) {
        HashMap specMap = JsonUtils.parse(specialSpecOrigin, HashMap.class);
        List<String> color = (List<String>) specMap.get("机身颜色");
        List<String> memory = (List<String>) specMap.get("内存");
        List<String> storage = (List<String>) specMap.get("机身存储");
        SpecialSpecSerialize serialize = new SpecialSpecSerialize();
        serialize.setColor(color);
        serialize.setMemory(memory);
        serialize.setStorage(storage);
        return JsonUtils.serialize(serialize);
    }

}
