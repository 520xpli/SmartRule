package com.sdnx.st.sm.dao;

import java.util.Date;
import java.util.List;

import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.model.StStructrue;
import com.sdnx.st.sm.vo.StStructrueVo;

public interface StStructrueDao extends BaseDao<StStructrue>{

	void deleteByCode(String struccode) throws Exception;
	public List<StStructrueVo> queryRateScoreDetail(String logcode) throws Exception;
	public List<StStructrueVo> queryRateScoreDetailHi(String businesscode,String operperiod) throws Exception;

}
