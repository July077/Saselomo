package com.huahua.saselomo.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huahua.saselomo.common.exception.DeleteObjectException;
import com.huahua.saselomo.common.exception.FindObjectException;
import com.huahua.saselomo.common.exception.NullPropertyException;
import com.huahua.saselomo.common.exception.SaveObjectException;
import com.huahua.saselomo.common.exception.UpdateObjectException;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.system.dao.RoleDao;
import com.huahua.saselomo.system.entity.Role;
import com.huahua.saselomo.system.service.RoleService;
/**
 * 角色service层实现类
 * @author Lin·Y
 *
 */
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao;
	
	public Map<String, Object> findObjects(String role, PageObject pageObject) {
		//1. 获取表中要显示的记录
		List<Role> list = roleDao.findObjects(role, pageObject);
		//2. 计算分页相关信息
		int rowCount = roleDao.getRowCounts(role);//获取总记录数
		pageObject.setRowCount(rowCount);//计算总页数
		//3. 创建封装数据的map对象
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roles", list);//封装角色数据
		map.put("pageObject", pageObject);//封装分页信息
		return map;
	}

	public void saveObject(Role role) {
		//1. 判定存储数据是否为空
		if (role == null) {
			throw new NullPropertyException("Save, 角色信息不能为空..");
		}
		//2. 存储
		int row = roleDao.saveObject(role);
		if (row != 1) {
			throw new SaveObjectException("存储角色信息失败!");
		}
		
	}
	
	public void deleteObject(Integer id) {
		if (id == null) {
			throw new NullPropertyException("删除的角色id不能为空");
		}
		int row = roleDao.deleteObject(id);
		if (row != 1) {
			throw new DeleteObjectException("角色信息删除失败!");
		}
	}

	public Role findObjectById(Integer id) {
		if (id == null) {
			throw new NullPropertyException("查询的角色id不能为空");
		}
		Role role = roleDao.findObjectById(id);
		if (role == null) {
			throw new FindObjectException("查询角色信息失败!");
		}
		return role;
	}

	public void updateObject(Role role) {
		if(role==null){
			throw new NullPropertyException("更新角色信息，角色对象不能为空！");
		}
		//更新
		int row = roleDao.updateObject(role);
		if (row != 1) {
			throw new UpdateObjectException("角色更新失败!");
		}
	}

}
