package receiving.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.receiving.entity.Receiving;

import common.TestBase;

public class TestReceivingDao extends TestBase {
	/** 测试根据收获单编号进行查询*/ 
	@Test
	public void testFindObjectsByReceiptCode(){
		String receiptCode = "R20180806";
		List<Receiving> receivings = receivingDao.findObjectsByReceiptCode(receiptCode);
		System.out.println(receivings);
		for(Receiving receiving : receivings){
			System.out.println(receiving);
		}
	}
	/** 测试查询收货单信息
	 * @throws ParseException */
	@Test
	public void testFindObjects() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Receiving receiving = new Receiving();
		receiving.setPurchaseTime(sdf.parse("2018-07-02"));
		PageObject pageObject = new PageObject();//分页对象
		pageObject.setRowCount(receivingDao.getRowCount(receiving));//查询表中记录数,设置分页信息
		List<Receiving> receivings = receivingDao.findObjects(receiving, pageObject);
		Assert.assertNotEquals(null, receivings);
		System.out.println(receivings);
	}
	
	/** 测试插入
	 * @throws ParseException */
	@Test
	public void testSaveObject() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Receiving receiving = new Receiving();
		receiving.setReceiptCode("R20180804002");
		receiving.setPurchaseTime(sdf.parse("2018-08-04"));
		receiving.setValid(1);
		receiving.setTotalPrice(290d);
		receiving.setCreatedUser("花花");
		int num = receivingDao.saveObject(receiving);//存储收获单信息
		Assert.assertEquals(1, num);
	}
	
	/** 测试修改
	 * @throws ParseException */
	@Test
	public void testUpdateObject() throws ParseException{
		Receiving receiving = receivingDao.findObjectById(4);
		receiving.setNote("哎呀呀呀呀呀呀");
		//更新
		int num = receivingDao.updateObject(receiving);
		Assert.assertEquals(1, num);
	}
	
	/** 测试删除*/
	@Test
	public void testDeleteObject(){
		int num = receivingDao.deleteObject(5);
		Assert.assertEquals(1, num);
	}
	
}
