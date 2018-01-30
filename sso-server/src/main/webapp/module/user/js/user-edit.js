$(function() {
	$('.skin-minimal input').iCheck({
		checkboxClass : 'icheckbox-blue',
		radioClass : 'iradio-blue',
		increaseArea : '20%'
	});

	$("#form-member-edit").Validform({
		tiptype : 2,
	});

});

function editOAuthUser(){
	var oAuthUser = $("#form-member-edit").toJson();
	console.debug(JSON.stringify(oAuthUser));
	$.ajax({
		url : Global.contextPath + "/oauthuser/updateOAuthUser.do",
		type : "post",
		dataType : "json",
		data : {
			jsonStr : JSON.stringify(oAuthUser)
		},
		success : function(data) {
			console.debug(data.data.id);
			// $("#id").val(data.data.id);
			parent.window.table.fnDraw(false);
			layer.alert('操作成功', {
				icon : 1,
				shade : 0.5,
				time : 3000
			});
		},
		error : function() {
			layer.alert('操作失败', {
				icon : 2,
				shade : 0.5,
				time : 3000
			});
		}
	});
	return false;
}