package com.huahua.saselomo.system.dao;
/**
 * 角色dao层接口;
 * 此接口对象为数据访问对象,关联一个mapper(RoleMapper)
 * @author Lin·Y
 *
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huahua.saselomo.common.dao.BaseDao;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.system.entity.Role;

public interface RoleDao extends BaseDao<Role>{
	/**
	 * 查询所有的角色信息
	 * @param role 根据角色查询 
	 * @param PageObject 分页
	 * @return
	 */
	List<Role> findObjects(@Param("role")String role, @Param("pageObject")PageObject PageObject);
	/**
	 * 获取表中记录数
	 * @param role
	 * @return
	 */
	int getRowCounts(@Param("role")String role);
	/**
	 * 根据id删除一条角色信息
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	/**
	 * 根据id查询一条角色信息
	 * @param id
	 * @return
	 */
	Role findObjectById(Integer id);
	
}
