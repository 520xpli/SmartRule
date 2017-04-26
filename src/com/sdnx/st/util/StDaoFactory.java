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
	//���̵߳���ģʽ��ʵ������
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
	//��ȡ��������dao����ʵ��
	public static StDatatypeDao getDatatypeDao(){
		return Inner.datatype;
	}
	//��ȡ��Ȳ���dao����ʵ��
	public static StLimitamountSpreadDao getLimitamountDao(){
		return Inner.limitamount;
	}
	//��ȡ�޿���ҵdao����ʵ��
	public static StLimitIndustryDao getLimitIndustryDao(){
		return Inner.limitindustry;
	}
	//��ȡ��־��������dao����ʵ��
	public static StLogInputdataDao getInputdataDao(){
		return Inner.inputdata;
	}
	//��ȡ��־����������ʷdao����ʵ��
	public static StLogInputdataHiDao getInputdatahiDao(){
		return Inner.inputdatahi;
	}
	//��ȡ����ģ��dao����ʵ��
	public static StModelDao getModelDao(){
		return Inner.model;
	}
	//��ȡ����ģ��dao����ʵ��
	public static StModuleDao getModuleDao(){
		return Inner.module;
	}
	//��ȡ����dao����ʵ��
	public static StObjectDao getObjectDao(){
		return Inner.object;
	}
	//��ȡ��������dao����ʵ��
	public static StObjectPropertyDao getObjectPropertyDao(){
		return Inner.objectProperty;
	}
	//��ȡ�۲���dao����ʵ��
	public static StObservationDao getObservationDao(){
		return Inner.observation;
	}
	//��ȡ�����ƹ�dao����ʵ��
	public static StOrgSpreadDao getOrgSpreadDao(){
		return Inner.orgspread;
	}
	//��ȡ��Ʒ�ƹ�dao����ʵ��
	public static StProductSpreadDao getProductSpreadDao(){
		return Inner.productspread;
	}
	//��ȡ������dao����ʵ��
	public static StRowDao getRowDao(){
		return Inner.row;
	}
	//��ȡ����dao����ʵ��
	public static StRuleDao getRuleDao(){
		return Inner.rule;
	}
	//��ȡ�������dao����ʵ��
	public static StRuleDetailDao getRuleDetailDao(){
		return Inner.ruledetail;
	}
	//��ȡ������־dao����ʵ��
	public static StRuleLogDao getRuleLogDao(){
		return Inner.rulelog;
	}
	//��ȡ������־��ʷdao����ʵ��
	public static StRuleLogHiDao getRuleLogHiDao(){
		return Inner.ruleloghi;
	}
	//��ȡ����ṹdao����ʵ��
	public static StStructrueDao getStructrueDao(){
		return Inner.struc;
	}
	//��ȡ�������dao����ʵ��
	public static StRateRulerDao getRateRulerDao(){
		return Inner.rateRuler;
	}
	//��ȡ�������dao����ʵ��
	public static StRateResultDao getRateResultDao(){
		return Inner.rateResultDao;
	}
	//��ȡ��������dao����ʵ��
	public static StRateresultDataDao getRateresultDataDao(){
		return Inner.rateresultDataDao;
	}
	//��ȡ��Ʒ��߶������dao����ʵ��
	public static StProductHighestAmountDao getProductHighestAmountDao(){
		return Inner.productHighestAmountDao;
	}
	//��ȡ����dao����ʵ��
	public static BaseDao getBaseDao(){
		return Inner.baseDao;
	}
}
