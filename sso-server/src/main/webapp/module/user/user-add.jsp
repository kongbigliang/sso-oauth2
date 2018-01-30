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
<title>添加用户</title>

<!-- 用户自定义js文件 -->
<script type="text/javascript" src="${contextPath }/module/user/js/user-add.js"></script>

</head>
<body>
	<div class="pd-20">
		<form action="javascript:void(0);" onsubmit="return addOAuthUser();" method="post" class="form form-horizontal" id="form-member-add">
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>用户名：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="" onblur="checkName();" id="username" name="username" datatype="*2-16" nullmsg="用户名不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>性别：</label>
				<div class="formControls col-5 skin-minimal">
					<div class="radio-box">
						<input type="radio" id="sex-1" name="sex" datatype="*" nullmsg="请选择性别！" value="001">
						<label for="sex-1">男</label>
					</div>
					<div class="radio-box">
						<input type="radio" id="sex-2" name="sex" value="002"> <label for="sex-2">女</label>
					</div>
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>手机：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="" placeholder="" id="phone" name="phone" datatype="m" nullmsg="手机不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">备注：</label>
				<div class="formControls col-5">
					<textarea name="remark" cols="" rows="" class="textarea" placeholder="说点什么..." datatype="*2-100" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="textarealength(this,100)"></textarea>
					<p class="textarea-numberbar">
						<em class="textarea-length">0</em>/100
					</p>
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<div class="col-9 col-offset-3">
					<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</div>
	</div>
</body>
<script type="text/javascript">
function checkName(){
	$.ajax({
		url : Global.contextPath + "/oauthuser/checkUsername.do",
		type : "post",
		dataType : "text",
		data : {
			username : $('#username').val()
		},
		success : function(data) {
			console.debug(data);
			parent.window.table.fnDraw(false);
			if (data == 'username_exist') {
				layer.alert('用户名已存在', {
					icon : 2,
					shade : 0.5
				});
			}
		},
	});
}
</script>

</html>