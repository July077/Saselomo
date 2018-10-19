package client.service;

import org.junit.Test;

import common.TestBase;
/**
 * 客户业务层测试类
 * @author Lin·Y
 *
 */
public class TestClientServiceImpl extends TestBase{
	@Test
	public void testFindObjects(){
//		Client client = new Client();
//		System.out.println(cs.findObjects(client));
	}
	
	@Test
	public void testFindObjectById(){
		System.out.println(cs.findObjectById(0));
	}
	
}
