package com.sdnx.st.dp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleRequestObject {
	/**
	 * 锟接匡拷锟斤拷息
	 */
	private InterInfo interInfo;
	
	/**
	 * 锟接口达拷锟斤拷锟斤拷锟�
	 * 
	 */
	private Map<String, Object> mapData = new HashMap<String, Object>();
	
	public void clear(){
		mapData.clear();
	}
	/**
	 * 执锟叫阶讹拷
	 */
	private String callStep;
	
	
	/**
	 * 锟斤拷取执锟叫阶讹拷
	 * @return
	 */
	public String getCallStep() {
		return callStep;
	}

	/**
	 * 锟斤拷锟斤拷执锟叫阶讹拷
	 * @param callStep
	 */
	public void setCallStep(String callStep) {
		this.callStep = callStep;
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟�
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value){
		mapData.put(key, value);
	}
	
	public Object get(String key){
		if(mapData.get(key) != null && (mapData.get(key) instanceof List || mapData.get(key) instanceof Map)){
			return mapData.get(key);
		}
		return String.valueOf(mapData.get(key));
	}
	
	public static void main(String[] args) {
		ArrayList<String> test = new ArrayList<String>();
		System.out.println(test instanceof List);
	}
	
	public InterInfo getInterInfo() {
		return interInfo;
	}
	
	/**
	 * 锟斤拷锟矫接匡拷锟斤拷息
	 * @param interInfo
	 */
	public void setInterInfo(InterInfo interInfo) {
		this.interInfo = interInfo;
	}

	/**
	 * 锟接匡拷锟斤拷息
	 */
	public static class InterInfo{
		/**
		 * 
		 * 锟接口憋拷锟�
		 */
		private String interCode;

		public String getInterCode() {
			return interCode;
		}

		public void setInterCode(String interCode) {
			this.interCode = interCode;
		}
		
	}
}
