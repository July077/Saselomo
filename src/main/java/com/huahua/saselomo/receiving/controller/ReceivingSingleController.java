package com.huahua.saselomo.receiving.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huahua.saselomo.common.web.JsonResult;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.receiving.entity.ReceivingSingle;
import com.huahua.saselomo.receiving.service.ReceivingSingleService;

@Controller
@RequestMapping("/receivingSingle/")
public class ReceivingSingleController {
	@Autowired
	@Qualifier("receivingSingleServiceImpl")
	private ReceivingSingleService receivingSingleService;
	/**
	 * 加载新建 | 修改模态框
	 * @return
	 */
	@RequestMapping("newModifyModalUI")
	public String newModifyModalUI(){
		return "receiving/single/receivingSingle_newModifyModal";
	}
	/**
	 * 确认收货模态框
	 * @return
	 */
	@RequestMapping("confirmRecSingleUI")
	public String confirmRecSingleUI(){
		return "receiving/single/recSingle_confirmRecSingle";
	}
	/**
	 * 查询收货单子项,相关信息
	 * @param parentId
	 * @param pageObject
	 * @return
	 */
	@RequestMapping("doFindReceivingSingles")
	@ResponseBody
	public JsonResult doFindObjects(String productName, Integer parentId, PageObject pageObject){
//		System.out.println("doFindObjects-ReceivingSingle");
		Map<String, Object> map = receivingSingleService.findObject(productName, parentId, pageObject);
		return new JsonResult(map);
	}
	/**
	 * 新建收获单子项,及其收获单子项与产品的对应关系
	 * @param receivingSingle
	 * @param productId
	 * @return
	 */
	@RequestMapping("doSaveReceivingSingle")
	@ResponseBody
	public JsonResult doSaveObject(ReceivingSingle receivingSingle, Integer productId){
		receivingSingleService.saveObject(receivingSingle, productId);
		return new JsonResult();
	}
	
	/**
	 * 根据id查询一条收货单子项相关信息
	 * @param receivingSingle
	 * @param productId
	 * @return
	 */
	@RequestMapping("doFindRecSingleById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		Map<String, Object> receivingSingles = receivingSingleService.findObjectById(id);
		return new JsonResult(receivingSingles);
	}
	/**
	 * 修改收货单子项相关信息
	 * @param receivingSingle
	 * @param productId
	 * @return
	 */
	@RequestMapping("doUpdateRecSingle")
	@ResponseBody
	public JsonResult doUpdateObject(ReceivingSingle receivingSingle){
		receivingSingleService.updateObject(receivingSingle);
		return new JsonResult();
	}
	
	/**
	 * 根据id删除一条收货单子项信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteRecSingle")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id, Integer receivingId){
		receivingSingleService.deleteObject(id, receivingId);
		return new JsonResult();
	}
	/**
	 * 确认收货收货单子项
	 * @param checkedIds
	 * @return
	 */
	@RequestMapping("doConfirmRecSingle")
	@ResponseBody
	public JsonResult doConfirmRecSingle(String recSinIds, Integer recId){
		receivingSingleService.confirmRecSingle(recSinIds, recId);
		return new JsonResult();
	}
	
}
