package com.itcourse.mdcc.dto;

import com.itcourse.mdcc.domain.Course;
import com.itcourse.mdcc.domain.CourseDetail;
import com.itcourse.mdcc.domain.CourseMarket;
import com.itcourse.mdcc.domain.CourseResource;
import lombok.Data;

import java.util.List;
@Data
public class CourseAddDto {
    public Course course;
    public CourseDetail courseDetail;
    public CourseMarket courseMarket;
    public CourseResource courseResource;
    public List<Long> teacharIds;
}

