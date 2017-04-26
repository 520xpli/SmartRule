package com.sdnx.st.sm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.dao.StOrgSpreadDao;
import com.sdnx.st.sm.model.StOrgSpread;
import com.sdnx.st.sm.vo.StOrgSpreadVo;
import com.sdnx.st.util.STConnectionManager;
import com.sdnx.st.util.STDBManager;

public class StOrgSpreadDaoImpl extends AbstractBaseDao<StOrgSpread> implements StOrgSpreadDao{

	public List<StOrgSpreadVo> queryDot(List<String> lists) throws Exception{
		String sql = "select * from st_org_spread where ORGCODE in (";
		for(int s=0;s<lists.size()-1;s++){
			sql += "'"+lists.get(s)+"',";
		}
		sql += "'"+lists.get(lists.size()-1)+"')";
		List<StOrgSpreadVo> list = queryListBySql(sql,StOrgSpreadVo.class);
		return list;
		
	}
	public void updateAll(List<StOrgSpread> lists){
		Connection con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		try {
			String sql = "update st_org_spread set isopen='0',LASTOPERTIME='"+lists.get(0).getLastopertime()+"', ENDTIME='"+lists.get(0).getEndtime()+"' where id in (";
			for(int i=0;i<lists.size()-1;i++){
				sql += lists.get(i).getId()+",";
			}
			sql += lists.get(lists.size()-1).getId()+")";
			st = con.prepareStatement(sql);
			st.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			STDBManager.getInstance().close(null, st);
		}
	}
	@Override
	public List<StOrgSpreadVo> querySpreadOrgByDots(String dots) throws Exception {
		String sql = "select * from st_org_spread where isopen='1' and iseffect='1' and orgcode in(" + dots + ")";
		return queryListBySql(sql, StOrgSpreadVo.class);
	}
}
