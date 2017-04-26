package com.sdnx.st.sm.dao;

import java.util.List;

import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.model.StRateResult;
import com.sdnx.st.sm.vo.StRateResultVo;

public interface StRateResultDao extends BaseDao<StRateResult>{

	public List<StRateResultVo> querybymodel(StRateResult stRateResult,List<String> lists,String datefrom,String dateTo)  throws Exception;

	List<StRateResultVo> querybyM(StRateResult stRateResult, List<String> lists, String datefrom, String dateTo)
			throws Exception;
}
