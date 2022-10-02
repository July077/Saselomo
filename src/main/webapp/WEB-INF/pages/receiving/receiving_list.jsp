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
				<span id="receivingTab">收货管理</span>
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
					<span><b>收货单编号:</b></span>
					<input type="text" id="searchReceivingReceiptCode" class="form-control input-sm" placeholder="收货单编号">
				</div>
				<div class="form-group">
					<span><b>进货时间:</b></span>
					<input autocomplete="off" type="text" id="searchReceivingPurchaseTime" class="form-control input-sm datepicker" placeholder="进货时间">
				</div>
				<button class="btn btn-danger receivingSearchBtn">
					<span class="glyphicon glyphicon-search"></span>
					<span>查询</span>
				</button>
			</form>
		</div>
		<div class="btnList">
			<button class="btn btn-danger receivingAddBtn">
				<span class="glyphicon glyphicon-plus"></span>
				<span>新建</span>
			</button>
			<button class="btn btn-danger recDownload">
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
	          <th>收货单编号</th>
	          <th>收货单状态</th>
	          <th>进货时间</th>
	          <th>总价格</th>
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
<!-- 导入收货单列表js -->
<script type="text/javascript" src="${basePath }/js/receiving/receiving_list.js"></script>
