package com.leetgou.sms.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.leetgou.sms.config.SmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * @Author: Felix
 * @Description:
 */
@Slf4j
@Component
@EnableConfigurationProperties(SmsProperties.class)
public class SmsUtil {

    @Autowired
    private SmsProperties prop;
    @Resource
    private StringRedisTemplate redisTemplete;

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    private static final String KEY_PROFIX = "sms:phone:";
    private static final long SMS_MIN_INTERVAL_IN_MILLIS = 60000;

    public SendSmsResponse sendSms(String phoneNumber, String signName, String templateCode, String templateParam) {
        String key = KEY_PROFIX + phoneNumber;
        //读取时间
        String lastTime = redisTemplete.opsForValue().get(key);
        //限流
        if (StringUtils.isNotBlank(lastTime)) {
            Long last = Long.valueOf(lastTime);
            if (System.currentTimeMillis() - last < SMS_MIN_INTERVAL_IN_MILLIS) {
                return null;
            }
        }
        try {
            //可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", prop.getAccessKeyId(), prop.getAccessKeySecret());
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);
            //必填:待发送手机号
            request.setPhoneNumbers(phoneNumber);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(signName);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam(templateParam);

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse resp = acsClient.getAcsResponse(request);

            if (!"OK".equals(resp.getCode())) {
                log.error("[短信服务]发送短信失败，phoneNumber:{}，原因:{}", phoneNumber, resp.getMessage());
            }
            //发送短信成功后写入redis，指定生存时间为1分钟
            redisTemplete.opsForValue().set(key, String.valueOf(System.currentTimeMillis()), 1, TimeUnit.MINUTES);
            return resp;
        } catch (Exception e) {
            log.error("[短信服务]发送短信失败，phoneNumber:{}，原因:{}", phoneNumber, e);
            return null;
        }
    }
}
