package com.huahua.saselomo.inventory.service.impl;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huahua.saselomo.common.excel.ExportExcel;
import com.huahua.saselomo.common.exception.NullPropertyException;
import com.huahua.saselomo.common.exception.SaveObjectException;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.inventory.dao.InventoryDao;
import com.huahua.saselomo.inventory.entity.Inventory;
import com.huahua.saselomo.inventory.service.InventoryService;
@Service
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	@Qualifier("inventoryDao")
	private InventoryDao inventoryDao;
	
	public Map<String, Object> findObjects(String proName, PageObject pageObject) {
		//获取页面填充数据
		List<Map<String, Object>> inventorys = inventoryDao.findObjects(proName, pageObject);
		//获取表中记录数,计算页数
		int rowCount = inventoryDao.getRowCount(proName);
		pageObject.setRowCount(rowCount);
		//封装数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("inventorys", inventorys);
		map.put("pageObject", pageObject);
		return map;
	}

	public void saveObject(Inventory inventory) {
		int rows = inventoryDao.saveObject(inventory);
		if(rows == -1){
			throw new SaveObjectException("库存更新失败...");
		}
	}

	public void updateObject(Inventory inventory) {
		int rows = inventoryDao.updateObject(inventory);
		if(rows == -1){
			throw new SaveObjectException("库存更新失败...");
		}
	}

	public Inventory findObjectById(Integer id) {
		if(id == null){
			throw new NullPropertyException("id不能为空...");
		}
		Inventory inven = inventoryDao.findObjectById(id);
		return inven;
	}

	public Inventory findObjectByProId(Integer productId) {
		if(productId == null){
			throw new NullPropertyException("id不能为空...");
		}
		Inventory inven = inventoryDao.findObjectByProId(productId);
		return inven;
	}

	public void exportInventory(OutputStream out) {
		//1. 查询库存信息
		List<Map<String, Object>> data = inventoryDao.findObjects(null, null);
		//2. 导出库存数据信息
		ExportExcel<Map<String, Object>> expExcel = new ExportExcel<Map<String,Object>>();
		String[] headers = {"产品名", "库存总量", "库存可用", "已下订单", "库存冻结"};
		String sheetName = "库存";
		String pattern = "HH:mm:ss";
		expExcel.exportExcelCar(headers, sheetName, data, out, pattern);
	}

}
