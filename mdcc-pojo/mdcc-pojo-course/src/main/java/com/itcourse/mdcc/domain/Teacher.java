package com.itcourse.mdcc.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 老师表
 * </p>
 *
 * @author daemon
 * @since 2024-07-15
 */
@TableName("t_teacher")
public class Teacher extends Model<Teacher> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    /**
     * 简介
     */
    private String intro;
    /**
     * 技术栈
     */
    private String technology;
    /**
     * 职位，高级讲师，架构师
     */
    private String position;
    /**
     * 头像
     */
    @TableField("head_img")
    private String headImg;
    private String tags;


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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Teacher{" +
        ", id=" + id +
        ", name=" + name +
        ", intro=" + intro +
        ", technology=" + technology +
        ", position=" + position +
        ", headImg=" + headImg +
        ", tags=" + tags +
        "}";
    }
}
