package com.sdnx.st.sm.dao;

import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.model.StProductSpread;

public interface StProductSpreadDao extends BaseDao<StProductSpread>{

	public void updateById(StProductSpread stProductSpread) throws Exception;
}
