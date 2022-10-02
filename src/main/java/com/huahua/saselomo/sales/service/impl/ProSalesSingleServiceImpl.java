package com.huahua.saselomo.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huahua.saselomo.common.exception.DeleteObjectException;
import com.huahua.saselomo.common.exception.SaveObjectException;
import com.huahua.saselomo.sales.dao.ProSalesSingleDao;
import com.huahua.saselomo.sales.entity.ProductSalesSingle;
import com.huahua.saselomo.sales.service.ProSalesSingleService;
/**
 * 产品与收货单子项对应关系,业务层实现类
 * @author Lin·Y
 *
 */
@Service
public class ProSalesSingleServiceImpl implements ProSalesSingleService {
	@Autowired
	@Qualifier("proSalesSingleDao")
	private ProSalesSingleDao proSalesSingleDao;
	public void saveObject(ProductSalesSingle proSalSingle) {
		//存储产品和售货单子项的对应关系信息
		int rowsProSalSingle = proSalesSingleDao.saveObject(proSalSingle);
		if(rowsProSalSingle == -1){
			throw new SaveObjectException("存储失败,请稍后再试...");
		}
	}

	public void deleteObject(Integer id) {
		int rowsProSalSingle = proSalesSingleDao.deleteObject(id);
		//返回-1,说明删除失败
		if(rowsProSalSingle == -1){
			throw new DeleteObjectException("售货单子项删除失败,请稍后再试...");
		}
	}

}
