package com.sdnx.st.sm.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StStructrueVo {
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
	private Integer seq;
	// �����ִ�й���
	private String objectrule;
	// ������ʱ��
	private Date lastopertime;
	// ����ģ����ɫ
	private StModuleVo stModuleVo = new StModuleVo();
	// ������ɫ
	private StRuleVo stRuleVo = new StRuleVo();
	// �ӽṹ��Ϣ
	private List<StStructrueVo> childList = new ArrayList<StStructrueVo>();
	// ���ṹ��Ϣ
	private StStructrueVo parent;
	// ���������Ϣ
	private List<StRuleDetailVo> detailList = new ArrayDetailList();
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
	// ������
	private String ruleresult;
	//��������
	private String ruleData;
	
	public String getRuleData() {
		return ruleData;
	}

	public void setRuleData(String ruleData) {
		this.ruleData = ruleData;
	}

	public String getRuleresult() {
		return ruleresult;
	}

	public void setRuleresult(String ruleresult) {
		this.ruleresult = ruleresult;
	}
	// �����б����ݵ��ύ
	private class ArrayDetailList extends ArrayList<StRuleDetailVo> {
		public StRuleDetailVo get(int index) {
			if (index >= super.size()) {
				int length = index - super.size() + 1;
				for (int i = 0; i < length; i++) {
					super.add(new StRuleDetailVo());
				}
			}
			return super.get(index);
		}
	}

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

	public List<StRuleDetailVo> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<StRuleDetailVo> detailList) {
		this.detailList = detailList;
	}

	public StStructrueVo getParent() {
		return parent;
	}

	public void setParent(StStructrueVo parent) {
		this.parent = parent;
	}

	public List<StStructrueVo> getChildList() {
		return childList;
	}

	public void setChildList(List<StStructrueVo> childList) {
		this.childList = childList;
	}

	public StRuleVo getStRuleVo() {
		return stRuleVo;
	}

	public void setStRuleVo(StRuleVo stRuleVo) {
		this.stRuleVo = stRuleVo;
	}

	public StModuleVo getStModuleVo() {
		return stModuleVo;
	}

	public void setStModuleVo(StModuleVo stModuleVo) {
		this.stModuleVo = stModuleVo;
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

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
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
