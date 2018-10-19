package com.huahua.saselomo.sales.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huahua.saselomo.client.entity.Client;
import com.huahua.saselomo.client.service.ClientService;
import com.huahua.saselomo.common.exception.DeleteObjectException;
import com.huahua.saselomo.common.exception.FindObjectException;
import com.huahua.saselomo.common.exception.NullPropertyException;
import com.huahua.saselomo.common.exception.SaveObjectException;
import com.huahua.saselomo.common.exception.UpdateObjectException;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.sales.dao.SalesDao;
import com.huahua.saselomo.sales.dao.SalesSingleDao;
import com.huahua.saselomo.sales.entity.ClientSales;
import com.huahua.saselomo.sales.entity.Sales;
import com.huahua.saselomo.sales.service.ClientSalesService;
import com.huahua.saselomo.sales.service.SalesService;
/**
 * 售货单业务层实现类,实现相关判定业务:
 * 	如:售货单状态,售货单总价,售货单与售货单子项的关联
 * @author Lin·Y
 *
 */
@Service
public class SalesServiceImpl implements SalesService {
	@Autowired
	@Qualifier("salesDao")
	private SalesDao salesDao;
	@Autowired
	@Qualifier("clientSalesServiceImpl")
	private ClientSalesService clientSalesService;
	@Autowired
	@Qualifier("clientServiceImpl")
	private ClientService clientService;
	@Autowired
	@Qualifier("salesSingleDao")
	private SalesSingleDao salesSingleDao;
	
	public Map<String, Object> findObjects(String clientName, Sales sale, PageObject pageObject) {
		//获取页面表格要显示的数据
		List<Map<String, Object>> sales = salesDao.findObjects(clientName, sale, pageObject);
		//将销售时间更改页面显示格式,date类型默认返long型
		for(Map<String, Object> map : sales){
			Date searchSaleDate = (Date)map.get("saleDate");//获取销售时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			map.put("saleDate", sdf.format(searchSaleDate));
		}
		//获取表中记录并计算页数
		int rowCount = salesDao.getRowCount(clientName, sale);
		pageObject.setRowCount(rowCount);
		//构建Map对象,封装从dao层获取的数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sales", sales);//记录信息
		map.put("pageObject", pageObject);//分页数据
		return map;
	}

	public void saveObject(Sales sales, Integer clientId) {
		//1.储存售货单信息
//		售货单编号为空,默认自动生成
		if(sales.getSaleCode()==null || sales.getSaleCode()==""){
			String receiptCode = getSaleCode();
			sales.setSaleCode(receiptCode);
		}
//		销售时间为空，默认当前时间
		if(sales.getSaleDate() == null){
			sales.setSaleDate(new Date());
		}
		int rows = salesDao.saveObject(sales);
		//返回-1,说明存储失败
		if(rows == -1){
			throw new SaveObjectException("售货单信息存储失败,请稍后再试...");
		}
		//2.储存客户与售货单关系信息
		ClientSales cs = new ClientSales();//创建客与售关系对象
		cs.setClientId(clientId);
		cs.setSalesId(sales.getId());
		clientSalesService.saveObject(cs);

	}
	/**
	 * 当售货单编号为空时，系统自动生成售货单编号，并返回给调用者;
	 * 格式为S(sales,第一个字母)+日期(20180806)+第几单(001,第一单)
	 * @return
	 */
	private String getSaleCode() {
		String saleCode = null;//生成的售货单编号
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String codeDate = sdf.format(new Date());//售货单日期部分
		String codePrelusion = "S"+ codeDate;//售货单编号前两个部分
		List<Sales> list = salesDao.findObjectsBySaleCode(codePrelusion);//获取查询记录
		if(list.isEmpty()){
			saleCode = codePrelusion + "001";
			return saleCode;
		}
		Sales sales = list.get(0);//最晚创建时间的售货单对象
		String receiptCodeTable = sales.getSaleCode();//最晚创建时间的售货单对象的编号
		Integer endCode = 
				Integer.valueOf(sales.getSaleCode().
						substring(receiptCodeTable.length()-3));//最晚创建时间售货单编号的后三位，转换为Integer类型
		if(endCode < 9){//小于9
			saleCode = codePrelusion + "00" + (endCode+1);
		}else if(endCode < 99){//小于99,大于9 
			saleCode = codePrelusion + "0" + (endCode+1);
		}else{//大于100
			saleCode = codePrelusion + (endCode+1);
		}
		return saleCode;
	}

	public void updateObject(Sales sales, Integer clientId) {
		//1. 更新售货单信息
		int rows = salesDao.updateObject(sales);
		if(rows != 1){//正常情况下是1,若不是,则是更新失败
			throw new UpdateObjectException("售货单信息更新失败,请稍后再试...");
		}
		//2. 同时更新客户与售货单对应关系中客户id
		ClientSales cs = new ClientSales();//创建客与售对象
		cs.setClientId(clientId);
		cs.setSalesId(sales.getId());
		clientSalesService.updateObject(cs);
	}

	public void deleteObject(Integer id) {
		//1. 判定此售货单是否为部分售货或者为全部售货
		Sales sales = salesDao.findObjectById(id);
		if(sales.getValid() == 2){
			throw new DeleteObjectException("此售货单已部分售货,无法删除");
		}else if(sales.getValid()==3){
			throw new DeleteObjectException("此售货单已全部售货,无法删除");
		}
		//2. 判断此售货单是否有子项
		List<Map<String , Object>> list = salesSingleDao.findObjects(null, id, null);//若关联有子项
		if(!list.isEmpty()){//抛出异常,无法删除
			throw new DeleteObjectException("请先删除此售货单子项...");
		}
		//3. 删除售货单信息
		int rows = salesDao.deleteObject(id);
		//返回-1,说明删除失败
		if(rows == -1){
			throw new DeleteObjectException("售货单信息删除失败,请稍后再试...");
		}
		//2. 同时删除客与售对应的信息
		clientSalesService.deleteObject(id);
	}

	public Map<String, Object> findObjectById(Integer id) {
		if(id == -1){
			throw new NullPropertyException("id不能为空...");
		}
		//1. 查询售货单信息
		Sales sales = salesDao.findObjectById(id);
		//收货单信息为空,说明收货单不存在,抛出异常
		if(sales == null){
			throw new FindObjectException("售货单信息不存在...");
		}
		//2. 查询对应客户id
		ClientSales cs = clientSalesService.findObjectBySalesId(id);
		//3. 查询对应客户信息
		Client c = clientService.findObjectById(cs.getClientId());
		//4. 创建map,封装售货单信息和客户信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sales", sales);
		map.put("client", c);
		return map;
	}

	public Sales findSalesById(Integer id) {
		if(id == -1){
			throw new NullPropertyException("id不能为空...");
		}
		Sales sales = salesDao.findObjectById(id);//查询
		return sales;
	}

	public void updateObject(Sales sales) {
		// 更新售货单信息
		int rows = salesDao.updateObject(sales);
		if(rows != 1){//正常情况下是1,若不是,则是更新失败
			throw new UpdateObjectException("售货单信息更新失败,请稍后再试...");
		}
	}

	public Map<String, Object> findCliAndSalById(Integer id) {
		Map<String, Object> map = salesDao.findCliAndSalById(id);
		return map;
	}

}
