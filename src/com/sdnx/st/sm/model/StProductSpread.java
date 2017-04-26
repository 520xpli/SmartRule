package com.sdnx.st.sm.model;

import java.math.BigDecimal;
import java.util.Date;

import com.sdnx.st.sm.base.BaseModel;

public class StProductSpread extends BaseModel{
	//���
	private String id;
	//�����˱���
	private String createusercode;
	//����ʱ��
	private Date createtime;
	//��Ʒ����
	private String productcode;
	//��Ʒ����
	private String productname;
	//���
	private BigDecimal limitamount;
	//�Ƿ���
	private String isopen;
	//�Ƿ���Ч
	private String iseffect;
	//����ʱ��
	private Date endtime;
	//������ʱ��
	private Date lastopertime;
	//��������
	private String orgcode;
	//��Ʒ����
	private String productcentercode;
	public String getOrgcode() {
		return orgcode;
	}
	public String getProductcentercode() {
		return productcentercode;
	}
	public void setProductcentercode(String productcentercode) {
		this.productcentercode = productcentercode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
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
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public BigDecimal getLimitamount() {
		return limitamount;
	}
	public void setLimitamount(BigDecimal limitamount) {
		this.limitamount = limitamount;
	}
	public String getIsopen() {
		return isopen;
	}
	public void setIsopen(String isopen) {
		this.isopen = isopen;
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
