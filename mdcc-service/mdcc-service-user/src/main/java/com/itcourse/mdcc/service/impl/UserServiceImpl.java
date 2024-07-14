package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.client.UaaFeignClient;
import com.itcourse.mdcc.constants.BaseConstants;
import com.itcourse.mdcc.domain.Login;
import com.itcourse.mdcc.domain.User;
import com.itcourse.mdcc.dto.UserRegisterDto;
import com.itcourse.mdcc.mapper.UserMapper;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itcourse.mdcc.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 会员登录账号 服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UaaFeignClient uaaFeignClient;

    /**
     * 用户注册
     *
     * @param userRegisterDto 用户注册对象
     */
    @Override
    public void register(UserRegisterDto userRegisterDto) {
        // 1.验证图形验证码
        String imageKey = BaseConstants.Verify.IMAGE_CODE + userRegisterDto.getImageCode();
        String imageCode = (String) redisTemplate.opsForValue().get(imageKey);
        AssertUtil.isNotEmpty(imageCode, "图形验证码失效，请刷新");
        AssertUtil.isEqualsIgnoreCase(imageCode, userRegisterDto.getImageCode(), "图形验证码不正确");

        // 2.验证短信验证码
        String smsKey = BaseConstants.Verify.SMS_CODE + userRegisterDto.getMobile();
        String smsCode = (String) redisTemplate.opsForValue().get(smsKey);
        AssertUtil.isNotEmpty(smsCode, "短信验证码失效，请重新发送");
        AssertUtil.isEquals(smsCode, userRegisterDto.getSmsCode(), "短信验证码不一致");

        // 3.存入登录表
        Login login = Login.builder()
                .username(userRegisterDto.getMobile())
                .password(userRegisterDto.getPassword())
                .build();
        JSONResult jsonResult = uaaFeignClient.saveOrUpdate(login);
        Long loginId = Long.valueOf(jsonResult.getData().toString());

        // 4.存入登录表
        User user = User.builder()
                .phone(userRegisterDto.getMobile())
                .password(userRegisterDto.getPassword())
                .loginId(loginId)
                .build();
        userMapper.insert(user);
    }
}
