package com.huahua.saselomo.product.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huahua.saselomo.common.util.CommonUtil;
import com.huahua.saselomo.common.web.JsonResult;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.product.entity.Product;
import com.huahua.saselomo.product.service.ProductService;

/**
 * 产品管理控制对象
 * @author Lin·Y
 *
 */
@Controller
@RequestMapping("/product/")
public class ProductController {
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	/**
	 * 加载产品页面
	 * @return
	 */
	@RequestMapping("listUI")
	public String listUI(){
		return "product/product_list";
	}
	/**
	 * 加载新建/修改模态框模块
	 * @return
	 */
	@RequestMapping("newModifyModalUI")
	public String newModifyModalUI(){
		return "product/product_newModifyModal";
	}
	/**
	 * 加载详情模态框
	 * @return
	 */
	@RequestMapping("detailsModalUI")
	public String detailsModalUI(){
		return "product/product_detailsModal";
	}
	/**
	 * 加载导入模态框
	 * @return
	 */
	@RequestMapping("importUI")
	public String importUI(){
		return "product/product_importModal";
	}
	/**
	 * 下载产品导入模板
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("downloadExcelTemplate")
	public void downloadExcelTemplate(HttpServletResponse response) throws IOException{
		//清空响应
		response.reset();
		//设置文件名
        response.addHeader("Content-Disposition", "attachment;filename=import_product.xlsx");
       //设置文本类型
        response.setContentType("text/html;charset=utf-8");
        OutputStream out = response.getOutputStream();
//        String filePath = "E:\\java\\Tomcat\\apache-tomcat-7.0.67\\wtpwebapps\\Saselomo1.0\\ExcelTemplate\\import_product.xlsx";
      String filePath = "/usr/local/tomcat/saselomo/Saselomo1.0/ExcelTemplate/import_product.xlsx";
        try {
        	CommonUtil.fileCopy(out, filePath);
		} catch (IOException e) {
			e = new IOException("文件下载失败");
			throw e;
		}
	}
	/**
	 * 读取导入的产品信息
	 * @param mFile
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("productUpload")
	@ResponseBody
	public JsonResult importProduct(@RequestParam("mFile")MultipartFile mFile) throws IOException{
		productService.importExcelInfo(mFile);
		return new JsonResult();
	}
	/**
	 * 将客户信息已excel形式导出
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("exportProduct")
	public void exportProduct(HttpServletResponse response) throws IOException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		File file = new File(sdf.format(new Date())+"_product.xls");
		// 清空response
        response.reset();
        // 设置response的Header
        //设置文件名
        response.addHeader("Content-Disposition", "attachment;filename="+file.getName());
        //设置响应格式,excel格式
//      response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setContentType("text/html;charset=utf-8");
        try {
        	//获取输出流
        	OutputStream out = response.getOutputStream();
        	//将客户信息导出
        	BufferedOutputStream bufferedOutPut = new BufferedOutputStream(out);  
        	productService.exportProduct(bufferedOutPut);
        } catch (IOException e) {
        	//需要记录日志
        	e = new IOException("产品信息导出异常");
        	throw e;
        } 
	}
	/**
	 * 根据产品名，进行模糊查询相关产品信息
	 * @return
	 */
	@RequestMapping("doFindObjectByName")
	@ResponseBody
	public JsonResult doFindObjectByName(String name){
		List<Product> products = productService.findObjectByName(name);
		return new JsonResult(products);
	}
	/**
	 * 根据产品名，进行模糊查询库存内的相关产品信息
	 * @return
	 */
	@RequestMapping("doFindInvenInObjectByName")
	@ResponseBody
	public JsonResult doFindInvenInObjectByName(String name){
		List<Product> products = productService.findInvenInObjectByName(name);
		return new JsonResult(products);
	}
	/**
	 * 获取所有产品信息
	 * @return
	 * 一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，
	 * 	加上@responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中。
	 * 	比如异步获取json数据，加上@responsebody后，会直接返回json数据。
	 */
	@RequestMapping("doFindProducts")
	@ResponseBody
	public JsonResult doFindObjects(Product product, PageObject pageObject){
		Map<String, Object> map = productService.findObjects(product, pageObject);
//		System.out.println(map);
		return new JsonResult(map);
	}
	/**
	 * 存储产品信息
	 * @return
	 */
	@RequestMapping("doSaveProduct")
	@ResponseBody
	public JsonResult doSaveObject(Product product){
		productService.saveObject(product);
		return new JsonResult();
	}
	/**
	 * 根据id查询一条产品信息
	 * @return
	 */
	@RequestMapping("doFindProductById")
	@ResponseBody
	public JsonResult doFindproductById(Integer id){
		Product product = productService.findObjectById(id);
		return new JsonResult(product);
	}
	/**
	 * 更新产品信息
	 * @param product
	 * @return
	 */
	@RequestMapping("doUpdateProduct")
	@ResponseBody
	public JsonResult doUpdateObject(Product product){
//		System.out.println(product);
		productService.updateObject(product);
		return new JsonResult();
	}
	/**
	 * 根据id删除一条产品信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteProduct")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		productService.deleteObject(id);
		return new JsonResult();
	}
	
}
