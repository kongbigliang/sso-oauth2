package com.kongbig.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kongbig.model.AccessTokenModel;

/**
 * 
 * Cookie 工具类
 *
 */
public final class CookieUtils {

	/**
	 * 
	 * @param response
	 * @param path
	 * @param time
	 * @param accessToken
	 */
	public static void setCookieValue(HttpServletResponse response, String path, Integer time,
			AccessTokenModel accessToken) {

		/*
		 * P3P解决跨域
		 */
		response.addHeader("P3P", "CP=CAO PSA OUR");
		// response.setHeader("P3P","CP=\"NON DSP COR CURa ADMa DEVa TAIa PSAa
		// PSDa IVAa IVDa CONa HISa TELa OTPa OUR UNRa IND UNI COM NAV INT DEM
		// CNT PRE LOC\"");

		// accessToken及账号
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("accessToken", accessToken.getAccessToken());
		map1.put("account", accessToken.getAccount());
		Cookie cookie1 = new Cookie("accessToken", JsonTools.mapToJson(map1).toString());
		cookie1.setPath(path);
		cookie1.setMaxAge(time);
		response.addCookie(cookie1);

		// refreshToken及账号
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("refreshToken", accessToken.getRefreshToken());
		map2.put("account", accessToken.getAccount());
		Cookie cookie2 = new Cookie("refreshToken", JsonTools.mapToJson(map2).toString());
		cookie2.setPath(path);
		cookie2.setMaxAge(86400);// refreshToken默认一天
		response.addCookie(cookie2);
	}

	/**
	 * 得到Cookie的值, 不编码
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		return getCookieValue(request, cookieName, false);
	}

	/**
	 * 得到Cookie的值,
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
		Cookie[] cookieList = request.getCookies();
		if (cookieList == null || cookieName == null) {
			return null;
		}
		String retValue = null;
		try {
			for (int i = 0; i < cookieList.length; i++) {
				if (cookieList[i].getName().equals(cookieName)) {
					if (isDecoder) {
						retValue = URLDecoder.decode(cookieList[i].getValue(), "UTF-8");
					} else {
						retValue = cookieList[i].getValue();
					}
					break;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return retValue;
	}

	/**
	 * 销毁cookie
	 * 
	 * @param response
	 * @param tokenName
	 * @return
	 */
	public static boolean delCookieValue(HttpServletResponse response, String tokenName) {
		try {
			Cookie cookie = new Cookie(tokenName, "");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
