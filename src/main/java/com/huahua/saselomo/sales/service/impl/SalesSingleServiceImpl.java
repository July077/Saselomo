package com.huahua.saselomo.sales.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huahua.saselomo.client.entity.ClientPurHistory;
import com.huahua.saselomo.client.service.ClientPurHistoryService;
import com.huahua.saselomo.common.exception.DeleteObjectException;
import com.huahua.saselomo.common.exception.FindObjectException;
import com.huahua.saselomo.common.exception.NullPropertyException;
import com.huahua.saselomo.common.exception.SaveObjectException;
import com.huahua.saselomo.common.exception.UpdateObjectException;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.inventory.entity.Inventory;
import com.huahua.saselomo.inventory.service.InventoryService;
import com.huahua.saselomo.sales.dao.SalesSingleDao;
import com.huahua.saselomo.sales.entity.ProductSalesSingle;
import com.huahua.saselomo.sales.entity.Sales;
import com.huahua.saselomo.sales.entity.SalesSingle;
import com.huahua.saselomo.sales.service.ProSalesSingleService;
import com.huahua.saselomo.sales.service.SalesService;
import com.huahua.saselomo.sales.service.SalesSingleService;
/**
 * 售货单子项业务层实现类
 * @author Lin·Y
 *
 */
@Service
public class SalesSingleServiceImpl implements SalesSingleService {
	@Autowired
	@Qualifier("salesSingleDao")
	private SalesSingleDao salesSingleDao;
	@Autowired
	@Qualifier("proSalesSingleServiceImpl")
	private ProSalesSingleService proSalesSingleService;
	@Autowired
	@Qualifier("salesServiceImpl")
	private SalesService salesService;
	@Autowired
	@Qualifier("inventoryServiceImpl")
	private InventoryService inventoryService;
	@Autowired
	@Qualifier("clientPurHistoryServiceImpl")
	private ClientPurHistoryService cphService;
	
