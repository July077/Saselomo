package com.huahua.saselomo.receiving.entity;

import java.io.Serializable;

/**
 * 产品和收货单子项对应关系实体类,对应表product_receiving_single
 * @author Lin·Y
 *
 */
public class ProductReceivingSingle implements Serializable{
	private static final long serialVersionUID = -7106503760775186733L;
	
	private Integer id;
	/** 产品id*/
	private Integer productId;
	/** 收货单子项id*/
	private Integer receivingSingleId;
	public ProductReceivingSingle() {
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
	public Integer getReceivingSingleId() {
		return receivingSingleId;
	}
	public void setReceivingSingleId(Integer receivingSingleId) {
		this.receivingSingleId = receivingSingleId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
