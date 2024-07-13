package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.domain.Role;
import com.itcourse.mdcc.mapper.RoleMapper;
import com.itcourse.mdcc.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-13
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
