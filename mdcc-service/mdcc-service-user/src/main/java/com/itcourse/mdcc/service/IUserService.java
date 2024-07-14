package com.itcourse.mdcc.service;

import com.itcourse.mdcc.domain.User;
import com.baomidou.mybatisplus.service.IService;
import com.itcourse.mdcc.dto.UserRegisterDto;

/**
 * <p>
 * 会员登录账号 服务类
 * </p>
 *
 * @author daemon
 * @since 2024-07-13
 */
public interface IUserService extends IService<User> {

    void register(UserRegisterDto userRegisterDto);
}
