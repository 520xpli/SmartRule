package com.sdnx.st.sm.action;

import org.apache.struts.action.ActionForm;

public class StLimitIndustryForm extends ActionForm{
	//当前时间用于解决ie ajax缓存问题
	private String currentTime;
	
	//行业编码
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	
}
