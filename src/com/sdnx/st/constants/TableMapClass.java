package com.sdnx.st.constants;

public enum TableMapClass {
	ST_MODEL("StModel", "ST_MODEL_SEQ", "ST"),
	ST_STRUCTRUE("StStructrue", "ST_STRUCTRUE_SEQ", "SS"),
	ST_MODULE("StModule", "ST_MODULE_SEQ", "SM"),
	ST_RULE("StRule", "ST_RULE_SEQ", "SR"),
	ST_RULE_DETAIL("StRuleDetail", "ST_RULEDETAIL_SEQ", "RD"),
	ST_ROW("StRow", "ST_ROW_SEQ", "SW"),
	ST_OBJECT("StObject", "ST_OBJECT_SEQ", "SO"),
	ST_OBJECT_PROPERTY("StObjectProperty", "ST_OBJECTPROPETY_SEQ", "OP"),
	ST_DATATYPE("StDatatype", "ST_DATATYPE_SEQ", "SD"),
	ST_RULE_LOG("StRuleLog", "ST_RULELOG_SEQ", "RL"),
	ST_RULE_LOG_HI("StRuleLogHi", "", ""),
	ST_LOG_INPUTDATA("StLogInputdata", "ST_INPUTDATA_SEQ", "LI"),
	ST_LOG_INPUTDATA_HI("StLogInputdataHi", "", ""),
	ST_OBSERVATION("StObservation", "ST_OBSERVATION_SEQ", "OB"),
	ST_ORG_SPREAD("StOrgSpread", "", ""),
	ST_PRODUCT_SPREAD("StProductSpread", "", ""),
	ST_LIMITAMOUNT_PARM("StLimitamountParm", "", ""),
	ST_LIMIT_INDUSTRY("StLimitIndustry", "", ""),
	ST_RATE_RULER("StRateRuler", "", ""),
	ST_TMP_DATA("StTmpData", "", ""),
	ST_RATE_RESULT("StRateResult","ST_RATERESULT_SEQ","SR"),
	ST_RATERESULT_DATA("StRateresultData","",""),
	ST_PRODUCT_HIGHESTAMOUNT("StProductHighestAmount","","");
	private String className;
	
	private String seqName;
	
	private String codeHead;

	public String getCodeHead() {
		return codeHead;
	}

	public void setCodeHead(String codeHead) {
		this.codeHead = codeHead;
	}

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	private TableMapClass(String className, String seqName, String codeHead){
		this.className = className;
		this.seqName = seqName;
		this.codeHead = codeHead;
	}
	
	public static String getTableName(String className){
		if(null == className)
			return null;
		if("StModel".equals(className))
			return ST_MODEL.toString();
		if("StStructrue".equals(className))
			return ST_STRUCTRUE.toString();
		if("StModule".equals(className))
			return ST_MODULE.toString();
		if("StRule".equals(className))
			return ST_RULE.toString();
		if("StRuleDetail".equals(className))
			return ST_RULE_DETAIL.toString();
		if("StRow".equals(className))
			return ST_ROW.toString();
		if("StObject".equals(className))
			return ST_OBJECT.toString();
		if("StObjectProperty".equals(className))
			return ST_OBJECT_PROPERTY.toString();
		if("StDatatype".equals(className))
			return ST_DATATYPE.toString();
		if("StRuleLog".equals(className))
			return ST_RULE_LOG.toString();
		if("StRuleLogHi".equals(className))
			return ST_RULE_LOG_HI.toString();
		if("StLogInputdata".equals(className))
			return ST_LOG_INPUTDATA.toString();
		if("StLogInputdataHi".equals(className))
			return ST_LOG_INPUTDATA_HI.toString();
		if("StObservation".equals(className))
			return ST_OBSERVATION.toString();
		if("StOrgSpread".equals(className))
			return ST_ORG_SPREAD.toString();
		if("StProductSpread".equals(className))
			return ST_PRODUCT_SPREAD.toString();
		if("StLimitamountParm".equals(className))
			return ST_LIMITAMOUNT_PARM.toString();
		if("StLimitIndustry".equals(className))
			return ST_LIMIT_INDUSTRY.toString();
		if("StRateRuler".equals(className))
			return ST_RATE_RULER.toString();
		if("StTmpData".equals(className))
			return ST_TMP_DATA.toString();
		if("StRateResult".equals(className))
			return ST_RATE_RESULT.toString();
		if("StRateresultData".equals(className))
			return ST_RATERESULT_DATA.toString();
		if("StProductHighestAmount".equals(className))
			return ST_PRODUCT_HIGHESTAMOUNT.toString();
		return null;
	}
	
