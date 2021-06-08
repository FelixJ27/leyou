package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
}
