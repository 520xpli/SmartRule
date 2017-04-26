package com.sdnx.st.se.calculator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lxp.calculator.Calculator;
import com.sdnx.st.constants.AdmitResult;
import com.sdnx.st.constants.ErrorCode;
import com.sdnx.st.constants.JEvalResult;
import com.sdnx.st.constants.ObjectCode;
import com.sdnx.st.constants.RuleClass;
import com.sdnx.st.constants.TableMapClass;
import com.sdnx.st.dp.model.PublicData;
import com.sdnx.st.exceptions.StCalculateException;
import com.sdnx.st.se.checker.StEngineChecker;
import com.sdnx.st.se.constants.CalculatorConstant;
import com.sdnx.st.se.constants.GroupCal;
import com.sdnx.st.se.init.StInit;
import com.sdnx.st.se.result.StResultObject;
import com.sdnx.st.se.util.CalculateUtil;
import com.sdnx.st.se.util.CheckUtil;
import com.sdnx.st.sm.dao.StLogInputdataDao;
import com.sdnx.st.sm.dao.StObservationDao;
import com.sdnx.st.sm.dao.StRuleLogDao;
import com.sdnx.st.sm.dao.StStructrueDao;
import com.sdnx.st.sm.model.StLogInputdata;
import com.sdnx.st.sm.model.StObservation;
import com.sdnx.st.sm.model.StRuleLog;
import com.sdnx.st.sm.model.StStructrue;
import com.sdnx.st.sm.vo.StModelVo;
import com.sdnx.st.sm.vo.StModuleVo;
import com.sdnx.st.sm.vo.StRuleDetailVo;
import com.sdnx.st.sm.vo.StRuleVo;
import com.sdnx.st.sm.vo.StStructrueVo;
import com.sdnx.st.util.StDaoFactory;
import com.sdnx.st.util.StUtil;

import net.sourceforge.jeval.EvaluationException;

public class StEngineCalculator implements StEngineCalculatorInterface {
	
	private StRuleLogDao logDao = StDaoFactory.getRuleLogDao();
	private StLogInputdataDao inputDao = StDaoFactory.getInputdataDao();
	private StObservationDao obDao = StDaoFactory.getObservationDao();
	private StStructrueDao ssDao = StDaoFactory.getStructrueDao();
	
	private static Logger logger = Logger.getLogger(StEngineCalculator.class);
	
