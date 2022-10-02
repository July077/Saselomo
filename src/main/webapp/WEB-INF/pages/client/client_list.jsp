<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 导入客户列表js -->
<script type="text/javascript" src="${basePath }/js/client/client_list.js"></script>
<!-- 客户核心内容 -->
<div id="coreNav">
	<!-- 标签导航 -->
	<ul class="nav nav-tabs">
		<li role="presentation" class="active">
			<a href="javascript:;">
				<span class="glyphicon glyphicon-flag"></span>
				<span id="clientTab">客户管理</span>
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
					<span><b>姓名:</b></span>
					<input type="text" class="form-control input-sm" id="searchClientName" placeholder="姓名">
				</div>
				<div class="form-group">
					<span><b>皮肤状况:</b></span>
					<input type="text" class="form-control input-sm" id="searchClientSkinCondition" placeholder="皮肤状况">
				</div>
				<div class="form-group">
					<span><b>作息时间:</b></span>
					<input autocomplete="off" type="text" class="form-control input-sm timepicker" id="searchClientTimetable" placeholder="作息时间">
				</div>
				<button class="btn btn-danger clientSearchBtn">
					<span class="glyphicon glyphicon-search"></span>
					<span>查询</span>
				</button>
			</form>
		</div>
		<div class="col-md-4 btnList">
			<button class="btn btn-danger clientAddBtn">
				<span class="glyphicon glyphicon-plus"></span>
				<span>新建</span>
			</button>
			<button class="btn btn-danger clientUpload">
				<span class="glyphicon glyphicon-upload"></span>
				<span>导入</span>
			</button>
			<button class="btn btn-danger clientDownload" onclick="exportClient()">
				<span class="glyphicon glyphicon-download"></span>
				<span>导出</span>
			</button>
		</div>
	</div>
	<!-- 表格结果 -->
	<div id="coreTable">
		<table class="table table-bordered">
	      <thead>
	        <tr>
	          <th style="width: 5%;">NO</th>
	          <th>姓名</th>
	          <th>性别</th>
	          <th>年龄</th>
	          <th>电话</th>
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

