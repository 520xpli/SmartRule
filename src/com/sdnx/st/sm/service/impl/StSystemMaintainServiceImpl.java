package com.sdnx.st.sm.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sdnx.st.constants.RuleState;
import com.sdnx.st.se.init.StInit;
import com.sdnx.st.sm.action.StSystemMaintainForm;
import com.sdnx.st.sm.base.AbstractBaseService;
import com.sdnx.st.sm.dao.StLogInputdataDao;
import com.sdnx.st.sm.dao.StModelDao;
import com.sdnx.st.sm.dao.StModuleDao;
import com.sdnx.st.sm.dao.StObjectDao;
import com.sdnx.st.sm.dao.StRateResultDao;
import com.sdnx.st.sm.dao.StRateresultDataDao;
import com.sdnx.st.sm.dao.StRowDao;
import com.sdnx.st.sm.dao.StRuleDao;
import com.sdnx.st.sm.dao.StRuleDetailDao;
import com.sdnx.st.sm.dao.StRuleLogDao;
import com.sdnx.st.sm.dao.StStructrueDao;
import com.sdnx.st.sm.model.StLogInputdata;
import com.sdnx.st.sm.model.StModel;
import com.sdnx.st.sm.model.StModule;
import com.sdnx.st.sm.model.StObject;
import com.sdnx.st.sm.model.StRateResult;
import com.sdnx.st.sm.model.StRateresultData;
import com.sdnx.st.sm.model.StRow;
import com.sdnx.st.sm.model.StRule;
import com.sdnx.st.sm.model.StRuleDetail;
import com.sdnx.st.sm.model.StStructrue;
import com.sdnx.st.sm.service.StSystemMaintainService;
import com.sdnx.st.sm.utils.SmUtil.User;
import com.sdnx.st.sm.vo.StLogInputdataVo;
import com.sdnx.st.sm.vo.StModelVo;
import com.sdnx.st.sm.vo.StObjectPropertyVo;
import com.sdnx.st.sm.vo.StRateResultVo;
import com.sdnx.st.sm.vo.StRateresultDataVo;
import com.sdnx.st.sm.vo.StRuleDetailVo;
import com.sdnx.st.sm.vo.StRuleLogVo;
import com.sdnx.st.sm.vo.StStructrueVo;
import com.sdnx.st.util.StDaoFactory;
import com.sdnx.st.util.StUtil;

public class StSystemMaintainServiceImpl extends AbstractBaseService<StModel> implements StSystemMaintainService{
	
	private static final Logger logger = Logger
			.getLogger(StSystemMaintainServiceImpl.class);
	private StModelDao modelDao = StDaoFactory.getModelDao();
	private StStructrueDao stStructureDao = StDaoFactory.getStructrueDao();
	private StModuleDao stModuleDao = StDaoFactory.getModuleDao();
	private StRuleDao stRuleDao = StDaoFactory.getRuleDao();
	private StRuleDetailDao stRuleDetailDao = StDaoFactory.getRuleDetailDao();
	private StRowDao stRowdao = StDaoFactory.getRowDao();
	private StObjectDao stObjectDao = StDaoFactory.getObjectDao();
	private StRateResultDao stRateResultDao = StDaoFactory.getRateResultDao();
	private StRateresultDataDao stRateresultDataDao = StDaoFactory.getRateresultDataDao();
	private StRuleLogDao stRuleLogDao = StDaoFactory.getRuleLogDao();
	private StLogInputdataDao inputdatDao = StDaoFactory.getInputdataDao();
	
	private  ThreadLocal<HashMap<String, StModelVo>> modelMap = new ThreadLocal<HashMap<String, StModelVo>>();
	private  ThreadLocal<HashMap<String, List<StStructrueVo>>> structrueVoListMap = new ThreadLocal<HashMap<String, List<StStructrueVo>>>();
	private  ThreadLocal<HashMap<String, StModule>> moduleMap = new ThreadLocal<HashMap<String, StModule>>();
	private  ThreadLocal<HashMap<String, List<StRule>>> ruleListMap = new ThreadLocal<HashMap<String, List<StRule>>>();
	private  ThreadLocal<HashMap<String, List<StRuleDetail>>> ruleDetailListMap = new ThreadLocal<HashMap<String, List<StRuleDetail>>>();
	private  ThreadLocal<HashMap<String, List<StRow>>> rowListMap = new ThreadLocal<HashMap<String, List<StRow>>>();
	private  ThreadLocal<HashMap<String, List<StObject>>> objectListMap = new ThreadLocal<HashMap<String, List<StObject>>>();
	
	private ThreadLocal<ArrayList<StStructrue>> strucList = new ThreadLocal<ArrayList<StStructrue>>();
	private ThreadLocal<ArrayList<StModule>> moduleList = new ThreadLocal<ArrayList<StModule>>();
	private ThreadLocal<ArrayList<StRuleDetail>> detailList = new ThreadLocal<ArrayList<StRuleDetail>>();
	private ThreadLocal<ArrayList<StRule>> ruleList = new ThreadLocal<ArrayList<StRule>>();
	private ThreadLocal<ArrayList<StObject>> objectList = new ThreadLocal<ArrayList<StObject>>();
	private ThreadLocal<ArrayList<StRow>> rowList = new ThreadLocal<ArrayList<StRow>>();
	
	private ThreadLocal<Integer> flag = new ThreadLocal<Integer>();
	private ThreadLocal<Integer> count = new ThreadLocal<Integer>();
	
	// 产品中类查询条件
	private String busyname = "cmiscc.pCustBusiCode";
	private String productco = "000000097642";

	
	@Override
	public void upgrade(Long modelId) {
		
	}

	public List<StModelVo> queryListByModel(StModel stModel) throws Exception {
		List<StModelVo> os = modelDao.queryListByModel(stModel, StModelVo.class);
		return os;
	}

