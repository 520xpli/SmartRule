package com.sdnx.st.dp.model;

public class LastTrial {
	//��������
	private String objType;
	//�ͻ����
	private String custCode;
	//�������
	private String rate;
	//�Ƿ�ʮ����
	private String isTenDeny;
	//����Ѻ���յȼ�
	private String collateralRiskLevel;
	//�Ƿ��޿���ҵ
	private String isLimitIndusty;
	//�����Ʒ���
	private String productCode;
	//�ͻ�����
	private String custName;
	public String getIsLimitIndusty() {
		return isLimitIndusty;
	}
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getObjType() {
		return objType;
	}
	public void setObjType(String objType) {
		this.objType = objType;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public void setIsLimitIndusty(String isLimitIndusty) {
		this.isLimitIndusty = isLimitIndusty;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getIsTenDeny() {
		return isTenDeny;
	}
	public void setIsTenDeny(String isTenDeny) {
		this.isTenDeny = isTenDeny;
	}
	public String getCollateralRiskLevel() {
		return collateralRiskLevel;
	}
	public void setCollateralRiskLevel(String collateralRiskLevel) {
		this.collateralRiskLevel = collateralRiskLevel;
	}
	
	
}
