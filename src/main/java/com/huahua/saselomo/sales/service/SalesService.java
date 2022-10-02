package com.huahua.saselomo.sales.service;
/**
 * 售货单业务层接口
 * @author Lin·Y
 *
 */

import java.util.Map;

import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.sales.entity.Sales;

public interface SalesService {
	/**
	 * 查询售货单对应客户的id,和售货单的销售时间
	 * @param id 根据售货单id
	 * @return
	 */
	Map<String, Object> findCliAndSalById(Integer id);
	/**
	 * 查询收货单相关信息
	 * @param clientName 根据客户名查询
	 * @param saleDate 根据出售时间
	 * @param pageObject 分页信息
	 * @return
	 */
	Map<String, Object> findObjects(String clientName, Sales sales, PageObject pageObject);
	/**
	 * 存储售货单信息
	 * @param sales
	 */
	void saveObject(Sales sales, Integer clientId);
	/**
	 * 更新售货单信息
	 * @param sales
	 */
	void updateObject(Sales sales, Integer clientId);
	/**
	 * 更新售货单信息
	 * @param sales
	 */
	void updateObject(Sales sales);
	/**
	 * 删除售货单信息
	 * @param id
	 */
	void deleteObject(Integer id);
	/**
	 * 根据id查询一条售货单信息,和对应客户信息
	 * @param id
	 * @return
	 */
	Map<String, Object> findObjectById(Integer id);
	/**
	 * 根据id查询一条售货单信息
	 * @param id
	 * @return
	 */
	Sales findSalesById(Integer id);
	
}
