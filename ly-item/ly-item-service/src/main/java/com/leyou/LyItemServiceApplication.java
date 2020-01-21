package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author Felix J
 * @Description item微服务启动类
 * @Date 2019/12/27 16:51
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.leyou.item.dao")
public class LyItemServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyItemServiceApplication.class);
    }
}
