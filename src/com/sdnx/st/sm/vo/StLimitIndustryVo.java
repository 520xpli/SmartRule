package com.sdnx.st.sm.vo;

import java.util.Date;

public class StLimitIndustryVo {
	//���
	private String id;
	//�����˱���
	private String createusercode;
	//����ʱ��
	private Date createtime;
	//��ҵ����
	private String industrycode;
	//��ҵ����
	private String industryname;
	//�Ƿ��޿�
	private String islimit;
	//�Ƿ���Ч
	private String iseffect;
	//����ʱ��
	private Date endtime;
	//������ʱ��
	private Date lastopertime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getIndustrycode() {
		return industrycode;
	}
	public void setIndustrycode(String industrycode) {
		this.industrycode = industrycode;
	}
	public String getIndustryname() {
		return industryname;
	}
	public void setIndustryname(String industryname) {
		this.industryname = industryname;
	}
	public String getIslimit() {
		return islimit;
	}
	public void setIslimit(String islimit) {
		this.islimit = islimit;
	}
	public String getIseffect() {
		return iseffect;
	}
	public void setIseffect(String iseffect) {
		this.iseffect = iseffect;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Date getLastopertime() {
		return lastopertime;
	}
	public void setLastopertime(Date lastopertime) {
		this.lastopertime = lastopertime;
	}
	
}
