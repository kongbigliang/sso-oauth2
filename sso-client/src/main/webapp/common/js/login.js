function login() {
	var user = $("#loginform").toJson();
	if (user.username == "" || user.password == "") {
		layer.alert("账号或者密码不可以为空！", {
			icon : 5
		});
		return false;
	} else {
		return true;
	}
}