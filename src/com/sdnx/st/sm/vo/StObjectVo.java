package com.sdnx.st.sm.vo;

import java.util.Date;
import java.util.List;

public class StObjectVo {
	//���
	private String id;
	//ģ�ͱ���
	private String modelcode;
	//��������
	private String objectname;
	//��������
	private String description;
	//�������
	private String objectcode;
	//������ʱ��
	private Date lastopertime;
	//���������б�
	private List<StObjectPropertyVo> propertyList;
	public List<StObjectPropertyVo> getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(List<StObjectPropertyVo> propertyList) {
		this.propertyList = propertyList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModelcode() {
		return modelcode;
	}
	public void setModelcode(String modelcode) {
		this.modelcode = modelcode;
	}
	public String getObjectname() {
		return objectname;
	}
	public void setObjectname(String objectname) {
		this.objectname = objectname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
