package com.huahua.saselomo.sales.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huahua.saselomo.common.dao.BaseDao;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.sales.entity.Sales;
/**
 * 售货单Dao层接口,
 * 此接口对象为数据访问对象,关联一个mapper(SalesMapper)
 * @author Lin·Y
 *
 */
public interface SalesDao extends BaseDao<Sales>{
	/**
	 * 描述: 根据客户id查询对应客户的售货单信息
	 * @param clientId
	 * @return
	 */
	List<Map<String, Object>> findObjectByClientId(@Param("id")Integer clientId);
	/**
	 * 查询售货单对应客户的id,和售货单的销售时间
	 * @param id 根据售货单id
	 * @return
	 */
	Map<String, Object> findCliAndSalById(@Param("id")Integer id);
	/**
	 * 根据售货单编号(部分)进行模糊查询,主要用于生成售货单编号
	 * @param saleCode
	 * @return
	 */
	List<Sales> findObjectsBySaleCode(@Param("saleCode")String saleCode);
	/**
	 * 查询售货单相关信息
	 * @param name
	 * @param saleDate
	 * @param pageObject
	 * @return
	 */
	List<Map<String, Object>> findObjects(@Param("clientName")String clientName, @Param("sale")Sales sales, @Param("pageObject")PageObject pageObject);

	/**
	 * 获取表中的总记录数
	 * @param name
	 * @param saleDate
	 * @return
	 */
	int getRowCount(@Param("clientName")String clientName, @Param("sale")Sales sale);
	/**
	 * 根据id查询一条收获单信息
	 * @param id
	 * @return
	 */
	Sales findObjectById(Integer id);
	/**
	 * 根据id删除一条售货单信息
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
}
