package com.sdnx.st.sm.vo;

import java.util.Date;

public class StRuleLogHiVo {
	// ���
	private String id;
	// ��־����
	private String logcode;
	// �ṹ����
	private String struccode;
	// ������
	private String result;
	// ִ��ʱ��
	private Date operatetime;
	// ҵ����
	private String businesscode;
	// ִ���˱���
	private String operatorcode;
	// ִ���˻�������
	private String instcode;
	// ��������
	private String deptcode;
	// ��������
	private String instcitycode;
	// ������ʱ��
	private Date lastopertime;
	//ִ�н׶�
	private String operperiod;
	//�������ݱ���
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
