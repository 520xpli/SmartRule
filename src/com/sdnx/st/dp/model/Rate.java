package com.sdnx.st.dp.model;

public class Rate {
	//对象类型
	private String objType;
	//客户编号
	private String custcode;
	//客户名称
	private String custname;
	//证件类型
	private String idtype;
	//证件号码
	private String idnum;
	//申请额度
	private String applyAmount;
	//行业
	private String industry;
	//申请日期
	private String applyDate;
	//婚姻状况
	private String marriage;
	//结婚登记年份
	private String marriageYear;
	//有无子女
	private String havaChild;
	//年龄
	private String age;
	//性别
	private String gender;
	//学历
	private String edu;
	//职业
	private String profession;
	//本单位起始年份
	private String workYear;
	//医疗保险上年度年缴纳总额（家庭总额）
	private String miAmount;
	//是否购买车库
	private String isBuyGarage;
	//首付比例
	private String downpayment;
	//家庭总负债
	private String familyDebt;
	//家庭或有负债
	private String familyPossibleDebt;
	//家庭总资产
	private String familyAsset;
	//借款人及家庭成员工作情况
	private String workStatus;
	//截至目前家庭收入
	private String familyIncomeThisYear;
	//去年家庭收入
	private String familyIncomeLastYear;
	//前年家庭收入
	private String familyIncomeBeforeLastYear;
	//截至目前家庭支出
	private String familyExpendThisYear;
	//去年家庭年支出
	private String familyExpendLatsYear;
	//前年家庭年支出
	private String familyExpendBeforeLastYear;
	//种植亩数
	private String muSum;
	//养殖/种植/经营年限
	private String plantYear;
	//是否在城区购买商住商用房
	private String isBuyHouse;
	//经营项目固定资产
	private String projectAssets;
	//营运车辆数量
	private String carCount;
	//上年度经营支出
	private String projectExpendLastYear;
	//上年度经营首收入
	private String projectIncomeLastYear;
	//生产经营面积
	private String projectArea;
	//有人身意外伤害保险或工伤保险的员工数量
	private String insuranceStaffCount;
	//职工数量
	private String staffCount;
	//自由房产数量
	private String ownHouseCount;
	//pos消费次数
	private String posConsumeCount;
	//最近12个月ATM与pos交易次数之和
	private String ATMPOSCountIn12M;
	//近6个月日均存款金额
	private String dailyDepositAmountIn6M;
	//近9个月日均存款金额
	private String dailyDepositAmountIn9M;
	//近12个月日均存款金额
	private String dailyDepositAmountIn12M;
	//最近一次利息逾期距离现在的月数(本行)
	private String rateOverdueMonthCount;
	//最近一次本金逾期距离现在的月数(本行)
	private String moneyOverdueMonthCount;
	//行内最近6个月本息最大逾期期数
	private String overdueCountIn6M;
	//行内最近12个月本息最大逾期期数
	private String overdueCountIn12M;
	//行内最近24个月本息最大逾期期数
	private String overdueCountIn24M;
	//行内最近36个月本息最大逾期期数
	private String overdueCountIn36M;
	//行内最近一次本息逾期距离现在的月数
	private String overdueMonthCount;
	//最近6个月征信报告查询次数
	private String crQueryCountIn6M;
	//最近12个月征信报告查询次数
	private String crQueryCountIn12M;
	//最近24个月征信报告查询次数
	private String crQueryCountIn24M;
	//最近36个月征信报告查询次数
	private String crQueryCountIn36M;
	//征信最近6个月本息最大逾期期数
	private String crOverdueCountIn6M;
	//征信最近12个月本息最大逾期期数
	private String crOverdueCountIn12M;
	//征信最近24个月本息最大逾期期数
	private String crOverdueCountI24M;
	//征信最近36个月本息最大逾期期数
	private String crOverdueCountIn36M;
	//征信最近一次本息逾期距离现在的月数
	private String crOverdueMonthCount;
	//征信最近12个月贷记卡开户个数
	private String crCreditcardOpenCount;
	//最早贷款帐户距今月数
	private String crEarly;
	//征信未结清贷款余额汇总
	private String crLoanbalancesum;
	//征信未销户贷记卡余额汇总
	private String crCreditcardbalancesum;
	//征信未销户准贷记卡余额汇总
	private String crQuasicreditcardbalancesum;
	//征信家庭或有负债
	private String crFamily;
	//申请时点帐户数（未结清）
	private String accountAcount;
	//贷款申请时点敞口数
	private String exposureCount;
	//最近一次贷款发放日
	private String loanPayDate;
	//上升起或下降期
	private String upOrDown;
	//申请产品编号
	private String productCode;
	//申请产品小类
	private String smallProduct;
	//大棚面积
	private String greenhoustArea;
	//生产经营场所总建筑面积
	private String projectBuildArea;
	//拟购房屋套数
	private String wantHouseCount;
	//对外担保本金余额（家庭）
	private String faOutGuarAmount;
	//对外担保本金余额（征信）
	private String crOutGuarAmount;
	//与下游客户平均合作年限
	private String averateCooYear;
	//是否在固定日发放工资
	private String isFixedWages;
	//近三年经营收入
	private String last3yearOperateIncome;
	//近三年经营支出
	private String last3yearOperateOutcome;
	//经营项目应收账款
	private String projectShouldIncome;
	//经营项目负债
	private String projectDebt;
	//是否有征信报告
	private String isCredit;
	//信用卡数量
	private String creditCardCount;
	//征信最近6个月本息最大逾期期数
	private String crOverdueMaxCountIn6M;
	//征信最近24个月本息最大逾期期数
	private String crOverdueMaxCountIn24M;
	//行内最近6个月本息最大逾期期数
	private String overdueMaxCountIn6M;
	//行内最近24个月本息最大逾期期数
	private String overdueMaxCountIn24M;
	//近6个月（存款+理财）日均额
	private String dailyFinAndDepAmountIn6M;
	//近9个月（存款+理财）日均额
	private String dailyFinAndDepAmountIn9M;
	//近12个月（存款+理财）日均额
	private String dailyFinAndDepAmountIn12M;
	//最近12个月POS交易次数（夜间）
	private String posNightCountIn12M;
	//申请时点其他敞口个数
	private String otherExposureCount;
	//与银行交易时长
	private String transactionDuration;
	//行内最近12个月利息最大逾期期数
	private String rateOverdueMaxCountIn12M;
	//本行贷款余额
	private String ourBackLoanBal;
	//他行贷款余额
	private String otherBackLoanBal;
	//贷记卡已用额度
	private String cardUserLimit;
	//准贷记卡透支额度
	private String cardBeyondLimit;
	//存货数量
	private String stockCount;
	//近三年实际控制人变更情况
	private String actualControllerChangeIn3Y;
	//是否有银行卡
	private String isHaveBankCard;
	//有效授信额度
	private String effectCreditAmount;
	//是否有本行银行卡
	private String isHaveOurBankCard;
	//是否有本行存储帐户
	private String isHaveOurStorageAcc;
	//拟购房产类别
	private String wantHouseType;
	//征信最近12个月利息最大逾期期数
	private String crOverdueMaxCountIn12M;
	//打分卡映射逻辑申请额度
	private String applyLimit;
	//客户类型
	private String custType;
	//本行是否有利息逾期
	private String isHaveOverdue;
	//本行是否有本息逾期
	private String ourIsOverdueInAndC;
	//征信是否有本息逾期 
	private String creIsOverdueInAndC;
	//本行是否有本金逾期
	private String ourIsOverdueCap;
	//规则运算用申请额度
	private String applyMoney;
	//是否有固定工作
	private String ifFixedWork;
	
