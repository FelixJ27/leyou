package com.leyou.item.web;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.JsonUtils;
import com.leyou.item.dao.TbSpecParamMapper;
import com.leyou.item.dao.TbSpuDetailMapper;
import com.leyou.item.pojo.TbSpuDetail;
import com.leyou.item.pojo.dto.SpecialSpecSerialize;
import com.sun.istack.internal.Nullable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Felix
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecificationControllerTest {
    public static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private TbSpecParamMapper tbSpecParamMapper;
    @Autowired
    private TbSpuDetailMapper spuDetailMapper;



}