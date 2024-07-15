package com.itcourse.mdcc.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author daemon
 * @since 2024-07-15
 */
@TableName("t_course")
public class Course extends Model<Course> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 适用人群
     */
    @TableField("for_user")
    private String forUser;
    /**
     * 课程分类
     */
    @TableField("course_type_id")
    private Long courseTypeId;
    @TableField("grade_name")
    private String gradeName;
    /**
     * 课程等级
     */
    @TableField("grade_id")
    private Long gradeId;
    /**
     * 课程状态，下线：0 ， 上线：1
     */
    private Integer status;
    /**
     * 添加课程的后台用户的ID
     */
    @TableField("login_id")
    private Long loginId;
    /**
     * 添加课程的后台用户
     */
    @TableField("login_user_name")
    private String loginUserName;
    /**
     * 课程的开课时间
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * 课程的节课时间
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * 封面，云存储地址
     */
    private String pic;
    /**
     * 时长，以分钟为单位
     */
    @TableField("total_minute")
    private Integer totalMinute;
    @TableField("online_time")
    private Date onlineTime;
    /**
     * 章节数
     */
    @TableField("chapter_count")
    private Integer chapterCount;
    /**
     * 讲师，逗号分隔多个
     */
    @TableField("teacher_names")
    private String teacherNames;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForUser() {
        return forUser;
    }

    public void setForUser(String forUser) {
        this.forUser = forUser;
    }

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getTotalMinute() {
        return totalMinute;
    }

    public void setTotalMinute(Integer totalMinute) {
        this.totalMinute = totalMinute;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Integer getChapterCount() {
        return chapterCount;
    }

    public void setChapterCount(Integer chapterCount) {
        this.chapterCount = chapterCount;
    }

    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Course{" +
        ", id=" + id +
        ", name=" + name +
        ", forUser=" + forUser +
        ", courseTypeId=" + courseTypeId +
        ", gradeName=" + gradeName +
        ", gradeId=" + gradeId +
        ", status=" + status +
        ", loginId=" + loginId +
        ", loginUserName=" + loginUserName +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", pic=" + pic +
        ", totalMinute=" + totalMinute +
        ", onlineTime=" + onlineTime +
        ", chapterCount=" + chapterCount +
        ", teacherNames=" + teacherNames +
        "}";
    }
}
