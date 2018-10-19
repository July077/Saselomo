<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 导入客户列表js -->
<script type="text/javascript" src="${basePath }/js/inventory/inventory_list.js"></script>
<!-- 内容显示栏 -->
<div id="coreNav">
	<!-- 标签导航 -->
	<ul class="nav nav-tabs">
		<li role="presentation" class="active">
			<a href="javascript:;">
				<span class="glyphicon glyphicon-flag"></span>
				<span id="inventoryTab">库存管理</span>
			</a>
		</li>
	</ul>
</div>

<div id="coreShow">	
	<!-- 查询框 -->
	<div class="row" id="queryBox">
		<div class="col-md-10">
			<!-- 查询框 -->
			<form class="form-inline" action="javascript:;">
				<div class="form-group">
					<span><b>产品名称:</b></span>
					<input type="text" id="searchProNameInven" class="form-control input-sm" placeholder="产品名称">
				</div>
				<button class="btn btn-danger inventorySearchBtn" >
					<span class="glyphicon glyphicon-search"></span>
					<span>查询</span>
				</button>
			</form>
		</div>
		<div class="btnList">
			<button class="btn btn-danger invenDownload" onclick="exportInventory()">
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
	          <th>产品名称</th>
	          <th>库存总量</th>
	          <th>库存可用</th>
	          <th>已下订单</th>
	          <th>库存冻结</th>
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
