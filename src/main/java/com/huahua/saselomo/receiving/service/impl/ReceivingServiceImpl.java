package com.huahua.saselomo.receiving.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.huahua.saselomo.receiving.dao.ReceivingDao;
import com.huahua.saselomo.receiving.dao.ReceivingSingleDao;
import com.huahua.saselomo.receiving.entity.Receiving;
import com.huahua.saselomo.receiving.service.ReceivingService;
/**
 * 收货单业务层实现类,实现相关判定业务:
 * 	如:收货单状态,收货单总价,收货单与收货单子项的关联
 * @author Lin·Y
 *
 */
@Service
public class ReceivingServiceImpl implements ReceivingService {
	@Autowired
	@Qualifier("receivingDao")
	private ReceivingDao receivingDao;
	@Autowired
	@Qualifier("receivingSingleDao")
	private ReceivingSingleDao receivingSingleDao;
	
	public Map<String, Object> findObjects(Receiving receiving, PageObject pageObject) {
		//获取页面表格要显示的数据
		List<Receiving> receivings = receivingDao.findObjects(receiving, pageObject);
		//获取表中记录并计算页数
		int rowCount = receivingDao.getRowCount(receiving);
		pageObject.setRowCount(rowCount);
		//构建Map对象,封装从dao层获取的数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("receivings", receivings);//记录信息
		map.put("pageObject", pageObject);//分页数据
		return map;
	}

	public Receiving findObjectById(Integer id) {
//		System.out.println("findObjectById");
		//若id为空,抛出异常空属性
		if(id == null){
			throw new NullPropertyException("Id不能为空...");
		}
		Receiving receiving = receivingDao.findObjectById(id);
		//收货单信息为空,说明收货单不存在,抛出异常
		if(receiving == null){
			throw new FindObjectException("收货单信息不存在...");
		}
		return receiving;
	}

	public void saveObject(Receiving receiving) {
//		System.out.println("saveObject");
//		收获单编号为空,默认自动生成
		if(receiving.getReceiptCode()==null || receiving.getReceiptCode()==""){
			String receiptCode = getReceiptCode();
//			System.out.println("receiptCode="+receiptCode);
			receiving.setReceiptCode(receiptCode);
		}
//		进货时间为空，默认当前时间
		if(receiving.getPurchaseTime() == null){
//			System.out.println("purchaseTime="+receiving.getPurchaseTime());
			receiving.setPurchaseTime(new Date());
		}
		int rows = receivingDao.saveObject(receiving);
		//返回-1,说明存储失败
		if(rows == -1){
			throw new SaveObjectException("收货单信息存储失败,请稍后再试...");
		}

	}
	/**
	 * 当收货单编号为空时，系统自动生成收货单编号，并返回给调用者;
	 * 格式为R(receiving,第一个字母)+日期(20180806)+第几单(001,第一单)
	 * @return
	 */
	private String getReceiptCode() {
//		System.out.println("getReceiptCode");
		String receiptCode = null;//生成的收货单编号
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String codeDate = sdf.format(new Date());//收货单日期部分
		String codePrelusion = "R"+ codeDate;//收货单编号前两个部分
		List<Receiving> list = receivingDao.findObjectsByReceiptCode(codePrelusion);//获取查询记录
//		System.out.println(list);
		if(list.isEmpty()){
			receiptCode = codePrelusion + "001";
			return receiptCode;
		}
		Receiving receiving = list.get(0);//最晚创建时间的收货单对象
		String receiptCodeTable = receiving.getReceiptCode();//最晚创建时间的收货单对象的编号
		Integer endCode = 
				Integer.valueOf(receiving.getReceiptCode().
						substring(receiptCodeTable.length()-3));//最晚创建时间收货单编号的后三位，转换为Integer类型
		if(endCode < 9){//小于9
			receiptCode = codePrelusion + "00" + (endCode+1);
		}else if(endCode < 99){//小于99,大于9 
			receiptCode = codePrelusion + "0" + (endCode+1);
		}else{//大于100
			receiptCode = codePrelusion + (endCode+1);
		}
		return receiptCode;
	}

	public void deleteObject(Integer id) {
		//1. 判断此收货单是否为部分收货或者全部收货
		Receiving rec = receivingDao.findObjectById(id);
		if(rec.getValid() == 2){
			throw new DeleteObjectException("此收货单已部分收货,无法删除");
		}else if(rec.getValid()==3){
			throw new DeleteObjectException("此收货单已全部收货,无法删除");
		}
		//2. 判断此收货单是否有子项
		List<Map<String , Object>> list = receivingSingleDao.findObjects(null, id, null);//若关联有子项
//		System.out.println(list);
		if(!list.isEmpty()){//抛出异常,无法删除
			throw new DeleteObjectException("请先删除此收货单子项...");
		}
		if(id == null){
			throw new NullPropertyException("id不能为空...");
		}
		//3. 进行删除
		int rows = receivingDao.deleteObject(id);
		//返回-1,说明删除失败
		if(rows == -1){
			throw new DeleteObjectException("收货单信息删除失败,请稍后再试...");
		}

	}

	public void updateObject(Receiving receiving) {
		int rows = receivingDao.updateObject(receiving);
		if(rows != 1){//正常情况下是1,若不是,则是更新失败
			throw new UpdateObjectException("收货单信息更新失败,请稍后再试...");
		}
	}

}