	public StModelVo queryById(String id) throws Exception {
		return (StModelVo) modelDao.queryById(id, StModelVo.class, "st_model");
	}
	//复制规则体系到其他法人机构
	public synchronized String copyModel(String modelId,String orgcode,User user,StSystemMaintainForm ruleForm) throws Exception {
		
		modelMap.set(new HashMap<String, StModelVo>());
		structrueVoListMap.set(new HashMap<String, List<StStructrueVo>>());
		moduleMap.set(new HashMap<String, StModule>());
		ruleListMap.set(new HashMap<String, List<StRule>>());
		ruleDetailListMap.set(new HashMap<String, List<StRuleDetail>>());
		rowListMap.set(new HashMap<String, List<StRow>>());
		objectListMap.set(new HashMap<String, List<StObject>>());
		
		strucList.set(new ArrayList<StStructrue>());
		moduleList.set(new ArrayList<StModule>());
		detailList.set(new ArrayList<StRuleDetail>());
		ruleList.set(new ArrayList<StRule>());
		objectList.set(new ArrayList<StObject>());
		rowList.set(new ArrayList<StRow>());
		
		flag.set(modelDao.getNextSeqVal());
		count.set(1);
		
		//map集合转换
		modelDao.getMapBySql(modelId, modelMap.get(), structrueVoListMap.get(), moduleMap.get(), ruleListMap.get(), ruleDetailListMap.get(), rowListMap.get(), objectListMap.get());
		//通过id查询规则体系数据
		StModelVo stModelVo = modelMap.get().get(String.valueOf(modelId));
		StModel smo = new StModel();
		smo.setOrgcode(orgcode);
		smo.setClassification(stModelVo.getClassification());
		List<StModelVo> lists = modelDao.queryListByModel(smo,StModelVo.class);
		String verNum = "";
		//获取复制后版本
		if(null != lists && lists.size() >0){
			verNum = modelDao.getMaxVersion(orgcode, stModelVo.getClassification());
		}else{
			verNum = "1";
		}
		//查询是否有新增状态的数据
		StModel smo1 = new StModel();
		smo1.setClassification(ruleForm.getStModelVo().getClassification());
		smo1.setOrgcode(ruleForm.getLegalOrgCode());
		smo1.setState(RuleState.add.getCode());
		List<StModelVo> lists1 = queryListByModel(smo1);
		String retu = "复制成功";
		if(lists1.size()>0){
			retu="该机构存在未提交的版本";
		}else{
			//复制后编码生成
			String newcode = modelDao.codeGenerator("StModel", flag.get(), count.get());
			count.set(count.get() + 1);
			StModel sm = new StModel();
			sm.setVersion(verNum);
			sm.setModelcode(newcode );
			sm.setState(RuleState.add.getCode());
			sm.setClassification(stModelVo.getClassification());
			sm.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
			sm.setOrgcode(orgcode);
			sm.setModelname(stModelVo.getModelname());
			sm.setCreateusercode(user.getId());
			sm.setModifytime(new java.sql.Timestamp(new Date().getTime()));
			sm.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
			modelDao.add(sm);
			//对象复制
			Map<String,String> map = copyObject(stModelVo.getModelcode(),sm.getModelcode());
			//复制规则结构
			copyStructureSystem(stModelVo.getModelcode(),sm.getModelcode(),map);
			
			stStructureDao.batchInsert(strucList.get());
			stModuleDao.batchInsert(moduleList.get());
			stRuleDetailDao.batchInsert(detailList.get());
			stRuleDao.batchInsert(ruleList.get());
			stObjectDao.batchInsert(objectList.get());
			stRowdao.batchInsert(rowList.get());
			
			//清空map
			modelMap.get().clear();
			structrueVoListMap.get().clear();
			moduleMap.get().clear();
			ruleListMap.get().clear();ruleDetailListMap.get().clear();rowListMap.get().clear(); objectListMap.get().clear();
			
			//清空list
			strucList.get().clear();moduleList.get().clear();detailList.get().clear();ruleList.get().clear();rowList.get().clear();objectList.get().clear();
		}
		return retu;
	}
	//升级规则体系
	public synchronized String upgradeModel(String modelId,User user,StSystemMaintainForm ruleForm) throws Exception {
		long start = System.currentTimeMillis();
		modelMap.set(new HashMap<String, StModelVo>());
		structrueVoListMap.set(new HashMap<String, List<StStructrueVo>>());
		moduleMap.set(new HashMap<String, StModule>());
		ruleListMap.set(new HashMap<String, List<StRule>>());
		ruleDetailListMap.set(new HashMap<String, List<StRuleDetail>>());
		rowListMap.set(new HashMap<String, List<StRow>>());
		objectListMap.set(new HashMap<String, List<StObject>>());
		
		strucList.set(new ArrayList<StStructrue>());
		moduleList.set(new ArrayList<StModule>());
		detailList.set(new ArrayList<StRuleDetail>());
		ruleList.set(new ArrayList<StRule>());
		objectList.set(new ArrayList<StObject>());
		rowList.set(new ArrayList<StRow>());
		
		flag.set(modelDao.getNextSeqVal());
		count.set(1);
		
		//map集合转换
		modelDao.getMapBySql(modelId, modelMap.get(), structrueVoListMap.get(), moduleMap.get(), ruleListMap.get(), ruleDetailListMap.get(), rowListMap.get(), objectListMap.get());
		//通过id查询规则体系数据
		StModelVo stModelVo = modelMap.get().get(String.valueOf(modelId));
		//获取升级后版本
		String verNum = modelDao.getMaxVersion(stModelVo.getOrgcode(), stModelVo.getClassification());
		//查询是否有新增状态的数据
		StModel smo = new StModel();
		smo.setOrgcode(ruleForm.getStModelVo().getOrgcode());
		smo.setClassification(ruleForm.getStModelVo().getClassification());
		smo.setState(RuleState.add.getCode());
		List<StModelVo> lists = queryListByModel(smo);
		String retu = "升级成功";
		if(lists.size()>0){
			retu="该规则模块存在未提交的版本";
		}else{
			//升级后编码生成
			String newcode = modelDao.codeGenerator("StModel", flag.get(), count.get());
			count.set(count.get() + 1);
			StModel sm = new StModel();
			sm.setVersion(verNum);
			sm.setModelcode(newcode );
			sm.setState(RuleState.add.getCode());
			sm.setClassification(stModelVo.getClassification());
			sm.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
			sm.setOrgcode(stModelVo.getOrgcode());
			sm.setModelname(stModelVo.getModelname());
			sm.setCreateusercode(user.getId());
			sm.setModifytime(new java.sql.Timestamp(new Date().getTime()));
			sm.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
			
			modelDao.add(sm);
			
			//对象复制
			Map<String,String> map = copyObject(stModelVo.getModelcode(),sm.getModelcode());
			//复制规则结构
			copyStructureSystem(stModelVo.getModelcode(),sm.getModelcode(),map);
			
			stStructureDao.batchInsert(strucList.get());
			stModuleDao.batchInsert(moduleList.get());
			stRuleDetailDao.batchInsert(detailList.get());
			stRuleDao.batchInsert(ruleList.get());
			stObjectDao.batchInsert(objectList.get());
			stRowdao.batchInsert(rowList.get());
			
			//清空map
			modelMap.get().clear();
			structrueVoListMap.get().clear();
			moduleMap.get().clear();
			ruleListMap.get().clear();ruleDetailListMap.get().clear();rowListMap.get().clear(); objectListMap.get().clear();
			//清空list
			strucList.get().clear();moduleList.get().clear();detailList.get().clear();ruleList.get().clear();rowList.get().clear();objectList.get().clear();
		}
		System.out.println("执行时间:" + (System.currentTimeMillis() - start));
		return retu;
		
	}
	/**
	 * 初始化规则体系
	 * @throws Exception 
	 */
	public void copyStructureSystem(String oldModelcode,String newModelcode,Map<String,String> map) throws Exception{
		
		StStructrue ss = new StStructrue();
		ss.setParentstruccode("0");
		List<StStructrueVo> list =  structrueVoListMap.get().get(ss.getParentstruccode());
			//复制规则类型下的模块、规则相关信息
		if(list.size()>0 && list!=null){
			for(StStructrueVo ssv:list){
				//复制规则类型下的模块、规则相关信息
				copyStruct(oldModelcode,newModelcode, ssv,"0",map);
			}
		}
	}
	/**
	 * 复制规则类型下的模块、规则相关信息
	 * @param newTypeId
	 * @param map
	 * @param oldStruct
	 * @throws Exception
	 */
	private void copyStruct(String oldModelcode,String newModelcode,StStructrueVo oldStruct,String parentStruCode,Map<String,String> map)throws Exception{
		//获取复制前的结构编码
		String oldStructcode = oldStruct.getStruccode();
		StStructrue newStruct = new StStructrue();
		//升级后编码生成
		String newStruCode = stStructureDao.codeGenerator("StStructrue", flag.get(), count.get());
		count.set(count.get() + 1);
		//设置新的规则体系code
		newStruct.setModelcode(newModelcode);
		//设置新的规则结构code
		newStruct.setStruccode(newStruCode);
		//处理父级code
		newStruct.setParentstruccode(parentStruCode);
		newStruct.setLevel(oldStruct.getLevel());
		newStruct.setStrucname(oldStruct.getStrucname());
		newStruct.setDescription(oldStruct.getDescription());
		newStruct.setSeq(oldStruct.getSeq() == null ? null: oldStruct.getSeq().shortValue());
		newStruct.setObjectrule(oldStruct.getObjectrule());
		newStruct.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
		newStruct.setPrompt(oldStruct.getPrompt());
		newStruct.setClassification(oldStruct.getClassification());
		newStruct.setRelatecode(oldStruct.getRelatecode());
		newStruct.setMisc(oldStruct.getMisc());
		newStruct.setIsleaf(oldStruct.getIsleaf());
		//新结构入库
		strucList.get().add(newStruct);
		//获取新的结构编码
		String newStructCode = newStruct.getStruccode();
		
		//查询模块特色
		StModule oldM =  moduleMap.get().get(oldStructcode);
		if(oldM != null){
			oldM.setId(null);//清空历史模块特色ID
			oldM.setStruccode(newStructCode);
			oldM.setModulecode(stModuleDao.codeGenerator("StModule", flag.get(), count.get()));
			count.set(count.get() + 1);
			oldM.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
			moduleList.get().add(oldM);
		}
		//查询规则特色相关并执行复制
		else{
			//1.查询规则特色集合
			List<StRule> oldRuleList = ruleListMap.get().get(oldStructcode);
			if(null != oldRuleList && oldRuleList.size()>0){
				for(StRule oldRule :oldRuleList){
					//1.规则特色复制
					oldRule.setId(null);
					oldRule.setStruccode(newStructCode);
					String newObjectCode =  map.get(oldRule.getObjectcode());
					oldRule.setObjectcode(newObjectCode);
					oldRule.setRulecode(stRuleDao.codeGenerator("StRule", flag.get(), count.get()));
					count.set(count.get() + 1);
					oldRule.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					ruleList.get().add(oldRule);
				}
			}
			//2.具体规则、规则行复制
			copyRuleDetail(oldStructcode, newStructCode);
		}
		//查询子模块或者规则，递归复制
		List<StStructrueVo> children =  structrueVoListMap.get().get(oldStructcode);
		if(children != null && children.size() > 0){
			for(StStructrueVo childStruct:children){
				copyStruct(oldModelcode, newModelcode, childStruct, newStructCode,map);
			}
		}		
	}
	
