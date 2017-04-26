package com.sdnx.st.constants;

public enum RuleState {
	 effect("1")
	,invalid("2")
	,outdate("3")
	,add("4")
	,approving("5")
	,approved("6")
	;
	private String code;
	private RuleState(String code){
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
