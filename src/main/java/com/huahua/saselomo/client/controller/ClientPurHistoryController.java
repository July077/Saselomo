package com.huahua.saselomo.client.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huahua.saselomo.client.entity.ClientPurHistory;
import com.huahua.saselomo.client.service.ClientPurHistoryService;
import com.huahua.saselomo.common.web.JsonResult;
import com.huahua.saselomo.common.web.PageObject;

/**
 * 客户购买记录控制层接口
 * @author Lin·Y
 *
 */
@Controller
@RequestMapping("/clientPurHistory/")
public class ClientPurHistoryController {
	@Autowired
	@Qualifier("clientPurHistoryServiceImpl")
	private ClientPurHistoryService cliPurHistoryService;
	
	/**
	 * 查询客户购买记录
	 * @param clientId 客户id
	 * @param purchaseDate 购买时间
	 * @param pageObject 分页
	 * @return
	 */
	@RequestMapping("doFindClientPurcHistorys")
	@ResponseBody
	public JsonResult doFindObjects(ClientPurHistory clientPurHistory,PageObject pageObject){
		Map<String, Object> map = cliPurHistoryService.findObjects(clientPurHistory, pageObject);
		return new JsonResult(map);
	}
	
}
