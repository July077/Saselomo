<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<!-- 导入分页列表js -->
<script type="text/javascript" src="${basePath }/js/common/pagination.js"></script>
<div class="paginationBox">
	<div class="box">
		<div id="pagination" class="page fl"></div>
	</div>
</div>

