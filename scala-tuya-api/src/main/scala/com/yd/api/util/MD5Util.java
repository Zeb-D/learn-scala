package com.yd.api.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Util {

	public static String getMD5(byte[] source) {
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
			// 用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
			// 所以表示成 16 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
				// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
				// >>> 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符串

		} catch (Exception e) {

		}
		return s;
	}

	public static String get(String input){
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(input.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			String md5 = new BigInteger(1, md.digest()).toString(32);
			//BigInteger会把0省略掉，需补全至32位
			return fillZero(md5);
		} catch (Exception e) {
			throw new RuntimeException("MD5加密错误:"+e.getMessage(), e);
		}

	}

	private static String fillZero(String str){
		if (str.length() == 32) {
			return str;
		}

		StringBuilder builder = new StringBuilder();
		Integer fillCount = 32 - str.length();
		for (int i=0;i<fillCount;i++) {
			builder.append("0");
		}
		builder.append(str);
		return builder.toString();
	}

	public static String getMD5(String inputStr) {
        return getMD5(inputStr.getBytes());
	}
}
