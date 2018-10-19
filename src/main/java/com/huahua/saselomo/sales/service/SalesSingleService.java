package com.huahua.saselomo.sales.service;

import java.util.Map;

import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.sales.entity.SalesSingle;

/**
 * 售货单子项业务层接口
 * @author Lin·Y
 *
 */
public interface SalesSingleService {
	
	/**
	 * 确认售货子项售货
	 * @param checkedIds
	 */
	void confirmSalSingle(String salSinIds, Integer salId);
	/**
	 * 查询对应售货单子项信息
	 * @param parentId
	 * @param pageObject
	 * @return
	 */
	Map<String, Object> findObject(String productName, Integer parentId, PageObject pageObject);
	/**
	 * 存储售货单子项信息，及售货单子项和产品的对应关系,更新总价格
	 * @param salesSingle
	 * @param productId
	 */
	void saveObject(SalesSingle salesSingle, Integer productId);
	/**
	 * 根据id查询一条售货单子项相关信息
	 * @param id
	 */
	Map<String, Object> findObjectById(Integer id);
	/**
	 * 更新售货单子项信息
	 * @param salesSingle
	 */
	void updateObject(SalesSingle salesSingle);
	/**
	 * 根据id删除一条售货单子项信息
	 * @param id
	 */
	void deleteObject(Integer id, Integer salesId);
}
