package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.domain.CourseTeacher;
import com.itcourse.mdcc.mapper.CourseTeacherMapper;
import com.itcourse.mdcc.service.ICourseTeacherService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程和老师的中间表 服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-15
 */
@Service
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper, CourseTeacher> implements ICourseTeacherService {

}
