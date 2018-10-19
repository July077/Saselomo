package client.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.huahua.saselomo.client.entity.Client;
import com.huahua.saselomo.common.excel.ExportExcel;
import com.huahua.saselomo.common.web.PageObject;

import common.TestBase;

public class TestClientDao extends TestBase{
	/** 测试批量导入*/
	@Test
	public void testImportObject(){
		List<Client> clients = new ArrayList<Client>();
		Client c = null;
		for (int i = 0; i < 10; i++) {
			c = new Client();
			c.setName("测试"+i);
			c.setAge(10+i);
			c.setSex("女");
			c.setValid(1);
			c.setAddress("河南省济源市"+i);
			c.setPhone("1573884757"+i);
			clients.add(c);
		}
		for (Client client : clients) {
			System.out.println(client);
		}
		int num = clientDao.importObject(clients);
		Assert.assertNotEquals(-1, num);
		System.out.println(num);
		
	}
	/** 测试Excel表导出客户信息
	 * @throws IOException */
	@Test
	public void testExportExcelClient() throws IOException{
		Client cli = new Client();
		PageObject pageObject = new PageObject();
		pageObject.setRowCount(clientDao.getRowCount(cli));
		List<Client> data = clientDao.findObjects(cli, pageObject);
		for (Client client : data) {
			System.out.println(client);
		}
		//测试导出客户数据信息
		ExportExcel<Client> expExcel = new ExportExcel<Client>();
		String[] headers = {"姓名", "性别", "年龄", "作息时间", "电话", "地址", "皮肤状态", "备注"};
		String sheetName = "客户";
		String pattern = "HH:mm:ss";
		FileOutputStream out = new FileOutputStream("E:\\ExcelDemo\\client.xls");
		expExcel.exportExcelCar(headers, sheetName, data, out, pattern);
	}
	
	/** 测试根据名字查询客户信息*/
	@Test
	public void testFindObjectByName(){
		String name = "迪丽";
		List<Client> list = clientDao.findObjectByName(name);
		for(Client c : list){
			System.out.println(c);
		}
	}
	
	/**
	 * 	MyBatis在底层会依据接口要求(即Mapper映射器)
	 * 	生成符合接口要求的对象.
	 */
	
	/** 测试查询客户
	 * @throws ParseException */
	@Test
	public void testFindObjects() throws ParseException{
		Client client = new Client();
//		client.setAddress("邵原");
//		client.setSkinCondition("do");
//		client.setSex("男");
//		client.setName("花");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//		System.out.println(sdf.format(new Date()));
//		System.out.println(sdf.parse("23:00:00"));
		client.setTimetable(sdf.parse("23:00:00"));
		PageObject pageObject = new PageObject();
//		pageObject.setCurrentPage();
		pageObject.setPageSize(10);
		pageObject.setRowCount(clientDao.getRowCount(client));
//		System.out.println(clientDao.getRowCount(client));
//		System.out.println(pageObject.getTotalPage());
//		System.out.println(pageObject.getStartIndex());
		List<Client> clients = clientDao.findObjects(client, pageObject);
		for(Client c : clients){
			System.out.println(c);
		}
//		System.out.println(clients);
		
	}
	
	/** 测试根据id查询一个客户信息*/
	@Test
	public void testFindObjectById(){
		Client client = clientDao.findObjectById(1);
		System.out.println(client);
	}
	
	/** 测试存储客户信息*/
	@Test
	public void testSaveObject(){
		Client client = new Client();
		client.setName("杨啦啦");
		client.setValid(1);
		client.setAddress("河南省济源市");
		client.setSex("女");
		client.setAge(24);
		int n = clientDao.saveObject(client);
		System.out.println(n);
	}
	
	/** 测试根据id删除一条客户信息*/
	@Test
	public void testDeleteObject(){
		int n = clientDao.deleteObject(6);
		System.out.println(n);
	}
	
	/** 测试更新客户信息*/
	@Test
	public void testUpdateObject(){
		Client client = new Client();
		client.setId(22);
		client.setName("张三");
		client.setAge(23);
		client.setSex("女");
		client.setAddress("河南省济源市");
		client.setSkinCondition("这个我还真不清楚怎么表达");
		client.setPhone("13838384388");
		int rows = clientDao.updateObject(client);
		System.out.println(rows);
		Assert.assertEquals(1, rows);
	}
	
	
}










