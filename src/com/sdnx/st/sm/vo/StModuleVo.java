package com.sdnx.st.sm.vo;

import java.util.Date;

public class StModuleVo {
	// ���
	private String id;
	// �ṹ����
	private String struccode;
	// ģ�����
	private String modulecode;
	// ǰ������
	private String precondition;
	// �Ƿ����ģ��
	private String isgroup;
	// ����㷨
	private String groupcal;
	// ������ʱ��
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

	public String getModulecode() {
		return modulecode;
	}

	public void setModulecode(String modulecode) {
		this.modulecode = modulecode;
	}

	public String getPrecondition() {
		return precondition;
	}

	public void setPrecondition(String precondition) {
		this.precondition = precondition;
	}

	public String getIsgroup() {
		return isgroup;
	}

	public void setIsgroup(String isgroup) {
		this.isgroup = isgroup;
	}

	public String getGroupcal() {
		return groupcal;
	}

	public void setGroupcal(String groupcal) {
		this.groupcal = groupcal;
	}

	public Date getLastopertime() {
		return lastopertime;
	}

	public void setLastopertime(Date lastopertime) {
		this.lastopertime = lastopertime;
	}
}
