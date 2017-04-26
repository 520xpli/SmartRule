package com.sdnx.st.sm.vo;

import java.util.Date;

public class StLimitIndustryVo {
	//编号
	private String id;
	//创建人编码
	private String createusercode;
	//创建时间
	private Date createtime;
	//行业编码
	private String industrycode;
	//行业名称
	private String industryname;
	//是否限控
	private String islimit;
	//是否生效
	private String iseffect;
	//结束时间
	private Date endtime;
	//最后操作时间
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
