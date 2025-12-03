package com.tempest.util;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: CSDN
 * @description: RSA工具类
 * @author: Alian
 * @create: 2021-06-08 09:30:14
 **/
public class RSAUtil {

    //签名算法名称
    private static final String RSA_KEY_ALGORITHM = "RSA";

    //标准签名算法名称
    private static final String RSA_SIGNATURE_ALGORITHM = "SHA1withRSA";
    private static final String RSA2_SIGNATURE_ALGORITHM = "SHA256withRSA";

    private static final String Public_Key ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCRSIlpm5fnsYmub7cgDbj7HJ2CQy38T7uq+XXkGRztSe5d/d9DGGtHfBYuAeOUFTAvWdZ+bl78+94szbdFLHoKUF/LIdIjgr1o8b7Mq1L5AFgKehsCnuDvr6ymLy6HrCF5oDvVFNsFx6Qc44+NHlXVvLKJcVAwYvBfeeYqLRIREQIDAQAB";
    
    private static final String Private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJFIiWmbl+exia5vtyANuPscnYJDLfxPu6r5deQZHO1J7l3930MYa0d8Fi4B45QVMC9Z1n5uXvz73izNt0UsegpQX8sh0iOCvWjxvsyrUvkAWAp6GwKe4O+vrKYvLoesIXmgO9UU2wXHpBzjj40eVdW8solxUDBi8F955iotEhERAgMBAAECgYAMA9+5aDDL7JbiYkLW18uwmNE98Khg8YcnwLHLR3ho93BLPa+1MNPOeair7WQQ3x+5Csj+c8pPSdU6B1lc0yzUhIhN2ZxEXkPthkF2Xrs8tLfL7qbh1jXs1U+ynBu4A+4wZXJMrxm5KfFFEcvNzqbngyiREND4LNz2Mqz31ZoCMQJBAJRY1UUdbemWGDVC9F5eS4Rw4SEv1+yaMEbB/pV06mjnNgfOe/BatA9DOSO8roASQ1mlK4x9zEQWxk05u2ODfa8CQQD6to17fvW2/ZJhd6iuXiCLXxntqZXEnSJBmBiW+FVMANBdiujaI1ptQiQHk2hryTthudljf2dvR8nvf+pqP80/AkAHvA36mYs2h53hgQkutmnnkN8cFNuEOZHUzp6QelUSfSOjCpAJuyDyPsYG5/qNMOaUEPmRTKnOzwUt15m+mYd/AkAH/dOqsRRR/ekxlCfbTwLIjoJ8EeDFQKD76EY4YbgOUSRTJv9fHKtgOjB90VQNw39SESN9yWzZnjcwnCozss7vAkEAiK/kfzJYJu4801AXI5R2Vrc2IjcXfSSV2f0QUIR/PziFw7BDeO1DlJ+eEyS5F7wa4iP2a8WJ4dhJT9IO1bVTfQ==";
    		
    //RSA密钥长度,默认密钥长度是1024,密钥长度必须是64的倍数，在512到65536位之间,不管是RSA还是RSA2长度推荐使用2048
    private static final int KEY_SIZE = 1024;

