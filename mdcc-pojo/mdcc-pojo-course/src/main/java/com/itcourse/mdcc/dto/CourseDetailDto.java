package com.itcourse.mdcc.dto;

import com.itcourse.mdcc.domain.*;
import lombok.Data;

import java.util.List;

@Data
public class CourseDetailDto {
    //课程
    private Course course;
    private CourseMarket courseMarket;
    //课程详情
    private CourseDetail courseDetail;
    //课程章节
    private List<CourseChapter> courseChapters;
    //教师列表
    private List<Teacher> teachers;

}
