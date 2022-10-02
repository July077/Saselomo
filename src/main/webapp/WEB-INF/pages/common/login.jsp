<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<!-- 导入自定义样式CSS文件 -->
	<link rel="stylesheet" type="text/css" href="${basePath }/assets/css/assets-pygments.css">
	<!-- 导入jQuery -->
	<script type="text/javascript" src="${basePath }/jquery/jquery-3.3.1.min.js"></script>
</head>
<body>
	<div class="login-box" id="rrapp">
	  <div class="login-logo">
	  	<img src="homepage.jpg" alt="logo" />
	  </div>
	  <div class="login-box-body">
	      <p class="login-box-msg"><b>用户登录</b></p>
	       <div class="alert alert-danger alert-dismissible" style="display:none">
	        <h4 style="margin-bottom: 0px;"><i class="fa fa-exclamation-triangle" id="errorMessage"></i></h4>
	      </div>
	      <div class="form-group has-feedback">
	          <input type="text" class="form-control" id="username" placeholder="账号">
	          <span class="glyphicon glyphicon-user form-control-feedback"></span>
	      </div>
	      <div class="form-group has-feedback">
	          <input autocomplete="off" type="password" class="form-control" id="userpwd" placeholder="密码">
	          <span class="glyphicon glyphicon-lock form-control-feedback"></span>
	      </div>

	      <div class="row">
	        <div class="col-xs-12">
	          <button type="button" class="btn btn-primary btn-block btn-flat" id="btn_login" >登录</button>
	        </div>
	      </div>
	  </div>
	</div>
	<script type="text/javascript">
		var SUCCESS = 1;
		$(function(){
			//回车按钮
			$(document).keyup(function(event){
				if(event.keyCode == 13){
					$('#btn_login').trigger('click');
				}
			});
			//点击登录按钮
			$('#btn_login').click(loginCheck);	
		});
		function loginCheck(){
			var userName = $('#username').val();//密码
			var userPwd = $('#userpwd').val();//密码
			if(userName==''){//账号为空
				$('#errorMessage').parent().parent().css('display','block');
				$('#errorMessage').text('用户名不能为空！');
				return false;
			}
			if(userPwd==''){//密码为空
				$('#errorMessage').parent().parent().css('display','block');
				$('#errorMessage').text('密码不能为空！');
				return false;
			}
			//判断用户是否存在,密码是否正确
			var url = 'confirmUser.do';
			var params = {'username':userName,'userpwd':userPwd};
			$.post(url,params,function(result){
				if(result.state==SUCCESS){   //用户校验成功，跳转到主页面
					location.href='index.do';
				}else{
					$('#errorMessage').parent().parent().css('display','block');
					$('#errorMessage').text(result.message);
				}
			});
		}
	</script>
</body>
</html>
