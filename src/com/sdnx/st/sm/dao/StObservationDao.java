package com.sdnx.st.sm.dao;

import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.model.StObservation;

public interface StObservationDao extends BaseDao<StObservation>{

	void deleteObservationByBusinesscode(String businesscode) throws Exception;
}
