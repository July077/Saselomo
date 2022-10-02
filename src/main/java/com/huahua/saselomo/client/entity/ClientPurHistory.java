package com.huahua.saselomo.client.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.huahua.saselomo.common.web.DateJsonTypeConvert;

/**
 * 客户购买记录实体类,对应表clientPurHistory
 * @author Lin·Y
 *
 */
public class ClientPurHistory implements Serializable{
	private static final long serialVersionUID = -7317729201734085426L;

	private Integer id;
	/** 客户id*/
	private Integer clientId;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	/** 购买时间*/
	private Date purchaseDate;
	/** 购买产品简称 格式：简称*购买数量*/
	private String purProSimp;
	/** 购买产品全称 格式:产品名*购买数量*/
	private String purProFull;
	public ClientPurHistory() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	@JsonSerialize(using=DateJsonTypeConvert.class)
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getPurProSimp() {
		return purProSimp;
	}
	public void setPurProSimp(String purProSimp) {
		this.purProSimp = purProSimp;
	}
	public String getPurProFull() {
		return purProFull;
	}
	public void setPurProFull(String purProFull) {
		this.purProFull = purProFull;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ClientPurchaseHistory [id=" + id + ", clientId=" + clientId + ", purchaseDate=" + purchaseDate
				+ ", purProSimp=" + purProSimp + ", purProFull=" + purProFull + "]";
	}
	
}
