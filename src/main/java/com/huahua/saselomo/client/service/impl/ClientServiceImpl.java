package com.huahua.saselomo.client.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.huahua.saselomo.client.dao.ClientDao;
import com.huahua.saselomo.client.entity.Client;
import com.huahua.saselomo.client.service.ClientService;
import com.huahua.saselomo.common.excel.ExportExcel;
import com.huahua.saselomo.common.excel.ImportExcel;
import com.huahua.saselomo.common.exception.DeleteObjectException;
import com.huahua.saselomo.common.exception.FindObjectException;
import com.huahua.saselomo.common.exception.NullPropertyException;
import com.huahua.saselomo.common.exception.SaveObjectException;
import com.huahua.saselomo.common.exception.UpdateObjectException;
import com.huahua.saselomo.common.util.CommonUtil;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.sales.dao.SalesDao;
/**
 * 客户管理service对象
 * 项目中所有与业务相关的事情一般都要放在service中,例如
 * 1)判定参数是否符合业务要求
 * 2)判定dao返回的数据是否是我们需要的结果
 * 3)执行一些日志的记录
 * 4)执行一些事务的处理
   5)....
 */
@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	@Qualifier("clientDao")
	private ClientDao clientDao;
	@Autowired
	@Qualifier("salesDao")
	private SalesDao salesDao;
	
	public Map<String, Object> findObjects(Client client, PageObject pageObject) {
//		System.out.println("findObjects");
		//获取页面表格要显示的数据
		List<Client> clients = clientDao.findObjects(client, pageObject);
		//获取表中记录并计算页数
		int rowCount = clientDao.getRowCount(client);
		pageObject.setRowCount(rowCount);
		//构建Map对象,封装从dao层获取的数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clients", clients);//记录信息
		map.put("pageObject", pageObject);//分页数据
		return map;
	}

	public Client findObjectById(Integer id) {
		//若id为空,抛出异常空属性
		if(id == null){
			throw new NullPropertyException("Id不能为空...");
		}
		Client client = clientDao.findObjectById(id);
		//客户信息为空,说明客户不存在,抛出异常
		if(client == null){
			throw new FindObjectException("客户信息不存在...");
		}
		return client;
	}

	public void saveObject(Client client) {
		int rows = clientDao.saveObject(client);
		//返回-1,说明存储失败
		if(rows == -1){
			throw new SaveObjectException("客户信息存储失败,请稍后再试...");
		}
	}

	public void deleteObject(Integer id) {
		if(id == null){
			throw new NullPropertyException("id不能为空...");
		}
		//判断此客户有么有进行购买产品,若购买抛出异常,删除失败
		List<Map<String, Object>> list = salesDao.findObjectByClientId(id);
		if(!list.isEmpty()) throw new DeleteObjectException("此客户已购买过产品,不能删除...");
		//进行删除动作
		int rows = clientDao.deleteObject(id);
		//返回-1,说明删除失败
		if(rows == -1){
			throw new DeleteObjectException("客户信息删除失败,请稍后再试...");
		}
	}

	public void updateObject(Client client) {
		int rows = clientDao.updateObject(client);
		if(rows != 1){//正常情况下是1,若不是,则是更新失败
			throw new UpdateObjectException("客户信息更新失败,请稍后再试...");
		}
	}

	public List<Client> findObjectByName(String name) {
		List<Client> clients = clientDao.findObjectByName(name);//获取信息
		return clients;
	}

	public void exportClient(OutputStream out) {
		//1. 查询客户数据
		Client cli = new Client();
		List<Client> data = clientDao.findObjects(cli, null);
		//2. 导出客户数据信息
		ExportExcel<Client> expExcel = new ExportExcel<Client>();
		String[] headers = {"姓名", "性别", "年龄", "作息时间", "电话", "地址", "皮肤状态", "备注"};
		String sheetName = "客户";
		String pattern = "HH:mm:ss";
		expExcel.exportExcelCar(headers, sheetName, data, out, pattern);
	}

	public void ImportExcelInfo(MultipartFile mFile) throws ParseException, IOException {
		InputStream in = null;
		//1. 获取输入流
		try {
			in = mFile.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		//2. 获取文件名
		String filename = mFile.getOriginalFilename();
		//3. 客户标题列
		String[] title = {"姓名", "性别", "年龄","作息时间","电话","地址", "皮肤状态", "备注"};
		//4. 获取导入文件内容,进行封装
		ImportExcel ie = new ImportExcel();
		List<List<Object>> listOb = ie.importExcelCar(in, filename, title);
		List<Client> listCli = new ArrayList<Client>();//导入的数据集合
		for (int i = 0; i < listOb.size(); i++) {
			Client client = new Client();//声明客户对象
			if("".equals(listOb.get(i).get(0).toString())){
				System.out.println((String)listOb.get(i).get(0));
				throw new NullPropertyException("姓名不能为空;位于第"+(i+2)+"行,第1列");
			}
			client.setName((String)listOb.get(i).get(0));//姓名
			client.setSex((String)listOb.get(i).get(1));//性别
			if("".equals(listOb.get(i).get(2).toString())){//年龄
				client.setAge(null);
			}else{
				String exceptionDesc = "年龄填写错误,请输入3位数字;位于第"+(i+2)+"行,第3列";
				client.setAge(CommonUtil.parseInt(listOb.get(i).get(2).toString(), exceptionDesc));
			}
			try {
				if("".equals((String)listOb.get(i).get(3))){//作息时间
					client.setTimetable(null);
				}else{
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					client.setTimetable(sdf.parse((String)listOb.get(i).get(3)));
				}
			} catch (ParseException e) {
				e = new ParseException("作息时间填写错误,请输入HH:mm:ss格式;位于第"+(i+2)+"行,第4列", i);
				throw e;
			}
			client.setPhone((String)listOb.get(i).get(4));//电话
			client.setAddress((String)listOb.get(i).get(5));//地址
			client.setSkinCondition((String)listOb.get(i).get(6));//皮肤情况
			client.setNote((String)listOb.get(i).get(7));//备注
			client.setValid(1);//状态
			client.setCreatedUser("花花");//创建人
			listCli.add(client);//封装一个客户信息
		}
		//5. 将客户数据存储
		clientDao.importObject(listCli);
	}
	
}



