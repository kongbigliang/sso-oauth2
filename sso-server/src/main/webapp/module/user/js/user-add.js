$(function() {
	$('.skin-minimal input').iCheck({
		checkboxClass : 'icheckbox-blue',
		radioClass : 'iradio-blue',
		increaseArea : '20%'
	});

	$("#form-member-add").Validform({
		tiptype : 2,
	});

});

function addOAuthUser() {
	var oAuthUser = $("#form-member-add").toJson();
	console.debug(JSON.stringify(oAuthUser));
	$.ajax({
		url : Global.contextPath + "/oauthuser/addOAuthUser.do",
		type : "post",
		dataType : "json",
		data : {
			jsonStr : JSON.stringify(oAuthUser)
		},
		success : function(data) {
			console.debug(data.code);
			parent.window.table.fnDraw(false);
			if (data.code == '002') {
				// alert(data.code);
				layer.alert('用户已存在!', {
					icon : 5,
					shade : 0.5,
					time : 3000
				});
				return;
			}
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