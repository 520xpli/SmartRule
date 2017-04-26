package com.sdnx.st.se.calculator;

import java.util.List;
import java.util.Map;

import com.sdnx.st.dp.model.PublicData;
import com.sdnx.st.exceptions.StCalculateException;
import com.sdnx.st.se.result.StResultObject;
import com.sdnx.st.sm.vo.StRuleDetailVo;

public interface StEngineCalculatorInterface {
	
	StResultObject calculate(List<Map<String, Object>> dataSource,String typeCode, PublicData pd);
	
	StResultObject calculate(List<Map<String, Object>> dataSource,String typeCode, PublicData pd, boolean isRecordLog);
	
	String parseFormula(Map<String, Object> object,List<StRuleDetailVo> ruleDetailList) throws StCalculateException;
}
