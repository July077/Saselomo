package receiving.dao;

import org.junit.Assert;
import org.junit.Test;

import com.huahua.saselomo.receiving.entity.ProductReceivingSingle;

import common.TestBase;

public class TestProductReceivingSingleDao extends TestBase{
	/** 测试插入*/
	@Test
	public void testSaveObject(){
		ProductReceivingSingle prs = new ProductReceivingSingle();
		prs.setProductId(0);
		prs.setReceivingSingleId(0);
		int num = productReceivingSingleDao.saveObject(prs);
		Assert.assertEquals(1, num);
	}
	/** 测试删除*/
	@Test
	public void testDeleteObject(){
		int num = productReceivingSingleDao.deleteObject(3);
		Assert.assertEquals(1, num);
	}
}
