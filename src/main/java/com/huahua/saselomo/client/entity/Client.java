package com.huahua.saselomo.client.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.huahua.saselomo.common.web.TimeJsonTypeConvert;
/**
 * 客户实体类,对应表client；
 * @author Lin·Y
 *
 */
public class Client implements Serializable {

	private static final long serialVersionUID = 4785532106486767084L;
	
	private Integer id;
	/** 客户姓名*/
	private String name;
	/** 状态,是否有效*/
	private Integer valid;
	/** 性别*/
	private String sex;
	/** 年龄*/
	private Integer age;
	/** 作息时间*/
	@DateTimeFormat(pattern="HH:mm:ss")//将前段发送的时间字符串,转化为date形式
	private Date timetable;
	/** 地址*/
	private String address;
	/** 电话*/
	private String phone;
	/** 皮肤状态*/
	private String skinCondition;
	/** 备注*/
	private String note;
	/** 创建时间*/
	private Date createdTime;
	/** 修改时间*/
	private Date modifiedTime;
	/** 创建人*/
	private String createdUser;
	/** 修改人*/
	private String modifiedUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSkinCondition() {
		return skinCondition;
	}
	public void setSkinCondition(String skinCondition) {
		this.skinCondition = skinCondition;
	}
	@JsonSerialize(using=TimeJsonTypeConvert.class)
	public Date getTimetable() {
		return timetable;
	}
	public void setTimetable(Date timetable) {
		this.timetable = timetable;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
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
		return "Client [id=" + id + ", name=" + name + ", valid=" + valid + ", sex=" + sex + ", age=" + age
				+ ", address=" + address + ", phone=" + phone + ", skinCondition=" + skinCondition + ", timetable="
				+ timetable + ", note=" + note + ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime
				+ ", createdUser=" + createdUser + ", modifiedUser=" + modifiedUser + "]";
	}
	public Client() {
		super();
	}
	
	
}
