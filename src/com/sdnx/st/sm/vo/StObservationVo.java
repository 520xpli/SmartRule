package com.sdnx.st.sm.vo;

import java.util.Date;

public class StObservationVo {
	//编号
	private String id;
	//结构编码
	private String struccode;
	//观察期记录编码
	private String observationcode;
	//开始时间
	private String starttime;
	//结束时间
	private String endtime;
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
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
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
