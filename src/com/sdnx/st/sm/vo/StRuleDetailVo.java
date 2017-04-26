package com.sdnx.st.sm.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StRuleDetailVo {
	//���
	private String id;
	//�ṹ����
	private String struccode;
	//����������
	private String detailcode;
	//����ǰ��
	private String precondition;
	//����������
	private String resulttype;
	//������
	private String ruleresult;
	//������ʱ��
	private Date lastopertime;
	//ǰ����������(1Ϊǰ��������2Ϊ���)
	private String preOrRe;
	//������������Ϣ
	private List rowList = new ArrayRowList();
	
	//�����б����ݵ��ύ
	private class ArrayRowList extends ArrayList{
		public Object get(int index){
			StRuleDetailVo a = new StRuleDetailVo();
			if (index >= super.size()) {
				int length = index - super.size() + 1;
				for (int i = 0; i < length; i++) {
					super.add(new StRowVo());
				}
			}
			return super.get(index);
		}
	}
	
	public String getPreOrRe() {
		return preOrRe;
	}
	public void setPreOrRe(String preOrRe) {
		this.preOrRe = preOrRe;
	}
	public List<StRowVo> getRowList() {
		return rowList;
	}
	public void setRowList(List<StRowVo> rowList) {
		this.rowList = rowList;
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
	public String getDetailcode() {
		return detailcode;
	}
	public void setDetailcode(String detailcode) {
		this.detailcode = detailcode;
	}
	public String getPrecondition() {
		return precondition;
	}
	public void setPrecondition(String precondition) {
		this.precondition = precondition;
	}
	public String getResulttype() {
		return resulttype;
	}
	public void setResulttype(String resulttype) {
		this.resulttype = resulttype;
	}
	
	public String getRuleresult() {
		return ruleresult;
	}
	public void setRuleresult(String ruleresult) {
		this.ruleresult = ruleresult;
	}
	public Date getLastopertime() {
		return lastopertime;
	}
	public void setLastopertime(Date lastopertime) {
		this.lastopertime = lastopertime;
	}
}