	public String getIfFixedWork() {
		return ifFixedWork;
	}
	public void setIfFixedWork(String ifFixedWork) {
		this.ifFixedWork = ifFixedWork;
	}
	public String getApplyMoney() {
		return applyMoney;
	}
	public void setApplyMoney(String applyMoney) {
		this.applyMoney = applyMoney;
	}
	public String getOurIsOverdueInAndC() {
		return ourIsOverdueInAndC;
	}
	public void setOurIsOverdueInAndC(String ourIsOverdueInAndC) {
		this.ourIsOverdueInAndC = ourIsOverdueInAndC;
	}
	public String getCreIsOverdueInAndC() {
		return creIsOverdueInAndC;
	}
	public void setCreIsOverdueInAndC(String creIsOverdueInAndC) {
		this.creIsOverdueInAndC = creIsOverdueInAndC;
	}
	public String getOurIsOverdueCap() {
		return ourIsOverdueCap;
	}
	public void setOurIsOverdueCap(String ourIsOverdueCap) {
		this.ourIsOverdueCap = ourIsOverdueCap;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getIdtype() {
		return idtype;
	}
	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getApplyLimit() {
		return applyLimit;
	}
	public void setApplyLimit(String applyLimit) {
		this.applyLimit = applyLimit;
	}
	public String getWantHouseType() {
		return wantHouseType;
	}
	public void setWantHouseType(String wantHouseType) {
		this.wantHouseType = wantHouseType;
	}
	public String getIsHaveOurBankCard() {
		return isHaveOurBankCard;
	}
	public void setIsHaveOurBankCard(String isHaveOurBankCard) {
		this.isHaveOurBankCard = isHaveOurBankCard;
	}
	public String getIsHaveOurStorageAcc() {
		return isHaveOurStorageAcc;
	}
	public void setIsHaveOurStorageAcc(String isHaveOurStorageAcc) {
		this.isHaveOurStorageAcc = isHaveOurStorageAcc;
	}
	public String getEffectCreditAmount() {
		return effectCreditAmount;
	}
	public void setEffectCreditAmount(String effectCreditAmount) {
		this.effectCreditAmount = effectCreditAmount;
	}
	public String getActualControllerChangeIn3Y() {
		return actualControllerChangeIn3Y;
	}
	public void setActualControllerChangeIn3Y(String actualControllerChangeIn3Y) {
		this.actualControllerChangeIn3Y = actualControllerChangeIn3Y;
	}
	public String getIsHaveBankCard() {
		return isHaveBankCard;
	}
	public void setIsHaveBankCard(String isHaveBankCard) {
		this.isHaveBankCard = isHaveBankCard;
	}
	public String getStockCount() {
		return stockCount;
	}
	public void setStockCount(String stockCount) {
		this.stockCount = stockCount;
	}
	public String getSmallProduct() {
		return smallProduct;
	}
	public void setSmallProduct(String smallProduct) {
		this.smallProduct = smallProduct;
	}
	public String getOurBackLoanBal() {
		return ourBackLoanBal;
	}
	public void setOurBackLoanBal(String ourBackLoanBal) {
		this.ourBackLoanBal = ourBackLoanBal;
	}
	public String getOtherBackLoanBal() {
		return otherBackLoanBal;
	}
	public void setOtherBackLoanBal(String otherBackLoanBal) {
		this.otherBackLoanBal = otherBackLoanBal;
	}
	public String getCardUserLimit() {
		return cardUserLimit;
	}
	public void setCardUserLimit(String cardUserLimit) {
		this.cardUserLimit = cardUserLimit;
	}
	public String getCardBeyondLimit() {
		return cardBeyondLimit;
	}
	public void setCardBeyondLimit(String cardBeyondLimit) {
		this.cardBeyondLimit = cardBeyondLimit;
	}
	public String getRateOverdueMaxCountIn12M() {
		return rateOverdueMaxCountIn12M;
	}
	public void setRateOverdueMaxCountIn12M(String rateOverdueMaxCountIn12M) {
		this.rateOverdueMaxCountIn12M = rateOverdueMaxCountIn12M;
	}
	public String getOtherExposureCount() {
		return otherExposureCount;
	}
	public void setOtherExposureCount(String otherExposureCount) {
		this.otherExposureCount = otherExposureCount;
	}
	public String getTransactionDuration() {
		return transactionDuration;
	}
	public void setTransactionDuration(String transactionDuration) {
		this.transactionDuration = transactionDuration;
	}
	public String getPosNightCountIn12M() {
		return posNightCountIn12M;
	}
	public void setPosNightCountIn12M(String posNightCountIn12M) {
		this.posNightCountIn12M = posNightCountIn12M;
	}
	public String getDailyFinAndDepAmountIn6M() {
		return dailyFinAndDepAmountIn6M;
	}
	public void setDailyFinAndDepAmountIn6M(String dailyFinAndDepAmountIn6M) {
		this.dailyFinAndDepAmountIn6M = dailyFinAndDepAmountIn6M;
	}
	public String getDailyFinAndDepAmountIn9M() {
		return dailyFinAndDepAmountIn9M;
	}
	public void setDailyFinAndDepAmountIn9M(String dailyFinAndDepAmountIn9M) {
		this.dailyFinAndDepAmountIn9M = dailyFinAndDepAmountIn9M;
	}
	public String getDailyFinAndDepAmountIn12M() {
		return dailyFinAndDepAmountIn12M;
	}
	public void setDailyFinAndDepAmountIn12M(String dailyFinAndDepAmountIn12M) {
		this.dailyFinAndDepAmountIn12M = dailyFinAndDepAmountIn12M;
	}
	public String getOverdueMaxCountIn6M() {
		return overdueMaxCountIn6M;
	}
	public void setOverdueMaxCountIn6M(String overdueMaxCountIn6M) {
		this.overdueMaxCountIn6M = overdueMaxCountIn6M;
	}
	public String getOverdueMaxCountIn24M() {
		return overdueMaxCountIn24M;
	}
	public void setOverdueMaxCountIn24M(String overdueMaxCountIn24M) {
		this.overdueMaxCountIn24M = overdueMaxCountIn24M;
	}
	public String getCrOverdueMaxCountIn6M() {
		return crOverdueMaxCountIn6M;
	}
	public void setCrOverdueMaxCountIn6M(String crOverdueMaxCountIn6M) {
		this.crOverdueMaxCountIn6M = crOverdueMaxCountIn6M;
	}
	
	public String getCrOverdueMaxCountIn24M() {
		return crOverdueMaxCountIn24M;
	}
	public void setCrOverdueMaxCountIn24M(String crOverdueMaxCountIn24M) {
		this.crOverdueMaxCountIn24M = crOverdueMaxCountIn24M;
	}
	public String getAverateCooYear() {
		return averateCooYear;
	}
	public void setAverateCooYear(String averateCooYear) {
		this.averateCooYear = averateCooYear;
	}
	public String getIsFixedWages() {
		return isFixedWages;
	}
	public void setIsFixedWages(String isFixedWages) {
		this.isFixedWages = isFixedWages;
	}
	public String getLast3yearOperateIncome() {
		return last3yearOperateIncome;
	}
	public void setLast3yearOperateIncome(String last3yearOperateIncome) {
		this.last3yearOperateIncome = last3yearOperateIncome;
	}
	public String getLast3yearOperateOutcome() {
		return last3yearOperateOutcome;
	}
	public void setLast3yearOperateOutcome(String last3yearOperateOutcome) {
		this.last3yearOperateOutcome = last3yearOperateOutcome;
	}
	public String getProjectShouldIncome() {
		return projectShouldIncome;
	}
	public void setProjectShouldIncome(String projectShouldIncome) {
		this.projectShouldIncome = projectShouldIncome;
	}
	public String getProjectDebt() {
		return projectDebt;
	}
	public void setProjectDebt(String projectDebt) {
		this.projectDebt = projectDebt;
	}
	public String getIsCredit() {
		return isCredit;
	}
	public void setIsCredit(String isCredit) {
		this.isCredit = isCredit;
	}
	public String getCreditCardCount() {
		return creditCardCount;
	}
	public void setCreditCardCount(String creditCardCount) {
		this.creditCardCount = creditCardCount;
	}
	public String getFaOutGuarAmount() {
		return faOutGuarAmount;
	}
	public void setFaOutGuarAmount(String faOutGuarAmount) {
		this.faOutGuarAmount = faOutGuarAmount;
	}
	public String getCrOutGuarAmount() {
		return crOutGuarAmount;
	}
	public void setCrOutGuarAmount(String crOutGuarAmount) {
		this.crOutGuarAmount = crOutGuarAmount;
	}
	public String getWantHouseCount() {
		return wantHouseCount;
	}
	public void setWantHouseCount(String wantHouseCount) {
		this.wantHouseCount = wantHouseCount;
	}
	public String getProjectBuildArea() {
		return projectBuildArea;
	}
	public void setProjectBuildArea(String projectBuildArea) {
		this.projectBuildArea = projectBuildArea;
	}
	public String getHavaChild() {
		return havaChild;
	}
	public String getGreenhoustArea() {
		return greenhoustArea;
	}
	public void setGreenhoustArea(String greenhoustArea) {
		this.greenhoustArea = greenhoustArea;
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
	public String getCustcode() {
		return custcode;
	}
	public void setCustcode(String custcode) {
		this.custcode = custcode;
	}
	public String getApplyAmount() {
		return applyAmount;
	}
	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getMarriageYear() {
		return marriageYear;
	}
	public void setMarriageYear(String marriageYear) {
		this.marriageYear = marriageYear;
	}
	public String isHavaChild() {
		return havaChild;
	}
	public void setHavaChild(String havaChild) {
		this.havaChild = havaChild;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getWorkYear() {
		return workYear;
	}
	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}
	public String getMiAmount() {
		return miAmount;
	}
	public void setMiAmount(String miAmount) {
		this.miAmount = miAmount;
	}
	public String getIsBuyGarage() {
		return isBuyGarage;
	}
	public void setIsBuyGarage(String isBuyGarage) {
		this.isBuyGarage = isBuyGarage;
	}
	public String getDownpayment() {
		return downpayment;
	}
	public void setDownpayment(String downpayment) {
		this.downpayment = downpayment;
	}
	public String getFamilyDebt() {
		return familyDebt;
	}
	public void setFamilyDebt(String familyDebt) {
		this.familyDebt = familyDebt;
	}
	public String getFamilyPossibleDebt() {
		return familyPossibleDebt;
	}
	public void setFamilyPossibleDebt(String familyPossibleDebt) {
		this.familyPossibleDebt = familyPossibleDebt;
	}
	public String getFamilyAsset() {
		return familyAsset;
	}
	public void setFamilyAsset(String familyAsset) {
		this.familyAsset = familyAsset;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	public String getFamilyIncomeThisYear() {
		return familyIncomeThisYear;
	}
	public void setFamilyIncomeThisYear(String familyIncomeThisYear) {
		this.familyIncomeThisYear = familyIncomeThisYear;
	}
	public String getFamilyIncomeLastYear() {
		return familyIncomeLastYear;
	}
	public void setFamilyIncomeLastYear(String familyIncomeLastYear) {
		this.familyIncomeLastYear = familyIncomeLastYear;
	}
	public String getFamilyIncomeBeforeLastYear() {
		return familyIncomeBeforeLastYear;
	}
	public void setFamilyIncomeBeforeLastYear(String familyIncomeBeforeLastYear) {
		this.familyIncomeBeforeLastYear = familyIncomeBeforeLastYear;
	}
	public String getFamilyExpendThisYear() {
		return familyExpendThisYear;
	}
	public void setFamilyExpendThisYear(String familyExpendThisYear) {
		this.familyExpendThisYear = familyExpendThisYear;
	}
	public String getFamilyExpendLatsYear() {
		return familyExpendLatsYear;
	}
	public void setFamilyExpendLatsYear(String familyExpendLatsYear) {
		this.familyExpendLatsYear = familyExpendLatsYear;
	}
	public String getFamilyExpendBeforeLastYear() {
		return familyExpendBeforeLastYear;
	}
	public void setFamilyExpendBeforeLastYear(String familyExpendBeforeLastYear) {
		this.familyExpendBeforeLastYear = familyExpendBeforeLastYear;
	}
	public String getMuSum() {
		return muSum;
	}
	public void setMuSum(String muSum) {
		this.muSum = muSum;
	}
	public String getPlantYear() {
		return plantYear;
	}
	public void setPlantYear(String plantYear) {
		this.plantYear = plantYear;
	}
	public String getIsBuyHouse() {
		return isBuyHouse;
	}
	public void setIsBuyHouse(String isBuyHouse) {
		this.isBuyHouse = isBuyHouse;
	}
	public String getProjectAssets() {
		return projectAssets;
	}
	public void setProjectAssets(String projectAssets) {
		this.projectAssets = projectAssets;
	}
	public String getCarCount() {
		return carCount;
	}
	public void setCarCount(String carCount) {
		this.carCount = carCount;
	}
	public String getProjectExpendLastYear() {
		return projectExpendLastYear;
	}
	public void setProjectExpendLastYear(String projectExpendLastYear) {
		this.projectExpendLastYear = projectExpendLastYear;
	}
	public String getProjectIncomeLastYear() {
		return projectIncomeLastYear;
	}
	public void setProjectIncomeLastYear(String projectIncomeLastYear) {
		this.projectIncomeLastYear = projectIncomeLastYear;
	}
	public String getProjectArea() {
		return projectArea;
	}
	public void setProjectArea(String projectArea) {
		this.projectArea = projectArea;
	}
	public String getInsuranceStaffCount() {
		return insuranceStaffCount;
	}
	public void setInsuranceStaffCount(String insuranceStaffCount) {
		this.insuranceStaffCount = insuranceStaffCount;
	}
	public String getStaffCount() {
		return staffCount;
	}
	public void setStaffCount(String staffCount) {
		this.staffCount = staffCount;
	}
	public String getOwnHouseCount() {
		return ownHouseCount;
	}
	public void setOwnHouseCount(String ownHouseCount) {
		this.ownHouseCount = ownHouseCount;
	}
	public String getPosConsumeCount() {
		return posConsumeCount;
	}
	public void setPosConsumeCount(String posConsumeCount) {
		this.posConsumeCount = posConsumeCount;
	}
	public String getATMPOSCountIn12M() {
		return ATMPOSCountIn12M;
	}
	public void setATMPOSCountIn12M(String aTMPOSCountIn12M) {
		ATMPOSCountIn12M = aTMPOSCountIn12M;
	}
	public String getDailyDepositAmountIn6M() {
		return dailyDepositAmountIn6M;
	}
	public void setDailyDepositAmountIn6M(String dailyDepositAmountIn6M) {
		this.dailyDepositAmountIn6M = dailyDepositAmountIn6M;
	}
	public String getDailyDepositAmountIn9M() {
		return dailyDepositAmountIn9M;
	}
	public void setDailyDepositAmountIn9M(String dailyDepositAmountIn9M) {
		this.dailyDepositAmountIn9M = dailyDepositAmountIn9M;
	}
	public String getDailyDepositAmountIn12M() {
		return dailyDepositAmountIn12M;
	}
	public void setDailyDepositAmountIn12M(String dailyDepositAmountIn12M) {
		this.dailyDepositAmountIn12M = dailyDepositAmountIn12M;
	}
	
	public String getRateOverdueMonthCount() {
		return rateOverdueMonthCount;
	}
	public void setRateOverdueMonthCount(String rateOverdueMonthCount) {
		this.rateOverdueMonthCount = rateOverdueMonthCount;
	}
	public String getMoneyOverdueMonthCount() {
		return moneyOverdueMonthCount;
	}
	public void setMoneyOverdueMonthCount(String moneyOverdueMonthCount) {
		this.moneyOverdueMonthCount = moneyOverdueMonthCount;
	}
	public String getOverdueCountIn6M() {
		return overdueCountIn6M;
	}
	public void setOverdueCountIn6M(String overdueCountIn6M) {
		this.overdueCountIn6M = overdueCountIn6M;
	}
	public String getOverdueCountIn12M() {
		return overdueCountIn12M;
	}
	public void setOverdueCountIn12M(String overdueCountIn12M) {
		this.overdueCountIn12M = overdueCountIn12M;
	}
	public String getOverdueCountIn24M() {
		return overdueCountIn24M;
	}
	public void setOverdueCountIn24M(String overdueCountIn24M) {
		this.overdueCountIn24M = overdueCountIn24M;
	}
	public String getOverdueCountIn36M() {
		return overdueCountIn36M;
	}
	public void setOverdueCountIn36M(String overdueCountIn36M) {
		this.overdueCountIn36M = overdueCountIn36M;
	}
	public String getCrQueryCountIn6M() {
		return crQueryCountIn6M;
	}
	public void setCrQueryCountIn6M(String crQueryCountIn6M) {
		this.crQueryCountIn6M = crQueryCountIn6M;
	}
	public String getCrQueryCountIn12M() {
		return crQueryCountIn12M;
	}
	public void setCrQueryCountIn12M(String crQueryCountIn12M) {
		this.crQueryCountIn12M = crQueryCountIn12M;
	}
	public String getCrQueryCountIn24M() {
		return crQueryCountIn24M;
	}
	public void setCrQueryCountIn24M(String crQueryCountIn24M) {
		this.crQueryCountIn24M = crQueryCountIn24M;
	}
	public String getCrQueryCountIn36M() {
		return crQueryCountIn36M;
	}
	public void setCrQueryCountIn36M(String crQueryCountIn36M) {
		this.crQueryCountIn36M = crQueryCountIn36M;
	}
	public String getCrOverdueCountIn6M() {
		return crOverdueCountIn6M;
	}
	public void setCrOverdueCountIn6M(String crOverdueCountIn6M) {
		this.crOverdueCountIn6M = crOverdueCountIn6M;
	}
	public String getCrOverdueCountIn12M() {
		return crOverdueCountIn12M;
	}
	public void setCrOverdueCountIn12M(String crOverdueCountIn12M) {
		this.crOverdueCountIn12M = crOverdueCountIn12M;
	}
	public String getCrOverdueCountI24M() {
		return crOverdueCountI24M;
	}
	public void setCrOverdueCountI24M(String crOverdueCountI24M) {
		this.crOverdueCountI24M = crOverdueCountI24M;
	}
	public String getCrOverdueCountIn36M() {
		return crOverdueCountIn36M;
	}
	public void setCrOverdueCountIn36M(String crOverdueCountIn36M) {
		this.crOverdueCountIn36M = crOverdueCountIn36M;
	}
	public String getCrOverdueMonthCount() {
		return crOverdueMonthCount;
	}
	public void setCrOverdueMonthCount(String crOverdueMonthCount) {
		this.crOverdueMonthCount = crOverdueMonthCount;
	}
	public String getCrCreditcardOpenCount() {
		return crCreditcardOpenCount;
	}
	public void setCrCreditcardOpenCount(String crCreditcardOpenCount) {
		this.crCreditcardOpenCount = crCreditcardOpenCount;
	}
	public String getCrEarly() {
		return crEarly;
	}
	public void setCrEarly(String crEarly) {
		this.crEarly = crEarly;
	}
	public String getCrLoanbalancesum() {
		return crLoanbalancesum;
	}
	public void setCrLoanbalancesum(String crLoanbalancesum) {
		this.crLoanbalancesum = crLoanbalancesum;
	}
	public String getCrCreditcardbalancesum() {
		return crCreditcardbalancesum;
	}
	public void setCrCreditcardbalancesum(String crCreditcardbalancesum) {
		this.crCreditcardbalancesum = crCreditcardbalancesum;
	}
	public String getCrQuasicreditcardbalancesum() {
		return crQuasicreditcardbalancesum;
	}
	public void setCrQuasicreditcardbalancesum(String crQuasicreditcardbalancesum) {
		this.crQuasicreditcardbalancesum = crQuasicreditcardbalancesum;
	}
	public String getCrFamily() {
		return crFamily;
	}
	public void setCrFamily(String crFamily) {
		this.crFamily = crFamily;
	}
	
	public String getAccountAcount() {
		return accountAcount;
	}
	public void setAccountAcount(String accountAcount) {
		this.accountAcount = accountAcount;
	}
	public String getExposureCount() {
		return exposureCount;
	}
	public void setExposureCount(String exposureCount) {
		this.exposureCount = exposureCount;
	}
	public String getLoanPayDate() {
		return loanPayDate;
	}
	public void setLoanPayDate(String loanPayDate) {
		this.loanPayDate = loanPayDate;
	}
	public String getUpOrDown() {
		return upOrDown;
	}
	public void setUpOrDown(String upOrDown) {
		this.upOrDown = upOrDown;
	}
	public String getOverdueMonthCount() {
		return overdueMonthCount;
	}
	public void setOverdueMonthCount(String overdueMonthCount) {
		this.overdueMonthCount = overdueMonthCount;
	}
	public String getIsHaveOverdue() {
		return isHaveOverdue;
	}
	public void setIsHaveOverdue(String isHaveOverdue) {
		this.isHaveOverdue = isHaveOverdue;
	}
	public String getCrOverdueMaxCountIn12M() {
		return crOverdueMaxCountIn12M;
	}
	public void setCrOverdueMaxCountIn12M(String crOverdueMaxCountIn12M) {
		this.crOverdueMaxCountIn12M = crOverdueMaxCountIn12M;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	
	
}
