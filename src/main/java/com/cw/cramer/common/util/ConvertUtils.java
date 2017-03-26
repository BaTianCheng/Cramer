package com.cw.cramer.common.util;

import java.math.BigDecimal;

/**
 * 数据类型转换工具类
 * @author wicks
 */
public class ConvertUtils {

	/**
	 * 将字符串转换为double
	 * @param str
	 * @return
	 */
	public static double toDouble(String str){
		if(str == null || "".equals(str)){
			return 0;
		} else {
			return Double.valueOf(str);
		}
	}
	
	/**
	 * 将小数保留两位
	 * @param num
	 * @return
	 */
	public static double toDouble2(double num){
		BigDecimal b = new BigDecimal(num);
		double newNum = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return newNum;
	}
	
}
