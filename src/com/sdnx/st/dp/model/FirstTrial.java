package com.sdnx.st.dp.model;

public class FirstTrial {
	//对象类型
	private String objType;
	//客户编号
	private String custCode;
	//评级结果
	private String rate;
	//申请产品编号
	private String productCode;
	//授信金额
	private String creditAmount;
	//客户名称
	private String custName;
	//是否新增客户
	private String isNewCust;
	//评级模型类型
	private String rrModel;
	//压品类型
	private String keyType;
	//是否限控行业
	private String isLimitIndusty;
	//担保方式
	private String guaranteeType;
	
	public String getIsLimitIndusty() {
		return isLimitIndusty;
	}
	public void setIsLimitIndusty(String isLimitIndusty) {
		this.isLimitIndusty = isLimitIndusty;
	}
	public String getGuaranteeType() {
		return guaranteeType;
	}
	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}
	public String getRrModel() {
		return rrModel;
	}
	public void setRrModel(String rrModel) {
		this.rrModel = rrModel;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getIsNewCust() {
		return isNewCust;
	}
	public void setIsNewCust(String isNewCust) {
		this.isNewCust = isNewCust;
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
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	
	
}
