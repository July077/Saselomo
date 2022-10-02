<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 产品详情 -->
<form class="form-inline">
	<fieldset>
			<legend><span>基础信息</span></legend>
			<table class="wholeSituationModalDetails">
				<tr>
					<td>
						<span><b>简称:</b></span>
					</td>
					<td class="modalDetails">
						<span id="productAbbreviationDetails"></span>
					</td>

					<td>
						<span><b>产品名称:</b></span>
					</td>
					<td class="modalDetails">
						<span id="productNameDetails"></span>
					</td>
				</tr>
				<tr>
					<td>
						<span><b>一级(套/元):</b></span>
					</td>
					<td class="modalDetails">
						<span id="productFirstStageDetails"></span>
					</td>

					<td>
						<span><b>二级(套/元):</b></span>
					</td>
					<td class="modalDetails">
						<span id="productSecondStageDetails"></span>
					</td>
				</tr>
				<tr>
					<td>
						<span><b>至尊VIP(套/元):</b></span>
					</td>
					<td class="modalDetails">
						<span id="productSupremacyDetails"></span>
					</td>

					<td>
						<span><b>微商(套/元):</b></span>
					</td>
					<td class="modalDetails">
						<span id="productDerivativeDetails"></span>
					</td>
				</tr>
				<tr>
					<td>
						<span><b>零售(套/元):</b></span>
					</td>
					<td class="modalDetails">
						<span id="productRetailDetails"></span>
					</td>

					<td>
						<span><b>箱规(套/箱):</b></span>
					</td>
					<td class="modalDetails">
						<span id="productCartonSizeDetails"></span>
					</td>
				</tr>
				<tr>
					<td>
						<span><b>功效:</b></span>
					</td>
					<td colspan="7" class="modalDetails">
						<span id="productEffectDetails"></span>
					</td>
				</tr>
				<tr>
					<td>
						<span><b>卖点:</b></span>
					</td>
					<td colspan="3" class="modalDetails">
						<span id="productSellingPointsDetails"></span>
					</td>
				</tr>
				<tr>
					<td>
						<span><b>备注:</b></span>
					</td>
					<td colspan="3" class="modalDetails">
						<span id="productNoteDetails"></span>
					</td>
				</tr>
			</table>
	</fieldset>
</form>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 导入产品详情表单js -->
<script type="text/javascript" src="${basePath }/js/product/product_detailsModal.js"></script>