	public void confirmSalSingle(String salSinIds, Integer salId) {
		//1. 将选中子项进行售货
		if (salSinIds==null || salSinIds.length()==0) {
			throw new NullPropertyException("必须有选中的id值...");
		}
		//解析id字符串
		String[] ids = salSinIds.split(",");
		int rows = salesSingleDao.confirmSalSingle(ids);
		if(rows == -1){
			throw new UpdateObjectException("售货失败..");
		}
		//2. 将子项关联的售货单进行状态更新
		if(salId == null){
			throw new NullPropertyException("id不能为空...");
		}
		updateSalValid(salId);
		//3. 进行库存的更新
		for(int i=0; i<ids.length; i++){
			Integer id = Integer.parseInt(ids[i]);
			updateInventory(id, "confirm");
		}
		//4. 进行客户购买记录的插入|更新(若有当天购买记录,则更新)
		saveOrUpdatePurHistory(ids, salId);
	}
	/**
	 * 进行客户购买记录的插入|更新(若有当天购买记录,则更新)
	 * @param ids 子项的id
	 * @param salId 售货单id
	 */
	private void saveOrUpdatePurHistory(String[] ids, Integer salId) {
		//1. 查询售货要记录的购买记录,记录全称和简称
		List<Map<String, Object>> lsit = salesSingleDao.findConfirmObjects(ids);
		String purProSimp = null;//简称记录
		String purProFull = null;//全称记录
		for(Map<String, Object> map : lsit){
			if(purProSimp == null){//第一次赋值
				purProSimp = (String)map.get("abbreviation")+"*"+(Integer)map.get("count");
				purProFull = (String)map.get("name")+"*"+(Integer)map.get("count");
			}else{
				purProSimp += ","+(String)map.get("abbreviation")+"*"+(Integer)map.get("count");
				purProFull += ","+(String)map.get("name")+"*"+(Integer)map.get("count");
			}
		}
		//2. 查询出售货单对应的客户id和出售日期
		Map<String, Object> saleClient = salesService.findCliAndSalById(salId);
		//3. 查询是否有此客户的当日的购买记录
		ClientPurHistory cph = new ClientPurHistory();//创建客户购买记录对象
		cph.setClientId((Integer)saleClient.get("clientId"));
		cph.setPurchaseDate((Date)saleClient.get("saleDate"));
		List<ClientPurHistory> list = cphService.findObjects(cph);//当前客户当天的购买记录为1条
		if(list.isEmpty()){//没有此客户当日购买记录
			cph.setPurProFull(purProFull);
			cph.setPurProSimp(purProSimp);
			cphService.saveObject(cph);//插入
		}else{//有此客户当日购买记录
			cph = list.get(0);
			cph.setPurProFull(cph.getPurProFull()+","+purProFull);
			cph.setPurProSimp(cph.getPurProSimp()+","+purProSimp);
			cphService.updateObject(cph);
		}
	}
	/**
	 * 根据售货单子项id,更新库存信息
	 * @param id
	 */
	private void updateInventory(Integer id, String action){
		//获取售货单子项的相关信息
		Map<String, Object> salSingle = salesSingleDao.findObjectById(id);
		//获取产品id
		Integer productId = (Integer)salSingle.get("productId");
		//获取产品总数量
		Integer count = (Integer)salSingle.get("count");
		//根据产品id查询库存信息
		Inventory inven = inventoryService.findObjectByProId(productId);
		if(inven == null){//库存没有,存在异常情况，售货失败
			
		}
		//获取总数量
		Integer invCount = inven.getInventoryCount();
		//获取冻结数量
//		Integer invFreeze = inven.getInventoryFreeze();
		//获取可用
		Integer invAvailable = inven.getInventoryAvailable();
		//获取订单
		Integer invOrderForm = inven.getInventoryOrderForm();
		if("confirm".equals(action)){//确认销售
			//总数量=原数量-销售数
			inven.setInventoryCount(invCount-count);
			//库存订单=原订单-销售数
			inven.setInventoryOrderForm(invOrderForm-count);
			inventoryService.updateObject(inven);
		}else if("delete".equals(action)){//删除子项
			//库存可用=原可用+删除订单数
			inven.setInventoryAvailable(invAvailable+count);
			//库存订单=原订单-删除订单数
			inven.setInventoryOrderForm(invOrderForm-count);
			inventoryService.updateObject(inven);
		}else if("save".equals(action)){//存储子项
			//订单数=原订单数+销售数
			inven.setInventoryOrderForm(invOrderForm+count);
			//库存可用=原可用-销售数
			inven.setInventoryAvailable(invAvailable-count);
			inventoryService.updateObject(inven);//更新库存
		}else if("updateFront".equals(action)){//修改子项,先进行修改前的库存更新
			//库存可用=原可用+删除订单数
			inven.setInventoryAvailable(invAvailable+count);
			//库存订单=原订单-删除订单数
			inven.setInventoryOrderForm(invOrderForm-count);
			inventoryService.updateObject(inven);
		}else if("updateLater".equals(action)){
			//订单数=原订单数+销售数
			inven.setInventoryOrderForm(invOrderForm+count);
			//库存可用=原可用-销售数
			inven.setInventoryAvailable(invAvailable-count);
			inventoryService.updateObject(inven);//更新库存
		}
	}
	/**
	 * 更新售货单状态
	 * @param salId 根据id更新
	 */
	private void updateSalValid(Integer salId){
		//获取对应售货单对象
		Sales sal = salesService.findSalesById(salId);
		//获取相关全部售货单子项
		List<Map<String, Object>> sales = salesSingleDao.findObjects(null, salId, null);
		int count = 0;//计数
		//遍历子项
		for(Map<String, Object> salSingle : sales){
			if ((Integer)salSingle.get("valid") == 3) {
				count++;//是售货状态，+1
			}
		}
		if(count == sales.size()){//相等为全部售货
			sal.setValid(3);
			salesService.updateObject(sal);
		}else{//否则为部分售货
			sal.setValid(2);
			salesService.updateObject(sal);
		}
	}

	public Map<String, Object> findObject(String productName, Integer parentId, PageObject pageObject) {
		//获取页面表格要显示的数据
		List<Map<String, Object>> salesSingles = salesSingleDao.findObjects(productName, parentId, pageObject);
		//获取表中记录并计算页数
		int rowCount = salesSingleDao.getRowCount(productName, parentId);
//		pageObject.setPageSize(5);
		pageObject.setRowCount(rowCount);
		//构建Map对象,封装从dao层获取的数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("salesSingles", salesSingles);//记录信息
		map.put("pageObject", pageObject);//分页数据
		return map;
	}

	public void saveObject(SalesSingle salSingle, Integer productId) {
		//1. 存储售货单信息
		int numSalSingle = salesSingleDao.saveObject(salSingle);
		if(numSalSingle != 1){
			throw new SaveObjectException("存储失败,请稍后再试...");
		}
		Integer salesSingleId = salSingle.getId();//获取当前存储售货单子项的id
		//2. 新建产品和售货单子项的对应关系对象
		ProductSalesSingle proSalSingle = new ProductSalesSingle();
		proSalSingle.setProductId(productId);
		proSalSingle.setSalesSingleId(salesSingleId);
		proSalesSingleService.saveObject(proSalSingle);
		//3. 更新当前售货单总价格信息
		Sales sal = salesService.findSalesById(salSingle.getSalesId());//获取当前关联售货单
		Double totalPrice = sal.getTotalPrice();//获取售货单总价
		updateSalTotalPrice(salSingle, totalPrice, sal);
		//4. 更新库存可用数量和订单数量
		updateInventory(salesSingleId, "save");
	}
	/**
	 * 更新售货单总价格
	 * @param receivingSingle
	 */
	private void updateSalTotalPrice(SalesSingle salSingle, Double totalPrice, Sales sales){
		Double saleTotalPrice = salSingle.getSalePrice() * salSingle.getCount();//当前售货单子项总价格
		if(totalPrice != null){//如果当前售货单已有总价格
			totalPrice += saleTotalPrice;//加上当前子项总价
		}else{
			totalPrice = saleTotalPrice;
		}
		sales.setTotalPrice(totalPrice);//设置总价格
		salesService.updateObject(sales);
	}

