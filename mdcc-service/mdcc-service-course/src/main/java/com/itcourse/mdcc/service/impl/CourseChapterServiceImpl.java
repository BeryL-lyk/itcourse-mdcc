package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.domain.CourseChapter;
import com.itcourse.mdcc.mapper.CourseChapterMapper;
import com.itcourse.mdcc.service.ICourseChapterService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程章节 ， 一个课程，多个章节，一个章节，多个视频 服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-15
 */
@Service
public class CourseChapterServiceImpl extends ServiceImpl<CourseChapterMapper, CourseChapter> implements ICourseChapterService {

}
