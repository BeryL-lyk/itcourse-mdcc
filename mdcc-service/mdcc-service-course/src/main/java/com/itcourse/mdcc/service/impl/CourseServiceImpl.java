package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.constants.BaseConstants;
import com.itcourse.mdcc.domain.*;
import com.itcourse.mdcc.dto.CourseAddDto;
import com.itcourse.mdcc.mapper.*;
import com.itcourse.mdcc.service.ICourseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itcourse.mdcc.service.ITeacherService;
import com.itcourse.mdcc.utils.AssertUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-15
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private ITeacherService teacherService;
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

    /**-----------------------------------------------------------------
     * Description： 保存课程
     * 1.判断参数
     * 2.判断重复
     * 3.保存课程
     * 4.保存详情，设置课程ID作为详情ID
     * 5.保存营销，设置课程ID作为详情ID
     * 6.给选择的课程分类的课程数量+1
     *-----------------------------------------------------------------*/
    @Override
    public void save(CourseAddDto dto) {
        //* 1.判断参数
        Course course = dto.getCourse();
        CourseDetail courseDetail = dto.getCourseDetail();
        CourseMarket courseMarket = dto.getCourseMarket();

        AssertUtil.isNotEmpty(course.getName(),"课程名不可为空" );
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
        courseType.setTotalCount(courseType.getTotalCount()+1);
        courseTypeMapper.updateById(courseType);

        //保存课件
        CourseResource courseResource = dto.getCourseResource();
        courseResource.setCourseId(course.getId());
        courseResourceMapper.insert(dto.getCourseResource());

        //保存老师
        List<Long> teacharIds = dto.getTeacharIds();
        if(teacharIds != null && !teacharIds.isEmpty()){
            teacharIds.forEach(teacharId->{
                courseTeacherMapper.insert(new CourseTeacher(null,teacharId,course.getId()));
            });
        }
    }
}
