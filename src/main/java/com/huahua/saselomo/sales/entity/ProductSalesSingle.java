package com.huahua.saselomo.sales.entity;

import java.io.Serializable;

/**
 * 产品和售货单对应关系实体类,对应表product_sales_single
 * @author Lin·Y
 *
 */
public class ProductSalesSingle implements Serializable{
	private static final long serialVersionUID = 808973997326292493L;
	
	private Integer id;
	private Integer productId;
	private Integer salesSingleId;
	public ProductSalesSingle() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getSalesSingleId() {
		return salesSingleId;
	}
	public void setSalesSingleId(Integer salesSingleId) {
		this.salesSingleId = salesSingleId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ProductSalesSingle [id=" + id + ", productId=" + productId + ", salesSingleId=" + salesSingleId + "]";
	}
	
}
