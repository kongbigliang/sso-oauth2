package com.kongbig.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kongbig.model.AccessTokenModel;
import com.kongbig.model.User;
import com.kongbig.web.servlet.OAuthServlet;

@SuppressWarnings("serial")
public class ClientOauthServlet extends OAuthServlet {
	
	@Override
	public void loginSuccess(HttpServletRequest request, HttpServletResponse response,
			AccessTokenModel accessTokenModel) {
		User user = new User("username", "password");
		request.getSession().setAttribute("user", user);
		System.out.println("SSO登陆验证成功后的操作...");
	}

	@Override
	public void loginError(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("SSO登陆验证失败后的操作...");
	}


}
