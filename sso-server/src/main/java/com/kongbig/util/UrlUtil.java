package com.kongbig.util;

import java.io.UnsupportedEncodingException;

/**
 * url转码、解码
 * 
 * @author kongbig
 *
 */
public class UrlUtil {
	private final static String ENCODE = "UTF-8";

	/**
	 * URL 解码
	 * 
	 * @param str
	 * @return
	 */
	public static String getURLDecoderString(String str) {
		String result = "";
		if (null == str) {
			return "";
		}
		try {
			result = java.net.URLDecoder.decode(str, ENCODE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * URL 转码
	 * 
	 * @param str
	 * @return
	 */
	public static String getURLEncoderString(String str) {
		String result = "";
		if (null == str) {
			return "";
		}
		try {
			result = java.net.URLEncoder.encode(str, ENCODE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "http://127.0.0.1:8080/sso-client/OAuthController/hasLegalToken.do";
		System.out.println(getURLEncoderString(str));
		System.out.println(getURLDecoderString(str));

	}

}
