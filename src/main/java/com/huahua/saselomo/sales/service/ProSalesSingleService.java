package com.huahua.saselomo.sales.service;

import com.huahua.saselomo.sales.entity.ProductSalesSingle;

/**
 * 产品与售货单子项对应关系业务层接口
 * @author Lin·Y
 *
 */
public interface ProSalesSingleService {
	/**
	 * 存储一条产品和售货单子项的对应关系信息
	 * @param proSalSingle
	 */
	void saveObject(ProductSalesSingle proSalSingle);
	/**
	 * 根据id删除一条产品和售货单子项的对应关系信息
	 * @param id
	 */
	void deleteObject(Integer id);
}
