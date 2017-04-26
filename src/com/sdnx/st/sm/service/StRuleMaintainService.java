package com.sdnx.st.sm.service;


import java.util.List;

import com.sdnx.st.sm.action.StRuleForm;
import com.sdnx.st.sm.base.BaseService;
import com.sdnx.st.sm.model.StStructrue;
import com.sdnx.st.sm.vo.StStructrueVo;

public interface StRuleMaintainService extends BaseService<StStructrue>{
	void querySingleRule(Long strucId);
	String querySubTree(String modelCode, String isShowCode);
	void queryStrucInfo(StRuleForm srf);
	String deleteRule(StRuleForm srf);
	String[] insertRule(StRuleForm srf);
	String queryPorperty(String objectcode, String isShowCode);
	List<StStructrueVo> queryListByModel(StStructrue structrue);
	String queryProduct(String modelcode);
	String queryExistProduct(String modelcode);
	String queryNotExistProduct(String modelcode);
	boolean hasNewProduct(String modelcode);
	String copyProductLimitAmount(StStructrueVo ssv);
	String queryLittleProduct();
}
