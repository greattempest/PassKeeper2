package com.tempest;

public class Test {
	public static void main(String args[]) {
			String text = "浙C.D43915(网约)";
			String type=text.substring(0, text.indexOf("(") );
			//if(text.indexOf(".")<0)
				//text=text.substring(0,2)+"."+text.substring(2);
			type=type.replace(".", "");
			//carParams.put("PLATE_NUMBER", text);
			System.out.println("vehicleno:"+type);
		
		
	}
}
