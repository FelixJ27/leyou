package com.leyou.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Felix
 * @Description:
 * @Date: 2021/5/27 14:49
 * 面向对象面向卿，不负代码不负君
 */
@Data
@Document(indexName = "goods", type = "docs", shards = 1, replicas = 0)
public class Goods {

    @Id
    private Long id;//spuid
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String all;//所有需要被搜索的信息，包括标题，品牌，分类
    @Field(type = FieldType.Keyword, index = false)
    private String subTitle;//卖点
    private Long brandId;//品牌id
    private Long cid1;
    private Long cid2;
    private Long cid3;
    private Date createTime;//spu创建时间
    private Set<Long> price;//价格
    @Field(type = FieldType.Keyword, index = false)
    private String skus;//sku信息的json结构
    private Map<String, Object> specs;//可搜索的规格参数，k是参数名，v是参数值

}
