package com.cw.test;

import java.text.DecimalFormat;

public class tttt {

	public static void main(String[] args) {
		/*for(int i=1;i<5000000;i++){
			double x = Double.valueOf(String.valueOf(i))/100;
			System.out.println(x);
			System.out.println(String.valueOf(Integer.valueOf(String.valueOf(x*100))));
		}*/
		double x1 = 4659.27;
		System.out.println(String.valueOf(x1*100));
		System.out.println(Integer.parseInt(new DecimalFormat("0").format(x1*100)));
		Double x2 = x1*100;
		Double xx = 1.99999999999999;
		System.out.println(String.valueOf(Integer.parseInt(new DecimalFormat("0").format(xx))));
	}

}
