<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 菜单栏	导航 -->
<div class="col-md-2 menuNav">
	<ul class="nav nav-stacked">
		<li class="parentNav">
			<a href="javascript:;">
				<span class="glyphicon glyphicon-hourglass"></span>
				<span>基础数据管理</span>
				<span class="glyphicon glyphicon-menu-down pull-right dropdown-icon"></span>
			</a>
		</li>
		<li class="menu-hide"><a id="clientNav" href="javascript:;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;客户管理</a></li>
		<li class="menu-hide"><a id="productNav" href="javascript:;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;产品管理</a></li>
		<shiro:hasPermission name="user:all">
			<li class="menu-hide"><a id="userNav" href="javascript:;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户管理</a></li>
		</shiro:hasPermission>
	</ul>
	<ul class="nav nav-stacked">
		<li class="parentNav">
			<a href="javascript:;">
				<span class="glyphicon glyphicon-yen"></span>
				<span>供销管理</span>
				<span class="glyphicon glyphicon-menu-down  pull-right dropdown-icon"></span>
			</a>
		</li>
		<li class="menu-hide"><a id="receivingNav" href="javascript:;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;收货管理</a></li>
		<li class="menu-hide"><a id="salesNav" href="javascript:;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;销售管理</a></li>
	</ul>
	<ul class="nav nav-stacked">
		<li class="parentNav">
			<a href="javascript:;">
				<span class="glyphicon glyphicon-list-alt"></span>
				<span>库存管理</span>
				<span class="glyphicon glyphicon-menu-down  pull-right dropdown-icon"></span>
			</a>
		</li>
		<li class="menu-hide"><a id="inventoryNav" href="javascript:;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;库存信息管理</a></li>
		<li class="menu-hide"><a href="javascript:;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;库存冻结管理</a></li>
		<li class="menu-hide"><a href="javascript:;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;库存操作记录管理</a></li>
	</ul>
	<ul class="nav nav-stacked">
		<li class="parentNav">
			<a href="javascript:;">
				<span class="glyphicon glyphicon-dashboard"></span>
				<span>盘点管理</span>
				<span class="glyphicon glyphicon-menu-down  pull-right dropdown-icon"></span>
			</a>
		</li>
		<li class="menu-hide"><a href="javascript:;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;盘点管理</a></li>
	</ul>
	<ul class="nav nav-stacked">
		<li class="parentNav">
			<a href="javascript:;">
				<span class="glyphicon glyphicon-alert"></span>
				<span>警报管理</span>
				<span class="glyphicon glyphicon-menu-down  pull-right dropdown-icon"></span>
			</a>
		</li>
		<li class="menu-hide"><a href="javascript:;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报警设置管理</a></li>
		<li class="menu-hide"><a href="javascript:;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报警记录管理</a></li>
	</ul>
	<shiro:hasPermission name="system:all">
		<ul class="nav nav-stacked">
			<li class="parentNav">
				<a href="javascript:;">
					<span class="glyphicon glyphicon-lock"></span>
					<span>权限管理</span>
					<span class="glyphicon glyphicon-menu-down  pull-right dropdown-icon"></span>
				</a>
			</li>
			<li class="menu-hide"><a id="permissionNav" href="javascript:;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权限项管理</a></li>
			<li class="menu-hide"><a id="roleNav" href="javascript:;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;角色管理</a></li>
		</ul>	
	</shiro:hasPermission>
</div>

<script type="text/javascript" src="${basePath }/js/common/menu.js"></script>