package com.itcourse.mdcc.service;

import com.itcourse.mdcc.dto.SmsCodeDto;

public interface IVerifyCodeService {
    String getImageCode(String imgKey);

    void sendSmsCode(SmsCodeDto smsCodeDto);
}
