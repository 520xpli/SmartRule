package com.sdnx.st.sm.vo;

import java.util.Date;

public class StRuleLogVo {
	// 编号
	private String id;
	// 日志编码
	private String logcode;
	// 结构编码
	private String struccode;
	// 规则结果
	private String ruleresult;
	// 执行时间
	private Date operatetime;
	// 业务编号
	private String businesscode;
	// 执行人编码
	private String operatorcode;
	// 执行人机构编码
	private String instcode;
	// 所属部门
	private String deptcode;
	// 所属地市
	private String instcitycode;
	// 最后操作时间
	private Date lastopertime;
	//执行阶段
	private String operperiod;
	//输入数据编码
	private String inputdatacode;
	//机构名称
	private String orgname;
	//规则名称
	private String strucname;
	//执行对象
	private String objectcode;
	//规则类型
	private String classification;
	//是否也在
	private String isleaf;
	
	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getStrucname() {
		return strucname;
	}

	public void setStrucname(String strucname) {
		this.strucname = strucname;
	}


	public String getObjectcode() {
		return objectcode;
	}

	public void setObjectcode(String objectcode) {
		this.objectcode = objectcode;
	}

	public String getInputdatacode() {
		return inputdatacode;
	}

	public void setInputdatacode(String inputdatacode) {
		this.inputdatacode = inputdatacode;
	}

	public String getOperperiod() {
		return operperiod;
	}

	public void setOperperiod(String operperiod) {
		this.operperiod = operperiod;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogcode() {
		return logcode;
	}

	public void setLogcode(String logcode) {
		this.logcode = logcode;
	}

	public String getStruccode() {
		return struccode;
	}

	public void setStruccode(String struccode) {
		this.struccode = struccode;
	}

	public String getRuleresult() {
		return ruleresult;
	}

	public void setRuleresult(String ruleresult) {
		this.ruleresult = ruleresult;
	}

	public Date getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}

	public String getBusinesscode() {
		return businesscode;
	}

	public void setBusinesscode(String businesscode) {
		this.businesscode = businesscode;
	}

	public String getOperatorcode() {
		return operatorcode;
	}

	public void setOperatorcode(String operatorcode) {
		this.operatorcode = operatorcode;
	}

	public String getInstcode() {
		return instcode;
	}

	public void setInstcode(String instcode) {
		this.instcode = instcode;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getInstcitycode() {
		return instcitycode;
	}

	public void setInstcitycode(String instcitycode) {
		this.instcitycode = instcitycode;
	}

	public Date getLastopertime() {
		return lastopertime;
	}

	public void setLastopertime(Date lastopertime) {
		this.lastopertime = lastopertime;
	}

}
