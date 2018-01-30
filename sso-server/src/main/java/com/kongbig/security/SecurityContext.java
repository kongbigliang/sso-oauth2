package com.kongbig.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kongbig.model.OAuthUser;
import com.kongbig.util.MD5;

public class SecurityContext {

	/**
	 * 线程副本存储,可以缓存当前线程的数据,线程与线程之间的数据不存在任何的影响
	 */
	// 一定要做成静态的、单例！缓存每一个用户的数据！
	public static ThreadLocal<SecurityContext> threadLocal = new ThreadLocal<SecurityContext>();

	private SecurityProvider securityProvider;
	private HttpServletRequest request;
	private HttpServletResponse response;

	/*
	 * 无参构造器
	 */
	public SecurityContext() {
		super();
	}

	/*
	 * 带全参的构造器
	 */
	public SecurityContext(HttpServletRequest request, HttpServletResponse response,
			SecurityProvider securityProvider) {
		super();
		this.request = request;
		this.response = response;
		this.securityProvider = securityProvider;
	}

	/*
	 * HttpServletRequest 和 HttpServletResponse 的setter和getter方法
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 获取安全提供器,用来获取与用户相关的数据(getter方法)
	 *
	 * @return
	 */
	public SecurityProvider getSecurityProvider() {
		return securityProvider;
	}

	/**
	 * 设置安全提供器(setter方法)
	 *
	 * @return
	 */
	public void setSecurityProvider(SecurityProvider securityProvider) {
		this.securityProvider = securityProvider;
	}

	/**
	 * 
	 * @param context
	 */
	public void setThreadLocal(SecurityContext securityContext) {
		// void set(Object value) 将此线程局部变量的当前线程副本中的值设置为指定值
		threadLocal.set(securityContext);
	}

	public static SecurityContext getSecurityContext() {
		// Object get() 返回此线程局部变量的当前线程副本中的值
		return threadLocal.get();
	}

	/**
	 * 加密密码的方法
	 * 
	 * @param str
	 * @return
	 */
	public static String encryptPassword(String str) {
		return MD5.encrypt(str);
	}

	/**
	 * 获取当前的用户数据(从session中获取)
	 *
	 * @return
	 */
	public static OAuthUser getCurrentOAuthUser() {
		// 从threadLocal中获取安全上下文
		SecurityContext securityContext = threadLocal.get();
		// 从安全上下文中获取安全提供器中的当前用户(参数是当前安全上下文的request属性)
		return securityContext.getSecurityProvider().getCurrentOAuthUser(securityContext.request);
	}

	/**
	 * 设置当前用户的数据到session中
	 * 
	 * @param oAuthUser
	 */
	public static void setCurrentOAuthUser(OAuthUser oAuthUser) {
		SecurityContext securityContext = threadLocal.get();
		securityContext.getSecurityProvider().setCurrentOAuthUser(oAuthUser, securityContext.request);
	}

}
