package com.sdnx.st.dp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sdnx.st.constants.AdmitResult;
import com.sdnx.st.constants.ErrorCode;
import com.sdnx.st.constants.GroupRuler;
import com.sdnx.st.constants.GuaranteeType;
import com.sdnx.st.constants.ObjectCode;
import com.sdnx.st.constants.ObjectType;
import com.sdnx.st.constants.RateIndustry;
import com.sdnx.st.constants.YesNo;
import com.sdnx.st.constants.interfaceConstants.APP001;
import com.sdnx.st.constants.interfaceConstants.LMT001;
import com.sdnx.st.constants.interfaceConstants.Public;
import com.sdnx.st.constants.interfaceConstants.RR001;
import com.sdnx.st.constants.interfaceConstants.RuleInterface;
import com.sdnx.st.constants.interfaceConstants.ZD00001;
import com.sdnx.st.constants.interfaceConstants.ZD00002;
import com.sdnx.st.constants.interfaceConstants.ZD00003;
import com.sdnx.st.constants.interfaceConstants.ZD00004;
import com.sdnx.st.constants.interfaceConstants.ZD00005;
import com.sdnx.st.constants.interfaceConstants.ZD00006;
import com.sdnx.st.constants.interfaceConstants.ZD00007;
import com.sdnx.st.constants.interfaceConstants.ZD00008;
import com.sdnx.st.constants.interfaceConstants.ZD00009;
import com.sdnx.st.dp.model.Admit;
import com.sdnx.st.dp.model.AprovalProcess;
import com.sdnx.st.dp.model.FirstTrial;
import com.sdnx.st.dp.model.LastTrial;
import com.sdnx.st.dp.model.LimitAmount;
import com.sdnx.st.dp.model.PublicData;
import com.sdnx.st.dp.model.Rate;
import com.sdnx.st.dp.model.RuleRequestObject;
import com.sdnx.st.dp.model.RuleResponseObject;
import com.sdnx.st.se.init.StInit;
import com.sdnx.st.se.inter.StEngine;
import com.sdnx.st.se.inter.StEngineInterface;
import com.sdnx.st.se.result.StResultObject;
import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.dao.StLimitIndustryDao;
import com.sdnx.st.sm.dao.StLimitamountSpreadDao;
import com.sdnx.st.sm.dao.StOrgSpreadDao;
import com.sdnx.st.sm.dao.StProductHighestAmountDao;
import com.sdnx.st.sm.dao.StProductSpreadDao;
import com.sdnx.st.sm.dao.StRateResultDao;
import com.sdnx.st.sm.dao.StRateRulerDao;
import com.sdnx.st.sm.dao.StRateresultDataDao;
import com.sdnx.st.sm.model.StLimitIndustry;
import com.sdnx.st.sm.model.StLimitamountParm;
import com.sdnx.st.sm.model.StOrgSpread;
import com.sdnx.st.sm.model.StProductHighestAmount;
import com.sdnx.st.sm.model.StProductSpread;
import com.sdnx.st.sm.model.StRateResult;
import com.sdnx.st.sm.model.StRateresultData;
import com.sdnx.st.sm.utils.GroupDao;
import com.sdnx.st.sm.utils.ParamPopupBean;
import com.sdnx.st.sm.utils.SmTools;
import com.sdnx.st.sm.vo.StLimitIndustryVo;
import com.sdnx.st.sm.vo.StLimitamountParmVo;
import com.sdnx.st.sm.vo.StObjectPropertyVo;
import com.sdnx.st.sm.vo.StOrgSpreadVo;
import com.sdnx.st.sm.vo.StProductHighestAmountVo;
import com.sdnx.st.sm.vo.StProductSpreadVo;
import com.sdnx.st.util.StDaoFactory;
import com.sdnx.st.util.StUtil;

public class StCalculator implements StCalculatorInterface{
	private static Logger logger = Logger.getLogger(StCalculator.class);
	//运算通过编码
	private static final String passCode = "00000";
	
	//准入结果通过
	private static final String admitPass = "T";
	//准入结果拒绝
	private static final String admitRefuse = "1";
	//准入结果退回
	private static final String admitBack = "2";
	//准入结果分流
	private static final String admitSplit = "3";
	//准入结果提示
	private static final String admitPrompt = "4";
	//机构未开通自动化
	private static final String orgSpreadNotOpen = "当前机构未开通自动化审批流程";
	//当前机构产品未开通自动化
	private static final String productSpreadNotOpen = "当前客户申请产品未开通自动化审批流程";
	//产品申请额度过高
	private static final String productAmountLimit = "申请额度超过产品最高限额";
	//数据为空标识
	private static final String isEmptyFlag = "@EMPTY@";
	//低风险抵质押字典编码
	private static final String lowGuarDict = "cmiscc.lowGuarantyType";
	//中度风险抵质押字典编码
	private static final String mediumGuarDict = "cmiscc.mediumGuarantyType";
	//高风险抵质押字典编码
	private static final String highGuarDict = "cmiscc.highGuarantyType";
	//传入保证编码
	private static final String guarCode = "WZ000";
	//终审担保方式低风险抵质押代码
	private static final String LlowGuar = "LGTLR0";
	//终审担保方式中度风险抵质押代码
	private static final String LmediumGuar = "LGTMR0";
	//终审担保方式高风险抵质押代码
	private static final String LhighGuar = "LGTHR0";
	//终审担保方式保证代码
	private static final String Lguar = "LGTG00";
	//终审担保方式信用代码
	private static final String Lcredit = "LGTC00";
	//终审担保方式信用值
	private static final String Credit = "C100";
	//终审担保方式保证值
	private static final String guarantee = "C101";
	//核定额度
	private static final String checkLimit = "checkLimit";
	//无月数传值
	private static final String noMonth = "-1";
	
	private StEngineInterface stEngine = StEngine.getInstance();
	
	private StOrgSpreadDao orgDao = StDaoFactory.getOrgSpreadDao();
	
	private StProductSpreadDao productDao = StDaoFactory.getProductSpreadDao();
	
	private StLimitamountSpreadDao limitAmountDao = StDaoFactory.getLimitamountDao();
	
	private StLimitIndustryDao limitIndustryDao = StDaoFactory.getLimitIndustryDao();
	
	private StRateRulerDao rateRulerDao = StDaoFactory.getRateRulerDao();
	
	private BaseDao baseDao = StDaoFactory.getBaseDao();
	
	private StRateResultDao rateResultDao = StDaoFactory.getRateResultDao();
	
	private StRateresultDataDao rateresultDataDao = StDaoFactory.getRateresultDataDao();
	
	private StProductHighestAmountDao productAmountDao = StDaoFactory.getProductHighestAmountDao();
	
	
	private static class Inner{
		static StCalculator stCalculator = new StCalculator();
	}
	
	public static StCalculator getInstance(){
		return Inner.stCalculator;
	}
	
	private StCalculator(){
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.sdnx.st.dp.StCalculatorInterface#calculateRule(com.sdnx.rule.vo.RuleRequestObject)
	 */
	@Override
	public RuleResponseObject calculateRule(RuleRequestObject requestObject) {
		long start = System.currentTimeMillis();
		RuleResponseObject result = new RuleResponseObject();
/*		requestObject.add("INSTCITYCODE", "02000A");
		requestObject.add("FRCODE", "020297");
		requestObject.add("UPBUSITYPE", "BZ9000");
		requestObject.add("APPBUSITYPE", "TSW120");*/
		String interCode = requestObject.getInterInfo().getInterCode();
		//用于存储客户id与对象的映射关系
		Map<String, Object> objectMap = new HashMap<String, Object>();
		Map<String, Object> objectMapByCardNum = new HashMap<String, Object>();
		Map<String, Object> cardNumToId = new HashMap<String, Object>();
		//得到当前年月日
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		int currentMonth = cal.get(Calendar.MONTH) + 1;
		int currentDay = cal.get(Calendar.DAY_OF_MONTH);
		//接口编码校验
		if(null == interCode){
			result.setCode(ErrorCode.noRuleInterCode.getCode());
			result.setDesc(ErrorCode.noRuleInterCode.getDesc());
			return result;
		}
		//数据为空校验
		if(checkIsNull(requestObject, result, interCode))
			return result;
		//得到公共数据信息
		PublicData pd = publicDataHandle(requestObject);
		//核心地势编号
		String cityCode = "";
		try {
			cityCode = GroupDao.getInstance().queryValueByAppoint(pd.getInscode(), "CITYCODE");
			pd.setCoreCityCode(cityCode);
		} catch (Exception e1) {
			logger.error(e1);
			result.setResult(ErrorCode.getCityCodeError.getCode());
			result.setDesc(ErrorCode.getCityCodeError.getDesc());
			return result;
		}
		if(RuleInterface.ZD00001.equals(interCode)){
			//机构推广
			StOrgSpread org = new StOrgSpread();
			org.setIseffect(YesNo.yes.getCode());
			org.setIsopen(YesNo.yes.getCode());
			org.setOrgcode(pd.getLegalCode());
			try {
				String dots = pd.getInscode();
/*				Group group =  BaseGroupManager.getInstance().getGroup(dots);
				dots = group.getId();
				while(!GroupDao.getInstance().isFrFlag(group.getId())){
					dots += "," + group.getId();
					group = group.getParent();
				}*/
				List<StOrgSpreadVo> orgSpread = orgDao.querySpreadOrgByDots(dots);
				if(org.getOrgcode() == null || orgSpread == null || orgSpread.size() == 0){
					result.setResult(admitSplit);
					result.setCode(passCode);
					result.setDesc(orgSpreadNotOpen);
					return result;
				}
			} catch (Exception e) {
				result.setResult(ErrorCode.spreadError.getCode());
				result.setDesc(ErrorCode.spreadError.getDesc());
				return result;
			}
			//产品推广
			String upProductCode = requestObject.get(ZD00001.UPBUSITYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00001.UPBUSITYPE.toString())).replaceAll("'", "");
			String productCode = requestObject.get(ZD00001.APPBUSITYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00001.APPBUSITYPE.toString())).replaceAll("'", "");
			//测试用代码
			if(upProductCode == null || productCode == null){
//			if(productCode == null){
				result.setResult(admitSplit);
				result.setCode(passCode);
				result.setDesc(productSpreadNotOpen);
				return result;
			}
			StProductSpread product = new StProductSpread();
			product.setIseffect(YesNo.yes.getCode());
			product.setIsopen(YesNo.yes.getCode());
			product.setOrgcode(pd.getLegalCode());
			product.setProductcentercode(upProductCode);
			product.setProductcode(productCode);
			try {
				List<StProductSpreadVo> productList = productDao.queryListByModel(product, StProductSpreadVo.class);
				//不存在相应产品推广数据
				if(productList == null || productList.size() == 0){
					result.setResult(admitSplit);
					result.setCode(passCode);
					result.setDesc(productSpreadNotOpen);
				}
				else{
					StProductSpreadVo productVo = productList.get(productList.size() - 1);
					Object o = requestObject.get(ZD00001.REQLMT.toString());
					boolean check = true;
					//如果额度为空
					if(o.equals(isEmptyFlag))
						check = false;
					else{
						double amount = Double.valueOf(String.valueOf(o));
						//如果额度大于推广额度
						if(productVo.getLimitamount() != null && amount > productVo.getLimitamount().doubleValue())
							check = false;
					}
					if(check){
						result.setCode(passCode);
						result.setResult(admitPass);
					}
					else{
						result.setCode(passCode);
						result.setResult(admitSplit);
						result.setDesc(productAmountLimit);
					}
				}
			} catch (Exception e) {
				result.setResult(ErrorCode.spreadError.getCode());
				result.setDesc(ErrorCode.spreadError.getDesc());
			}
		}
		else if(RuleInterface.ZD00002.equals(interCode)){
			Admit admit = new Admit();
			//对象编码
			admit.setObjType(ObjectCode.getObjectCode(ObjectCode.admit, String.valueOf(requestObject.get(ZD00002.CHECKOBJ.toString()))));
			//客户编码
			admit.setCustCode((requestObject.get(ZD00002.CUSTID.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00002.CUSTID.toString()))));
			//客户名称
			admit.setCustName(requestObject.get(ZD00002.CNAME.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00002.CNAME.toString())));
			//产品编码
			admit.setProductCode(requestObject.get(ZD00002.APPBUSITYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00002.APPBUSITYPE.toString())));
			//上层业务编号
			admit.setSmallProduct(requestObject.get(ZD00002.UPBUSITYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00002.UPBUSITYPE.toString())));
			//申请金额
			admit.setApplyAmount(requestObject.get(ZD00002.REQLMT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00002.REQLMT.toString())));
			//授信期限
			admit.setApplyTerm(requestObject.get(ZD00002.LIMT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00002.LIMT.toString())));
			//婚姻状况
			admit.setMarrige(requestObject.get(ZD00002.MARRIGE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00002.MARRIGE.toString())));
			//家庭总资产
			//年龄
			admit.setAge(requestObject.get(ZD00002.AGE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00002.AGE.toString())));
			
//			admit.setFamilyAsset(requestObject.get(ZD00002.FAMASSET.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00002.FAMASSET.toString())));
			//家庭总负债
