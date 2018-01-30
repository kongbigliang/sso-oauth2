package com.kongbig.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

	public static long getTimeMills(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = sdf.format(date);
		long millionSeconds = 0;
		try {
			millionSeconds = sdf.parse(str).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return millionSeconds / 1000;
	}

	/**
	 * 是否已过期
	 * 
	 * @param createTime:创建时间
	 * @param expiresIn:多少秒后过期
	 * @return
	 */
	public static boolean isOutOfDate(Date createTime, String expiresIn) {
		Integer _expiresIn = Integer.parseInt(expiresIn);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str1 = sdf.format(createTime);// 创建时间->毫秒
		String str2 = sdf.format(new Date());// 当前时间->毫秒

		long millionSeconds1 = 0;
		long millionSeconds2 = 0;
		try {
			millionSeconds1 = sdf.parse(str1).getTime();
			millionSeconds2 = sdf.parse(str2).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if ((millionSeconds1 / 1000) + _expiresIn < (millionSeconds2 / 1000)) {// 已过期
			return true;
		} else {
			return false;
		}

	}
	
}
