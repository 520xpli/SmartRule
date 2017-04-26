package com.sdnx.st.constants;

public enum GuaranteeType {
	collaterLoan("C01"),
	guaranteeLoan("C02"),
	creditLoan("C03");
	private static final String credit = "C100";
	private static final String guarantee = "C101";
	private static final String mortgage = "C102";
	private static final String pledge = "C103";
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private GuaranteeType(String code){
		this.code = code;
	}
	
	public static String getParmGuaranteeType(String guaranteeType){
		if(credit.equals(guaranteeType)) return creditLoan.code;
		else if(guarantee.equals(guaranteeType)) return guaranteeLoan.code;
		else if(mortgage.equals(guaranteeType) || pledge.equals(guaranteeType)) return collaterLoan.code;
		return null;
	}
}
