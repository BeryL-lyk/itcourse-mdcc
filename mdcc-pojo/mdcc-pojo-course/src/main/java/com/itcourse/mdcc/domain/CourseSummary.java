package com.itcourse.mdcc.domain;

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
@TableName("t_course_summary")
public class CourseSummary extends Model<CourseSummary> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 销量
     */
    @TableField("sale_count")
    private Integer saleCount;
    /**
     * 浏览量
     */
    @TableField("view_count")
    private Integer viewCount;
    /**
     * 评论数
     */
    @TableField("comment_count")
    private Integer commentCount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CourseSummary{" +
        ", id=" + id +
        ", saleCount=" + saleCount +
        ", viewCount=" + viewCount +
        ", commentCount=" + commentCount +
        "}";
    }
}
