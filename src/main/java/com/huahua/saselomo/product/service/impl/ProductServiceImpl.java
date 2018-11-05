package com.huahua.saselomo.product.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.huahua.saselomo.common.excel.ExportExcel;
import com.huahua.saselomo.common.excel.ImportExcel;
import com.huahua.saselomo.common.exception.DeleteObjectException;
import com.huahua.saselomo.common.exception.FindObjectException;
import com.huahua.saselomo.common.exception.ImportObjectException;
import com.huahua.saselomo.common.exception.NullPropertyException;
import com.huahua.saselomo.common.exception.SaveObjectException;
import com.huahua.saselomo.common.exception.UpdateObjectException;
import com.huahua.saselomo.common.util.CommonUtil;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.inventory.entity.Inventory;
import com.huahua.saselomo.inventory.service.InventoryService;
import com.huahua.saselomo.product.dao.ProductDao;
import com.huahua.saselomo.product.entity.Product;
import com.huahua.saselomo.product.service.ProductService;
/**
 * 产品管理Service
 * @author Lin·Y
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	@Qualifier("productDao")
	private ProductDao productDao;
	@Autowired
	@Qualifier("inventoryServiceImpl")
	private InventoryService inventoryService;
	
	public List<Product> findInvenInObjectByName(String name) {
		List<Product> products = productDao.findInvenInObjectByName(name);
		return products;
	}

	public List<Product> findObjectByName(String name) {
		List<Product> products = productDao.findObjectByName(name);
		return products;
	}
	public Map<String, Object> findObjects(Product product, PageObject pageObject) {
//		System.out.println("findObjects");
		//获取页面显示的产品信息 
		List<Product> products = productDao.findObjects(product, pageObject);
		//获取表中总记录数,并计算页数
		int rowCount = productDao.getRowCount(product);
		pageObject.setRowCount(rowCount);
		//创建Map对象,封装从dao层获取的数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("products", products);
		map.put("pageObject", pageObject);
		return map;
	}
	public Product findObjectById(Integer id) {
//		System.out.println("findObjectById");
		//若id为空,抛出异常空属性
		if(id == null){
			throw new NullPropertyException("Id不能为空...");
		}
		Product product = productDao.findObjectById(id);
		//产品信息为空,说明产品不存在,抛出异常
		if(product == null){
			throw new FindObjectException("产品信息不存在...");
		}
		return product;
	}
	public void saveObject(Product product) {
//		System.out.println("saveObject");
		int rows = productDao.saveObject(product);
		//返回-1,说明存储失败
		if(rows == -1){
			throw new SaveObjectException("产品信息存储失败,请稍后再试...");
		}
	}
	public void deleteObject(Integer id) {
		if(id == null){
			throw new NullPropertyException("id不能为空...");
		}
		Inventory inven = inventoryService.findObjectByProId(id);
		if(inven != null){//说明有库存,此产品不能删除
			throw new DeleteObjectException("产品已有库存,库存数量为:"+inven.getInventoryCount()+",不能删除");
		}
		int rows = productDao.deleteObject(id);
		//返回-1,说明删除失败
		if(rows == -1){
			throw new DeleteObjectException("产品信息删除失败,请稍后再试...");
		}
	}
	public void updateObject(Product product) {
		int rows = productDao.updateObject(product);
		if(rows != 1){//正常情况下是1,若不是,则是更新失败
			throw new UpdateObjectException("产品信息更新失败,请稍后再试...");
		}
	}
	public void exportProduct(OutputStream out) {
		//1. 查询产品数据
		Product pro = new Product();
		List<Product> data = productDao.findObjects(pro, null);
		//2. 导出产品数据信息
		ExportExcel<Product> expExcel = new ExportExcel<Product>();
		String[] headers = {"产品名", "产品简称", "一级价格", "二级价格", "至尊价格", "微商价格", "零售价格", "箱规","功效", "卖点", "备注"};
		String sheetName = "产品";
		String pattern = "yyyy-MM-dd";
		expExcel.exportExcelCar(headers, sheetName, data, out, pattern);
	}
	public void importExcelInfo(MultipartFile mFile) throws IOException {
		InputStream in = null;
		try {
			//1. 获取输入流
			 in = mFile.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		//2. 获取文件名
		String filename = mFile.getOriginalFilename();
		//3. 产品标题列
		String[] titles = {"产品名", "产品简称", "一级价格", "二级价格", "至尊价格", "微商价格", "零售价格", "箱规", "功效", "卖点", "备注"};
		//4. 获取导入产品信息,并进行封装
		ImportExcel iExcel = new ImportExcel();
		List<List<Object>>list = iExcel.importExcelCar(in, filename, titles);//获取导入的产品信息
		List<Product> products = new ArrayList<Product>();//声明封装产品数据信息集合
		for (int i = 0; i < list.size(); i++) {
			Product pro = new Product();//声明产品对象
			if ("".equals(list.get(i).get(0).toString())) {
				throw new ImportObjectException("产品名不能为空,位于第"+(i+2)+"行,第1列");
			}
			pro.setName(list.get(i).get(0).toString());//产品名
			pro.setAbbreviation(list.get(i).get(1).toString());//简称
			if ("".equals(list.get(i).get(2).toString())) {//一级价格
				pro.setFirstStage(0d);
			}else {
				String exceptionDesc = "一级价格填写错误,请参照模板说明填写,位于第"+(i+2)+"行,第3列";
				pro.setFirstStage(CommonUtil.parseDouble(list.get(i).get(2).toString(), exceptionDesc));
			}
			if ("".equals(list.get(i).get(3).toString())) {//二级价格
				pro.setSecondStage(0d);
			}else {
				String exceptionDesc = "二级价格填写错误,请参照模板说明填写,位于第"+(i+2)+"行,第4列";
				pro.setSecondStage(CommonUtil.parseDouble(list.get(i).get(3).toString(), exceptionDesc));
			}
			if ("".equals(list.get(i).get(4).toString())) {//至尊价格
				pro.setSupremacy(0d);
			}else {
				String exceptionDesc = "至尊价格填写错误,请参照模板说明填写,位于第"+(i+2)+"行,第5列";
				pro.setSupremacy(CommonUtil.parseDouble(list.get(i).get(4).toString(), exceptionDesc));
			}
			if ("".equals(list.get(i).get(5).toString())) {//微商价格
				pro.setDerivative(0d);
			}else {
				String exceptionDesc = "微商价格填写错误,请参照模板说明填写,位于第"+(i+2)+"行,第6列";
				pro.setDerivative(CommonUtil.parseDouble(list.get(i).get(5).toString(), exceptionDesc));
			}
			if ("".equals(list.get(i).get(6).toString())) {//零售价格
				pro.setRetail(0d);
			}else {
				String exceptionDesc = "零售价格填写错误,请参照模板说明填写,位于第"+(i+2)+"行,第7列";
				pro.setRetail(CommonUtil.parseDouble(list.get(i).get(6).toString(), exceptionDesc));
			}
			String exceptionDesc = "箱规填写错误,请参照模板说明填写,位于第"+(i+2)+"行,第8列";
			pro.setCartonSize(CommonUtil.parseInt(list.get(i).get(7).toString(), exceptionDesc));//箱规
			pro.setEffect(list.get(i).get(8).toString());//功效
			pro.setSellingPoints(list.get(i).get(9).toString());//卖点
			pro.setNote(list.get(i).get(10).toString());//备注
			pro.setValid(1);//状态
			pro.setCreatedUser("花花");//创建人
			products.add(pro);//封装产品数据
		}
		//5. 存储产品数据
		productDao.importObject(products);
		
	}

}
