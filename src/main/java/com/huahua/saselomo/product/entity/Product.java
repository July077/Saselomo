package com.huahua.saselomo.product.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 产品实体类,对应product表
 * @author Lin·Y
 *
 */
public class Product implements Serializable {
	private static final long serialVersionUID = -8731630634296609164L;
	
	private Integer id;
	/** 产品名*/
	private String name;
	/** 产品名简称*/
	private String abbreviation;
	/** 状态(是否有效)*/
	private Integer valid;
	/** 一级代理产品价(套/元)*/
	private Double firstStage;
	/** 二级价格*/
	private Double secondStage;
	/** 至尊VIP价格*/
	private Double supremacy;
	/** 微商价格*/
	private Double derivative;
	/** 零售价格*/
	private Double retail;
	/** 箱规(套/箱)*/
	private Integer cartonSize;
	/** 功效*/
	private String effect;
	/** 卖点*/
	private String sellingPoints;
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
	public Product() {
		super();
	}
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
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public Double getFirstStage() {
		return firstStage;
	}
	public void setFirstStage(Double firstStage) {
		this.firstStage = firstStage;
	}
	public Double getSecondStage() {
		return secondStage;
	}
	public void setSecondStage(Double secondStage) {
		this.secondStage = secondStage;
	}
	public Double getSupremacy() {
		return supremacy;
	}
	public void setSupremacy(Double supremacy) {
		this.supremacy = supremacy;
	}
	public Double getDerivative() {
		return derivative;
	}
	public void setDerivative(Double derivative) {
		this.derivative = derivative;
	}
	public Double getRetail() {
		return retail;
	}
	public void setRetail(Double retail) {
		this.retail = retail;
	}
	public Integer getCartonSize() {
		return cartonSize;
	}
	public void setCartonSize(Integer cartonSize) {
		this.cartonSize = cartonSize;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	public String getSellingPoints() {
		return sellingPoints;
	}
	public void setSellingPoints(String sellingPoints) {
		this.sellingPoints = sellingPoints;
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
		return "Product [id=" + id + ", name=" + name + ", abbreviation=" + abbreviation + ", valid=" + valid
				+ ", firstStage=" + firstStage + ", secondStage=" + secondStage + ", supremacy=" + supremacy
				+ ", derivative=" + derivative + ", retail=" + retail + ", cartonSize=" + cartonSize + ", effect="
				+ effect + ", sellingPoints=" + sellingPoints + ", note=" + note + ", createdTime=" + createdTime
				+ ", modifiedTime=" + modifiedTime + ", createdUser=" + createdUser + ", modifiedUser=" + modifiedUser
				+ "]";
	}
	
	
}












