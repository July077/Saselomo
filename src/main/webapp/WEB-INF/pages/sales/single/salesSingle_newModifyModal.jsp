<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="form-inline" action="javascript:;" autocomplete="off">
	<fieldset>
		<legend><span>基础信息</span></legend>
		<table class="wholeSituationModal">
			<tr>
				<td class="text-right">
					<span>选择产品:</span>
				</td>
				<td>
					<div class="btn-group">
						<input type="text" name="productNameRec" id="productNameSalSingle" class="form-control" placeholder="选择产品" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<ul class="dropdown-menu" style="overflow: auto; height: 170px;" id="productDropdownMenu">
						</ul>
					</div>
				</td>
			</tr>
			<tr>
				<td class="text-right">
					<span>选择级别:</span>
				</td>
				<td>
					<div class="btn-group">
						<input type="text" name="gradeSal" id="gradeSalSingle" class="form-control" placeholder="选择级别" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" readonly="readonly">
						<ul class="dropdown-menu" id="gradeDropdownMenu">
							<li><a href="javascript:;">一级</a></li>
							<li><a href="javascript:;">二级</a></li>
							<li><a href="javascript:;">至尊VIP</a></li>
							<li><a href="javascript:;">微商</a></li>
							<li><a href="javascript:;">零售</a></li>
						</ul>
					</div>
				</td>
			</tr>
			<tr>
				<td class="text-right">
					<span>价格:</span>
				</td>
				<td>
					<input type="text" id="proPrice"  class="form-control" placeholder="价格" readonly>
					<span class="hintFont">选择级别自动生成</span>
				</td>
			</tr>
			<tr>
				<td class="text-right">
					<span>数量:</span>
				</td>
				<td>
					<input maxlength="5" type="text" id="salSingleCount" name="count" class="form-control countRegEx" placeholder="数量">
					<span id="salInvenJudgeAlert" class="hintFont"></span>
				</td>
			</tr>
			<tr style="display: none;">
				<td class="text-right">
					<span>状态:</span>
				</td>
				<td>
					<input readonly="readonly" maxlength="5" type="text" id="salSingleValid" name="valid" class="form-control" placeholder="valid">
				</td>
			</tr>
		</table>
	</fieldset>
		
	<div class="row" style="position: relative;top: 10px;height: 60px;">
		<div class="col-md-4 col-md-offset-8">
			<input type="button" value="取消" class="btn btn-sm btn-default cancelBtn">
			<input type="button" class="btn btn-sm btn-danger salSingleOk" value="确认">
		</div>
	</div>
</form>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 导入售货单新建修改js -->
<script type="text/javascript" src="${basePath }/js/sales/single/salesSingle_newModifyModal.js"></script>

