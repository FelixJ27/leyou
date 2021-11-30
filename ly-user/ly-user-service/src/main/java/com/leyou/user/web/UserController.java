package com.leyou.user.web;

import com.leyou.pojo.User;
import com.leyou.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * @Author: Felix
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Author Felix
     * @Description 验证信息
     * @Param [data, type]
     * @Return org.springframework.http.ResponseEntity<java.lang.Boolean>
     */
    @GetMapping("/check/{data}/{type}")
    public ResponseEntity<Boolean> checkData(
            @PathVariable(name = "data") String data,
            @PathVariable(name = "type") Integer type
    ) {
        return ResponseEntity.ok(userService.checkData(data, type));
    }

    /**
     * @Author Felix
     * @Description 发送验证码
     * @Param [phone]
     * @Return org.springframework.http.ResponseEntity<java.lang.Void>
     */
    @PostMapping("code")
    public ResponseEntity<Void> sendCode(@RequestParam("phone") String phone) {
        userService.sendCode(phone);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * @Author Felix
     * @Description 注册
     */
    @PostMapping("register")
    public ResponseEntity<Void> register(@Valid User user, BindingResult result, @RequestParam("code") String code) {
        /*if (result.hasFieldErrors()) {
            throw new RuntimeException(result.getFieldErrors().stream()
                    .map(e -> e.getDefaultMessage()).collect(Collectors.joining("|")));
        }*/
        userService.register(user, code);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * @Author Felix
     * @Description 根据用户名和密码查询用户
     */
    @GetMapping("query")
    public ResponseEntity<User> queryUserByUsernameAndPassword(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        return ResponseEntity.ok(userService.queryUserByUsernameAndPassword(username, password));
    }

}
