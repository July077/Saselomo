package com.huahua.saselomo.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huahua.saselomo.common.dao.BaseDao;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.system.entity.User;

/**
 * 用户管理dao层接口.
 * 此接口实现为数据访问对象,关联mapper: UserMapper.xml
 * @author Lin·Y
 *
 */
public interface UserDao extends BaseDao<User>{
	/**
	 * 根据用户id查询当前用户所拥有的权限信息
	 * @param userId
	 * @return
	 */
	List<String> findPermissions(Integer userId);
	/**
	 * 查询用户信息
	 * @param user
	 * @param pageObject
	 * @return
	 */
	List<User> findObjects(@Param("user")User user, @Param("pageObject")PageObject pageobject);
	/**
	 * 获取表中的记录数
	 * @param client
	 * @return
	 */
	 int getRowCounts(@Param("user")User user);
	/**
	 * 根据id删除一条用户信息
	 * @param id
	 * @return
	 */
	 int deleteObject(Integer id);
	 /**
	  * 根据id查询一条用户信息
	  * @param id
	  * @return
	  */
	 User findObjectById(Integer id);
	 /**
	  * 更新用户信息
	  */
	 int updateObject(User user);
	/**
	 * 根据用户名,判定用户是否存在
	 * @param username
	 * @return
	 */
	int isExist(String username);
	/**
	 * 根据用户名查询一个用户信息
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
}
