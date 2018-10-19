<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
	<h4 class="modal-title">批量导入产品信息</h4>
</div>
<div class="modal-body">
	<form class="form-inline col-md-offset-1" method="post" enctype="multipart/form-data">
		<!-- 一个简单的文件表单字段 -->
		<input type="hidden" id="AttachGUID" name="AttachGUID" />
		<!-- name的值要和controller文件中MultipartFile的@RequestParam筛选的值一致 --> 
		<input id="excelProduct" type="file" name="mFile">  
	</form>
</div>
<div class="modal-footer">
	<div class="col-md-4">
		<a href="product/downloadExcelTemplate.do">
			<span style="font-size: 18px;"><b>下载导入模板...</b></span>
		</a>
	</div>
</div>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 产品信息导入js -->
<script type="text/javascript" src="${basePath }/js/product/product_importModal.js"></script>