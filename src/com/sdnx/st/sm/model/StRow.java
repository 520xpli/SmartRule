package com.sdnx.st.sm.model;

import java.util.Date;

import com.sdnx.st.sm.base.BaseModel;

public class StRow extends BaseModel{
	//���
	private String id;
	//����������
	private String detailcode;
	//�б���
	private String rowcode;
	//������
	private String rowtype;
	//��һ�������
	private String firstcal;
	//�Ƿ���������
	private String isleft;
	//�������Ա���
	private String propertycode;
	//�ڶ��������
	private String secondcal;
	//��ֵ
	private String value;
	//�Ƿ���������
	private String isright;
	//����
	private Integer rowcount;
	//������ʱ��
	private Date lastopertime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDetailcode() {
		return detailcode;
	}
	public void setDetailcode(String detailcode) {
		this.detailcode = detailcode;
	}
	public String getRowcode() {
		return rowcode;
	}
	public void setRowcode(String rowcode) {
		this.rowcode = rowcode;
	}
	public String getRowtype() {
		return rowtype;
	}
	public void setRowtype(String rowtype) {
		this.rowtype = rowtype;
	}
	public String getFirstcal() {
		return firstcal;
	}
	public void setFirstcal(String firstcal) {
		this.firstcal = firstcal;
	}
	public String getIsleft() {
		return isleft;
	}
	public void setIsleft(String isleft) {
		this.isleft = isleft;
	}
	public String getPropertycode() {
		return propertycode;
	}
	public void setPropertycode(String propertycode) {
		this.propertycode = propertycode;
	}
	public String getSecondcal() {
		return secondcal;
	}
	public void setSecondcal(String secondcal) {
		this.secondcal = secondcal;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getIsright() {
		return isright;
	}
	public void setIsright(String isright) {
		this.isright = isright;
	}
	
	public Integer getRowcount() {
		return rowcount;
	}
	public void setRowcount(Integer rowcount) {
		this.rowcount = rowcount;
	}
	public Date getLastopertime() {
		return lastopertime;
	}
	public void setLastopertime(Date lastopertime) {
		this.lastopertime = lastopertime;
	}
	
}
