package com.sdnx.st.constants;

public enum YesNo {
	 yes("1", "T")
	,no("0", "F")
	;
	private String code;
	private String sCode;
	private YesNo(String code, String sCode){
		this.code = code;
		this.sCode = sCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getsCode() {
		return sCode;
	}
	public void setsCode(String sCode) {
		this.sCode = sCode;
	}
	
}
