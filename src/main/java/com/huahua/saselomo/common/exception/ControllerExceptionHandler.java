package com.huahua.saselomo.common.exception;

import java.text.ParseException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 此注解标注用于标识此类为全局的异常处理类
 * @author Lin·Y
 *
 */

import com.huahua.saselomo.common.web.JsonResult;
@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(ParseException.class)
	@ResponseBody
	public JsonResult handleParseException(ParseException e){
		e.printStackTrace();
		return new  JsonResult(e);
	}
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult handleException(Exception e){
		e = new Exception("系统未知错误，请稍后再试...");
		System.out.println("exception");
		e.printStackTrace();
		return new JsonResult(e);
	}	
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonResult handleException(RuntimeException e){
		System.out.println("runtime exception");
		e.printStackTrace();
		return new JsonResult(e);
	}
}
