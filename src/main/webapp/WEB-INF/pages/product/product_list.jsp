<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 导入产品列表js -->
<script type="text/javascript" src="${basePath }/js/product/product_list.js"></script>
<!-- 产品核心内容 -->
<!-- 核心内容 -->
<div id="coreNav">
	<!-- 标签导航 -->
	<ul class="nav nav-tabs">
		<li role="presentation" class="active">
			<a href="javascript:;">
				<span class="glyphicon glyphicon-flag"></span>
				<span id="productTab">产品管理</span>
			</a>
		</li>
	</ul>
</div>

<div id="coreShow">	
	<!-- 查询框 -->
	<div class="row" id="queryBox">
		<div class="col-md-9">
			<!-- 查询框 -->
			<form class="form-inline" action="javascript:;">
				<div class="form-group">
					<span><b>产品名称:</b></span>
					<input size="16" maxlength="50" type="text" class="form-control input-sm" id="searchProductName" placeholder="产品名称">
				</div>
				<div class="form-group">
					<span><b>简称:</b></span>
					<input size="16" maxlength="50" type="text" class="form-control input-sm" id="searchProductAbbreviation" placeholder="简称">
				</div>
				<div class="form-group">
					<span><b>卖点:</b></span>
					<input size="16" maxlength="255" type="text" class="form-control input-sm" id="searchProductSellingPoints" placeholder="卖点">
				</div>
				<div class="form-group">
					<span><b>功效:</b></span>
					<input size="16" maxlength="255" type="text" class="form-control input-sm" id="searchProductEffect" placeholder="功效">
				</div>
				<button class="btn btn-danger productSearchBtn">
					<span class="glyphicon glyphicon-search"></span>
					<span>查询</span>
				</button>
			</form>
		</div>
		<div class="btnList">
			<button class="btn btn-danger productAddBtn">
				<span class="glyphicon glyphicon-plus"></span>
				<span>新建</span>
			</button>
			<button class="btn btn-danger productUpload">
				<span class="glyphicon glyphicon-upload"></span>
				<span>导入</span>
			</button>
			<button class="btn btn-danger productDownload" onclick="exportProduct()">
				<span class="glyphicon glyphicon-download"></span>
				<span>导出</span>
			</button>
		</div>
	</div>
	<!-- 表格结果 -->
	<div id="coreTable" class="">
		<table class="table table-bordered table-hover">
	      <thead>
	        <tr>
	          <th style="width: 5%;">NO</th>
	          <th>简称</th>
	          <th>产品名称</th>
	          <th>功效</th>
	          <th>箱规(套/箱)</th>
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
<script>
	$(function () {
		$('[data-toggle="tooltip"]').tooltip();
	});
</script>
