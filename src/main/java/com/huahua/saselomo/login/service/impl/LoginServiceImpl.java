package com.huahua.saselomo.login.service.impl;

import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huahua.saselomo.common.exception.NullPropertyException;
import com.huahua.saselomo.login.service.LoginService;
import com.huahua.saselomo.system.dao.UserDao;
import com.huahua.saselomo.system.entity.User;
/**
 * 登录service实现类
 * @author Lin·Y
 *
 */
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
	public User isExist(String username) {
		if (username == null) {//用户名为空
			throw new NullPropertyException("用户不能为空...");
		}
		int i = userDao.isExist(username);
		if (i != 1) {//用户不存在
			throw new UnknownAccountException("该用户不存在...");
		}
		//根据用户名查询用户
		User user = userDao.findByUsername(username);
		return user;
	}

}
