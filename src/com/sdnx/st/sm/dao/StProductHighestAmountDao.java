package com.sdnx.st.sm.dao;

import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.model.StProductHighestAmount;

public interface StProductHighestAmountDao extends BaseDao<StProductHighestAmount>{

	public void updateById(StProductHighestAmount stProductHighestAmount) throws Exception;
}
