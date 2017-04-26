package com.sdnx.st.se.constants;

public enum GroupCal {
	 add("1","add")
	,max("2","max")
	,min("3","min")
	;
	private String code;
	private String function;
	private GroupCal(String code,String function){
		this.code = code;
		this.function = function;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public static GroupCal getFunctionByCode(String code){
		if(GroupCal.max.code.equals(code)){
			return max;
		}else if(GroupCal.min.code.equals(code)){
			return min;
		}
		return add;
	}
}