	/**
 * 复制规则类型下的模块、规则相关信息,用于额度计算产品规则信息的复制
 * @param newTypeId
 * @param map
 * @param oldStruct
 * @throws Exception
 */
public void copyStruct(String oldModelcode,String newModelcode,StStructrueVo oldStruct,String parentStruCode)throws Exception{
	//获取复制前的结构编码
	String oldStructcode = oldStruct.getStruccode();
	StStructrue newStruct = new StStructrue();
	//升级后编码生成
	String newStruCode = stStructureDao.codeGenerator("StStructrue", flag.get(), count.get());
	count.set(count.get() + 1);
	//设置新的规则体系code
	newStruct.setModelcode(newModelcode);
	//设置新的规则结构code
	newStruct.setStruccode(newStruCode);
	//处理父级code
	newStruct.setParentstruccode(parentStruCode);
	newStruct.setLevel(oldStruct.getLevel());
	newStruct.setStrucname(oldStruct.getStrucname());
	newStruct.setDescription(oldStruct.getDescription());
	newStruct.setSeq(oldStruct.getSeq().shortValue());
	newStruct.setObjectrule(oldStruct.getObjectrule());
	newStruct.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
	newStruct.setPrompt(oldStruct.getPrompt());
	newStruct.setClassification(oldStruct.getClassification());
	newStruct.setRelatecode(oldStruct.getRelatecode());
	newStruct.setMisc(oldStruct.getMisc());
	//新结构入库
	strucList.get().add(newStruct);
	//获取新的结构编码
	String newStructCode = newStruct.getStruccode();
	
	//查询模块特色
	StModule stModule = new StModule();
	stModule.setStruccode(oldStructcode);
	List<StModule> oldMlist = stModuleDao.queryListByModel(stModule, StModule.class);
	if(oldMlist != null && oldMlist.size() > 0){
		StModule oldM = oldMlist.get(0);
		oldM.setId(null);//清空历史模块特色ID
		oldM.setStruccode(newStructCode);
		oldM.setModulecode(stModuleDao.codeGenerator("StModule", flag.get(), count.get()));
		count.set(count.get() + 1);
		oldM.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
		moduleList.get().add(oldM);
	}
	//查询规则特色相关并执行复制
	else{
		//1.查询规则特色集合
		StRule rule = new StRule();
		rule.setStruccode(oldStructcode);
		List<StRule> oldRuleList =  stRuleDao.queryListByModel(rule, StRule.class);
		for(StRule oldRule :oldRuleList){
			//1.规则特色复制
			oldRule.setId(null);
			oldRule.setStruccode(newStructCode);
			oldRule.setRulecode(stRuleDao.codeGenerator("StRule", flag.get(), count.get()));
			count.set(count.get() + 1);
			oldRule.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
			ruleList.get().add(oldRule);
		}
		//2.具体规则、规则行复制
		copyRuleDetail(oldStructcode, newStructCode);
	}
	//查询子模块或者规则，递归复制
	StStructrue structrue = new StStructrue();
	structrue.setParentstruccode(oldStructcode);
	List<StStructrueVo> children = stStructureDao.queryListByModel(structrue, StStructrueVo.class);
	if(children != null && children.size() > 0){
		for(StStructrueVo childStruct:children){
			copyStruct(oldModelcode, newModelcode, childStruct, newStructCode);
		}
	}		
}
	/**
	 * 复制规则类型下的对象、对象属性信息
	 * @param typeId
	 * @param map
	 * @param oldStruct
	 * @throws Exception
	 */
	public Map<String, String> copyObject(String oldModelcode,String newModelcode) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		//复制对象
		StObject stObject = new StObject();
		stObject.setModelcode(oldModelcode);
		List<StObject> sovs = objectListMap.get().get(oldModelcode);
		if(sovs.size()>0){
			for(StObject st:sovs){
				String oldObjectCode = st.getObjectcode();
				st.setId(null);
				st.setModelcode(newModelcode);
				st.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				objectList.get().add(st);
				map.put(oldObjectCode,st.getObjectcode());
			}
		}
		return map;
	}
	/**
	 * 具体规则、规则行复制
	 * @param oldStructId
	 * @param newStructId
	 * @throws Exception
	 */
	private void copyRuleDetail(String oldStructcode,String newStructcode)throws Exception{
		StRuleDetail ruleDetail = new StRuleDetail();
		ruleDetail.setStruccode(oldStructcode);
		List<StRuleDetail> oldRuleDetailList = ruleDetailListMap.get().get(oldStructcode);
		if(null != oldRuleDetailList&& oldRuleDetailList.size() > 0){
			for(StRuleDetail ord:oldRuleDetailList){
				String oldRdCode = ord.getDetailcode();
				ord.setId(null);
				ord.setDetailcode(stRuleDetailDao.codeGenerator("StRuleDetail", flag.get(), count.get()));
				count.set(count.get() + 1);
				ord.setStruccode(newStructcode);
				ord.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				detailList.get().add(ord);
				String newDetailCode = ord.getDetailcode();
				
				//3.查询规则行数据集合
				List<StRow> oldRowList = rowListMap.get().get(oldRdCode);
				if(null != oldRowList && oldRowList.size() >0){
					for(StRow oldRow:oldRowList){
						oldRow.setId(null);
						oldRow.setRowcode(stRowdao.codeGenerator("StRow", flag.get(), count.get()));
						count.set(count.get() + 1);
						oldRow.setDetailcode(newDetailCode);
						oldRow.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
						rowList.get().add(oldRow);
					}
				}
			}
		}
	}
	/**
	 * 删除规则体系
	 */
	public synchronized void deleteModel(String modelId) throws Exception {
		String modelSql = "delete from st_model where id='" + modelId + "'";
		String rowSql = "delete from st_row where detailcode in(select detailcode from st_rule_detail where struccode "
				+ "in (select struccode from st_structrue where modelcode in(select modelcode from st_model where id='" + modelId + "')))";
		String detailSql = "delete from st_rule_detail where struccode in(select struccode from st_structrue where modelcode "
				+ "in(select modelcode from st_model where id='" + modelId + "'))";
		String ruleSql = "delete from st_rule where struccode in(select struccode from st_structrue where modelcode "
				+ "in(select modelcode from st_model where id='" + modelId + "'))";
		String moduleSql = "delete from st_module where struccode in(select struccode from st_structrue where modelcode "
				+ "in(select modelcode from st_model where id='" + modelId + "'))";
		String strucSql = "delete from st_structrue where modelcode in(select modelcode from st_model where id='" + modelId + "')";
		String objectSql = "delete from st_object where modelcode in(select modelcode from st_model where id='" + modelId + "')";
		modelDao.deleteBySql(rowSql);
		modelDao.deleteBySql(detailSql);
		modelDao.deleteBySql(ruleSql);
		modelDao.deleteBySql(moduleSql);
		modelDao.deleteBySql(strucSql);
		modelDao.deleteBySql(objectSql);
		modelDao.deleteBySql(modelSql);
	}
	public void deleteChild(String ParentCode) throws Exception{
		StStructrue structrue = new StStructrue();
		structrue.setParentstruccode(ParentCode);
		List<StStructrue> ssvs = stStructureDao.queryListByModel(structrue, StStructrue.class);
		if(ssvs.size()>0){
			for(StStructrue ss:ssvs){
				stStructureDao.deleteById(ss.getId(), "ST_STRUCTRUE");
				deleteChild(ss.getStruccode());
			}
		}
	}

	@Override
	public List<StRateResultVo> querybymodel(StRateResult stRateResult,List<String> lists,String datefrom,String dateTo) throws Exception {
		return  stRateResultDao.querybymodel(stRateResult,lists,datefrom,dateTo);
	}

	@Override
	public List<StRateresultDataVo> queryListByModel(StRateresultData stRateresultData) throws Exception {
		return stRateresultDataDao.queryListByModel(stRateresultData, StRateresultDataVo.class);
	}

	@Override
	public List<StRateResultVo> queryListbymodel(StRateResult stRateResult) throws Exception {
		return stRateResultDao.queryListByModel(stRateResult, StRateResultVo.class);
	}

	@Override
	public List<StRateResultVo> querybyM(StRateResult stRateResult, List<String> lists, String datefrom, String dateTo)
			throws Exception {
		return stRateResultDao.querybyM(stRateResult, lists, datefrom, dateTo);
	}

	@Override
	public void ruleStatisticsExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rateScoreExport(HttpServletRequest request, HttpServletResponse response,
			StSystemMaintainForm systemForm) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rateDetailQuery(StSystemMaintainForm systemForm) throws Exception {
		// TODO Auto-generated method stub
		
	}

