package com.sdnx.st.sm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.dao.StProductSpreadDao;
import com.sdnx.st.sm.model.StProductHighestAmount;
import com.sdnx.st.sm.model.StProductSpread;
import com.sdnx.st.util.STConnectionManager;
import com.sdnx.st.util.STDBManager;

public class StProductSpreadDaoImpl extends AbstractBaseDao<StProductSpread> implements StProductSpreadDao{
	public void updateById(StProductSpread stProductSpread) throws Exception {
		Connection con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		try {
			String preSql = "update st_product_spread set limitamount="+stProductSpread.getLimitamount()+",lastopertime='"
					+stProductSpread.getLastopertime()+"' , isopen='"+stProductSpread.getIsopen()+"' where id='"+stProductSpread.getId()+"'";
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
