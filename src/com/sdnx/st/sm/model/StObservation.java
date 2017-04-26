package com.sdnx.st.sm.model;

import java.util.Date;

import com.sdnx.st.sm.base.BaseModel;

public class StObservation extends BaseModel{
	//编号
	private String id;
	//结构编码
	private String struccode;
	//观察期记录编码
	private String observationcode;
	//开始时间
	private Date starttime;
	//结束时间
	private Date endtime;
	//客户编码
	private String customercode;
	//业务编码
	private String businesscode;
	//最后操作时间
	private Date lastopertime;
	//地市编号
		private String instcitycode;
		
		public String getInstcitycode() {
			return instcitycode;
		}
		public void setInstcitycode(String instcitycode) {
			this.instcitycode = instcitycode;
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
	public String getObservationcode() {
		return observationcode;
	}
	public void setObservationcode(String observationcode) {
		this.observationcode = observationcode;
	}
	
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getCustomercode() {
		return customercode;
	}
	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}
	public String getBusinesscode() {
		return businesscode;
	}
	public void setBusinesscode(String businesscode) {
		this.businesscode = businesscode;
	}
	public Date getLastopertime() {
		return lastopertime;
	}
	public void setLastopertime(Date lastopertime) {
		this.lastopertime = lastopertime;
	}
	
}
