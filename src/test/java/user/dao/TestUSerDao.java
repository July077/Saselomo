package user.dao;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Assert;
import org.junit.Test;

import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.system.entity.User;

import common.TestBase;

public class TestUSerDao extends TestBase{
	/** 测试查询当前用户权限*/
	@Test
	public void testFindPermission(){
		List<String> permissions = userDao.findPermissions(2);
		Assert.assertNotEquals(null, permissions);
		System.out.println(permissions);
	}
	/** 测试查询*/
	@Test
	public void testFindObjects(){
		User user = new User();
		user.setName("1");
		PageObject pageObject = new  PageObject();
		pageObject.setRowCount(userDao.getRowCounts(user));
		List<User> list = userDao.findObjects(user, pageObject);
		for(User u : list){
			System.out.println(u);
		}
	}
	/** 测试插入*/
	@Test
	public void testSaveObject(){
		User user = new User();
		user.setUsername("admin");
		user.setSalt(algorithmMD5(null, "admin"));
		System.out.println(user.getSalt());
		user.setPassword(algorithmMD5("123", user.getSalt()));
		user.setName("admin");
		user.setMobile("15738847570");
		user.setValid(1);
		userDao.saveObject(user);
	}
	/** 测试更新*/
	@Test
	public void testUpdateObject(){
		User user = userDao.findObjectById(1);
		System.out.println(user);
		user.setName("admin");
		user.setEmail("admin@qq.com");
		int row = userDao.updateObject(user);
		Assert.assertEquals(1, row);
	}
	private String algorithmMD5(String password, String salt){
		if(password==null){//密码为空,返回加密后的盐
			Md5Hash hash = new Md5Hash(salt);
			return hash.toString();
		}
		//否则返回加密后的密码
		Md5Hash hash2 = new Md5Hash(password, salt, 3);
		return hash2.toString();
	}
	
	@Test
	public void testUUID(){
		System.out.println(UUID.randomUUID());
	}
}
