package com.huahua.saselomo.system.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 用户管理控制器对象
 * @author Lin·Y
 *
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.huahua.saselomo.common.web.JsonResult;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.system.entity.User;
import com.huahua.saselomo.system.service.UserService;
@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	//加载用户UI
	@RequestMapping("listUI")
	@RequiresPermissions("user:all")
	public String listUI(){
		return "system/user/user_list";
	}
	//加载用户新建UI
	@RequestMapping("newModifyModalUI")
	public String newModifyModalUI(){
		return "system/user/user_newModifyModal";
	}
	/**
	 * 以分页形式查询全部用户信息
	 * @param user
	 * @param pageObject
	 * @return
	 */
	@RequestMapping("doFindUsers")
	@ResponseBody
	public JsonResult doFindObject(User user, PageObject pageObject){
		Map<String, Object> map = userService.findObjects(user, pageObject);
		return new JsonResult(map);
	}
	/**
	 * 存储一条用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping("doSaveUser")
	@ResponseBody
	public JsonResult doSaveUser(User user){
		userService.saveObject(user);
		return new JsonResult();
	}
	/**
	 * 更新一条用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping("doUpdateUser")
	@ResponseBody
	public JsonResult doUpdateUser(User user){
		userService.updateObject(user);
		return new JsonResult();
	}
	/**
	 * 根据id查询一条用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doFindUserById")
	@ResponseBody
	public JsonResult doFindUserById(Integer id){
		User user = userService.findObjectById(id);
		return new JsonResult(user);
	}
	/**
	 * 删除一条用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteUser")
	@ResponseBody
	public JsonResult doDeleteUser(Integer id){
		userService.deleteObject(id);
		return new JsonResult();
	}
}
