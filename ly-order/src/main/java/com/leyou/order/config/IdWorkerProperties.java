package com.leyou.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Author: Felix
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "ly.worker")
public class IdWorkerProperties {

    private long workerId;//当前机器ID

    private long dataCenterId;//序列号
}