	private StResultObject checkObservation(List<Map<String, Object>> dataSource, String typeCode, StResultObject sr){
		if(typeCode.equals(RuleClass.admit.getCode())){
			try {
			//判断客户是否在观察期内
				for(Map<String, Object> obj : dataSource){
					if(obj.get(CalculatorConstant.CUST_CODE_FLAG) != null && obj.get(CalculatorConstant.CUST_CODE_FLAG) != null && ObjectCode.Admit.AdmitBorrower.toString().equals(obj.get(CalculatorConstant.OBJECT_FLAG))){
						String custCode = String.valueOf(obj.get(CalculatorConstant.CUST_CODE_FLAG)).replaceAll("'", "");
						StObservation so = new StObservation();
						so.setCustomercode(custCode);
						List<StObservation> list = obDao.queryListByModel(so, StObservation.class);
						if(list != null && list.size() > 0){
							for(StObservation o : list){
								long endTime = o.getEndtime().getTime();
								if(endTime >= new Date().getTime()){
									sr.setResult(String.valueOf(AdmitResult.refuse.getResult()));
									sr.setCode(CalculatorConstant.PASS_CODE);
									StStructrue ss = new StStructrue();
									ss.setStruccode(o.getStruccode());
									List<StStructrueVo> ssvList = ssDao.queryListByModel(ss, StStructrueVo.class);
									if(ssvList != null && !ssvList.isEmpty()){
										String custName = "客户:" + (obj.get(CalculatorConstant.CUST_NAME_FLAG) == null ? "" : String.valueOf(obj.get(CalculatorConstant.CUST_NAME_FLAG)).replaceAll("'", "")) + ",";
										sr.setSingleRuleDescription(custName + ssvList.get(0).getStrucname() + CalculatorConstant.OBSERVATION_PROMPT);
									}
									return sr;
								}
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error(e);
				sr.setCode(ErrorCode.observationError.getCode());
				sr.setSingleRuleDescription(ErrorCode.observationError.getDesc());
				return sr;
			}
		}
		return sr;
	}
	
	private void batchInsertLog(List<Map<String, Object>> dataSource, StObservation obser, List<StRuleLog> rule, List<StLogInputdata> inputdata, PublicData pd, boolean isRecordLog){
		try {
			if(obser.getStruccode() != null){
				obser.setInstcitycode(pd.getCoreCityCode());
				obser.setObservationcode(obDao.codeGenerator(TableMapClass.ST_OBSERVATION.getClassName()));
				obDao.addBySelfTransaction(obser);
			}
			if(!isRecordLog){
				return;
			}
			java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
			String inputCode = inputDao.codeGenerator(TableMapClass.ST_LOG_INPUTDATA.getClassName());
			for(Map<String, Object> data : dataSource){
				for (Iterator<Map.Entry<String, Object>> it = data.entrySet().iterator(); it.hasNext();) {
					Map.Entry<String, Object> entry = it.next();
					String key = entry.getKey();
					String value = entry.getValue() == null ? null : entry.getValue().toString();
					if(value == null) continue;
					StLogInputdata li = new StLogInputdata();
					li.setInputdatacode(inputCode);
					li.setObjectcode(data.get(CalculatorConstant.OBJECT_FLAG).toString());
					li.setPropertykey(key);
					li.setPropertyvalue(value);
					li.setLastopertime(now);
					li.setInstcitycode(pd.getCoreCityCode());
					inputdata.add(li);
				}
			}
			for(StRuleLog r : rule){
				String logCode = logDao.codeGenerator(TableMapClass.ST_RULE_LOG.getClassName());
				r.setLogcode(logCode);
				r.setInputdatacode(inputCode);
			}
			logDao.batchInsert(rule);
			inputDao.batchInsert(inputdata);
			
		} catch (Exception e) {
			logger.error("规则日志或者观察期记录插入失败,错误信息：" + e.getMessage());
		}
		rule.clear();
		inputdata.clear();
	}
	
	private Integer calGroupFunction(Integer a, Integer b, GroupCal function){
		switch (function) {
		case max:
			return CalculateUtil.max(a, b);
		case min:
			return CalculateUtil.min(a, b);
		case add:
			return CalculateUtil.add(a, b);
		default:
			return b;
		}
	}
	
	@Override
	public StResultObject calculate(List<Map<String, Object>> dataSource, String typeCode, PublicData pd, boolean isRecordLog){
		StInit.getInstance().checkIsNew();
		
		//数据校验
		StResultObject sr = StEngineChecker.checkData(dataSource, typeCode, pd);
		if(!sr.getCode().equals(CalculatorConstant.PASS_CODE)){
			return sr;
		}
		
		//观察期检查
		sr = checkObservation(dataSource, typeCode, sr);
		if(!sr.getCode().equals(CalculatorConstant.PASS_CODE)){
			return sr;
		}
		
		// 存储观察期数据
		StObservation obser = new StObservation();
		// 存储批量插入日志数据
		List<StRuleLog> rule = new LinkedList<StRuleLog>();
		List<StLogInputdata> inputdata = new LinkedList<StLogInputdata>();
		
		//获取规则树根结点
		Map<String, Object> ruleTypeMap = StInit.getRuleData(pd, typeCode);
		StModelVo stVo = (StModelVo) ruleTypeMap.get(typeCode);
		StStructrueVo ss = stVo.getSsList().get(0);
		
		//获取组合算法信息
		StModuleVo sm = ss.getStModuleVo();
		
		//得到最终结果
		Integer result = null;
		try {
			result = calculateRule(ss.getChildList(), dataSource, sm.getIsgroup(), sm.getGroupcal(), pd, sr,rule,obser);
		} catch (StCalculateException e) {
			sr.setCode(ErrorCode.ruleCalculateError.getCode());
			sr.setSingleRuleDescription(ErrorCode.ruleCalculateError.getDesc());
			return sr;
		}
		if (sr.getFinalResult() != Integer.MIN_VALUE) {
			result = sr.getFinalResult();
		}
		sr.setResult(String.valueOf(result == null ? "0" : result));
		
		//批量插入日志
		batchInsertLog(dataSource, obser, rule, inputdata, pd, isRecordLog);
		return sr;
	}

	@Override
	public String parseFormula(Map<String, Object> object, List<StRuleDetailVo> ruleDetailList) throws StCalculateException {
		String r = "0";
		String result = JEvalResult.FALSE.getCode();
		if(CheckUtil.listIsNull(ruleDetailList)){
			return r;
		}
		
		for (StRuleDetailVo rule : ruleDetailList) {
			try {
				String formula = rule.getPrecondition(), ruleResult = rule.getRuleresult();
				if (formula == null) {
					if (ruleResult == null) {
						result = ruleResult;
						r = ruleResult;
						break;
					}
					r = Calculator.arithmeticCalByNifix(ruleResult, object).toPlainString();
//					r = StUtil.getResult(ruleResult, object);
					result = r;
					break;
				}
//				result = StUtil.getResult(formula, object);
//				if (JEvalResult.TRUE.getCode().equals(result)) {
				if(Calculator.logicCalByNifix(result, object)){
					r = ruleResult;
					break;
				}
			} catch (Exception e) {
				
			}
		}
		
		return r;
	}

	private Integer calculateRule(List<StStructrueVo> strucList, List<Map<String, Object>> objList, String isGroup,
			String groupCal, PublicData pd, StResultObject ro, List<StRuleLog> ruleLog, StObservation obser) throws StCalculateException {
		if (AdmitResult.resultIsRefuse(ro.getFinalResult())) {
			return 0;
		}
		Integer result = null;
		//如果属于组合算法
		if (CheckUtil.strIsYes(isGroup)) {
			outer:
			for (StStructrueVo strucVo : strucList) {
				Integer tmp = null;
				//如果是叶子节点
				if (CheckUtil.listIsNull(strucVo.getChildList())) {
					for (Map<String, Object> obj : objList) {
						StRuleVo ruleVo = strucVo.getStRuleVo();
						//规则是否匹配当前对象
						if (ruleVo != null && String.valueOf(obj.get(CalculatorConstant.OBJECT_FLAG)).equals(ruleVo.getObjectcode())) {
							int singleResultTmp = StUtil.strToInt(parseFormula(obj, ruleVo.getRuleDetailList()));
							//如果结果大于0则表示满足组合规则条件
							if(singleResultTmp > 0){
								addGroupDescription(ro, strucVo.getPrompt(), obj);
							}
							
							logRecord(strucVo.getStruccode(), String.valueOf(singleResultTmp), pd, ruleLog);
							
							tmp = calGroupFunction(tmp, singleResultTmp, GroupCal.getFunctionByCode(strucVo.getObjectrule()));
						}
					}
				//如果是非叶子节点
				} else {
					StModuleVo moduleVo = strucVo.getStModuleVo();
					if (moduleVo.getPrecondition() != null) {
						for (Map<String, Object> obj : objList) {
							try {
								String groupCheckResult = StUtil.getResult(moduleVo.getPrecondition(), obj);
								if (groupCheckResult.equals(JEvalResult.TRUE.getCode())) {
									addDescription(ro, strucVo.getRelatecode(), null);
									break;
								}
								continue outer;
							} catch (EvaluationException e) {
								continue outer;
							}
						}
					}
					if (null != strucVo.getObjectrule()) {
						for (Map<String, Object> obj : objList) {
							List<Map<String, Object>> singleObjectList = new ArrayList<Map<String, Object>>();
							singleObjectList.add(obj);
							Integer singleResultTmp = calculateRule(strucVo.getChildList(), singleObjectList,
									strucVo.getStModuleVo().getIsgroup(), strucVo.getStModuleVo().getGroupcal(), pd,
									ro, ruleLog, obser);
							// 日志记录
							logRecord(strucVo.getStruccode(), String.valueOf(singleResultTmp), pd, ruleLog);
							
							tmp = calGroupFunction(tmp, singleResultTmp, GroupCal.getFunctionByCode(strucVo.getObjectrule()));
						}
					} else {
						tmp = calculateRule(strucVo.getChildList(), objList, strucVo.getStModuleVo().getIsgroup(),
								strucVo.getStModuleVo().getGroupcal(), pd, ro, ruleLog, obser);
						// 日志记录
						logRecord(strucVo.getStruccode(), String.valueOf(tmp), pd, ruleLog);
					}
				}
				// 计算组合算法
				result = calGroupFunction(tmp, result, GroupCal.getFunctionByCode(strucVo.getObjectrule()));
			}
			
		} else {
			//用于规则被拒绝后推出循环
			outer:
			for (StStructrueVo strucVo : strucList) {
				if (CheckUtil.listIsNull(strucVo.getChildList())) {
					for (Map<String, Object> obj : objList) {
						if (strucVo.getStRuleVo() == null) {
							break;
						}
						StRuleVo ruleVo = strucVo.getStRuleVo();
						if (String.valueOf(obj.get(CalculatorConstant.OBJECT_FLAG)).equals(ruleVo.getObjectcode())) {
							result = StUtil.strToInt((parseFormula(obj, ruleVo.getRuleDetailList())));
							// 日志记录
							logRecord(strucVo.getStruccode(), String.valueOf(result), pd, ruleLog);
							//如果准入结果不是通过
							if (AdmitResult.resultIsSplit(result)) {
								//用于退出递归
								ro.setFinalResult(result);
								addDescription(ro, strucVo.getPrompt(), obj);
								
								//设置规则观察期
								Integer cycle = ruleVo.getCycle();
								if(result == AdmitResult.refuse.getResult() && cycle != null && cycle != 0){
									setObservation(obj, pd, obser, strucVo, cycle);
								}
							}
							
							if(AdmitResult.resultIsRefuse(result)){
								break outer;
							}
							
							if(result > 0)
								addDescription(ro, strucVo.getPrompt(), obj);
						}
					}
				} else {
					StModuleVo moduleVo = strucVo.getStModuleVo();
					if (moduleVo.getPrecondition() != null) {
						for (Map<String, Object> obj : objList) {
							try {
								String groupCheckResult = StUtil.getResult(moduleVo.getPrecondition(), obj);
								if (groupCheckResult.equals(JEvalResult.TRUE.getCode())) {
									break;
								}
								continue outer;
							} catch (EvaluationException e) {
								continue outer;
							}
						}
					}
					result = calculateRule(strucVo.getChildList(), objList, moduleVo == null ? null : moduleVo.getIsgroup(),
							moduleVo == null ? null : moduleVo.getGroupcal(), pd, ro, ruleLog, obser);
					// 日志记录
					logRecord(strucVo.getStruccode(), String.valueOf(result), pd,ruleLog);
				}
			}
		}
		return result;
	}
	
	private void setObservation(Map<String, Object> obj, PublicData pd, StObservation obser, StStructrueVo strucVo, Integer cycle){
		Object custName = obj.get(CalculatorConstant.CUST_NAME_FLAG);
		obser.setCustomercode(custName == null ? null : String.valueOf(custName));
		obser.setBusinesscode(pd.getBusinesscode());
		obser.setStruccode(strucVo.getStruccode());
		Date now = new java.sql.Timestamp(new Date().getTime());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long l = 0;
		try {
			 l = sdf.parse(sdf.format(Calendar.getInstance().getTime())).getTime();
		} catch (ParseException e) {
			
		}
		obser.setStarttime(now);
		obser.setLastopertime(now);
		obser.setEndtime(new java.sql.Timestamp(l + cycle * 86400000));
	}

	private void addGroupDescription(StResultObject ro, String prompt, Map<String, Object> obj) {
		if(obj == null){
			ro.setGroupRuleDescription(prompt);
			return;
		}
		String custName = "客户:" + (obj.get(CalculatorConstant.CUST_NAME_FLAG) == null ? "" : String.valueOf(obj.get(CalculatorConstant.CUST_NAME_FLAG)).replaceAll("'", "")) + ",";
		if(ro.getGroupRuleDescription() == null)
			ro.setGroupRuleDescription(custName + prompt);
		else ro.setGroupRuleDescription(ro.getGroupRuleDescription() + ";\n" + custName + prompt);
	}

	// 日志记录
	private void logRecord(String strucCode, String result, PublicData pd, List<StRuleLog> ruleLog) {
		StRuleLog rl = new StRuleLog();
		java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
		rl.setStruccode(strucCode);
		rl.setRuleresult(result);
		rl.setOperatetime(now);
		rl.setBusinesscode(pd.getBusinesscode());
		rl.setOperatorcode(pd.getOprCode());
		rl.setInstcode(pd.getInscode());
		rl.setInstcitycode(pd.getCoreCityCode());
		rl.setDeptcode(pd.getLegalCode());
		rl.setOperperiod(pd.getStep());
		rl.setLastopertime(now);
		ruleLog.add(rl);
	}

	// 向返回结果添加提示信息
	private void addDescription(StResultObject ro, String prompt, Map<String, Object> obj) {
		if(obj == null) {
			ro.setSingleRuleDescription(prompt);
			return;
		}
		String custName = "客户:" + (obj.get(CalculatorConstant.CUST_NAME_FLAG) == null ? "" : String.valueOf(obj.get(CalculatorConstant.CUST_NAME_FLAG)).replaceAll("'", "")) + ",";
		if (ro == null || prompt == null || prompt.equals(""))
			return;
		if (ro.getSingleRuleDescription() == null || ro.getFinalResult() == AdmitResult.back.getResult() || ro.getFinalResult() == AdmitResult.refuse.getResult())
			ro.setSingleRuleDescription(custName + prompt);
		else
			ro.setSingleRuleDescription(ro.getSingleRuleDescription() + ";\n" + custName + prompt);
	}

	@Override
	public StResultObject calculate(List<Map<String, Object>> dataSource, String typeCode, PublicData pd) {
		return calculate(dataSource, typeCode, pd, Boolean.TRUE);
	}

}