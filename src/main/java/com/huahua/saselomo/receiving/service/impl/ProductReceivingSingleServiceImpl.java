package com.huahua.saselomo.receiving.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huahua.saselomo.common.exception.DeleteObjectException;
import com.huahua.saselomo.common.exception.SaveObjectException;
import com.huahua.saselomo.receiving.dao.ProductReceivingSingleDao;
import com.huahua.saselomo.receiving.entity.ProductReceivingSingle;
import com.huahua.saselomo.receiving.service.ProductReceivingSingleService;
@Service
public class ProductReceivingSingleServiceImpl implements ProductReceivingSingleService {
	@Autowired
	@Qualifier("productReceivingSingleDao")
	private ProductReceivingSingleDao productReceivingSingleDao;
	
	public void saveObject(ProductReceivingSingle proRecSingle) {
		//存储产品和收货单子项的对应关系信息
		int numProRecSingle = productReceivingSingleDao.saveObject(proRecSingle);
		if(numProRecSingle == -1){
			throw new SaveObjectException("存储失败,请稍后再试...");
		}
	}

	public void deleteObject(Integer id) {
		int rowsProRecSingle = productReceivingSingleDao.deleteObject(id);
		//返回-1,说明删除失败
		if(rowsProRecSingle == -1){
			throw new DeleteObjectException("收货单子项删除失败,请稍后再试...");
		}
	}

}
