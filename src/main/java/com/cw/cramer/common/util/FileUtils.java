package com.cw.cramer.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * 文件操作类
 * @author wicks
 */
public class FileUtils extends org.apache.commons.io.FileUtils {
	
	public static String inputPath = "files\\input\\";
	public static String outputPath = "files\\output\\";
	
	/**
	 * 文件写入
	 * @param inputStream
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static boolean copyInputStreamToFile(InputStream inputStream, String path, String fileName){
		//获取子目录
		path = path + inputPath ;
		
		try{
			File file = new File(path, fileName);
			if(!file.exists()){//判断文件是否存在  
	            try {  
	                file.createNewFile();  //创建文件  
	                  
	            } catch (IOException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            }  
	        }  
			
			FileOutputStream fos = new FileOutputStream(file);
			int ch = 0;
			while((ch=inputStream.read()) != -1){  
	            fos.write(ch);  
	        }  
			
			inputStream.close();
			fos.close();
			LogUtils.info("文件"+fileName+"写入"+path);
			return true;
		}
		catch(Exception ex){
			LogUtils.error("【文件写入失败】文件"+fileName+"写入"+path, ex);
			return false;
		}
	}
	
	/**
	 * 自动生成文件名
	 * @param userId
	 * @param fileType
	 * @return
	 */
	public static String generateFileName(String userId, String fileType){
		String fileName = "";
		fileName += userId;
		fileName += System.currentTimeMillis();
		fileName += new Random().nextInt(1000);
		fileName += "." + fileType;
		return fileName;
	}

}
