package com.huahua.saselomo.receiving.entity;

import java.io.Serializable;

/**
 * 收货单子项实体类,对应表receiving _single
 * @author Lin·Y
 *
 */
public class ReceivingSingle implements Serializable{
	private static final long serialVersionUID = -911581957628547830L;
	
	private Integer id;
	/** 收货单id*/
	private Integer receivingId;
	/** 收货单子项状态*/
	private Integer valid;
	/** 进货价格*/
	private Double purchasePrice;
	/** 进货数量*/
	private Integer count;
	public ReceivingSingle() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReceivingId() {
		return receivingId;
	}
	public void setReceivingId(Integer receivingId) {
		this.receivingId = receivingId;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ReceivingSingle [id=" + id + ", receivingId=" + receivingId + ", valid=" + valid + ", purchasePrice="
				+ purchasePrice + ", count=" + count + "]";
	}
	
	
	
}
