<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="form-inline" action="javascript:;">
	<fieldset>
		<legend><span>基础信息</span></legend>
		<table class="wholeSituationModal">
			<tr>
				<td class="text-right">
					<span>收货单编号:</span>
				</td>
				<td>
					<input type="text" name="receiptCode" id="receivingReceiptCodeModify" class="form-control" placeholder="收货单编号">
					<span class="hintFont">若为空,系统将自动生成</span>
				</td>
			</tr>

			<tr>
				<td class="text-right">
					<span>进货时间:</span>
				</td>
				<td>
					<input autocomplete="off" type="text" name="purchaseTime" id="receivingPurchaseTimeModify" class="form-control datepicker" placeholder="进货日期">
					<span class="hintFont">若为空,默认当天时间</span>
				</td>
			</tr>


			<tr>
				<td class="text-right">
					<span>备注:</span>
				</td>
				<td>
					<textarea name="receivingNote" id="receivingNoteModify" class="form-control" placeholder="备注"></textarea>
				</td>
			</tr>
			<tr style="display: none;">
				<td class="text-right">
					<span>价格:</span>
				</td>
				<td>
					<input id="receivingTotalPriceModify" class="form-control" placeholder="价格" readonly="readonly"/>
				</td>
			</tr>
			<tr style="display: none;">
				<td class="text-right">
					<span>状态:</span>
				</td>
				<td>
					<input id="recValidModify" class="form-control" placeholder="状态" readonly="readonly"/>
				</td>
			</tr>
		</table>
	</fieldset>
		
	<div class="row" style="position: relative;top: 10px;height: 60px;">
		<div class="col-md-4 col-md-offset-8">
			<input type="button" value="取消" class="btn btn-sm btn-default cancelBtn">
			<input type="button" class="btn btn-sm btn-danger receivingOk" value="确认">
		</div>
	</div>
</form>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 导入收货单新建修改js -->
<script type="text/javascript" src="${basePath }/js/receiving/receiving_newModifyModal.js"></script>