package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.domain.Menu;
import com.itcourse.mdcc.mapper.MenuMapper;
import com.itcourse.mdcc.service.IMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-13
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
