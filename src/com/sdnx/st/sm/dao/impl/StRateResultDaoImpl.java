package com.sdnx.st.sm.dao.impl;

import java.util.List;

import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.dao.StRateResultDao;
import com.sdnx.st.sm.model.StRateResult;
import com.sdnx.st.sm.vo.StRateResultVo;
import com.sdnx.st.util.StUtil;

public class StRateResultDaoImpl extends AbstractBaseDao<StRateResult> implements StRateResultDao{

	@Override
	public List<StRateResultVo> querybymodel(StRateResult stRateResult,List<String> lists,String datefrom,String dateTo) throws Exception {
		
		String sql = "select * from st_rate_result where lastopertime in (select max(lastopertime) from st_rate_result where BUSINESSCODE is not null group by BUSINESSCODE)";
		
		if(StUtil.judgeIsNull(stRateResult.getCustcode())){
			
			sql = sql + " and CUSTCODE='"+stRateResult.getCustcode()+"'";
		}
		if(StUtil.judgeIsNull(stRateResult.getCustname())){
			sql = sql + " and CUSTNAME like '%"+stRateResult.getCustname()+"%'";
		}
		if(StUtil.judgeIsNull(stRateResult.getIdtype())){
			sql = sql + " and IDTYPE='"+stRateResult.getIdtype()+"'";
		}
		if(StUtil.judgeIsNull(stRateResult.getIdnumber())){
			sql = sql + " and IDNUMBER='"+stRateResult.getIdnumber()+"'";
		}
		if(StUtil.judgeIsNull(stRateResult.getRatecode())){
			sql = sql + " and RATECODE='"+stRateResult.getRatecode()+"'";
		}
		if(datefrom!=null && !datefrom .equals("") && dateTo != null && !dateTo .equals("")){
			sql = sql + " and ratedate>='"+datefrom + "' and ratedate<='"+dateTo+"'";
		}else if(datefrom!=null && !datefrom .equals("") &&( dateTo == null || dateTo .equals(""))){
			sql = sql + " and ratedate>='"+datefrom+"'";
		}else if((datefrom == null || datefrom .equals("")) && dateTo != null && !dateTo .equals("")){
			sql = sql + " and ratedate<='"+dateTo+"'";
		}
		if(null != lists && lists.size() > 0){
			sql = sql + " and ORGCODE in (";
			for(int i=0;i<lists.size()-1;i++){
				if(StUtil.judgeIsNull(lists.get(i)))
					sql = sql+ "'" + lists.get(i)+"',";
			}
			if(sql.charAt(sql.length() - 1) == ',')
				sql = sql.substring(0, sql.length() - 1);
			sql += ")";
		}
		sql += " order by lastopertime desc";
		List<StRateResultVo> list = queryListBySql(sql,StRateResultVo.class);
		return list;
	}
	@Override
	public List<StRateResultVo> querybyM(StRateResult stRateResult,List<String> lists,String datefrom,String dateTo) throws Exception {
		
		String sql = "select * from st_rate_result where lastopertime in (select max(lastopertime) from st_rate_result where BUSINESSCODE is not null group by BUSINESSCODE)";
		
		if(StUtil.judgeIsNull(stRateResult.getCustcode())){
			
			sql = sql + " and CUSTCODE='"+stRateResult.getCustcode()+"'";
		}
		if(StUtil.judgeIsNull(stRateResult.getCustcode())){
			
			sql = sql + " and CUSTCODE='"+stRateResult.getCustcode()+"'";
		}
		if(StUtil.judgeIsNull(stRateResult.getCustname())){
			sql = sql + " and CUSTNAME like '%"+stRateResult.getCustname()+"%'";
		}
		if(StUtil.judgeIsNull(stRateResult.getIdtype())){
			sql = sql + " and IDTYPE='"+stRateResult.getIdtype()+"'";
		}
		if(StUtil.judgeIsNull(stRateResult.getIdnumber())){
			sql = sql + " and IDNUMBER='"+stRateResult.getIdnumber()+"'";
		}
		if(StUtil.judgeIsNull(stRateResult.getRatecode())){
			sql = sql + " and RATECODE='"+stRateResult.getRatecode()+"'";
		}
		if(StUtil.judgeIsNull(stRateResult.getCustmanager())){
			sql = sql + " and custmanager='"+stRateResult.getCustmanager()+"'";
		}if(StUtil.judgeIsNull(stRateResult.getOrgcode())){
			sql = sql + " and ORGCODE='"+stRateResult.getOrgcode()+"'";
		}
		if(datefrom!=null && !datefrom .equals("") && dateTo != null && !dateTo .equals("")){
			sql = sql + " and ratedate>='"+datefrom + "' and ratedate<='"+dateTo+"'";
		}else if(datefrom!=null && !datefrom .equals("") &&( dateTo == null || dateTo .equals(""))){
			sql = sql + " and ratedate>='"+datefrom+"'";
		}else if((datefrom == null || datefrom .equals("")) && dateTo != null && !dateTo .equals("")){
			sql = sql + " and ratedate<='"+dateTo+"'";
		}
		if(null != lists && lists.size() > 0){
			sql = sql + " and CUSTCODE in (";
			for(int i=0;i<lists.size();i++){
				if(StUtil.judgeIsNull(lists.get(i)))
					sql = sql+ "'" + lists.get(i)+"',";
			}
			if(sql.charAt(sql.length() - 1) == ',')
				sql = sql.substring(0, sql.length() - 1);
			sql += ")";
		}else if((null == lists || lists.size() == 0) && !StUtil.judgeIsNull(stRateResult.getCustmanager())) { 
			sql += " and CUSTMANAGER=NULL"; 
		}
		sql += " order by lastopertime desc";
		List<StRateResultVo> list = queryListBySql(sql,StRateResultVo.class);
		return list;
	}
}
