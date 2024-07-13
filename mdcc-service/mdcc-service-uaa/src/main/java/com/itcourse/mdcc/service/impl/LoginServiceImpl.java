package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.domain.Login;
import com.itcourse.mdcc.mapper.LoginMapper;
import com.itcourse.mdcc.service.ILoginService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录表 服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-13
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements ILoginService {

}
