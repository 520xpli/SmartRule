package com.sdnx.st.sm.model;

import java.util.Date;

import com.sdnx.st.sm.base.BaseModel;

public class StDatatype extends BaseModel{
	//编号
	private String id;
	//数据类型编码
	private String datatypecode;
	//类型名称
	private String datatypename;
	//字典编码
	private String dictcode;
	//是否枚举
	private String isenum;
	//最后操作时间
	private Date lastopertime;
	public String getDatatypecode() {
		return datatypecode;
	}
	public void setDatatypecode(String datatypecode) {
		this.datatypecode = datatypecode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDatatypename() {
		return datatypename;
	}
	public void setDatatypename(String datatypename) {
		this.datatypename = datatypename;
	}
	public String getDictcode() {
		return dictcode;
	}
	public void setDictcode(String dictcode) {
		this.dictcode = dictcode;
	}
	public String getIsenum() {
		return isenum;
	}
	public void setIsenum(String isenum) {
		this.isenum = isenum;
	}
	public Date getLastopertime() {
		return lastopertime;
	}
	public void setLastopertime(Date lastopertime) {
		this.lastopertime = lastopertime;
	}
	public static void main(String args[]){
		String a = "a";
		a += "b";
		System.out.println(a);
	}
}
