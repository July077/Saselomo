package com.huahua.saselomo.receiving.service;
/**
 * 收货单子项业务层接口
 * @author Lin·Y
 *
 */

import java.util.Map;

import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.receiving.entity.ReceivingSingle;

public interface ReceivingSingleService {
	/**
	 * 确认收货子项收货
	 * @param checkedIds
	 */
	void confirmRecSingle(String recSinIds, Integer recId);
	/**
	 * 查询对应收货单子项信息
	 * @param parentId
	 * @param pageObject
	 * @return
	 */
	Map<String, Object> findObject(String productName, Integer parentId, PageObject pageObject);
	/**
	 * 存储收货单子项信息，及收货单子项和产品的对应关系,更新总价格
	 * @param receivingSingle
	 * @param productId
	 */
	void saveObject(ReceivingSingle receivingSingle, Integer productId);
	/**
	 * 根据id查询一条收货单子项相关信息
	 * @param id
	 */
	Map<String, Object> findObjectById(Integer id);
	/**
	 * 更新收货单子项信息
	 * @param receivingSingle
	 */
	void updateObject(ReceivingSingle receivingSingle);
	/**
	 * 根据id删除一条收货单子项信息
	 * @param id
	 */
	void deleteObject(Integer id, Integer receivingId);
	
}








