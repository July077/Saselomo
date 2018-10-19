package com.huahua.saselomo.inventory.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huahua.saselomo.common.dao.BaseDao;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.inventory.entity.Inventory;

/**
 * 库存dao层接口,
 * 对应InventoryDaoMapper
 * @author Lin·Y
 *
 */
public interface InventoryDao extends BaseDao<Inventory>{
	/**
	 * 根据产品id,查询一条库存信息
	 * @param proId
	 * @return
	 */
	Inventory findObjectByProId(@Param("productId")Integer proId);
	/**
	 * 查询全部库存信息
	 * @param proName
	 * @return
	 */
	List<Map<String, Object>> findObjects(@Param("productName")String proName, @Param("pageObject")PageObject pageObject);
	/**
	 * 查询库存总记录数
	 * @param proName
	 * @return
	 */
	int getRowCount( @Param("productName")String proName);
	/**
	 * 根据id查询一条库存信息
	 * @param id
	 * @return
	 */
	Inventory findObjectById(Integer id);
}
