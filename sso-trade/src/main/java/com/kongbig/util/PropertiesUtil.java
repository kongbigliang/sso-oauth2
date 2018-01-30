package com.kongbig.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	private static Properties props = new Properties();
	static{
		ClassLoader loader = PropertiesUtil.class.getClassLoader();
		InputStream is = loader.getResourceAsStream("properties/resource.properties");
		try {
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		return props.getProperty(key);
	}

}
