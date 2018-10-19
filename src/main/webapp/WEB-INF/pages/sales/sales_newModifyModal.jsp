<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<form class="form-inline" action="javascript:;" autocomplete="off">
	<fieldset>
		<legend><span>基础信息</span></legend>
		<table class="wholeSituationModal">
			<tr>
				<td class="text-right">
					<span>售货单编号:</span>
				</td>
				<td>
					<input type="text" id="salesSaCodeModify" name="saleCode" class="form-control" placeholder="售货单编号">
					<span class="hintFont">若为空,系统将自动生成</span>
				</td>
			</tr>

			<tr>
				<td class="text-right">
					<span>售货日期:</span>
				</td>
				<td>
					<input autocomplete="off" type="text" id="salesSaDateModify" name="saleDate" class="form-control datepicker" placeholder="售货日期">
					<span class="hintFont">若为空,默认当天时间</span>
				</td>
			</tr>
			<tr>
				<td class="text-right">
					<span>选择客户:</span>
				</td>
				<td>
					<div class="btn-group">
						<input type="text" id="clientNameSales" class="form-control" placeholder="选择客户" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<ul id="clientDropdownMenu" class="dropdown-menu" style="overflow: auto; height: 170px;">
							<li><a href="#">迪丽热巴</a></li>
							<li><a href="#">杨幂</a></li>
							<li><a href="#">马云VIP</a></li>
							<li><a href="#">马化腾</a></li>
							<li><a href="#">杨木木</a></li>
						</ul>
					</div>
				</td>
				
			</tr>

			<tr>
				<td class="text-right">
					<span>备注:</span>
				</td>
				<td>
					<textarea id="salesNoteModify" name="note" class="form-control" placeholder="备注"></textarea>
				</td>
			</tr>
			<tr style="display: none;">
				<td class="text-right">
					<span>价格:</span>
				</td>
				<td>
					<input id="salesTotalPriceModify" class="form-control" placeholder="价格" readonly="readonly"/>
				</td>
			</tr>
			<tr style="display: none;">
				<td class="text-right">
					<span>状态:</span>
				</td>
				<td>
					<input id="salesValidModify" class="form-control" placeholder="状态" readonly="readonly"/>
				</td>
			</tr>
		</table>
	</fieldset>
		
	<div class="row" style="position: relative;top: 10px;height: 60px;">
		<div class="col-md-4 col-md-offset-8">
			<input type="button" value="取消" class="btn btn-sm btn-default cancelBtn">
			<input type="button" class="btn btn-sm btn-danger salesOk" value="确认">
		</div>
	</div>
</form>
<!-- 导入售货单新建 | 修改js -->
<script type="text/javascript" src="${basePath }/js/sales/sales_newModifyModal.js"></script>