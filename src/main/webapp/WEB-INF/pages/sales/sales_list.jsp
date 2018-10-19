<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 核心内容 -->
<div id="coreNav">
	<!-- 标签导航 -->
	<ul class="nav nav-tabs">
		<li role="presentation" class="active">
			<a href="javascript:;">
				<span class="glyphicon glyphicon-flag"></span>
				<span id="saleTab">销售管理</span>
			</a>
		</li>
	</ul>
</div>

<div id="coreShow">	
	<!-- 查询框 -->
	<div class="row" id="queryBox">
		<div class="col-md-8">
			<!-- 查询框 -->
			<form class="form-inline" action="javascript:;">
				<div class="form-group">
					<span><b>客户名称:</b></span>
					<input type="text" id="searchClientName" class="form-control input-sm" placeholder="客户名称">
				</div>
				<div class="form-group">
					<span><b>销售时间:</b></span>
					<input autocomplete="off" type="text" id="searchSalesSaDate" class="form-control input-sm datepicker" placeholder="销售时间">
				</div>
				
				<button class="btn btn-danger salesSearchBtn">
					<span class="glyphicon glyphicon-search"></span>
					<span>查询</span>
				</button>
			</form>
		</div>
		<div class="btnList">
			<button class="btn btn-danger salesAddBtn">
				<span class="glyphicon glyphicon-plus"></span>
				<span>新建</span>
			</button>
			<button class="btn btn-danger salDownload">
				<span class="glyphicon glyphicon-download"></span>
				<span>导出</span>
			</button>
		</div>
	</div>
	<!-- 表格结果 -->
	<div id="coreTable">
		<table class="table table-bordered table-hover">
	      <thead>
	        <tr>
	          <th style="width: 5%;">NO</th>
	          <th>售货单编号</th>
	          <th>售货单状态</th>
	          <th>销售时间</th>
	          <th>总价格</th>
	          <th>购买人</th>
	          <th>操作</th>
	        </tr>
	      </thead>
	      <tbody id="content">
	      </tbody>
	    </table>
	</div>
	<!-- 分页 -->
	<%@include file="../common/pagination.jsp" %>
</div>
<!-- 导入售货单列表js -->
<script type="text/javascript" src="${basePath }/js/sales/sales_list.js"></script>
