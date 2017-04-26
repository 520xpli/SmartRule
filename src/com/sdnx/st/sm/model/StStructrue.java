package com.sdnx.st.sm.model;

import java.util.Date;

import com.sdnx.st.sm.base.BaseModel;

public class StStructrue extends BaseModel {
	// ���
	private String id;
	// ģ�ͱ���
	private String modelcode;
	// �ṹ����
	private String struccode;
	// ���ṹ����
	private String parentstruccode;
	// �㼶
	private Short level;
	// �ṹ����
	private String strucname;
	// ����
	private String description;
	// ִ��˳��
	private Short seq;
	// �����ִ�й���
	private String objectrule;
	// ������ʱ��
	private Date lastopertime;
	// ��ʾ��Ϣ
	private String prompt;
	// ����
	private String classification;
	// ��������
	private String relatecode;
	// ��չ�ֶ�
	private String misc;
	//�Ƿ�Ҷ�ӽڵ�
	private String isleaf;

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getRelatecode() {
		return relatecode;
	}

	public void setRelatecode(String relatecode) {
		this.relatecode = relatecode;
	}

	public String getMisc() {
		return misc;
	}

	public void setMisc(String misc) {
		this.misc = misc;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
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

	public String getStruccode() {
		return struccode;
	}

	public void setStruccode(String struccode) {
		this.struccode = struccode;
	}

	public String getParentstruccode() {
		return parentstruccode;
	}

	public void setParentstruccode(String parentstruccode) {
		this.parentstruccode = parentstruccode;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public String getStrucname() {
		return strucname;
	}

	public void setStrucname(String strucname) {
		this.strucname = strucname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getSeq() {
		return seq;
	}

	public void setSeq(Short seq) {
		this.seq = seq;
	}

	public String getObjectrule() {
		return objectrule;
	}

	public void setObjectrule(String objectrule) {
		this.objectrule = objectrule;
	}

	public Date getLastopertime() {
		return lastopertime;
	}

	public void setLastopertime(Date lastopertime) {
		this.lastopertime = lastopertime;
	}

	public String getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}
	
}
