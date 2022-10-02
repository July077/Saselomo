package com.huahua.saselomo.client.service;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.huahua.saselomo.client.entity.Client;
import com.huahua.saselomo.common.web.PageObject;

/**
 * 客户业务层接口
 * @author Lin·Y
 *
 */
 public interface ClientService {
	 /**
	  * 进行客户信息的导入
	  * @param mFile 与文件相关的表示
	  */
	 void ImportExcelInfo(MultipartFile mFile) throws IOException, ParseException;
	 /**
	  * 进行客户信息的导出
	  * @param out 与设备关联的输出流
	  */
	 void exportClient(OutputStream out);
	/**
	 * 查询客户信息
	 * @return
	 */
	 Map<String, Object> findObjects(Client client, PageObject pageObject);
	/**
	 * 根据id查询一条客户信息
	 * @return
	 */
	 Client findObjectById(Integer id);
	/**
	 * 存储一条客户信息
	 * @param client
	 */
	 void saveObject(Client client);
	/**
	 * 根据id删除一条客户信息
	 * @param id
	 */
	 void deleteObject(Integer id);
	/**
	 * 更新客户信息
	 * @param client
	 */
	 void updateObject(Client client);
	 /**
	  * 根据客户名模糊查询客户信息。主要用于:售货单客户的下拉选
	  * @param name
	  * @return
	  */
	 List<Client> findObjectByName(String name);
}













