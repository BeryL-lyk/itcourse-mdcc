package com.itcourse.mdcc.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程目录
 * </p>
 *
 * @author daemon
 * @since 2024-07-15
 */
@TableName("t_course_type")
public class CourseType extends Model<CourseType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("create_time")
    private Long createTime;
    @TableField("update_time")
    private Long updateTime;
    /**
     * 类型名
     */
    private String name;
    /**
     * 父ID
     */
    private Long pid;
    /**
     * 图标
     */
    private String logo;
    /**
     * 描述
     */
    private String description;
    @TableField("sort_index")
    private Integer sortIndex;
    /**
     * 路径
     */
    private String path;
    /**
     * 课程数量
     */
    @TableField("total_count")
    private Integer totalCount;

    @TableField(exist = false)
    private List<CourseType> children = new ArrayList<CourseType>();

    public List<CourseType> getChildren() {
        return children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CourseType{" +
        ", id=" + id +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", name=" + name +
        ", pid=" + pid +
        ", logo=" + logo +
        ", description=" + description +
        ", sortIndex=" + sortIndex +
        ", path=" + path +
        ", totalCount=" + totalCount +
        "}";
    }
}
