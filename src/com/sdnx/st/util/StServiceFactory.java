package com.sdnx.st.util;

import com.sdnx.st.sm.service.impl.StLimitIndustryServiceImpl;
import com.sdnx.st.sm.service.impl.StLimitamountServiceImpl;
import com.sdnx.st.sm.service.impl.StOrgSpreadServiceImpl;
import com.sdnx.st.sm.service.impl.StProductHighestAmountImpl;
import com.sdnx.st.sm.service.impl.StProductSpreadImpl;
import com.sdnx.st.sm.service.impl.StRateRulerServiceImpl;
import com.sdnx.st.sm.service.impl.StRuleMaintainServiceImpl;
import com.sdnx.st.sm.service.impl.StSystemMaintainServiceImpl;

public class StServiceFactory {
	//���̵߳���ģʽ������
	private static class Inner{
		static StLimitamountServiceImpl limitamount = new StLimitamountServiceImpl();
		static StLimitIndustryServiceImpl limitindustry = new StLimitIndustryServiceImpl();
		static StOrgSpreadServiceImpl orgspread = new StOrgSpreadServiceImpl();
		static StProductSpreadImpl productspread =  new StProductSpreadImpl();
		static StRuleMaintainServiceImpl rule = new StRuleMaintainServiceImpl();
		static StSystemMaintainServiceImpl system = new StSystemMaintainServiceImpl();
		static StRateRulerServiceImpl rateruler = new StRateRulerServiceImpl();
		static StProductHighestAmountImpl productHighestAmount = new StProductHighestAmountImpl();
 	}
	//��ȡ��Ȳ���ά��service����ʵ��
	public static StLimitamountServiceImpl getLimitamountService(){
		return Inner.limitamount;
	}
	//��ȡ�޿���ҵά��service����ʵ��
	public static StLimitIndustryServiceImpl getLimiIndustyService(){
		return Inner.limitindustry;
	}
	//��ȡ�����ƹ�ά��service����ʵ��
	public static StOrgSpreadServiceImpl getOrgSpreadService(){
		return Inner.orgspread;
	}
	//��ȡ��Ʒ�ƹ�service����ʵ��
	public static StProductSpreadImpl getProductSpreadService(){
		return Inner.productspread;
	}
	//��ȡ����ά��service����ʵ��
	public static StRuleMaintainServiceImpl getRuleMaintainService(){
		return Inner.rule;
	}
	//��ȡ������ϵά��service����ʵ��
	public static StSystemMaintainServiceImpl getSystemMaintainService(){
		return Inner.system;
	}
	//��ȡ�������service����ʵ��
	public static StRateRulerServiceImpl getRateRulerService(){
		return Inner.rateruler;
	}
	//��ȡ��Ʒ��߶��service����ʵ��
	public static StProductHighestAmountImpl getProductHighestAmount(){
		return Inner.productHighestAmount;
	}
}
