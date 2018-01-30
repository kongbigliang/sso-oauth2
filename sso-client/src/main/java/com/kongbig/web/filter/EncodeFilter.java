package com.kongbig.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodeFilter implements Filter {

	private String code;

	/**
	 * 这是过滤器销毁的回调方法，在web容器关闭的时候执行
	 */
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding(code);
		resp.setCharacterEncoding(code);
		chain.doFilter(req, resp);
	}

	/**
	 * 这是过滤器的初始化方法，只会被初始化一次
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		code = config.getInitParameter("code");
	}

}
