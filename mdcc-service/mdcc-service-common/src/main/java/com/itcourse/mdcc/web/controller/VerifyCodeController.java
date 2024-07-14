package com.itcourse.mdcc.web.controller;

import com.itcourse.mdcc.dto.SmsCodeDto;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.service.IVerifyCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/verifycode")
@Slf4j
@Api("验证码")
public class VerifyCodeController {
    @Autowired
    private IVerifyCodeService verifyCodeService;

    @ApiOperation("图片验证码")
    @GetMapping("/imageCode/{imgKey}")
    public JSONResult imageCode(@PathVariable String imgKey){
        log.info("imgKey:{}",imgKey);
        String base64 = verifyCodeService.getImageCode(imgKey);
        return JSONResult.success(base64);
    }

    @ApiOperation("短信验证码")
    @PostMapping("/sendSmsCode")
    public JSONResult sendSmsCode(@RequestBody SmsCodeDto smsCodeDto){
        verifyCodeService.sendSmsCode(smsCodeDto);
        return JSONResult.success();
    }
}
