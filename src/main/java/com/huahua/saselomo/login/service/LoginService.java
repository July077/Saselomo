package com.huahua.saselomo.login.service;

import com.huahua.saselomo.system.entity.User;

/**
 * 登录service接口
 * @author Lin·Y
 *
 */
public interface LoginService {
	/**
	 * 根据用户名判断用户是否存在
	 * @param username 用户名
	 * @return
	 */
	User isExist(String username);
}
