package com.huahua.saselomo.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 权限管理控制器对象
 * @author Lin·Y
 *
 */
@Controller
@RequestMapping("/permission/")
public class PermissionController {
	
	@RequestMapping("listUI")
	@RequiresPermissions("system:all")
	public String listUI(){
		return "system/permission/permission_list";
	}
	
}