//			admit.setFamilyDebt(requestObject.get(ZD00002.FAMDEBT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00002.FAMDEBT.toString())));
			//性别
			admit.setGender(requestObject.get(ZD00002.SEX.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00002.SEX.toString())));
			objectMap.put(admit.getObjType(), admit);
			
			StResultObject stResult = stEngine.StAdmitWithObject(getDataSource(objectMap), pd);
			result = admitResultSwitch(result, stResult);
		}
		else if(RuleInterface.ZD00003.equals(interCode)){
			Admit admit = new Admit();
			//对象编码
			admit.setObjType(ObjectCode.getObjectCode(ObjectCode.admit, ObjectType.borrower.getCode()));
			//申请金额
			admit.setApplyAmount(requestObject.get(ZD00003.REQLMT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00003.REQLMT.toString())));
			//相关企业是否存在用信余额
			admit.setIsEtpHaveCredit((requestObject.get(ZD00003.RELALOAN.toString()).equals(isEmptyFlag) ? null : String.valueOf(ZD00003.RELALOAN.toString())));
			//外部失信或客户违法违纪、不良嗜好信息名单、黑名单
			Object tmp = requestObject.get(ZD00003.CUSTLIST.toString());
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> custList = (List<Map<String, Object>>) tmp;
				if(custList != null && custList.size() > 0){
					for(Map<String, Object> cust : custList){
						Admit ad;
						//对象编码
						String objectCode = String.valueOf(cust.get(ZD00003.custlist.CHECKOBJ.toString()));
						String objectId = String.valueOf(cust.get(ZD00003.custlist.CUSTID.toString()));
						if(objectCode.equals(ObjectType.borrower.getCode())){
							 ad = admit;
						}
						else ad = new Admit();
						//客户编码
						ad.setCustCode((requestObject.get(ZD00003.custlist.CUSTID.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00003.custlist.CUSTID.toString()))));
						//客户名称
						ad.setCustName(requestObject.get(ZD00003.custlist.CNAME.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00003.custlist.CNAME.toString())));
						//是否外部名单
						ad.setIsOutList(cust.get(ZD00003.custlist.INSXLIST.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00003.custlist.INSXLIST.toString())));
						ad.setObjType(objectCode.equals(isEmptyFlag) ? null : ObjectCode.getObjectCode(ObjectCode.admit, objectCode));
						//是否黑名单
						ad.setIsBlackList(cust.get(ZD00003.custlist.INBWLIST.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00003.custlist.INBWLIST.toString())));
						//家庭总资产
						ad.setFamilyAsset(cust.get(ZD00003.custlist.FAMASSET.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00003.custlist.FAMASSET.toString())));
						//家庭总负债
						ad.setFamilyDebt(cust.get(ZD00003.custlist.FAMDEBT.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00003.custlist.FAMDEBT.toString())));
						//近两年账户是否有过冻结
						ad.setIsFreeze(cust.get(ZD00003.custlist.IFFREEZE.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00003.custlist.IFFREEZE.toString())));
						objectMap.put(objectId, ad);
					}
				}
			}
			//不判断借款人家庭净资产
			admit.setFamilyAsset(null);
			admit.setFamilyDebt(null);
			//1、客户有贷款余额的贷款信息(机构、贷款产品)
			//2、表内未结清贷款信息
			//3、表内未结清贷款五级分类状态
			tmp = requestObject.get("BNLIST");
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> bnList = (List<Map<String, Object>>) tmp;
				if(bnList != null && bnList.size() > 0){
					for(Map<String, Object> cust : bnList){
						if(cust.get(ZD00003.bnlist.CUSTID.toString()).equals(isEmptyFlag)){
							continue;
						}
						String objectId = String.valueOf(cust.get(ZD00003.bnlist.CUSTID.toString()));
						Admit ad;
						if(objectId != null && objectMap.get(objectId) != null)
							ad = (Admit) objectMap.get(objectId);
						else ad = new Admit();
						//最差五级分类形态
						if(!cust.get(ZD00003.bnlist.FIVECLASS.toString()).equals(isEmptyFlag)){
							int fiveClass = Integer.valueOf(String.valueOf(cust.get(ZD00003.bnlist.FIVECLASS.toString())));
							if(ad.getWorstFiveClass() != null && Integer.valueOf(ad.getWorstFiveClass()) < fiveClass){
								ad.setWorstFiveClass(String.valueOf(fiveClass));
							}
						}
						//是否存在本金逾期、欠息
						ad.setIsOverdue(YesNo.yes.getsCode());
						objectMap.put(objectId, ad);
					}
				}
			}
			//表外未结清贷款信息
			//表外已结清贷款信息
			tmp = requestObject.get(ZD00003.BWLIST.toString());
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> bwList = (List<Map<String, Object>>) tmp;
				if(bwList != null && bwList.size() > 0){
					for(Map<String, Object> cust : bwList){
						if(cust.get(ZD00003.bwlist.OWNERID.toString()).equals(isEmptyFlag)){
							continue;
						}
						String objectId = String.valueOf(cust.get(ZD00003.bwlist.OWNERID.toString()));
						Admit ad;
						if(objectId != null && objectMap.get(objectId) != null)
							ad = (Admit) objectMap.get(objectId);
						else ad = new Admit();
						//是否存在表外不良
						if(cust.get(ZD00003.bwlist.CLOSEDATE.toString()).equals(isEmptyFlag)){
							ad.setIsUnsettledOutSheetBad(YesNo.yes.getsCode());
						}else if(ad.getIsUnsettledOutSheetBad() == null){
							ad.setIsUnsettledOutSheetBad(YesNo.no.getsCode());
						}
						//过去3年是否存在已结清表外不良
						if(!cust.get(ZD00003.bwlist.CLOSEDATE.toString()).equals(isEmptyFlag)){
							String closeDate = String.valueOf(cust.get(ZD00003.bwlist.CLOSEDATE.toString()));
							int before3Year = currentYear - 3;
							String before3Date = String.valueOf(before3Year) + (currentMonth < 10 ? "0" : "") +  String.valueOf(currentMonth) + String.valueOf(currentDay);
							if(Integer.valueOf(closeDate.replaceAll("-", "")) >= Integer.valueOf(before3Date)){
								ad.setIsSettledOutSheetBad(YesNo.yes.getsCode());
							}
							else ad.setIsSettledOutSheetBad(YesNo.no.getsCode());
						}
						objectMap.put(objectId, ad);
					}
				}
			}
			//展期(根据发起展期授权的日期进行判断)
			tmp = requestObject.get(ZD00003.EXPLIST.toString());
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> expList = (List<Map<String, Object>>) tmp;
				if(expList != null && expList.size() > 0){
					for(Map<String, Object> cust : expList){
						if(cust.get(ZD00003.explist.CUSTID.toString()).equals(isEmptyFlag)){
							continue;
						}
						String objectId = String.valueOf(cust.get(ZD00003.explist.CUSTID.toString()));
						Admit ad;
						if(objectId != null && objectMap.get(objectId) != null)
							ad = (Admit) objectMap.get(objectId);
						else ad = new Admit();
						//客户近12个月是否存在展期
						if(!cust.get(ZD00003.explist.LOANDATE.toString()).equals(isEmptyFlag)){
							String loanDate = String.valueOf(cust.get(ZD00003.explist.LOANDATE.toString()));
							int before1Year = currentYear - 1;
							String before1Date = String.valueOf(before1Year) + (currentMonth < 10 ? "0" : "") +  String.valueOf(currentMonth) + String.valueOf(currentDay);
							if(Integer.valueOf(loanDate.replaceAll("-", "")) >= Integer.valueOf(before1Date)){
								ad.setIsExend(YesNo.yes.getsCode());
							}
							else ad.setIsExend(YesNo.no.getsCode());
						}
						objectMap.put(objectId, ad);
					}
				}
			}
			//客户对外担保表内未结清贷款
			//客户对外担保表外未结清贷款
			tmp = requestObject.get(ZD00003.BNWLIST_DB.toString());
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> bnwList = (List<Map<String, Object>>) tmp;
				if(bnwList != null && bnwList.size() > 0){
					for(Map<String, Object> cust : bnwList){
						if(cust.get(ZD00003.bnwlist_db.CUSTID.toString()).equals(isEmptyFlag)){
							continue;
						}
						String objectId = String.valueOf(cust.get(ZD00003.bnwlist_db.CUSTID.toString()));
						Admit ad;
						if(objectId != null && objectMap.get(objectId) != null)
							ad = (Admit) objectMap.get(objectId);
						else ad = new Admit();
						//客户是否存在对外担保表内未结清贷款信息
						//客户是否存在对外担保表外未结清贷款信息
						if(!cust.get(ZD00003.bnwlist_db.ISOUTTABLELOAN.toString()).equals(isEmptyFlag)){
							if(cust.get(ZD00003.bnwlist_db.ISOUTTABLELOAN.toString()).equals(YesNo.yes.getsCode())){
								ad.setIsGuaranteOutSheetBad(YesNo.yes.getsCode());
								if(ad.getIsGuaranteInSheetBad()==null)
									ad.setIsGuaranteInSheetBad(YesNo.no.getsCode());
							}
							else{
								if(ad.getIsGuaranteOutSheetBad()==null)
									ad.setIsGuaranteOutSheetBad(YesNo.no.getsCode());
								ad.setIsGuaranteInSheetBad(YesNo.yes.getsCode());
							}
						}
						objectMap.put(objectId, ad);
					}
				}
			}
			
			StResultObject stResult = stEngine.StAdmitWithObject(getDataSource(objectMap), pd);
			result = admitResultSwitch(result, stResult);

		}
		else if(RuleInterface.ZD00004.equals(interCode)){
			//征信列表
			Object tmp = requestObject.get(ZD00004.ZXLIST.toString());
			String custna = "";
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> zxList = (List<Map<String, Object>>) tmp;
				if(zxList != null && zxList.size() > 0){
					for(Map<String, Object> cust : zxList){
						Admit admit = new Admit();
						//对象编码
						admit.setObjType(cust.get(ZD00004.zxlist.CHECKOBJ.toString()).equals(isEmptyFlag) ? null : ObjectCode.getObjectCode(ObjectCode.admit, String.valueOf(cust.get(ZD00004.zxlist.CHECKOBJ.toString()))));
						//客户编码
						admit.setCustCode((cust.get(ZD00004.zxlist.CUSTID.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.CUSTID.toString()))));
						//客户名称
						admit.setCustName(requestObject.get(ZD00004.zxlist.CNAME.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00004.zxlist.CNAME.toString())));
						//当前预期数
						admit.setCrOverdueCount(cust.get(ZD00004.zxlist.OVERDUETERM.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.OVERDUETERM.toString())));
						//他行当前最差五级分类形态
						admit.setCrOtherWorstFiveClass(cust.get(ZD00004.zxlist.THWROSTCLASS.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.THWROSTCLASS.toString())));
						//对外担保本金余额
						admit.setCrGuranteeAmount(cust.get(ZD00004.zxlist.DBSUMBALAMT.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.DBSUMBALAMT.toString())));
						//贷款法人机构数
						admit.setCrLoanOrgCount(cust.get(ZD00004.zxlist.FRNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.FRNUM.toString())));
						//未销户信用卡发卡机构数
						admit.setCrCreditCardOrgCount(cust.get(ZD00004.zxlist.NORMALCARDNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.NORMALCARDNUM.toString())));
						//征信近12个月本息逾期期数
						admit.setCrOverdueCountIn12M(cust.get(ZD00004.zxlist.ONEYOVERDUENUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.ONEYOVERDUENUM.toString())));
						//最近24个月本息最大逾期期数
						admit.setCrOverdueSerialIn36m(cust.get(ZD00004.zxlist.TOWYOVERDUETERM.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.TOWYOVERDUETERM.toString())));
						//近12个月查询次数
						admit.setCrQueryCountIn12M(cust.get(ZD00004.zxlist.ONEYCHECKNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.ONEYCHECKNUM.toString())));
						custna = admit.getCustName();
						objectMap.put(admit.getObjType(), admit);
					}
					StResultObject stResult = stEngine.StAdmitWithObject(getDataSource(objectMap), pd);
					result = admitResultSwitch(result, stResult);
				}
				else{
					result.setCode(passCode);
					result.setResult(admitPass);
				}
			}
			else{
				result.setCode(passCode);
				result.setResult(admitPass);
			}
			
		}
		else if(RuleInterface.ZD00005.equals(interCode)){
			//客户列表
			Object tmp = requestObject.get(ZD00005.CUSTLIST.toString());
			String custna = "";
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> custList = (List<Map<String, Object>>) tmp;
				if(custList != null && custList.size() > 0){
					for(Map<String, Object> cust : custList){
						Admit ad = new Admit();
						//对象编码
						ad.setObjType(cust.get(ZD00005.custlist.CHECKOBJ.toString()).equals(isEmptyFlag) ? null : ObjectCode.getObjectCode(ObjectCode.admit,String.valueOf(cust.get(ZD00005.custlist.CHECKOBJ.toString()))));
						//客户名称
						ad.setCustName(requestObject.get(ZD00005.custlist.CNAME.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00005.custlist.CNAME.toString())));
						//家庭总资产
						ad.setFamilyAsset(cust.get(ZD00005.custlist.FAMASSET.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00005.custlist.FAMASSET.toString())));
						//家庭总负债
						ad.setFamilyDebt(cust.get(ZD00005.custlist.FAMDEBT.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00005.custlist.FAMDEBT.toString())));
						custna = ad.getCustName();
						objectMap.put(ad.getObjType(), ad);
					}
				}
			
			}
			
			StResultObject stResult = stEngine.StAdmitWithObject(getDataSource(objectMap), pd);
			result = admitResultSwitch(result, stResult);
			
		}
		else if(RuleInterface.ZD00006.equals(interCode)){
			Admit admit = new Admit();
			String custna = "";
			//对象编码
			admit.setObjType(ObjectCode.getObjectCode(ObjectCode.admit, ObjectType.borrower.getCode()));
			//对外担保本金余额
			admit.setCrGuranteeAmount(requestObject.get(ZD00006.DBSUMBALAMT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00006.DBSUMBALAMT.toString())));
			//申请金额
			admit.setApplyAmount(requestObject.get(ZD00006.REQLMT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00003.REQLMT.toString())));
			//相关企业是否存在用信余额
			admit.setIsEtpHaveCredit((requestObject.get(ZD00006.RELALOAN.toString()).equals(isEmptyFlag) ? null : String.valueOf(ZD00003.RELALOAN.toString())));
			//外部失信或客户违法违纪、不良嗜好信息名单、黑名单
			Object tmp = requestObject.get(ZD00006.CUSTLIST.toString());
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> custList = (List<Map<String, Object>>) tmp;
				if(custList != null && custList.size() > 0){
					for(Map<String, Object> cust : custList){
						Admit ad;
						//对象编码
						String objectCode = String.valueOf(cust.get(ZD00006.custlist.CHECKOBJ.toString()));
						String objectId = String.valueOf(cust.get(ZD00006.custlist.CUSTID.toString()));
						String cardNum = String.valueOf(cust.get(ZD00006.custlist.CARDNUM.toString()));
						if((!"STEP_3".equals(requestObject.getCallStep()) && objectCode.equals(ObjectType.borrower.getCode())) || ("STEP_3".equals(requestObject.getCallStep()) && objectCode.equals(ObjectType.warrant.getCode()))){
							 ad = admit;
						}
						else ad = new Admit();
						//是否新增客户
						ad.setIsNewCust(requestObject.get(ZD00006.IFNEWCUST.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00006.IFNEWCUST.toString())));
						//客户编码
						ad.setCustCode((cust.get(ZD00006.custlist.CUSTID.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00006.custlist.CUSTID.toString()))));
						//客户名称
						ad.setCustName(cust.get(ZD00006.custlist.CNAME.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00006.custlist.CNAME.toString())));
						//是否外部名单
						ad.setIsOutList(cust.get(ZD00006.custlist.INSXLIST.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00006.custlist.INSXLIST.toString())));
						ad.setObjType(objectCode.equals(isEmptyFlag) ? null : ObjectCode.getObjectCode(ObjectCode.admit, objectCode));
						//是否黑名单
						ad.setIsBlackList(cust.get(ZD00006.custlist.INBWLIST.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00006.custlist.INBWLIST.toString())));
						//家庭总资产
						ad.setFamilyAsset(cust.get(ZD00006.custlist.FAMASSET.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00006.custlist.FAMASSET.toString())));
						//家庭总负债
						ad.setFamilyDebt(cust.get(ZD00006.custlist.FAMDEBT.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00006.custlist.FAMDEBT.toString())));
						//近两年账户是否有过冻结
						ad.setIsFreeze(cust.get(ZD00006.custlist.IFFREEZE.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00006.custlist.IFFREEZE.toString())));
						if(!isEmptyFlag.equals(objectId))
							objectMap.put(objectId, ad);
						else objectMapByCardNum.put(cardNum, ad);
					}
				}
			}
			//1、客户有贷款余额的贷款信息(机构、贷款产品)
			//2、表内未结清贷款信息
			//3、表内未结清贷款五级分类状态
			tmp = requestObject.get("BNLIST");
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> bnList = (List<Map<String, Object>>) tmp;
				if(bnList != null && bnList.size() > 0){
					for(Map<String, Object> cust : bnList){
						if(cust.get(ZD00006.bnlist.CUSTID.toString()).equals(isEmptyFlag)){
							continue;
						}
						String objectId = String.valueOf(cust.get(ZD00006.bnlist.CUSTID.toString()));
						Admit ad;
						if(objectId != null && objectMap.get(objectId) != null)
							ad = (Admit) objectMap.get(objectId);
						else ad = new Admit();
						//最差五级分类形态
						if(!cust.get(ZD00006.bnlist.FIVECLASS.toString()).equals(isEmptyFlag)){
							int fiveClass = Integer.valueOf(String.valueOf(cust.get(ZD00006.bnlist.FIVECLASS.toString())));
							if(ad.getWorstFiveClass() != null && Integer.valueOf(ad.getWorstFiveClass()) < fiveClass){
								ad.setWorstFiveClass(String.valueOf(fiveClass));
							}
							else if(ad.getWorstFiveClass() == null) ad.setWorstFiveClass(String.valueOf(fiveClass));
						}
						//是否存在本金逾期、欠息
						if(((isEmptyFlag.equals(cust.get(ZD00006.bnlist.DELAYAMTDAYS.toString())) || "0".equals(cust.get(ZD00006.bnlist.DELAYAMTDAYS.toString())))
						  && (isEmptyFlag.equals(cust.get(ZD00006.bnlist.DELAYINTERESTDAYS.toString())) || "0".equals(cust.get(ZD00006.bnlist.DELAYINTERESTDAYS.toString())))
						  && !YesNo.yes.getsCode().equals(ad.getIsOverdue())) || isEmptyFlag.equals(cust.get(ZD00006.bnlist.BALAMT.toString()))
						  || Double.valueOf(String.valueOf(cust.get(ZD00006.bnlist.BALAMT.toString()))) <= 0){
							ad.setIsOverdue(YesNo.no.getsCode());
						}
						else{
							ad.setIsOverdue(YesNo.yes.getsCode());
						}
						objectMap.put(objectId, ad);
					}
				}
			}
			//表外未结清贷款信息
			//表外已结清贷款信息
			tmp = requestObject.get(ZD00006.BWLIST.toString());
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> bwList = (List<Map<String, Object>>) tmp;
				if(bwList != null && bwList.size() > 0){
					for(Map<String, Object> cust : bwList){
						if(cust.get(ZD00006.bwlist.OWNERID.toString()).equals(isEmptyFlag)){
							continue;
						}
						String objectId = String.valueOf(cust.get(ZD00006.bwlist.OWNERID.toString()));
						Admit ad;
						if(objectId != null && objectMap.get(objectId) != null)
							ad = (Admit) objectMap.get(objectId);
						else ad = new Admit();
						//是否存在行内未结清表外不良
						Object loanBanlance = cust.get(ZD00006.bwlist.LOANBALANCE.toString());
						if((isEmptyFlag.equals(loanBanlance) || Double.valueOf(String.valueOf(loanBanlance)) == 0)
								&& !YesNo.yes.getsCode().equals(ad.getIsUnsettledOutSheetBad())){
							ad.setIsUnsettledOutSheetBad(YesNo.no.getsCode());
						}else{
							ad.setIsUnsettledOutSheetBad(YesNo.yes.getsCode());
						}
						//过去3年是否存在已结清表外不良
						if(!cust.get(ZD00006.bwlist.CLOSEDATE.toString()).equals(isEmptyFlag)){
							String closeDate = String.valueOf(cust.get(ZD00006.bwlist.CLOSEDATE.toString()));
							int before3Year = currentYear - 3;
							String before3Date = String.valueOf(before3Year) + (currentMonth < 10 ? "0" : "") +  String.valueOf(currentMonth) 
												 + (currentDay < 10 ? "0" : "") + String.valueOf(currentDay);
							if(Integer.valueOf(closeDate.replaceAll("-", "")) >= Integer.valueOf(before3Date)){
								ad.setIsSettledOutSheetBad(YesNo.yes.getsCode());
							}
							else ad.setIsSettledOutSheetBad(YesNo.no.getsCode());
						}
						objectMap.put(objectId, ad);
					}
				}
			}
			//展期(根据发起展期授权的日期进行判断)
			tmp = requestObject.get(ZD00006.EXPLIST.toString());
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> expList = (List<Map<String, Object>>) tmp;
				if(expList != null && expList.size() > 0){
					for(Map<String, Object> cust : expList){
						if(cust.get(ZD00006.explist.CUSTID.toString()).equals(isEmptyFlag)){
							continue;
						}
						String objectId = String.valueOf(cust.get(ZD00006.explist.CUSTID.toString()));
						Admit ad;
						if(objectId != null && objectMap.get(objectId) != null)
							ad = (Admit) objectMap.get(objectId);
						else ad = new Admit();
						//客户近12个月是否存在展期
						if(!cust.get(ZD00006.explist.LOANDATE.toString()).equals(isEmptyFlag)){
							String loanDate = String.valueOf(cust.get(ZD00006.explist.LOANDATE.toString()));
							int before1Year = currentYear - 1;
							String before1Date = String.valueOf(before1Year) + (currentMonth < 10 ? "0" : "") +  String.valueOf(currentMonth) 
												 + (currentDay < 10 ? "0" : "") + String.valueOf(currentDay);
							if(Integer.valueOf(loanDate.replaceAll("-", "")) >= Integer.valueOf(before1Date)){
								ad.setIsExend(YesNo.yes.getsCode());
							}
							else ad.setIsExend(YesNo.no.getsCode());
						}
						objectMap.put(objectId, ad);
					}
				}
			}
			//客户对外担保表内未结清贷款
			//客户对外担保表外未结清贷款
			//客户对外担保表内未结清贷款
			//客户对外担保表外未结清贷款
			tmp = requestObject.get(ZD00003.BNWLIST_DB.toString());
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> bnwList = (List<Map<String, Object>>) tmp;
				if(bnwList != null && bnwList.size() > 0){
					for(Map<String, Object> cust : bnwList){
						if(cust.get(ZD00003.bnwlist_db.CUSTID.toString()).equals(isEmptyFlag)){
							continue;
						}
						String objectId = String.valueOf(cust.get(ZD00003.bnwlist_db.CUSTID.toString()));
						Admit ad;
						if(objectId != null && objectMap.get(objectId) != null)
							ad = (Admit) objectMap.get(objectId);
						else ad = new Admit();
						//客户是否存在对外担保表内未结清贷款信息
						//客户是否存在对外担保表外未结清贷款信息
						if(!cust.get(ZD00003.bnwlist_db.ISOUTTABLELOAN.toString()).equals(isEmptyFlag)){
							if(cust.get(ZD00003.bnwlist_db.ISOUTTABLELOAN.toString()).equals(YesNo.yes.getsCode())){
								if((isEmptyFlag.equals(cust.get(ZD00003.bnwlist_db.DELAYAMTDAYS.toString())) || "0".equals(cust.get(ZD00003.bnwlist_db.DELAYAMTDAYS.toString())))
										  && (isEmptyFlag.equals(cust.get(ZD00003.bnwlist_db.DELAYINTERESTDAYS.toString())) || "0".equals(cust.get(ZD00003.bnwlist_db.DELAYINTERESTDAYS.toString())))
										  && !YesNo.yes.getsCode().equals(ad.getIsGuaranteOutSheetBad())){
									ad.setIsGuaranteOutSheetBad(YesNo.no.getsCode());
								}else{
									ad.setIsGuaranteOutSheetBad(YesNo.yes.getsCode());
								}
							}
							else{
								if((isEmptyFlag.equals(cust.get(ZD00003.bnwlist_db.DELAYAMTDAYS.toString())) || "0".equals(cust.get(ZD00003.bnwlist_db.DELAYAMTDAYS.toString())))
										  && (isEmptyFlag.equals(cust.get(ZD00003.bnwlist_db.DELAYINTERESTDAYS.toString())) || "0".equals(cust.get(ZD00003.bnwlist_db.DELAYINTERESTDAYS.toString())))
										  && !YesNo.yes.getsCode().equals(ad.getIsGuaranteInSheetBad())){
									ad.setIsGuaranteInSheetBad(YesNo.no.getsCode());
								}
								else{
									ad.setIsGuaranteInSheetBad(YesNo.yes.getsCode());
								}
								
							}
						}
						objectMap.put(objectId, ad);
					}
				}
			}
			StResultObject stResult = stEngine.StAdmitWithObject(getDataSource(objectMap), pd);
			result = admitResultSwitch(result, stResult);

		}
		else if(RuleInterface.ZD00007.equals(interCode)){
			FirstTrial ft = new FirstTrial();
			//客户编号
			ft.setCustCode(requestObject.get(ZD00007.CUSTID.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00007.CUSTID.toString())));
			//客户名称
			ft.setCustName(requestObject.get(ZD00007.CNAME.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00007.CNAME.toString())));
			//对象编码
			ft.setObjType(requestObject.get(ZD00007.CHECKOBJ.toString()).equals(isEmptyFlag) ? null : ObjectCode.getObjectCode(ObjectCode.firstTrial, String.valueOf(requestObject.get(ZD00007.CHECKOBJ.toString()))));
			//评级结果
			ft.setRate(requestObject.get(ZD00007.NPSCORE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00007.NPSCORE.toString())));
			//是否新增客户
			ft.setIsNewCust(requestObject.get(ZD00007.IFNEWCUST.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00007.IFNEWCUST.toString())));
			//授信金额
			ft.setCreditAmount(requestObject.get(ZD00007.CSEQLMT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00007.CSEQLMT.toString())));
			//评级模型类型
			ft.setRrModel(requestObject.get(ZD00007.RRMODEL.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00007.RRMODEL.toString())));
			//押品类型
			ft.setKeyType(requestObject.get(ZD00007.KEYTYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00007.KEYTYPE.toString())));
			String industryCode = requestObject.get(ZD00007.INDUSTRY.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00007.INDUSTRY.toString()));
			//是否限控行业
			try {
				ft.setIsLimitIndusty(getIsLimitIndustry(industryCode));
			} catch (Exception e) {
				result.setCode(ErrorCode.limitIndustryError.getCode());
				result.setDesc(ErrorCode.limitIndustryError.getDesc());
			}
			//担保方式
			ft.setGuaranteeType(requestObject.get(ZD00007.MAINASSURE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00007.MAINASSURE.toString())));
