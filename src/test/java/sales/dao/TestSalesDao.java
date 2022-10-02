package sales.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.sales.entity.ClientSales;
import com.huahua.saselomo.sales.entity.Sales;

import common.TestBase;

public class TestSalesDao extends TestBase {
	/** 测试根据客户id,查询售货单信息*/
	@Test
	public void testFindObjectByClientId(){
		List<Map<String, Object>> list = salesDao.findObjectByClientId(54);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	/** 测试查询售货单对应客户id,和售货单的销售时间*/
	@Test
	public void testFindCliAndSalById(){
		Integer id = 21;
		Map<String, Object> map = salesDao.findCliAndSalById(id);
		System.out.println(map);
	}
	/** 测试查询*/
	@Test
	public void testFindObjects(){
		String clientName = "";
		Sales sale = new Sales();
		sale.setSaleDate(new Date());
		PageObject pageObject = new PageObject();
		int rowCount = salesDao.getRowCount(clientName, sale);//获取总记录数
		System.out.println(rowCount);
		pageObject.setRowCount(rowCount);//计算总页数
		List<Map<String, Object>> sales = salesDao.findObjects(clientName, sale, pageObject);
		for(Map<String, Object> map : sales){
			System.out.println(map);
		}
	}
	
	/** 测试插入*/
	@Test
	public void testSaveObject(){
		Sales sales = new Sales();//售货单对象
		sales.setSaleCode("S20180815003");
		sales.setSaleDate(new Date());
		sales.setValid(1);
		sales.setTotalPrice(120d);
		sales.setCreatedUser("花花");
		int rows1 = salesDao.saveObject(sales);//存储
		Assert.assertNotEquals(-1, rows1);
		ClientSales cs = new ClientSales();//客户与售货单关系对象
		cs.setClientId(17);
		System.out.println(sales.getId());//售货单返回的id
		cs.setSalesId(sales.getId());
		int rows2 = clientSalesDao.saveObject(cs);
		Assert.assertNotEquals(-1, rows2);
	}
	
	/** 测试更新*/
	@Test
	public void testUpdateObject(){
		Sales sales = salesDao.findObjectById(3);
		System.out.println(sales);
		sales.setTotalPrice(80d);
		int rows = salesDao.updateObject(sales);
		Assert.assertNotEquals(-1, rows);
	}
	
	/** 测试删除*/
	@Test
	public void testDeleteObject(){
		int rows1 = salesDao.deleteObject(3);
		Assert.assertNotEquals(-1, rows1);
		int rows2 = clientSalesDao.deleteObject(3);
		Assert.assertNotEquals(-1, rows2);
	}

}
