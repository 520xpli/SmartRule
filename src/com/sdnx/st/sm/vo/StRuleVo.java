package com.sdnx.st.sm.vo;

import java.util.Date;
import java.util.List;

public class StRuleVo {
	// ���
	private String id;
	// �ṹ����
	private String struccode;
	// �������
	private String rulecode;
	// ����ܾ����ڣ��£�
	private Integer cycle;
	// ���������
	private String parentcode;
	// �ӻ�������
	private String orgcode;
	// ���ö������
	private String objectcode;
	// ������ʱ��
	private Date lastopertime;
	//������ϸ�б�
	private List<StRuleDetailVo> ruleDetailList;

	public List<StRuleDetailVo> getRuleDetailList() {
		return ruleDetailList;
	}

	public void setRuleDetailList(List<StRuleDetailVo> ruleDetailList) {
		this.ruleDetailList = ruleDetailList;
	}

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

	public String getRulecode() {
		return rulecode;
	}

	public void setRulecode(String rulecode) {
		this.rulecode = rulecode;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public String getParentcode() {
		return parentcode;
	}

	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
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
