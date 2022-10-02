package com.huahua.saselomo.common.web;

import java.io.Serializable;

/**
 * 借此对象封装分页信息
 * @author Lin·Y
 *
 */
public class PageObject implements Serializable{
	private static final long serialVersionUID = -2525554846151962312L;
	/** 当前页*/
	private int currentPage=1;
	/** 总记录数(表中的记录)*/
	private int rowCount;
	/** 总页数*/
	private int totalPage;
	/** 每页显示的记录数*/
	private int pageSize=10;
	/** 下一页的起始记录*/
	private int startIndex;
	
	/** 计算总页数*/
	public int getTotalPage(){
		int pages = rowCount / pageSize;
		if(rowCount%pageSize!=0){
			pages+=1;
		}
		return pages;
	}
	
	/**计算下一页的起始记录 */
	public int getStartIndex(){
		return (currentPage-1)*pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	@Override
	public String toString() {
		return "PageObject [currentPage=" + currentPage + ", rowCount=" + rowCount + ", totalPage=" + totalPage
				+ ", pageSize=" + pageSize + ", startIndex=" + startIndex + "]";
	}
	
	
}
