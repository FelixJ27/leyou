package com.leyou.repository;

import com.leyou.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: Felix
 * @Description:
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}
