package com.cw.cramer.common.util;

import java.io.InputStream;
import java.util.Properties;

import com.cw.cramer.common.constant.Dialect;

public final class SqlUtils {
	
	/**
	 * 数据库方言
	 */
	private static Dialect dialect = Dialect.oracle;
	
	//读取数据库方言
	static{
		try{
			Properties pro = new Properties();
			InputStream in = SqlUtils.class.getResourceAsStream("/jdbc.properties");
			pro.load(in);
			in.close();
			dialect = Dialect.of (Dialect.fromJdbcUrl(pro.getProperty("url")));
			
		} catch(Exception ex){
			LogUtils.error("文件读取失败，",ex);
		}
	}
	
	/**
	 * 获得序列前缀
	 * @return
	 */
	public static String getSequencePrefix(){
		if(dialect == Dialect.oracle){
			return "";
		} else if(dialect == Dialect.mysql){
			return "nextval('";
		} else {
			return "";
		}
		
	}
	
	/**
	 * 获得序列后缀
	 * @return
	 */
	public static String getSequenceSuffix(){
		if(dialect == Dialect.oracle){
			return ".nextval from dual";
		} else if(dialect == Dialect.mysql){
			return "')";
		} else {
			return "";
		}
	}

}
