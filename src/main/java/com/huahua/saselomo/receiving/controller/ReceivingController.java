package com.huahua.saselomo.receiving.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huahua.saselomo.common.web.JsonResult;
import com.huahua.saselomo.common.web.PageObject;
import com.huahua.saselomo.receiving.entity.Receiving;
import com.huahua.saselomo.receiving.service.ReceivingService;

/**
 * 收货单管理控制对象
 * @author Lin·Y
 *
 */
@Controller
@RequestMapping("/receiving/")
public class ReceivingController {
	@Autowired
	@Qualifier("receivingServiceImpl")
	private ReceivingService receivingService;
	/**
	 * 加载收货单页面
	 * @return
	 */
	@RequestMapping("listUI")
	public String listUI(){
		return "receiving/receiving_list";
	}
	/**
	 * 加载新建/修改模态框模块
	 * @return
	 */
	@RequestMapping("newModifyModalUI")
	public String newModifyModalUI(){
		return "receiving/receiving_newModifyModal";
	}
	/**
	 * 加载详情页
	 * @return
	 */
	@RequestMapping("detailsUI")
	public String detailsModalUI(){
		return "receiving/receiving_details";
	}
	/**
	 * 获取所有收货单信息
	 * @return
	 * 一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，
	 * 	加上@responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中。
	 * 	比如异步获取json数据，加上@responsebody后，会直接返回json数据。
	 */
	@RequestMapping("doFindReceivings")
	@ResponseBody
	public JsonResult doFindObjects(Receiving receiving, PageObject pageObject){
//		System.out.println("doFindObjects-receiving");
		Map<String, Object> map = receivingService.findObjects(receiving, pageObject);
//		System.out.println(map);
		return new JsonResult(map);
	}
	/**
	 * 存储收货单信息
	 * @return
	 */
	@RequestMapping("doSaveReceiving")
	@ResponseBody
	public JsonResult doSaveObject(Receiving receiving){
		receivingService.saveObject(receiving);
		return new JsonResult();
	}
	/**
	 * 根据id查询一条收货单信息
	 * @return
	 */
	@RequestMapping("doFindReceivingById")
	@ResponseBody
	public JsonResult doFindReceivingById(Integer id){
		Receiving receiving = receivingService.findObjectById(id);
		return new JsonResult(receiving);
	}
	/**
	 * 更新收货单信息
	 * @param receiving
	 * @return
	 */
	@RequestMapping("doUpdateReceiving")
	@ResponseBody
	public JsonResult doUpdateObject(Receiving receiving){
		receivingService.updateObject(receiving);
//		System.out.println("receiving="+receiving);
		return new JsonResult();
	}
	/**
	 * 根据id删除一条收货单信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteReceiving")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		receivingService.deleteObject(id);
		return new JsonResult();
	}
}


