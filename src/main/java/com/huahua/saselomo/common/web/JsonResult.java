package com.huahua.saselomo.common.web;
/**
 * 通过此对象封装控制层返回的JSON结果,
 * 便于对控制层返回的数据进行统一格式化
 * @author Lin·Y
 *
 */
public class JsonResult {
	public static final int SUCCESS = 1;
	public static final int ERROR = 0; 
	/** 服务端的响应状态*/
	private int state;
	/** 信息(给客户的提示)*/
	private String message;
	/** 服务端返回的数据*/
	private Object data;
	
	public JsonResult(){
		this.state = SUCCESS;
		this.message = "ok";
	}
	
	public JsonResult(Object data){
		this();
		this.data = data;
	}
	
	public JsonResult(Throwable e){
		this.state = ERROR;
		this.message = e.getMessage();
	}

	public static int getSuccess() {
		return SUCCESS;
	}

	public static int getError() {
		return ERROR;
	}

	public int getState() {
		return state;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
	
	
	
}
