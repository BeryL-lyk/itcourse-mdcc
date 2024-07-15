package com.itcourse.mdcc.domain;

import java.math.BigDecimal;
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
@TableName("t_course_market")
public class CourseMarket extends Model<CourseMarket> {

    private static final long serialVersionUID = 1L;

    /**
     * 课程id
     */
    private Long id;
    /**
     * 收费规则：，收费1免费，2收费
     */
    private Integer charge;
    /**
     * 咨询qq
     */
    private String qq;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 原价
     */
    @TableField("price_old")
    private BigDecimal priceOld;
    @TableField("valid_days")
    private Integer validDays;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(BigDecimal priceOld) {
        this.priceOld = priceOld;
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CourseMarket{" +
        ", id=" + id +
        ", charge=" + charge +
        ", qq=" + qq +
        ", price=" + price +
        ", priceOld=" + priceOld +
        ", validDays=" + validDays +
        "}";
    }
}
