package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Felix
 * @Description 面向对象面向卿，不负代码不负君
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LyUploadApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(LyUploadApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LyUploadApplication.class);
    }
}
