package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.domain.UserAddress;
import com.itcourse.mdcc.mapper.UserAddressMapper;
import com.itcourse.mdcc.service.IUserAddressService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收货地址 服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-13
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements IUserAddressService {

}
