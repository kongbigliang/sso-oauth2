package com.kongbig.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ClientLogoutServlet extends LogoutServlet {

	@Override
	public void logoutSuccess(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("user", null);
		System.out.println("退出成功后的操作...");
	}

	@Override
	public void logoutError(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("退出失败后的操作...");
	}

}
