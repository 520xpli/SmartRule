package com.sdnx.st.sm.dao;

import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.model.StLimitIndustry;

public interface StLimitIndustryDao extends BaseDao<StLimitIndustry>{

	void deleteByIndustryCode(String code) throws Exception;

}
