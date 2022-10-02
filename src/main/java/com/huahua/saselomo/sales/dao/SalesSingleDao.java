package com.huahua.saselomo.sales.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huahua.saselomo.common.dao.BaseDao;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.sales.entity.SalesSingle;

/**
 * 售货单子项dao层接口,
 * 其对象为数据访问对象,关联mapper(SalesSingleMapper)
 * @author Lin·Y
 *
 */
public interface SalesSingleDao extends BaseDao<SalesSingle>{
	/**
	 * 查询子项,用于客户购买记录的添加|更新
	 * @param ids 根据确认收货的子项id
	 * @return
	 */
	List<Map<String, Object>> findConfirmObjects(@Param("ids")String[] ids);
	/**
	 * 确认售货子项
	 * @param ids
	 * @param valid
	 * @return 表示更新的行数,假如返回值为-1表示更新失败
	 */
	int confirmSalSingle(@Param("ids")String[] ids);
	/**
	 * 查询对应售货单子项
	 * @param pageObject
	 * @return
	 */
	List<Map<String, Object>> findObjects(@Param("productName")String productName, @Param("parentId")Integer parentId, @Param("pageObject")PageObject pageObject);
	/**
	 * 获取对应售货单子项记录数
	 * @param parentId
	 * @return
	 */
	int getRowCount(@Param("productName")String productName, @Param("parentId")Integer parentId);
	/**
	 * 根据id删除一条售货单子项
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	/**
	 * 根据id查询一条售货单子项相关信息
	 * @param id
	 * @return
	 */
	Map<String, Object> findObjectById(Integer id);	
}
