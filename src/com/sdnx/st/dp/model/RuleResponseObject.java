package com.sdnx.st.dp.model;

public class RuleResponseObject {
	/**
	 * ���ر���
	 */
	private String code;
	/**
	 * ����������Ϣ
	 */
	private String desc;
	/**
	 * ���ؽ��
	 */
	private String result;
	
	/**
	 * ���ؼ�����
	 */
	private Double cseqLmt;
	
	/**
	 * ��ȡ���ؼ�����
	 * @return
	 */
	
	/**
	 * ��ֿ�����
	 */
	private String rrModel;
	
	/**
	 * ���ֿ���߶��
	 */
	private String rateHighestaMount;

	/**
	 * ������������
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
	 * ��ȡ��ֿ�����
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
