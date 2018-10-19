<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>三草两木</title>
	<!-- 导入bootstrap.min.css -->
	<link rel="stylesheet" type="text/css" href="${basePath }/bootstrap/css/bootstrap.min.css">
	<!-- 移动设备优先,确保移动端也能良好的展示 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 导入jQuery -->
	<script type="text/javascript" src="${basePath }/jquery/jquery-3.3.1.min.js"></script>
	<!-- 导入自定义样式CSS文件 -->
	<link rel="stylesheet" type="text/css" href="${basePath }/assets/css/assets-pygments.css">
	<!-- 导入分页插件css -->
	<link rel="stylesheet" href="${basePath }/pagination/css/jquery.pagination.css" />
	<!-- 导入bootstrap时间控件css -->
	<link rel="stylesheet" type="text/css" href="${basePath }/bootstrap/css/bootstrap-datetimepicker.min.css">
	<!-- 导入文件上传css -->
	<link rel="stylesheet" href="${basePath }/fileinput/css/fileinput.min.css" />
	<!-- 引入阿里云字符图标 -->
	<!-- <link rel="stylesheet" href="//at.alicdn.com/t/font_867816_7bhe5gy6gwk.css"> -->
	
</head>
<body>
	<%@include file="navbar.jsp" %>
	<div class="row" >
		<%@include file="menu.jsp" %>
		<!-- 核心内容 -->
		<div id="context-core" class="col-md-10">
			<img src="saselomo.jpg" class="" height="620px" width="99%" id="mainPage">
		</div>
	</div>
	<!-- 导入bootstrap.min.js -->
	<script type="text/javascript" src="${basePath }/bootstrap/js/bootstrap.min.js"></script>
	<!-- 导入自定义js文件 -->
	<script type="text/javascript" src="${basePath }/assets/js/assets-pygments.js"></script>
	<!-- 导入bootstrap时间控件js -->
	<script type="text/javascript" src="${basePath }/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
	<!-- 导入分页插件js -->
	<script type="text/javascript" src="${basePath }/pagination/js/jquery.pagination.min.js"></script>
	<!-- 导入文件上传js -->
	<script src="fileinput/js/fileinput.min.js"></script>
	<script src="fileinput/js/fileinput_locale_zh.js"></script>
	<!-- 导入自定义工具 -->
	<script type="text/javascript" src="${basePath }/js/common/myUtil.js"></script>
	
	<!-- 大号新建 | 修改 Modal -->
	<div class="modal fade" id="newModifyModal" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					
		      	</div>
			</div>
		</div>
	</div>
	<!-- 正常大小新建 | 修改 Modal 收货单、售货单模块方便使用  -->
	<div class="modal fade" id="newModifyModalBills" tabindex="-1" style="margin-top: 80px;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					
		      	</div>
			</div>
		</div>
	</div>
	<!-- 详情 Modal -->
	<div class="modal fade" id="detailsModal" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					
		      	</div>
			</div>
		</div>
	</div>
	<!-- 删除 Modal -->
	<div class="modal fade" id="deleteModal" tabindex="-1" style="margin-top: 100px;">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-body">
					<h4><span class="glyphicon glyphicon-question-sign text-warning" style="font-size: 30px;"></span><span class="deletePromptMessage"></span></h4>
					<form class="form-inline" action="javascript:;">
						<div class="row" style="position: relative;top: 10px;height: 60px;">
							<div class="col-md-6 col-md-offset-6">
								<input type="button" value="取消" class="btn btn-sm btn-default cancelBtn">
								<input type="button" class="btn btn-sm btn-danger okBtn" value="确认">
							</div>
						</div>
					</form>
		      	</div>
			</div>
		</div>
	</div>
	
	<!-- 导入 Modal -->
	<div class="modal fade" id="importModal" tabindex="-1" style="top: 80px;">
		<div class="modal-dialog">
			<div class="modal-content">
				
			</div>
		</div>
	</div>
	<!-- 警告框 -->
	<div id="alertBox">
		<div class="alert alert-danger" id="myAlert">
			<button type="button" class="close">
				<span aria-hidden="true">&times;</span>
			</button>
			<p></p>
			<p id="alertInfo" class="hintFont"></p>
		</div>
	</div>
	<!-- 导入全局共用js -->
	<script type="text/javascript" src="${basePath }/js/common/index.js"></script>
	
</body>
</html>


