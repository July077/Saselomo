package inventory;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.inventory.entity.Inventory;

import common.TestBase;

public class TestInventoryDao extends TestBase {
	/** 测试根据产品id查询*/
	@Test
	public void testFindObjectByProId(){
		Integer productId = 18;
		Inventory inven = inventoryDao.findObjectByProId(productId);
		System.out.println(inven);
	}
	/** 测试查询*/
	@Test
	public void testFindOBjects(){
		String proName = "";
		PageObject pageObject = new PageObject();
		System.out.println(inventoryDao.getRowCount(proName));
		pageObject.setRowCount(inventoryDao.getRowCount(proName));
		List<Map<String, Object>> inventorys = inventoryDao.findObjects(proName, pageObject);
		for(Map<String, Object> inventory : inventorys ){
			System.out.println(inventory);
		}
	}
	/** 测试插入*/
	@Test
	public void testSaveObject(){
		Inventory inven = new Inventory();
		inven.setProductId(3);
		inven.setInventoryCount(10);
		inven.setInventoryValid(3);
		inven.setInventoryAvailable(10);
		inven.setInventoryFreeze(0);
		int rows = inventoryDao.saveObject(inven);
		Assert.assertNotEquals(-1, rows);
	}
	/** 测试修改*/
	@Test
	public void testUpdateObject(){
		Inventory inven = inventoryDao.findObjectById(2);
		System.out.println(inven);
		inven.setInventoryCount(30);
		System.out.println(inven);
		int rows = inventoryDao.updateObject(inven);
		Assert.assertNotEquals(-1, rows);
	}
}
