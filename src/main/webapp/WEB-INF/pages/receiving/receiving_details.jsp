<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="coreNav">
	<!-- 标签导航 -->
	<ul class="nav nav-tabs">
		<li role="presentation" class="active">
			<a href="#">
				<span class="glyphicon glyphicon-flag"></span>
				<span id="receivingTab">收货管理 - 收货详情</span>
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
						<span><b>收获单编号:</b></span>
					</td>
					<td class="modalDetails">
						<span id="receivingReceiptCodeDetails"></span>
					</td>
	
					<td>
						<span><b>收获单状态:</b></span>
					</td>
					<td class="modalDetails">
						<span id="receivingValidDetails"></span>
					</td>
					<td>
						<span><b>进货日期:</b></span>
					</td>
					<td class="modalDetails">
						<span id="receivingPurchaseTimeDetails"></span>
					</td>
					<td>
						<span><b>总价格:</b></span>
					</td>
					<td class="modalDetails">
						<span id="receivingTotalPriceDetails"></span>
					</td>
				</tr>
	
				<tr>
					<td>
						<span><b>备注:</b></span>
					</td>
					<td class="modalDetails" colspan="5" >
						<span id="receivingNoteDetails"></span>
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
					<span><b>产品名称:</b></span>
					<input type="text" id="searchRecProductNameId" class="form-control input-sm" placeholder="产品名称">
				</div>
				<button class="btn btn-danger recSingleSearchBtn">
					<span class="glyphicon glyphicon-search"></span>
					<span>查询</span>
				</button>
			</form>
		</div>
		<div>
			<div class="col-md-4">
				<button class="btn btn-danger recSingleAddBtn" >
					<span class="glyphicon glyphicon-plus"></span>
					<span>新建</span>
				</button>
				<button class="btn btn-danger recConfirmBtn" >
					<span>确认收货</span>
				</button>
				<button class="btn btn-danger recDetailsReturn" >
					<span class="glyphicon glyphicon-share-alt"></span>
					<span>返回</span>
				</button>
			</div>
		</div>
	</div>
	
	<div id="detailsTablePage">
		<table class="table table-bordered table-hover" id="detailsTable">
	      <thead>
	        <tr>
	          <th class="checkboxSingle">
	          	<label>
			    	<input type="checkbox" id="checkedAll">
			    	全选
			    </label>
	          </th>
	          <th style="width: 5%;">NO</th>
	          <th>收货单编号</th>
	          <th>产品名称</th>
	          <th>收获单状态</th>
	          <th>进货价格(元)</th>
	          <th>数量</th>
	          <th>操作</th>
	        </tr>
	      </thead>
	      <tbody id="contentReceivingSingles">
		  </tbody>
		</table>
	</div>
	
	<div class="paginationBox">
		<div class="box">
			<div id="paginationReceivingSingle" class="page fl"></div>
		</div>
	</div>
</div>
<!-- 确定收货选中的子项 -->
<div class="modal fade" id="confirmReceiving" tabindex="-1" style="margin-top: 100px;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-body">
				<h4><span class="glyphicon glyphicon-question-sign text-warning" style="font-size: 30px;"></span><span class="confirmPromptMessage"></span></h4>
				<form class="form-inline" action="javascript:;">
					<div class="row" style="position: relative;top: 10px;height: 60px;">
						<div class="col-md-6 col-md-offset-6">
							<input type="button" value="取消" class="btn btn-sm btn-default cancelBtn">
							<input type="button" class="btn btn-sm btn-danger confirmRecOk" value="确认">
						</div>
					</div>
				</form>
	      	</div>
		</div>
	</div>
</div>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 导入收货单详情js -->
<script type="text/javascript" src="${basePath }/js/receiving/receiving_details.js"></script>