package com.kongbig.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author kongbig
 *
 */
public class OAuthUtil {

	/**
	 * 将目标地址拼接到地址栏后
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void oauth(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getServletPath();
		StringBuilder sb = new StringBuilder();
		sb.append("/sso/oauth?returnURI=" + uri);
		request.getRequestDispatcher(sb.toString()).forward(request, response);
	}

}
