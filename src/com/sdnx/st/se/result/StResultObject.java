package com.sdnx.st.se.result;

public class StResultObject {
	private String result;
	private String code;
	private String singleRuleDescription;
	private String groupRuleDescription;
	private int finalResult = Integer.MIN_VALUE;
	
	
	public String getSingleRuleDescription() {
		return singleRuleDescription;
	}
	public void setSingleRuleDescription(String singleRuleDescription) {
		this.singleRuleDescription = singleRuleDescription;
	}
	public String getGroupRuleDescription() {
		return groupRuleDescription;
	}
	public void setGroupRuleDescription(String groupRuleDescription) {
		this.groupRuleDescription = groupRuleDescription;
	}
	public int getFinalResult() {
		return finalResult;
	}
	public void setFinalResult(int finalResult) {
		this.finalResult = finalResult;
	}

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
