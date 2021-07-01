package com.leyou.page.service;

import com.leyou.item.pojo.*;
import com.leyou.page.client.BrandClient;
import com.leyou.page.client.CategoryClient;
import com.leyou.page.client.GoodsClient;
import com.leyou.page.client.SpecificationClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Felix
 * @Description:
 */
@Slf4j
@Service
public class PageService {

    @Autowired
    private BrandClient brandClient;
    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    private SpecificationClient specClient;
    @Autowired
    private TemplateEngine templateEngine;

    public Map<String, Object> loadModel(Long spuId) {
        Map<String, Object> model = new HashMap<>();
        TbSpu spu = goodsClient.querySpuById(spuId);
        List<TbSku> skus = spu.getSkus();
        TbSpuDetail detail = spu.getSpuDetail();
        TbBrand brand = brandClient.queryBrandById(spu.getBrandId());
        List<TbCategory> categories = categoryClient.queryCategoryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
        List<TbSpecGroup> specs = specClient.queryGroupByCid(spu.getCid3());
        model.put("title", spu.getTitle());
        model.put("subTitle", spu.getSubTitle());
        model.put("skus", skus);
        model.put("detail", detail);
        model.put("brand", brand);
        model.put("categories", categories);
        model.put("specs", specs);
        return model;
    }

    /**
     * @Author Felix
     * @Description 创建静态页
     * @Param
     * @Return
     */
    public void createHtml(Long id) {
        //上下文
        Context context = new Context();
        context.setVariables(loadModel(id));
        //输出流
        File dest = new File("D:\\BaiduNetdiskDownload", id + ".html");
        if (dest.exists()) {
            dest.delete();
        }
        try (PrintWriter writer = new PrintWriter(dest, "UTF-8")) {
            //生成html
            templateEngine.process("item", context, writer);
        } catch (Exception e) {
            log.error("[静态页服务]生成静态页异常", e);
        }
    }

    /**
     * @Author Felix
     * @Description 删除静态页
     * @Param
     * @Return
     */
    public void deleteHtml(Long spuId) {
        File dest = new File("D:\\BaiduNetdiskDownload", spuId + ".html");
        if (dest.exists()) {
            dest.delete();
        }
    }
}
