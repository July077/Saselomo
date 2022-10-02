package com.huahua.saselomo.receiving.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.huahua.saselomo.common.web.DateJsonTypeConvert;

/**
 * 收获单实体类,对应表receiving
 * @author Lin·Y
 *
 */
public class Receiving implements Serializable {

	private static final long serialVersionUID = -4734863171945045269L;

	private Integer id;
	/** 收货单编号*/
	private String receiptCode;
	/** 收货单状态*/
	private Integer valid;
	/** 进货时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd")//将前段发送的时间字符串,转换为Date形式
	private Date purchaseTime;
	/** 总价*/
	private Double totalPrice;
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
	public Receiving() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReceiptCode() {
		return receiptCode;
	}
	public void setReceiptCode(String receiptCode) {
		this.receiptCode = receiptCode;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	@JsonSerialize(using=DateJsonTypeConvert.class)
	public Date getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
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
		return "Receiving [id=" + id + ", receiptCode=" + receiptCode + ", valid=" + valid + ", purchaseTime="
				+ purchaseTime + ", totalPrice=" + totalPrice + ", note=" + note + ", createdTime=" + createdTime
				+ ", modifiedTime=" + modifiedTime + ", createdUser=" + createdUser + ", modifiedUser=" + modifiedUser
				+ "]";
	}
	
	
	
}
