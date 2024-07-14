package com.itcourse.mdcc.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


@Data
public class SmsCodeDto {
    @NotBlank(message = "图形验证码为空")
    private String imageCode;

    @NotBlank(message = "图片验证码的key为空")
    private String imageCodeKey;

    @Length(max = 11, min = 11, message = "手机号格式错误")
    private String mobile;
}
