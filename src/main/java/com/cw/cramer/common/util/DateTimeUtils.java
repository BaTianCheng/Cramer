package com.cw.cramer.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author wicks
 */
public final class DateTimeUtils {
	
	public static SimpleDateFormat dateFormater_YYYYMMDDhhmmss = new SimpleDateFormat("YYYYMMDDhhmmss");
	public static SimpleDateFormat dateFormater_YYYY_MM_DD_hh_mm_ss = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
	
	/**
	 * 获取当前时间字符串形式（YYYYMMDDhhmmss）
	 * @return
	 */
	public static String getStrCurrentTime(){
		Date date = new Date();
		return dateFormater_YYYYMMDDhhmmss.format(date);
	}
	
	/**
	 * 获取时间戳的字符串形式（YYYYMMDDhhmmss）
	 * @param timeStamp
	 * @return
	 */
	public static String getStrTime(long timeStamp){
		Date date = new Date(timeStamp);
		return dateFormater_YYYYMMDDhhmmss.format(date);
	}
	
	/**
	 * 获取当前时间字符串描述形式（YYYY-MM-DD hh:mm:ss）
	 * @return
	 */
	public static String getDescCurrentTime(){
		Date date = new Date();
		return dateFormater_YYYY_MM_DD_hh_mm_ss.format(date);
	}
	
	/**
	 * 获取时间戳的字符串描述形式（YYYY-MM-DD hh:mm:ss）
	 * @param timeStamp
	 * @return
	 */
	public static String getDescTime(long timeStamp){
		Date date = new Date(timeStamp);
		return dateFormater_YYYY_MM_DD_hh_mm_ss.format(date);
	}
	
	/**
	 * 获取时间戳的字符形式
	 * @param timeStamp
	 * @param strFormat
	 * @return
	 */
	public static String getStrTime(long timeStamp, String strFormat){
		Date date = new Date(timeStamp);
		SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
		return dateFormat.format(date);
	}

}
