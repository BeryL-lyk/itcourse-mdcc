package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.domain.User;
import com.itcourse.mdcc.mapper.UserMapper;
import com.itcourse.mdcc.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
