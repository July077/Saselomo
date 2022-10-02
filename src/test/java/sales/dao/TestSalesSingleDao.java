package sales.dao;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.sales.entity.ProductSalesSingle;
import com.huahua.saselomo.sales.entity.SalesSingle;

import common.TestBase;

public class TestSalesSingleDao extends TestBase {
	/** 测试查询子项相关信息,用于客户购买记录的添加|更新,根据确认收货的子项id*/
	@Test
	public void testFindConfirmObjects(){
		String[] ids = {"45","46"};
		List<Map<String, Object>> list = salesSingleDao.findConfirmObjects(ids);
		for(Map<String, Object> map : list){
			System.out.println(map);
		}
	}
	/** 测试查询*/
	@Test
	public void testFindObjects(){
		String productName = "";
		Integer parentId = 2;
		PageObject pageObject = new PageObject();
		System.out.println(salesSingleDao.getRowCount(productName, parentId));
		pageObject.setRowCount(salesSingleDao.getRowCount(productName, parentId));
		List<Map<String, Object>> list = salesSingleDao.findObjects(productName, parentId, pageObject);
		for(Map<String, Object> map : list){
			System.out.println(map);
		}
	}
	/** 测试存储*/
	@Test
	public void testSaveObject(){
		//创建对象售货单子项
		SalesSingle salSingle = new SalesSingle();
		salSingle.setSalesId(2);
		salSingle.setValid(1);
		salSingle.setSalePrice(200d);
		salSingle.setCount(1);
		//存储
		int rows = salesSingleDao.saveObject(salSingle);
		Assert.assertNotEquals(-1, rows);
		//创建产与售关系对象
		ProductSalesSingle pss = new ProductSalesSingle();
		pss.setProductId(21);
		System.out.println("salSingle.getId="+salSingle.getId());
		pss.setSalesSingleId(salSingle.getId());//设置创建售货单子项返回的key
		int rows2 = proSalesSingleDao.saveObject(pss);//存储
		Assert.assertNotEquals(-1, rows2);
	}
	
	/** 测试更新*/
	@Test
	public void testUpdateObject(){
//		Integer id = 1;
//		Map<String, Object> map = salesSingleDao.findObjectById(id);
//		System.out.println(map);
		SalesSingle ss = new SalesSingle();
		ss.setId(1);
		ss.setSalesId(1);
		ss.setCount(1);
		ss.setSalePrice(120d);
		ss.setValid(1);
		int rows = salesSingleDao.updateObject(ss);
		Assert.assertNotEquals(-1, rows);
	}
	/** 测试删除*/
	@Test
	public void testDeleteObject(){
		Integer id = 6;
		int rows = salesSingleDao.deleteObject(id);
		Assert.assertNotEquals(-1, rows);
		int rows2 = proSalesSingleDao.deleteObject(id);
		Assert.assertNotEquals(-1, rows2);
	}
}
