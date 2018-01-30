<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>第三方接入sso-server</title>

<!-- 用户自定义js文件 -->
<script type="text/javascript" src="${contextPath }/common/client/js/switch-on-sso.js"></script>

</head>
<body>
	<h1 style="text-align:center;"><strong>第三方接入申请</strong></h1>
	<div class="pd-20">
		<form action="javascript:void(0);" onsubmit="return switchOnSso();"
			method="post" class="form form-horizontal" id="form-switch-on">
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>应用名：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value=""
						onblur="checkClientName();" id="clientName" name="clientName"
						datatype="*2-16" nullmsg="应用名不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>初始密码：</label>
				<div class="formControls col-5">
					<input type="password" placeholder="密码" autocomplete="off" value=""
						class="input-text" datatype="*6-20" nullmsg="密码不能为空"
						name="clientSecret" id="clientSecret">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>确认密码：</label>
				<div class="formControls col-5">
					<input type="password" placeholder="确认新密码" autocomplete="off"
						class="input-text" errormsg="您两次输入的新密码不一致！"
						datatype="*" nullmsg="请再输入一次新密码！" recheck="clientSecret"
						id="confirmSecret" name="confirmSecret">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>ip：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="" id="ip" name="ip"
						datatype="*2-16" nullmsg="ip不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>域名：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="" id="domainName"
						name="domainName" datatype="*2-16" nullmsg="域名不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<div class="col-9 col-offset-3">
					<input class="btn btn-primary radius" type="submit"
						value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</div>
	</div>
</body>
<script type="text/javascript">
	function checkClientName() {
		$.ajax({
			url : Global.contextPath + "/oauth/client/common/checkClientname.do",
			type : "post",
			dataType : "text",
			data : {
				clientName : $('#clientName').val(),

			},
			success : function(data) {
				console.debug(data);
				if (data == 'clientName_exist') {
					layer.alert('应用名已存在', {
						icon : 2,
						shade : 0.5
					});
				}
			},
		});
	}

</script>

</html>