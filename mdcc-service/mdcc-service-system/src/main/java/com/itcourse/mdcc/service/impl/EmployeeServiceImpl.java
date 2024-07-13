package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.domain.Employee;
import com.itcourse.mdcc.mapper.EmployeeMapper;
import com.itcourse.mdcc.service.IEmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