/*	@Override
	public void ruleStatisticsExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("开始规则日志统计数据导出...");
		long start = System.currentTimeMillis();
		String orgCode = request.getParameter("orgCode");
		String startTime = request.getParameter("dateFrom");
		String endTime = request.getParameter("dateTo");
		String mapId = request.getParameter("mapId");
		
		if(orgCode != null){
			StringBuffer sb = new StringBuffer("('" + orgCode + "'");
			Group group =  BaseGroupManager.getInstance().getGroup(orgCode);
			Iterator lists = BaseGroupManager.getInstance().getChildGroups(group, false);
			while(lists.hasNext()) {
				Group gr = (Group)lists.next();
				sb.append(",'");
				sb.append(gr.getId());
				sb.append("'");
			}
			sb.append(")");
			orgCode = sb.toString();
		}
		String basePath = request.getSession().getServletContext()
				.getRealPath("");
		String downloadPath = basePath + Constants.TEMP_DIRECTORY_FOR_DOWNLOAD;
		File downloadDirectory = new File(downloadPath);
		if (!downloadDirectory.exists())
			downloadDirectory.mkdirs();
		try {
			response.getWriter().print(getZippedExcelBySQLAfterTranslate(orgCode, startTime, endTime, mapId, downloadPath));
		} catch (Exception e) {
			logger.error(e);
			response.getWriter().print("ERROR");
		}
		System.out.println("导出用时：" + (System.currentTimeMillis() - start));
		
	}
	private String getZippedExcelBySQLAfterTranslate(String orgCodes, String startTime, String endTime,
			String mapId, String excelSavePath) throws Exception {
		String rarFileName = "ruleStatisticsData";
		File rarDirectory = new File(excelSavePath + "//" + rarFileName);
		if (rarDirectory.exists())
			rarDirectory.delete();
		rarDirectory.mkdir();
		List<StRuleLogVo> data = stRuleLogDao.querExportData(orgCodes, startTime, endTime);
		*//**
		 * 创建内容
		 *//*
		File excelFile = null;
		
		BufferedWriter bw = null;
		ColumnTranslateUtil colUtil = new ColumnTranslateUtil();
		int i = 0;
		StringBuffer sb = null;
		if(data != null && data.size() != 0){
			for(StRuleLogVo log : data){
				if(i % 50000 == 0){
					if(bw != null){
						bw.flush();
						bw.close();
					}
					excelFile = new File(excelSavePath + "//" + rarFileName + "//"
							+ rarFileName + "_" + i + ".csv");
					bw = new BufferedWriter(new FileWriter(excelFile));
					String header = "所属机构,业务编号,执行阶段,规则名称,规则结果,规则类型,执行对象,执行时间\n";
					bw.write(header);
					bw.flush();
				}
				
				//所属机构
				log.setOrgname(colUtil.getTranslatedParamValue(mapId, "instcode", new String[]{log.getInstcode()}));
				//业务编号
				
				//执行阶段
				String period = log.getOperperiod();
				if("STEP_1".equals(period)){
					period = "业务受理";
				}
				else if("STEP_2".equals(period)){
					period = "受理结论选择";
				}
				else if("STEP_3".equals(period)){
					period = "担保人添加";
				}
				else if("STEP_4".equals(period)){
					period = "初审阶段（补充准入判断）";
				}
				else if("STEP_7".equals(period)){
					period = "初审阶段（初审策略）";
				}
				else if("STEP_8".equals(period)){
					period = "终审阶段";
				}
				log.setOperperiod(period);
				
	
				//规则名称
				
				//规则结果
				String classification = log.getClassification();
				String ruleresult = log.getRuleresult();
				if("2".equals(classification)){
					String isleaf = log.getIsleaf();
					if("1".equals(isleaf)){
						if("1".equals(ruleresult)){
							ruleresult = "低";
						}
						else if("2".equals(ruleresult)){
							ruleresult = "中";
						}
						else if("4".equals(ruleresult)){
							ruleresult = "高";
						}
						else {
							ruleresult = "无";
						}
					}
					else{
						try {
							int t = Integer.valueOf(ruleresult);
							if(t > 4){
								ruleresult = "分流";
							}
							else{
								ruleresult = "通过";
							}
						} catch (Exception e) {
							ruleresult = "错误数据";
						}
					}
					
				}
				else{
					if("-1".equals(ruleresult)){
						ruleresult = "拒绝";
					}
					else if("-2".equals(ruleresult)){
						ruleresult = "退回";
					}
					else if("-3".equals(ruleresult)){
						ruleresult = "分流";
					}
					else{
						ruleresult = "通过";
					}
				}
				log.setRuleresult(ruleresult);
				
				//规则类型
				if("2".equals(classification)){
					classification = "组合规则";
				}
				else{
					classification = "单规则";
				}
				log.setClassification(classification);
				
				//执行对象
				String objectCode = log.getObjectcode();
				if(objectCode != null){
					if(objectCode.contains("Borrower") || objectCode.contains("Trial")){
						objectCode = "借款人";
					}
					else if(objectCode.contains("Warrant")){
						objectCode = "担保人";
					}
					else if(objectCode.contains("RelateCompany")){
						objectCode = "关联企业";
					}
					else if(objectCode.contains("Family")){
						objectCode = "家庭成员";
					}
				}
				log.setObjectcode(objectCode);
				//执行时间
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sb = new StringBuffer();
				sb.append(log.getOrgname() == null ? "" : log.getOrgname());
				sb.append(",");
				sb.append(log.getBusinesscode() == null ? "" : log.getBusinesscode());
				sb.append(",");
				sb.append(log.getOperperiod() == null ? "" : log.getOperperiod());
				sb.append(",");
				sb.append(log.getClassification() ==  null ? "" : log.getClassification());
				sb.append(",");
				sb.append(log.getStrucname() == null ? "" : log.getStrucname());
				sb.append(",");
				sb.append(log.getRuleresult() == null ? "" : log.getRuleresult());
				sb.append(",");
				sb.append(log.getObjectcode() == null ? "" : log.getObjectcode());
				sb.append(",");
				sb.append(log.getOperatetime() == null ? "" : format.format(log.getOperatetime()));
				sb.append("\n");
				
				bw.write(sb.toString());
				bw.flush();
				i++;
			}
		}
		else{
			excelFile = new File(excelSavePath + "//" + rarFileName + "//"
					+ rarFileName + "_" + i + ".csv");
			bw = new BufferedWriter(new FileWriter(excelFile));
			String header = "所属机构,业务编号,执行阶段,规则名称,规则结果,规则类型,执行对象,执行时间\n";
			bw.write(header);
			bw.flush();
		}
		bw.close();
		ZipUtil.compress(excelSavePath + "//" + rarFileName, excelSavePath
				+ "//" + rarFileName + ".zip");
		return rarFileName + ".zip";
	}*/
	/**
	 * 规则统计数据处理
	 * 
	 * @param sql
	 * @param mapId
	 * @param excelSavePath
	 * @return 已压缩Excel形成zip文件
	 * @throws Exception
	 */
	/*private String getZippedExcelBySQLAfterTranslate(String orgCodes, String startTime, String endTime,
			String mapId, String excelSavePath) throws Exception {
		String rarFileName = "ruleStatisticsData";
		File rarDirectory = new File(excelSavePath + "//" + rarFileName);
		if (rarDirectory.exists())
			rarDirectory.delete();
		rarDirectory.mkdir();
		List<StRuleLogVo> data = stRuleLogDao.querExportData(orgCodes, startTime, endTime);
		*//**
		 * 创建内容
		 *//*
		int pageNumber = 1;
		File excelFile = null;
		OutputStream os = null;
		ZWorkBook workbook = null;
		HSSFSheet sheet = null;
		ColumnTranslateUtil colUtil = new ColumnTranslateUtil();
		for (int i = 1, x = data.size(); i <= 100000; i++) {
			if(i % 10000 == 0){
				System.out.println(i);
			}
			*//**
			 * 创建表头
			 *//*
			if (i == 1 || i % 50001 == 0) {
				if (excelFile != null) {
					workbook.write(os);
					os.flush();
					os.close();
					excelFile = null;
					sheet = null;
					workbook = null;
				}
				excelFile = new File(excelSavePath + "//" + rarFileName + "//"
						+ rarFileName + i + ".xls");
				os = new FileOutputStream(excelFile);
				workbook = new ZWorkBook();
				sheet = workbook.createSheet();
				HSSFRow titleRow = sheet.createRow(0);
				short tmp = 0;
				
				HSSFCell cell = titleRow.createCell(tmp++);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue("所属机构");
				cell.setCellStyle(workbook.getTitleStyle());
				
				cell = titleRow.createCell(tmp++);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue("业务编号");
				cell.setCellStyle(workbook.getTitleStyle());
				
				cell = titleRow.createCell(tmp++);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue("执行阶段");
				cell.setCellStyle(workbook.getTitleStyle());
				
				cell = titleRow.createCell(tmp++);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue("规则类型");
				cell.setCellStyle(workbook.getTitleStyle());
				
				cell = titleRow.createCell(tmp++);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue("规则名称");
				cell.setCellStyle(workbook.getTitleStyle());
				
				cell = titleRow.createCell(tmp++);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue("规则结果");
				cell.setCellStyle(workbook.getTitleStyle());
				
				cell = titleRow.createCell(tmp++);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue("执行对象");
				cell.setCellStyle(workbook.getTitleStyle());
				
				cell = titleRow.createCell(tmp++);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue("执行时间");
				cell.setCellStyle(workbook.getTitleStyle());
				
				pageNumber++;
			}
			if (workbook == null)
				throw new Exception("查询结果为空");
			if (sheet == null)
				throw new Exception("生成Excel失败，sheet为null");
			HSSFRow row = sheet.createRow(i % 50000);
			StRuleLogVo log = data.get(i - 1);
			
			short tmp = 0;
			HSSFCell cell = row.createCell(tmp++);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			//所属机构
			cell.setCellValue(colUtil.getTranslatedParamValue(mapId, "instcode", new String[]{log.getInstcode()}));
			//业务编号
			cell = row.createCell(tmp++);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(log.getBusinesscode());
			//执行阶段
			cell = row.createCell(tmp++);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			String period = log.getOperperiod();
			if("STEP_1".equals(period)){
				period = "业务受理";
			}
			else if("STEP_2".equals(period)){
				period = "受理结论选择";
			}
			else if("STEP_3".equals(period)){
				period = "担保人添加";
			}
			else if("STEP_4".equals(period)){
				period = "初审阶段（补充准入判断）";
			}
			else if("STEP_7".equals(period)){
				period = "初审阶段（初审策略）";
			}
			else if("STEP_8".equals(period)){
				period = "终审阶段";
			}
			cell.setCellValue(period);
			
			//规则类型
			cell = row.createCell(tmp++);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			String classification = log.getClassification();
			if("2".equals(classification)){
				classification = "组合规则";
			}
			else{
				classification = "单规则";
			}
			cell.setCellValue(classification);
			
			//规则名称
			cell = row.createCell(tmp++);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(log.getStrucname());
			
			//规则结果
			cell = row.createCell(tmp++);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			classification = log.getClassification();
			String ruleresult = log.getRuleresult();
			if("1".equals(classification)){
				if("-1".equals(ruleresult)){
					ruleresult = "拒绝";
				}
				else if("-2".equals(ruleresult)){
					ruleresult = "退回";
				}
				else if("-3".equals(ruleresult)){
					ruleresult = "分流";
				}
				else{
					ruleresult = "通过";
				}
			}
			else{
				String isleaf = log.getIsleaf();
				if("1".equals(isleaf)){
					if("1".equals(ruleresult)){
						ruleresult = "低";
					}
					else if("2".equals(ruleresult)){
						ruleresult = "中";
					}
					else if("4".equals(ruleresult)){
						ruleresult = "高";
					}
					else {
						ruleresult = "无";
					}
				}
				else{
					try {
						int t = Integer.valueOf(ruleresult);
						if(t > 4){
							ruleresult = "分流";
						}
						else{
							ruleresult = "通过";
						}
					} catch (Exception e) {
						ruleresult = "错误数据";
					}
				}
			}
			cell.setCellValue(ruleresult);
			
			//执行对象
			cell = row.createCell(tmp++);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			String objectCode = log.getObjectcode();
			if(objectCode != null){
				if(objectCode.contains("Borrower") || objectCode.contains("Trial")){
					objectCode = "借款人";
				}
				else if(objectCode.contains("Warrant")){
					objectCode = "担保人";
				}
				else if(objectCode.contains("RelateCompany")){
					objectCode = "关联企业";
				}
				else if(objectCode.contains("Family")){
					objectCode = "家庭成员";
				}
			}
			cell.setCellValue(objectCode);
			//执行时间
			cell = row.createCell(tmp++);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			cell.setCellValue(dateFormat.format(log.getOperatetime()));
		}
		if (workbook == null)
			throw new Exception("没有符合条件的记录!");
		workbook.write(os);
		os.flush();
		os.close();
		os = null;
		ZipUtil.compress(excelSavePath + "//" + rarFileName, excelSavePath
				+ "//" + rarFileName + ".zip");
		return rarFileName + ".zip";
	}
*/


