package com.sdnx.st.dp.model;


public class PublicData {
	//业务编号
	private String businesscode;
	//当前操作员编码
	private String oprCode;
	//当前机构编码
	private String inscode;
	//当前地市编码
	private String cityCode;
	//当前法人编码
	private String legalCode;
	//业务日期
	private String busDate;
	//自行阶段
	private String step;
	//核心地势编码
	private String coreCityCode;
	//所选评分卡类型
	private String rateCardCode;
	
	
	public String getRateCardCode() {
		return rateCardCode;
	}
	public void setRateCardCode(String rateCardCode) {
		this.rateCardCode = rateCardCode;
	}
	public String getCoreCityCode() {
		return coreCityCode;
	}
	public void setCoreCityCode(String coreCityCode) {
		this.coreCityCode = coreCityCode;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getBusinesscode() {
		return businesscode;
	}
	public void setBusinesscode(String businesscode) {
		this.businesscode = businesscode;
	}
	public String getOprCode() {
		return oprCode;
	}
	public void setOprCode(String oprCode) {
		this.oprCode = oprCode;
	}
	public String getInscode() {
		return inscode;
	}
	public void setInscode(String inscode) {
		this.inscode = inscode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getLegalCode() {
		return legalCode;
	}
	public void setLegalCode(String legalCode) {
		this.legalCode = legalCode;
	}
	public String getBusDate() {
		return busDate;
	}
	public void setBusDate(String busDate) {
		this.busDate = busDate;
	}
	
	
}
