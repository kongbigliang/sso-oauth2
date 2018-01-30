$(function() {
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-switch-on").Validform({
		tiptype:2,
		callback:function(form){
			form[0].submit();
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});

});

function switchOnSso() {
	var clientMessage = $("#form-switch-on").toJson();
	console.debug(JSON.stringify(clientMessage));
	$.ajax({
		url : Global.contextPath + "/oauth/client/common/addClient.do",
		type : "post",
		dataType : "json",
		data : {
			clientMessage : JSON.stringify(clientMessage)
		},
		success : function(data) {
			console.debug(data.code);
			if (data.code == '002') {
				// alert(data.code);
				layer.alert('应用已存在!', {
					icon : 5,
					shade : 0.5,
					time : 3000
				});
				return;
			}
			layer.alert('操作成功!应用id： ' + data.data.clientID, {
				icon : 1,
				shade : 0.5
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