/*	@Override
	public void rateScoreExport(HttpServletRequest request, HttpServletResponse response,StSystemMaintainForm systemForm) throws Exception {
		logger.debug("开始规则日志统计数据导出...");
		long start = System.currentTimeMillis();
		String basePath = request.getSession().getServletContext()
				.getRealPath("");
		String downloadPath = basePath + Constants.TEMP_DIRECTORY_FOR_DOWNLOAD;
		File downloadDirectory = new File(downloadPath);
		if (!downloadDirectory.exists())
			downloadDirectory.mkdirs();
		try {
			response.getWriter().print(rateScoreExcelBySQLAfterTranslate(systemForm, downloadPath));
		} catch (Exception e) {
			logger.error(e);
			response.getWriter().print("ERROR");
		}
		System.out.println("导出用时：" + (System.currentTimeMillis() - start));
		
	}
	private String rateScoreExcelBySQLAfterTranslate(StSystemMaintainForm systemForm, String excelSavePath) throws Exception {
		String rarFileName = "ruleScoreData";
		File rarDirectory = new File(excelSavePath + "//" + rarFileName);
		if (rarDirectory.exists())
			rarDirectory.delete();
		rarDirectory.mkdir();
		rateDetailQuery(systemForm);
		List<StStructrueVo> ratedata = systemForm.getRateList();
		
		List<StStructrueVo> amountData = systemForm.getAmountList();
		StStructrueVo ssv = new StStructrueVo();
		ratedata.add(ssv);
		if(amountData.size() >0 &null != amountData){
			for(int i=0 ; i<amountData.size();i++){
				ratedata.add(amountData.get(i));
			}
		}
		File excelFile = null;
		
		BufferedWriter bw = null;
		int i = 0;
		StringBuffer sb = null;
		if(ratedata != null && ratedata.size() != 0){
			for(StStructrueVo log : ratedata){
				if(i % 50000 == 0){
					if(bw != null){
						bw.flush();
						bw.close();
					}
					excelFile = new File(excelSavePath + "//" + rarFileName + "//"
							+ rarFileName + "_" + i + ".csv");
					bw = new BufferedWriter(new FileWriter(excelFile));
					String header = "规则名称,规则数据,执行结果\n";
					bw.write(header);
					bw.flush();
				}
				sb = new StringBuffer();
				sb.append(log.getStrucname() == null ? "" : log.getStrucname());
				sb.append(",");
				String data = "";
				if(log.getRuleData() != null){
					String [] ruledata = log.getRuleData().split(",");
					for(int k=0 ; k<ruledata.length ; k++){
						data += ruledata[k];
					}
				}
				sb.append(data);
				sb.append(",");
				sb.append(log.getRuleresult() == null ? "" : log.getRuleresult());
				sb.append("\n");
				
				bw.write(sb.toString());
				bw.flush();
				i++;
			}
		}
		else{
			excelFile = new File(excelSavePath + "//" + rarFileName + "//"
					+ rarFileName + "_" + i + ".csv");
			bw = new BufferedWriter(new FileWriter(excelFile));
			String header = "规则名称,规则数据,执行结果\n";
			bw.write(header);
			bw.flush();
		}
		bw.close();
		ZipUtil.compress(excelSavePath + "//" + rarFileName, excelSavePath
				+ "//" + rarFileName + ".zip");
		return rarFileName + ".zip";
	}*/
	
