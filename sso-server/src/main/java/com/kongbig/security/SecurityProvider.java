package com.kongbig.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kongbig.model.OAuthUser;

/**
 * 
 * @author kongbig
 *
 */
public class SecurityProvider {

	// 当前用户
	public static final String CURRENT$OAUTH2USER = "CURRENT$OAUTH2USER";

	/**
	 * 专门用来获取session的方法;
	 * 
	 * @param request
	 * @return
	 */
	public HttpSession getSession(HttpServletRequest request) {
		// 从HttpServletRequest中获取session
		return request.getSession();
	}

	/**
	 * 退出功能:销毁session即可;
	 * 
	 * @param request
	 */
	public void logout(HttpServletRequest request) {
		HttpSession session = getSession(request);
		if (session != null) {
			session.invalidate();
		}
	}

	/**
	 * 将当前用户设置到session中
	 * @param oAuthUser
	 * @param request
	 */
	public void setCurrentOAuthUser(OAuthUser oAuthUser, HttpServletRequest request) {
		HttpSession session = getSession(request);
		session.setAttribute(CURRENT$OAUTH2USER, oAuthUser);
	}

	/**
	 * 从session中获取当前用户的对象;参数为HttpServletRequest
	 * 
	 * @param request
	 * @return
	 */
	public OAuthUser getCurrentOAuthUser(HttpServletRequest request) {
		HttpSession session = getSession(request);
		Object obj = session.getAttribute(CURRENT$OAUTH2USER);
		if (obj == null) {
			return null;
		} else {
			return (OAuthUser) obj;
		}
	}

}
