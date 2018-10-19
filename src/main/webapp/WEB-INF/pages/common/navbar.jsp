<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro"  uri="http://shiro.apache.org/tags"%>
<!-- 导航条 -->
<nav class="navbar">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="#" class="navbar-brand">
				<span class="glyphicon glyphicon-tree-conifer"></span>
				<span style="color: white;">三草两木</span>
			</a>
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#userSelect" aria-expanded="false" style="background: white;">
				<span class="icon-bar" style="background: #CCC;"></span>
				<span class="icon-bar" style="background: #CCC;"></span>
				<span class="icon-bar" style="background: #CCC;"></span>
			</button>
		</div>

		<div id="userSelect" class="collapse navbar-collapse">
			<p class="navbar-text navbar-right">
				<span style="color: #FFF" class="lead">欢迎您, </span>
				<span style="color: #FFF" class="lead" id="CurrentLoginUser"><shiro:principal property="name"></shiro:principal></span>
				<span style="margin-left: 80px;">
					<a href="logout.do">
						<i class="glyphicon glyphicon-off"></i>
						<!-- 引入阿里云图标 -->
						<!-- <i class="iconfont icon-tuichu3"></i> -->
						<span>退出</span>
					</a>
				</span>
			</p>
		</div>
	</div>
</nav>