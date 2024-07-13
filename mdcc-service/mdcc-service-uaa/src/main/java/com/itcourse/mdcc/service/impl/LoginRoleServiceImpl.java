package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.domain.LoginRole;
import com.itcourse.mdcc.mapper.LoginRoleMapper;
import com.itcourse.mdcc.service.ILoginRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户和角色中间表 服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-13
 */
@Service
public class LoginRoleServiceImpl extends ServiceImpl<LoginRoleMapper, LoginRole> implements ILoginRoleService {

}
