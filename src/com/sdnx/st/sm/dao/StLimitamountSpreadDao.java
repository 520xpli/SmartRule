package com.sdnx.st.sm.dao;

import java.util.List;

import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.model.StLimitamountParm;
import com.sdnx.st.sm.vo.StLimitamountParmVo;

public interface StLimitamountSpreadDao extends BaseDao<StLimitamountParm> {

	public List<StLimitamountParmVo> queryUnRate() throws Exception;
	public List<StLimitamountParmVo> queryfamilyRate(String grade, String guar) throws Exception;
	public List<StLimitamountParmVo> queryfamilyIncomeRate(String grade, String guar) throws Exception;
	public List<StLimitamountParmVo> queryCollRate(String grade, String guar) throws Exception;
	public List<StLimitamountParmVo> queryExpand(String guaranteeType) throws Exception ;
	public List<StLimitamountParmVo> queryEepandRatio(String guar) throws Exception;
	public List<StLimitamountParmVo> query(StLimitamountParm stLimitamountParm) throws Exception;
}
