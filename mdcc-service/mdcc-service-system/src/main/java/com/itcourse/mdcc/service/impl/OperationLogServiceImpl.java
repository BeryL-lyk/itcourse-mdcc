package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.domain.OperationLog;
import com.itcourse.mdcc.mapper.OperationLogMapper;
import com.itcourse.mdcc.service.IOperationLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-13
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

}
