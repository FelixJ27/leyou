package com.leyou.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author Felix J
 * @Description
 * @Date 2019/12/27 16:51
 */
@EnableEurekaServer
@SpringBootApplication
public class LyRegistryApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(LyRegistryApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LyRegistryApplication.class);
    }
}
