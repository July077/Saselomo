package com.huahua.saselomo.client.service;
/**
 * 客户购买记录业务层接口
 * @author Lin·Y
 *
 */

import java.util.List;
import java.util.Map;

import com.huahua.saselomo.client.entity.ClientPurHistory;
import com.huahua.saselomo.common.web.PageObject;

public interface ClientPurHistoryService {
	/**
	 * 查询客户购买记录信息
	 * @param clientId 查询的客户id
	 * @param purDate 购买时间
	 * @param pageObject
	 * @return
	 */
	Map<String, Object> findObjects(ClientPurHistory clientPurHistory, PageObject pageObject);
	/**
	 * 查询客户购买记录信息,不返回分页信息,主要用来做客户购买记录查询的更新和存储
	 * @param clientPurHistory
	 * @return
	 */
	List<ClientPurHistory> findObjects(ClientPurHistory clientPurHistory);
	/**
	 * 存储客户购买记录信息
	 * @param clientPurHistory
	 * @return
	 */
	void saveObject(ClientPurHistory clientPurHistory);
	/**
	 * 更新客户购买记录信息
	 * @param clientPurHistory
	 * @return
	 */
	void updateObject(ClientPurHistory clientPurHistory);
}
