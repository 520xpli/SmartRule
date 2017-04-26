package com.sdnx.st.se.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.sdnx.st.constants.ObjectCode;
import com.sdnx.st.constants.RuleClass;
import com.sdnx.st.constants.RuleState;
import com.sdnx.st.dp.model.PublicData;
import com.sdnx.st.se.constants.CalculatorConstant;
import com.sdnx.st.se.util.CheckUtil;
import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.model.StModule;
import com.sdnx.st.sm.model.StRuleDetail;
import com.sdnx.st.sm.vo.StDatatypeVo;
import com.sdnx.st.sm.vo.StModelVo;
import com.sdnx.st.sm.vo.StModuleVo;
import com.sdnx.st.sm.vo.StObjectPropertyVo;
import com.sdnx.st.sm.vo.StObjectVo;
import com.sdnx.st.sm.vo.StRuleDetailVo;
import com.sdnx.st.sm.vo.StRuleVo;
import com.sdnx.st.sm.vo.StStructrueVo;
import com.sdnx.st.util.StDaoFactory;

public class StInit {
	private static Map<String, Object> stRuleMapData = new HashMap<String, Object>();
	private static BaseDao<?> baseDao = StDaoFactory.getBaseDao();
	private static Map<String, List<StModelVo>> modelMap = new HashMap<String, List<StModelVo>>();
	private static Map<String, StStructrueVo> strucRootMap = new HashMap<String, StStructrueVo>();
	private static Map<String, List<StStructrueVo>> strucChildrenMap = new HashMap<String, List<StStructrueVo>>();
	private static Map<String, StRuleVo> ruleMap = new HashMap<String, StRuleVo>();
	private static Map<String, StModuleVo> moduleMap = new HashMap<String, StModuleVo>();
	private static Map<String, List<StRuleDetailVo>> detailMap = new HashMap<String, List<StRuleDetailVo>>();
	private static List<StObjectVo> objectList;
	private static Map<String, List<StObjectPropertyVo>> propertyMap = new HashMap<String, List<StObjectPropertyVo>>();
	private static Map<String, StObjectPropertyVo> propertyKeyMap = new HashMap<String, StObjectPropertyVo>();
	private static Map<String, Map<String, List<StObjectPropertyVo>>> rateDemandProperty = new HashMap<String, Map<String,List<StObjectPropertyVo>>>();
	private static Map<String, Map<String, List<StObjectPropertyVo>>> rateQueryProperty = new HashMap<String, Map<String,List<StObjectPropertyVo>>>();
	private static Map<String, StDatatypeVo> datatypeMap = new HashMap<String, StDatatypeVo>();
	private static String checkIsnew = "";
	
	private static int status = 0;
	
	
	
	private static Logger logger = Logger.getLogger(StInit.class);

	private static class Inner {
		static StInit stInit = new StInit();
	}

	public static StInit getInstance() {
		return Inner.stInit;
	}

	private StInit() {

	}
	
	static{
		initialStRule();
	}
	
