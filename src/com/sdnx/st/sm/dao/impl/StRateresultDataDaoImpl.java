package com.sdnx.st.sm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;

import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.dao.StRateresultDataDao;
import com.sdnx.st.sm.model.StRateresultData;
import com.sdnx.st.util.STConnectionManager;
import com.sdnx.st.util.STDBManager;

public class StRateresultDataDaoImpl extends AbstractBaseDao<StRateresultData> implements StRateresultDataDao{
	@Override
	public void batchInsert(List<StRateresultData> modelList) throws Exception{
		if(modelList == null || modelList.size() == 0) return;
		if(modelList == null || modelList.size() == 0) return;
		StringBuffer sb = new StringBuffer("insert into st_rateresult_data(id,rateresultcode,datakey,datavalue,"
				+ "dataname,isenum,dictcode,instcitycode,lastopertime) values ");
		String start = "'";
		String end = "',";
		for(int i = modelList.size() - 1; i >= 0; i--){
			StRateresultData data = modelList.get(i);
			sb.append("(");
			//id
			sb.append("'");
			sb.append(UUID.randomUUID().toString().replaceAll("-", ""));
			sb.append("',");
			//rateresultcode
			sb.append(start);
			sb.append(data.getRateresultcode());
			sb.append(end);
			//datakey
			sb.append(start);
			sb.append(data.getDatakey());
			sb.append(end);
			//datavalue
			if(data.getDatavalue() != null){
				sb.append(start);
				sb.append(data.getDatavalue());
				sb.append(end);
			}else{
				sb.append("null,");
			}
			//dataname
			sb.append(start);
			sb.append(data.getDataname());
			sb.append(end);
			//isenum
			sb.append(start);
			sb.append(data.getIsenum());
			sb.append(end);
			//dictcode
			sb.append(start);
			sb.append(data.getDictcode());
			sb.append(end);
			//instcitycode
			sb.append(start);
			sb.append(data.getInstcitycode());
			sb.append(end);
			//lastopertime
			sb.append(start);
			sb.append(data.getLastopertime());
			sb.append("'");
			
			sb.append("),");
		}
		String sql = sb.toString();
		sql = sql.substring(0, sql.length() - 1);
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = STConnectionManager.getInstance().getConnection();
			st = conn.prepareStatement(sql);
			st.execute();
		} catch (Exception e) {
			throw e;
		}finally{
			STDBManager.getInstance().close(null, st);
		}
	}
}
