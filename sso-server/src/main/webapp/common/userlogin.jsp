<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="">
<meta name="description" content="">
<title>电子商城登陆界面</title>
<!-- H+ css -->
<link href="/sso-server/static/hplus/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="/sso-server/static/hplus/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="/sso-server/static/hplus/animate.css" rel="stylesheet">
<link href="/sso-server/static/hplus/style.css?v=4.1.0" rel="stylesheet">
<script type="text/javascript" src="/sso-server/static/lib/jquery/1.9.1/jquery.min.js"></script>
</head>
<body class="gray-bg">

	<div class="middle-box text-center loginscreen  animated fadeInDown">
		<div>
			<div>
				<h1 class="logo-name">H+</h1>
			</div>
			<h3>欢迎使用 H+</h3>

			<form id="loginform" class="m-t" action="/sso-server/LoginController/shopuserlogin.do" method="post">
				<%-- 重定向uri --%>
				<input type="hidden" value="" id="redirectURI" name="redirectURI">
				<%-- 目标地址 --%>
				<input type="hidden" value="" id="returnURI" name="returnURI">
				<%-- 临时令牌 --%>
				<input type="hidden" value="" id="tempToken" name="tempToken">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="账号" id="account" name="account" required="">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="password" name="password" placeholder="密码" required="">
				</div>
				<button type="submit" class="btn btn-primary block full-width m-b">登录</button>
			</form>
		</div>
	</div>

</body>
<script type="text/javascript">
$(document).ready(function(){
	var src = window.location.href;
	console.debug('src = ' + src);
	
	var array = src.split('=');
	var array2 = array[1].split('&');
	$('#redirectURI').val(array2[0]);
	console.debug('redirectURI = ' + array2[0]);
	
	var array3 = array[2].split('&');
	$('#returnURI').val(array3[0]);
	console.debug('returnURI = ' + array3[0]);
	
	$('#tempToken').val(array[3]);
	console.debug('tempToken = ' + array[3]);
});
</script>
</html>