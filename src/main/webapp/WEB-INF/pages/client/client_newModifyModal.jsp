<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 新建客户内容信息 -->
<form class="form-horizontal" action="javascript:;">
	<fieldset>
		<legend><span>基础信息</span></legend>
		<table class="wholeSituationModal">
			<tr>
				<td class="text-right">
					<span class="glyphicon glyphicon-asterisk asteriskIcon"></span>
					<span>客户姓名:</span>
				</td>
				<td>
					<div class="col-md-8">
						<input maxlength="50" type="text" name="clientName" id="clientNameModify" class="form-control" placeholder="姓名">
					</div>
					
				</td>
				<td class="text-right">
					<span>性别:</span>
				</td>
				<td>
					<div class="col-md-8">
						<select class="form-control required" id="clientSexModify">
						    <option>请选择性别</option>
						    <option>男</option>
						    <option>女</option>
					   </select>
					</div>
				</td>
			</tr>

			<tr>
				<td class="text-right">
					<span>年龄:</span>
				</td>
				<td>
					<div class="col-md-8">
						<input maxlength="3" type="text" name="age" id="clientAgeModify" class="form-control" placeholder="年龄">
					</div>
					
				</td>
				<td class="text-right">
					<span>地址:</span>
				</td>
				<td>
					<div class="col-md-8">
						<input maxlength="100" type="text" name="address" id="clientAddressModify" class="form-control" placeholder="地址">
					</div>
					
				</td>
			</tr>

			<tr>
				<td class="text-right">
					<span>电话:</span>
				</td>
				<td>
					<div class="col-md-8">
						<input maxlength="30" type="tel" name="phone" id="clientPhoneModify" class="form-control" placeholder="电话" >
					</div>
					
				</td>
				<td class="text-right">
					<span>皮肤状态:</span>
				</td>
				<td>
					<div class="col-md-8">
						<input maxlength="100" type="text" name="skinCondition" id="clientSkinConditionModify" class="form-control" placeholder="皮肤状态">
					</div>
					
				</td>
			</tr>

			<tr>
				<td class="text-right">
					<span>作息时间:</span>
				</td>
				<td>
					<div class="col-md-8">
						<input autocomplete="off" type="text" name="timetable" id="clientTimetableModify" placeholder="作息时间" class="form-control timepicker">
					</div>
				</td>
				
				<td class="text-right">
					<span>备注:</span>
				</td>
				<td>
					<div class="col-md-8">
						<textarea maxlength="255" class="form-control" id="clientNoteModify" name="clientNote" placeholder="备注"></textarea>
					</div>
					
				</td>
			</tr>
		</table>
	</fieldset>
		
	<div class="row" style="position: relative;top: 10px;height: 60px;">
		<div class="col-md-2 col-md-offset-5">
			<input type="button" value="取消" class="btn btn-sm btn-default cancelBtn">
			<input type="button" class="btn btn-sm btn-danger clientOk" value="确认">
		</div>
	</div>
</form>

<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 导入客户新建修改表单js -->
<script type="text/javascript" src="${basePath }/js/client/client_newModifyModal.js"></script>