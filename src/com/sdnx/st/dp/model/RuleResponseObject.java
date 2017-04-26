package com.sdnx.st.dp.model;

public class RuleResponseObject {
	/**
	 * 返回编码
	 */
	private String code;
	/**
	 * 返回描述信息
	 */
	private String desc;
	/**
	 * 返回结果
	 */
	private String result;
	
	/**
	 * 返回计算额度
	 */
	private Double cseqLmt;
	
	/**
	 * 获取返回计算额度
	 * @return
	 */
	
	/**
	 * 打分卡类型
	 */
	private String rrModel;
	
	/**
	 * 评分卡最高额度
	 */
	private String rateHighestaMount;

	/**
	 * 返回流程走向
	 */
	private String authOrity;
	
	public String getAuthOrity() {
		return authOrity;
	}

	public void setAuthOrity(String authOrity) {
		this.authOrity = authOrity;
	}

	public String getRateHighestaMount() {
		return rateHighestaMount;
	}

	public void setRateHighestaMount(String rateHighestaMount) {
		this.rateHighestaMount = rateHighestaMount;
	}

	/**
	 * 获取打分卡类型
	 * @return
	 */
	public String getRrModel() {
		return rrModel;
	}

	public void setRrModel(String rrModel) {
		this.rrModel = rrModel;
	}

	public Double getCseqLmt() {
		return cseqLmt;
	}

	public void setCseqLmt(Double cseqLmt) {
		this.cseqLmt = cseqLmt;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
