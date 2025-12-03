package com.tempest;

import com.tempest.math.AES256Util;
import com.tempest.math.AESUtil;
import com.tempest.math.Code;

public class Test {
	public static void main(String args[]) {
		String pass = "122,126,66,71,13,125,96,91,112,119,66,83,126,7,121,64,6,64,67,92,74,83,8,11,";
		try {
			String objpass = Code.DECODE(pass, "PassKeeper");
			objpass = AESUtil.decrypt(AES256Util.AesPass,objpass);
			System.out.println(objpass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
