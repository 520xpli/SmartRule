package com.sdnx.st.sm.model;

import java.math.BigDecimal;
import java.util.Date;

import com.sdnx.st.sm.base.BaseModel;

public class StLimitamountParm extends BaseModel{
	//���
	private String id;
	//�����˱���
	private String createusercode;
	//����ʱ��
	private Date createtime;
	//��Ʒ����
	private String productcode;
	//���ޱ���
	private String termcode;
	//��������
	private String gradecode;
	//������ؾ��ʲ�ϵ��
	private BigDecimal rateralatefamilynetassetratio;
	//��ͥ�꾻����ϵ��
	private BigDecimal familynetinratio;
	//�����޹ؾ��ʲ�ϵ��
	private BigDecimal unratefamilynetassetratio;
	//����Ѻϵ��
	private BigDecimal collateralratio;
	//�Ƿ���Ч
	private String iseffect;
	//����ʱ��
	private Date endtime;
	//������ʱ��
	private Date lastopertime;
	//��Ʒ�������
	private String productcentercode;
	//��������
	private String orgcode;
	//������ʽ
	private String guaranteetype;
	//����ϵ��
	private BigDecimal expandratio;
	public BigDecimal getExpandratio() {
		return expandratio;
	}
	public void setExpandratio(BigDecimal expandratio) {
		this.expandratio = expandratio;
	}
	public String getProductcentercode() {
		return productcentercode;
	}
	public void setProductcentercode(String productcentercode) {
		this.productcentercode = productcentercode;
	}
	public String getOrgcode() {
		return orgcode;
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
	public String getTermcode() {
		return termcode;
	}
	public void setTermcode(String termcode) {
		this.termcode = termcode;
	}
	public String getGradecode() {
		return gradecode;
	}
	public void setGradecode(String gradecode) {
		this.gradecode = gradecode;
	}
	public BigDecimal getFamilynetinratio() {
		return familynetinratio;
	}
	public void setFamilynetinratio(BigDecimal familynetinratio) {
		this.familynetinratio = familynetinratio;
	}
	public BigDecimal getRateralatefamilynetassetratio() {
		return rateralatefamilynetassetratio;
	}
	public void setRateralatefamilynetassetratio(BigDecimal rateralatefamilynetassetratio) {
		this.rateralatefamilynetassetratio = rateralatefamilynetassetratio;
	}
	public BigDecimal getUnratefamilynetassetratio() {
		return unratefamilynetassetratio;
	}
	public void setUnratefamilynetassetratio(BigDecimal unratefamilynetassetratio) {
		this.unratefamilynetassetratio = unratefamilynetassetratio;
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
	public String getGuaranteetype() {
		return guaranteetype;
	}
	public void setGuaranteetype(String guaranteetype) {
		this.guaranteetype = guaranteetype;
	}
	public BigDecimal getCollateralratio() {
		return collateralratio;
	}
	public void setCollateralratio(BigDecimal collateralratio) {
		this.collateralratio = collateralratio;
	}
	
}