	public static String getSeqName(String className){
		if(null == className)
			return null;
		if("StModel".equals(className))
			return ST_MODEL.seqName;
		if("StStructrue".equals(className))
			return ST_STRUCTRUE.seqName;
		if("StModule".equals(className))
			return ST_MODULE.seqName;
		if("StRule".equals(className))
			return ST_RULE.seqName;
		if("StRuleDetail".equals(className))
			return ST_RULE_DETAIL.seqName;
		if("StRow".equals(className))
			return ST_ROW.seqName;
		if("StObject".equals(className))
			return ST_OBJECT.seqName;
		if("StObjectProperty".equals(className))
			return ST_OBJECT_PROPERTY.seqName;
		if("StDatatype".equals(className))
			return ST_DATATYPE.seqName;
		if("StRuleLog".equals(className))
			return ST_RULE_LOG.seqName;
		if("StRuleLogHi".equals(className))
			return ST_RULE_LOG_HI.seqName;
		if("StLogInputdata".equals(className))
			return ST_LOG_INPUTDATA.seqName;
		if("StLogInputdataHi".equals(className))
			return ST_LOG_INPUTDATA_HI.seqName;
		if("StObservation".equals(className))
			return ST_OBSERVATION.seqName;
		if("StOrgSpread".equals(className))
			return ST_ORG_SPREAD.seqName;
		if("StProductSpread".equals(className))
			return ST_PRODUCT_SPREAD.seqName;
		if("StLimitamountParm".equals(className))
			return ST_LIMITAMOUNT_PARM.seqName;
		if("StLimitIndustry".equals(className))
			return ST_LIMIT_INDUSTRY.seqName;
		if("StRateRuler".equals(className))
			return ST_RATE_RULER.seqName;
		if("StObservation".equals(className))
			return ST_OBSERVATION.seqName;
		if("StRateResult".equals(className))
			return ST_RATE_RESULT.seqName;
		if("StRateresultData".equals(className))
			return ST_RATERESULT_DATA.seqName;
		if("StProductHighestAmount".equals(className))
			return ST_PRODUCT_HIGHESTAMOUNT.toString();
		return null;
	}
	public static String getCodeHead(String className){
		if(null == className)
			return null;
		if("StModel".equals(className))
			return ST_MODEL.codeHead;
		if("StStructrue".equals(className))
			return ST_STRUCTRUE.codeHead;
		if("StModule".equals(className))
			return ST_MODULE.codeHead;
		if("StRule".equals(className))
			return ST_RULE.codeHead;
		if("StRuleDetail".equals(className))
			return ST_RULE_DETAIL.codeHead;
		if("StRow".equals(className))
			return ST_ROW.codeHead;
		if("StObject".equals(className))
			return ST_OBJECT.codeHead;
		if("StObjectProperty".equals(className))
			return ST_OBJECT_PROPERTY.codeHead;
		if("StDatatype".equals(className))
			return ST_DATATYPE.codeHead;
		if("StRuleLog".equals(className))
			return ST_RULE_LOG.codeHead;
		if("StRuleLogHi".equals(className))
			return ST_RULE_LOG_HI.codeHead;
		if("StLogInputdata".equals(className))
			return ST_LOG_INPUTDATA.codeHead;
		if("StLogInputdataHi".equals(className))
			return ST_LOG_INPUTDATA_HI.codeHead;
		if("StObservation".equals(className))
			return ST_OBSERVATION.codeHead;
		if("StOrgSpread".equals(className))
			return ST_ORG_SPREAD.codeHead;
		if("StProductSpread".equals(className))
			return ST_PRODUCT_SPREAD.codeHead;
		if("StLimitamountParm".equals(className))
			return ST_LIMITAMOUNT_PARM.codeHead;
		if("StLimitIndustry".equals(className))
			return ST_LIMIT_INDUSTRY.codeHead;
		if("StRateRuler".equals(className))
			return ST_RATE_RULER.codeHead;
		if("StObservation".equals(className))
			return ST_OBSERVATION.codeHead;
		if("StRateResult".equals(className))
			return ST_RATE_RESULT.codeHead;
		if("StRateresultData".equals(className))
			return ST_RATERESULT_DATA.codeHead;
		if("StProductHighestAmount".equals(className))
			return ST_PRODUCT_HIGHESTAMOUNT.toString();
		return null;
	}
}
