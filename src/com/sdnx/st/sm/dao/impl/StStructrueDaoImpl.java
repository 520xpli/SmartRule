package com.sdnx.st.sm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.dao.StStructrueDao;
import com.sdnx.st.sm.model.StStructrue;
import com.sdnx.st.sm.vo.StStructrueVo;
import com.sdnx.st.util.STConnectionManager;
import com.sdnx.st.util.STDBManager;

public class StStructrueDaoImpl extends AbstractBaseDao<StStructrue> implements StStructrueDao {
	@Override
	public void deleteByCode(String struccode) throws Exception {
		Connection con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		try {
			String preSql = "delete from st_structrue where struccode='" + struccode + "'";
			st = con.prepareStatement(preSql);
			st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			STDBManager.getInstance().close(null, st);
		}
	}
	
	//评级分数详细查询
	public List<StStructrueVo> queryRateScoreDetail(String logcode) throws Exception {
		String sql = "(select s.strucname, s.struccode, l.ruleresult,l.deptcode from st_rule_log l left join st_structrue s"
					+" on l.struccode=s.struccode where l.inputdatacode='" + logcode + "')"; 
		sql += " union all";
		sql += "(select s.strucname, s.struccode, l.ruleresult,l.deptcode from st_rule_log_hi l left join st_structrue s"
				+" on l.struccode=s.struccode where l.inputdatacode='" + logcode + "')"; 
		return queryListBySql(sql, StStructrueVo.class);
	}
	//评级分数详细查询
	public List<StStructrueVo> queryRateScoreDetailHi(String businesscode,String operperiod) throws Exception {
		String sql = "select s.strucname,l.ruleresult from st_structrue s left join" +
					" st_rule_log_hi l on s.struccode=l.struccode where businesscode='"+businesscode+"' and operperiod='"+operperiod+"' order by l.id asc";
		return queryListBySql(sql, StStructrueVo.class);
	}
}
