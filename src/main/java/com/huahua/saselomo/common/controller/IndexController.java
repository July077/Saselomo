package com.huahua.saselomo.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 加载主页
 * @author Lin·Y
 *
 */
@Controller
public class IndexController {
	@RequestMapping("index")
	public String indexUI(){
		return "common/index";
	}
}
