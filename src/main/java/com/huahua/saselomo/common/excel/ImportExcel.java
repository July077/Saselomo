package com.huahua.saselomo.common.excel;
/**
 * @category excel导入公共类
 * @author Lin·Y
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.huahua.saselomo.common.exception.ImportObjectException;
import com.huahua.saselomo.common.exception.NullPropertyException;

public class ImportExcel {
	public final static String excel2003 = ".xls";//2003- 版本的excel
	public final static String excel2007 = ".xlsx";//2007- 版本的excel
	
	public final static String client = "client";
	public final static String product = "product";
	/**
	 * 导入信息入口
	 * @param in 与输入设备关联的流对象
	 * @param filename 文件名
	 * @param title 调用着,对应的标题
	 * @return
	 * @throws Exception 
	 */
	public List<List<Object>> importExcelCar(InputStream in, String filename, String[] title) throws ImportObjectException, IOException {
		List<List<Object>> list = importExcel(in, filename, title);
		return list;
	}
	/**
	 * 导入实现
	 * @param in 与设输入设备关联的流对象
	 * @param filename 文件名
	 * @param title 调用着,对应的标题
	 * @return
	 * @throws IOException 
	 */
	private List<List<Object>> importExcel(InputStream in, String filename, String[] title) throws IOException {
		//创建excel工作簿
		Workbook wb = getWorkbook(in, filename);
		if (wb == null) {
			throw new NullPropertyException("创建Excel工作簿为空...");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		//创建封装数据集合
		List<List<Object>> list = new ArrayList<List<Object>>();
		//获取Excel中的第一个sheet
		sheet = wb.getSheetAt(0);
		System.out.println("64-row:"+sheet.getFirstRowNum()+":"+sheet.getLastRowNum());
		//获取标题列
		row = sheet.getRow(sheet.getFirstRowNum());
		//校验标题列是否满足
		int[] cellNum = checkTitle(title, row);
		//遍历当前sheet中除了标题列的所有行
		for (int j = (sheet.getFirstRowNum()+1); j <=sheet.getLastRowNum(); j++) {
			//内容行
			row = sheet.getRow(j);
			//行为空跳过
			if(row==null) continue;
			//创建封装单条值集合
			List<Object> li = new ArrayList<Object>();
//			System.out.println("cell:"+cellNum[0]+":"+cellNum[1]);
			for (int k = cellNum[0]; k < cellNum[1]; k++) {//对应标题列的开始结束下标
				cell = row.getCell(k);
//				System.out.println("78---"+getCellValue(cell).toString());
				li.add(getCellValue(cell));
			}
			list.add(li);
		}
		
		return list;
	}
	/**
	 * 描述: 检查用户导入文件的标题是否与系统规则一致
	 * @param title 
	 * @param row 用户导入的文件标题
	 * @return 返回标题列开始下标与结束下标
	 */
	private int[] checkTitle(String[] title, Row row) {
		int cellNum = row.getFirstCellNum();
		int[] titleIndex = new int[]{cellNum, row.getLastCellNum()};
		for (int i = 0; i < title.length; i++) {
			String inputTitle = row.getCell(cellNum).getRichStringCellValue().toString();
			if (!title[i].equals(inputTitle)) {//检查标题列是否一致
				throw new RuntimeException("位于第"+(cellNum+1)+"列,您的标题头为:"+("".equals(inputTitle)?"空":inputTitle)+",标题列为:"+title[i]);
			}
			cellNum++;
		}
		return titleIndex;
	}
	/**
     * 描述：对表格中数值进行格式化
     */
    private  Object getCellValue(Cell cell){
    	if(cell == null) return "";
    	Object value = null;
        DecimalFormat dfNum = new DecimalFormat("0");  //格式化字符类型的数字
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");  //时间格式化
        DecimalFormat df2Dou = new DecimalFormat("0.00");  //格式化数字
//        System.out.println("90行,获取值类型---"+cell.getCellType());
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC://数字型
                if("General".equals(cell.getCellStyle().getDataFormatString())){//常规
                    value = dfNum.format(cell.getNumericCellValue());
                }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){//日期
                    value = sdfDate.format(cell.getDateCellValue());
                }else if ("h:mm:ss".equals(cell.getCellStyle().getDataFormatString())) {//时间
					value = sdfTime.format(cell.getDateCellValue());
				}else{
                    value = df2Dou.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN://布尔
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK://空
                value = "";
                break;
            default:
                break;
        }
        return value;
    }
	/**
	 * 根据文件后缀,适应上传的Excel版本
	 * @param in
	 * @param filename
	 * @return
	 * @throws Exception 
	 */
	private Workbook getWorkbook(InputStream in, String filename) throws IOException, ImportObjectException{
		Workbook wb = null;
		String fileType = filename.substring(filename.lastIndexOf("."));//获取文件后缀
		if(excel2003.equals(fileType)){//2003版excel
			wb = new HSSFWorkbook(in);
		}else if (excel2007.equals(fileType)) {//2007版excel
			wb = new XSSFWorkbook(in);
		}else {//不是excel文件
			throw new ImportObjectException("解析的文件格式有误...");
		}
		return wb;
	}
	
}
