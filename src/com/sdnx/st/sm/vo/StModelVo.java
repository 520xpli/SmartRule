package com.sdnx.st.sm.vo;

import java.util.Date;
import java.util.List;

public class StModelVo {
	//���
	private String id;
	//ģ������
	private String modelname;
	//ģ�ͱ���
	private String modelcode;
	//��������
	private String orgcode;
	//������
	private String orgname;
	//�汾��
	private String version;
	//���
	private String classification;
	//�����˱���
	private String createusercode;
	//����ʱ��
	private Date createtime;
	//�޸�ʱ��
	private Date modifytime;
	//��Чʱ��
	private Date effecttime;
	//����ʱ��
	private Date endtime;
	//״̬
	private String state;
	//������ʱ��
	private Date lastopertime;
	//�ṹlist
	private List<StStructrueVo> ssList;
	public List<StStructrueVo> getSsList() {
		return ssList;
	}
	public void setSsList(List<StStructrueVo> ssList) {
		this.ssList = ssList;
	}
	public String getModelname() {
		return modelname;
	}
	public void setModelname(String modelname) {
		this.modelname = modelname;
	}
	public String getModelcode() {
		return modelcode;
	}
	public void setModelcode(String modelcode) {
		this.modelcode = modelcode;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getCreateusercode() {
		return createusercode;
	}
	public void setCreateusercode(String createusercode) {
		this.createusercode = createusercode;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getModifytime() {
		return modifytime;
	}
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	public Date getEffecttime() {
		return effecttime;
	}
	public void setEffecttime(Date effecttime) {
		this.effecttime = effecttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getLastopertime() {
		return lastopertime;
	}
	public void setLastopertime(Date lastopertime) {
		this.lastopertime = lastopertime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
}
