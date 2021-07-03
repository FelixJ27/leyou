package com.leetgou.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Felix
 * @Description: 获取yml中自定义属性
 */
@Data
@ConfigurationProperties(prefix = "ly.sms")
public class SmsProperties {

    private String accessKeyId;
    private String accessKeySecret;
    private String signName;
    private String verifyCodeTemplate;
}
