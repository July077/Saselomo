<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 导入用户列表js -->
<script type="text/javascript" src="${basePath }/js/system/user/user_list.js"></script>
<!-- 用户核心内容 -->
<div id="coreNav">
	<!-- 标签导航 -->
	<ul class="nav nav-tabs">
		<li role="presentation" class="active">
			<a href="javascript:;">
				<span class="glyphicon glyphicon-flag"></span>
				<span id="userTab">用户管理</span>
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
					<input type="text" class="form-control input-sm" id="searchUserName" placeholder="姓名">
				</div>
				<div class="form-group">
					<span><b>用户名:</b></span>
					<input type="text" class="form-control input-sm" id="searchUserUName" placeholder="用户名">
				</div>
				<div class="form-group">
					<span><b>手机号:</b></span>
					<input type="text" class="form-control input-sm" id="searchUserMobile" placeholder="手机号">
				</div>
				<button class="btn btn-danger userSearchBtn">
					<span class="glyphicon glyphicon-search"></span>
					<span>查询</span>
				</button>
			</form>
		</div>
		<div class="col-md-4 btnList">
			<button class="btn btn-danger userAddBtn">
				<span class="glyphicon glyphicon-plus"></span>
				<span>新建</span>
			</button>
			<button class="btn btn-danger userUpload">
				<span class="glyphicon glyphicon-upload"></span>
				<span>导入</span>
			</button>
			<button class="btn btn-danger userDownload" onclick="exportUser()">
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
	          <th>用户名</th>
	          <th>邮箱</th>
	          <th>手机号</th>
	          <th>状态</th>
	          <th>创建日期</th>
	          <th>操作</th>
	        </tr>
	      </thead>
	      <tbody id="content">
	      </tbody>
	    </table>
	</div>
	<!-- 分页 -->
	<%@include file="../../common/pagination.jsp" %>
</div>

