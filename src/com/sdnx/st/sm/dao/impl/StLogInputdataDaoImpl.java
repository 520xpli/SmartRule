package com.sdnx.st.sm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;

import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.dao.StLogInputdataDao;
import com.sdnx.st.sm.model.StLogInputdata;
import com.sdnx.st.util.STConnectionManager;
import com.sdnx.st.util.STDBManager;

public class StLogInputdataDaoImpl extends AbstractBaseDao<StLogInputdata> implements StLogInputdataDao{
	@Override
	public void batchInsert(List<StLogInputdata> modelList) throws Exception{
		if(modelList == null || modelList.size() == 0) return;
		StringBuffer sb = new StringBuffer("insert into st_log_inputdata(id,inputdatacode,objectcode,"
				+ "propertykey,propertyvalue,instcitycode,lastopertime) values ");
		String start = "'";
		String end = "',";
		for(int i = modelList.size() - 1; i >= 0; i--){
			StLogInputdata li = modelList.get(i);
			sb.append("(");
			//id
			sb.append("'");
			sb.append(UUID.randomUUID().toString().replaceAll("-", ""));
			sb.append("',");
			//inputdatacode
			sb.append(start);
			sb.append(li.getInputdatacode());
			sb.append(end);
			//objectcode
			sb.append(start);
			sb.append(li.getObjectcode());
			sb.append(end);
			//propertykey
			sb.append(start);
			sb.append(li.getPropertykey());
			sb.append(end);
			//propertyvalue
			if(li.getPropertyvalue() != null){
				sb.append(start);
				sb.append(li.getPropertyvalue().replaceAll("'", ""));
				sb.append(end);
			}else{
				sb.append("null,");
			}
			//instcitycode
			sb.append(start);
			sb.append(li.getInstcitycode());
			sb.append(end);
			//lastopertime
			sb.append(start);
			sb.append(li.getLastopertime());
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
