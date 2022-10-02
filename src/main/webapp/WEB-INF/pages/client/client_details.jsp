<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 客户详情 -->
<div id="coreNav">
	<!-- 标签导航 -->
	<ul class="nav nav-tabs">
		<li role="presentation" class="active">
			<a href="javascript:;">
				<span class="glyphicon glyphicon-flag"></span>
				<span id="clientDetailsTab">客户详情</span>
			</a>
		</li>
	</ul>
</div>
<div id="coreShow">	
	<div class="recDetails">
		<fieldset>
			<legend><span>基础信息</span></legend>
			<table class="wholeSituationModalDetails">
				<tr>
					<td>
						<span><b>姓名:</b></span>
					</td>
					<td class="modalDetails">
						<span id="clientNameDetails"></span>
					</td>

					<td>
						<span><b>性别:</b></span>
					</td>
					<td class="modalDetails">
						<span id="clientSexDetails"></span>
					</td>

					<td>
						<span><b>年龄:</b></span>
					</td>
					<td class="modalDetails">
						<span id="clientAgeDetails"></span>
					</td>

					<td>
						<span><b>皮肤状态:</b></span>
					</td>
					<td class="modalDetails">
						<span id="clientSkinConditionDetails"></span>
					</td>
				</tr>
				<tr>
					<td>
						<span><b>电话:</b></span>
					</td>
					<td class="modalDetails">
						<span id="clientPhoneDetails"></span>
					</td>

					<td>
						<span><b>作息时间:</b></span>
					</td>
					<td class="modalDetails">
						<span id="clientTimetableDetails"></span>
					</td>

					<td>
						<span><b>地址:</b></span>
					</td>
					<td class="modalDetails" colspan="3">
						<span id="clientAddressDetails"></span>
					</td>
				</tr>
				<tr>
					<td>
						<span><b>备注:</b></span>
					</td>
					<td colspan="7" class="modalDetails">
						<span id="clientNoteDetails"></span>
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
	
	<!-- 查询框 -->
	<div class="row detailsBtnList" id="queryBox">
		<div class="col-md-8">
			<!-- 查询框 -->
			<form class="form-inline" action="javascript:;">
				<div class="form-group">
					<span><b>购买时间:</b></span>
					<input autocomplete="off" type="text" id="searchPurDateId" class="form-control input-sm datepicker" placeholder="购买时间">
				</div>
				<button class="btn btn-danger cliPurHisSearchBtn">
					<span class="glyphicon glyphicon-search"></span>
					<span>查询</span>
				</button>
				<div id="proNameShow" class="btn-group" style="margin-left: 30px; ">
					<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span>产品名全简显示</span>
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" style="overflow: auto; height: 80px;">
						<li><a href="javascript:;">全称</a></li>
						<li><a href="javascript:;">简称</a></li>
					</ul>
				</div>
			</form>
		</div>
		<div class="col-md-4">
			<button class="btn btn-danger clientDownload">
				<span class="glyphicon glyphicon-download"></span>
				<span>导出</span>
			</button>
			<button class="btn btn-danger cliDetailsReturn" >
				<span class="glyphicon glyphicon-share-alt"></span>
				<span>返回</span>
			</button>
		</div>
	</div>
	
	<div id="detailsTablePage">
		<!-- 表格结果 -->
		<table class="table table-condensed table-hover" id="detailsTable">
			<thead>
				<tr>
					<th style="width: 5%;">NO</th>
					<th>购买时间</th>
					<th>购买产品</th>				
				</tr>
			</thead>
			<tbody id="content">
			</tbody>
		</table>
	</div>
	
	<div class="paginationBox">
		<div class="box">
			<div id="paginationReceivingSingle" class="page fl"></div>
		</div>
	</div>
</div>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 导入客户详情js -->
<script type="text/javascript" src="${basePath }/js/client/client_details.js"></script>