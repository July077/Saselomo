package com.huahua.saselomo.sales.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.huahua.saselomo.common.web.DateJsonTypeConvert;
/**
 * 售货单实体类,对应表sales
 * @author Lin·Y
 *
 */
public class Sales implements Serializable{
	private static final long serialVersionUID = -4998984088034706735L;
	
	private Integer id;
	/** 售货单编号*/
	private String saleCode;
	/** 售货单状态*/
	private Integer valid;
	/** 总价格*/
	private Double totalPrice;
	/** 销售时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date saleDate;
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
	public Sales() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSaleCode() {
		return saleCode;
	}
	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	@JsonSerialize(using=DateJsonTypeConvert.class)
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
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
		return "Sales [id=" + id + ", saleCode=" + saleCode + ", valid=" + valid + ", totalPrice=" + totalPrice
				+ ", saleDate=" + saleDate + ", note=" + note + ", createdTime=" + createdTime + ", modifiedTime="
				+ modifiedTime + ", createdUser=" + createdUser + ", modifiedUser=" + modifiedUser + "]";
	}
	
}
