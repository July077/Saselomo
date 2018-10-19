package com.huahua.saselomo.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huahua.saselomo.common.dao.BaseDao;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.product.entity.Product;
/**
 * 产品管理DAO层接口,
 * 	数据访问对象,关联一个mapper
 * @author Lin·Y
 *
 */
public interface ProductDao extends BaseDao<Product> {
	/**
	 * 批量导入产品信息
	 * @param products
	 * @return
	 */
	int importObject(@Param("products")List<Product> products);
	/**
	  * 根据产品名，进行模糊查询库存内的相关产品信息
	  * @param name
	  * @return
	  */
	List<Product> findInvenInObjectByName(@Param("name")String name);
	/**
	 * 根据产品名,进行模糊查询产品信息
	 * @param name
	 * @return
	 */
	List<Product> findObjectByName(@Param("name")String name);
	/**
	 * 查询产品信息
	 * @return
	 */
	List<Product> findObjects(@Param("product")Product product, @Param("pageObject")PageObject pageObject);
	/**
	 * 获取表中记录数
	 * @param product
	 * @return
	 */
	int getRowCount(@Param("product")Product product );
	/**
	 * 根据id查询一条产品信息
	 * @param id
	 * @return
	 */
	Product findObjectById(Integer id);
	/**
	 * 根据id删除一条产品信息
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	

}
