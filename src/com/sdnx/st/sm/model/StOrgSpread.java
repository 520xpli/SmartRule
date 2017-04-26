package com.sdnx.st.sm.model;

import java.util.Date;

import com.sdnx.st.sm.base.BaseModel;

public class StOrgSpread extends BaseModel{
	//编号
	private String id;
	//创建人编码
	private String createusercode;
	//创建时间
	private Date createtime;
	//机构编码
	private String orgcode;
	//机构名称
	private String orgname;
	//是否开启
	private String isopen;
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
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
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
