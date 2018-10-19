package com.huahua.saselomo.receiving.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huahua.saselomo.common.exception.DeleteObjectException;
import com.huahua.saselomo.common.exception.FindObjectException;
import com.huahua.saselomo.common.exception.NullPropertyException;
import com.huahua.saselomo.common.exception.SaveObjectException;
import com.huahua.saselomo.common.exception.UpdateObjectException;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.inventory.entity.Inventory;
import com.huahua.saselomo.inventory.service.InventoryService;
import com.huahua.saselomo.receiving.dao.ReceivingSingleDao;
import com.huahua.saselomo.receiving.entity.ProductReceivingSingle;
import com.huahua.saselomo.receiving.entity.Receiving;
import com.huahua.saselomo.receiving.entity.ReceivingSingle;
import com.huahua.saselomo.receiving.service.ProductReceivingSingleService;
import com.huahua.saselomo.receiving.service.ReceivingService;
import com.huahua.saselomo.receiving.service.ReceivingSingleService;
@Service
public class ReceivingSingleServiceImpl implements ReceivingSingleService {
	@Autowired
	@Qualifier("receivingSingleDao")
	private ReceivingSingleDao receivingSingleDao;
	@Autowired
	@Qualifier("productReceivingSingleServiceImpl")
	private ProductReceivingSingleService productReceivingSingleService;
	@Autowired
	@Qualifier("receivingServiceImpl")
	private ReceivingService receivingService;
	@Autowired
	@Qualifier("inventoryServiceImpl")
	private InventoryService inventoryService;

	public Map<String, Object> findObject(String productName, Integer parentId, PageObject pageObject) {
		//获取页面表格要显示的数据
		List<Map<String, Object>> receivingSingles = receivingSingleDao.findObjects(productName, parentId, pageObject);
		//获取表中记录并计算页数
		int rowCount = receivingSingleDao.getRowCount(productName, parentId);
//		pageObject.setPageSize(5);
		pageObject.setRowCount(rowCount);
		//构建Map对象,封装从dao层获取的数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("receivingSingles", receivingSingles);//记录信息
		map.put("pageObject", pageObject);//分页数据
		return map;
	}

	public void saveObject(ReceivingSingle recSingle, Integer productId) {
		//1. 存储收获单信息
		int numRecSingle = receivingSingleDao.saveObject(recSingle);
		if(numRecSingle != 1){
			throw new SaveObjectException("存储失败,请稍后再试...");
		}
		Integer receivingSingleId = recSingle.getId();//获取当前存储收获单子项的id
//		System.out.println("receivingSingleId="+receivingSingleId);
		//2. 新建产品和收货单子项的对应关系对象
		ProductReceivingSingle proRecSingle = new ProductReceivingSingle();
		proRecSingle.setProductId(productId);
		proRecSingle.setReceivingSingleId(receivingSingleId);
		productReceivingSingleService.saveObject(proRecSingle);
		//3. 更新当前收货单总价格信息
		Receiving rec = receivingService.findObjectById(recSingle.getReceivingId());//获取当前关联收货单
		Double totalPrice = rec.getTotalPrice();//获取收货单总价
//		System.out.println("totalPrice="+totalPrice);
		updateRecTotalPrice(recSingle, totalPrice, rec);
	}

	public Map<String, Object> findObjectById(Integer id) {
		if(id == null){
			throw new NullPropertyException("id不能为空...");
		}
		Map<String, Object> recSingles = receivingSingleDao.findObjectById(id);
		//收货单信息为空,说明收货单不存在,抛出异常
		if(recSingles.isEmpty()){
			throw new FindObjectException("收货单信息不存在...");
		}
		return recSingles;
	}

	public void updateObject(ReceivingSingle recSingle){
		//进行判定此收货单子项是否为收货状态
		Map<String, Object> rs = receivingSingleDao.findObjectById(recSingle.getId());
		if((Integer)rs.get("valid")==2){
			throw new UpdateObjectException("此收货单子项为部分收货,无法更新");
		}else if ((Integer)rs.get("valid")==3) {
			throw new UpdateObjectException("此收货单子项为全部收货,无法更新");
		}
		//1. 进行此售货单价格更新
		Receiving rec = receivingService.findObjectById(recSingle.getReceivingId());//获取当前关联收货单
		Double totalPrice = recSingleIsUpdate(recSingle, rec);
		updateRecTotalPrice(recSingle, totalPrice, rec);
		//2. 更新子项
		int num = receivingSingleDao.updateObject(recSingle);//更新当前收货单总价格
		if(num == -1){
			throw new UpdateObjectException("更新失败,请稍后重试...");
		}
		
	}
	/**
	 * 若为更新,应该先减去原有表中价格,在进行新价格的计算
	 * @param recSingle
	 * @return
	 */
	private Double recSingleIsUpdate(ReceivingSingle recSingle, Receiving rec){
		Double totalPrice = rec.getTotalPrice();//获取收货单总价
		Map<String, Object> map = receivingSingleDao.findObjectById(recSingle.getId());//更新时,先获取表中的当前收货单子项
//		System.out.println(map);
		Integer count = (Integer) map.get("count");//获取数量
		Double purchasePrice = (Double)map.get("purchasePrice");//获取价格
		totalPrice -= (count * purchasePrice);//总价减去原有价格
		return totalPrice;
	}
	/**
	 * 更新收货单总价格
	 * @param receivingSingle
	 */
	private void updateRecTotalPrice(ReceivingSingle recSingle, Double totalPrice, Receiving rec){
		Double purchaseTotalPrice = recSingle.getPurchasePrice() * recSingle.getCount();//当前收货单子项总价格
//		System.out.println("purchaseTotalPrice="+purchaseTotalPrice);
		if(totalPrice != null){//如果当前收货单已有总价格
			totalPrice += purchaseTotalPrice;//加上当前子项总价
		}else{
			totalPrice = purchaseTotalPrice;
		}
//		System.out.println("totalPrice="+totalPrice);
		rec.setTotalPrice(totalPrice);//设置总价格
		receivingService.updateObject(rec);
	}
	
