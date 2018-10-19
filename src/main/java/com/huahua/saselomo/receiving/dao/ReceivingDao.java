package com.huahua.saselomo.receiving.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huahua.saselomo.common.dao.BaseDao;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.receiving.entity.Receiving;
/**
 * 收获单DAO层接口
 * 此接口对象为数据访问对象,关联一个mapper(ReceivingMapper)
 * @author Lin·Y
 *
 */
public interface ReceivingDao extends BaseDao<Receiving>{
	/**
	 * 根据收货单编号(部分)进行模糊查询,主要用于生成收货单编号
	 * @param receiptCode
	 * @return
	 */
	List<Receiving> findObjectsByReceiptCode(@Param("receiptCode")String receiptCode);
	/**
	 * 查询收货
	 * 单信息
	 * @param receiving
	 * @param pageObject
	 * @return
	 */
	List<Receiving> findObjects(@Param("receiving")Receiving receiving, @Param("pageObject")PageObject pageObject);
	/**
	 * 获取表中的总记录数
	 * @param receiving
	 * @return
	 */
	int getRowCount(@Param("receiving")Receiving receiving);
	/**
	 * 根据id查询一条收获单信息
	 * @param id
	 * @return
	 */
	Receiving findObjectById(Integer id);
	/**
	 * 根绝id删除一条收货单信息
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	
}
