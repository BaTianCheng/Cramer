package com.cw.cramer.common.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 对称性加密算法
 * @author wicks
 */
public class EncryptionUtils {
	
	/**
	 * 对称性加密密码
	 */
	private static final String password = "jinying@025-84707089";
	
	/**
	 * 加密算法
	 * @param content
	 * @return
	 */
	public static String encrypt(String content) {  
        try {             
                KeyGenerator kgen = KeyGenerator.getInstance("AES");  
                kgen.init(128, new SecureRandom(password.getBytes()));  
                SecretKey secretKey = kgen.generateKey();  
                byte[] enCodeFormat = secretKey.getEncoded();  
                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
                Cipher cipher = Cipher.getInstance("AES");// 创建密码器  
                byte[] byteContent = content.getBytes("utf-8");  
                cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化  
                byte[] result = cipher.doFinal(byteContent);  
                return parseByte2HexStr(result); // 加密  
        } catch (Exception e) {  
                e.printStackTrace();  
        } 
        return null;  
	}
	
	/**
	 * 解密 
	 * @param content  待解密内容 
	 * @return 
	 */  
	public static String decrypt(String content) {  
	        try {  
	                 KeyGenerator kgen = KeyGenerator.getInstance("AES");  
	                 kgen.init(128, new SecureRandom(password.getBytes()));  
	                 SecretKey secretKey = kgen.generateKey();  
	                 byte[] enCodeFormat = secretKey.getEncoded();  
	                 SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");              
	                 Cipher cipher = Cipher.getInstance("AES");// 创建密码器  
	                cipher.init(Cipher.DECRYPT_MODE, key);// 初始化  
	                byte[] byteContent = parseHexStr2Byte(content);  
	                byte[] result = cipher.doFinal(byteContent);  
	                return new String(result); // 解密  
	        } catch (Exception e) {  
	                e.printStackTrace();  
	        }
	        return null;  
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
	        if (hexStr.length() < 1)  
	                return null;  
	        byte[] result = new byte[hexStr.length()/2];  
	        for (int i = 0;i< hexStr.length()/2; i++) {  
	                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
	                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
	                result[i] = (byte) (high * 16 + low);  
	        }  
	        return result;  
	}
	
	public static void main(String[] args){
		String x = encrypt("123.34");
		System.out.println(x +">"+decrypt(x));
		x = encrypt("89536646");
		System.out.println(x +">"+decrypt(x));
	}
	
	
}
