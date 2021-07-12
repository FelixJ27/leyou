package com.leyou.api;

import com.leyou.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Felix
 * @Description:
 */
public interface UserApi {
    /**
     * @Author Felix
     * @Description 根据用户名和密码查询用户
     */
    @GetMapping("query")
    User queryUserByUsernameAndPassword(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );
}
