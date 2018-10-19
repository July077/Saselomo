package client.exceltemplate;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class TestClientExcelTemplate {
	/** 测试查询文件
	 * @throws IOException */
	@Test
	public void testFileFindExce() throws IOException{
		File dir = new File(".");
		/*
		 * boolean isFile()
		 * 判断当前File表示的是否为一个文件
		 * 
		 * boolean isDirectory()
		 * 判断当前File表示的是否为一个目录
		 */
		if(dir.isDirectory()){
			//获取一个目录中的所有子项
			File[] subs = dir.listFiles();
			System.out.println("len:"+subs.length);
			for(File sub : subs){
				if(sub.isFile()){
					System.out.print("文件:");
				}
				if(sub.isDirectory()){
					System.out.print("目录:");
				}
				System.out.println(sub.getName());
			}
			File[] subSrc = subs[5].listFiles();
			for(File sub : subSrc){
				if (sub.isFile()) {
					System.out.print("文件:");
				}
				if (sub.isDirectory()) {
					System.out.print("目录:");
				}
				System.out.println(sub.getName());
			}
		}
	}
}
