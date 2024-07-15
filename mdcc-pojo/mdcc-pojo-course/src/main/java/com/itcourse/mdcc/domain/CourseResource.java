package com.itcourse.mdcc.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 课件
 * </p>
 *
 * @author daemon
 * @since 2024-07-15
 */
@TableName("t_course_resource")
public class CourseResource extends Model<CourseResource> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 课程id
     */
    @TableField("course_id")
    private Long courseId;
    /**
     * 文件云地址
     */
    private String resources;
    /**
     * 资料类型：0课件，1其他
     */
    private Integer type;


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

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CourseResource{" +
        ", id=" + id +
        ", courseId=" + courseId +
        ", resources=" + resources +
        ", type=" + type +
        "}";
    }
}
