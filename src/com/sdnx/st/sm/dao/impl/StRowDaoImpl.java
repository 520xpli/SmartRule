package com.sdnx.st.sm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.dao.StRowDao;
import com.sdnx.st.sm.model.StRow;
import com.sdnx.st.sm.vo.StRowVo;
import com.sdnx.st.util.STConnectionManager;
import com.sdnx.st.util.STDBManager;

public class StRowDaoImpl extends AbstractBaseDao<StRow> implements StRowDao{

	@Override
	public void deleteByDetailCode(String detailcode) throws Exception {
		Connection con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		try {
			String preSql = "delete from st_row where detailcode='" + detailcode + "'";
			st = con.prepareStatement(preSql);
			st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			STDBManager.getInstance().close(null, st);
		}
		
	}

	@Override
	public List<StRowVo> queryBydetailCode(String detailCode) throws Exception {
		String preSql = "select * from st_row where detailcode='" + detailCode + "' order by rowcount";
		
		return queryListBySql(preSql, StRowVo.class);
	}

}
