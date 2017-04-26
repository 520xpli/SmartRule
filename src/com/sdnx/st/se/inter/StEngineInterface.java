package com.sdnx.st.se.inter;

import java.util.List;
import java.util.Map;

import com.sdnx.st.dp.model.PublicData;
import com.sdnx.st.se.result.StResultObject;

public interface StEngineInterface {
	StResultObject StAdmitWithMap(List<Map<String,Object>> dataSource,PublicData pd );
	StResultObject StFirstTrialWithMap(List<Map<String,Object>> dataSource,PublicData pd );
	StResultObject StLastTrialWithMap(List<Map<String,Object>> dataSource,PublicData pd );
	StResultObject StCalculateLimtAmoutWithMap(List<Map<String,Object>> dataSource,PublicData pd );
	StResultObject StRateWithMap(List<Map<String,Object>> dataSource,PublicData pd );
	StResultObject StProcessWithMap(List<Map<String,Object>> dataSource,PublicData pd );
	StResultObject StAdmitWithObject(List<Object> dataSource,PublicData pd );
	StResultObject StFirstTrialWithObject(List<Object> dataSource,PublicData pd );
	StResultObject StLastTrialWithObject(List<Object> dataSource,PublicData pd );
	StResultObject StCalculateLimtAmoutWithObject(List<Object> dataSource,PublicData pd );
	StResultObject StRateWithObject(List<Object> dataSource,PublicData pd );
	StResultObject StProcessWithObject(List<Object> dataSource,PublicData pd );
	boolean StCheckRuleCompleteness();
	
	StResultObject StRateWithObjectNotRecordLog(List<Object> dataSource,PublicData pd );
}
