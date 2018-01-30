package com.kongbig.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kongbig.model.OAuthUser;
import com.kongbig.security.SecurityContext;
import com.kongbig.security.SecurityProvider;

public class SecurityFilter implements Filter {

	private String ignorePattern = "";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse responce = (HttpServletResponse) resp;
		// 创建当前线程的安全上下文
		SecurityProvider provider = new SecurityProvider();
		SecurityContext securityContext = new SecurityContext(request, responce, provider);
		SecurityContext.threadLocal.set(securityContext);

		if (isIgnore(request)) {
			chain.doFilter(req, resp);
			return; //
		} else {
			OAuthUser oAuthUser = SecurityContext.getCurrentOAuthUser();
			if (oAuthUser == null) {
				// 1.还没登陆的情况
				responce.sendRedirect(request.getContextPath() + "/common/login.jsp");
			} else {
				// 2.登陆后的情况
				chain.doFilter(req, resp);
				return; //
			}
		}
	}

	/**
	 * 匿名访问地址(不用登陆,直接访问.)
	 * 
	 * @param request
	 * @return
	 */
	private boolean isIgnore(HttpServletRequest request) {
		String uri = request.getServletPath();
		System.out.println("isIgnore()中输出:" + uri);
		String[] ignorePatterns = ignorePattern.split("\\|");
		for (String pattern : ignorePatterns) {
			if (pattern.indexOf("*") < 0) {
				if (uri.equals(pattern)) {
					return true;
				}
			} else if (pattern.startsWith("*")) {
				pattern = pattern.replace("*", "");
				if (uri.endsWith(pattern)) {
					return true;
				}
			} else if (pattern.endsWith("*")) {
				pattern = pattern.replace("*", "");
				if (uri.startsWith(pattern)) {
					return true;
				}

			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		ignorePattern = config.getInitParameter("ignorePattern");
	}

}
