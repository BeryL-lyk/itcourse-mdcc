package com.itcourse.mdcc.service;

import com.itcourse.mdcc.domain.Course;
import com.baomidou.mybatisplus.service.IService;
import com.itcourse.mdcc.dto.CourseAddDto;
import com.itcourse.mdcc.dto.CourseDetailDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author daemon
 * @since 2024-07-15
 */
public interface ICourseService extends IService<Course> {

    void save(CourseAddDto dto);

    CourseDetailDto selectDetailData(Long id);
}
