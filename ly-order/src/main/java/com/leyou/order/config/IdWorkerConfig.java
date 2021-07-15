package com.leyou.order.config;

import com.leyou.common.utils.IdWorker;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Felix
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(IdWorkerProperties.class)
public class IdWorkerConfig {

    /**
     * @Author Felix
     * @Description 注册IdWork到spring
     */
    @Bean
    public IdWorker idWorker(IdWorkerProperties prop) {
        return new IdWorker(prop.getWorkerId(), prop.getDataCenterId());
    }
}
