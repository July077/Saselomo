<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 新建用户信息 -->
<form class="form-horizontal" action="javascript:;">
	<fieldset>
		<legend><span>基础信息</span></legend>
		<table class="wholeSituationModal">
			<tr>
				<td class="text-right">
					<span class="glyphicon glyphicon-asterisk asteriskIcon"></span>
					<span>用户名:</span>
				</td>
				<td>
					<div class="col-md-8">
						<input maxlength="50" type="text" name="userUName" id="userUNameModify" class="form-control input-sm" placeholder="登录账号">
					</div>
					
				</td>
			</tr>
			<tr>
				<td class="text-right">
					<span class="glyphicon glyphicon-asterisk asteriskIcon"></span>
					<span>密码:</span>
				</td>
				<td>
					<div class="col-md-8">
						<input maxlength="50" type="password" name="password" id="userPasswordModify" class="form-control input-sm" placeholder="密码">
					</div>
				</td>
			</tr>
			<tr style="display: none;">
				<td class="text-right">
					<span class="glyphicon glyphicon-asterisk asteriskIcon"></span>
					<span>新密码:</span>
				</td>
				<td>
					<div class="col-md-8">
						<input maxlength="50" type="password" name="password" id="userNewPasswordModify" class="form-control input-sm" placeholder="新密码">
					</div>
				</td>
			</tr>
			<tr>
				<td class="text-right">
					<span class="glyphicon glyphicon-asterisk asteriskIcon"></span>
					<span>姓名:</span>
				</td>
				<td>
					<div class="col-md-8">
						<input maxlength="100" type="text" name="userName" id="userNameModify" class="form-control input-sm" placeholder="姓名">
					</div>
					
				</td>
			</tr>
			<tr>
				<td class="text-right">
					<span>手机号:</span>
				</td>
				<td>
					<div class="col-md-8">
						<input type="text" name="mobile" id="userMobileModify" placeholder="手机号" class="form-control input-sm">
					</div>
				</td>
			</tr>
			<tr>
				<td class="text-right">
					<span>邮箱:</span>
				</td>
				<td>
					<div class="col-md-8">
						<input maxlength="50" type="tel" name="email" id="userEmailModify" class="form-control input-sm" placeholder="邮箱" >
					</div>
				</td>
			</tr>
			<tr>
				<td class="text-right">
					<span class="glyphicon glyphicon-asterisk asteriskIcon"></span>
					<span>选择角色:</span>
				</td>
				<td>
					<span class="hintFont">攻击中...</span>
				</td>
			</tr>
		</table>
	</fieldset>
		
	<div class="row" style="position: relative;top: 10px;height: 60px;">
		<div class="col-md-4 col-md-offset-8">
			<input type="button" value="取消" class="btn btn-sm btn-default cancelBtn">
			<input type="button" class="btn btn-sm btn-danger userOk" value="确认">
		</div>
	</div>
</form>

<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 导入用户新建修改表单js -->
<script type="text/javascript" src="${basePath }/js/system/user/user_newModifyModal.js"></script>
