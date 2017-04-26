package com.sdnx.st.util;

import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.base.BaseDaoImpl;
import com.sdnx.st.sm.dao.StDatatypeDao;
import com.sdnx.st.sm.dao.StLimitIndustryDao;
import com.sdnx.st.sm.dao.StLimitamountSpreadDao;
import com.sdnx.st.sm.dao.StLogInputdataDao;
import com.sdnx.st.sm.dao.StLogInputdataHiDao;
import com.sdnx.st.sm.dao.StModelDao;
import com.sdnx.st.sm.dao.StModuleDao;
import com.sdnx.st.sm.dao.StObjectDao;
import com.sdnx.st.sm.dao.StObjectPropertyDao;
import com.sdnx.st.sm.dao.StObservationDao;
import com.sdnx.st.sm.dao.StOrgSpreadDao;
import com.sdnx.st.sm.dao.StProductHighestAmountDao;
import com.sdnx.st.sm.dao.StProductSpreadDao;
import com.sdnx.st.sm.dao.StRateResultDao;
import com.sdnx.st.sm.dao.StRateRulerDao;
import com.sdnx.st.sm.dao.StRateresultDataDao;
import com.sdnx.st.sm.dao.StRowDao;
import com.sdnx.st.sm.dao.StRuleDao;
import com.sdnx.st.sm.dao.StRuleDetailDao;
import com.sdnx.st.sm.dao.StRuleLogDao;
import com.sdnx.st.sm.dao.StRuleLogHiDao;
import com.sdnx.st.sm.dao.StStructrueDao;
import com.sdnx.st.sm.dao.impl.StDatatypeDaoImpl;
import com.sdnx.st.sm.dao.impl.StLimitIndustryDaoImpl;
import com.sdnx.st.sm.dao.impl.StLimitamountSpreadDaoImpl;
import com.sdnx.st.sm.dao.impl.StLogInputdataDaoImpl;
import com.sdnx.st.sm.dao.impl.StLogInputdataHiDaoImpl;
import com.sdnx.st.sm.dao.impl.StModelDaoImpl;
import com.sdnx.st.sm.dao.impl.StModuleDaoImpl;
import com.sdnx.st.sm.dao.impl.StObjectDaoImpl;
import com.sdnx.st.sm.dao.impl.StObjectPropertyDaoImpl;
import com.sdnx.st.sm.dao.impl.StObservationDaoImpl;
import com.sdnx.st.sm.dao.impl.StOrgSpreadDaoImpl;
import com.sdnx.st.sm.dao.impl.StProductHighestAmountDaoImpl;
import com.sdnx.st.sm.dao.impl.StProductSpreadDaoImpl;
import com.sdnx.st.sm.dao.impl.StRateResultDaoImpl;
import com.sdnx.st.sm.dao.impl.StRateRulerDaoImpl;
import com.sdnx.st.sm.dao.impl.StRateresultDataDaoImpl;
import com.sdnx.st.sm.dao.impl.StRowDaoImpl;
import com.sdnx.st.sm.dao.impl.StRuleDaoImpl;
import com.sdnx.st.sm.dao.impl.StRuleDetailDaoImpl;
import com.sdnx.st.sm.dao.impl.StRuleLogDaoImpl;
import com.sdnx.st.sm.dao.impl.StRuleLogHiDaoImpl;
import com.sdnx.st.sm.dao.impl.StStructrueDaoImpl;

