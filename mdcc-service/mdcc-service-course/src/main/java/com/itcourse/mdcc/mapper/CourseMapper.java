package com.itcourse.mdcc.mapper;

import com.itcourse.mdcc.domain.Course;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itcourse.mdcc.domain.Teacher;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author daemon
 * @since 2024-07-15
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<Teacher> selectByCourseId(Long courseId);
}