/*			//让所有的评级都通过
			ft.setRate("1");*/
			objectMap.put(ft.getObjType(), ft);
			
			StResultObject stResult = stEngine.StFirstTrialWithObject(getDataSource(objectMap), pd);
			result = admitResultSwitch(result, stResult);
		}
		else if(RuleInterface.ZD00008.equals(interCode)){
			LastTrial lt = new LastTrial();
			//客户编号
			lt.setCustCode(requestObject.get(ZD00008.CUSTID.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00008.CUSTID.toString())));
			//客户名称
			lt.setCustName(requestObject.get(ZD00008.CNAME.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00007.CNAME.toString())));
			//对象编码
			lt.setObjType(requestObject.get(ZD00008.CHECKOBJ.toString()).equals(isEmptyFlag) ? null : ObjectCode.getObjectCode(ObjectCode.lastTrial, String.valueOf(requestObject.get(ZD00008.CHECKOBJ.toString()))));
			//十项否决
			lt.setIsTenDeny(requestObject.get(ZD00008.TENVOTED.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00008.TENVOTED.toString())));
			objectMap.put(lt.getObjType(), lt);
			
			StResultObject stResult = stEngine.StLastTrialWithObject(getDataSource(objectMap), pd);
			result = admitResultSwitch(result, stResult);
		}
		else if(RuleInterface.ZD00009.equals(interCode)){
			LastTrial lt = new LastTrial();
			//客户编号
			lt.setCustCode(requestObject.get(ZD00008.CUSTID.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00008.CUSTID.toString())));
			//对象编码
			lt.setObjType(requestObject.get(ZD00009.CHECKOBJ.toString()).equals(isEmptyFlag) ? null : ObjectCode.getObjectCode(ObjectCode.lastTrial, String.valueOf(requestObject.get(ZD00009.CHECKOBJ.toString()))));
			//客户名称
			lt.setCustName(requestObject.get(ZD00009.CNAME.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00009.CNAME.toString())));
			//评级结果
			lt.setRate(requestObject.get(ZD00009.NPSCORE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00009.NPSCORE.toString())));
			String industryCode = requestObject.get(ZD00009.INDUSTRY.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00009.INDUSTRY.toString()));
			//是否限控行业
			try {
				lt.setIsLimitIndusty(getIsLimitIndustry(industryCode));
			} catch (Exception e) {
				result.setCode(ErrorCode.limitIndustryError.getCode());
				result.setDesc(ErrorCode.limitIndustryError.getDesc());
			}
			//担保方式
			String guarantType = requestObject.get(ZD00009.MAINASSURE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00009.MAINASSURE.toString()));
			//判断保证和信用
			if(guarantType.equals(Credit)) {
				lt.setCollateralRiskLevel(Lcredit);
			}
			else if(guarantType.equals(guarantee)){
				lt.setCollateralRiskLevel(Lguar);
			}
			else{
				//抵质押风险等级
				String guarType = requestObject.get(ZD00009.KEYTYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00009.KEYTYPE.toString()));
				if(guarType != null && guarType != "")
					guarType = guarType.trim();
				//判断低风险抵质押
				List<ParamPopupBean> lowGuarList = SmTools.getParamsDisplay(lowGuarDict,null,null);
				for(ParamPopupBean pp : lowGuarList){
					if(pp.getCode().equals(guarType)){
						lt.setCollateralRiskLevel(LlowGuar);
						break;
					}
				}
				//判断中等风险抵质押
				if(lt.getCollateralRiskLevel() == null){
					List<ParamPopupBean> mediumGuarList = SmTools.getParamsDisplay(mediumGuarDict,null,null);
					for(ParamPopupBean pp : mediumGuarList){
						if(pp.getCode().equals(guarType)){
							lt.setCollateralRiskLevel(LmediumGuar);
							break;
						}
					}
					
				}
				//判断高风险抵质押
				if(lt.getCollateralRiskLevel() == null){
					List<ParamPopupBean> highGuarList = SmTools.getParamsDisplay(highGuarDict,null,null);
					for(ParamPopupBean pp : highGuarList){
						if(pp.getCode().equals(guarType)){
							lt.setCollateralRiskLevel(LhighGuar);
							break;
						}
					}
					
				}
			}
			//判断保证和信用
			/*if(lt.getCollateralRiskLevel() == null){
				if(guarType.equals(guarCode))
					lt.setCollateralRiskLevel(Lguar);
				else lt.setCollateralRiskLevel(Lcredit);
			}*/
			objectMap.put(lt.getObjType(), lt);
			StResultObject stResult = stEngine.StLastTrialWithObject(getDataSource(objectMap), pd);
			result = admitResultSwitch(result, stResult);
			
		}
		else if(RuleInterface.LMT001.equals(interCode)){
			LimitAmount la = new LimitAmount();
			//对象编码
			la.setObjType(ObjectCode.getObjectCode(ObjectCode.limitAmount, ObjectType.borrower.getCode()));
			//申请授信额度
			String applyLimit = requestObject.get(LMT001.REQLMT.toString()).equals(isEmptyFlag) ? "0" : String.valueOf(requestObject.get(LMT001.REQLMT.toString()));
			String applyAmount = requestObject.get(LMT001.USEUAMT.toString()).equals(isEmptyFlag) ? "0" : String.valueOf(requestObject.get(LMT001.USEUAMT.toString()));
			
// 			la.setApplyAmount(String.valueOf((Double.valueOf(applyLimit) + Double.valueOf(applyAmount))));
 			la.setApplyAmount(String.valueOf((Double.valueOf(applyLimit))));
			//自有资金投入
			la.setOwnFundInvest(requestObject.get(LMT001.SELFCAPITALRATE.toString()).equals(isEmptyFlag) ? "0" : String.valueOf(requestObject.get(LMT001.SELFCAPITALRATE.toString())));
			//总房价
			la.setHousePrice(requestObject.get(LMT001.TOTALCAPITALREQ.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.TOTALCAPITALREQ.toString())));
			//首付比例
			la.setPaymentRatio(requestObject.get(LMT001.FIRSTPAYRATE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.FIRSTPAYRATE.toString())));
			if(la.getPaymentRatio() != null){
				la.setPaymentRatio(String.valueOf(Double.valueOf(la.getPaymentRatio()) / 100));
			}
			//实际资金需求额度
			la.setNeedAmount(requestObject.get(LMT001.TOTALCAPITALREQ.toString()).equals(isEmptyFlag) ? "0" : String.valueOf(requestObject.get(LMT001.TOTALCAPITALREQ.toString())));
			la.setNeedAmount(String.valueOf(Double.valueOf(la.getNeedAmount()) - Double.valueOf(la.getOwnFundInvest())));
			//业务品种（上层业务种类）
			la.setSmallProduct(requestObject.get(LMT001.BUSITYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.BUSITYPE.toString())));
			//特色产品
			la.setProductCode(requestObject.get(LMT001.APPBUSITYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.APPBUSITYPE.toString())));
			//内部评级结果
			la.setGrade(requestObject.get(LMT001.NPSCORE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.NPSCORE.toString())));
			//贷款期限
			la.setLoanTimeLimit(requestObject.get(LMT001.LIMIT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.LIMIT.toString())));
			//担保方式
			la.setGuaranteeType(requestObject.get(LMT001.MAINASSURE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.MAINASSURE.toString())));
			//评分卡最高额度
			la.setRateMaxAmount(requestObject.get(LMT001.RATEHIGHESTAMOUNT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.RATEHIGHESTAMOUNT.toString())));
			//调整评分卡最高额度
			if(la.getRateMaxAmount() == null || "0".equals(la.getRateMaxAmount()))
				la.setRateMaxAmount(String.valueOf(Integer.MAX_VALUE));
			//与评级不相关家庭净资产系数
			//家庭年净收入系数
			//与评级相关家庭净资产系数
			//抵质押系数
			//产品最高限额
			//扩张系数
			try {
				StProductHighestAmount amount = new StProductHighestAmount();
				String productCode = requestObject.get(LMT001.APPBUSITYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.APPBUSITYPE.toString()));
				amount.setProductcode(productCode);
				amount.setProductmiddlecode(la.getSmallProduct());
				amount.setOrgcode(pd.getLegalCode());
				amount.setIseffect(YesNo.yes.getCode());
				//产品最高限额
				List<StProductHighestAmountVo> productAmountList = productAmountDao.queryListByModel(amount, StProductHighestAmountVo.class);
				if(productAmountList.size() == 0 || productAmountList.get(productAmountList.size() - 1).getLimitamount() == null)
					la.setProductMaxAmount(String.valueOf(Integer.MAX_VALUE));
				else la.setProductMaxAmount(productAmountList.get(productAmountList.size() - 1).getLimitamount().toString());
				
				
				String guaranteeType = GuaranteeType.getParmGuaranteeType(la.getGuaranteeType());
				List<StLimitamountParmVo> unRate = limitAmountDao.queryUnRate();
				List<StLimitamountParmVo> familyIncome = limitAmountDao.queryfamilyIncomeRate(la.getGrade(), guaranteeType);
				List<StLimitamountParmVo> rate = limitAmountDao.queryfamilyRate(la.getGrade(), guaranteeType);
				List<StLimitamountParmVo> coll = limitAmountDao.queryCollRate(la.getGrade(), guaranteeType);
				List<StLimitamountParmVo> expand = limitAmountDao.queryExpand(guaranteeType);
				if(unRate.size() == 0 && familyIncome.size() == 0 && rate.size() == 0 && coll.size() == 0 && expand.size() == 0){
					result.setCode(ErrorCode.notHaveAmountParm.getCode());
					result.setDesc(ErrorCode.notHaveAmountParm.getDesc());
					return result;
				}
				if(unRate.size() > 0)
					la.setFamilyNetAssetRadio(unRate.get(unRate.size() - 1).getUnratefamilynetassetratio().toString());
				if(familyIncome.size() > 0)
					la.setFamliyNetIncomeRatio(familyIncome.get(familyIncome.size() - 1).getFamilynetinratio().toString());
				if(rate.size() > 0)
					la.setRfamilyNetAssetRadio(rate.get(rate.size() - 1).getRateralatefamilynetassetratio().toString());
				if(coll.size() > 0)
					la.setCollateralRatio(coll.get(coll.size() - 1).getCollateralratio().toString());
				if(expand.size() > 0)
					la.setExpandRatio(expand.get(expand.size() - 1).getExpandratio().toString());
					
			} catch (Exception e) {
				result.setCode(ErrorCode.amountQueryError.getCode());
				result.setDesc(ErrorCode.amountQueryError.getDesc());
				return result;
			}
			//家庭年收入
			la.setFamilyIncome(requestObject.get(LMT001.FAMANNUINCOME.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.FAMANNUINCOME.toString())));
			//家庭年支出
			la.setFamilyExpend(requestObject.get(LMT001.FAMANNUINOUT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.FAMANNUINOUT.toString())));
			//家庭总资产
			la.setFamilyAsset(requestObject.get(LMT001.FAMASSET.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.FAMASSET.toString())));
			//家庭总负债
			la.setFamilyDebt(requestObject.get(LMT001.FAMDEBT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.FAMDEBT.toString())));
			//推荐额度
			la.setRecommendAmount(requestObject.get(LMT001.CPREQLMT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.CPREQLMT.toString())));
			//已占用额度
			la.setOccupyAmount(requestObject.get(LMT001.USEUAMT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.USEUAMT.toString())));
			//抵质押额度
			la.setCollateralAmount(requestObject.get(LMT001.MONEY.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.MONEY.toString())));
			//是否二手房
			la.setIsSecondhandHouse(requestObject.get(LMT001.IFUSEDHOUSE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.IFUSEDHOUSE.toString())));
			//拟购房产类别
			la.setHouseType(requestObject.get(LMT001.HOUSETYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.HOUSETYPE.toString())));
			//是否取得产权证
			la.setIsGetCertificate(requestObject.get(LMT001.IFFCZ.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.IFFCZ.toString())));
			//拟购房屋建筑面积
			la.setHouseArea(requestObject.get(LMT001.HOUSEBUILDAREA.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.HOUSEBUILDAREA.toString())));
			//拟购房屋套数
			la.setHouseCount(requestObject.get(LMT001.HOUSENUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.HOUSENUM.toString())));
			//其他银行负债
			la.setOtherBankDebt(requestObject.get(LMT001.FZMONEY.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(LMT001.FZMONEY.toString())));
			
