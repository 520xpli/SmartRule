package com.sdnx.st.sm.model;

import java.util.Date;

import com.sdnx.st.sm.base.BaseModel;

public class StRateRuler extends BaseModel{

	//id
	private String id;
	//创建人
	private String createusercode;
	//创建时间
	private Date createtime;
	//评级编码
	private String ratecode;
	//评级上限
	private Integer rateup;
	//评级下限
	private Integer ratedown;
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
	public String getRatecode() {
		return ratecode;
	}
	public void setRatecode(String ratecode) {
		this.ratecode = ratecode;
	}
	public Integer getRateup() {
		return rateup;
	}
	public void setRateup(Integer rateup) {
		this.rateup = rateup;
	}
	public Integer getRatedown() {
		return ratedown;
	}
	public void setRatedown(Integer ratedown) {
		this.ratedown = ratedown;
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
