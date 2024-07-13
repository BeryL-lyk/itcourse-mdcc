package com.itcourse.mdcc.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
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
 * @since 2024-07-13
 */
@TableName("t_account_flow")
public class AccountFlow extends Model<AccountFlow> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 流水金额
     */
    private BigDecimal amount;
    /**
     * 账户ID
     */
    @TableField("account_id")
    private Long accountId;
    /**
     * 流水描述
     */
    private String remark;
    @TableField("create_time")
    private Date createTime;
    /**
     * 流水类型：充值，提现，购买课程
     */
    @TableField("business_type")
    private Integer businessType;
    @TableField("business_name")
    private String businessName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AccountFlow{" +
        ", id=" + id +
        ", amount=" + amount +
        ", accountId=" + accountId +
        ", remark=" + remark +
        ", createTime=" + createTime +
        ", businessType=" + businessType +
        ", businessName=" + businessName +
        "}";
    }
}
