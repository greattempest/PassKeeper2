package com.tempest.math;

public class Code {
	private static String DEFAULT_SEEDS = "PassKeeper";
	
	public static String CODE(String source, String seeds){
		String result = "";
		if(source==null) {
			return "";
		}
		if(seeds==null || seeds.equals("")){
			seeds=DEFAULT_SEEDS;
		}
		try {
			for(int i = 0; i<source.length(); i++){
				char ascii = source.charAt(i);
				char seed = getSeed(seeds,i);
				char xor = (char) (ascii^seed);
				result+=(int)xor+",";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String DECODE(String source, String seeds){
		String result = "";
		if(source==null) {
			return "";
		}
		if(seeds==null || seeds.equals("")){
			seeds=DEFAULT_SEEDS;
		}
		String[] codes = source.split(",");
		try {
			for(int i = 0; i<codes.length; i++){
				if(codes[i].equals("")){
					continue;
				}
				char ascii = (char) Integer.valueOf(codes[i]).intValue();
				char seed = getSeed(seeds,i);
				char xor = (char) (ascii^seed);
				result+=xor;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	private static char getSeed(String seeds,int index){
		char seed = 0;
		try {
			if(seeds==null || seeds.equals("")){
				seeds=DEFAULT_SEEDS;
			}
			while(index>=seeds.length()){
				index=index-seeds.length();
			}
			seed=seeds.charAt(index);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seed;
	}
	
}
