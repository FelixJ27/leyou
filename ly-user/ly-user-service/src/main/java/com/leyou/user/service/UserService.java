package com.leyou.user.service;

import com.leyou.pojo.User;

/**
 * @Author: Felix
 * @Description:
 */
public interface UserService {
    /**
     * @Author Felix
     * @Description 数据验证
     * @Param
     * @Return
     */
    Boolean checkData(String data, Integer type);

    /**
     * @Author Felix
     * @Description 发送验证码
     */
    void sendCode(String phone);

    /**
     * @Author Felix
     * @Description 注册
     */
    void  register(User user, String code);
}
