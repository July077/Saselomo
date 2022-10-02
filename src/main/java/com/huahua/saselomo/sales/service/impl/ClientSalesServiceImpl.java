package com.huahua.saselomo.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huahua.saselomo.common.exception.DeleteObjectException;
import com.huahua.saselomo.common.exception.FindObjectException;
import com.huahua.saselomo.common.exception.NullPropertyException;
import com.huahua.saselomo.common.exception.SaveObjectException;
import com.huahua.saselomo.common.exception.UpdateObjectException;
import com.huahua.saselomo.sales.dao.ClientSalesDao;
import com.huahua.saselomo.sales.entity.ClientSales;
import com.huahua.saselomo.sales.service.ClientSalesService;
/**
 * 客户与收货单对应关系业务层实现类
 * @author Lin·Y
 *
 */
@Service
public class ClientSalesServiceImpl implements ClientSalesService {
	@Autowired
	@Qualifier("clientSalesDao")
	private ClientSalesDao clientSalesDao;
	public void saveObject(ClientSales clientSales) {
		int rows = clientSalesDao.saveObject(clientSales);
		if(rows == -1){//返回-1说明存储失败
			throw new SaveObjectException("售货单信息存储失败...");
		}
	}

	public void deleteObject(Integer salesId) {
		int rows = clientSalesDao.deleteObject(salesId);
		if(rows == -1){//返回-1说明删除失败
			throw new DeleteObjectException("售货单信息删除失败...");
		}
	}

	public ClientSales findObjectBySalesId(Integer salesId) {
		if(salesId == null){
			throw new NullPropertyException("售货单id不能为空...");
		}
		ClientSales cs = clientSalesDao.findObjectBySalesId(salesId);
		if(cs == null){//查询失败
			throw new FindObjectException("售货单信息查询失败...");
		}
		return cs;
	}

	public void updateObject(ClientSales clientSales) {
		int rows = clientSalesDao.updateObject(clientSales);
		if(rows == -1){//返回-1说明删除失败
			throw new UpdateObjectException("售货单信息更新失败...");
		}
	}

}
