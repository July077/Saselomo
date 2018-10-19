package com.huahua.saselomo.client.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huahua.saselomo.client.entity.ClientPurHistory;
import com.huahua.saselomo.common.dao.BaseDao;
import com.huahua.saselomo.common.web.PageObject;

/**
 * 客户购买记录Dao层接口,
 * 其对象为数据访问对象,对应mapper(ClientPurHistoryMapper)
 * @author Lin·Y
 *
 */
public interface ClientPurHistoryDao extends BaseDao<ClientPurHistory>{
	/**
	 * 查询此客户全部购买记录
	 * @param clientId 查询对应客户的
	 * @param purchaseDate 根据购买时间查询
	 * @param pageObject
	 * @return
	 */
	List<ClientPurHistory> findObjects(@Param("clientId")Integer clientId, @Param("purDate")Date purchaseDate, @Param("pageObject")PageObject pageObject);
	/**
	 * 获取总记录数
	 * @param clientId 查询对应客户的
	 * @param purchaseDate 根据购买时间查询
	 * @return
	 */
	int getRowCount(@Param("clientId")Integer clientId, @Param("purDate")Date purchaseDate);
	
}
