package com.sdnx.st.sm.vo;

import java.util.Date;

public class StModuleVo {
	// 编号
	private String id;
	// 结构编码
	private String struccode;
	// 模块编码
	private String modulecode;
	// 前提条件
	private String precondition;
	// 是否组合模块
	private String isgroup;
	// 组合算法
	private String groupcal;
	// 最后操作时间
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
