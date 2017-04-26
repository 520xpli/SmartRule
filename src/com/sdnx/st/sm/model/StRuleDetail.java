package com.sdnx.st.sm.model;

import java.util.Date;

import com.sdnx.st.sm.base.BaseModel;

public class StRuleDetail extends BaseModel{
	//编号
	private String id;
	//结构编码
	private String struccode;
	//具体规则编码
	private String detailcode;
	//规则前提
	private String precondition;
	//规则结果类型
	private String resulttype;
	//规则结果
	private String ruleresult;
	//最后操作时间
	private Date lastopertime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStruccode() {
		return struccode;
	}
	public void setStruccode(String struccode) {
		this.struccode = struccode;
	}
	public String getDetailcode() {
		return detailcode;
	}
	public void setDetailcode(String detailcode) {
		this.detailcode = detailcode;
	}
	public String getPrecondition() {
		return precondition;
	}
	public void setPrecondition(String precondition) {
		this.precondition = precondition;
	}
	public String getResulttype() {
		return resulttype;
	}
	public void setResulttype(String resulttype) {
		this.resulttype = resulttype;
	}
	public Date getLastopertime() {
		return lastopertime;
	}
	public void setLastopertime(Date lastopertime) {
		this.lastopertime = lastopertime;
	}
	public String getRuleresult() {
		return ruleresult;
	}
	public void setRuleresult(String ruleresult) {
		this.ruleresult = ruleresult;
	}
	
}
