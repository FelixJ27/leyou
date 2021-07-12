package com.leyou.auth.web;

import com.leyou.auth.config.JwtProperties;
import com.leyou.auth.service.AuthService;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.CookieUtils;
import com.leyou.common.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Felix
 * @Description:
 */
@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtProperties prop;

    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        String token = authService.login(username, password);
        //写入cookie
        CookieUtils.setCookie(request, response, prop.getCookieName(), token, prop.getCookieMaxAge());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * @Author Felix
     * @Description 验证用户登录状态
     */
    @GetMapping("verify")
    public ResponseEntity<UserInfo> verify(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @CookieValue("LY_TOKEN")String token) {
        try {
            //解析token
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, prop.getPublicKey());
            //刷新token,重新生成token
            String newToken = JwtUtils.generateToken(userInfo, prop.getPrivateKey(), prop.getExpire());
            //写回cookie
            CookieUtils.setCookie(request,response, prop.getCookieName(), newToken);
            return ResponseEntity.ok(userInfo);
        }catch (Exception e) {
            //token已过期，或者token被篡改
            throw new LyException(ExceptionEnum.UNAUTHORIZED);
        }
    }
}
