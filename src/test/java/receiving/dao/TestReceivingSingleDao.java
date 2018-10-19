package receiving.dao;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.receiving.entity.ReceivingSingle;

import common.TestBase;
/**
 * 测试收货单子项
 * @author Lin·Y
 *
 */
public class TestReceivingSingleDao extends TestBase{
	/** 测试确认收货*/
	@Test
	public void testConfirmRecSingle(){
		String[] ids = {"178", "177"};
		int rows = receivingSingleDao.confirmRecSingle(ids);
		Assert.assertNotEquals(-1, rows);
	}
	/** 测试查询对应收货单子项信息*/
	@Test
	public void testFindObjects(){
		PageObject pageObject = new PageObject();
		String proName = "自然";
		System.out.println(receivingSingleDao.getRowCount(proName, 88));
		pageObject.setRowCount(receivingSingleDao.getRowCount(proName, 88));
		List<Map<String, Object>> list = receivingSingleDao.findObjects(proName, 88, pageObject);
		for(Map<String, Object> map :list){
			System.out.println(map);
		}
	}
	/** 测试插入对应收货单子项信息*/
	@Test
	public void testSaveObject(){
		ReceivingSingle receivingSingle = new ReceivingSingle();
		receivingSingle.setReceivingId(18);
		receivingSingle.setValid(1);
		receivingSingle.setPurchasePrice(86d);
		receivingSingle.setCount(2);
		int num = receivingSingleDao.saveObject(receivingSingle);
		Assert.assertEquals(1, num);
		System.out.println(receivingSingle.getId());
	}
	/** 测试根据id查询一条收货单子项相关信息*/
	@Test
	public void testFindObjectById(){
		Map<String, Object> map = receivingSingleDao.findObjectById(1);
		Assert.assertNotEquals(null, map);
		System.out.println(map);
	}
}
