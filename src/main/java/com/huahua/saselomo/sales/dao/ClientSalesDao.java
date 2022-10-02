package com.huahua.saselomo.sales.dao;

import org.apache.ibatis.annotations.Param;

import com.huahua.saselomo.common.dao.BaseDao;
import com.huahua.saselomo.sales.entity.ClientSales;

/**
 * 客户和售货单对应关系dao层接口,
 * 此接口对象为数据访问对象,管理一个mapper(ClientSalesMapper)
 * @author Lin·Y
 *
 */
public interface ClientSalesDao extends BaseDao<ClientSales>{
	
	/**
	 * 根据收货单id删除一条信息
	 * @param id
	 * @return
	 */
	int deleteObject(@Param("salesId")Integer id);
	/**
	 * 根据售货单id查询对应客户id信息,以此对象返回
	 * @param salesId
	 * @return
	 */
	ClientSales findObjectBySalesId(@Param("salesId")Integer salesId);
	
}
