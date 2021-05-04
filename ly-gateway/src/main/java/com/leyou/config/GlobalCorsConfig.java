package com.leyou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author: Felix
 * @Description: Cors跨域配置
 * @Date: 2020/2/26 22:33
 * 面向对象面向卿，不负代码不负君
 */
@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        //1.添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        //允许的域，不要写*，否则cookie无法使用
        //config.addAllowedOrigin("http://manage.leetgou.com");
        config.addAllowedOrigin("http://manage.leyou.com");
        //是否发送cookie信息
        config.setAllowCredentials(true);
        //允许请求的方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        //允许的头信息
        config.addAllowedHeader("*");
        //有效时长
        config.setMaxAge(3600L);//3600秒

        //2.添加映射路径（这里拦截一切请求）
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter
        return new CorsFilter(configurationSource);
    }
}