package com.sdnx.st.sm.dao;

import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.model.StRateRuler;

public interface StRateRulerDao extends BaseDao<StRateRuler>{
	String getRateByScore(String score) throws Exception;
}
