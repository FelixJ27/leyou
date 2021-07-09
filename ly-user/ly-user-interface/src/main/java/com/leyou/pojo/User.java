package com.leyou.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 4, max = 32, message = "用户名长度必须在4~32位")
    private String username;
    @Length(min = 4, max = 32, message = "密码长度必须在4~32位")
    @JsonIgnore
    private String password;
    @Pattern(regexp = "/^(0|86|17951)?(13[0-9]|15[0123456789]|17[678]|18[0-9]|14[57])[0-9]{8}$/", message = "手机号不正确")
    private String phone;

    private Date created;
    @JsonIgnore
    private String salt;
}