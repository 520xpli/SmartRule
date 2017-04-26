package com.sdnx.st.se.constants;

public enum DataType {
	 age("1","0")
	,fiveClass("2","1")
	,date("3","0")
	,times("4","0")
	,term("5","0")
	,amount("6","0")
	,isenum("7","0")
	;
	private String code;
	private String isEnum;
	private DataType(String code,String isEnum){
		this.code = code;
		this.isEnum = isEnum;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIsEnum() {
		return isEnum;
	}
	public void setIsEnum(String isEnum) {
		this.isEnum = isEnum;
	}
	
}
