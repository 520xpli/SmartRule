package com.sdnx.st.sm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.dao.StRuleDetailDao;
import com.sdnx.st.sm.model.StRuleDetail;
import com.sdnx.st.util.STConnectionManager;
import com.sdnx.st.util.STDBManager;

public class StRuleDetailDaoImpl extends AbstractBaseDao<StRuleDetail> implements StRuleDetailDao{

	@Override
	public void deleteByStruccode(String struccode) throws Exception {
		Connection con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		try {
			String preSql = "delete from st_rule_detail where struccode='" + struccode + "'";
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
