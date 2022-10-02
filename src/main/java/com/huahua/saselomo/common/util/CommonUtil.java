package com.huahua.saselomo.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 工具类
 * @author Lin·Y
 *
 */
public class CommonUtil {
	/**
	 * 描述: 此方法用于文件的复制
	 * @param out 与输出设备相关的输出流
	 * @param filePath 文件的路径
	 * @throws IOException 
	 */
	public static void fileCopy(OutputStream out, String filePath) throws IOException{
		//获取文件
		File file = new File(filePath);
		//读取文件
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		//缓冲输出流
		BufferedOutputStream bos = new BufferedOutputStream(out);
		//声明读取的数据
		int d = -1;
		while ((d = bis.read()) != -1) {//读取
			bos.write(d);//输出
			bos.flush();//强制全部输出
		}
		//关闭流
		bis.close();
		bos.close();
	}
	/**
	 * 描述:将调用者传入的数值转换为Double类型,转换失败则抛出调用者希望抛出的异常描述
	 * @param number 要转换的数值
	 * @param exceptionDesc 异常描述
	 * @return
	 */
	public static Double parseDouble(String number, String exceptionDesc){
		Double num = null;
		try {
			num = Double.parseDouble(number);
		} catch (NumberFormatException e) {
			e = new NumberFormatException(exceptionDesc);
			throw e;
		}
		return num;
	}
	
	/**
	 * 描述:将调用者传入的数值转换为Integer类型,转换失败则抛出调用者希望抛出的异常描述
	 * @param number 要转换的数值
	 * @param exceptionDesc 异常描述
	 * @return
	 */
	public static Integer parseInt(String number, String exceptionDesc){
		Integer num = null;
		try {
			num = Integer.parseInt(number);
		} catch (NumberFormatException e) {
			e = new NumberFormatException(exceptionDesc);
			throw e;
		}
		return num;
	}
	
}