/*			//查询核定额度
			StTmpData t = new StTmpData();
			t.setBusinesscode(pd.getBusinesscode());
			t.setDatakey(checkLimit);
			t.setUsemodel(RuleClass.limitAmount.getCode());
			try {
				List<StTmpDataVo> tmpList = baseDao.queryListByModel(t, StTmpDataVo.class);
				if(tmpList.size() > 0){
					result.setCode(ErrorCode.checkLimitAddError.getCode());
					result.setDesc(ErrorCode.checkLimitAddError.getDesc());
					return result;
				}
				la.setCheckLimit(tmpList.get(tmpList.size() - 1).getDatavalue());
			} catch (Exception e) {
				logger.error(e);
				result.setCode(ErrorCode.checkLimitAddError.getCode());
				result.setDesc(ErrorCode.checkLimitAddError.getDesc());
				return result;
			}*/
			objectMap.put(la.getObjType(), la);
			
			StResultObject stResult = stEngine.StCalculateLimtAmoutWithObject(getDataSource(objectMap), pd);
			result.setCode(stResult.getCode());
			result.setDesc(stResult.getSingleRuleDescription());
//			result.setCseqLmt(applyAmount == null ? Double.valueOf(stResult.getResult()) : Double.valueOf(stResult.getResult()) - Double.valueOf(applyAmount));
			
			result.setCseqLmt(applyAmount == null ? Double.valueOf(stResult.getResult()) : Double.valueOf(stResult.getResult()));
			Double d = result.getCseqLmt();
			if(d < 1000)
				d = 0d;
			else{
				String s = String.valueOf(d.intValue());
				s = s.substring(0,s.length() - 3) + "000";
				d = Double.valueOf(s);
			}
			result.setCseqLmt(d);
			if(result.getCseqLmt() < 0)
				result.setCseqLmt(0d);
		}
		else if(RuleInterface.RR002.equals(interCode)){
			Rate rateSurvey = new Rate();
			//对象编码,调查信息
			rateSurvey.setObjType(ObjectCode.getObjectCode(ObjectCode.rate, ObjectCode.rateSurvey));
			//是否有固定工作
			rateSurvey.setIfFixedWork(requestObject.get(RR001.IFFIXEDWORK.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.IFFIXEDWORK.toString())));
			//客户编号 
			rateSurvey.setCustcode(requestObject.get(RR001.CUSTID.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.CUSTID.toString())));
			rateSurvey.setSmallProduct(requestObject.get(RR001.BUSITYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.BUSITYPE.toString())));
			//客户名称
			rateSurvey.setCustname(requestObject.get(RR001.CNAME.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.CNAME.toString())));
			//证件类型
			rateSurvey.setIdtype(requestObject.get(RR001.CARDTYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.CARDTYPE.toString())));
			//证件号码
			rateSurvey.setIdnum(requestObject.get(RR001.PERCARDNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.PERCARDNUM.toString())));
			//客户类型
			rateSurvey.setCustType(requestObject.get(RR001.CUSTTYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.CUSTTYPE.toString())));
			//申请授信金额
			Object applyAmount = requestObject.get(RR001.APPREQLMT.toString());
			Object effectAmount = requestObject.get(RR001.USEUAMT.toString());
			if(applyAmount.equals(isEmptyFlag) && !effectAmount.equals(isEmptyFlag))
				rateSurvey.setApplyAmount(String.valueOf(effectAmount));
			else if(!applyAmount.equals(isEmptyFlag) && effectAmount.equals(isEmptyFlag))
				rateSurvey.setApplyAmount(String.valueOf(applyAmount));
			else if(!applyAmount.equals(isEmptyFlag) && !effectAmount.equals(isEmptyFlag)){
				rateSurvey.setApplyAmount(String.valueOf(Double.valueOf(String.valueOf(applyAmount)) + Double.valueOf(String.valueOf(effectAmount))));
			}
			//打分卡用申请金额
			rateSurvey.setApplyLimit(rateSurvey.getApplyAmount());
			//规则运算用申请金额
			rateSurvey.setApplyMoney(String.valueOf(applyAmount));
			//行业
			String industry = requestObject.get(RR001.INDUSTRY.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.INDUSTRY.toString()));
			if(industry != null && industry.length() == 5){
				//个人运输
				boolean check = false;
				for(String str : RateIndustry.personalTrans.getIndustry()){
					if(industry.matches("^" + str + ".*")){
						check = true;
						break;
					}
				}
				//种植蔬菜
				if(industry.equals(RateIndustry.plantVeg.getIndustry()[0]))
					rateSurvey.setIndustry(RateIndustry.plantVeg.getCode());
				//种植其他
				else if(industry.matches("^" + RateIndustry.plantOther.getIndustry()[0] + ".*") && !industry.equals(RateIndustry.plantVeg.getIndustry()[0]))
					rateSurvey.setIndustry(RateIndustry.plantOther.getCode());
				//养猪
				else if(industry.matches("^" + RateIndustry.breadPig.getIndustry()[0] + ".*"))
					rateSurvey.setIndustry(RateIndustry.breadPig.getCode());
				//养殖其他
				else if(industry.matches("^" + RateIndustry.breadOther.getIndustry()[0] + ".*") && !industry.equals(RateIndustry.breadPig.getIndustry()[0]))
					rateSurvey.setIndustry(RateIndustry.breadOther.getCode());
				//个人批发零售
				else if(industry.matches("^" + RateIndustry.personalRetail.getIndustry()[0] + ".*"))
					rateSurvey.setIndustry(RateIndustry.personalRetail.getCode());
				//个人运输
				else if(check)
					rateSurvey.setIndustry(RateIndustry.personalTrans.getCode());
				//个人制造
				else if(industry.matches("^" + RateIndustry.personalMake.getIndustry()[0] + ".*"))
					rateSurvey.setIndustry(RateIndustry.personalMake.getCode());
				//个人餐饮
				else if(industry.matches("^" + RateIndustry.personalCaterer.getIndustry()[0] + ".*"))
					rateSurvey.setIndustry(RateIndustry.personalCaterer.getCode());
				else rateSurvey.setIndustry(RateIndustry.personalOther.getCode());
				
			}
			//产品编码
			rateSurvey.setProductCode(requestObject.get(RR001.APPBUSITYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.APPBUSITYPE.toString())));
			objectMap.put(rateSurvey.getObjType(), rateSurvey);
			List<Object> objList = getDataSource(objectMap);
			StResultObject stResult = stEngine.StRateWithObjectNotRecordLog(objList, pd);
			if(stResult.getSingleRuleDescription() == null){
				result.setCode(stResult.getCode());
				result.setRrModel("NODFK");
			}
			else{
				result.setCode(stResult.getCode());
				result.setRrModel(stResult.getSingleRuleDescription());
				result.setDesc(stResult.getSingleRuleDescription());
			}
			return result;
			
		}
		else if(RuleInterface.RR001.equals(interCode)){
			Rate rateSurvey = new Rate();
			//对象编码,调查信息
			rateSurvey.setObjType(ObjectCode.getObjectCode(ObjectCode.rate, ObjectCode.rateSurvey));
			//是否有固定工作
			rateSurvey.setIfFixedWork(requestObject.get(RR001.IFFIXEDWORK.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.IFFIXEDWORK.toString())));
			//评级模型类型
			pd.setRateCardCode(requestObject.get(RR001.RRMODEL.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.RRMODEL.toString())));
			//客户编号 
			rateSurvey.setCustcode(requestObject.get(RR001.CUSTID.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.CUSTID.toString())));
			rateSurvey.setSmallProduct(requestObject.get(RR001.BUSITYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.BUSITYPE.toString())));
			//客户名称
			rateSurvey.setCustname(requestObject.get(RR001.CNAME.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.CNAME.toString())));
			//证件类型
			rateSurvey.setIdtype(requestObject.get(RR001.CARDTYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.CARDTYPE.toString())));
			//证件号码
			rateSurvey.setIdnum(requestObject.get(RR001.PERCARDNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.PERCARDNUM.toString())));
			//客户类型
			rateSurvey.setCustType(requestObject.get(RR001.CUSTTYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.CUSTTYPE.toString())));
			//营运车辆数量
			rateSurvey.setCarCount(requestObject.get(RR001.CARNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.CARNUM.toString())));
			//家庭总负债
				//家庭总负债
			String familyDebt = requestObject.get(RR001.FAMDEBT.toString()).equals(isEmptyFlag) ? "0" : String.valueOf(requestObject.get(RR001.FAMDEBT.toString()));
				//我行贷款余额
			String ourBankLoan = requestObject.get(RR001.BHLOANBAL.toString()).equals(isEmptyFlag) ? "0" : String.valueOf(requestObject.get(RR001.BHLOANBAL.toString()));
				//他行贷款余额
			String otherBankLoan = requestObject.get(RR001.THLOANBAL.toString()).equals(isEmptyFlag) ? "0" : String.valueOf(requestObject.get(RR001.THLOANBAL.toString()));
				//贷记卡已用额度
			String cardUseLimit = requestObject.get(RR001.DEBITCARDUSEDAMT.toString()).equals(isEmptyFlag) ? "0" : String.valueOf(requestObject.get(RR001.DEBITCARDUSEDAMT.toString()));
				//准贷记卡透支余额
			String cardBeyondLimit = requestObject.get(RR001.ZDEBITCARDUSEDAMT.toString()).equals(isEmptyFlag) ? "0" : String.valueOf(requestObject.get(RR001.ZDEBITCARDUSEDAMT.toString()));
			Double familyDebt1 = (Double.valueOf(ourBankLoan) + Double.valueOf(otherBankLoan) + Double.valueOf(cardUseLimit) + Double.valueOf(cardBeyondLimit));
			if(Double.valueOf(familyDebt) < familyDebt1){
				rateSurvey.setFamilyDebt(String.valueOf(familyDebt1));
			}
			else rateSurvey.setFamilyDebt(familyDebt);
			//或有负债
			String familyOutDebt = requestObject.get(RR001.DBSUMBALAMT1.toString()).equals(isEmptyFlag) ? "0" : String.valueOf(requestObject.get(RR001.DBSUMBALAMT1.toString()));
			String creditOutDebt = requestObject.get(RR001.DBSUMBALAMT2.toString()).equals(isEmptyFlag) ? "0" : String.valueOf(requestObject.get(RR001.DBSUMBALAMT2.toString()));
			rateSurvey.setFamilyPossibleDebt(String.valueOf(Double.valueOf(familyOutDebt) + Double.valueOf(creditOutDebt)));
			//家庭总资产
			rateSurvey.setFamilyAsset(requestObject.get(RR001.FAMASSET.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.FAMASSET.toString())));
			//本单位工作起始年份
			Object workYear = requestObject.get(RR001.EMPLOYERTIME.toString());
			if(!workYear.equals(isEmptyFlag)){
				rateSurvey.setWorkYear(String.valueOf(workYear));
			}
			//申请授信金额
			Object applyAmount = requestObject.get(RR001.APPREQLMT.toString());
			Object effectAmount = requestObject.get(RR001.USEUAMT.toString());
			if(applyAmount.equals(isEmptyFlag) && !effectAmount.equals(isEmptyFlag))
				rateSurvey.setApplyAmount(String.valueOf(effectAmount));
			else if(!applyAmount.equals(isEmptyFlag) && effectAmount.equals(isEmptyFlag))
				rateSurvey.setApplyAmount(String.valueOf(applyAmount));
			else if(!applyAmount.equals(isEmptyFlag) && !effectAmount.equals(isEmptyFlag)){
				rateSurvey.setApplyAmount(String.valueOf(Double.valueOf(String.valueOf(applyAmount)) + Double.valueOf(String.valueOf(effectAmount))));
			}
			//打分卡用申请金额
			rateSurvey.setApplyLimit(rateSurvey.getApplyAmount());
			//规则运算用申请金额
			rateSurvey.setApplyMoney(String.valueOf(applyAmount));
			//行业
			String industry = requestObject.get(RR001.INDUSTRY.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.INDUSTRY.toString()));
			if(industry != null && industry.length() == 5){
				//个人运输
				boolean check = false;
				for(String str : RateIndustry.personalTrans.getIndustry()){
					if(industry.matches("^" + str + ".*")){
						check = true;
						break;
					}
				}
				//种植蔬菜
				if(industry.equals(RateIndustry.plantVeg.getIndustry()[0]))
					rateSurvey.setIndustry(RateIndustry.plantVeg.getCode());
				//种植其他
				else if(industry.matches("^" + RateIndustry.plantOther.getIndustry()[0] + ".*") && !industry.equals(RateIndustry.plantVeg.getIndustry()[0]))
					rateSurvey.setIndustry(RateIndustry.plantOther.getCode());
				//养猪
				else if(industry.matches("^" + RateIndustry.breadPig.getIndustry()[0] + ".*"))
					rateSurvey.setIndustry(RateIndustry.breadPig.getCode());
				//养殖其他
				else if(industry.matches("^" + RateIndustry.breadOther.getIndustry()[0] + ".*") && !industry.equals(RateIndustry.breadPig.getIndustry()[0]))
					rateSurvey.setIndustry(RateIndustry.breadOther.getCode());
				//个人批发零售
				else if(industry.matches("^" + RateIndustry.personalRetail.getIndustry()[0] + ".*"))
					rateSurvey.setIndustry(RateIndustry.personalRetail.getCode());
				//个人运输
				else if(check)
					rateSurvey.setIndustry(RateIndustry.personalTrans.getCode());
				//个人制造
				else if(industry.matches("^" + RateIndustry.personalMake.getIndustry()[0] + ".*"))
					rateSurvey.setIndustry(RateIndustry.personalMake.getCode());
				//个人餐饮
				else if(industry.matches("^" + RateIndustry.personalCaterer.getIndustry()[0] + ".*"))
					rateSurvey.setIndustry(RateIndustry.personalCaterer.getCode());
				else rateSurvey.setIndustry(RateIndustry.personalOther.getCode());
				
			}
			//医疗保险上年度合计缴纳金额
			rateSurvey.setMiAmount(requestObject.get(RR001.SECUREMONEY.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.SECUREMONEY.toString())));
			//首付比例
			rateSurvey.setDownpayment(requestObject.get(RR001.FIRSTPAYRATE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.FIRSTPAYRATE.toString())));
			//是否购买车库
			rateSurvey.setIsBuyGarage(requestObject.get(RR001.IFBUYGARAGE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.IFBUYGARAGE.toString())));
			//借款人及家庭成员工作状况
			rateSurvey.setWorkStatus(requestObject.get(RR001.CUSTWORKINFO.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.CUSTWORKINFO.toString())));
			//截止目前家庭收入
			rateSurvey.setFamilyIncomeThisYear(requestObject.get(RR001.FAMANNUINCOME.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.FAMANNUINCOME.toString())));
			//截止目前家庭支出
			rateSurvey.setFamilyExpendThisYear(requestObject.get(RR001.FAMANNUINOUT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.FAMANNUINOUT.toString())));
			//上年度家庭收入
			rateSurvey.setFamilyIncomeLastYear(requestObject.get(RR001.LASTFAMINCOME.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.LASTFAMINCOME.toString())));
			//上年度家庭支出
			rateSurvey.setFamilyExpendLatsYear(requestObject.get(RR001.LASTFAMINOUT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.LASTFAMINOUT.toString())));
			//前年家庭收入
			rateSurvey.setFamilyIncomeBeforeLastYear(requestObject.get(RR001.FAMINCOMEBEFORE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.FAMINCOMEBEFORE.toString())));
			//前年家庭支出
			rateSurvey.setFamilyExpendBeforeLastYear(requestObject.get(RR001.FAMINCOMEBEFOUT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.FAMINCOMEBEFOUT.toString())));
			//种植/养殖/经营年限
			rateSurvey.setPlantYear(requestObject.get(RR001.JJXMLMT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.JJXMLMT.toString())));
			//是否在城区购买商住商用房
			rateSurvey.setIsBuyHouse(requestObject.get(RR001.IFBUYHOUSE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.IFBUYHOUSE.toString())));
			//经营项目固定资产
			rateSurvey.setProjectAssets(requestObject.get(RR001.JJXMPPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.JJXMPPE.toString())));
			//上年度经营收入
			rateSurvey.setProjectIncomeLastYear(requestObject.get(RR001.LASTYSR.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.LASTYSR.toString())));
			//上年度经营支出
			rateSurvey.setProjectExpendLastYear(requestObject.get(RR001.LASTYZC.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.LASTYZC.toString())));
			//经营场地面积（含自有）
			rateSurvey.setProjectArea(requestObject.get(RR001.FIELDAREAZY.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.FIELDAREAZY.toString())));
			//有人身意外伤害保险或工伤保险的员工数量
			rateSurvey.setInsuranceStaffCount(requestObject.get(RR001.BXNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.BXNUM.toString())));
			//职工人数（雇员人数）
			rateSurvey.setStaffCount(requestObject.get(RR001.EMPLOYEENUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.EMPLOYEENUM.toString())));
			//产品编码
			rateSurvey.setProductCode(requestObject.get(RR001.APPBUSITYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.APPBUSITYPE.toString())));
			//大棚面积
			rateSurvey.setGreenhoustArea(requestObject.get(RR001.JJXMBUILDAREA.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.JJXMBUILDAREA.toString())));
			//生产经营场所总建筑面积（含租赁）
			rateSurvey.setProjectBuildArea(requestObject.get(RR001.FIELDAREAZL.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.FIELDAREAZL.toString())));
			//申请时点所在周期
			rateSurvey.setUpOrDown(requestObject.get(RR001.APPDATECYCLE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.APPDATECYCLE.toString())));
			//拟购房产类别
			rateSurvey.setWantHouseType(requestObject.get(RR001.HOUSETYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.HOUSETYPE.toString())));
			//自有房产数量
			rateSurvey.setOwnHouseCount(requestObject.get(RR001.ZYHOUSENUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.ZYHOUSENUM.toString())));
