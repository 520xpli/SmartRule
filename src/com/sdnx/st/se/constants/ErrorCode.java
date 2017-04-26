package com.sdnx.st.se.constants;

public enum ErrorCode {
	notHaveRule("SSSSS01","不存在对应规则"),
	notHaveObjectFlag("SSSSS02","数据源对象标识不存在"),
	objectNotMatch("SSSSS03","传入对象与规则体系不匹配"),
	notInitObjectProperty("SSSSS04","对象属性不存在"),
	dataCannotNull("SSSSS05", "不可为空"),
	propertyNotMatchEnum("SSSSS06","不符合枚举规范"),
	ageIsError("SSSSS07","不符合年龄规范"),
	dateIsError("SSSSS08","不符合日期规范"),
	timesIsError("SSSSS09","不符合次数规范"),
	termIsError("SSSSS10","不符合期限规范"),
	useRateError("SSSSS11","不符合使用率规范"),
	amountIsError("SSSSS12","不符合金额规范"),
	noRuleInterCode("SSSSS13","接口编号为空"),
	notHaveRuleInterCode("SSSSS14","接口编号不存在"),
	dataIsNull("SSSSS15","数据为空"),
	ruleCalculateError("SSSSS16","规则运算错误"),
	spreadError("SSSSS17", "机构产品推广数据库查询错误"),
	productSpreadError("SSSSS18", "产品推广数据库查询错误"),
	notHaveAmountParm("SSSSS19", "当前机构产品下额度参数不匹配"),
	amountQueryError("SSSSS20", "额度参数数据库查询错误"),
	limitIndustryError("SSSSS21","限控行业数据库查询错误"),
	observationError("SSSSS22","观察期数据库查询错误"),
	dataSourceIsNull("SSSSS23","数据源为空"),
	checkLimitAddError("SSSSS24", "核定额度添加或查询失败"),
	notFindFlow("SSSSS25", "找不到流程分支"),
	rateRulerError("SSSSS26", "评级数据库操作失败"),
	getCityCodeError("SSSSS27", "获取核心地势编号失败");
	private String code;
	private String desc;
	private ErrorCode(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
