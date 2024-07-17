package com.itcourse.mdcc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.itcourse.mdcc.client.MediaFeignClient;
import com.itcourse.mdcc.constants.BaseConstants;
import com.itcourse.mdcc.domain.*;
import com.itcourse.mdcc.dto.CourseAddDto;
import com.itcourse.mdcc.dto.CourseDetailDto;
import com.itcourse.mdcc.mapper.*;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.service.ICourseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itcourse.mdcc.service.ITeacherService;
import com.itcourse.mdcc.utils.AssertUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-15
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private MediaFeignClient mediaFeignClient;

    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseDetailMapper courseDetailMapper;
    @Autowired
    private CourseMarketMapper courseMarketMapper;
    @Autowired
    private CourseTypeMapper courseTypeMapper;
    @Autowired
    private CourseResourceMapper courseResourceMapper;
    @Autowired
    private CourseTeacherMapper courseTeacherMapper;
    @Autowired
    private CourseChapterMapper courseChapterMapper;

    @Override
    public CourseDetailDto selectDetailData(Long courseId) {
        CourseDetailDto dto = new CourseDetailDto();

        //查询课程
        Course course = baseMapper.selectById(courseId);
        AssertUtil.isNotNull(course,"无效的课程");
//        AssertUtil.isEquals(course.getStatus(),Course.STATUS_ONLINE , "课程没有上线");


        CourseDetail courseDetail = courseDetailMapper.selectById(courseId);


        //查询教师
        List<Teacher> teachers = courseMapper.selectByCourseId(courseId);



        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);

        //3.查询章节
        List<CourseChapter> courseChapters = getCourseChapterByCourseId(courseId);

        dto.setCourse(course);
        dto.setCourseDetail(courseDetail);
        dto.setTeachers(teachers);
        dto.setCourseMarket(courseMarket);
        dto.setCourseChapters(courseChapters);

        return dto;
    }

    /**-----------------------------------------------------------------
     * Description： 保存课程
     * 1.判断参数
     * 2.判断重复
     * 3.保存课程
     * 4.保存详情，设置课程ID作为详情ID
     * 5.保存营销，设置课程ID作为详情ID
     * 6.给选择的课程分类的课程数量+1
     * -----------------------------------------------------------------
     */
    @Override
    public void save(CourseAddDto dto) {
        //* 1.判断参数
        Course course = dto.getCourse();
        CourseDetail courseDetail = dto.getCourseDetail();
        CourseMarket courseMarket = dto.getCourseMarket();

        AssertUtil.isNotEmpty(course.getName(), "课程名不可为空");
        //* 2.判断重复，省略..

        //* 3.保存课程
        course.setStatus(BaseConstants.CourseConstants.STATUS_OFF_LIINE);

        //获取登录信息 省略
//        Login login = LoginContext.getLogin();

        course.setLoginId(1l);
        course.setLoginUserName("admin");

        List<String> teacharNames = teacherService.selectBatchIds(dto.getTeacharIds()).stream().map(t -> t.getName()).collect(Collectors.toList());
        String teacharNameStr = StringUtils.join(teacharNames.toArray(), ",");
        course.setTeacherNames(teacharNameStr);
        super.insert(course);


        //* 4.保存详情，设置课程ID作为详情ID
        courseDetail.setId(course.getId());
        courseDetailMapper.insert(courseDetail);

        //* 5.保存营销，设置课程ID作为详情ID
        courseMarket.setId(course.getId());
        courseMarketMapper.insert(courseMarket);

        //* 6.给选择的课程分类的课程数量+1
        CourseType courseType = courseTypeMapper.selectById(course.getCourseTypeId());
        courseType.setTotalCount(courseType.getTotalCount() + 1);
        courseTypeMapper.updateById(courseType);

        //保存课件
        CourseResource courseResource = dto.getCourseResource();
        courseResource.setCourseId(course.getId());
        courseResourceMapper.insert(dto.getCourseResource());

        //保存老师
        List<Long> teacharIds = dto.getTeacharIds();
        if (teacharIds != null && !teacharIds.isEmpty()) {
            teacharIds.forEach(teacharId -> {
                courseTeacherMapper.insert(new CourseTeacher(null, teacharId, course.getId()));
            });
        }
    }

    private List<CourseChapter> getCourseChapterByCourseId(Long courseId) {

        Wrapper<CourseChapter> chapterQuery = new EntityWrapper<>();
        chapterQuery.eq("course_id",courseId);
        List<CourseChapter> courseChapters = courseChapterMapper.selectList(chapterQuery);


        //4.远程获取视频列表
        JSONResult jsonResult = mediaFeignClient.getByCourseId(courseId);

        AssertUtil.isTrue(jsonResult.isSuccess(), "课程章节获取失败");
        AssertUtil.isNotNull(jsonResult.getData(), "课程章节获取失败");

        //获取视频列表
        List<MediaFile> mediaFiles = JSON.parseArray(JSONObject.toJSONString(jsonResult.getData()), MediaFile.class);


        Map<Long, CourseChapter> courseChapterMap = courseChapters.stream().collect(Collectors.toMap(CourseChapter::getId, CourseChapter -> CourseChapter));

        //媒体文件添加到章节
        mediaFiles.forEach(mediaFile -> {
            CourseChapter courseChapter = courseChapterMap.get(mediaFile.getChapterId());
            if (courseChapter != null) {
                courseChapter.getMediaFiles().add(mediaFile);
            }
        });

        return courseChapters;
    }
}
