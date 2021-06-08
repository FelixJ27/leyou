package com.leyou.repository;

import com.leyou.pojo.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Author: Felix
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsRepositoryTest {

    @Autowired
    private ElasticsearchRestTemplate template;


    @Test
    public void testCreateIndex(){
        template.createIndex(Goods.class);
        template.putMapping(Goods.class);
    }

}