package com.huahua.saselomo.receiving.service;

import java.util.Map;

import com.huahua.saselomo.receiving.entity.Receiving;
import com.huahua.saselomo.common.web.PageObject;

/**
 * 收货单业务处接口
 * @author Lin·Y
 *
 */
public interface ReceivingService {
	/**
	 * 查询收货单信息
	 * @return
	 */
	 Map<String, Object> findObjects(Receiving receiving, PageObject pageObject);
	/**
	 * 根据id查询一条收货单信息
	 * @return
	 */
	 Receiving findObjectById(Integer id);
	/**
	 * 存储一条收货单信息
	 * @param receiving
	 */
	 void saveObject(Receiving receiving);
	/**
	 * 根据id删除一条收货单信息
	 * @param id
	 */
	 void deleteObject(Integer id);
	/**
	 * 更新收货单信息
	 * @param receiving
	 */
	 void updateObject(Receiving receiving);
}
