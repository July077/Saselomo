package com.huahua.saselomo.receiving.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huahua.saselomo.common.dao.BaseDao;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.receiving.entity.ReceivingSingle;
/**
 * 收货单子项DAO层接口;
 * 此接口对象为数据访问层对象,关联一个mapper(ReceivingSingleMapper)
 * @author Lin·Y
 *
 */
public interface ReceivingSingleDao extends BaseDao<ReceivingSingle> {
	/**
	 * 确认收货子项
	 * @param ids
	 * @param valid
	 * @return 表示更新的行数,假如返回值为-1表示更新失败
	 */
	int confirmRecSingle(@Param("ids")String[] ids);
	/**
	 * 查询对应收货单子项
	 * @param pageObject
	 * @return
	 */
	List<Map<String, Object>> findObjects(@Param("productName")String productName, @Param("parentId")Integer parentId, @Param("pageObject")PageObject pageObject);
	/**
	 * 获取对应收货单子项记录数
	 * @param parentId
	 * @return
	 */
	int getRowCount(@Param("productName")String productName, @Param("parentId")Integer parentId);
	/**
	 * 根据id删除一条收货单子项
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	/**
	 * 根据id查询一条收货单子项相关信息
	 * @param id
	 * @return
	 */
	Map<String, Object> findObjectById(Integer id);
}
