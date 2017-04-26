package com.sdnx.st.sm.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sdnx.st.sm.base.BaseForm;
import com.sdnx.st.sm.model.StModel;
import com.sdnx.st.sm.vo.StOrgSpreadVo;

public class StRuleMaintainForm extends BaseForm{
	
	private StModel stModel = new StModel();
	private List<StModel> ModelList = new ArrayList<StModel>();
	private List<StOrgSpreadVo> orgSpreadVoList = new ArrayList<StOrgSpreadVo>();
	private String ruleResult;
	public List<StOrgSpreadVo> getOrgSpreadVoList() {
		return orgSpreadVoList;
	}
	public void setOrgSpreadVoList(List<StOrgSpreadVo> orgSpreadVoList) {
		this.orgSpreadVoList = orgSpreadVoList;
	}
	public StOrgSpreadVo getStOrgSpreadVo() {
		return stOrgSpreadVo;
	}
	public void setStOrgSpreadVo(StOrgSpreadVo stOrgSpreadVo) {
		this.stOrgSpreadVo = stOrgSpreadVo;
	}
	private StOrgSpreadVo stOrgSpreadVo = new StOrgSpreadVo();
	public StModel getStModel() {
		return stModel;
	}
	public void setStModel(StModel stModel) {
		this.stModel = stModel;
	}
	public List<StModel> getModelList() {
		return ModelList;
	}
	public void setModelList(List<StModel> modelList) {
		ModelList = modelList;
	}
	public String getRuleResult() {
		return ruleResult;
	}
	public void setRuleResult(String ruleResult) {
		this.ruleResult = ruleResult;
	}
	
}