    /**
     * 生成密钥对
     *
     * @return 返回包含公私钥的map
     */
    public static Map<String, String> generateKey() {
        KeyPairGenerator keygen;
        try {
            keygen = KeyPairGenerator.getInstance(RSA_KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("RSA初始化密钥出现错误,算法异常");
        }
        SecureRandom secrand = new SecureRandom();
        //初始化随机产生器
        secrand.setSeed("Alian".getBytes());
        //初始化密钥生成器
        keygen.initialize(KEY_SIZE, secrand);
        KeyPair keyPair = keygen.genKeyPair();
        //获取公钥并转成base64编码
        byte[] pub_key = keyPair.getPublic().getEncoded();
        String publicKeyStr = Base64.getEncoder().encodeToString(pub_key);
        //获取私钥并转成base64编码
        byte[] pri_key = keyPair.getPrivate().getEncoded();
        String privateKeyStr = Base64.getEncoder().encodeToString(pri_key);
        //创建一个Map返回结果
        Map<String, String> keyPairMap = new HashMap<>();
        keyPairMap.put("publicKeyStr", publicKeyStr);
        keyPairMap.put("privateKeyStr", privateKeyStr);
        return keyPairMap;
    }

    /**
     * 公钥加密(用于数据加密)
     *
     * @param data         加密前的字符串
     * @param publicKeyStr base64编码后的公钥
     * @return base64编码后的字符串
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, String publicKeyStr) throws Exception {
        //Java原生base64解码
        byte[] pubKey = Base64.getDecoder().decode(publicKeyStr);
        //创建X509编码密钥规范
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        //返回转换指定算法的KeyFactory对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        //根据X509编码密钥规范产生公钥对象
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        //根据转换的名称获取密码对象Cipher（转换的名称：算法/工作模式/填充模式）
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        //用公钥初始化此Cipher对象（加密模式）
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        //对数据加密
        byte[] encrypt = cipher.doFinal(data.getBytes());
        //返回base64编码后的字符串
        return Base64.getEncoder().encodeToString(encrypt);
    }
    
    public static String encryptByPublicKey(String data) {
    	try {
			return encryptByPublicKey(data,Public_Key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    

    /**
     * 私钥解密(用于数据解密)
     *
     * @param data          解密前的字符串
     * @param privateKeyStr 私钥
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data, String privateKeyStr) throws Exception {
        //Java原生base64解码
        byte[] priKey = Base64.getDecoder().decode(privateKeyStr);
        //创建PKCS8编码密钥规范
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
        //返回转换指定算法的KeyFactory对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        //根据PKCS8编码密钥规范产生私钥对象
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //根据转换的名称获取密码对象Cipher（转换的名称：算法/工作模式/填充模式）
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        //用私钥初始化此Cipher对象（解密模式）
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        //对数据解密
        byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(data));
        //返回字符串
        return new String(decrypt);
    }

    public static String decryptByPrivateKey(String data) {
    	try {
			return decryptByPrivateKey(data,Private_key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    /**
     * 私钥加密(用于数据签名)
     *
     * @param data          加密前的字符串
     * @param privateKeyStr base64编码后的私钥
     * @return base64编码后后的字符串
     * @throws Exception
     */
    public static String encryptByPrivateKey(String data, String privateKeyStr) throws Exception {
        //Java原生base64解码
        byte[] priKey = Base64.getDecoder().decode(privateKeyStr);
        //创建PKCS8编码密钥规范
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
        //返回转换指定算法的KeyFactory对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        //根据PKCS8编码密钥规范产生私钥对象
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //根据转换的名称获取密码对象Cipher（转换的名称：算法/工作模式/填充模式）
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        //用私钥初始化此Cipher对象（加密模式）
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        //对数据加密
        byte[] encrypt = cipher.doFinal(data.getBytes());
        //返回base64编码后的字符串
        return Base64.getEncoder().encodeToString(encrypt);
    }
    
    public static String encryptByPrivateKey(String data) {
    	try {
			return encryptByPrivateKey(data,Private_key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    /**
     * 公钥解密(用于数据验签)
     *
     * @param data         解密前的字符串
     * @param publicKeyStr base64编码后的公钥
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decryptByPublicKey(String data, String publicKeyStr) throws Exception {
        //Java原生base64解码
        byte[] pubKey = Base64.getDecoder().decode(publicKeyStr);
        //创建X509编码密钥规范
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        //返回转换指定算法的KeyFactory对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        //根据X509编码密钥规范产生公钥对象
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        //根据转换的名称获取密码对象Cipher（转换的名称：算法/工作模式/填充模式）
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        //用公钥初始化此Cipher对象（解密模式）
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        //对数据解密
        byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(data));
        //返回字符串
        return new String(decrypt);
    }
    
    public static String decryptByPublicKey(String data) {
    	try {
			return decryptByPublicKey(data,Public_Key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    /**
     * RSA签名
     *
     * @param data     待签名数据
     * @param priKey   私钥
     * @param signType RSA或RSA2
     * @return 签名
     * @throws Exception
     */
    public static String sign(byte[] data, byte[] priKey, String signType) throws Exception {
        //创建PKCS8编码密钥规范
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
        //返回转换指定算法的KeyFactory对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        //根据PKCS8编码密钥规范产生私钥对象
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //标准签名算法名称(RSA还是RSA2)
        String algorithm = RSA_KEY_ALGORITHM.equals(signType) ? RSA_SIGNATURE_ALGORITHM : RSA2_SIGNATURE_ALGORITHM;
        //用指定算法产生签名对象Signature
        Signature signature = Signature.getInstance(algorithm);
        //用私钥初始化签名对象Signature
        signature.initSign(privateKey);
        //将待签名的数据传送给签名对象(须在初始化之后)
        signature.update(data);
        //返回签名结果字节数组
        byte[] sign = signature.sign();
        //返回Base64编码后的字符串
        return Base64.getEncoder().encodeToString(sign);
    }

    /**
     * RSA校验数字签名
     *
     * @param data     待校验数据
     * @param sign     数字签名
     * @param pubKey   公钥
     * @param signType RSA或RSA2
     * @return boolean 校验成功返回true，失败返回false
     */
    public static boolean verify(byte[] data, byte[] sign, byte[] pubKey, String signType) throws Exception {
        //返回转换指定算法的KeyFactory对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        //创建X509编码密钥规范
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        //根据X509编码密钥规范产生公钥对象
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        //标准签名算法名称(RSA还是RSA2)
        String algorithm = RSA_KEY_ALGORITHM.equals(signType) ? RSA_SIGNATURE_ALGORITHM : RSA2_SIGNATURE_ALGORITHM;
        //用指定算法产生签名对象Signature
        Signature signature = Signature.getInstance(algorithm);
        //用公钥初始化签名对象,用于验证签名
        signature.initVerify(publicKey);
        //更新签名内容
        signature.update(data);
        //得到验证结果
        return signature.verify(sign);
    }
    
    public static void genkey() {
    	Map<String, String> keyMap = RSAUtil.generateKey();
        String publicKeyStr = keyMap.get("publicKeyStr");
        String privateKeyStr = keyMap.get("privateKeyStr");
        System.out.println("-----------------生成的公钥和私钥------------------------------");
        System.out.println("获取到的公钥：" + publicKeyStr);
        System.out.println("获取到的私钥：" + privateKeyStr);
        // 数字签名
        try {
			String data = "tranSeq=1920542585&amount=100&payType=wechat";
			System.out.println("待签名的数据：" + data);
			String sign = RSAUtil.sign(data.getBytes(), Base64.getDecoder().decode(privateKeyStr), "RSA");
			System.out.println("数字签名结果：" + sign);
			boolean verify = RSAUtil.verify(data.getBytes(), Base64.getDecoder().decode(sign), Base64.getDecoder().decode(publicKeyStr), "RSA");
			System.out.println("数字签名验证结果：" + verify);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
   
}
