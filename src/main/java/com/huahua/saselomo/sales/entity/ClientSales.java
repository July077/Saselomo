package com.huahua.saselomo.sales.entity;
/**
 * 客户和售货单对应关系实体类,对应表client_sales
 * @author Lin·Y
 *
 */

import java.io.Serializable;

public class ClientSales implements Serializable{
	private static final long serialVersionUID = -1540395319546464985L;
	private Integer id;
	private Integer clientId;
	private Integer salesId;
	public ClientSales() {
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
	public Integer getSalesId() {
		return salesId;
	}
	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ClientSales [id=" + id + ", clientId=" + clientId + ", salesId=" + salesId + "]";
	}
	
}
