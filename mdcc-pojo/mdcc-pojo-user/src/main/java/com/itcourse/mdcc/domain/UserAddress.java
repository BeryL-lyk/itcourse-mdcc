package com.itcourse.mdcc.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 收货地址
 * </p>
 *
 * @author daemon
 * @since 2024-07-13
 */
@TableName("t_user_address")
public class UserAddress extends Model<UserAddress> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("create_time")
    private Long createTime;
    @TableField("update_time")
    private Long updateTime;
    /**
     * 登录用户
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 收货人
     */
    private String reciver;
    /**
     * 区域
     */
    @TableField("area_code")
    private String areaCode;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 全地址
     */
    @TableField("full_address")
    private String fullAddress;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 备用手机号
     */
    @TableField("phone_back")
    private String phoneBack;
    /**
     * 固定电话
     */
    private String tel;
    /**
     * 邮编
     */
    @TableField("post_code")
    private String postCode;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 是否默认
     */
    @TableField("default_address")
    private Integer defaultAddress;


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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneBack() {
        return phoneBack;
    }

    public void setPhoneBack(String phoneBack) {
        this.phoneBack = phoneBack;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Integer defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
        ", id=" + id +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", userId=" + userId +
        ", reciver=" + reciver +
        ", areaCode=" + areaCode +
        ", address=" + address +
        ", fullAddress=" + fullAddress +
        ", phone=" + phone +
        ", phoneBack=" + phoneBack +
        ", tel=" + tel +
        ", postCode=" + postCode +
        ", email=" + email +
        ", defaultAddress=" + defaultAddress +
        "}";
    }
}
