package com.sdnx.st.sm.vo;

import java.util.Date;

public class StLogInputdataHiVo {
	//编号
	private String id;
	//输入数据编码
	private String inputdatacode;
	//日志编码
	private String logcode;
	//对象编码
	private String objectcode;
	//对象属性名
	private String propertykey;
	//对象属性值
	private String propertyvalue;
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
	public String getInputdatacode() {
		return inputdatacode;
	}
	public void setInputdatacode(String inputdatacode) {
		this.inputdatacode = inputdatacode;
	}
	public String getLogcode() {
		return logcode;
	}
	public void setLogcode(String logcode) {
		this.logcode = logcode;
	}
	public String getObjectcode() {
		return objectcode;
	}
	public void setObjectcode(String objectcode) {
		this.objectcode = objectcode;
	}

	public String getPropertykey() {
		return propertykey;
	}
	public void setPropertykey(String propertykey) {
		this.propertykey = propertykey;
	}
	public String getPropertyvalue() {
		return propertyvalue;
	}
	public void setPropertyvalue(String propertyvalue) {
		this.propertyvalue = propertyvalue;
	}
	public Date getLastopertime() {
		return lastopertime;
	}
	public void setLastopertime(Date lastopertime) {
		this.lastopertime = lastopertime;
	}
}
