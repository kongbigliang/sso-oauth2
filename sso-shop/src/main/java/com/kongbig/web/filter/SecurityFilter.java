package com.kongbig.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kongbig.util.OAuthUtil;

/**
 * 
 * @author kongbig
 *
 */
public class SecurityFilter implements Filter {

	private String ignorePattern = "";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ignorePattern = filterConfig.getInitParameter("ignorePattern");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		if (isIgnore(request)) {
			chain.doFilter(req, resp);
		} else if (request.getRequestURI().contains("/sso/oauth")) {
			chain.doFilter(req, resp);
		} else if (request.getSession().getAttribute("user") != null) {
			chain.doFilter(req, resp);
		} else {
			OAuthUtil.oauth(request, response);
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
	public void destroy() {

	}

}