/*			//拟购房屋套数
			Object houseCount = requestObject.get(RR001.HOUSENUM.toString());
			if(houseCount.equals(isEmptyFlag)){
				rateSurvey.setOwnHouseCount(null);
			}
			else{
				int count = Integer.valueOf(String.valueOf(houseCount));
				if(count < 1)
					rateSurvey.setOwnHouseCount("0");
				else rateSurvey.setOwnHouseCount(String.valueOf(count - 1));
			}*/
			//对外担保本金余额（家庭）
			rateSurvey.setFaOutGuarAmount(requestObject.get(RR001.DBSUMBALAMT1.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.DBSUMBALAMT1.toString())));
			//是否在固定日发放工资 
			rateSurvey.setIsFixedWages(requestObject.get(RR001.IFGDRFFGZ.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.IFGDRFFGZ.toString())));
			//与下游客户的平均合作年限 
			rateSurvey.setAverateCooYear(requestObject.get(RR001.JOINWENTURE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.JOINWENTURE.toString())));
			//近三年经营收入 
			rateSurvey.setLast3yearOperateIncome(requestObject.get(RR001.LASTTHREEYSR.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.LASTTHREEYSR.toString())));
			//近三年经营支出 
			rateSurvey.setLast3yearOperateOutcome(requestObject.get(RR001.LASTTHREEYZC.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.LASTTHREEYZC.toString())));
			//经营项目应收账款 
			rateSurvey.setProjectShouldIncome(requestObject.get(RR001.ACCRECEIVABLE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.ACCRECEIVABLE.toString())));
			//经营项目负债 
			rateSurvey.setProjectDebt(requestObject.get(RR001.OPERATEDEBT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.OPERATEDEBT.toString())));
			//最高学历
			rateSurvey.setEdu(requestObject.get(RR001.EDUCATIONLEVEL.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.EDUCATIONLEVEL.toString())));
			//存货数量
			rateSurvey.setStockCount(requestObject.get(RR001.JJXMCL.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.JJXMCL.toString())));
			//近3年实际控制人是否变更
			rateSurvey.setActualControllerChangeIn3Y(requestObject.get(RR001.CONTROLLERCHANGE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.CONTROLLERCHANGE.toString())));
			objectMap.put(rateSurvey.getObjType(), rateSurvey);
			
			Rate rateBase = new Rate();
			//对象编码，基本信息
			rateBase.setObjType(ObjectCode.getObjectCode(ObjectCode.rate, ObjectCode.rateBase));
			//客户类型
			rateBase.setCustType(requestObject.get(RR001.CUSTTYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.CUSTTYPE.toString())));
			//职业细分
			rateBase.setProfession(requestObject.get(RR001.VOCATIONMIN.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.VOCATIONMIN.toString())));
			//年龄
			rateBase.setAge(requestObject.get(RR001.AGE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.AGE.toString())));
			//性别
			rateBase.setGender(requestObject.get(RR001.SEX.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.SEX.toString())));
			//婚姻状况
			rateBase.setMarriage(requestObject.get(RR001.MARRIGE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.MARRIGE.toString())));
			//有无子女
			rateBase.setHavaChild(requestObject.get(RR001.IFHAVECHILD.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.IFHAVECHILD.toString())));
			//结婚证登记日期
			Object marrigeDate = requestObject.get(RR001.MARRIGEDATE.toString());
			if(!marrigeDate.equals(isEmptyFlag)){
				String[] time = String.valueOf(marrigeDate).split("-");
				int month = StUtil.strToInt(time[1]);
				int year = StUtil.strToInt(time[0]);
				if(currentMonth - month >= 0){
					rateBase.setMarriageYear(String.valueOf(currentYear - year));
				}
				else rateBase.setMarriageYear(String.valueOf(currentYear - year - 1));
			}
			objectMap.put(rateBase.getObjType(), rateBase);
			
			Rate rateCredit = new Rate();
			//对象编码，征信信息
			rateCredit.setObjType(ObjectCode.getObjectCode(ObjectCode.rate, ObjectCode.rateCredit));
			//申请授信金额
			rateCredit.setApplyAmount(rateSurvey.getApplyAmount());
			//申请金额
			rateCredit.setApplyMoney(String.valueOf(applyAmount));
			//最近6个月征信报告查询次数
			rateCredit.setCrQueryCountIn6M(requestObject.get(RR001.SIXMCHECKNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.SIXMCHECKNUM.toString())));
			//最近12个月征信报告查询次数
			rateCredit.setCrQueryCountIn12M(requestObject.get(RR001.ONEYCHECKNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.ONEYCHECKNUM.toString())));
			//最近24个月征信报告查询次数
			rateCredit.setCrQueryCountIn24M(requestObject.get(RR001.TOWYCHECKNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.TOWYCHECKNUM.toString())));
			//最近36个月征信报告查询次数
			rateCredit.setCrQueryCountIn36M(requestObject.get(RR001.THREEYCHECKNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.THREEYCHECKNUM.toString())));
			//最近6个月本息的逾期次数（征信）
			rateCredit.setCrOverdueCountIn6M(requestObject.get(RR001.SIXMOVERDUENUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.SIXMOVERDUENUM.toString())));
			//最近12个月本息的逾期次数（征信）
			rateCredit.setCrOverdueCountIn12M(requestObject.get(RR001.ONEYOVERDUENUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.ONEYOVERDUENUM.toString())));
			//最近24个月本息的逾期次数（征信）
			rateCredit.setCrOverdueCountI24M(requestObject.get(RR001.TOWYOVERDUENUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.TOWYOVERDUENUM.toString())));
			//最近36个月本息的逾期次数（征信）
			rateCredit.setCrOverdueCountIn36M(requestObject.get(RR001.THREEYOVERDUENUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.THREEYOVERDUENUM.toString())));
			//最近一次本息逾期距离现在的月数(征信）
			rateCredit.setCrOverdueMonthCount(requestObject.get(RR001.MINOVERDUETDATE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.MINOVERDUETDATE.toString())));
			//最近12个月贷记卡开户个数（征信）
			rateCredit.setCrCreditcardOpenCount(requestObject.get(RR001.ONEYKHNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.ONEYKHNUM.toString())));
			//最早贷款账户距今月数（征信）
			rateCredit.setCrEarly(requestObject.get(RR001.FIRSTACCMONTH.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.FIRSTACCMONTH.toString())));
			//对外担保本金余额（征信）
			rateCredit.setCrOutGuarAmount(requestObject.get(RR001.DBSUMBALAMT2.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.DBSUMBALAMT2.toString())));
			//是否有征信报告
			rateCredit.setIsCredit(requestObject.get(RR001.IFEXISTCREDIT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.IFEXISTCREDIT.toString())));
			//信用卡数量（不含销户）
			rateCredit.setCreditCardCount(requestObject.get(RR001.CCARDNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.CCARDNUM.toString())));
			//最近6个月本息最大逾期期数（征信）
			rateCredit.setCrOverdueMaxCountIn6M(requestObject.get(RR001.SIXMOVERDUETERM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.SIXMOVERDUETERM.toString())));
			//最近24个月本息最大逾期期数（征信）
			rateCredit.setCrOverdueMaxCountIn24M(requestObject.get(RR001.TOWYOVERDUETERM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.TOWYOVERDUETERM.toString())));
			//近6个月日均存款金额
			rateCredit.setDailyDepositAmountIn6M(requestObject.get(RR001.SIXMDAILY.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.SIXMDAILY.toString())));
			//申请时点未结清账户数
			rateCredit.setAccountAcount(requestObject.get(RR001.APPDATELOANNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.APPDATELOANNUM.toString())));
			//近12个月日均存款金额
			rateCredit.setDailyDepositAmountIn12M(requestObject.get(RR001.ONEYDAILY.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.ONEYDAILY.toString())));
			//最近12个月ATM与POS交易次数之和（夜间）
			rateCredit.setATMPOSCountIn12M(requestObject.get(RR001.ONEYSPOSNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.ONEYSPOSNUM.toString())));
			//最近一次利息逾期距离现在的月数(本行）
			rateCredit.setRateOverdueMonthCount(requestObject.get(RR001.BHMINRATEOVERDUETDATE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.BHMINRATEOVERDUETDATE.toString())));
			//最近一次本金逾期距离现在的月数(本行）
			rateCredit.setMoneyOverdueMonthCount(requestObject.get(RR001.BHMINOVERDUETDATE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.BHMINOVERDUETDATE.toString())));
			//行内最近一次本息逾期距离现在的月数
			if(rateCredit.getRateOverdueMonthCount() == null)
				rateCredit.setOverdueMonthCount(rateCredit.getMoneyOverdueMonthCount());
			else if(rateCredit.getMoneyOverdueMonthCount() == null)
				rateCredit.setOverdueMonthCount(rateCredit.getRateOverdueMonthCount());
			else{
				rateCredit.setOverdueMonthCount(String.valueOf(Math.min(Integer.valueOf(rateCredit.getMoneyOverdueMonthCount()), Integer.valueOf(rateCredit.getRateOverdueMonthCount()))));
			}
			//最近6个月本息的逾期次数（本行）
			rateCredit.setOverdueCountIn6M(requestObject.get(RR001.BHSIXMOVERDUENUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.BHSIXMOVERDUENUM.toString())));
			//最近12个月本息的逾期次数（本行）
			rateCredit.setOverdueCountIn12M(requestObject.get(RR001.BHONEYOVERDUENUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.BHONEYOVERDUENUM.toString())));
			//最近24个月本息的逾期次数（本行）
			rateCredit.setOverdueCountIn24M(requestObject.get(RR001.BHTOWYOVERDUENUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.BHTOWYOVERDUENUM.toString())));
			//最近36个月本息的逾期次数（本行）
			rateCredit.setOverdueCountIn36M(requestObject.get(RR001.BHTHREEYOVERDUENUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.BHTHREEYOVERDUENUM.toString())));
			//贷款申请时点敞口数
			rateCredit.setExposureCount(requestObject.get(RR001.APPOPENNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.APPOPENNUM.toString())));
			//最近6个月本息最大逾期期数（本行）
			rateCredit.setOverdueMaxCountIn6M(requestObject.get(RR001.BHSIXMOVERDUETERM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.BHSIXMOVERDUETERM.toString())));
			//最近24个月本息最大逾期期数（本行）
			rateCredit.setOverdueMaxCountIn24M(requestObject.get(RR001.BHTOWYOVERDUETERM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.BHTOWYOVERDUETERM.toString())));
			//近6个月（存款与理财）日均额
			rateCredit.setDailyFinAndDepAmountIn6M(requestObject.get(RR001.SIXMSDAILY.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.SIXMSDAILY.toString())));
			//近9个月（存款与理财）日均额
			rateCredit.setDailyFinAndDepAmountIn9M(requestObject.get(RR001.NINEMDAILY.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.NINEMDAILY.toString())));
			//近12个月（存款+理财）日均额
			rateCredit.setDailyFinAndDepAmountIn12M(requestObject.get(RR001.ONEYSDAILY.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.ONEYSDAILY.toString())));
			//最近12个月POS交易次数（夜间）
			rateCredit.setPosNightCountIn12M(requestObject.get(RR001.ONEYPOSNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.ONEYPOSNUM.toString())));
			//申请时点前其他敞口个数
			rateCredit.setOtherExposureCount(requestObject.get(RR001.APPOTHEROPENNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.APPOTHEROPENNUM.toString())));
			//与银行交易时长
			rateCredit.setTransactionDuration(requestObject.get(RR001.FIRSTLOANDAY.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.FIRSTLOANDAY.toString())));
			//最近12个月利息最大逾期期数（本行）
			rateCredit.setRateOverdueMaxCountIn12M(requestObject.get(RR001.BHONEYRATEOVERDUETERM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.BHONEYRATEOVERDUETERM.toString())));
			//最近12个月本息最大逾期期数（征信）
			rateCredit.setCrOverdueMaxCountIn12M(requestObject.get(RR001.ONEYOVERDUETERM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.ONEYOVERDUETERM.toString())));
			//是否有本行银行卡
			rateCredit.setIsHaveOurBankCard(requestObject.get(RR001.CREDITCARDFLAG.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.CREDITCARDFLAG.toString())));
			//是否有本行存储帐户
			rateCredit.setIsHaveOurStorageAcc(requestObject.get(RR001.DEPOSITINFOFLAG.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.DEPOSITINFOFLAG.toString())));
			//申请时点所在周期
			rateCredit.setUpOrDown(requestObject.get(RR001.APPDATECYCLE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.APPDATECYCLE.toString())));
			//本行是否有利息逾期
			Object ourIn = requestObject.get(RR001.BHMINRATEOVERDUETDATE.toString());
			if(noMonth.equals(ourIn))
				rateCredit.setIsHaveOverdue(YesNo.no.getsCode());
			else rateCredit.setIsHaveOverdue(YesNo.yes.getsCode());
			//本行是否有本息逾期
			Object ourCap = requestObject.get(RR001.BHMINOVERDUETDATE.toString());
			if(noMonth.equals(ourIn) && noMonth.equals(ourCap))
				rateCredit.setOurIsOverdueInAndC(YesNo.no.getsCode());
			else rateCredit.setOurIsOverdueInAndC(YesNo.yes.getsCode());
			//征信是否有本息逾期 
			if(YesNo.yes.getsCode().equals(rateCredit.getIsCredit())){
				if(noMonth.equals(requestObject.get(RR001.MINOVERDUETDATE.toString())))
					rateCredit.setCreIsOverdueInAndC(YesNo.no.getsCode());
				else rateCredit.setCreIsOverdueInAndC(YesNo.yes.getsCode());
			}
			//本行是否有本金逾期
			if(noMonth.equals(ourCap))
				rateCredit.setOurIsOverdueCap(YesNo.no.getsCode());
			else rateCredit.setOurIsOverdueCap(YesNo.yes.getsCode());
			objectMap.put(rateCredit.getObjType(), rateCredit);
			List<Object> objList = getDataSource(objectMap);
			StResultObject stResult = stEngine.StRateWithObject(objList, pd);
			
			
			
			result.setCode(stResult.getCode());
			result.setDesc(stResult.getSingleRuleDescription());
			if(!passCode.equals(stResult.getCode())) return result;
			else if(result.getDesc() == null){
				result.setRrModel("NODFK");
				result.setCseqLmt(-1d);
			}
			else if(passCode.equals(stResult.getCode()) && result.getDesc() != null){
				try {
					//计算评分卡最高额度
//					int limit = calCheckLimit(objectMap, pd, 0, 500, isEmptyFlag.equals(effectAmount) ? 0d : Double.valueOf(String.valueOf(effectAmount)));
					int limit = 0;
					String rateHighestAmount = "";
					if(limit==0)
						rateHighestAmount = String .valueOf(limit);
					else
						rateHighestAmount = String .valueOf(limit)+"0000";
					result.setRateHighestaMount(rateHighestAmount);
					
					//计算完成后重置申请金额和申请授信金额
					if(applyAmount.equals(isEmptyFlag) && !effectAmount.equals(isEmptyFlag))
						rateSurvey.setApplyAmount(String.valueOf(effectAmount));
					else if(!applyAmount.equals(isEmptyFlag) && effectAmount.equals(isEmptyFlag))
						rateSurvey.setApplyAmount(String.valueOf(applyAmount));
					else if(!applyAmount.equals(isEmptyFlag) && !effectAmount.equals(isEmptyFlag)){
						rateSurvey.setApplyAmount(String.valueOf(Double.valueOf(String.valueOf(applyAmount)) + Double.valueOf(String.valueOf(effectAmount))));
					}
					//打分卡用申请金额
					rateSurvey.setApplyLimit(String.valueOf(applyAmount));
					//规则运算用申请金额
					rateSurvey.setApplyMoney(String.valueOf(applyAmount));
					//申请授信金额
					rateCredit.setApplyAmount(rateSurvey.getApplyAmount());
					
					//计算完成后修改某些值
					
					//是否有银行卡
					if(YesNo.no.getsCode().equals(rateCredit.getIsHaveOurBankCard())){
						//最近12个月ATM与POS交易次数之和（夜间）
						rateCredit.setATMPOSCountIn12M(null);
						//最近12个月POS交易次数（夜间）
						rateCredit.setPosNightCountIn12M(null);
					}
					
					//有无行内存款信息
					if(YesNo.no.getsCode().equals(rateCredit.getIsHaveOurStorageAcc())){
						//近6个月（存款与理财）日均额
						rateCredit.setDailyFinAndDepAmountIn6M(null);
						//近9个月（存款与理财）日均额
						rateCredit.setDailyFinAndDepAmountIn9M(null);
						//近12个月（存款+理财）日均额
						rateCredit.setDailyFinAndDepAmountIn12M(null);
						//近6个月日均存款金额
						rateCredit.setDailyDepositAmountIn6M(null);
						//近12个月日均存款金额
						rateCredit.setDailyDepositAmountIn12M(null);
					}
					
					//行内是否有本息逾期
					if(YesNo.no.getsCode().equals(rateCredit.getOurIsOverdueInAndC())){
						//最近一次利息逾期距离现在的月数(本行）
						if("-1".equals(rateCredit.getRateOverdueMonthCount()))
							rateCredit.setRateOverdueMonthCount("无逾期");
						//最近一次本金逾期距离现在的月数(本行）
						if("-1".equals(rateCredit.getMoneyOverdueMonthCount()))
							rateCredit.setMoneyOverdueMonthCount("无逾期");
						//最近一次本息逾期距离现在的月数
						if("-1".equals(rateCredit.getOverdueMonthCount()))
								rateCredit.setOverdueMonthCount("无逾期");
						//最近6个月本息的逾期次数（本行）
						rateCredit.setOverdueCountIn6M("无逾期");
						//最近12个月本息的逾期次数（本行）
						rateCredit.setOverdueCountIn12M("无逾期");
						//最近24个月本息的逾期次数（本行）
						rateCredit.setOverdueCountIn24M("无逾期");
						//最近36个月本息的逾期次数（本行）
						rateCredit.setOverdueCountIn36M("无逾期");
						//最近6个月本息最大逾期期数（本行）
						rateCredit.setOverdueMaxCountIn6M("无逾期");
						//最近24个月本息最大逾期期数（本行）
						rateCredit.setOverdueMaxCountIn24M("无逾期");
					}
					
					//行内逾期次数为0是改为无逾期
					if("0".equals(rateCredit.getOverdueCountIn6M()))
						rateCredit.setOverdueCountIn6M("无逾期");
					if("0".equals(rateCredit.getOverdueCountIn12M()))
						rateCredit.setOverdueCountIn12M("无逾期");
					if("0".equals(rateCredit.getOverdueCountIn24M()))
						rateCredit.setOverdueCountIn24M("无逾期");
					if("0".equals(rateCredit.getOverdueCountIn36M()))
						rateCredit.setOverdueCountIn36M("无逾期");
					if("0".equals(rateCredit.getOverdueMaxCountIn6M()))
						rateCredit.setOverdueMaxCountIn6M("无逾期");
					if("0".equals(rateCredit.getOverdueMaxCountIn24M()))
						rateCredit.setOverdueMaxCountIn24M("无逾期");
					
					//征信是否有本息逾期
					if(YesNo.no.getsCode().equals(rateCredit.getCreIsOverdueInAndC())){
						//最近一次本息逾期距离现在的月数(征信）
						rateCredit.setCrOverdueMonthCount("无逾期");
						//最近6个月本息的逾期次数（征信）
						rateCredit.setCrOverdueCountIn6M("无逾期");
						//最近12个月本息的逾期次数（征信）
						rateCredit.setCrOverdueCountIn12M("无逾期");
						//最近24个月本息的逾期次数（征信）
						rateCredit.setCrOverdueCountI24M("无逾期");
						//最近36个月本息的逾期次数（征信）
						rateCredit.setCrOverdueCountIn36M("无逾期");
						//最近6个月本息最大逾期期数（征信）
						rateCredit.setCrOverdueMaxCountIn6M("无逾期");
						//最近24个月本息最大逾期期数（征信）
						rateCredit.setCrOverdueMaxCountIn24M("无逾期");
					}
					
					//征信逾期次数为0改为无逾期
					if("0".equals(rateCredit.getCrOverdueCountIn6M()))
						rateCredit.setCrOverdueCountIn6M("无逾期");
					if("0".equals(rateCredit.getCrOverdueCountIn12M()))
						rateCredit.setCrOverdueCountIn12M("无逾期");
					if("0".equals(rateCredit.getCrOverdueCountI24M()))
						rateCredit.setCrOverdueCountI24M("无逾期");
					if("0".equals(rateCredit.getCrOverdueCountIn36M()))
						rateCredit.setCrOverdueCountIn36M("无逾期");
					if("0".equals(rateCredit.getCrOverdueMaxCountIn6M()))
						rateCredit.setCrOverdueMaxCountIn6M("无逾期");
					if("0".equals(rateCredit.getCrOverdueMaxCountIn24M()))
						rateCredit.setCrOverdueMaxCountIn24M("无逾期");
					
					
					String r = rateRulerDao.getRateByScore(stResult.getResult());
					result.setCseqLmt(Double.valueOf(r));
					result.setRrModel(result.getDesc());
					result.setDesc(null);
					
					java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
					
					StRateResult rateResult = new StRateResult();
					String rateResultCode = rateResultDao.codeGenerator("StRateResult");
					rateResult.setOrgcode(pd.getInscode());
					rateResult.setRateresultcode(rateResultCode);
					rateResult.setRatecode(r);
					rateResult.setCustcode(rateSurvey.getCustcode());
					Object custname = requestObject.get(RR001.CNAME.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.CNAME.toString()));
					Object idtype = requestObject.get(RR001.CARDTYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.CARDTYPE.toString()));
					Object idnumber = requestObject.get(RR001.PERCARDNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(RR001.PERCARDNUM.toString()));
					Object orgcode = requestObject.get(Public.DEPTCODE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(Public.DEPTCODE.toString()));
					Object operator = requestObject.get(Public.OPERATOR.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(Public.OPERATOR.toString()));
					Object businesscode = requestObject.get(Public.ID.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(Public.ID.toString()));
					String name = "智易贷";
					rateResult.setCustname(custname == null ? null : String.valueOf(custname));
					rateResult.setIdtype(idtype == null ? null : String.valueOf(idtype));
					rateResult.setIdnumber(idnumber == null ? null : String.valueOf(idnumber)); 
					rateResult.setCardcode(result.getRrModel());
					rateResult.setRatescore(String.valueOf(stResult.getResult()));
					rateResult.setRatedate(now);
					rateResult.setOrgcode(orgcode == null ? null :String.valueOf(orgcode));
					rateResult.setCustmanager(operator == null ? null : String.valueOf(operator));
					rateResult.setLastopertime(now);
					rateResult.setInstcitycode(cityCode);
					rateResult.setBusinesscode(businesscode == null ? null : String.valueOf(businesscode));
					Map<String, Object> tmp = new HashMap<String, Object>();
					List<StRateresultData> datas = new ArrayList<StRateresultData>();
					for(Object o : objList) objectToMap(o, tmp);
					Map<String, Map<String, List<StObjectPropertyVo>>> demandProperty = StInit.getRateDemandProperty();
					Map<String, List<StObjectPropertyVo>> dProperty = demandProperty.get(pd.getLegalCode()) == null ? demandProperty.get(pd.getCityCode()) : demandProperty.get(pd.getLegalCode());
					
					//插入产品中类
					StRateresultData productData = new StRateresultData();
					productData.setRateresultcode(rateResultCode);
					productData.setDatakey("smallProduct");
					productData.setDatavalue(rateSurvey.getSmallProduct());
					productData.setDataname("产品中类");
					productData.setLastopertime(now);
					productData.setInstcitycode(cityCode);
					datas.add(productData);
					
					rateResultDao.addBySelfTransaction(rateResult);
					
					//重置行业值
					tmp.put("industry", industry);
					if(dProperty != null && dProperty.get(result.getRrModel()) != null){
						List<StObjectPropertyVo> propertyList = dProperty.get(result.getRrModel());
						if(propertyList.size() > 0){
							for(StObjectPropertyVo vo : propertyList){
								StRateresultData data = new StRateresultData();
								data.setRateresultcode(rateResultCode);
								data.setDatakey(vo.getPropertykey());
								data.setDatavalue(tmp.get(vo.getPropertykey()) == null ? null : String.valueOf(tmp.get(vo.getPropertykey())));
								data.setDataname(vo.getPropertyname());
								data.setIsenum(vo.getDatatypeVo() == null ? null : vo.getDatatypeVo().getIsenum());
								data.setDictcode(vo.getDatatypeVo() == null ? null : vo.getDatatypeVo().getDictcode());
								data.setInstcitycode(cityCode);
								data.setLastopertime(now);
								datas.add(data);
							}
						}
					}
					//插入行业
