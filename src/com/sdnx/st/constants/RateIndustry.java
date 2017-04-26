package com.sdnx.st.constants;

public enum RateIndustry {
	plantVeg("1",new String[]{"A0141"}), //种植蔬菜
	plantOther("2",new String[]{"A01"}),//种植其他
	breadPig("3",new String[]{"A0313"}),//养猪
	breadOther("4",new String[]{"A03"}),//养殖其他
	personalRetail("5",new String[]{"F"}),//个人批发零售
	personalTrans("6",new String[]{"G"}),//个人运输
	personalMake("7",new String[]{"C"}),//个人制造
	personalCaterer("8",new String[]{"H"}),//个人餐饮
	personalOther("9",new String[]{});//个人其他
	
	private String code;
	private String[] industry;
	
	public String[] getIndustry() {
		return industry;
	}

	public void setIndustry(String[] industry) {
		this.industry = industry;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private RateIndustry(String code, String[] industry){
		this.code = code;
		this.industry = industry;
	}
}
