<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<html>
<head>
	<title>Welcome!</title>
	<!-- 导入jQuery -->
	<script type="text/javascript" src="${basePath }/jquery/jquery-3.2.1.min.js"></script>
	<style type="text/css">
		body{
			background: #F8F8F8;
		}
		img{
			display: block;
			margin: 100px auto;
		}
		p{
			display: block;
			width: 400px;
			margin: 10px auto;
			font-size: 20px;
		}
	</style>
	<script type="text/javascript">
		setTimeout(function(){
			window.location.href = "index.do";
		}, 3000);
	</script>
</head>
<body>
	<div>
		<img src="${basePath }/homepage.jpg">
		<p><b>愿每一个夜晚都满怀期待等待明天...</b></p>
	</div>
</body>
</html>