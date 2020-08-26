package com.tempest;

public class Test {
	public static void main(String args[]) {
		long month=-12000;
		double r=0.05;
		double r2=0.04;
		long save=4000000;
		int y=0;
		while(y<50) {
			save=new Double(save*(1+r)+(month*12)).longValue();
			month=new Double(month*(1+r2)).longValue();
			y++;
			System.out.println("Y:"+y+",S:"+save+",C:"+month);
			//System.out.println(save);
		}
	}
}
