package com.sdnx.st.sm.action;

import org.apache.struts.action.ActionForm;

public class StLimitIndustryForm extends ActionForm{
	//��ǰʱ�����ڽ��ie ajax��������
	private String currentTime;
	
	//��ҵ����
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
