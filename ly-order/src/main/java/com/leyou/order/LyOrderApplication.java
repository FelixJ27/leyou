package com.leyou.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Felix
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.leyou.order.dao")
public class LyOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyOrderApplication.class, args);
    }
}
