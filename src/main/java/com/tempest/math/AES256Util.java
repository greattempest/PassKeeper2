package com.tempest.math;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import it.sauronsoftware.base64.Base64;  

public class AES256Util {
	public static String AesPass = "SCARLETT O’HARA was not beautiful, but men seldom realized it when caught by her charm as the Tarleton twins were.";

	public static byte[] encrypt(String content, String password) {
		try {
			// "AES"：请求的密钥算法的标准名称
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			// 256：密钥生成参数；securerandom：密钥生成器的随机源
			//SecureRandom securerandom = new SecureRandom(tohash256Deal(password));
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", new CryptoProvider());  
			sr.setSeed(tohash256Deal(password));  
	        kgen.init(128, sr); // 192 and 256 bits may not be available  
	        //SecretKey skey = kgen.generateKey();  
			//kgen.init(256, sr);
			// 生成秘密（对称）密钥
			SecretKey secretKey = kgen.generateKey();
			
			// 返回基本编码格式的密钥
			byte[] enCodeFormat = secretKey.getEncoded();
			// 根据给定的字节数组构造一个密钥。enCodeFormat：密钥内容；"AES"：与给定的密钥内容相关联的密钥算法的名称
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			// 将提供程序添加到下一个可用位置
			Security.addProvider(new BouncyCastleProvider());
			// 创建一个实现指定转换的 Cipher对象，该转换由指定的提供程序提供。
			// "AES/ECB/PKCS7Padding"：转换的名称；"BC"：提供程序的名称
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");

			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] byteContent = content.getBytes("utf-8");
			byte[] cryptograph = cipher.doFinal(byteContent);
			return Base64.encode(cryptograph);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(byte[] cryptograph, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom securerandom = new SecureRandom(tohash256Deal(password));
			kgen.init(256, securerandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");

			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] content = cipher.doFinal(Base64.decode(cryptograph));
			return new String(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String parseByte2HexStr(byte buf[]) {
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

	private static byte[] tohash256Deal(String datastr) {
		try {
			MessageDigest digester = MessageDigest.getInstance("SHA-256");
			digester.update(datastr.getBytes());
			byte[] hex = digester.digest();
			return hex;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void main1(String[] args) {

		String content = "1234567890abcdefghijklmnopqrstuvwyxz";
		String password = AesPass;
		System.out.println("明文：" + content);
		// System.out.println("key：" + password);

		byte[] encryptResult = AES256Util.encrypt(content, password);
		System.out.println("密文：" + AES256Util.parseByte2HexStr(encryptResult));

		String decryptResult = AES256Util.decrypt(encryptResult, password);
		System.out.println("解密：" + decryptResult);
	}

}
