package com.leyou.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Felix
 * @Description: es配置
 */
@Configuration
public class ElasticSearchConfig {
    @Bean
    public RestHighLevelClient esRestClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        //在这里配置你的elasticsearch的情况
                        new HttpHost("192.168.150.128", 9200, "http")
                )
        );
        return client;
    }

}
