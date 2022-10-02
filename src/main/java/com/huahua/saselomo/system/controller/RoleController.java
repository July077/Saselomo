package com.huahua.saselomo.system.controller;
/**
 * 角色管理控制器对象
 * @author Lin·Y
 *
 */

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huahua.saselomo.common.web.JsonResult;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.system.entity.Role;
import com.huahua.saselomo.system.service.RoleService;

@Controller
@RequestMapping("/role/")
public class RoleController {
	@Autowired
	@Qualifier("roleServiceImpl")
	private RoleService roleService;
	@RequestMapping("listUI")
	@RequiresPermissions("system:all")
	public String listUI(){
		return "system/role/role_list";
	}
	/**
	 * 以分页形式查询全部角色信息
	 * @param role
	 * @param pageObject
	 * @return
	 */
	@RequestMapping("doFindRoles")
	@ResponseBody
	public JsonResult doFindObject(String role, PageObject pageObject){
		Map<String, Object> map = roleService.findObjects(role, pageObject);
		return new JsonResult(map);
	}
	/**
	 * 存储一条角色信息
	 * @param role
	 * @return
	 */
	@RequestMapping("doSaveRole")
	@ResponseBody
	public JsonResult doSaveRole(Role role){
		roleService.saveObject(role);
		return new JsonResult();
	}
	/**
	 * 更新一条角色信息
	 * @param role
	 * @return
	 */
	@RequestMapping("doUpdateRole")
	@ResponseBody
	public JsonResult doUpdateRole(Role role){
		roleService.updateObject(role);
		return new JsonResult();
	}
	/**
	 * 根据id查询一条角色信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doFindRoleById")
	@ResponseBody
	public JsonResult doFindRoleById(Integer id){
		Role role = roleService.findObjectById(id);
		return new JsonResult(role);
	}
	/**
	 * 删除一条角色信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteRole")
	@ResponseBody
	public JsonResult doDeleteRole(Integer id){
		roleService.deleteObject(id);
		return new JsonResult();
	}
	
}
