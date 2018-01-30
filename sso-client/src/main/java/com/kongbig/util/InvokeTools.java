package com.kongbig.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeTools {

	public static Object invoke(String className, String method, Class[] type, Object[] params) {
		Object value = null;
		try {
			Class clz = Class.forName(className);
			Method methodObj = clz.getMethod(method, type);
			Object o = clz.newInstance();
			value = methodObj.invoke(o, params);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			return value;
		}
	}

}
