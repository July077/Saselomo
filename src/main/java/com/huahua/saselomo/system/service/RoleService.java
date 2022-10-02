package com.huahua.saselomo.system.service;
/**
 * 角色service接口
 * @author Lin·Y
 *
 *
 */

import java.util.Map;

import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.system.entity.Role;

public interface RoleService {
	/**
	 * 查询所有角色信息
	 * @param role
	 * @param pageObject
	 * @return
	 */
	Map<String, Object> findObjects(String role, PageObject pageObject);
	/**
	 * 存储角色信息
	 * @param role
	 */
	void saveObject(Role role);
	/**
	 * 删除角色信息
	 * @param id
	 */
	void deleteObject(Integer id);
	/**
	 * 根据id查询一条角色信息
	 * @param id
	 * @return
	 */
	Role findObjectById(Integer id);
	/**
	 * 更新一条角色信息
	 * @param role
	 */
	void updateObject(Role role);
}
