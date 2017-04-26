package com.sdnx.st.sm.model;

import java.util.Date;

import com.sdnx.st.sm.base.BaseModel;

public class StRule extends BaseModel{
	//编号
	private String id;
	//结构编码
	private String struccode;
	//规则编码
	private String rulecode;
	//规则拒绝周期（月）
	private Integer cycle;
	//父规则编码
	private String parentcode;
	//子机构编码
	private String orgcode;
	//适用对象编码
	private String objectcode;
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
	public String getRulecode() {
		return rulecode;
	}
	public void setRulecode(String rulecode) {
		this.rulecode = rulecode;
	}
	public Integer getCycle() {
		return cycle;
	}
	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}
	public String getParentcode() {
		return parentcode;
	}
	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getObjectcode() {
		return objectcode;
	}
	public void setObjectcode(String objectcode) {
		this.objectcode = objectcode;
	}
	public Date getLastopertime() {
		return lastopertime;
	}
	public void setLastopertime(Date lastopertime) {
		this.lastopertime = lastopertime;
	}
	
}
