package role.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.system.entity.Role;

import common.TestBase;

public class TestRoleDao extends TestBase{
	/** 测试查询*/
	@Test
	public void testFindObjects(){
		String role = "系统";
		PageObject pageObject = new PageObject(); 
		pageObject.setRowCount(roleDao.getRowCounts(role));
		List<Role> roles = roleDao.findObjects(role, pageObject);
		System.out.println(roles);
	}
	/** 测试存储*/
	@Test
	public void testSaveObject(){
		Role role = new Role();
		role.setRole("信息管理员");
		role.setDescription("基础信息管理");
		role.setParentId(0);
		int row = roleDao.saveObject(role);
		Assert.assertNotEquals(-1, row);
	}
	/** 测试修改*/
	@Test
	public void testUpdateObject(){
		Role role = roleDao.findObjectById(4);
		System.out.println(role);
		role.setRole("产品管理员");
		role.setDescription("产品的收/售管理");
		int row = roleDao.updateObject(role);
		Assert.assertNotEquals(-1, row);
	}
	
	/** 测试删除*/
	@Test
	public void testDeleteObject(){
		int row = roleDao.deleteObject(4);
		Assert.assertNotEquals(-1, row);
	}
	
}
