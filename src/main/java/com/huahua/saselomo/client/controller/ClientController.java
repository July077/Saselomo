 package com.huahua.saselomo.client.controller;
/**
 * 客户管理控制器对象
 * @author Lin·Y
 *
 */

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huahua.saselomo.client.entity.Client;
import com.huahua.saselomo.client.service.ClientService;
import com.huahua.saselomo.common.util.CommonUtil;
import com.huahua.saselomo.common.web.JsonResult;
import com.huahua.saselomo.common.web.PageObject;

@Controller
@RequestMapping("/client/")
public class ClientController {
	@Autowired
	@Qualifier("clientServiceImpl")
	private ClientService clientService;
	
	/**
	 * 加载客户页面 
	 * @return
	 */
	@RequestMapping("listUI")
	public String listUI(){
		return "client/client_list";
	}
	/**
	 * 加载新建/修改模态框模块
	 * @return
	 */
	@RequestMapping("newModifyModalUI")
	public String newModifyModalUI(){
		return "client/client_newModifyModal";
	}
	/**
	 * 加载详情页
	 * @return
	 */
	@RequestMapping("detailsUI")
	public String detailsModalUI(){
		return "client/client_details";
	}
	/**
	 * 加载导入模态框
	 * @return
	 */
	@RequestMapping("importUI")
	public String importModalUI(){
		return "client/client_importModal";
	}
	/**
	 * 下载客户导入模板
	 * @param response 
	 * @throws IOException
	 */
	@RequestMapping("downloadExcelTemplate")
	public void downloadExcelTemplate(HttpServletResponse response) throws IOException{
		//清空响应
		response.reset();
		//设置文件名
        response.addHeader("Content-Disposition", "attachment;filename=import_client.xlsx");
       //设置文本类型
        response.setContentType("text/html;charset=utf-8");
        OutputStream out = response.getOutputStream();
//        String filePath = "E:\\java\\Tomcat\\apache-tomcat-7.0.67\\wtpwebapps\\Saselomo1.0\\ExcelTemplate\\import_client.xlsx";
      String filePath = "/usr/local/tomcat/saselomo/Saselomo1.0/ExcelTemplate/import_client.xlsx";
        try {
        	CommonUtil.fileCopy(out, filePath);
		} catch (IOException e) {
			e = new IOException("文件下载失败");
			throw e;
		}
	}
	/**
	 * 读取客户信息的excel
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@RequestMapping(value="clientUpload",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult importClient(@RequestParam(value="mFile")MultipartFile mFile) throws IOException, ParseException  {
		clientService.ImportExcelInfo(mFile);
		return new JsonResult();
	}
	/**
	 * 将客户信息已excel形式导出
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("exportClient")
	public void exportClient(HttpServletResponse response) throws IOException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		File file = new File(sdf.format(new Date())+"_client.xls");
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
        	clientService.exportClient(bufferedOutPut);
        } catch (IOException e) {
        	//需要记录日志
        	e = new IOException("客户信息导出异常");
        	throw e;
        } 
	}
	
	/**
	 * 根据客户名称获取客户信息,主要用于客户下拉表
	 * @param name
	 * @return
	 */
	@RequestMapping("doFindObjectByName")
	@ResponseBody
	public JsonResult doFindObjectByName(String name){
		List<Client> clients = clientService.findObjectByName(name);
		return new JsonResult(clients);
	}
	/**
	 * 获取所有客户信息
	 * @return
	 * 一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，
	 * 	加上@responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中。
	 * 	比如异步获取json数据，加上@responsebody后，会直接返回json数据。
	 */
	@RequestMapping("doFindClients")
	@ResponseBody
	public JsonResult doFindObjects(Client client, PageObject pageObject){
		Map<String, Object> map = clientService.findObjects(client, pageObject);
//		System.out.println(map);
		return new JsonResult(map);
	}
	/**
	 * 存储客户信息
	 * @return
	 */
	@RequestMapping("doSaveClient")
	@ResponseBody
	public JsonResult doSaveObject(Client client){
		clientService.saveObject(client);
		return new JsonResult();
	}
	/**
	 * 根据id查询一条客户信息
	 * @return
	 */
	@RequestMapping("doFindClientById")
	@ResponseBody
	public JsonResult doFindClientById(Integer id){
		Client client = clientService.findObjectById(id);
		return new JsonResult(client);
	}
	/**
	 * 更新客户信息
	 * @param client
	 * @return
	 */
	@RequestMapping("doUpdateClient")
	@ResponseBody
	public JsonResult doUpdateObject(Client client){
		System.out.println(client);
		clientService.updateObject(client);
		return new JsonResult();
	}
	/**
	 * 根据id删除一条客户信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteClient")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		clientService.deleteObject(id);
		return new JsonResult();
	}
	
	
}
