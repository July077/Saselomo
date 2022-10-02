package com.huahua.saselomo.login.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huahua.saselomo.common.web.JsonResult;

@Controller
public class LoginController {
	/**
	 * 转到登录页面
	 * @return
	 */
	@RequestMapping("toLogin")
	public String toLoginUI(){
		return "common/login";
	}
	/**
	 * 校验账户密码
	 * @param username
	 * @param userpwd
	 * @return
	 */
	@RequestMapping("confirmUser")
	@ResponseBody
	public JsonResult confirmUser(String username, String userpwd){
		//1. 获取当前主体对象
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {//未认证
			//将用户名密码封装成UsernamePasswordToken对象
			UsernamePasswordToken token = new UsernamePasswordToken(username, userpwd);
			try {
				//登录认证 - 调用userRealm
				currentUser.login(token);
			} catch (IncorrectCredentialsException e) {
				throw new IncorrectCredentialsException("密码错误");
			} catch (AuthenticationException e) {
				throw new AuthenticationException(e.getMessage());
			}
		}
		return new JsonResult();
	}
}
