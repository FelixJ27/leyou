package com.leyou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Felix J
 * @Description item微服务启动类
 * @Date 2019/12/27 16:51
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.leyou.item.dao")
public class LyItemServiceApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(LyItemServiceApplication.class);
    }

   /* @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LyItemServiceApplication.class);
    }*/
}