/*	//查询评级数据
	public void rateDetailQuery(StSystemMaintainForm systemForm) throws Exception{
		// 查询评级数据
		StRateresultData sr = new StRateresultData();
		sr.setRateresultcode(systemForm.getStRateresultDataVo().getRateresultcode());
		sr.getPageListData().setPageSize(Integer.MAX_VALUE);
		List<StRuleLogVo> logList = stRuleLogDao.queryRateLog(systemForm.getStRateResultVo().getBusinesscode());
		List<StRateresultDataVo> rvlist = queryListByModel(sr);
		List<StRateresultDataVo> rvlists = new ArrayList<StRateresultDataVo>();
		if (null != rvlist && rvlist.size() > 0) {
			for (StRateresultDataVo srv : rvlist) {
				if ("1".equals(srv.getIsenum())) {
					try {
						Map map = SmTools.getParamListInfo(srv.getDictcode());
						Iterator it = map.entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry entry = (Map.Entry) it.next();
							if (entry.getKey().equals(srv.getDatavalue())) {
								srv.setDatavalue((String) entry.getValue());
							}
						}
					} catch (Exception e) {
						List<ParamPopupBean> list = SmTools.getParamsDisplay(srv.getDictcode(), null, null);
						if (list != null && list.size() > 0) {
							for (ParamPopupBean pp : list) {
								if (srv.getDatavalue().equals(pp.getCode()))
									srv.setDatavalue(pp.getName());
							}
						}
					}

				}
				if (StUtil.judgeIsNull(srv.getDatavalue()) && "industry".equals(srv.getDatakey())) {
					String name = SmTools.getParamName(null, srv.getDatavalue());
					srv.setDatavalue(name);
				}
				if (StUtil.judgeIsNull(srv.getDatavalue()) && "productCode".equals(srv.getDatakey())) {
					DictEntryVo dev = DictEntryDao.getInstance().getDictEntryVo("tsBusiType", srv.getDatavalue());
					srv.setDatavalue(dev.getDeName());
				}
				if (StUtil.judgeIsNull(srv.getDatavalue()) && "smallProduct".equals(srv.getDatakey())) {
					// 查询产品中类
					List<ParamPopupBean> lists = new SmTools().getParamsDisplay(busyname, productco, null);
					for (ParamPopupBean ppb : lists) {
						if (ppb.getCode().equals(srv.getDatavalue())) {
							srv.setDatavalue(ppb.getName());
						}
					}
				}
				if (StUtil.strIsDouble(srv.getDatavalue())) {
					srv.setDatavalue(StUtil.formatAmount(srv.getDatavalue()));
				}
				rvlists.add(srv);
			}
		}
		List<StStructrueVo> strucList = stStructureDao.queryRateScoreDetail(logList.get(1).getInputdatacode());
		Pattern p = Pattern.compile("#\\{[^#]*\\}");
		Matcher m = null;
		if (!strucList.isEmpty()) {
			for (StStructrueVo strucVo : strucList) {
				StRuleDetail detail = new StRuleDetail();
				detail.setStruccode(strucVo.getStruccode());
				Set<String> set = new HashSet<String>();
				List<StRuleDetailVo> detailList = stRuleDetailDao.queryListByModel(detail, StRuleDetailVo.class);
				if (!detailList.isEmpty()) {
					for (StRuleDetailVo detailVo : detailList) {
						if(detailVo.getPrecondition() != null){
							m = p.matcher(detailVo.getPrecondition());
							while (m.find()) {
								String g = m.group();
								String key = g.substring(2, g.length() - 1);
								if(!set.add(key)){
									continue;
								}
								for (StRateresultDataVo srv : rvlists) {
									if (key.equals(srv.getDatakey())) {
										if (strucVo.getRuleData() == null) {
											strucVo.setRuleData(srv.getDataname() + ":" + (srv.getDatavalue() == null ? "无" : srv.getDatavalue()));
										} else {
											strucVo.setRuleData(strucVo.getRuleData() + ";" + srv.getDataname() + ":"
													+ (srv.getDatavalue() == null ? "无" : srv.getDatavalue()));
										}
									}
								}
							}
						}
					}
				}
			}
		}
		systemForm.setRateList(strucList);
		strucList = stStructureDao.queryRateScoreDetail(logList.get(0).getInputdatacode());
		List<StObjectPropertyVo> propertyList = StInit.getPropertyList().get("LimitAmountBorrower");
		StLogInputdata inputdata = new StLogInputdata();
		inputdata.setInputdatacode(logList.get(0).getInputdatacode());
		inputdata.getPageListData().setPageSize(Integer.MAX_VALUE);
		List<StLogInputdataVo> inputdataList = inputdatDao.queryListByModel(inputdata, StLogInputdataVo.class);
		if (!strucList.isEmpty()) {
			for (StStructrueVo strucVo : strucList) {
				StRuleDetail detail = new StRuleDetail();
				detail.setStruccode(strucVo.getStruccode());
				strucVo.setRuleresult(StUtil.formatAmountAll(strucVo.getRuleresult()));
				List<StRuleDetailVo> detailList = stRuleDetailDao.queryListByModel(detail, StRuleDetailVo.class);
				Set<String> set = new HashSet<String>();
				if (!detailList.isEmpty()) {
					for (StRuleDetailVo detailVo : detailList) {
						if(detailVo.getRuleresult() != null){
							m = p.matcher(detailVo.getRuleresult());
							while (m.find()) {
								String g = m.group();
								String key = g.substring(2, g.length() - 1);
								String name = "";
								String value = "";
								if(!set.add(key)){
									continue;
								}
								for(StObjectPropertyVo propertyVo : propertyList){
									if(key.equals(propertyVo.getPropertykey())){
										name = propertyVo.getPropertyname();
										break;
									}
								}
								for(StLogInputdataVo inputdataVo : inputdataList){
									if(key.equals(inputdataVo.getPropertykey())){
										value = inputdataVo.getPropertyvalue();
										break;
									}
								}
								if(strucVo.getRuleData() == null){
									strucVo.setRuleData(name + ":" + StUtil.formatAmount(value));
								}
								else{
									strucVo.setRuleData(strucVo.getRuleData() + ";" + name + ":" + StUtil.formatAmount(value));
								}
							}
						}
					}
				}
			}
		}
		systemForm.setAmountList(strucList);
	}*/
}
