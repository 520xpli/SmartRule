package com.sdnx.st.sm.vo;

import java.util.Date;

public class StTmpDataVo {
	// ���
	private String id;
	// ҵ����
	private String businesscode;
	// ��ӹ���ģ��
	private String addmodel;
	// ʹ�ù���ģ��
	private String usemodel;
	// ���ݱ�ʶ
	private String datakey;
	// ����ֵ
	private String datavalue;
	// ���ʱ��
	private Date addtime;
	// ������ʱ��
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
