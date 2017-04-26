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
	//多线程单例模式类声明
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
	//获取额度参数维护service对象实例
	public static StLimitamountServiceImpl getLimitamountService(){
		return Inner.limitamount;
	}
	//获取限控行业维护service对象实例
	public static StLimitIndustryServiceImpl getLimiIndustyService(){
		return Inner.limitindustry;
	}
	//获取机构推广维护service对象实例
	public static StOrgSpreadServiceImpl getOrgSpreadService(){
		return Inner.orgspread;
	}
	//获取产品推广service对象实例
	public static StProductSpreadImpl getProductSpreadService(){
		return Inner.productspread;
	}
	//获取规则维护service对象实例
	public static StRuleMaintainServiceImpl getRuleMaintainService(){
		return Inner.rule;
	}
	//获取规则体系维护service对象实例
	public static StSystemMaintainServiceImpl getSystemMaintainService(){
		return Inner.system;
	}
	//获取评级标尺service对象实例
	public static StRateRulerServiceImpl getRateRulerService(){
		return Inner.rateruler;
	}
	//获取产品最高额度service对象实例
	public static StProductHighestAmountImpl getProductHighestAmount(){
		return Inner.productHighestAmount;
	}
}
