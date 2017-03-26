package com.cw.cramer.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author wicks
 */
public class DateTimeUtils {
	
	public static SimpleDateFormat dateFormater_YYYYMMDDhhmmss = new SimpleDateFormat("YYYYMMDDhhmmss");
	
	/**
	 * 获取当前时间（YYYYMMDDhhmmss）
	 * @return
	 */
	public static String getStrCurrentTime(){
		Date date = new Date();
		return dateFormater_YYYYMMDDhhmmss.format(date);
	}

}
