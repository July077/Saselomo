package com.huahua.saselomo.inventory.service;

import java.io.OutputStream;
import java.util.Map;

import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.inventory.entity.Inventory;
/**
 * 库存业务层接口
 * @author Lin·Y
 *
 */
public interface InventoryService {
	/**
	 * 进行库存信息导出
	 * @param out 与设备关联的输出流
	 */
	void exportInventory(OutputStream out);
	/**
	 * 根据产品id,查询一条库存信息
	 * @param productId
	 * @return
	 */
	Inventory findObjectByProId(Integer productId);
	/**
	 * 查询库存信息
	 * @param proId
	 * @param proName
	 * @param pageObject
	 * @return
	 */
	Map<String, Object> findObjects(String proName, PageObject pageObject);
	/**
	 * 存储库存信息
	 * @param inventory
	 */
	void saveObject(Inventory inventory);
	/**
	 * 更新库存信息
	 * @param inventory
	 */
	void updateObject(Inventory inventory);
	/**
	 * 根据id查询一条库存信息
	 * @param id
	 * @return
	 */
	Inventory findObjectById(Integer id);
}
