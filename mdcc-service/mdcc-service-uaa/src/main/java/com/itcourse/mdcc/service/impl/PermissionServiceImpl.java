package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.domain.Permission;
import com.itcourse.mdcc.mapper.PermissionMapper;
import com.itcourse.mdcc.service.IPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-13
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
