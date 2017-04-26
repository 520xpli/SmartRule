package com.sdnx.st.constants;

public enum JEvalResult {
	 TRUE("1.0")
	,FALSE("0.0")
	;
	private String code;
	private JEvalResult(String code){
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
