package com.sdnx.st.sm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.dao.StProductHighestAmountDao;
import com.sdnx.st.sm.model.StProductHighestAmount;
import com.sdnx.st.util.STConnectionManager;
import com.sdnx.st.util.STDBManager;

public class StProductHighestAmountDaoImpl extends AbstractBaseDao<StProductHighestAmount> implements StProductHighestAmountDao{

	public void updateById(StProductHighestAmount stProductHighestAmount) throws Exception {
		Connection con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		try {
			String preSql = "update st_product_highestamount set limitamount="+stProductHighestAmount.getLimitamount()+",lastopertime='"
					+stProductHighestAmount.getLastopertime()+"' where id='"+stProductHighestAmount.getId()+"'";
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
