package com.sdnx.st.sm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.dao.StModelDao;
import com.sdnx.st.sm.model.StModel;
import com.sdnx.st.sm.model.StModule;
import com.sdnx.st.sm.model.StObject;
import com.sdnx.st.sm.model.StRow;
import com.sdnx.st.sm.model.StRule;
import com.sdnx.st.sm.model.StRuleDetail;
import com.sdnx.st.sm.vo.StModelVo;
import com.sdnx.st.sm.vo.StStructrueVo;
import com.sdnx.st.util.STConnectionManager;
import com.sdnx.st.util.STDBManager;

public class StModelDaoImpl extends AbstractBaseDao<StModel> implements StModelDao{

	
	public String getMaxVersion(String orgcode,String classification) throws Exception{
		String ver = "";
		String sql = "select * from st_model where VERSION = (select max(VERSION) from st_model where ORGCODE='"+orgcode+"' and CLASSIFICATION='"+classification+"') and ORGCODE='"+orgcode+"' and CLASSIFICATION='"+classification+"'"; 
		List<StModel>  lists= queryListBySql(sql,StModel.class);
		ver = Integer.valueOf(lists.get(0).getVersion())+1+"";
		return ver;
	}
	
	public void getMapBySql(String modelId,Map<String,StModelVo> modelVoMap , Map<String,List<StStructrueVo>> structrueVoListMap
	, Map<String,StModule> moduleMap ,Map<String,List<StRule>> ruleListMap,Map<String,List<StRuleDetail>> ruleDetailListMap , Map<String,List<StRow>> rowListMap ,Map<String,List<StObject>> objectListMap) throws Exception{
		String modelSql = "select * from st_model where id = '"+modelId + "'";
		String structrueSql = "select * from st_structrue where modelcode in(select modelcode from st_model where id = '"+modelId+"')";
		String moduleSql = "select * from st_module where struccode in(select struccode from st_structrue where modelcode in(select modelcode from st_model where id = '"+modelId+"'))";
		String ruleSql = "select * from st_rule where struccode in(select struccode from st_structrue where modelcode in(select modelcode from st_model where id = '"+modelId+"'))";
		String ruleDetailSql = "select * from st_rule_detail where struccode in(select struccode from st_structrue where modelcode in(select modelcode from st_model where id = '"+modelId+"'))";
		String rowSql = "select * from st_row where detailcode in(select detailcode from st_rule_detail where struccode in(select struccode from st_structrue where modelcode in(select modelcode from st_model where id = '"+modelId+"')))";
		String objectSql = "select * from st_object where modelcode in(select modelcode from st_model where id = '"+modelId +"')";
		
		List<StModelVo> modelList =  queryListBySql(modelSql, StModelVo.class);
		if(modelList.size() > 0 && null != modelList){
			modelVoMap.put(String.valueOf(modelId), modelList.get(0));
		}
		
		List<StStructrueVo> strucVoList = queryListBySql(structrueSql, StStructrueVo.class);
		if(null != strucVoList &&strucVoList.size() >0){
			for(StStructrueVo ss : strucVoList){
				if(structrueVoListMap.get(ss.getParentstruccode())==null){
					List<StStructrueVo> tmp = new ArrayList<StStructrueVo>();
					tmp.add(ss);
					structrueVoListMap.put(ss.getParentstruccode(), tmp);
				}else{
					structrueVoListMap.get(ss.getParentstruccode()).add(ss);
				}
			}
		}
		
		List<StModule> moduleList = queryListBySql(moduleSql, StModule.class);
		if(null != moduleList && moduleList.size() > 0){
			for(StModule sm : moduleList){
				moduleMap.put(sm.getStruccode(), sm);
			}
		}
		
		List<StRule> ruleList  = queryListBySql(ruleSql, StRule.class);
		for(StRule sr : ruleList){
			if(ruleListMap.get(sr.getStruccode()) == null){
				List<StRule> ruleLists = new ArrayList<StRule>();
				ruleLists.add(sr);
				ruleListMap.put(sr.getStruccode(), ruleLists);
			}else{
				ruleListMap.get(sr.getStruccode()).add(sr);
			}
		}
		
		List<StRuleDetail> detailList  = queryListBySql(ruleDetailSql, StRuleDetail.class);
		if(null != detailList && detailList.size() > 0){
			for(StRuleDetail srd : detailList){
				if(ruleDetailListMap.get(srd.getStruccode()) == null){
					List<StRuleDetail> detailLists = new ArrayList<StRuleDetail>();
					detailLists.add(srd);
					ruleDetailListMap.put(srd.getStruccode(), detailLists);
				}else{
					ruleDetailListMap.get(srd.getStruccode()).add(srd);
				}
			}
		}
		
		List<StRow> rowList  = queryListBySql(rowSql, StRow.class);
		if(null != rowList && rowList.size() > 0){
			for(StRow sr : rowList){
				if(rowListMap.get(sr.getDetailcode()) == null){
					List<StRow> rowLists = new ArrayList<StRow>();
					rowLists.add(sr);
					rowListMap.put(sr.getDetailcode(), rowLists);
				}else{
					rowListMap.get(sr.getDetailcode()).add(sr);
				}
			}
		}
		
		List<StObject> objectList  = queryListBySql(objectSql, StObject.class);
		if(null != objectList && objectList.size() > 0){
			for(StObject so : objectList){
				if(objectListMap.get(so.getModelcode()) == null){
					List<StObject> objectLists = new ArrayList<StObject>();
					objectLists.add(so);
					objectListMap.put(so.getModelcode(), objectLists);
				}else{
					objectListMap.get(so.getModelcode()).add(so);
				}
			}
		}
	}

	@Override
	public Integer getNextSeqVal() {
		Connection con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		Integer code = null;
		String sql = "select nextval for ST_MODEL_SEQ as KEYID from sysibm.sysdummy1";
		try {
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if(rs.next())
				code = rs.getInt("KEYID");
		} catch (SQLException e) {
			
		}
		finally {
			STDBManager.getInstance().close(rs, st);
		}
		return code;
	}

}