	public Map<String, Object> findObjectById(Integer id) {
		if(id == null){
			throw new NullPropertyException("id不能为空...");
		}
		Map<String, Object> salSingles = salesSingleDao.findObjectById(id);
		//售货单信息为空,说明售货单不存在,抛出异常
		if(salSingles.isEmpty()){
			throw new FindObjectException("售货单信息不存在...");
		}
		return salSingles;
	}

	public void updateObject(SalesSingle salesSingle) {
		//进行判定此售货单子项是否为售货
		Map<String, Object> ss = salesSingleDao.findObjectById(salesSingle.getId());
		if((Integer)ss.get("valid")==2){
			throw new UpdateObjectException("此售货单子项为部分售货,无法更新");
		}else if((Integer)ss.get("valid")==3){
			throw new UpdateObjectException("此售货单子项为全部售货,无法更新");
		}
		//1. 更新当前售货单价格
		Sales sal = salesService.findSalesById(salesSingle.getSalesId());//获取当前关联收货单
		Double totalPrice = salSingleIsUpdate(salesSingle, sal);
		updateSalTotalPrice(salesSingle, totalPrice, sal);//更新当前售货单总价格
		//2. 更新前还原库存信息
		updateInventory(salesSingle.getId(), "updateFront");
		//3. 执行更新动作
		int num = salesSingleDao.updateObject(salesSingle);
		if(num == -1){
			throw new UpdateObjectException("更新失败,请稍后重试...");
		}
		//4. 更新完毕,进行库存更新
		updateInventory(salesSingle.getId(), "updateLater");
	}
	/**
	 * 更新或删除,先减去子项的原有价格
	 * @param salSingle 
	 * @return 返回减去子项后的价格
	 */
	private Double salSingleIsUpdate(SalesSingle salSingle, Sales sal){
		Double totalPrice = sal.getTotalPrice();//获取售货单总价
		Map<String, Object> map = salesSingleDao.findObjectById(salSingle.getId());//更新时,先获取表中的当前售货单子项
		Integer count = (Integer) map.get("count");//获取数量
		Double salePrice = (Double)map.get("salePrice");//获取价格
		totalPrice -= (count * salePrice);//总价减去原有价格
		return totalPrice;
	}

	public void deleteObject(Integer id, Integer salesId) {
		//进行判定此售货单子项是否为售货
		Map<String, Object> salesSingle = salesSingleDao.findObjectById(id);
		if((Integer)salesSingle.get("valid")==2){
			throw new DeleteObjectException("此售货单子项为部分售货,无法删除");
		}else if((Integer)salesSingle.get("valid")==3){
			throw new DeleteObjectException("此售货单子项为全部售货,无法删除");
		}
		//1. 进行子项删除后售货单价格计算
		delTotalPriceUpdate(id, salesId);
		//2. 进行库存更新
		updateInventory(id, "delete");
		//3. 执行删除动作
		if(id == null){
			throw new NullPropertyException("id不能为空...");
		}
		int rowSalSingle = salesSingleDao.deleteObject(id);
		//返回-1,说明删除失败
		if(rowSalSingle == -1){
			throw new DeleteObjectException("售货单子项删除失败,请稍后再试...");
		}
		//同时删除产品和收货单子项对应关系表，对应信息
		proSalesSingleService.deleteObject(id);
	}
	/**
	 * 进行删除子项后,价格更新
	 * @param id
	 * @param receivingId
	 */
	private void delTotalPriceUpdate(Integer id, Integer salesId){
		Map<String, Object> map = salesSingleDao.findObjectById(id);//查询当前删除对象信息
		SalesSingle salSingle = new SalesSingle();//创建售货单子项对象
		salSingle.setId(id);
		salSingle.setCount((Integer)map.get("count"));
		salSingle.setSalePrice((Double)map.get("SalePrice"));
		Sales sal = salesService.findSalesById(salesId);//获取当前关联售货单
		Double totalPrice = salSingleIsUpdate(salSingle, sal);//通过此方法减去删除子项的价格
		sal.setTotalPrice(totalPrice);
		salesService.updateObject(sal);//更新售货单
	}

}
