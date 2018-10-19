package com.huahua.saselomo.client.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huahua.saselomo.client.dao.ClientPurHistoryDao;
import com.huahua.saselomo.client.entity.ClientPurHistory;
import com.huahua.saselomo.client.service.ClientPurHistoryService;
import com.huahua.saselomo.common.exception.NullPropertyException;
import com.huahua.saselomo.common.exception.SaveObjectException;
import com.huahua.saselomo.common.web.PageObject;
/**
 * 客户购买记录业务层实现类
 * @author Lin·Y
 *
 */
@Service
public class ClientPurHistoryServiceImpl implements ClientPurHistoryService {
	@Autowired
	@Qualifier("clientPurHistoryDao")
	private ClientPurHistoryDao cphDao;
	
	public Map<String, Object> findObjects(ClientPurHistory clientPurHistory, PageObject pageObject) {
		if(clientPurHistory.getClientId() == null){
			throw new NullPropertyException("客户id不能为空");
		}
		//获取页面表格要显示的数据
		List<ClientPurHistory> cliPurHistory = cphDao.findObjects(clientPurHistory.getClientId(), clientPurHistory.getPurchaseDate(), pageObject);
		//获取表中记录并计算页数
		int rowCount = cphDao.getRowCount(clientPurHistory.getClientId(), clientPurHistory.getPurchaseDate());
		pageObject.setRowCount(rowCount);
		//构建Map对象,封装从dao层获取的数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clientPurHistory", cliPurHistory);//记录信息
		map.put("pageObject", pageObject);//分页数据
		return map;
	}

	public void saveObject(ClientPurHistory clientPurHistory) {
		int rows = cphDao.saveObject(clientPurHistory);
		if(rows == -1){
			throw new SaveObjectException("客户购买记录存储失败");
		}
	}

	public void updateObject(ClientPurHistory clientPurHistory) {
		int rows = cphDao.updateObject(clientPurHistory);
		if(rows == -1){
			throw new SaveObjectException("客户购买记录更新失败");
		}
	}

	public List<ClientPurHistory> findObjects(ClientPurHistory cpHis) {
		if(cpHis.getClientId() == null){
			throw new NullPropertyException("客户id不能为空");
		}
		List<ClientPurHistory> list = cphDao.findObjects(cpHis.getClientId(), cpHis.getPurchaseDate(), null);
		return list;
	}

}
