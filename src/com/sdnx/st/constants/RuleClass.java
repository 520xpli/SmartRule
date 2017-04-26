package com.sdnx.st.constants;

public enum RuleClass {
	admit("1"),
	limitAmount("2"),
	price("3"),
	rate("4"),
	firstTrial("5"),
	lastTrial("6"),
	ProcessTrend("7")
	;
	private String code;
	private RuleClass(String code){
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
