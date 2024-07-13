package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.domain.LoginLog;
import com.itcourse.mdcc.mapper.LoginLogMapper;
import com.itcourse.mdcc.service.ILoginLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录记录 服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-13
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

}
