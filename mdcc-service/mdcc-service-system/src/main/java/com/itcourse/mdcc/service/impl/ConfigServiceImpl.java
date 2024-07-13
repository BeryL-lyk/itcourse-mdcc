package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.domain.Config;
import com.itcourse.mdcc.mapper.ConfigMapper;
import com.itcourse.mdcc.service.IConfigService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-13
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
