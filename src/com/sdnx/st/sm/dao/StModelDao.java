package com.sdnx.st.sm.dao;

import java.util.List;
import java.util.Map;

import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.model.StModel;
import com.sdnx.st.sm.model.StModule;
import com.sdnx.st.sm.model.StObject;
import com.sdnx.st.sm.model.StRow;
import com.sdnx.st.sm.model.StRule;
import com.sdnx.st.sm.model.StRuleDetail;
import com.sdnx.st.sm.vo.StModelVo;
import com.sdnx.st.sm.vo.StStructrueVo;

public interface StModelDao extends BaseDao<StModel>{
	public String getMaxVersion(String orgcode,String classification) throws Exception;
	public void getMapBySql(String modelId,Map<String,StModelVo> modelVoMap , Map<String,List<StStructrueVo>> structrueListMap
			, Map<String,StModule> moduleMap ,Map<String,List<StRule>> ruleListMap,Map<String,List<StRuleDetail>> ruleDetailListMap , Map<String,List<StRow>> rowListMap ,Map<String,List<StObject>> objectListMap) throws Exception;
	Integer getNextSeqVal();
}
