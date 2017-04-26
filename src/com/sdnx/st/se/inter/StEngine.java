package com.sdnx.st.se.inter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sdnx.st.constants.RuleClass;
import com.sdnx.st.dp.model.PublicData;
import com.sdnx.st.se.calculator.StEngineCalculator;
import com.sdnx.st.se.calculator.StEngineCalculatorInterface;
import com.sdnx.st.se.constants.CalculatorConstant;
import com.sdnx.st.se.result.StResultObject;
import com.sdnx.st.util.StUtil;

public class StEngine implements StEngineInterface{
	private static class Inner{
		static StEngine stEngine = new StEngine();
	}
	public static StEngine getInstance(){
		return Inner.stEngine;
	}
	private StEngine(){
	}
	
	private StEngineCalculatorInterface calculator = new StEngineCalculator();
	
	@Override
	public StResultObject StAdmitWithMap(List<Map<String, Object>> dataSource, PublicData pd ) {
		return null;
	}
	@Override
	public StResultObject StFirstTrialWithMap(List<Map<String, Object>> dataSource, PublicData pd) {
		return null;
	}
	@Override
	public StResultObject StLastTrialWithMap(List<Map<String, Object>> dataSource, PublicData pd) {
		return null;
	}
	@Override
	public StResultObject StCalculateLimtAmoutWithMap(List<Map<String, Object>> dataSource, PublicData pd) {
		return null;
	}
	@Override
	public StResultObject StRateWithMap(List<Map<String, Object>> dataSource, PublicData pd ){
		return null;
	}
	@Override
	public StResultObject StAdmitWithObject(List<Object> dataSource, PublicData pd ) {
		List<Map<String, Object>> mapDataSource = new ArrayList<Map<String, Object>>();
		for(Object o : dataSource){
			mapDataSource.add(objectToMap(o));
		}
		return calculator.calculate(mapDataSource, RuleClass.admit.getCode(), pd);
	}
	@Override
	public StResultObject StFirstTrialWithObject(List<Object> dataSource, PublicData pd ){
		List<Map<String, Object>> mapDataSource = new ArrayList<Map<String, Object>>();
		for(Object o : dataSource){
			mapDataSource.add(objectToMap(o));
		}
		return calculator.calculate(mapDataSource, RuleClass.firstTrial.getCode(), pd);
	}
	@Override
	public StResultObject StLastTrialWithObject(List<Object> dataSource, PublicData pd ){
		List<Map<String, Object>> mapDataSource = new ArrayList<Map<String, Object>>();
		for(Object o : dataSource){
			mapDataSource.add(objectToMap(o));
		}
		return calculator.calculate(mapDataSource, RuleClass.lastTrial.getCode(), pd);
	}
	@Override
	public StResultObject StCalculateLimtAmoutWithObject(List<Object> dataSource, PublicData pd) {
		List<Map<String, Object>> mapDataSource = new ArrayList<Map<String, Object>>();
		for(Object o : dataSource){
			mapDataSource.add(objectToMap(o));
		}
		return calculator.calculate(mapDataSource, RuleClass.limitAmount.getCode(), pd);
	}
	@Override
	public StResultObject StRateWithObject(List<Object> dataSource, PublicData pd ){
		List<Map<String, Object>> mapDataSource = new ArrayList<Map<String, Object>>();
		for(Object o : dataSource){
			mapDataSource.add(objectToMap(o));
		}
		return calculator.calculate(mapDataSource, RuleClass.rate.getCode(), pd);
	}
	@Override
	public boolean StCheckRuleCompleteness() {
		return false;
	}
	@Override
	public StResultObject StProcessWithMap(List<Map<String, Object>> dataSource, PublicData pd) {
		return null;
	}
	@Override
	public StResultObject StProcessWithObject(List<Object> dataSource, PublicData pd) {
		List<Map<String, Object>> mapDataSource = new ArrayList<Map<String, Object>>();
		for(Object o : dataSource){
			mapDataSource.add(objectToMap(o));
		}
		return calculator.calculate(mapDataSource, RuleClass.ProcessTrend.getCode(), pd);
	}
	
	//对象转map
	public static Map<String, Object> objectToMap(Object o){
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fs = o.getClass().getDeclaredFields();
		for(Field f : fs){
			String fieldName = f.getName();
			char[] cs = fieldName.toCharArray();
			String tailMethod = fieldName;
			if(cs[0] >= 'a' && cs[0] <= 'z'){
				cs[0] -= 32;
				tailMethod = String.valueOf(cs);
			}
			try {
				Method m = o.getClass().getDeclaredMethod("get" + tailMethod, new Class[]{});
				//所有字符数据加单引号
				Object tmp = m.invoke(o, new Object[]{});
				if(tmp == null || StUtil.strIsDouble(String.valueOf(tmp)) || fieldName.equals(CalculatorConstant.CUST_CODE_FLAG) || fieldName.equals(CalculatorConstant.CUST_NAME_FLAG))
					map.put(fieldName, tmp);
				else map.put(fieldName, tmp == null ? null : ("'" + tmp.toString().trim() + "'"));
				if("custType".equals(fieldName)){
					if(StUtil.judgeIsNull(tmp+""))
						map.put(fieldName, "'" + tmp + "'");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return map;
	}
	@Override
	public StResultObject StRateWithObjectNotRecordLog(List<Object> dataSource, PublicData pd) {
		List<Map<String, Object>> mapDataSource = new ArrayList<Map<String, Object>>();
		for(Object o : dataSource){
			mapDataSource.add(objectToMap(o));
		}
		return calculator.calculate(mapDataSource, RuleClass.rate.getCode(), pd, Boolean.FALSE);
	}
	
}
