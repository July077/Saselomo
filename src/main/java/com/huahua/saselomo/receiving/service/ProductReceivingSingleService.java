package com.huahua.saselomo.receiving.service;

import com.huahua.saselomo.receiving.entity.ProductReceivingSingle;

/**
 * 产品和收货单子项的对应关系,业务层接口
 * @author Lin·Y
 *
 */
public interface ProductReceivingSingleService {
	/**
	 * 存储一条产品和收货单子项的对应关系信息
	 * @param proRecSingle
	 */
	void saveObject(ProductReceivingSingle proRecSingle);
	/**
	 * 根据id删除一条产品和收货单子项的对应关系信息
	 * @param id
	 */
	void deleteObject(Integer id);
}
