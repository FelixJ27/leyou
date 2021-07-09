package com.leyou.user.service.impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.NumberUtils;
import com.leyou.pojo.User;
import com.leyou.user.dao.UserMapper;
import com.leyou.user.service.UserService;
import com.leyou.user.utils.CodecUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Felix
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "user:verify:phone";

    /**
     * @Author Felix
     * @Description 数据验证
     * @Param [data, type]
     * @Return java.lang.Boolean
     */
    @Override
    public Boolean checkData(String data, Integer type) {
        User user = new User();
        //判断数据类型
        switch (type) {
            case 1:
                user.setUsername(data);
                break;
            case 2:
                user.setPhone(data);
                break;
            default:
                throw new LyException(ExceptionEnum.INVALID_USER_DATA_TYPE);
        }
        return userMapper.selectCount(user) == 0;
    }

    /**
     * @Author Felix
     * @Description 发送验证码
     */
    @Override
    public void sendCode(String phone) {
        String key = KEY_PREFIX + phone;

        String code = NumberUtils.generateCode(6);
        Map<String, String> msg = new HashMap<>();
        msg.put("phone", phone);
        msg.put("code", code);
        amqpTemplate.convertAndSend("ly.sms.exchange", "sms.verify.code", msg);
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);
    }

    @Override
    public void register(User user, String code) {
        //验证码校验
        String cacheCode = redisTemplate.opsForValue().get(KEY_PREFIX + user.getPhone());
        if (!StringUtils.equals(cacheCode, code)) {
            throw new LyException(ExceptionEnum.INVALID_VERIFY_CODE);
        }
        //生成盐值
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        //对密码加密
        user.setPassword(CodecUtils.md5Hex(user.getPassword(), salt));
        user.setCreated(new Date());
        userMapper.insertSelective(user);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        User record = new User();
        record.setUsername(username);
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new LyException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
        }
        //校验密码
        if (!StringUtils.equals(user.getPassword(), CodecUtils.md5Hex(password, user.getSalt()))) {
            throw new LyException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
        }
        return user;
    }
}
