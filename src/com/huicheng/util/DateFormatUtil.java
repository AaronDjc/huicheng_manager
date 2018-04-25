package com.huicheng.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

	/**
	 * 年月日 时分秒
	 */
	public static final int YYYY_MM_DD_HH_MM_SS_FORMAT = 1;
	private static Format ymdhmsformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 年月日
	 */
	public static final int YYYY_MM_DD_FORMAT = 2;
	private static Format ymdformat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 日期格式化
	 * @param date		DateUtil.YYYY_MM_DD_HH_MM_SS_FORMAT / DateUtil.YYYY_MM_DD_FORMAT
	 * @param pattern
	 * @return
	 */
	public static String dateToString(Date date, int pattern) {
		if(pattern == 1) {
			return ymdhmsformat.format(date);
		} else {
			return ymdformat.format(date);
		}
	}
}
