package com.huahua.saselomo.sales.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huahua.saselomo.common.web.JsonResult;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.sales.entity.Sales;
import com.huahua.saselomo.sales.service.SalesService;

/**
 * 售货单管理控制对象
 * @author Lin·Y
 *
 */
@Controller
@RequestMapping("/sales/")
public class SalesController {
	@Autowired
	@Qualifier("salesServiceImpl")
	private SalesService salesService;
	
	/**
	 * 加载售货单页面
	 * @return
	 */
	@RequestMapping("listUI")
	public String listUI(){
		return "sales/sales_list";
	}
	/**
	 * 加载新建/修改模态框模块
	 * @return
	 */
	@RequestMapping("newModifyModalUI")
	public String newModifyModalUI(){
		return "sales/sales_newModifyModal";
	}
	/**
	 * 加载详情页
	 * @return
	 */
	@RequestMapping("detailsUI")
	public String detailsModalUI(){
		return "sales/sales_details";
	}
	/**
	 * 获取所有售货单信息
	 * @return
	 */
	@RequestMapping("doFindSales")
	@ResponseBody
	public JsonResult doFindObjects(String clientName, Sales sales, PageObject pageObject){
//		System.out.println("doFindObjects-sales");
		Map<String, Object> map = salesService.findObjects(clientName, sales, pageObject);
//		System.out.println(map);
		return new JsonResult(map);
	}
	/**
	 * 存储售货单信息
	 * @return
	 */
	@RequestMapping("doSaveSales")
	@ResponseBody
	public JsonResult doSaveObject(Sales sales, Integer clientId){
		salesService.saveObject(sales, clientId);
		return new JsonResult();
	}
	/**
	 * 根据id查询一条售货单与对应客户信息
	 * @return
	 */
	@RequestMapping("doFindSalesOrClientById")
	@ResponseBody
	public JsonResult doFindSalesOrClientById(Integer id){
		Map<String, Object> map= salesService.findObjectById(id);
		return new JsonResult(map);
	}
	/**
	 * 根据id查询一条售货单信息
	 * @return
	 */
	@RequestMapping("doFindSalesById")
	@ResponseBody
	public JsonResult doFindSalesById(Integer id){
		Sales sales= salesService.findSalesById(id);
		return new JsonResult(sales);
	}
	/**
	 * 更新售货单信息
	 * @param receiving
	 * @return
	 */
	@RequestMapping("doUpdateSales")
	@ResponseBody
	public JsonResult doUpdateObject(Sales sales, Integer clientId){
		salesService.updateObject(sales, clientId);
//		System.out.println("sales="+sales);
		return new JsonResult();
	}
	/**
	 * 根据id删除一条售货单信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteSales")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		System.out.println("doDeleteSales");
		salesService.deleteObject(id);
		return new JsonResult();
	}
}
