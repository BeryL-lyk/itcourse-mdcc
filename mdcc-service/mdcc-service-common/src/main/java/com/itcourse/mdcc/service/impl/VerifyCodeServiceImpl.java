package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.constants.BaseConstants;
import com.itcourse.mdcc.dto.SmsCodeDto;
import com.itcourse.mdcc.exception.GlobalException;
import com.itcourse.mdcc.exception.GlobalExceptionEnum;
import com.itcourse.mdcc.service.IVerifyCodeService;
import com.itcourse.mdcc.utils.AssertUtil;
import com.itcourse.mdcc.utils.StrUtils;
import com.itcourse.mdcc.utils.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 获取图片的验证码
     *
     * @param imgKey 图片key
     * @return 验证码
     */
    @Override
    public String getImageCode(String imgKey) {
        // 1.判断参数是否为空
        if (imgKey == null) {
            throw new GlobalException(GlobalExceptionEnum.PARAM_IS_NULL_EXCEPTION);
        }
        // 2.生成一个4位图形验证码
        String imgCode = StrUtils.getComplexRandomString(BaseConstants.Verify.CODE_LENGTH);

        // 3.存入缓存
        String key = BaseConstants.Verify.IMAGE_CODE + imgKey;
        redisTemplate.opsForValue().set(key, imgCode, BaseConstants.Verify.TIMEOUT, TimeUnit.SECONDS);

        // 4.生成随机验证码
        String base64 = VerifyCodeUtils.verifyCode(BaseConstants.Verify.WIDTH, BaseConstants.Verify.HEIGHT, imgCode);
        return base64;
    }

    @Override
    public void sendSmsCode(SmsCodeDto smsCodeDto) {
        // 1.获取缓存中的图形验证码
        String imgCode = (String) redisTemplate.opsForValue().get(BaseConstants.Verify.IMAGE_CODE + smsCodeDto.getImageCodeKey());

        // 2.和前端传过来的验证码进行对比
        AssertUtil.isEqualsIgnoreCase(imgCode, smsCodeDto.getImageCode(), "验证码有误");

        // 3.验证通过后发送短信验证码
        String smsCode = StrUtils.getRandomString(BaseConstants.Verify.CODE_LENGTH);

        // 4.将生成的验证码存入缓存
        // 4.1验证是否存在，如果不存在直接存入
        String key = BaseConstants.Verify.SMS_CODE + smsCodeDto.getMobile();
        Boolean b = redisTemplate.opsForValue().setIfAbsent(key, smsCode, BaseConstants.Verify.TIMEOUT, TimeUnit.SECONDS);
        // 如果存在
        if (Boolean.FALSE.equals(b)){
            // 获取剩余时间
            Long expire = redisTemplate.opsForValue().getOperations().getExpire(key, TimeUnit.SECONDS);
            // 如果剩余时间大于EXPIRE，说明距离上次发送没有超过一分钟
            if (expire > BaseConstants.Verify.EXPIRE){
                throw new GlobalException("发送验证码过于频繁");
            }
            // 如果发送时间超过一分钟了，重新发送
            redisTemplate.opsForValue().set(key, smsCode, BaseConstants.Verify.TIMEOUT, TimeUnit.SECONDS);
        }

        //TODO 5.将验证码发送给用户
    }
}
