package com.sdnx.st.sm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.dao.StObservationDao;
import com.sdnx.st.sm.model.StObservation;
import com.sdnx.st.util.STConnectionManager;
import com.sdnx.st.util.STDBManager;
import com.sdnx.st.util.StDaoFactory;

public class StObservationDaoImpl extends AbstractBaseDao<StObservation> implements StObservationDao{

	@Override
	public void deleteObservationByBusinesscode(String businesscode) throws Exception {
		Connection con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		try {
			String preSql = "delete from st_observation where businesscode=" + businesscode;
			st = con.prepareStatement(preSql);
			st.execute();
		} catch (Exception e) {
			throw e;
		}finally{
			STDBManager.getInstance().close(null, st);
		}
	}

}
