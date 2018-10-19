package com.huahua.saselomo.sales.entity;

import java.io.Serializable;

/**
 * 售货单子项实体类,对应表sales_single
 * @author Lin·Y
 *
 */
public class SalesSingle implements Serializable{
	private static final long serialVersionUID = 6373721000806797211L;
	
	private Integer id;
	/** 售货单id*/
	private Integer salesId;
	/** 售货单子项状态*/
	private Integer valid;
	/** 销售价格*/
	private Double salePrice;
	/** 数量*/
	private Integer count;
	public SalesSingle() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSalesId() {
		return salesId;
	}
	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
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
		return "SalesSingle [id=" + id + ", salesId=" + salesId + ", valid=" + valid + ", salePrice=" + salePrice
				+ ", count=" + count + "]";
	}
	
}
