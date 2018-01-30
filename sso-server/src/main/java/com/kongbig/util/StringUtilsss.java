package com.kongbig.util;

/**
 * 
 * @author kongbig
 *
 */
public class StringUtilsss {

	public static String getIP(String redirectURI) {
		String[] str = redirectURI.split(":");
		return str[1].replace("/", "");
	}

}
