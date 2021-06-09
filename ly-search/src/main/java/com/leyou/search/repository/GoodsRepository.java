package com.leyou.search.repository;

import com.leyou.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: Felix
 * @Description:
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}
