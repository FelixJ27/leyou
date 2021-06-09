package com.leyou.search.repository;

import com.leyou.search.client.GoodsClient;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.TbSpu;
import com.leyou.item.pojo.vo.SpuVo;
import com.leyou.search.pojo.Goods;
import com.leyou.search.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author: Felix
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsRepositoryTest {

    @Autowired
    private ElasticsearchRestTemplate template;

    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    private SearchService searchService;
    @Autowired
    private GoodsRepository goodsRepository;

    @Test
    public void testCreateIndex() {
        template.createIndex(Goods.class);
        template.putMapping(Goods.class);
    }

    @Test
    public void loadData() {
        int page = 1;
        int rows = 100;
        int size = 0;
        do {
            //查询spu信息
            PageResult<SpuVo> result = goodsClient.querySpuByPage(page, null, true, rows);
            List<SpuVo> spuVoList = result.getItems();
            if (CollectionUtils.isEmpty(spuVoList)) {
                break;
            }
            //构建成goods
            List<TbSpu> spuList = spuVoList.stream().map(spuVo -> new TbSpu(spuVo.getId(),
                    spuVo.getTitle(),
                    spuVo.getSubTitle(),
                    spuVo.getCid1(),
                    spuVo.getCid2(),
                    spuVo.getCid3(),
                    spuVo.getBrandId(),
                    spuVo.getSaleable(),
                    spuVo.getValid(),
                    spuVo.getCreateTime(),
                    spuVo.getLastUpdateTime())).collect(Collectors.toList());

            List<Goods> goodsList = spuList.stream().map(searchService::buildGoods).collect(Collectors.toList());
            //存入索引
            goodsRepository.saveAll(goodsList);
            //翻页
            page++;
            size = spuList.size();
        } while (size == 100);

    }
}