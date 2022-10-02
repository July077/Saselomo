package sales.service;

import java.text.ParseException;

import org.junit.Test;

import common.TestBase;

public class TestSalesSingleService extends TestBase{
	/** 测试进行客户购买记录的插入|更新(若有当天购买记录,则更新)
	 * @throws ParseException */
	@SuppressWarnings(value="all")
	@Test
	public void testSaveOrUpdatePurHistory(){
		String[] ids = {"47"};
		Integer salId = 21;
//		salesSingleService.saveOrUpdatePurHistory(ids, salId);
	}
}
