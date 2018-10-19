package product.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.huahua.saselomo.common.excel.ExportExcel;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.product.entity.Product;

import common.TestBase;
/**
 * 产品dao层测试
 * @author Lin·Y
 *
 */
public class TestProductDao extends TestBase{
	/** 测试批量导入*/
	@Test
	public void testImportObject(){
		//模拟导入数据
		List<Product> list = new ArrayList<Product>();
		for (int i = 0; i < 3; i++) {
			Product p = new Product();
			p.setName("测试产品"+i);
			p.setAbbreviation("产品简称"+i);
			p.setValid(1);
			p.setFirstStage(12d+i);
			p.setSecondStage(13d+i);
			p.setSupremacy(14d+i);
			p.setDerivative(15d+i);
			p.setRetail(16d+i);
			p.setCartonSize(10);
			p.setEffect("测试功效"+i);
			p.setSellingPoints("测试卖点"+i);
			p.setNote("测试备注"+i);
			list.add(p);
		}
		int num = productDao.importObject(list);
		Assert.assertNotEquals(-1, num);
	}
	/** 测试Excel表导出客户信息
	 * @throws IOException */
	@Test
	public void testExportExcelProduct() throws IOException{
		Product pro = new Product();
		PageObject pageObject = new PageObject();
		pageObject.setRowCount(productDao.getRowCount(pro));
		List<Product> data = productDao.findObjects(pro, pageObject);
		for (Product p : data) {
			System.out.println(p);
		}
		//测试导出客户数据信息
		ExportExcel<Product> expExcel = new ExportExcel<Product>();
		String[] headers = {"产品名", "产品简称", "一级价格", "二级价格", "至尊价格", "微商价格", "零售价格", "箱规","功效", "卖点", "备注"};
		String sheetName = "产品";
		String pattern = "yyyy-MM-dd";
		FileOutputStream out = new FileOutputStream("E:\\ExcelDemo\\client.xls");
		expExcel.exportExcelCar(headers, sheetName, data, out, pattern);
	}
	/** 测试根据产品名模糊查询库存内产品信息*/
	@Test
	public void testFindInvenInObjectByName(){
		//模糊查询对象
		String name = "";
		//获取产品信息
		List<Product> products = productDao.findInvenInObjectByName(name);
		Assert.assertNotNull(products);
		for(Product p : products){
			System.out.println(p);
		}
	}
	/** 测试根据产品名模糊查询产品信息*/
	@Test
	public void testFindObjectsByName(){
		//模糊查询对象
		String name = "精华水";
		//获取产品信息
		List<Product> products = productDao.findObjectByName(name);
		Assert.assertNotNull(products);
		for(Product p : products){
			System.out.println(p);
		}
		System.out.println(products);
	}
	/** 测试查询产品信息*/
	@Test
	public void testFindObjects(){
		//模糊查询对象
		Product product = new Product();
		//分页对象
		PageObject pageObject = new PageObject();
		//添加总记录数
		pageObject.setRowCount(productDao.getRowCount(product));
		//获取产品信息
		List<Product> products = productDao.findObjects(product, pageObject);
		Assert.assertNotNull(products);
		System.out.println(products);
	}
	
	/** 测试存储*/
	@Test
	public void testSaveObject(){
		//创建存储对象
		Product product = new Product();
		product.setName("自然肌悦滋养沐浴露");
		product.setAbbreviation("沐浴露");
		product.setValid(1);
		product.setFirstStage(46d);
		product.setSecondStage(56d);
		product.setSupremacy(66d);
		product.setDerivative(88d);
		product.setRetail(99d);
		product.setCartonSize(10);
		product.setEffect("肌悦滋养");
		product.setSellingPoints("功效强大");
		product.setNote("柑橘有用");
		product.setCreatedUser("花花");
		int num = productDao.saveObject(product);//存储
		Assert.assertEquals(1, num);
	}
	
	/** 测试删除*/
	@Test
	public void testDeleteObject(){
		int num = productDao.deleteObject(3);
		Assert.assertEquals(1, num);
	}
	
	/** 测试更新*/
	@Test
	public void testUpdateObject(){
		//根据id查询要更新的产品
		Product product = productDao.findObjectById(4);
		System.out.println(product);
		product.setName("测试产品");
		//创建更新对象
		int num = productDao.updateObject(product);
		Assert.assertEquals(1, num);
	}
	
	
	
}
