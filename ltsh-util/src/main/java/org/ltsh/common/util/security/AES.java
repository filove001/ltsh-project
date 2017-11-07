package org.ltsh.common.util.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/*
 * AES对称加密和解密
 */
public class AES {
	private static final String IV_STRING = "16-Bytes--String";
	/**
	 * 加密
	 * 
	 * @param content
	 * @param key
	 * @return
	 */
	public static String encrypt(String content, String key) {
		try{
			byte[] byteContent = content.getBytes("UTF-8");
			// 注意，为了能与 iOS 统一
			// 这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
			byte[] enCodeFormat = key.getBytes();
			SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
			byte[] initParam = IV_STRING.getBytes();
			IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
			// 指定加密的算法、工作模式和填充方式
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			byte[] encryptedBytes = cipher.doFinal(byteContent);
			// 同样对加密后数据进行 base64 编码
			return Base64.encode(encryptedBytes);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解密
	 * 
	 * @param content
	 * @param key
	 * @return
	 */
	public static String decrypt(String content, String key){
		try{
			// base64 解码
			byte[] encryptedBytes = Base64.decode(content);
			byte[] enCodeFormat = key.getBytes();
			SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, "AES");
			byte[] initParam = IV_STRING.getBytes();
			IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
			byte[] result = cipher.doFinal(encryptedBytes);
			return new String(result, "UTF-8");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
