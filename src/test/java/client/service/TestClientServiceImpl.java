package client.service;

import org.junit.Test;
import com.huahua.saselomo.client.entity.Client;
import com.huahua.saselomo.common.web.PageObject;

import common.TestBase;
/**
 * 客户业务层测试类
 * @author Lin·Y
 *
 */
public class TestClientServiceImpl extends TestBase{
	@Test
	public void testFindObjects(){
		Client client = new Client();
		PageObject pageObject = new PageObject();
		System.out.println(cs.findObjects(client,pageObject));
	}
	
	@Test
	public void testFindObjectById(){
		System.out.println(cs.findObjectById(0));
	}
	
}