public class StDaoFactory {
	//多线程单例模式类实例声明
	private static class Inner{
		static StDatatypeDao datatype = new StDatatypeDaoImpl();
		static StLimitamountSpreadDao limitamount = new StLimitamountSpreadDaoImpl();
		static StLimitIndustryDao limitindustry = new StLimitIndustryDaoImpl();
		static StLogInputdataDao inputdata = new StLogInputdataDaoImpl();
		static StLogInputdataHiDao inputdatahi = new StLogInputdataHiDaoImpl();
		static StModelDao model = new StModelDaoImpl();
		static StModuleDao module = new StModuleDaoImpl();
		static StObjectDao object = new StObjectDaoImpl();
		static StObjectPropertyDao objectProperty = new StObjectPropertyDaoImpl();
		static StObservationDao observation = new StObservationDaoImpl();
		static StOrgSpreadDao orgspread = new StOrgSpreadDaoImpl();
		static StProductSpreadDao productspread = new StProductSpreadDaoImpl();
		static StRowDao row = new StRowDaoImpl();
		static StRuleDao rule = new StRuleDaoImpl();
		static StRuleDetailDao ruledetail = new StRuleDetailDaoImpl();
		static StRuleLogHiDao ruleloghi = new StRuleLogHiDaoImpl();
		static StRuleLogDao rulelog = new StRuleLogDaoImpl();
		static StStructrueDao struc = new StStructrueDaoImpl();
		static StRateRulerDao rateRuler = new StRateRulerDaoImpl();
		static StRateResultDao rateResultDao = new StRateResultDaoImpl();
		static StRateresultDataDao rateresultDataDao = new StRateresultDataDaoImpl();
		static StProductHighestAmountDao productHighestAmountDao = new StProductHighestAmountDaoImpl();
		static BaseDao baseDao = new BaseDaoImpl();
	}
	//获取数据类型dao对象实例
	public static StDatatypeDao getDatatypeDao(){
		return Inner.datatype;
	}
	//获取额度参数dao对象实例
	public static StLimitamountSpreadDao getLimitamountDao(){
		return Inner.limitamount;
	}
	//获取限控行业dao对象实例
	public static StLimitIndustryDao getLimitIndustryDao(){
		return Inner.limitindustry;
	}
	//获取日志输入数据dao对象实例
	public static StLogInputdataDao getInputdataDao(){
		return Inner.inputdata;
	}
	//获取日志输入数据历史dao对象实例
	public static StLogInputdataHiDao getInputdatahiDao(){
		return Inner.inputdatahi;
	}
	//获取规则模型dao对象实例
	public static StModelDao getModelDao(){
		return Inner.model;
	}
	//获取规则模块dao对象实例
	public static StModuleDao getModuleDao(){
		return Inner.module;
	}
	//获取对象dao对象实例
	public static StObjectDao getObjectDao(){
		return Inner.object;
	}
	//获取对象属性dao对象实例
	public static StObjectPropertyDao getObjectPropertyDao(){
		return Inner.objectProperty;
	}
	//获取观察期dao对象实例
	public static StObservationDao getObservationDao(){
		return Inner.observation;
	}
	//获取机构推广dao对象实例
	public static StOrgSpreadDao getOrgSpreadDao(){
		return Inner.orgspread;
	}
	//获取产品推广dao对象实例
	public static StProductSpreadDao getProductSpreadDao(){
		return Inner.productspread;
	}
	//获取行数据dao对象实例
	public static StRowDao getRowDao(){
		return Inner.row;
	}
	//获取规则dao对象实例
	public static StRuleDao getRuleDao(){
		return Inner.rule;
	}
	//获取具体规则dao对象实例
	public static StRuleDetailDao getRuleDetailDao(){
		return Inner.ruledetail;
	}
	//获取规则日志dao对象实例
	public static StRuleLogDao getRuleLogDao(){
		return Inner.rulelog;
	}
	//获取规则日志历史dao对象实例
	public static StRuleLogHiDao getRuleLogHiDao(){
		return Inner.ruleloghi;
	}
	//获取规则结构dao对象实例
	public static StStructrueDao getStructrueDao(){
		return Inner.struc;
	}
	//获取评级标尺dao对象实例
	public static StRateRulerDao getRateRulerDao(){
		return Inner.rateRuler;
	}
	//获取评级结果dao对象实例
	public static StRateResultDao getRateResultDao(){
		return Inner.rateResultDao;
	}
	//获取评级数据dao对象实例
	public static StRateresultDataDao getRateresultDataDao(){
		return Inner.rateresultDataDao;
	}
	//获取产品最高额度数据dao对象实例
	public static StProductHighestAmountDao getProductHighestAmountDao(){
		return Inner.productHighestAmountDao;
	}
	//获取基本dao对象实例
	public static BaseDao getBaseDao(){
		return Inner.baseDao;
	}
}
