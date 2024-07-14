package com.itcourse.mdcc.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserRegisterDto {
    @NotBlank(message = "图形验证码为空")
    private String imageCode;

    @Length(min = 11, max = 11, message = "手机号格式不对")
    private String mobile;

    @NotBlank(message = "密码为空")
    private String password;

    @NotNull(message = "通道为空")
    private Integer regChannel;

    @NotBlank(message = "短信验证码为空")
    private String smsCode;
}
