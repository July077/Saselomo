package client.dao;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.huahua.saselomo.client.entity.ClientPurHistory;
import com.huahua.saselomo.common.web.PageObject;

import common.TestBase;

public class TestClientPurHistory extends TestBase {
	/** 测试查询*/
	@Test
	public void testFindObjects(){
		Integer clientId = 36;//客户id
		Date purDate = null;//购买时间
		PageObject pageObject = new PageObject();//分页
		int rowCount = clientPurHistoryDao.getRowCount(clientId, purDate);//获取总记录数
		System.out.println(rowCount);
		pageObject.setRowCount(rowCount);
		//查询
		List<ClientPurHistory> list = clientPurHistoryDao.findObjects(clientId, purDate, pageObject);
		for(ClientPurHistory cph : list){
			System.out.println(cph);
		}
	}
	/** 测试插入*/
	@Test
	public void testSaveObject(){
		ClientPurHistory cph = new ClientPurHistory();
		cph.setClientId(28);
		cph.setPurchaseDate(new Date());
		cph.setPurProFull("啦啦啦啦啦啦啦啦啦啦啦啦");
		cph.setPurProSimp("哈哈哈哈哈哈哈哈哈哈或或或或或或");
		int rows = clientPurHistoryDao.saveObject(cph);
		Assert.assertNotEquals(-1, rows);
	}
}
