package com.sdnx.st.constants;

public enum ObjectType {
	borrower("1"),
	warrant("2"),
	relateCompany("3"),
	family("4");
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private ObjectType(String code){
		this.code = code;
	}
}
