package com.huahua.saselomo.sales.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huahua.saselomo.common.web.JsonResult;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.sales.entity.SalesSingle;
import com.huahua.saselomo.sales.service.SalesSingleService;

@Controller
@RequestMapping("/salesSingle/")
public class SalesSingleController {
	@Autowired
	@Qualifier("salesSingleServiceImpl")
	private SalesSingleService salesSingleService;
	/**
	 * 加载新建 | 修改模态框
	 * @return
	 */
	@RequestMapping("newModifyModalUI")
	public String newModifyModalUI(){
		return "sales/single/salesSingle_newModifyModal";
	}
	/**
	 * 确认售货子项单项模态框
	 * @return
	 */
	@RequestMapping("confirmSalSingleUI")
	public String confirmRecSingleUI(){
		return "sales/single/salSingle_confirmSalSingle";
	}
	/**
	 * 查询售货单子项,相关信息
	 * @param parentId
	 * @param pageObject
	 * @return
	 */
	@RequestMapping("doFindSalesSingles")
	@ResponseBody
	public JsonResult doFindObjects(String productName, Integer parentId, PageObject pageObject){
//		System.out.println("doFindObjects-ReceivingSingle");
		Map<String, Object> map = salesSingleService.findObject(productName, parentId, pageObject);
		return new JsonResult(map);
	}
	/**
	 * 新建售货单子项,及其售货单子项与产品的对应关系
	 * @param salesSingle
	 * @param productId
	 * @return
	 */
	@RequestMapping("doSaveSalesSingle")
	@ResponseBody
	public JsonResult doSaveObject(SalesSingle salesSingle, Integer productId){
		salesSingleService.saveObject(salesSingle, productId);
		return new JsonResult();
	}
	
	/**
	 * 根据id查询一条售货单子项相关信息
	 * @param salesSingle
	 * @param productId
	 * @return
	 */
	@RequestMapping("doFindSalSingleById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		Map<String, Object> salesSingles = salesSingleService.findObjectById(id);
		return new JsonResult(salesSingles);
	}
	/**
	 * 修改售货单子项相关信息
	 * @param salesSingle
	 * @param productId
	 * @return
	 */
	@RequestMapping("doUpdateSalSingle")
	@ResponseBody
	public JsonResult doUpdateObject(SalesSingle salesSingle){
		salesSingleService.updateObject(salesSingle);
		return new JsonResult();
	}
	
	/**
	 * 根据id删除一条售货单子项信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteSalSingle")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id, Integer salesId){
		salesSingleService.deleteObject(id, salesId);
		return new JsonResult();
	}
	/**
	 * 确认售货选中子项
	 * @param checkedIds
	 * @return
	 */
	@RequestMapping("doConfirmSalSingle")
	@ResponseBody
	public JsonResult doConfirmSalSingle(String salSinIds, Integer salId){
		salesSingleService.confirmSalSingle(salSinIds, salId);
		return new JsonResult();
	}
}
