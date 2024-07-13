package com.itcourse.mdcc.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 登录表
 * </p>
 *
 * @author daemon
 * @since 2024-07-13
 */
@TableName("t_login")
public class Login extends Model<Login> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    /**
     * 0是后台，1是前台
     */
    private Integer type;
    private Boolean enabled;
    @TableField("account_non_expired")
    private Boolean accountNonExpired;
    @TableField("credentials_non_expired")
    private Boolean credentialsNonExpired;
    @TableField("account_non_locked")
    private Boolean accountNonLocked;
    /**
     * 对应Oauth2客户端详情ID
     */
    @TableField("client_id")
    private String clientId;
    @TableField("client_secret")
    private String clientSecret;
    private String avatar;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Login{" +
        ", id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", type=" + type +
        ", enabled=" + enabled +
        ", accountNonExpired=" + accountNonExpired +
        ", credentialsNonExpired=" + credentialsNonExpired +
        ", accountNonLocked=" + accountNonLocked +
        ", clientId=" + clientId +
        ", clientSecret=" + clientSecret +
        ", avatar=" + avatar +
        "}";
    }
}
