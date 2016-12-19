package com.se.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public final class MD5Helper {
		public static String encrypt(String inStr){
			String outString="";
			try {
				MessageDigest md5 = MessageDigest.getInstance("md5");//创建MD5对象
			    byte[] cipherData = md5.digest(inStr.getBytes());  //加密操作
			    StringBuilder builder = new StringBuilder();  
			    for(byte cipher : cipherData) {  
			        String toHexStr = Integer.toHexString(cipher & 0xff);  //转换为十六进制字符串
			        builder.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr); //确保生成32位字符串
			    }  
			    outString=builder.toString();
			  
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			  return outString;
		}
		public static void main(String args[]){
			System.out.print(encrypt("hhh"));
		}
}


