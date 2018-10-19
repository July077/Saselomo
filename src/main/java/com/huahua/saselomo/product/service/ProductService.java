package com.huahua.saselomo.product.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.product.entity.Product;

/**
 * 产品业务接口
 * @author Lin·Y
 *
 */
 public interface ProductService {
	 /**
	  * 进行产品信息的导入
	  * @param mFile 封装导入文件相关的对象
	  */
	 void importExcelInfo(MultipartFile mFile) throws IOException;
	 /**
	  * 进行产品信息的导出
	  * @param out 与设备关联的输出流
	  */
	 void exportProduct(OutputStream out);
	 /**
	  * 根据产品名，进行模糊查询库存内的相关产品信息
	  * @param name
	  * @return
	  */
	List<Product> findInvenInObjectByName(String name);
	 /**
	  * 根据产品名，进行模糊查询产品信息
	  * @param name
	  * @return
	  */
	 List<Product> findObjectByName(String name);
	/**
	 * 查询产品信息
	 * @return
	 */
	 Map<String, Object> findObjects(Product product, PageObject pageObject);
	/**
	 * 根据id查询一条产品信息
	 * @return
	 */
	 Product findObjectById(Integer id);
	/**
	 * 存储一条产品信息
	 * @param Product
	 */
	 void saveObject(Product product);
	/**
	 * 根据id删除一条产品信息
	 * @param id
	 */
	 void deleteObject(Integer id);
	/**
	 * 更新产品信息
	 * @param Product
	 */
	 void updateObject(Product product);
}
