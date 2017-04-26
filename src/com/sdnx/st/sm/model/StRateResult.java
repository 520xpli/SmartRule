package com.sdnx.st.sm.model;

import com.sdnx.st.sm.base.BaseModel;

public class StRateResult extends BaseModel{
	//id
	private String id;
	//评级结果编码
	private String rateresultcode;
	//客户编号
	private String custcode;
	//客户名称
	private String custname;
	//证件类型
	private String idtype;
	//证件号码
	private String idnumber;
	//评分卡敞口
	private String cardcode;
	//评分日期
	private java.util.Date ratedate;
	//评级结果
	private String ratecode;
	//评级分数
	private String ratescore;
	//机构编码
	private String orgcode;
	//客户经理
	private String custmanager;
	//最后操作时间
	private java.util.Date lastopertime;
	//业务编号
	private String businesscode;
	//地市编号
		private String instcitycode;
		
		public String getInstcitycode() {
			return instcitycode;
		}
		public void setInstcitycode(String instcitycode) {
			this.instcitycode = instcitycode;
		}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRateresultcode() {
		return rateresultcode;
	}
	public void setRateresultcode(String rateresultcode) {
		this.rateresultcode = rateresultcode;
	}
	public String getCustcode() {
		return custcode;
	}
	public void setCustcode(String custcode) {
		this.custcode = custcode;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getIdtype() {
		return idtype;
	}
	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public String getCardcode() {
		return cardcode;
	}
	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}
	public java.util.Date getRatedate() {
		return ratedate;
	}
	public void setRatedate(java.util.Date ratedate) {
		this.ratedate = ratedate;
	}
	public String getRatecode() {
		return ratecode;
	}
	public void setRatecode(String ratecode) {
		this.ratecode = ratecode;
	}
	public String getRatescore() {
		return ratescore;
	}
	public void setRatescore(String ratescore) {
		this.ratescore = ratescore;
	}
	public java.util.Date getLastopertime() {
		return lastopertime;
	}
	public void setLastopertime(java.util.Date lastopertime) {
		this.lastopertime = lastopertime;
	}
	public String getCustmanager() {
		return custmanager;
	}
	public void setCustmanager(String custmanager) {
		this.custmanager = custmanager;
	}
	public String getBusinesscode() {
		return businesscode;
	}
	public void setBusinesscode(String businesscode) {
		this.businesscode = businesscode;
	}
	
}
