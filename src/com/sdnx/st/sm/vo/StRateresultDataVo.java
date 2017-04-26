package com.sdnx.st.sm.vo;

public class StRateresultDataVo {
	//id
		private String id;
		//评级结果编码
		private String rateresultcode;
		//数据标识
		private String datakey;
		//数据值
		private String datavalue;
		//数据名称
		private String dataname;
		//是否枚举
		private String isenum;
		//字典编码
		private String dictcode;
		//最后操作时间
		private java.util.Date lastopertime;
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
		public String getRateresultcode() {
			return rateresultcode;
		}
		public void setRateresultcode(String rateresultcode) {
			this.rateresultcode = rateresultcode;
		}
		public String getDatakey() {
			return datakey;
		}
		public void setDatakey(String datakey) {
			this.datakey = datakey;
		}
		public String getDatavalue() {
			return datavalue;
		}
		public void setDatavalue(String datavalue) {
			this.datavalue = datavalue;
		}
		public String getDataname() {
			return dataname;
		}
		public void setDataname(String dataname) {
			this.dataname = dataname;
		}
		public String getIsenum() {
			return isenum;
		}
		public void setIsenum(String isenum) {
			this.isenum = isenum;
		}
		public String getDictcode() {
			return dictcode;
		}
		public void setDictcode(String dictcode) {
			this.dictcode = dictcode;
		}
		public java.util.Date getLastopertime() {
			return lastopertime;
		}
		public void setLastopertime(java.util.Date lastopertime) {
			this.lastopertime = lastopertime;
		}
		
}
