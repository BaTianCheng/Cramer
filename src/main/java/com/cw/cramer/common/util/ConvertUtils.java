package com.cw.cramer.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;

/**
 * 数据类型转换工具类
 * @author wicks
 */
public final class ConvertUtils {

	/**
	 * 将字符串转换为Double
	 * @param str
	 * @return
	 */
	public static Double toDouble(String str) {
		if (Strings.isNullOrEmpty(str)) {
			return null;
		} else {
			return Double.valueOf(str);
		}
	}
	
	/**
	 * 将字符串转换为Integer
	 * @param str
	 * @return
	 */
	public static Integer toInteger(String str) {
		if (Strings.isNullOrEmpty(str)) {
			return 0;
		} else {
			return Integer.valueOf(str);
		}
	}

	/**
	 * 将小数保留两位
	 * @param num
	 * @return
	 */
	public static Double toDouble2(double num) {
		BigDecimal b = new BigDecimal(num);
		double newNum = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return newNum;
	}
	
	/**
	 * 转换字符串数组
	 * @param list
	 * @return
	 */
	public static List<String> toStrings(List<Integer> list){
		List<String> strs = new ArrayList<String>();
		if(list == null){
			return strs;
		} else {
			for(Object obj : list){
				strs.add(obj.toString());
			}
			return strs;
		}
	}

	/**
	 * 将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制 
	 * @param hexStr 
	 * @return 
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) {
			return null;
		}
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

}
