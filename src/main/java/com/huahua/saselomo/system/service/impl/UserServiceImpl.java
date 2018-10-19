package com.huahua.saselomo.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huahua.saselomo.common.exception.DeleteObjectException;
import com.huahua.saselomo.common.exception.FindObjectException;
import com.huahua.saselomo.common.exception.NullPropertyException;
import com.huahua.saselomo.common.exception.SaveObjectException;
import com.huahua.saselomo.common.exception.UpdateObjectException;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.system.dao.UserDao;
import com.huahua.saselomo.system.entity.User;
import com.huahua.saselomo.system.service.UserService;
/**
 * 用户service层实现类
 * @author Lin·Y
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
	public Map<String, Object> findObjects(User user, PageObject pageObject) {
		//1. 获取表中要显示的记录
		List<User> list = userDao.findObjects(user, pageObject);
		//2. 计算分页相关信息
		int rowCount = userDao.getRowCounts(user);//获取总记录数
		pageObject.setRowCount(rowCount);//计算总页数
		//3. 创建封装数据的map对象
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("users", list);//封装用户数据
		map.put("pageObject", pageObject);//封装分页信息
		return map;
	}

	public void saveObject(User user) {
		//1. 判定存储数据是否为空
		if (user == null) {
			throw new NullPropertyException("Save, 用户信息不能为空..");
		}
		//2. 生成盐, 然加密密码
		String saltStr = UUID.randomUUID().toString();//使用uuid生成盐; UUID,通用唯一识别码
		ByteSource salt = ByteSource.Util.bytes(saltStr); 
		String password = new SimpleHash("MD5", user.getPassword(), salt, 3).toString();//加密后的密码
		System.out.println(password);
		user.setSalt(saltStr);
		user.setPassword(password);
		//3. 存储
		int row = userDao.saveObject(user);
		if (row != 1) {
			throw new SaveObjectException("存储用户信息失败!");
		}
		
	}
	
	public void deleteObject(Integer id) {
		if (id == null) {
			throw new NullPropertyException("删除的用户id不能为空");
		}
		int row = userDao.deleteObject(id);
		if (row != 1) {
			throw new DeleteObjectException("用户信息删除失败!");
		}
	}

	public User findObjectById(Integer id) {
		if (id == null) {
			throw new NullPropertyException("查询的用户id不能为空");
		}
		User user = userDao.findObjectById(id);
		if (user == null) {
			throw new FindObjectException("查询用户信息失败!");
		}
		return user;
	}

	public void updateObject(User user) {
		if(user==null){
			throw new NullPropertyException("更新用户信息，用户对象不能为空！");
		}
		//生成盐, 然加密密码
		String saltStr = UUID.randomUUID().toString();//使用uuid生成盐; UUID,通用唯一识别码
		ByteSource salt = ByteSource.Util.bytes(saltStr); 
		String password = new SimpleHash("MD5", user.getPassword(), salt, 3).toString();//加密后的密码
		System.out.println(password);
		user.setSalt(saltStr);
		user.setPassword(password);
		//更新
		int row = userDao.updateObject(user);
		if (row != 1) {
			throw new UpdateObjectException("用户更新失败!");
		}
	}

	public List<String> findPermissions(Integer userId) {
		if(userId == null){
			throw new NullPropertyException("权限查询,用户id不可为空...");
		}
		List<String> permissions = userDao.findPermissions(userId);
		return permissions;
	}

}
