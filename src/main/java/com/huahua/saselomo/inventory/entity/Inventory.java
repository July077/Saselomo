package com.huahua.saselomo.inventory.entity;

import java.io.Serializable;

/**
 * 库存实体类对象,对应inventory表.
 * 一个产品对象一条库存信息
 * @author Lin·Y
 *
 */
public class Inventory implements Serializable{
	private static final long serialVersionUID = -5153144696874399158L;
	
	private Integer id;
	/** 产品id*/
	private Integer productId;
	/** 库存数量*/
	private Integer inventoryCount;
	/** 库存状态*/
	private Integer inventoryValid;
	/** 库存冻结*/
	private Integer inventoryFreeze;
	/** 库存可用*/
	private Integer inventoryAvailable;
	/** 库存订单数*/
	private Integer inventoryOrderForm;
	public Inventory() {
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
	public Integer getInventoryCount() {
		return inventoryCount;
	}
	public void setInventoryCount(Integer inventoryCount) {
		this.inventoryCount = inventoryCount;
	}
	public Integer getInventoryValid() {
		return inventoryValid;
	}
	public void setInventoryValid(Integer inventoryValid) {
		this.inventoryValid = inventoryValid;
	}
	public Integer getInventoryFreeze() {
		return inventoryFreeze;
	}
	public void setInventoryFreeze(Integer inventoryFreeze) {
		this.inventoryFreeze = inventoryFreeze;
	}
	public Integer getInventoryAvailable() {
		return inventoryAvailable;
	}
	public void setInventoryAvailable(Integer inventoryAvailable) {
		this.inventoryAvailable = inventoryAvailable;
	}
	public Integer getInventoryOrderForm() {
		return inventoryOrderForm;
	}
	public void setInventoryOrderForm(Integer inventoryOrderForm) {
		this.inventoryOrderForm = inventoryOrderForm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Inventory [id=" + id + ", productId=" + productId + ", inventoryCount=" + inventoryCount
				+ ", inventoryValid=" + inventoryValid + ", inventoryFreeze=" + inventoryFreeze
				+ ", inventoryAvailable=" + inventoryAvailable + ", inventoryOrderForm=" + inventoryOrderForm + "]";
	}
}