	public void deleteObject(Integer id, Integer receivingId) {
		//进行判定此收货单子项是否为收货状态
		Map<String, Object> recSingle = receivingSingleDao.findObjectById(id);
		if((Integer)recSingle.get("valid")==2){
			throw new DeleteObjectException("此收货单子项为部分收货,无法删除");
		}else if ((Integer)recSingle.get("valid")==3) {
			throw new DeleteObjectException("此收货单子项为全部收货,无法删除");
		}
		//1. 进行价格更新
		delTotalPriceUpdate(id, receivingId);
		//2. 执行删除动作
		if(id == null){
			throw new NullPropertyException("id不能为空...");
		}
		int rowRecSingle = receivingSingleDao.deleteObject(id);
		//返回-1,说明删除失败
		if(rowRecSingle == -1){
			throw new DeleteObjectException("收货单子项删除失败,请稍后再试...");
		}
		//同时删除产品和收货单子项对应关系表，对应信息
		productReceivingSingleService.deleteObject(id);
		
	}
	/**
	 * 进行删除子项后,价格更新
	 * @param id
	 * @param receivingId
	 */
	private void delTotalPriceUpdate(Integer id, Integer receivingId){
		Map<String, Object> map = receivingSingleDao.findObjectById(id);//查询当前删除对象信息
		ReceivingSingle recSingle = new ReceivingSingle();//创建收货单子项对象
		recSingle.setId(id);
		recSingle.setCount((Integer)map.get("count"));
		recSingle.setPurchasePrice((Double)map.get("purchasePrice"));
		Receiving rec = receivingService.findObjectById(receivingId);//获取当前关联收货单
		Double totalPrice = recSingleIsUpdate(recSingle, rec);
//		System.out.println("删除后的价格="+totalPrice);
		rec.setTotalPrice(totalPrice);
		receivingService.updateObject(rec);
	}

	/**
	 * 根据收货单子项id,更新库存信息
	 * @param id
	 */
	private void updateOrNewInventory(Integer id){
		//获取收货单子项的相关信息
		Map<String, Object> recSingle = receivingSingleDao.findObjectById(id);
		//获取产品id
		Integer productId = (Integer)recSingle.get("productId");
		//获取产品总数量
		Integer count = (Integer)recSingle.get("count");
		//根据产品id查询库存信息
		Inventory inven = inventoryService.findObjectByProId(productId);
		if(inven == null){//库存没有,新建插入
			inven = new Inventory();
			inven.setProductId(productId);
			inven.setInventoryCount(count);
			inven.setInventoryAvailable(count);
			inven.setInventoryFreeze(0);
			inven.setInventoryValid(3);
			inven.setInventoryOrderForm(0);
			inventoryService.saveObject(inven);
		}else{//库存有,更新库存数量
			inven.setInventoryCount(inven.getInventoryCount()+count);
			//库存可用,库存总数-库存冻结-已下订单
			inven.setInventoryAvailable(inven.getInventoryCount()-inven.getInventoryFreeze()-inven.getInventoryOrderForm());
			inventoryService.updateObject(inven);
		}
		
	}
	
	public void confirmRecSingle(String recSinIds, Integer recId) {
		//1. 将选中子项进行收货
		if (recSinIds==null || recSinIds.length()==0) {
			throw new NullPropertyException("必须有选中的id值...");
		}
		//解析id字符串
		String[] ids = recSinIds.split(",");
		int rows = receivingSingleDao.confirmRecSingle(ids);
		if(rows == -1){
			throw new UpdateObjectException("收货失败..");
		}
		//2. 将相关收货单进行状态更新
		if(recId == null){
			throw new NullPropertyException("id不能为空...");
		}
		updateRecValid(recId);
		//3. 进行库存的更新(库存中若有此产品进行更新,若无进行新建)
		for(int i=0; i<ids.length; i++){
			Integer id = Integer.parseInt(ids[i]);
			updateOrNewInventory(id);
		}
	}
	/**
	 * 更新收货单状态
	 * @param recId 根据id更新
	 */
	private void updateRecValid(Integer recId){
		//获取对应收货单对象
		Receiving rec = receivingService.findObjectById(recId);
		//获取相关全部收货单子项
		List<Map<String, Object>> receivings = receivingSingleDao.findObjects(null, recId, null);
		int count = 0;//计数
		//遍历子项
		for(Map<String, Object> recSingle : receivings){
			if ((Integer)recSingle.get("valid") == 3) {
				count++;//是收货状态，+1
			}
		}
		if(count == receivings.size()){//相等为全部收货
			rec.setValid(3);
			receivingService.updateObject(rec);
		}else{//否则为部分收货
			rec.setValid(2);
			receivingService.updateObject(rec);
		}
	}

}
