package com.sdnx.st.sm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdnx.st.sm.action.StSystemMaintainForm;
import com.sdnx.st.sm.base.BaseService;
import com.sdnx.st.sm.model.StModel;
import com.sdnx.st.sm.model.StRateResult;
import com.sdnx.st.sm.model.StRateresultData;
import com.sdnx.st.sm.utils.SmUtil.User;
import com.sdnx.st.sm.vo.StModelVo;
import com.sdnx.st.sm.vo.StRateResultVo;
import com.sdnx.st.sm.vo.StRateresultDataVo;

public interface StSystemMaintainService extends BaseService<StModel>{
	void upgrade(Long modelId);
	public List<StModelVo> queryListByModel(StModel stModel) throws Exception;
	public StModelVo queryById(String string) throws Exception ;
	public String upgradeModel(String string,User user,StSystemMaintainForm ruleForm) throws Exception;
	public String copyModel(String string,String orgcode,User user,StSystemMaintainForm ruleForm) throws Exception;
	public List<StRateResultVo> querybymodel(StRateResult stRateResult,List<String> lists,String datefrom,String dateTo) throws Exception;
	public List<StRateResultVo> queryListbymodel(StRateResult stRateResult) throws Exception;
	public List<StRateresultDataVo> queryListByModel(StRateresultData stRateresultData) throws Exception;
	public List<StRateResultVo> querybyM(StRateResult stRateResult,List<String> lists,String datefrom,String dateTo) throws Exception;
	void ruleStatisticsExport(HttpServletRequest request, HttpServletResponse response) throws Exception;
	void deleteModel(String id) throws Exception;
	public void rateScoreExport(HttpServletRequest request, HttpServletResponse response,StSystemMaintainForm systemForm) throws Exception;
	public void rateDetailQuery(StSystemMaintainForm systemForm) throws Exception;
}
