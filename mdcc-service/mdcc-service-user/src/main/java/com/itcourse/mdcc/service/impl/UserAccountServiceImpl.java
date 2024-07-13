package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.domain.UserAccount;
import com.itcourse.mdcc.mapper.UserAccountMapper;
import com.itcourse.mdcc.service.IUserAccountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-13
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements IUserAccountService {

}
