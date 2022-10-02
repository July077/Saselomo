package com.huahua.saselomo.receiving.dao;

import org.apache.ibatis.annotations.Param;

import com.huahua.saselomo.common.dao.BaseDao;
import com.huahua.saselomo.receiving.entity.ProductReceivingSingle;

/**
 * 收货单子项与产品对应关系
 * @author Lin·Y
 *
 */
public interface ProductReceivingSingleDao extends BaseDao<ProductReceivingSingle>{
	/**
	 * 根据收货单id删除一条信息
	 * @param id
	 * @return
	 */
	int deleteObject(@Param("recSingleId")Integer id);
}
