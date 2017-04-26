package com.sdnx.st.dp.model;

public class LastTrial {
	//对象类型
	private String objType;
	//客户编号
	private String custCode;
	//评级结果
	private String rate;
	//是否十项否决
	private String isTenDeny;
	//抵质押风险等级
	private String collateralRiskLevel;
	//是否限控行业
	private String isLimitIndusty;
	//申请产品编号
	private String productCode;
	//客户名称
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
