<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>编辑用户</title>

<!-- 自定义js文件 -->
<script type="text/javascript" src="${contextPath }/module/user/js/user-edit.js"></script>

</head>
<body>
	<div class="pd-20">
		<form action="javascript:void(0);" onsubmit="return editOAuthUser();" method="post" class="form form-horizontal" id="form-member-edit">
			<input type="hidden" name="id" id="id" value="${oAuthUser.id }" />
			<%-- <c:if test="${empty oAuthUser.id }"> --%>
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>用户名：</label>
					<div class="formControls col-5">
						<input type="text" class="input-text" readonly="readonly" value="${oAuthUser.username }" placeholder="" id="username" name="username" datatype="*2-16" nullmsg="用户名不能为空">
					</div>
					<div class="col-4"></div>
				</div>
			<%-- </c:if> --%>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>性别：</label>
				<div class="formControls col-5 skin-minimal">
					<div class="radio-box">
						<input type="radio" id="sex-1" name="sex" datatype="*"
							nullmsg="请选择性别！" value="001" ${oAuthUser.sex=='001'?'checked':''}>
						<label for="sex-1">男</label>
					</div>
					<div class="radio-box">
						<input type="radio" id="sex-2" name="sex" value="002"
							${oAuthUser.sex=='002'?'checked':''}> <label for="sex-2">女</label>
					</div>
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>手机：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${oAuthUser.phone }" placeholder="" id="phone" name="phone" datatype="m" nullmsg="手机不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">状态：</label>
				<div class="formControls col-5 skin-minimal">
					<div class="radio-box">
						<input type="radio" id="status-1" name="status" datatype="*" nullmsg="请选择状态！" value="001" ${oAuthUser.status=='001'?'checked':''}>
						<label for="status-1">已启用</label>
					</div>
					<div class="radio-box">
						<input type="radio" id="status-2" name="status" value="002" ${oAuthUser.status=='002'?'checked':''}> 
						<label for="status-2">未启用</label>
					</div>
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">备注：</label>
				<div class="formControls col-5">
					<textarea name="remark" cols="" rows="" class="textarea" placeholder="说点什么..." datatype="*2-100" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="textarealength(this,100)">${oAuthUser.remark }</textarea>
					<p class="textarea-numberbar">
						<em class="textarea-length">0</em>/100
					</p>
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
</html>