package com.sdnx.st.sm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;

import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.dao.StRuleLogDao;
import com.sdnx.st.sm.model.StRuleLog;
import com.sdnx.st.sm.vo.StRuleLogVo;
import com.sdnx.st.util.STConnectionManager;
import com.sdnx.st.util.STDBManager;

public class StRuleLogDaoImpl extends AbstractBaseDao<StRuleLog> implements StRuleLogDao{
	@Override
	public void batchInsert(List<StRuleLog> modelList) throws Exception{
		if(modelList == null || modelList.size() == 0) return;
		StringBuffer sb = new StringBuffer("insert into st_rule_log(id,logcode,inputdatacode,struccode,ruleresult,"
				+ "operatetime,businesscode,operatorcode,"
				+ "instcode,instcitycode,deptcode,operperiod,lastopertime) values ");
		for(int i = modelList.size() - 1; i >= 0; i--){
			StRuleLog rl = modelList.get(i);
			sb.append("(");
			//id
			sb.append("'");
			sb.append(UUID.randomUUID().toString().replaceAll("-", ""));
			sb.append("',");
			//logcode
			sb.append("'");
			sb.append(rl.getLogcode());
			sb.append("',");
			//inputdatacode
			sb.append("'");
			sb.append(rl.getInputdatacode());
			sb.append("',");
			//struccode
			sb.append("'");
			sb.append(rl.getStruccode());
			sb.append("',");
			//ruleresult
			sb.append("'");
			sb.append(rl.getRuleresult());
			sb.append("',");
			//operatetime
			sb.append("'");
			sb.append(rl.getOperatetime().toString());
			sb.append("',");
			//businesscode
			sb.append("'");
			sb.append(rl.getBusinesscode());
			sb.append("',");
			//operatorcode
			sb.append("'");
			sb.append(rl.getOperatorcode());
			sb.append("',");
			//instcode
			sb.append("'");
			sb.append(rl.getInstcode());
			sb.append("',");
			//instcitycode
			sb.append("'");
			sb.append(rl.getInstcitycode());
			sb.append("',");
			//deptcode
			sb.append("'");
			sb.append(rl.getDeptcode());
			sb.append("',");
			//operperiod
			sb.append("'");
			sb.append(rl.getOperperiod());
			sb.append("',");
			//lastopertime
			sb.append("'");
			sb.append(rl.getLastopertime());
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

	@Override
	public List<StRuleLogVo> querExportData(String orgCodes, String startTime, String endTime) throws Exception {
		if(orgCodes == null || startTime == null || endTime == null) return null;
		StringBuffer sb = new StringBuffer();
		sb.append("select l.instcode,l.businesscode,l.operperiod,s.classification,s.strucname"
				+ ",l.ruleresult,r.objectcode,l.operatetime,s.isleaf"
				+ " from st_rule_log_hi l left join st_structrue s on l.struccode=s.struccode" +
				 " left join (select * from st_rule where struccode in(select max(struccode) from st_rule group by struccode)) r"
				+ " on s.struccode=r.struccode where");
		sb.append(" l.instcode in " + orgCodes);
		sb.append(" and l.operatetime >= '" + startTime + "'");
		sb.append(" and l.operatetime <= '" + endTime + "'");
		sb.append(" and l.operperiod in ('STEP_1','STEP_2','STEP_3','STEP_4','STEP_7','STEP_8')");
		return queryListBySql(sb.toString(), StRuleLogVo.class);
	}

	@Override
	public List<StRuleLogVo> queryRateLog(String businessCode) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("(select l.inputdatacode from st_rule_log l where l.businesscode='" + businessCode + "' and operperiod='STEP_5' group by l.inputdatacode order by l.inputdatacode desc fetch first 2 rows only )");
		sb.append(" union all ");
		sb.append("(select l.inputdatacode from st_rule_log_hi l where l.businesscode='" + businessCode + "' and operperiod='STEP_5' group by l.inputdatacode order by l.inputdatacode desc fetch first 2 rows only )");
		return queryListBySql(sb.toString(), StRuleLogVo.class);
	}
}
