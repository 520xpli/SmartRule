package com.sdnx.st.constants;

public enum RateIndustry {
	plantVeg("1",new String[]{"A0141"}), //��ֲ�߲�
	plantOther("2",new String[]{"A01"}),//��ֲ����
	breadPig("3",new String[]{"A0313"}),//����
	breadOther("4",new String[]{"A03"}),//��ֳ����
	personalRetail("5",new String[]{"F"}),//������������
	personalTrans("6",new String[]{"G"}),//��������
	personalMake("7",new String[]{"C"}),//��������
	personalCaterer("8",new String[]{"H"}),//���˲���
	personalOther("9",new String[]{});//��������
	
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
