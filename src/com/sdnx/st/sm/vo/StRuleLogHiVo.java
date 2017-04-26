package com.sdnx.st.sm.vo;

import java.util.Date;

public class StRuleLogHiVo {
	// 编号
	private String id;
	// 日志编码
	private String logcode;
	// 结构编码
	private String struccode;
	// 规则结果
	private String result;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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
