package com.kongbig.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类，加密后转换为Base64格式
 */
public final class MD5 {

	private static MessageDigest digest;

	static {
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 Algorithm Not Supported", e);
		}

	}

	/**
	 * 加密方法
	 * 
	 * @param text
	 * @return
	 */
	public static String encrypt(String text) {
		try {
			return Base64.encodeBytes(digest.digest(text.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("unsupported encoding : UTF-8", e);
		}
	}
}
