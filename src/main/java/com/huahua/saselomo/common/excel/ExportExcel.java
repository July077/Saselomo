package com.huahua.saselomo.common.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @category excel导出公共类
 * @param t 导出的数据
 * @author Lin·Y
 *
 */
public class ExportExcel <T>{
	/**
	 * 导出信息入口
	 * @param headers 表格属性列名数组
	 * @param sheetName 表的表单标题名称
	 * @param data 需要显示的数据集合
	 * @param out 与输出设备关联的流对象
	 * @param pattern 时间数据的输出格式
	 */
	public void exportExcelCar(String[] headers, String sheetName, Collection<T> data, OutputStream out, String pattern){
		exportExcel(headers, sheetName, data, out, pattern);
	}
	/**
	 * 导出实现
	 * @param headers 表格属性列名数组
	 * @param sheetName 表的表单标题名称
	 * @param data 需要显示的数据集合
	 * @param out 与输出设备关联的流对象
	 * @param pattern 时间数据的输出格式
	 */
	private void exportExcel(String[] headers, String sheetName, Collection<T> data, OutputStream out, String pattern) {
		//声明一个工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//生成工作表
		HSSFSheet sheet = workbook.createSheet(sheetName);
		//设置表默认列宽为15
		sheet.setDefaultColumnWidth(15);
		//表的标题行
		HSSFRow row = sheet.createRow(0);
		for(int i=0; i<headers.length; i++){
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(headers[i]);
		}
		//遍历数据集合，产生数据行
		if("客户".equals(sheetName)){//客户的数据集合
			iteratorClientData(data, sheet, pattern);
		}else if ("产品".equals(sheetName)) {//产品的数据集合
			iteratorProductData(data, sheet, pattern);
		}else if("库存".equals(sheetName)){//库存的数据集合
			iteratorInventoryData(data, sheet, pattern);
		}
		try {
			workbook.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 产生库存信息的数据行
	 * @param data 库存信息
	 * @param sheet 工作簿
	 * @param pattern 时间格式
	 */
	@SuppressWarnings ({"unchecked","rawtypes"})//忽略类型检查和没有指定类型的警告
	private void iteratorInventoryData(Collection<T> data, HSSFSheet sheet, String pattern) {
		Iterator<T> it = data.iterator();//使用iterator遍历集合
		int rowIndex = 0;//行下标
		while (it.hasNext()) {
			rowIndex++;
			HSSFRow row = sheet.createRow(rowIndex);//获取当前行
			Map<String, Object> map = (Map)it.next();//将一条库存信息转换为map对象
			HSSFCell cell = row.createCell(0);//第一行
			cell.setCellValue(map.get("name").toString());//产品名
			cell = row.createCell(1);//第二行
			cell.setCellValue((Integer)map.get("inventoryCount"));//库存总量
			cell = row.createCell(2);//第三行
			cell.setCellValue((Integer)map.get("inventoryAvailable"));//库存可用
			cell = row.createCell(3);//第四行
			cell.setCellValue((Integer)map.get("inventoryOrderForm"));//已下订单
			cell = row.createCell(4);//第五行
			cell.setCellValue((Integer)map.get("inventoryFreeze"));//库存冻结
		}
	}
	/**
	 * 产生产品信息数据行
	 * @param data 产品信息
	 * @param sheet 工作簿
	 * @param pattern 时间格式
	 */
	@SuppressWarnings ({"unchecked","rawtypes"})//忽略类型检查和没有指定类型的警告
	private void iteratorProductData(Collection<T> data, HSSFSheet sheet, String pattern) {
		Iterator<T> it = data.iterator();//使用iterator遍历集合
		int rowIndex = 0;//行下标
		while (it.hasNext()) {
			rowIndex++;
			HSSFRow row = sheet.createRow(rowIndex);//获取行
			T t = (T) it.next();
			//利用java反射,获取javabean属性
			Field[] field = t.getClass().getDeclaredFields();
			int cellIndex = 0;//单元格下标
			for (int i = 0; i < field.length; i++) {
				String fieldName = field[i].getName();//获取属性名
				//不需要导出的属性值,跳过此次循环
				if ("serialVersionUID".equals(fieldName)) {
					continue;
				} else if("id".equals(fieldName)){
					continue;
				}else if ("valid".equals(fieldName)) {
					continue;
				} else if("createdTime".equals(fieldName)){
					continue;
				}else if ("modifiedTime".equals(fieldName)) {
					continue;
				}else if ("createdUser".equals(fieldName)) {
					continue;
				}else if ("modifiedUser".equals(fieldName)) {
					continue;
				}
				//组合成get方法
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				//获取单元格
				HSSFCell cell = row.createCell(cellIndex);
				cellIndex++;
				//判断值后,进行类型转换
				String textValue = null;
				try {
					Class tCls = t.getClass();//获取类对象
					Method getMethod = tCls.getMethod(getMethodName, new Class[]{});//获取此方法
					Object value = getMethod.invoke(t, new Object[] {});//运行
					if (value instanceof Date) {//时间格式
						Date date = (Date)value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					}else {//其余格式以字符串输出
						if (value == null) {
							textValue = "";
						}else{
							textValue = value.toString();
						}
					}
					//写入单元格
					cell.setCellValue(textValue);
					
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 产生客户信息的数据行
	 * @param data 客户信息
	 * @param sheet 工作簿
	 * @param pattern 时间格式
	 */
	@SuppressWarnings({"unchecked","rawtypes"})//忽略类型检查和没有指定类型的警告
	private void iteratorClientData(Collection<T> data, HSSFSheet sheet, String pattern) {
		Iterator<T> it = data.iterator();//使用iterator遍历集合
		int rowIndex = 0;//行下标
		while (it.hasNext()) {
			rowIndex++;
			HSSFRow row = sheet.createRow(rowIndex);//获取行
			T t = (T) it.next();
			//利用java反射,获取javabean的属性
			Field[] fields = t.getClass().getDeclaredFields();
			int cellIndex = 0;//单元格下标
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();//获取属性名
				//不需要导出的属性值,跳过此次循环
				if ("serialVersionUID".equals(fieldName)) {
					continue;
				} else if("id".equals(fieldName)){
					continue;
				}else if ("valid".equals(fieldName)) {
					continue;
				} else if("createdTime".equals(fieldName)){
					continue;
				}else if ("modifiedTime".equals(fieldName)) {
					continue;
				}else if ("createdUser".equals(fieldName)) {
					continue;
				}else if ("modifiedUser".equals(fieldName)) {
					continue;
				}
				//组合成get方法
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				//获取单元格
				HSSFCell cell = row.createCell(cellIndex);
				cellIndex++;
				//判断值后,进行类型转换
				String textValue = null;
				try {
					Class tCls = t.getClass();//获取类对象
					Method getMethod = tCls.getMethod(getMethodName, new Class[]{});//获取此方法
					Object value = getMethod.invoke(t, new Object[] {});//运行
					if (value instanceof Date) {//时间格式
						Date date = (Date)value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					}else {//其余格式以字符串输出
						if (value == null) {
							textValue = "";
						}else{
							textValue = value.toString();
						}
					}
					//写入单元格
					cell.setCellValue(textValue);
					
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
