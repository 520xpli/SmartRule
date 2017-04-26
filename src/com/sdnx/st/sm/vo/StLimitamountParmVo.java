package com.sdnx.st.sm.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StLimitamountParmVo {
	//编号
	private String id;
	//创建人编码
	private String createusercode;
	//创建时间
	private Date createtime;
	//产品编码
	private String productcode;
	//期限编码
	private String termcode;
	//评级编码
	private String gradecode;
	//期限名称
	private String termname;
	//评级名称
	private String gradename;
	public String getTermname() {
		return termname;
	}
	public void setTermname(String termname) {
		this.termname = termname;
	}
	public String getGradename() {
		return gradename;
	}
	public void setGradename(String gradename) {
		this.gradename = gradename;
	}
	//评级相关净资产系数
	private BigDecimal rateralatefamilynetassetratio;
	public String getCreateusercode() {
		return createusercode;
	}
	public void setCreateusercode(String createusercode) {
		this.createusercode = createusercode;
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
	//家庭年净收入系数
	private BigDecimal familynetinratio;
	//评级无关净资产系数
	private BigDecimal unratefamilynetassetratio;
	//抵质押系数
	private BigDecimal collateralratio;
	//是否生效
	private String iseffect;
	//结束时间
	private Date endtime;
	//最后操作时间
	private Date lastopertime;
	//产品中类编码
	private String productcentercode;
	//机构编码
	private String orgcode;
	//担保方式
	private String guaranteetype;
	//扩张系数
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
	//额度信息
	private List<StLimitamountParmVo> limitAmountVoList = new ArrayDetailList();
	
	// 用于列表数据的提交
	private class ArrayDetailList extends ArrayList<StLimitamountParmVo> {
		public StLimitamountParmVo get(int index) {
			if (index >= super.size()) {
				int length = index - super.size() + 1;
				for (int i = 0; i < length; i++) {
					super.add(new StLimitamountParmVo());
				}
			}
			return super.get(index);
		}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public List<StLimitamountParmVo> getLimitAmountVoList() {
		return limitAmountVoList;
	}
	public void setLimitAmountVoList(List<StLimitamountParmVo> limitAmountVoList) {
		this.limitAmountVoList = limitAmountVoList;
	}
	public String getGuaranteetype() {
		return guaranteetype;
	}
	public void setGuaranteetype(String guaranteetype) {
		this.guaranteetype = guaranteetype;
	}
	public String getcreateusercode() {
		return createusercode;
	}
	public void setcreateusercode(String createusercode) {
		this.createusercode = createusercode;
	}
	public BigDecimal getCollateralratio() {
		return collateralratio;
	}
	public void setCollateralratio(BigDecimal collateralratio) {
		this.collateralratio = collateralratio;
	}
	
}