	public boolean checkIsNew(){
		String state = RuleState.effect.getCode();
		String modelSql = "select * from st_model where state=" + state;
		try {
			List<StModelVo> modelList;
			modelList = baseDao.queryListBySql(modelSql, StModelVo.class);
			StringBuffer tmp = new StringBuffer();
			if(modelList != null && modelList.size() > 0){
				for(StModelVo vo: modelList){
					tmp.append(vo.getModelcode());
				}
			}
			if(checkIsnew.equals(tmp.toString())){
				return true;
			}
			else{
				resetRuleData();
				return false;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return true;
	}
	
	public synchronized static void resetRuleData(){
		status = 1;
		
		stRuleMapData.clear();
		modelMap.clear();
		strucRootMap.clear();
		strucChildrenMap.clear();
		ruleMap.clear();
		moduleMap.clear();
		detailMap.clear();
		objectList.clear();
		propertyMap.clear();
		propertyKeyMap.clear();
		rateDemandProperty.clear();
		datatypeMap.clear();
		
		initialStRule();
		status = 0;
	}

	public static Map<String, Object> getStRuleMapData() {
		while(status != 0){
			
		}
		return stRuleMapData;
	}
	public static Map<String, Map<String, List<StObjectPropertyVo>>> getRateDemandProperty(){
		return rateDemandProperty;
	}
	
	public static Map<String, Map<String, List<StObjectPropertyVo>>> getRateQueryProperty(){
		return rateQueryProperty;
	}
	
	public static Map<String, List<StObjectPropertyVo>> getPropertyList(){
		return propertyMap;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getRuleData(PublicData pd, String typeCode){
		Map<String, Object> ruleTypeMap = (Map<String, Object>) StInit.getStRuleMapData()
				.get(pd.getInscode());
		if (ruleTypeMap == null || ruleTypeMap.get(typeCode) == null) {
			ruleTypeMap = (Map<String, Object>) StInit.getStRuleMapData()
					.get(pd.getLegalCode());
			if (ruleTypeMap == null || ruleTypeMap.get(typeCode) == null) {
				ruleTypeMap = (Map<String, Object>) StInit.getStRuleMapData()
						.get(pd.getCityCode());
				if(ruleTypeMap == null || ruleTypeMap.get(typeCode) == null){
					return null;
				}
			}
		}
		return ruleTypeMap;
	}
	
	private synchronized static void initialStRule() {
		String state = RuleState.effect.getCode();
		String modelSql = "select * from st_model where state=" + state;
		String strucSql = "select * from st_structrue where modelcode in (select modelcode from st_model where state=" + state +")";
		String ruleSql = "select * from st_rule where struccode in(select struccode from st_structrue where modelcode in ("
				+ "select modelcode from st_model where state=" + state + "))";
		String moduleSql = "select * from st_module where struccode in(select struccode from st_structrue where modelcode in ("
				+ "select modelcode from st_model where state=" + state + "))";
		String detailSql = "select * from st_rule_detail where struccode in(select struccode from st_structrue where modelcode in ("
				+ "select modelcode from st_model where state=" + state + "))";
		String objectSql = "select * from st_object where modelcode in (select modelcode from st_model where state=" + state + ")";
		String propertySql = "select * from st_object_property where objectcode in (select objectcode from st_object where modelcode"
				+ " in(select modelcode from st_model where state=" + state + "))";
		String datatypeSql = "select * from st_datatype";
		try {
			List<StModelVo> modelList = baseDao.queryListBySql(modelSql, StModelVo.class);
			if(CheckUtil.listIsNull(modelList)){
				for(StModelVo vo : modelList){
					String org = vo.getOrgcode();
					checkIsnew += vo.getModelcode();
					if(modelMap.get(org) == null){
						List<StModelVo> tmpList = new ArrayList<StModelVo>();
						tmpList.add(vo);
						modelMap.put(org, tmpList);
					}else modelMap.get(org).add(vo);
				}
			} else return;
			
			List<StStructrueVo> strucList = baseDao.queryListBySql(strucSql, StStructrueVo.class);
			if(CheckUtil.listIsNull(strucList)){
				for(StStructrueVo vo : strucList){
					String pCode = vo.getParentstruccode();
					if("0".equals(pCode)){
						strucRootMap.put(vo.getModelcode(), vo);
					}
					if(strucChildrenMap.get(pCode) == null){
						List<StStructrueVo> tmpList = new ArrayList<StStructrueVo>();
						tmpList.add(vo);
						strucChildrenMap.put(pCode, tmpList);
					}else strucChildrenMap.get(pCode).add(vo);
				}
			}
			
			List<StRuleVo> ruleList = baseDao.queryListBySql(ruleSql, StRuleVo.class);
			if(CheckUtil.listIsNull(ruleList))
				for(StRuleVo vo : ruleList) ruleMap.put(vo.getStruccode(), vo);
			
			List<StModuleVo> moduleList = baseDao.queryListBySql(moduleSql, StModuleVo.class);
			if(CheckUtil.listIsNull(moduleList))
				for(StModuleVo vo : moduleList) moduleMap.put(vo.getStruccode(), vo);
			
			List<StRuleDetailVo> detailList = baseDao.queryListBySql(detailSql, StRuleDetailVo.class);
			if(CheckUtil.listIsNull(detailList)){
				for(StRuleDetailVo vo : detailList){
					String sCode = vo.getStruccode();
					if(detailMap.get(sCode) == null){
						List<StRuleDetailVo> tmpList = new ArrayList<StRuleDetailVo>();
						tmpList.add(vo);
						detailMap.put(sCode, tmpList);
					} else detailMap.get(sCode).add(vo);
				}
			}
			objectList = baseDao.queryListBySql(objectSql, StObjectVo.class);
			
			List<StObjectPropertyVo> propertyList = baseDao.queryListBySql(propertySql, StObjectPropertyVo.class);
			if(CheckUtil.listIsNull(propertyList)){
				for(StObjectPropertyVo vo : propertyList){
					String oCode = vo.getObjectcode();
					if(propertyMap.get(oCode) == null){
						List<StObjectPropertyVo> tmpList = new ArrayList<StObjectPropertyVo>();
						tmpList.add(vo);
						propertyMap.put(oCode, tmpList);
					}else propertyMap.get(oCode).add(vo);
					for(ObjectCode.Rate rate : ObjectCode.Rate.values()){
						if(rate.toString().equals(vo.getObjectcode())){
							propertyKeyMap.put(vo.getPropertykey(), vo);
							break;
						}
					}
				}
			}
			
			List<StDatatypeVo> datatypeList = baseDao.queryListBySql(datatypeSql, StDatatypeVo.class);
			if(CheckUtil.listIsNull(datatypeList)){
				for(StDatatypeVo vo : datatypeList) datatypeMap.put(vo.getDatatypecode(),vo);
			}
			
			if(CheckUtil.listIsNull(modelList)){
				for(StModelVo smv : modelList){
					initRuleSystem(smv.getOrgcode());
				}
			}
		} catch (Exception e) {
			logger.error("规则初始化失败,错误信息" + e.getMessage());
		}
	}
	
	private static void initRuleSystem(String orgCode) throws Exception{
		Map<String,StObjectVo> objMap = new HashMap<String,StObjectVo>();
		for(StObjectVo sov : objectList){
			List<StObjectPropertyVo> sops = propertyMap.get(sov.getObjectcode());
			sov.setPropertyList(sops);
			objMap.put(sov.getObjectcode(), sov);
		}
		stRuleMapData.put(CalculatorConstant.OBJ_MAP_KEY, objMap);
		
		if(stRuleMapData.get(orgCode) != null) return;
		//查询某机构下有效的规则类型
		List<StModelVo> stTypelist = modelMap.get(orgCode);
		Map<String,Object> ruleTypeMap = new HashMap<String,Object>();
		for(StModelVo stVo : stTypelist){
			StStructrueVo sv = strucRootMap.get(stVo.getModelcode());
			if(sv == null) continue;
			List<StStructrueVo> ssList = new ArrayList<StStructrueVo>(Arrays.asList(sv));
			//查询规则类型下的模块、规则相关信息
			for(StStructrueVo ssv: ssList){
				getChildModuleOrRule(ssv,null);
			}
			stVo.setSsList(ssList);
			ruleTypeMap.put(stVo.getClassification(), stVo);
			if(RuleClass.rate.getCode().equals(stVo.getClassification())){
				getRateProperty(orgCode, stVo, objMap);
			}
		}
		
		stRuleMapData.put(orgCode,ruleTypeMap);
		
	}
	
	private static void getRateProperty(String orgCode, StModelVo modelVo, Map<String,StObjectVo> objMap){
		if(modelVo != null && modelVo.getSsList().size() > 0){
			List<StStructrueVo> list = modelVo.getSsList().get(0).getChildList();
			if(list != null && list.size() > 0){
				Pattern p = Pattern.compile("#\\{[^#]*\\}");
				Matcher m = null;
				Map<String, List<StObjectPropertyVo>> map = new HashMap<String, List<StObjectPropertyVo>>();
				Map<String, List<StObjectPropertyVo>> rateQueryMap = new HashMap<String, List<StObjectPropertyVo>>();
				for(StStructrueVo vo : list){
					Map<String, StObjectPropertyVo> tmpMap = new HashMap<String, StObjectPropertyVo>();
					List<StObjectPropertyVo> propertyList = new ArrayList<StObjectPropertyVo>();
					if(vo.getStModuleVo() != null && vo.getStModuleVo().getPrecondition() != null){
						m = p.matcher(vo.getStModuleVo().getPrecondition());
						while(m.find()){
							String g = m.group();
							String key = g.substring(2,g.length() - 1);
							if(tmpMap.get(key) == null){
								StObjectPropertyVo propertyVo = propertyKeyMap.get(key);
								if(propertyVo != null){
									propertyVo.setDatatypeVo(datatypeMap.get(propertyVo.getDatatypecode()));
									propertyList.add(propertyVo);
									tmpMap.put(key, propertyVo);
								}
							}
						}
					}
					if(vo.getChildList() != null && vo.getChildList().size() > 0){
						for(StStructrueVo childVo : vo.getChildList()){
							StRuleVo ruleVo = childVo.getStRuleVo();
							if(ruleVo != null){
								List<StRuleDetailVo> detailList = childVo.getStRuleVo().getRuleDetailList();
								List<StObjectPropertyVo> rateQueryPropertyList = new ArrayList<StObjectPropertyVo>();
								if(detailList != null && detailList.size() > 0){
									for(StRuleDetailVo dv : detailList){
										if(dv.getPrecondition() != null){
											m = p.matcher(dv.getPrecondition());
											while(m.find()){
												String g = m.group();
												String key = g.substring(2,g.length() - 1);
												if(tmpMap.get(key) == null){
													StObjectPropertyVo propertyVo = propertyKeyMap.get(key);
													if(propertyVo != null){
														propertyVo.setDatatypeVo(datatypeMap.get(propertyVo.getDatatypecode()));
														propertyList.add(propertyVo);
														rateQueryPropertyList.add(propertyVo);
														tmpMap.put(key, propertyVo);
													}
												}
											}
										}
									}
								}
								rateQueryMap.put(vo.getStruccode(), rateQueryPropertyList);
							}
						}
					}
					map.put(vo.getRelatecode(), propertyList);
				}
				rateDemandProperty.put(orgCode, map);
				rateQueryProperty.put(orgCode, rateQueryMap);
			}
		}
	}
	
	private static void getChildModuleOrRule(StStructrueVo ssv,StStructrueVo parent) throws Exception{
		//查询模块特色信息
		StModule stModule = new StModule();
		stModule.setStruccode(ssv.getStruccode());
		ssv.setStModuleVo(moduleMap.get(ssv.getStruccode()));
		//查询规则特色信息
		StRuleVo stRuleVo =  ruleMap.get(ssv.getStruccode());
		if(stRuleVo != null){
			//查询具体规则
			StRuleDetail srd = new StRuleDetail(); 
			srd.setStruccode(ssv.getStruccode());
			List<StRuleDetailVo> srdList = detailMap.get(ssv.getStruccode());
			//设置规则明细
			stRuleVo.setRuleDetailList(srdList);
			//设置规则特色
			ssv.setStRuleVo(stRuleVo);
		}
		
		//查询子模块的模块、规则
		List<StStructrueVo> childList = strucChildrenMap.get(ssv.getStruccode());
		if(childList != null && childList.size() > 0){
			for(StStructrueVo cssv: childList){
				getChildModuleOrRule(cssv,ssv);
			}
			Collections.sort(childList,new compareStruc());
			ssv.setChildList(childList);
		}
		//设置父节点
		ssv.setParent(parent);
	}
	
	private static class compareStruc implements Comparator<StStructrueVo>{

		@Override
		public int compare(StStructrueVo arg0, StStructrueVo arg1) {
			if(arg0 == arg1) return 0;
			else if(arg0 == null || arg0.getSeq() == null) return -1;
			else if(arg1 == null || arg1.getSeq() == null) return 1;
			else if(arg0.getSeq() > arg1.getSeq()) return 1;
			else if(arg0.getSeq() < arg1.getSeq()) return -1;
			else return 0;
		}
		
	}
}
