package com.kalo.entity;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Kalo
 * @create 2020-04-20 15:54
 */
@Data
public class UserModel {
    private Integer id;

    @NotBlank(message = "姓名不能为空")
//    @Max(value = 50, message = "长度不能超过50个字符")
    private String name;

    @NotNull(message = "性别不能为空")
    private Byte gender;

    @NotNull(message = "年龄不能为空")
    @Min(value = 0, message = "年龄不能小于0")
    @Max(value = 200, message = "年龄不能大于200")
    private Integer age;

    @NotBlank
//    @Max(value = 50, message = "长度不能超过50个字符")
    private String telphone;

    @NotBlank(message = "验证码不能为空")
    private String otpCode;


    @NotBlank
//    @Max(value = 50, message = "密码长度不能超过50个字符")
    private String encrptPassword;
}
