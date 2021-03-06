package com.sdnx.st.sm.vo;

import java.util.Date;
import java.util.Map;

public class StObjectPropertyVo {
	//编号
	private String id;
	//对象编码
	private String objectcode;
	//属性编码
	private String propertycode;
	//属性标识
	private String propertykey;
	//属性名称
	private String propertyname;
	//数据类型编码
	private String datatypecode;
	//是否可为空
	private String isnull;
	//最后操作时间
	private Date lastopertime;
	//属性枚举值
	private Map edatas;
	//属性字典编码
	private String dictcode;
	//属性是否枚举
	private String isenum;
	//数据类型对象
	private StDatatypeVo datatypeVo;
	
	public StDatatypeVo getDatatypeVo() {
		return datatypeVo;
	}
	public void setDatatypeVo(StDatatypeVo datatypeVo) {
		this.datatypeVo = datatypeVo;
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
	public Map getEdatas() {
		return edatas;
	}
	public void setEdatas(Map edatas) {
		this.edatas = edatas;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObjectcode() {
		return objectcode;
	}
	public void setObjectcode(String objectcode) {
		this.objectcode = objectcode;
	}
	public String getPropertycode() {
		return propertycode;
	}
	public void setPropertycode(String propertycode) {
		this.propertycode = propertycode;
	}
	public String getPropertykey() {
		return propertykey;
	}
	public void setPropertykey(String propertykey) {
		this.propertykey = propertykey;
	}
	public String getPropertyname() {
		return propertyname;
	}
	public void setPropertyname(String propertyname) {
		this.propertyname = propertyname;
	}
	public String getDatatypecode() {
		return datatypecode;
	}
	public void setDatatypecode(String datatypecode) {
		this.datatypecode = datatypecode;
	}
	public String getIsnull() {
		return isnull;
	}
	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}
	public Date getLastopertime() {
		return lastopertime;
	}
	public void setLastopertime(Date lastopertime) {
		this.lastopertime = lastopertime;
	}
	
}
