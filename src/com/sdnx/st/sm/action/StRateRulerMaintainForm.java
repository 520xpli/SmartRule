package com.sdnx.st.sm.action;

import java.util.ArrayList;
import java.util.List;

import com.sdnx.st.sm.base.BaseForm;
import com.sdnx.st.sm.vo.StRateRulerVo;

public class StRateRulerMaintainForm extends BaseForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StRateRulerVo stRateRulerVo = new StRateRulerVo();
	
	private List<StRateRulerVo> rateList = new ArrayList<StRateRulerVo>();

	public StRateRulerVo getStRateRulerVo() {
		return stRateRulerVo;
	}

	public void setStRateRulerVo(StRateRulerVo stRateRulerVo) {
		this.stRateRulerVo = stRateRulerVo;
	}

	public List<StRateRulerVo> getRateList() {
		return rateList;
	}

	public void setRateList(List<StRateRulerVo> rateList) {
		this.rateList = rateList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
