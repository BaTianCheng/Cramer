package com.cw.cramer.common.util.file;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.cw.cramer.common.util.LogUtils;

/**
 * 属性文件工具类
 * @author wicks
 */
public class PropertiesUtils {

	/**
	 * 读取属性文件
	 * @param path
	 * @return
	 */
	public static Properties getProperties(String path){
		Properties pro = new Properties();
		try{
			FileInputStream in = new FileInputStream(path);
			pro.load(in);
			in.close();
		}
		catch(Exception ex){
			LogUtils.error("读取属性文件失败", ex);
		}
		return pro;
	}
	
	/**
	 * 读取属性文件
	 * @param path
	 * @return
	 */
	public static Properties getProperties(InputStream in){
		Properties pro = new Properties();
		try{
			pro.load(in);
			in.close();
		}
		catch(Exception ex){
			LogUtils.error("读取属性文件失败", ex);
		}
		return pro;
	}
	
}
