package com.huahua.saselomo.inventory.controller;
/**
 * 库存管理控制对象
 * @author Lin·Y
 *
 */

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huahua.saselomo.common.web.JsonResult;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.inventory.entity.Inventory;
import com.huahua.saselomo.inventory.service.InventoryService;

@Controller
@RequestMapping("/inventory/")
public class InventoryController {
	@Autowired
	@Qualifier("inventoryServiceImpl")
	private InventoryService inventoryService;
	
	@RequestMapping("listUI")
	public String listUI(){
		return "inventory/inventory_list";
	}
	/**
	 * 导出库存信息
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("exportInventory")
	public void exportInventory(HttpServletResponse response) throws IOException{
		//创建文件
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		File file = new File(sdf.format(new Date()) + "_inventory.xls");
		//清空response
		response.reset();
		//设置response的Header
		//设置文件名
		response.addHeader("Content-Disposition", "attachment;filename="+file.getName());
		//设置响应格式,excel格式
//		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setContentType("text/html;charset=utf-8");
        try {
        	//获取输出流
        	OutputStream out = response.getOutputStream();
        	//将客户信息导出
        	BufferedOutputStream bufferedOutPut = new BufferedOutputStream(out);  
        	inventoryService.exportInventory(bufferedOutPut);
        } catch (IOException e) {
        	//需要记录日志
        	e = new IOException("客户信息导出异常");
        	throw e;
        } 
	}
	/**
	 * 查询全部库存信息
	 * @param proName 可根据产品名,模糊查询
	 * @param pageObject
	 * @return
	 */
	@RequestMapping("doFindInventorys")
	@ResponseBody
	public JsonResult doFindObjects(String proName, PageObject pageObject){
		Map<String, Object> map = inventoryService.findObjects(proName, pageObject);
		return new JsonResult(map);
	}
	/**
	 * 根据产品id查询一条库存信息
	 * @param productId
	 * @return
	 */
	@RequestMapping("doFindObjectByProId")
	@ResponseBody
	public JsonResult doFindObjectByProId(Integer productId){
		Inventory inven = inventoryService.findObjectByProId(productId);
		return new JsonResult(inven);
	}
	
}
