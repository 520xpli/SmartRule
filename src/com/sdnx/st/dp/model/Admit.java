package com.sdnx.st.dp.model;


public class Admit {
	//对象类型
	private String objType;
	//客户编号
	private String custCode;
	//授信申请期限
	private String applyTerm;
	//年龄
	private String age;
	//婚姻状况
	private String marrige;
	//家庭总资产
	private String familyAsset;
	//家庭总负债
	private String familyDebt;
	//申请金额
	private String applyAmount;
	//办理机构是否相同
	private String isOrgSame;
	//相关企业是否存在贷款余额
	private String isEtpHaveCredit;
	//是否存在本金逾期,欠息
	private String isOverdue;
	//客户未结清贷款最差五级分类形态
	private String worstFiveClass;
	//是否存在表外不良
	private String isUnsettledOutSheetBad;
	//过去三年是否存在已结清表外不良
	private String isSettledOutSheetBad;
	//客户近12个月是否存在展期
	private String isExend;
	//客户是否存在对外担保表内未结清贷款信息
	private String isGuaranteInSheetBad;
	//客户是否存在对外担保表外未结清贷款信息
	private String isGuaranteOutSheetBad;
	//是否外部名单
	private String isOutList;
	//是否黑名单
	private String isBlackList;
	//是否有过冻结
	private String isFreeze;
	//当前逾期数
	private String crOverdueCount;
	//他行当前最差五级分类形态
	private String crOtherWorstFiveClass;
	//对外担保本金余额
	private String crGuranteeAmount;
	//贷款法人机构数
	private String crLoanOrgCount;
	//未销户信用卡发卡机构数
	private String crCreditCardOrgCount;
	//12个月累计逾期期数
	private String crOverdueCountIn12M;
	//36个月连续最高高逾期次数
	private String crOverdueSerialIn36m;
	//近12个月查询次数
	private String crQueryCountIn12M;
	//申请产品编号
	private String productCode;
	//上层业务品种
	private String smallProduct;
	//对外担保贷款最差五级分类形态
	private String crGuaranteWorstFiveClass;
	//未销户贷记卡与准贷记卡最近6个月平均使用率
	private String withoutCreditAndSemiLateUseRatioIn6M;
	//是否有征信信息
	private String isHaveCreditInfo;
	//是否新增客户
	private String isNewCust;
	//客户名称
	private String custName;
	//征信查询状态
	private String creditStatus;
	//性别
	private String gender;
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCreditStatus() {
		return creditStatus;
	}
	public void setCreditStatus(String creditStatus) {
		this.creditStatus = creditStatus;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getObjType() {
		return objType;
	}
	public void setObjType(String objType) {
		this.objType = objType;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(String applyTerm) {
		this.applyTerm = applyTerm;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getMarrige() {
		return marrige;
	}
	public void setMarrige(String marrige) {
		this.marrige = marrige;
	}
	public String getFamilyAsset() {
		return familyAsset;
	}
	public void setFamilyAsset(String familyAsset) {
		this.familyAsset = familyAsset;
	}
	public String getFamilyDebt() {
		return familyDebt;
	}
	public void setFamilyDebt(String familyDebt) {
		this.familyDebt = familyDebt;
	}
	public String getApplyAmount() {
		return applyAmount;
	}
	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}
	public String isOrgSame() {
		return isOrgSame;
	}
	public void setOrgSame(String isOrgSame) {
		this.isOrgSame = isOrgSame;
	}
	public String isEtpHaveCredit() {
		return isEtpHaveCredit;
	}
	public void setEtpHaveCredit(String isEtpHaveCredit) {
		this.isEtpHaveCredit = isEtpHaveCredit;
	}
	public String isOverdue() {
		return isOverdue;
	}
	public void setOverdue(String isOverdue) {
		this.isOverdue = isOverdue;
	}
	public String getWorstFiveClass() {
		return worstFiveClass;
	}
	public void setWorstFiveClass(String worstFiveClass) {
		this.worstFiveClass = worstFiveClass;
	}
	public String isUnsettledOutSheetBad() {
		return isUnsettledOutSheetBad;
	}
	public void setUnsettledOutSheetBad(String isUnsettledOutSheetBad) {
		this.isUnsettledOutSheetBad = isUnsettledOutSheetBad;
	}
	public String isSettledOutSheetBad() {
		return isSettledOutSheetBad;
	}
	public void setSettledOutSheetBad(String isSettledOutSheetBad) {
		this.isSettledOutSheetBad = isSettledOutSheetBad;
	}
	public String isExend() {
		return isExend;
	}
	public void setExend(String isExend) {
		this.isExend = isExend;
	}
	public String isGuaranteInSheetBad() {
		return isGuaranteInSheetBad;
	}
	public void setGuaranteInSheetBad(String isGuaranteInSheetBad) {
		this.isGuaranteInSheetBad = isGuaranteInSheetBad;
	}
	public String isGuaranteOutSheetBad() {
		return isGuaranteOutSheetBad;
	}
	public void setGuaranteOutSheetBad(String isGuaranteOutSheetBad) {
		this.isGuaranteOutSheetBad = isGuaranteOutSheetBad;
	}
	public String isOutList() {
		return isOutList;
	}
	public void setOutList(String isOutList) {
		this.isOutList = isOutList;
	}
	public String isBlackList() {
		return isBlackList;
	}
	public void setBlackList(String isBlackList) {
		this.isBlackList = isBlackList;
	}
	public String isFreeze() {
		return isFreeze;
	}
	public void setFreeze(String isFreeze) {
		this.isFreeze = isFreeze;
	}
	public String getCrOverdueCount() {
		return crOverdueCount;
	}
	public void setCrOverdueCount(String crOverdueCount) {
		this.crOverdueCount = crOverdueCount;
	}
	public String getCrOtherWorstFiveClass() {
		return crOtherWorstFiveClass;
	}
	public void setCrOtherWorstFiveClass(String crOtherWorstFiveClass) {
		this.crOtherWorstFiveClass = crOtherWorstFiveClass;
	}
	public String getCrGuranteeAmount() {
		return crGuranteeAmount;
	}
	public void setCrGuranteeAmount(String crGuranteeAmount) {
		this.crGuranteeAmount = crGuranteeAmount;
	}
	public String getCrLoanOrgCount() {
		return crLoanOrgCount;
	}
	public void setCrLoanOrgCount(String crLoanOrgCount) {
		this.crLoanOrgCount = crLoanOrgCount;
	}
	public String getCrCreditCardOrgCount() {
		return crCreditCardOrgCount;
	}
	public void setCrCreditCardOrgCount(String crCreditCardOrgCount) {
		this.crCreditCardOrgCount = crCreditCardOrgCount;
	}
	public String getCrOverdueCountIn12M() {
		return crOverdueCountIn12M;
	}
	public void setCrOverdueCountIn12M(String crOverdueCountIn12M) {
		this.crOverdueCountIn12M = crOverdueCountIn12M;
	}
	public String getCrOverdueSerialIn36m() {
		return crOverdueSerialIn36m;
	}
	public void setCrOverdueSerialIn36m(String crOverdueSerialIn36m) {
		this.crOverdueSerialIn36m = crOverdueSerialIn36m;
	}
	public String getCrQueryCountIn12M() {
		return crQueryCountIn12M;
	}
	public void setCrQueryCountIn12M(String crQueryCountIn12M) {
		this.crQueryCountIn12M = crQueryCountIn12M;
	}
	public String getIsOrgSame() {
		return isOrgSame;
	}
	public void setIsOrgSame(String isOrgSame) {
		this.isOrgSame = isOrgSame;
	}
	public String getIsEtpHaveCredit() {
		return isEtpHaveCredit;
	}
	public void setIsEtpHaveCredit(String isEtpHaveCredit) {
		this.isEtpHaveCredit = isEtpHaveCredit;
	}
	public String getIsOverdue() {
		return isOverdue;
	}
	public void setIsOverdue(String isOverdue) {
		this.isOverdue = isOverdue;
	}
	public String getIsUnsettledOutSheetBad() {
		return isUnsettledOutSheetBad;
	}
	public void setIsUnsettledOutSheetBad(String isUnsettledOutSheetBad) {
		this.isUnsettledOutSheetBad = isUnsettledOutSheetBad;
	}
	public String getIsSettledOutSheetBad() {
		return isSettledOutSheetBad;
	}
	public void setIsSettledOutSheetBad(String isSettledOutSheetBad) {
		this.isSettledOutSheetBad = isSettledOutSheetBad;
	}
	public String getIsExend() {
		return isExend;
	}
	public void setIsExend(String isExend) {
		this.isExend = isExend;
	}
	public String getIsGuaranteInSheetBad() {
		return isGuaranteInSheetBad;
	}
	public void setIsGuaranteInSheetBad(String isGuaranteInSheetBad) {
		this.isGuaranteInSheetBad = isGuaranteInSheetBad;
	}
	public String getIsGuaranteOutSheetBad() {
		return isGuaranteOutSheetBad;
	}
	public void setIsGuaranteOutSheetBad(String isGuaranteOutSheetBad) {
		this.isGuaranteOutSheetBad = isGuaranteOutSheetBad;
	}
	public String getIsOutList() {
		return isOutList;
	}
	public void setIsOutList(String isOutList) {
		this.isOutList = isOutList;
	}
	public String getIsBlackList() {
		return isBlackList;
	}
	public void setIsBlackList(String isBlackList) {
		this.isBlackList = isBlackList;
	}
	public String getIsFreeze() {
		return isFreeze;
	}
	public void setIsFreeze(String isFreeze) {
		this.isFreeze = isFreeze;
	}
	public String getCrGuaranteWorstFiveClass() {
		return crGuaranteWorstFiveClass;
	}
	public void setCrGuaranteWorstFiveClass(String crGuaranteWorstFiveClass) {
		this.crGuaranteWorstFiveClass = crGuaranteWorstFiveClass;
	}
	public String getWithoutCreditAndSemiLateUseRatioIn6M() {
		return withoutCreditAndSemiLateUseRatioIn6M;
	}
	public void setWithoutCreditAndSemiLateUseRatioIn6M(String withoutCreditAndSemiLateUseRatioIn6M) {
		this.withoutCreditAndSemiLateUseRatioIn6M = withoutCreditAndSemiLateUseRatioIn6M;
	}
	public String getIsHaveCreditInfo() {
		return isHaveCreditInfo;
	}
	public void setIsHaveCreditInfo(String isHaveCreditInfo) {
		this.isHaveCreditInfo = isHaveCreditInfo;
	}
	public String getIsNewCust() {
		return isNewCust;
	}
	public void setIsNewCust(String isNewCust) {
		this.isNewCust = isNewCust;
	}
	public String getSmallProduct() {
		return smallProduct;
	}
	public void setSmallProduct(String smallProduct) {
		this.smallProduct = smallProduct;
	}
	
	
}
