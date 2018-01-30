package com.kongbig.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kongbig.util.CookieUtils;

/**
 * 
 * @author kongbig
 *
 */
@SuppressWarnings("serial")
public abstract class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logout(req, resp);
	}

	/**
	 * 成功后的回调方法
	 * 
	 * @param req
	 * @param resp
	 * @param tokenNew
	 */
	public abstract void logoutSuccess(HttpServletRequest req, HttpServletResponse resp);

	/**
	 * 失败后的回调方法
	 * 
	 * @param req
	 * @param resp
	 */
	public abstract void logoutError(HttpServletRequest req, HttpServletResponse resp);

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		if (!CookieUtils.delCookieValue(response, "accessToken")
				|| !CookieUtils.delCookieValue(response, "refreshToken")) {
			logoutError(request, response);
		}
		logoutSuccess(request, response);
	}

}
