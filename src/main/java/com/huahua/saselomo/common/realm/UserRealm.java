package com.huahua.saselomo.common.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.huahua.saselomo.login.service.LoginService;
import com.huahua.saselomo.system.entity.User;
import com.huahua.saselomo.system.service.UserService;

public class UserRealm extends AuthorizingRealm {
	@Autowired
	@Qualifier("loginServiceImpl")
	private LoginService loginService;
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	@Override
	public String getName() {
		return "UserRealm";
	}
	//授权
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		//获取当前用户
		User user = (User) principal.getPrimaryPrincipal();
		//查询用户所拥有的权限
		List<String> permissions = userService.findPermissions(user.getId()) ;
		//认证比对
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissions);
		return info;
	}
	//认证操作
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1. 从token中获取登录用户名
		String username = (String)token.getPrincipal();
		//2. 判定用户名是否存在,若存在,返回user对象
		User user = loginService.isExist(username);
		/*
		 * 	info对象表示realm登录对比信息: 参数1: 用户信息(真实登录中是登录对象user对象); 参数2: 密码; 参数3: 盐; 
		 * 参数4: 当前realm的名字;
		 */
		AuthenticationInfo info = new SimpleAuthenticationInfo(
							user,
							user.getPassword(),
							ByteSource.Util.bytes(user.getSalt()), 
							getName());
		return info;
	}

}
