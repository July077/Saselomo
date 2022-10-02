package com.huahua.saselomo.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.huahua.saselomo.common.web.DateJsonTypeConvert;

/**
 * 用户实体类,对应表sys_users
 * @author Lin·Y
 *
 */
public class User implements Serializable{

	private static final long serialVersionUID = 5091002326443577679L;
	private Integer id;
	/** 用户名,作为登录账号*/
	private String username;
	/** 密码*/
	private String password;
	/** 盐*/
	private String salt;
	/** 用户姓名*/
	private String name;
	/** 邮箱*/
	private String email;
	/** 手机号*/
	private String mobile;
	/** 状态(可用 | 禁用)*/
	private Integer valid;
	/** 创建时间*/
	private Date createdTime;
	/** 修改时间*/
	private Date modifiedTime;
	/** 创建人*/
	private String createdUser;
	/** 修改人*/
	private String modifiedUser;
	public User() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	@JsonSerialize(using=DateJsonTypeConvert.class)
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt + ", name="
				+ name + ", email=" + email + ", mobile=" + mobile + ", valid=" + valid + ", createdTime=" + createdTime
				+ ", modifiedTime=" + modifiedTime + ", createdUser=" + createdUser + ", modifiedUser=" + modifiedUser
				+ "]";
	}
	
	
}
