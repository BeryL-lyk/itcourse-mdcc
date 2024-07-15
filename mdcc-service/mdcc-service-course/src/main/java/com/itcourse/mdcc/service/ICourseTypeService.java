package com.itcourse.mdcc.service;

import com.itcourse.mdcc.domain.CourseType;
import com.baomidou.mybatisplus.service.IService;
import com.itcourse.mdcc.result.JSONResult;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 课程目录 服务类
 * </p>
 *
 * @author daemon
 * @since 2024-07-15
 */
public interface ICourseTypeService extends IService<CourseType> {

    JSONResult getTreeData();

    @Override
    boolean deleteById(Serializable serializable);

    @Override
    boolean updateById(CourseType courseType);

    @Override
    boolean insert(CourseType courseType);
}