/*					StRateresultData industryData = new StRateresultData();
					industryData.setRateresultcode(rateResultCode);
					industryData.setIsenum(YesNo.yes.getCode());
					industryData.setDictcode("party.industry");
					industryData.setDatakey("industry");
					industryData.setDatavalue(industry);
					industryData.setDataname("行业");
					industryData.setLastopertime(now);
					datas.add(industryData);*/
					rateresultDataDao.batchInsert(datas);
					/*StTmpData t = new StTmpData();
					t.setBusinesscode(pd.getBusinesscode());
					t.setAddmodel(RuleClass.rate.getCode());
					t.setUsemodel(RuleClass.limitAmount.getCode());
					t.setDatakey(checkLimit);
					t.setAddtime(new java.sql.Timestamp(new Date().getTime()));
					double d = Double.valueOf(rateSurvey.getApplyLimit());
					t.setDatavalue(String.valueOf(calCheckLimit(objectMap, pd, 0, 500)) + "0000");*/
/*					try {
						baseDao.add(t);
					} catch (Exception e) {
						result.setCode(ErrorCode.checkLimitAddError.getCode());
						result.setDesc(ErrorCode.checkLimitAddError.getDesc());
						result.setResult(null);
						e.printStackTrace();
						logger.error(e);
					}*/
					
				} catch (Exception e) {
					logger.error(e);
					result.setCode(ErrorCode.rateRulerError.getCode());
					result.setDesc(ErrorCode.rateRulerError.getDesc());
					return result;
				}
			}
		}else if (RuleInterface.SUBMITINTER_ZD.equals(interCode)){
			AprovalProcess approve = new AprovalProcess();
			//对象类型
			approve.setObjType(ObjectCode.getObjectCode(ObjectCode.processTrend, ObjectCode.ProcessTrend.ApprovalProcessTrend.toString()));
			//流程标识
			approve.setProcessId(requestObject.get(APP001.PROCDEFID.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(APP001.PROCDEFID.toString())));
			//申请产品
			approve.setProductCode(requestObject.get(APP001.APPBUSITYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(APP001.APPBUSITYPE.toString())));
			//申请额度
			approve.setApplyAmount(requestObject.get(APP001.REQLMT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(APP001.REQLMT.toString())));
			//客户授信额度
			approve.setCustCreditAmount(requestObject.get(APP001.SUMREQLMT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(APP001.SUMREQLMT.toString())));
			//分支岗位
			approve.setBranchPost(requestObject.get(APP001.ACTIVITYID.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(APP001.ACTIVITYID.toString())));
			//产品中类
			approve.setSmallProduct(requestObject.get(APP001.UPBUSITYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(APP001.UPBUSITYPE.toString())));
			//担保方式
			approve.setCollaterguaranteeType(requestObject.get(APP001.MAINASSURE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(APP001.MAINASSURE.toString())));
			//客户类型
			approve.setCustType(requestObject.get(APP001.CUSTTYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(APP001.CUSTTYPE.toString())));
			//授信类型
			approve.setIfNewCust(requestObject.get(APP001.IFNEWCUST.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(APP001.IFNEWCUST.toString())));
			//产品小类
			approve.setLittleProduct(requestObject.get(APP001.BUSITYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(APP001.BUSITYPE.toString())));
			//机构单户限额
			approve.setOrgSingleLimit(requestObject.get(APP001.GROUPSINGLELIMIT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(APP001.GROUPSINGLELIMIT.toString())));
			//押品类型
			approve.setKeyType(requestObject.get(APP001.KEYTYPE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(APP001.KEYTYPE.toString())));
			//期限
			approve.setLimit(requestObject.get(APP001.LIMIT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(APP001.LIMIT.toString())));
			//浮动比例
			approve.setFloatRate(requestObject.get(APP001.FLOATRATE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(APP001.FLOATRATE.toString())));
			objectMap.put(approve.getObjType(), approve);
			StResultObject stResult = stEngine.StProcessWithObject(getDataSource(objectMap), pd);
			result.setCode(stResult.getCode());
			result.setDesc(stResult.getSingleRuleDescription());
			if(YesNo.no.getCode().equals(stResult.getResult())){
				result.setCode(ErrorCode.notFindFlow.getCode());
				result.setDesc(ErrorCode.notFindFlow.getDesc());
			}
			else if(passCode.equals(stResult.getCode()))
				result.setAuthOrity(stResult.getResult());
		}else if(RuleInterface.ZD00010.equals(interCode)){
			Admit admit = new Admit();
			//对象编码
			if("STEP_3".equals(requestObject.getCallStep())){
				admit.setObjType(ObjectCode.getObjectCode(ObjectCode.admit, ObjectType.warrant.getCode()));
			}
			else{
				admit.setObjType(ObjectCode.getObjectCode(ObjectCode.admit, ObjectType.borrower.getCode()));
			}
			//申请金额
			admit.setApplyAmount(requestObject.get(ZD00003.REQLMT.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00003.REQLMT.toString())));
			//相关企业是否存在用信余额
			admit.setIsEtpHaveCredit((requestObject.get(ZD00003.RELALOAN.toString()).equals(isEmptyFlag) ? null : String.valueOf(ZD00003.RELALOAN.toString())));
			//外部失信或客户违法违纪、不良嗜好信息名单、黑名单
			Object tmp = requestObject.get(ZD00003.CUSTLIST.toString());
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> custList = (List<Map<String, Object>>) tmp;
				if(custList != null && custList.size() > 0){
					for(Map<String, Object> cust : custList){
						Admit ad;
						//对象编码
						String objectCode = String.valueOf(cust.get(ZD00003.custlist.CHECKOBJ.toString()));
						String objectId = String.valueOf(cust.get(ZD00003.custlist.CUSTID.toString()));
						String cardNum = String.valueOf(cust.get(ZD00003.custlist.CARDNUM.toString()));
						if((!"STEP_3".equals(requestObject.getCallStep()) && objectCode.equals(ObjectType.borrower.getCode())) || ("STEP_3".equals(requestObject.getCallStep()) && objectCode.equals(ObjectType.warrant.getCode()))){
							 ad = admit;
						}
						else ad = new Admit();
						//是否新增客户
						ad.setIsNewCust(requestObject.get(ZD00003.IFNEWCUST.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00003.IFNEWCUST.toString())));
						//客户编码
						ad.setCustCode((cust.get(ZD00003.custlist.CUSTID.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00003.custlist.CUSTID.toString()))));
						//客户名称
						ad.setCustName(cust.get(ZD00003.custlist.CNAME.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00003.custlist.CNAME.toString())));
						//是否外部名单
						ad.setIsOutList(cust.get(ZD00003.custlist.INSXLIST.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00003.custlist.INSXLIST.toString())));
						ad.setObjType(objectCode.equals(isEmptyFlag) ? null : ObjectCode.getObjectCode(ObjectCode.admit, objectCode));
						//是否黑名单
						ad.setIsBlackList(cust.get(ZD00003.custlist.INBWLIST.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00003.custlist.INBWLIST.toString())));
						//家庭总资产
						ad.setFamilyAsset(cust.get(ZD00003.custlist.FAMASSET.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00003.custlist.FAMASSET.toString())));
						//家庭总负债
						ad.setFamilyDebt(cust.get(ZD00003.custlist.FAMDEBT.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00003.custlist.FAMDEBT.toString())));
						//近两年账户是否有过冻结
						ad.setIsFreeze(cust.get(ZD00003.custlist.IFFREEZE.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00003.custlist.IFFREEZE.toString())));
						if(!isEmptyFlag.equals(objectId))
							objectMap.put(objectId, ad);
						else objectMapByCardNum.put(cardNum, ad);
					}
				}
			}
			//1、客户有贷款余额的贷款信息(机构、贷款产品)
			//2、表内未结清贷款信息
			//3、表内未结清贷款五级分类状态
			tmp = requestObject.get("BNLIST");
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> bnList = (List<Map<String, Object>>) tmp;
				if(bnList != null && bnList.size() > 0){
					for(Map<String, Object> cust : bnList){
						if(cust.get(ZD00003.bnlist.CUSTID.toString()).equals(isEmptyFlag)){
							continue;
						}
						String objectId = String.valueOf(cust.get(ZD00003.bnlist.CUSTID.toString()));
						Admit ad;
						if(objectId != null && objectMap.get(objectId) != null)
							ad = (Admit) objectMap.get(objectId);
						else ad = new Admit();
						//最差五级分类形态
						if(!cust.get(ZD00003.bnlist.FIVECLASS.toString()).equals(isEmptyFlag)){
							int fiveClass = Integer.valueOf(String.valueOf(cust.get(ZD00003.bnlist.FIVECLASS.toString())));
							if(ad.getWorstFiveClass() != null && Integer.valueOf(ad.getWorstFiveClass()) < fiveClass){
								ad.setWorstFiveClass(String.valueOf(fiveClass));
							}
							else if(ad.getWorstFiveClass() == null) ad.setWorstFiveClass(String.valueOf(fiveClass));
						}
						//是否存在本金逾期、欠息
						if(((isEmptyFlag.equals(cust.get(ZD00003.bnlist.DELAYAMTDAYS.toString())) || "0".equals(cust.get(ZD00003.bnlist.DELAYAMTDAYS.toString())))
						  && (isEmptyFlag.equals(cust.get(ZD00003.bnlist.DELAYINTERESTDAYS.toString())) || "0".equals(cust.get(ZD00003.bnlist.DELAYINTERESTDAYS.toString())))
						  && !YesNo.yes.getsCode().equals(ad.getIsOverdue())) || isEmptyFlag.equals(cust.get(ZD00003.bnlist.BALAMT.toString()))
						  || Double.valueOf(String.valueOf(cust.get(ZD00003.bnlist.BALAMT.toString()))) <= 0){
							ad.setIsOverdue(YesNo.no.getsCode());
						}
						else{
							ad.setIsOverdue(YesNo.yes.getsCode());
						}
						objectMap.put(objectId, ad);
					}
				}
			}
			//表外未结清贷款信息
			//表外已结清贷款信息
			tmp = requestObject.get(ZD00003.BWLIST.toString());
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> bwList = (List<Map<String, Object>>) tmp;
				if(bwList != null && bwList.size() > 0){
					for(Map<String, Object> cust : bwList){
						if(cust.get(ZD00003.bwlist.OWNERID.toString()).equals(isEmptyFlag)){
							continue;
						}
						String objectId = String.valueOf(cust.get(ZD00003.bwlist.OWNERID.toString()));
						Admit ad;
						if(objectId != null && objectMap.get(objectId) != null)
							ad = (Admit) objectMap.get(objectId);
						else ad = new Admit();
						//是否存在行内未结清表外不良
						Object loanBanlance = cust.get(ZD00003.bwlist.LOANBALANCE.toString());
						if((isEmptyFlag.equals(loanBanlance) || Double.valueOf(String.valueOf(loanBanlance)) == 0)
								&& !YesNo.yes.getsCode().equals(ad.getIsUnsettledOutSheetBad())){
							ad.setIsUnsettledOutSheetBad(YesNo.no.getsCode());
						}else{
							ad.setIsUnsettledOutSheetBad(YesNo.yes.getsCode());
						}
						//过去3年是否存在已结清表外不良
						if(!cust.get(ZD00003.bwlist.CLOSEDATE.toString()).equals(isEmptyFlag)){
							String closeDate = String.valueOf(cust.get(ZD00003.bwlist.CLOSEDATE.toString()));
							int before3Year = currentYear - 3;
							String before3Date = String.valueOf(before3Year) + (currentMonth < 10 ? "0" : "") +  String.valueOf(currentMonth) 
												 + (currentDay < 10 ? "0" : "") + String.valueOf(currentDay);
							if(Integer.valueOf(closeDate.replaceAll("-", "")) >= Integer.valueOf(before3Date)){
								ad.setIsSettledOutSheetBad(YesNo.yes.getsCode());
							}
							else ad.setIsSettledOutSheetBad(YesNo.no.getsCode());
						}
						objectMap.put(objectId, ad);
					}
				}
			}
			//展期(根据发起展期授权的日期进行判断)
			tmp = requestObject.get(ZD00003.EXPLIST.toString());
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> expList = (List<Map<String, Object>>) tmp;
				if(expList != null && expList.size() > 0){
					for(Map<String, Object> cust : expList){
						if(cust.get(ZD00003.explist.CUSTID.toString()).equals(isEmptyFlag)){
							continue;
						}
						String objectId = String.valueOf(cust.get(ZD00003.explist.CUSTID.toString()));
						Admit ad;
						if(objectId != null && objectMap.get(objectId) != null)
							ad = (Admit) objectMap.get(objectId);
						else ad = new Admit();
						//客户近12个月是否存在展期
						if(!cust.get(ZD00003.explist.LOANDATE.toString()).equals(isEmptyFlag)){
							String loanDate = String.valueOf(cust.get(ZD00003.explist.LOANDATE.toString()));
							int before1Year = currentYear - 1;
							String before1Date = String.valueOf(before1Year) + (currentMonth < 10 ? "0" : "") +  String.valueOf(currentMonth) 
												 + (currentDay < 10 ? "0" : "") + String.valueOf(currentDay);
							if(Integer.valueOf(loanDate.replaceAll("-", "")) >= Integer.valueOf(before1Date)){
								ad.setIsExend(YesNo.yes.getsCode());
							}
							else ad.setIsExend(YesNo.no.getsCode());
						}
						objectMap.put(objectId, ad);
					}
				}
			}
			//客户对外担保表内未结清贷款
			//客户对外担保表外未结清贷款
			tmp = requestObject.get(ZD00003.BNWLIST_DB.toString());
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> bnwList = (List<Map<String, Object>>) tmp;
				if(bnwList != null && bnwList.size() > 0){
					for(Map<String, Object> cust : bnwList){
						if(cust.get(ZD00003.bnwlist_db.CUSTID.toString()).equals(isEmptyFlag)){
							continue;
						}
						String objectId = String.valueOf(cust.get(ZD00003.bnwlist_db.CUSTID.toString()));
						Admit ad;
						if(objectId != null && objectMap.get(objectId) != null)
							ad = (Admit) objectMap.get(objectId);
						else ad = new Admit();
						//客户是否存在对外担保表内未结清贷款信息
						//客户是否存在对外担保表外未结清贷款信息
						if(!cust.get(ZD00003.bnwlist_db.ISOUTTABLELOAN.toString()).equals(isEmptyFlag)){
							if(cust.get(ZD00003.bnwlist_db.ISOUTTABLELOAN.toString()).equals(YesNo.yes.getsCode())){
								if((isEmptyFlag.equals(cust.get(ZD00003.bnwlist_db.DELAYAMTDAYS.toString())) || "0".equals(cust.get(ZD00003.bnwlist_db.DELAYAMTDAYS.toString())))
										  && (isEmptyFlag.equals(cust.get(ZD00003.bnwlist_db.DELAYINTERESTDAYS.toString())) || "0".equals(cust.get(ZD00003.bnwlist_db.DELAYINTERESTDAYS.toString())))
										  && !YesNo.yes.getsCode().equals(ad.getIsGuaranteOutSheetBad())){
									ad.setIsGuaranteOutSheetBad(YesNo.no.getsCode());
								}else{
									ad.setIsGuaranteOutSheetBad(YesNo.yes.getsCode());
								}
							}
							else{
								if((isEmptyFlag.equals(cust.get(ZD00003.bnwlist_db.DELAYAMTDAYS.toString())) || "0".equals(cust.get(ZD00003.bnwlist_db.DELAYAMTDAYS.toString())))
										  && (isEmptyFlag.equals(cust.get(ZD00003.bnwlist_db.DELAYINTERESTDAYS.toString())) || "0".equals(cust.get(ZD00003.bnwlist_db.DELAYINTERESTDAYS.toString())))
										  && !YesNo.yes.getsCode().equals(ad.getIsGuaranteInSheetBad())){
									ad.setIsGuaranteInSheetBad(YesNo.no.getsCode());
								}
								else{
									ad.setIsGuaranteInSheetBad(YesNo.yes.getsCode());
								}
								
							}
						}
						objectMap.put(objectId, ad);
					}
				}
			}
			//征信列表
			tmp = requestObject.get(ZD00004.ZXLIST.toString());
			if(!tmp.equals(isEmptyFlag)){
				List<Map<String, Object>> zxList = (List<Map<String, Object>>) tmp;
				if(zxList != null && zxList.size() > 0){
					for(Map<String, Object> cust : zxList){
						Admit ad;
						String objectCode = ObjectCode.getObjectCode(ObjectCode.admit, String.valueOf(cust.get(ZD00004.zxlist.CHECKOBJ.toString())));
						String objectId = String.valueOf(cust.get(ZD00004.zxlist.CUSTID.toString()));
						String cardNum = String.valueOf(cust.get(ZD00003.custlist.CARDNUM.toString()));
						if(cardNum != null && objectMapByCardNum.get(cardNum) != null){
							ad = (Admit) objectMapByCardNum.get(cardNum);
						}
						else if(objectId != null && objectMap.get(objectId) != null){
							ad = (Admit) objectMap.get(objectId);
						}
						else ad = new Admit();
						//是否新增客户
						ad.setIsNewCust(requestObject.get(ZD00003.IFNEWCUST.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get(ZD00003.IFNEWCUST.toString())));
						//对象编码
						ad.setObjType(cust.get(ZD00004.zxlist.CHECKOBJ.toString()).equals(isEmptyFlag) ? null : ObjectCode.getObjectCode(ObjectCode.admit, String.valueOf(cust.get(ZD00004.zxlist.CHECKOBJ.toString()))));
						//客户编码
						ad.setCustCode((cust.get(ZD00004.zxlist.CUSTID.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.CUSTID.toString()))));
						//当前预期数
						ad.setCrOverdueCount(cust.get(ZD00004.zxlist.OVERDUETERM.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.OVERDUETERM.toString())));
						//他行当前最差五级分类形态
						ad.setCrOtherWorstFiveClass(cust.get(ZD00004.zxlist.THWROSTCLASS.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.THWROSTCLASS.toString())));
						//对外担保本金余额
						String crGuranteeAmount = cust.get(ZD00004.zxlist.DBSUMBALAMT.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.DBSUMBALAMT.toString()));
						if(ObjectCode.Admit.AdmitBorrower.toString().equals(objectCode) || ObjectCode.Admit.AdmitFamily.toString().equals(objectCode)){
							if(admit.getCrGuranteeAmount() == null)
								admit.setCrGuranteeAmount(crGuranteeAmount);
							else {
								if(crGuranteeAmount == null){
									
								}
//									admit.setCrGuranteeAmount(crGuranteeAmount);
								else
									admit.setCrGuranteeAmount(String.valueOf(Double.valueOf(admit.getCrGuranteeAmount()) + Double.valueOf(crGuranteeAmount)));
							}
						}else{
							ad.setCrGuranteeAmount(crGuranteeAmount);
						}
						//贷款法人机构数
						ad.setCrLoanOrgCount(cust.get(ZD00004.zxlist.FRNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.FRNUM.toString())));
						//未销户信用卡发卡机构数
						ad.setCrCreditCardOrgCount(cust.get(ZD00004.zxlist.NORMALCARDNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.NORMALCARDNUM.toString())));
						//12个月累计逾期次数
						ad.setCrOverdueCountIn12M(cust.get(ZD00004.zxlist.ONEYOVERDUENUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.ONEYOVERDUENUM.toString())));
						//未销户贷记卡与准贷记卡最近6个月平均使用率
						ad.setWithoutCreditAndSemiLateUseRatioIn6M(cust.get(ZD00004.zxlist.USERATE.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.USERATE.toString())));
						//最近24个月本息最大逾期期数（征信）
						ad.setCrOverdueSerialIn36m(cust.get(ZD00004.zxlist.TOWYOVERDUETERM.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.TOWYOVERDUETERM.toString())));
						//近12个月查询次数
						ad.setCrQueryCountIn12M(cust.get(ZD00004.zxlist.ONEYCHECKNUM.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.ONEYCHECKNUM.toString())));
						//征信状态
						ad.setCreditStatus(cust.get(ZD00004.zxlist.STATUS.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.STATUS.toString())));
						//对外担保贷款最差五级分类形态
						ad.setCrGuaranteWorstFiveClass(cust.get(ZD00004.zxlist.DBWROSTCLASS.toString()).equals(isEmptyFlag) ? null : String.valueOf(cust.get(ZD00004.zxlist.DBWROSTCLASS.toString())));
						if(objectId != null && objectMap.get(objectId) != null)
							objectMap.put(objectId, ad);
						else{
							objectMap.put(cardNum, ad);
							objectMapByCardNum.remove(cardNum);
						}
					}
				}
			}
			objectMap.putAll(objectMapByCardNum);
			StResultObject stResult = stEngine.StAdmitWithObject(getDataSource(objectMap), pd);
			result = admitResultSwitch(result, stResult);
		}
		else{
			result.setCode(ErrorCode.notHaveRuleInterCode.getCode());
			result.setDesc(ErrorCode.notHaveRuleInterCode.getDesc());
		}
		logger.info(interCode + "运行时间：" + (System.currentTimeMillis() - start));
		return result;
	}
	//计算核定额度
	private int calCheckLimit(Map<String, Object> objectMap, PublicData pd, int start, int end, double effectAmount){
		Rate rateSurvey = (Rate) objectMap.get(ObjectCode.getObjectCode(ObjectCode.rate, ObjectCode.rateSurvey));
		Rate rateCredit = (Rate) objectMap.get(ObjectCode.getObjectCode(ObjectCode.rate, ObjectCode.rateCredit));
		if(end == start){
			rateSurvey.setApplyMoney(end + "0000");
			rateSurvey.setApplyAmount(String.valueOf(Double.valueOf(rateSurvey.getApplyMoney()) + effectAmount));
			rateCredit.setApplyMoney(end + "0000");
			rateCredit.setApplyAmount(String.valueOf(Double.valueOf(rateSurvey.getApplyMoney()) + effectAmount));
			int eResult = Integer.valueOf(stEngine.StRateWithObjectNotRecordLog(getDataSource(objectMap), pd).getResult());
			if(eResult >= 620) return end;
			else return 0;
		}
		if(end == start + 1){
			rateSurvey.setApplyMoney(start + "0000");
			rateSurvey.setApplyAmount(String.valueOf(Double.valueOf(rateSurvey.getApplyMoney()) + effectAmount));
			rateCredit.setApplyMoney(start + "0000");
			rateCredit.setApplyAmount(String.valueOf(Double.valueOf(rateSurvey.getApplyMoney()) + effectAmount));
			int sResult = Integer.valueOf(stEngine.StRateWithObjectNotRecordLog(getDataSource(objectMap), pd).getResult());
			rateSurvey.setApplyMoney(end + "0000");
			rateSurvey.setApplyAmount(String.valueOf(Double.valueOf(rateSurvey.getApplyMoney()) + effectAmount));
			rateCredit.setApplyMoney(end + "0000");
			rateCredit.setApplyAmount(String.valueOf(Double.valueOf(rateSurvey.getApplyMoney()) + effectAmount));
			int eResult = Integer.valueOf(stEngine.StRateWithObjectNotRecordLog(getDataSource(objectMap), pd).getResult());
			if(eResult >= 620) return end;
			else if(sResult >= 620) return start;
			else return 0;
		}
		int m = start + end >> 1;
		rateSurvey.setApplyMoney(String.valueOf(m + "0000"));
		rateSurvey.setApplyAmount(String.valueOf(Double.valueOf(rateSurvey.getApplyMoney()) + effectAmount));
		rateCredit.setApplyMoney(String.valueOf(m + "0000"));
		rateCredit.setApplyAmount(String.valueOf(Double.valueOf(rateSurvey.getApplyMoney()) + effectAmount));
		int result = Integer.valueOf(stEngine.StRateWithObjectNotRecordLog(getDataSource(objectMap), pd).getResult());
		if(result >= 620){
			int tmp = calCheckLimit(objectMap, pd, m + 1, end,effectAmount);
			if(tmp == 0) return m;
			else return tmp;
		}
		return calCheckLimit(objectMap, pd, start, m - 1,effectAmount);
	}
	
	//得到规则所需数据源数据
	private List<Object> getDataSource(Map<String, Object> map){
		//构造规则所需数据
		Iterator<Object> it = map.values().iterator();
		List<Object> dataSource = new ArrayList<Object>();
		for(;it.hasNext();){
			dataSource.add(it.next());
		}
		return dataSource;
	}
	
	//共有对象数据加工
	private PublicData publicDataHandle(RuleRequestObject requestObject){
		PublicData pd = new PublicData();
		//业务编号
		pd.setBusinesscode(requestObject.get(Public.ID.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get("ID")));
		//操作员编码
		pd.setOprCode(requestObject.get(Public.OPERATOR.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get("OPERATOR")));
		//机构编码
		pd.setInscode(requestObject.get(Public.DEPTCODE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get("DEPTCODE")));
		//地市编码
		pd.setCityCode(requestObject.get(Public.INSTCITYCODE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get("INSTCITYCODE")));
		//法人机构编号
		pd.setLegalCode(requestObject.get(Public.FRCODE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get("FRCODE")));
		//业务日期
		pd.setBusDate(requestObject.get(Public.CURRENTDATE.toString()).equals(isEmptyFlag) ? null : String.valueOf(requestObject.get("CURRENTDATE")));
		//执行阶段
		pd.setStep(requestObject.getCallStep().equals(isEmptyFlag) ? null : String.valueOf(requestObject.getCallStep()));
		return pd;
	}
	//判断传入数据是否有空值
	private boolean checkIsNull(RuleRequestObject requestObject, RuleResponseObject result, String interCode){
		boolean r = false;
		String prompt = "";
		if(requestObject.getCallStep() == null){
			prompt = "CALLSTEP";
			result.setCode(ErrorCode.dataIsNull.getCode());
			result.setDesc(prompt + ErrorCode.dataIsNull.getDesc());
			return false;
		}
		if(RuleInterface.ZD00001.equals(interCode)){
			for(ZD00001 e : ZD00001.values()){
				if(requestObject.get(e.toString()) == null){
					prompt = e.toString();
					r = true;
					break;
				}
			}
		}
		else if(RuleInterface.ZD00002.equals(interCode)){
			for(ZD00002 e : ZD00002.values()){
				if(requestObject.get(e.toString()) == null){
					prompt = e.toString();
					r = true;
					break;
				}
			}
		}
		else if(RuleInterface.ZD00003.equals(interCode)){
			for(ZD00003 e : ZD00003.values()){
				if(r)
					break;
				if(requestObject.get(e.toString()) == null){
					prompt = e.toString();
					r = true;
					break;
				}
				if(e == ZD00003.CUSTLIST && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r) break;
							for(ZD00003.custlist c : ZD00003.custlist.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
				if(e == ZD00003.BNLIST && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r) break;
							for(ZD00003.bnlist c : ZD00003.bnlist.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
				if(e == ZD00003.BWLIST && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r) break;
							for(ZD00003.bwlist c : ZD00003.bwlist.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
				if(e == ZD00003.EXPLIST && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r) break;
							for(ZD00003.explist c : ZD00003.explist.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
				if(e == ZD00003.BNWLIST_DB && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r) break;
							for(ZD00003.bnwlist_db c : ZD00003.bnwlist_db.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
			}
		}
		else if(RuleInterface.ZD00004.equals(interCode)){
			for(ZD00004 e : ZD00004.values()){
				if(requestObject.get(e.toString()) == null){
					prompt = e.toString();
					r = true;
					break;
				}
				if(e == ZD00004.ZXLIST && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r)
								break;
							for(ZD00004.zxlist c : ZD00004.zxlist.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
			}
		}
		else if(RuleInterface.ZD00005.equals(interCode)){
			for(ZD00005 e : ZD00005.values()){
				if(requestObject.get(e.toString()) == null){
					prompt = e.toString();
					r = true;
					break;
				}
			}
		}
		else if(RuleInterface.ZD00006.equals(interCode)){
			for(ZD00006 e : ZD00006.values()){
				if(r)
					break;
				if(requestObject.get(e.toString()) == null){
					prompt = e.toString();
					r = true;
					break;
				}
				if(e == ZD00006.CUSTLIST && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r) break;
							for(ZD00003.custlist c : ZD00003.custlist.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
				if(e == ZD00006.BNLIST && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r) break;
							for(ZD00003.bnlist c : ZD00003.bnlist.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
				if(e == ZD00006.BWLIST && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r) break;
							for(ZD00003.bwlist c : ZD00003.bwlist.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
				if(e == ZD00006.EXPLIST && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r) break;
							for(ZD00003.explist c : ZD00003.explist.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
				if(e == ZD00006.BNWLIST_DB && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r) break;
							for(ZD00003.bnwlist_db c : ZD00003.bnwlist_db.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
			}
		}
		else if(RuleInterface.ZD00007.equals(interCode)){
			for(ZD00007 e : ZD00007.values()){
				if(requestObject.get(e.toString()) == null){
					prompt = e.toString();
					r = true;
					break;
				}
			}
		}
		else if(RuleInterface.ZD00008.equals(interCode)){
			for(ZD00008 e : ZD00008.values()){
				if(requestObject.get(e.toString()) == null){
					prompt = e.toString();
					r = true;
					break;
				}
			}
		}
		else if(RuleInterface.ZD00009.equals(interCode)){
			for(ZD00009 e : ZD00009.values()){
				if(requestObject.get(e.toString()) == null){
					prompt = e.toString();
					r = true;
					break;
				}
			}
		}
		else if(RuleInterface.ZD00010.equals(interCode)){
			for(ZD00003 e : ZD00003.values()){
				if(r)
					break;
				if(requestObject.get(e.toString()) == null){
					prompt = e.toString();
					r = true;
					break;
				}
				if(e == ZD00003.CUSTLIST && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r) break;
							for(ZD00003.custlist c : ZD00003.custlist.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
				if(e == ZD00003.BNLIST && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r) break;
							for(ZD00003.bnlist c : ZD00003.bnlist.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
				if(e == ZD00003.BWLIST && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r) break;
							for(ZD00003.bwlist c : ZD00003.bwlist.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
				if(e == ZD00003.EXPLIST && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r) break;
							for(ZD00003.explist c : ZD00003.explist.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
				if(e == ZD00003.BNWLIST_DB && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r) break;
							for(ZD00003.bnwlist_db c : ZD00003.bnwlist_db.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
			}
			for(ZD00004 e : ZD00004.values()){
				if(requestObject.get(e.toString()) == null){
					prompt = e.toString();
					r = true;
					break;
				}
				if(e == ZD00004.ZXLIST && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r)
								break;
							for(ZD00004.zxlist c : ZD00004.zxlist.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
			}
		}
		else if(RuleInterface.LMT001.equals(interCode)){
			for(LMT001 e : LMT001.values()){
				if(requestObject.get(e.toString()) == null){
					prompt = e.toString();
					r = true;
					break;
				}
//取消担保人净资产，联合组织其他成员家庭净资产校验				
/*				if(e == LMT001.GUARLIST && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r)
								break;
							for(LMT001.guarlist c : LMT001.guarlist.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}
				if(e == LMT001.UNIONLIST && !requestObject.get(e.toString()).equals(isEmptyFlag)){
					List<Map<String, Object>> list = (List<Map<String, Object>>) requestObject.get(e.toString());
					if(list.size() > 0){
						for(Map<String, Object> map : list){
							if(r) break;
							for(LMT001.unionlist c : LMT001.unionlist.values()){
								if(map.get(c.toString()) == null){
									prompt = e.toString() + "." + c.toString();
									r = true;
									break;
								}
							}
						}
					}
				}*/
			}
		}
		else if(RuleInterface.RR001.equals(interCode)){
			for(RR001 e : RR001.values()){
				if(requestObject.get(e.toString()) == null){
					r = true;
					prompt = e.toString();
					break;
				}

			}
		}
		else if(RuleInterface.SUBMITINTER_ZD.equals(interCode)){
			for(APP001 e : APP001.values()){
				if(requestObject.get(e.toString()) == null){
					r = true;
					prompt = e.toString();
					break;
				}
			}
		}
		if(r){
			result.setCode(ErrorCode.dataIsNull.getCode());
			result.setDesc(prompt + ErrorCode.dataIsNull.getDesc());
		}
		return r;
	}
	
	//准入运算结果转换
	private RuleResponseObject admitResultSwitch(RuleResponseObject responseObject, StResultObject stResultObject){
		responseObject.setCode(stResultObject.getCode());
		responseObject.setDesc(stResultObject.getSingleRuleDescription());
		if(passCode.equals(stResultObject.getCode())){
			String stResult = stResultObject.getResult();
			if(stResult.equals(String.valueOf(AdmitResult.pass.getResult())))
				responseObject.setResult(admitPass);
			else if(stResult.equals(String.valueOf(AdmitResult.back.getResult())))
				responseObject.setResult(admitBack);
			else if(stResult.equals(String.valueOf(AdmitResult.split.getResult()))){
				responseObject.setResult(admitSplit);
			}
			else if(stResult.equals(String.valueOf(AdmitResult.refuse.getResult())))
				responseObject.setResult(admitRefuse);
			else {
				responseObject.setResult(GroupRuler.getResult(Integer.valueOf(stResult)));
				if(admitSplit.equals(responseObject.getResult()))
					responseObject.setDesc(responseObject.getDesc() == null ? stResultObject.getGroupRuleDescription() : (responseObject.getDesc() + ";") + stResultObject.getGroupRuleDescription());
				else if(admitBack.equals(responseObject.getResult()) || admitRefuse.equals(responseObject.getResult()))
					responseObject.setDesc(stResultObject.getGroupRuleDescription());
			}
		}
		else{
			responseObject.setResult(stResultObject.getResult());
		}
		return responseObject;
	}
	
	//获取额度计算系数
	private String getLimitamountRatio(String grade, String guar, int ratioType) throws Exception{
		String result = null;
		StLimitamountParm parm = new StLimitamountParm();
		List<StLimitamountParmVo> listTerm = limitAmountDao.queryListByModel(parm, StLimitamountParmVo.class);
		parm.setTermcode(null);
		parm.setGradecode(grade);
		List<StLimitamountParmVo> listGrade = limitAmountDao.queryListByModel(parm, StLimitamountParmVo.class);
		if((listTerm == null || listTerm.size() == 0) && (listGrade == null || listTerm.size() == 0))
			return result;
		else{
			StLimitamountParmVo termVo = listTerm.get(0);
			StLimitamountParmVo gradeVo = listGrade.get(0);
		}
		return result;
	}
	//获取是否限控行业
	private String getIsLimitIndustry(String industryCode) throws Exception{
		StLimitIndustry industry = new StLimitIndustry();
		industry.setIndustrycode(industryCode);
		List<StLimitIndustryVo> industryList = limitIndustryDao.queryListByModel(industry, StLimitIndustryVo.class);
		if(industryList == null || industryList.size() == 0)
			return YesNo.no.getsCode();
		else return YesNo.yes.getsCode();
	}
	
	//对象转map
	public static Map<String, Object> objectToMap(Object o, Map<String, Object> map){
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
				if(map.get(fieldName) == null){
					Method m = o.getClass().getDeclaredMethod("get" + tailMethod, new Class[]{});
					//所有字符数据加单引号
					Object tmp = m.invoke(o, new Object[]{});
					map.put(fieldName, tmp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return map;
	}

	

	
}
