package com.itcourse.mdcc.domain;

import java.util.Date;
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
@TableName("t_course_view_log")
public class CourseViewLog extends Model<CourseViewLog> {

    private static final long serialVersionUID = 1L;

    private Long id;
    @TableField("course_id")
    private Long courseId;
    @TableField("user_id")
    private Long userId;
    @TableField("create_time")
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        return "CourseViewLog{" +
        ", id=" + id +
        ", courseId=" + courseId +
        ", userId=" + userId +
        ", createTime=" + createTime +
        "}";
    }
}
