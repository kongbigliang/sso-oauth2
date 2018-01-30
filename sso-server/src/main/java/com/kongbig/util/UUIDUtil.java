package com.kongbig.util;

import java.util.UUID;

/**
 * 
 * @author kongbig
 *
 */
public class UUIDUtil {

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

}
