package com.sdnx.st.sm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.dao.StLimitIndustryDao;
import com.sdnx.st.sm.model.StLimitIndustry;
import com.sdnx.st.util.STConnectionManager;
import com.sdnx.st.util.STDBManager;

public class StLimitIndustryDaoImpl extends AbstractBaseDao<StLimitIndustry> implements StLimitIndustryDao{

	@Override
	public void deleteByIndustryCode(String code) throws Exception {
		Connection con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		try {
			String preSql = "delete from st_limit_industry where industrycode='" + code + "'";
			st = con.prepareStatement(preSql);
			st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			STDBManager.getInstance().close(null, st);
		}
		
	}

}
