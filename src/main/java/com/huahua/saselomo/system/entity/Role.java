package com.huahua.saselomo.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.huahua.saselomo.common.web.DateJsonTypeConvert;

/**
 * 角色实体类,对应表sys_roles
 * 
 * @author Lin·Y
 *
 */
public class Role implements Serializable{
	private static final long serialVersionUID = -2729183744327077741L;
	private Integer id;
	/** 角色*/
	private String role;
	/** 角色描述*/
	private String description;
	/** 父类角色id*/
	private Integer parentId;
	/** 创建时间*/
	private Date createdTime;
	/** 修改时间*/
	private Date modifiedTime;
	/** 创建人*/
	private String createdUser;
	/** 修改人*/
	private String modifiedUser;
	public Role() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
		return "Role [id=" + id + ", role=" + role + ", description=" + description + ", parentId=" + parentId
				+ ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + ", createdUser=" + createdUser
				+ ", modifiedUser=" + modifiedUser + "]";
	}
	
}
