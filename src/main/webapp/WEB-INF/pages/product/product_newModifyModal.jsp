<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 产品新建修改 -->
<form class="form-inline" action="javascript:;">
	<fieldset>
		<legend><span>基础信息</span></legend>
		<table class="wholeSituationModal">
			<tr>
				<td class="text-right">
					<span class="glyphicon glyphicon-asterisk asteriskIcon"></span>
					<span>产品名称:</span>
				</td>
				<td>
					<input maxlength="50" type="text" name="productName" id="productNameModify" class="form-control" placeholder="产品名称">
				</td>
				<td class="text-right">
					<span>简称:</span>
				</td>
				<td>
					<input maxlength="50" type="text" name="abbreviation" id="productAbbreviationModify" class="form-control" placeholder="简称">
				</td>
			</tr>
	
			<tr>
				<td class="text-right">
					<span>箱规(套/箱) :</span>
				</td>
				<td>
					<input maxlength="5" type="text" name="cartonSize" id="productCartonSizeModify" class="form-control" placeholder="箱规">
				</td>
				<td class="text-right">
					<span>一级(套/元):</span>
				</td>
				<td>
					<input maxlength="10" type="text" name="firstStage" id="productFirstStageModify" class="form-control proPrice" placeholder="一级价格">
				</td>
			</tr>
			<tr>
				<td class="text-right">
					<span>二级(套/元):</span>
				</td>
				<td>
					<input maxlength="10" type="tel" name="secondStage" id="productSecondStageModify" class="form-control proPrice" placeholder="二级价格" >
				</td>
				<td class="text-right">
					<span>至尊VIP(套/元):</span>
				</td>
				<td>
					<input maxlength="10" type="text" name="supremacy" id="productSupremacyModify" class="form-control proPrice" placeholder="至尊价格">
				</td>
			</tr>
			<tr>
				<td class="text-right">
					<span>微商(套/元):</span>
				</td>
				<td>
					<input maxlength="10" type="tel" name="derivative" id="productDerivativeModify" class="form-control proPrice" placeholder="微商价格" >
				</td>
				<td class="text-right">
					<span>零售(套/元):</span>
				</td>
				<td>
					<input maxlength="10" type="text" name="retail" id="productRetailModify" class="form-control proPrice" placeholder="零售价格">
				</td>
			</tr>
			<tr>
				<td class="text-right">
					<span>功效:</span>
				</td>
				<td>
					<textarea maxlength="255" class="form-control" name="effect" id="productEffectModify" placeholder="功效"></textarea>
				</td>
				<td class="text-right">
					<span>卖点:</span>
				</td>
				<td>
					<textarea maxlength="255" class="form-control" name="sellingPoints" id="productSellingPointsModify" placeholder="卖点"></textarea>
				</td>
			</tr>
			<tr>
				<td class="text-right">
					<span>备注:</span>
				</td>
				<td>
					<textarea maxlength="255" class="form-control" name="productNote" id="productNoteModify" placeholder="备注"></textarea>
				</td>
			</tr>
		</table>
	</fieldset>
	<div class="row" style="position: relative;top: 10px;height: 60px;">
		<div class="col-md-2 col-md-offset-5">
			<input type="button" value="取消" class="btn btn-sm btn-default cancelBtn">
			<input type="button" class="btn btn-sm btn-danger productOk" value="确认">
		</div>
	</div>
</form>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 导入产品新建修改表单js -->
<script type="text/javascript" src="${basePath }/js/product/product_newModifyModal.js"></script>