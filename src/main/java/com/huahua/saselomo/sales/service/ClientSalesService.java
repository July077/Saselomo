package com.huahua.saselomo.sales.service;

import com.huahua.saselomo.sales.entity.ClientSales;

/**
 * 客户与售货单对应关系业务接口
 * @author Lin·Y
 *
 */
public interface ClientSalesService {
	/**
	 * 存储一个客与售对应关系对象信息
	 * @param clientSales
	 */
	void saveObject(ClientSales clientSales);
	/**
	 * 根据售货单id删除一条客与售对应关系信息
	 * @param salesId 一个收货单对应一个客户,收货单id是唯一的
	 */
	void deleteObject(Integer salesId);
	/**
	 * 根据售货单id查询对应购买客户的id
	 */
	ClientSales findObjectBySalesId(Integer salesId);
	/**
	 * 更新客户与售货单对应的关系中的客户id
	 * @param clientSales
	 */
	void updateObject(ClientSales clientSales);
}
