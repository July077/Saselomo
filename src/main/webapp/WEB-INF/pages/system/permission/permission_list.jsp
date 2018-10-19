<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 导入权限列表js -->
<script type="text/javascript" src="${basePath }/js/system/permission/permission_list.js"></script>
<!-- 权限核心内容 -->
<div id="coreNav">
	<!-- 标签导航 -->
	<ul class="nav nav-tabs">
		<li role="presentation" class="active">
			<a href="javascript:;">
				<span class="glyphicon glyphicon-flag"></span>
				<span id="permissionTab">权限项管理</span>
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
					<span><b>权限:</b></span>
					<input type="text" class="form-control input-sm" id="searchRole" placeholder="权限">
				</div>
				<button class="btn btn-danger permissionSearchBtn">
					<span class="glyphicon glyphicon-search"></span>
					<span>查询</span>
				</button>
			</form>
		</div>
		<div class="col-md-4 btnList">
			<button class="btn btn-danger permissionAddBtn">
				<span class="glyphicon glyphicon-plus"></span>
				<span>新建</span>
			</button>
		</div>
	</div>
	<!-- 表格结果 -->
	<div id="coreTable">
		<table class="table table-bordered">
	      <thead>
	        <tr>
	          <th style="width: 5%;">NO</th>
	          <th>权限</th>
	          <th>描述</th>
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

