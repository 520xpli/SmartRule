package com.sdnx.st.sm.dao;

import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.model.StRuleDetail;

public interface StRuleDetailDao extends BaseDao<StRuleDetail>{

	void deleteByStruccode(String struccode) throws Exception;

}
