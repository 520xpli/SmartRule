package com.sdnx.st.se.util;

public class CalculateUtil {
	
	public static final Integer max(Integer a, Integer b){
		if(a == null){
			return b;
		}
		if(b == null){
			return a;
		}
		return Math.max(a, b);
	}
	
	public static final Integer min(Integer a, Integer b){
		if(a == null){
			return b;
		}
		if(b == null){
			return a;
		}
		return Math.min(a, b);
	}
	
	public static final Integer add(Integer a, Integer b){
		if(a == null){
			return b;
		}
		if(b == null){
			return a;
		}
		return a + b;
	}
}
