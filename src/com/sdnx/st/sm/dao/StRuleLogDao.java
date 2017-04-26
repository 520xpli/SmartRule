package com.sdnx.st.sm.dao;

import java.util.List;

import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.model.StRuleLog;
import com.sdnx.st.sm.vo.StRuleLogVo;

public interface StRuleLogDao extends BaseDao<StRuleLog>{

	List<StRuleLogVo> querExportData(String orgCodes, String startTime, String endTime) throws Exception;
	List<StRuleLogVo> queryRateLog(String businessCode) throws Exception;

}
