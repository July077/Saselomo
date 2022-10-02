package com.huahua.saselomo.system.service;
/**
 * 用户service接口
 * @author Lin·Y
 *
 *
 */

import java.util.List;
import java.util.Map;

import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.system.entity.User;

public interface UserService {
	/**
	 * 根据用户ID查询当前用户的权限信息
	 * @param userId
	 * @return
	 */
	List<String> findPermissions(Integer userId);
	/**
	 * 查询所有用户信息
	 * @param user
	 * @param pageObject
	 * @return
	 */
	Map<String, Object> findObjects(User user, PageObject pageObject);
	/**
	 * 存储用户信息
	 * @param user
	 */
	void saveObject(User user);
	/**
	 * 删除用户信息
	 * @param id
	 */
	void deleteObject(Integer id);
	/**
	 * 根据id查询一条用户信息
	 * @param id
	 * @return
	 */
	User findObjectById(Integer id);
	/**
	 * 更新一条用户信息
	 * @param user
	 */
	void updateObject(User user);
}
