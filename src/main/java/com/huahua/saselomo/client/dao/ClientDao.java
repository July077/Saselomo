package com.huahua.saselomo.client.dao;
/**
 * 客户管理DAO层接口
 * 此接口对象为一个数据访问对象(关联一个mapper)
 * @author Lin·Y
 *
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huahua.saselomo.client.entity.Client;
import com.huahua.saselomo.common.dao.BaseDao;
import com.huahua.saselomo.common.web.PageObject;
public interface ClientDao extends BaseDao<Client>{
	/**
	 * 批量插入客户信息,用于excel表格的导入
	 * @param clients 导入的客户信息
	 * @return
	 */
	int importObject(@Param("clients")List<Client> clients);
	/**
	 * 查询客户信息
	 * @return
	 */
	 List<Client> findObjects(@Param("client")Client client, @Param("pageObject")PageObject pageObject);
	/**
	 * 获取表中的记录数
	 * @param client
	 * @return
	 */
	 int getRowCount(@Param("client")Client client);
	/**
	 * 根据id查询一条客户信息
	 * @return
	 */
	 Client findObjectById(Integer id);
	/**
	 * 根据id删除一条客户信息
	 * @param id
	 * @return
	 */
	 int deleteObject(Integer id);
	 /**
	  * 根据客户名模糊查询客户信息。主要用于:售货单客户的下拉选
	  * @param name
	  * @return
	  */
	 List<Client> findObjectByName(@Param("name")String name);
}
