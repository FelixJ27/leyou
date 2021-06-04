package com.leyou.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author Felix J
 * @Description
 * @Date 2019/12/27 17:14
 */
@EnableZuulProxy
@SpringCloudApplication
public class LyGatewayApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LyGatewayApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(LyGatewayApplication.class, args);
    }
}
