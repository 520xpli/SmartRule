package com.sdnx.st.constants;

public enum RowType {
	precondition("1"),
	result("2");
	private String code;
	private RowType(String code){
		this.code = code;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
