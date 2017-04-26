package com.sdnx.st.sm.vo;

import java.util.Date;

public class StTmpDataVo {
	// 编号
	private String id;
	// 业务编号
	private String businesscode;
	// 添加规则模型
	private String addmodel;
	// 使用规则模型
	private String usemodel;
	// 数据标识
	private String datakey;
	// 数据值
	private String datavalue;
	// 添加时间
	private Date addtime;
	// 最后操作时间
	private Date lastopertime;

	public Date getLastopertime() {
		return lastopertime;
	}

	public void setLastopertime(Date lastopertime) {
		this.lastopertime = lastopertime;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinesscode() {
		return businesscode;
	}

	public void setBusinesscode(String businesscode) {
		this.businesscode = businesscode;
	}

	public String getAddmodel() {
		return addmodel;
	}

	public void setAddmodel(String addmodel) {
		this.addmodel = addmodel;
	}

	public String getUsemodel() {
		return usemodel;
	}

	public void setUsemodel(String usemodel) {
		this.usemodel = usemodel;
	}

	public String getDatakey() {
		return datakey;
	}

	public void setDatakey(String datakey) {
		this.datakey = datakey;
	}

	public String getDatavalue() {
		return datavalue;
	}

	public void setDatavalue(String datavalue) {
		this.datavalue = datavalue;
	}

}
