package com.huahua.saselomo.sales.dao;

import org.apache.ibatis.annotations.Param;

import com.huahua.saselomo.common.dao.BaseDao;
import com.huahua.saselomo.sales.entity.ProductSalesSingle;
/**
 * 产品与售货单子项对应关系
 * @author Lin·Y
 *
 */
public interface ProSalesSingleDao extends BaseDao<ProductSalesSingle> {
	/**
	 * 根据售货单id删除一条信息
	 * @param id
	 * @return
	 */
	int deleteObject(@Param("salSingleId")Integer id);
}
