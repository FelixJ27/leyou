package com.leyou.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Author Felix
 * @Description 上传属性
 * 面向对象面向卿，不负代码不负君
 */
@Data
@ConfigurationProperties("ly.upload")
public class UploadProperties {
    /**
     * 前缀基础路径
     */
    private String baseUrl;
    /**
     * 允许的类型
     */
    private List<String> allowTypes;
}
