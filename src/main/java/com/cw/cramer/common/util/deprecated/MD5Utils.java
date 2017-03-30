package com.cw.cramer.common.util.deprecated;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * MD5工具类
 * @author wicks
 */
public class MD5Utils {
	
	  /**利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException  
     */
    public static String EncoderByMd5(String str) {
    	try{
    		MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(str.getBytes());  
            byte b[] = md.digest();  
  
            int i;  
  
            StringBuffer buf = new StringBuffer("");  
            for (int offset = 0; offset < b.length; offset++) {  
                i = b[offset];  
                if (i < 0)  
                    i += 256;  
                if (i < 16)  
                    buf.append("0");  
                buf.append(Integer.toHexString(i));  
            }  
            //32位加密  
            return buf.toString();  
    	} 
    	catch(Exception ex){
    		return null;
    	}
    }
    
    public static void main(String[] args){
    	System.out.println(EncoderByMd5("123456"));
    }

}
