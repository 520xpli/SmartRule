package com.sdnx.st.se.constants;

public enum ErrorCode {
	notHaveRule("SSSSS01","�����ڶ�Ӧ����"),
	notHaveObjectFlag("SSSSS02","����Դ�����ʶ������"),
	objectNotMatch("SSSSS03","��������������ϵ��ƥ��"),
	notInitObjectProperty("SSSSS04","�������Բ�����"),
	dataCannotNull("SSSSS05", "����Ϊ��"),
	propertyNotMatchEnum("SSSSS06","������ö�ٹ淶"),
	ageIsError("SSSSS07","����������淶"),
	dateIsError("SSSSS08","���������ڹ淶"),
	timesIsError("SSSSS09","�����ϴ����淶"),
	termIsError("SSSSS10","���������޹淶"),
	useRateError("SSSSS11","������ʹ���ʹ淶"),
	amountIsError("SSSSS12","�����Ͻ��淶"),
	noRuleInterCode("SSSSS13","�ӿڱ��Ϊ��"),
	notHaveRuleInterCode("SSSSS14","�ӿڱ�Ų�����"),
	dataIsNull("SSSSS15","����Ϊ��"),
	ruleCalculateError("SSSSS16","�����������"),
	spreadError("SSSSS17", "������Ʒ�ƹ����ݿ��ѯ����"),
	productSpreadError("SSSSS18", "��Ʒ�ƹ����ݿ��ѯ����"),
	notHaveAmountParm("SSSSS19", "��ǰ������Ʒ�¶�Ȳ�����ƥ��"),
	amountQueryError("SSSSS20", "��Ȳ������ݿ��ѯ����"),
	limitIndustryError("SSSSS21","�޿���ҵ���ݿ��ѯ����"),
	observationError("SSSSS22","�۲������ݿ��ѯ����"),
	dataSourceIsNull("SSSSS23","����ԴΪ��"),
	checkLimitAddError("SSSSS24", "�˶������ӻ��ѯʧ��"),
	notFindFlow("SSSSS25", "�Ҳ������̷�֧"),
	rateRulerError("SSSSS26", "�������ݿ����ʧ��"),
	getCityCodeError("SSSSS27", "��ȡ���ĵ��Ʊ��ʧ��");
	private String code;
	private String desc;
	private ErrorCode(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
