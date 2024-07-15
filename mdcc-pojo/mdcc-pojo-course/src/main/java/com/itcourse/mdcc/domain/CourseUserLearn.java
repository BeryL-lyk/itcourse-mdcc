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
@TableName("t_course_user_learn")
public class CourseUserLearn extends Model<CourseUserLearn> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("login_id")
    private Long loginId;
    @TableField("start_time")
    private Date startTime;
    @TableField("end_time")
    private Date endTime;
    /**
     * 0已购买，1已过期
     */
    private Integer state;
    @TableField("course_id")
    private Long courseId;
    @TableField("course_order_no")
    private String courseOrderNo;
    @TableField("create_time")
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseOrderNo() {
        return courseOrderNo;
    }

    public void setCourseOrderNo(String courseOrderNo) {
        this.courseOrderNo = courseOrderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CourseUserLearn{" +
        ", id=" + id +
        ", loginId=" + loginId +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", state=" + state +
        ", courseId=" + courseId +
        ", courseOrderNo=" + courseOrderNo +
        ", createTime=" + createTime +
        "}";
    }
}
