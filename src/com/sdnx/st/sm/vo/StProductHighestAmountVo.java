package com.sdnx.st.sm.vo;

import java.math.BigDecimal;
import java.util.Date;

public class StProductHighestAmountVo {
	// 编号
	private String id;
	// 创建人编码
	private String createusercode;
	// 创建时间
	private Date createtime;
	// 产品编码
	private String productcode;
	// 产品名称
	private String productname;
	// 额度
	private BigDecimal limitamount;
	// 是否生效
	private String iseffect;
	// 结束时间
	private Date endtime;
	// 最后操作时间
	private Date lastopertime;
	// 机构编码
	private String orgcode;
	//产品中类
	private String productmiddlecode;
	//机构名
	private String orgName;
	//产品中类名
	private String productMiddleName;
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getProductMiddleName() {
		return productMiddleName;
	}

	public void setProductMiddleName(String productMiddleName) {
		this.productMiddleName = productMiddleName;
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

	public String getProductmiddlecode() {
		return productmiddlecode;
	}

	public void setProductmiddlecode(String productmiddlecode) {
		this.productmiddlecode = productmiddlecode;
	}


}
