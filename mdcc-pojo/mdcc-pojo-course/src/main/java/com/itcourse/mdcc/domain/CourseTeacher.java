package com.itcourse.mdcc.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 课程和老师的中间表
 * </p>
 *
 * @author daemon
 * @since 2024-07-15
 */
@TableName("t_course_teacher")
public class CourseTeacher extends Model<CourseTeacher> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("teacher_id")
    private Long teacherId;
    @TableField("course_id")
    private Long courseId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CourseTeacher{" +
        ", id=" + id +
        ", teacherId=" + teacherId +
        ", courseId=" + courseId +
        "}";
    }
}
