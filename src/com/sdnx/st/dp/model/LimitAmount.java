package com.sdnx.st.dp.model;

public class LimitAmount {
	//对象类型
	private String objType;
	//最高额度
	private String maxAmount;
	//申请额度
	private String applyAmount;
	//已占用额度
	private String OccupyAmount;
	//实际资金需求额度
	private String needAmount;
	//自由资金投入
	private String ownFundInvest;
	//自由资金投入系数
	private String ownFundInvestRadio;
	//家庭年收入
	private String familyIncome;
	//家庭年收入系数
	private String familyIncomeRadio;
	//家庭总资产
	private String familyAsset;
	//家庭总负债
	private String familyDebt;
	//家庭年支出
	private String familyExpend;
	//与评级无关家庭净资产系数
	private String familyNetAssetRadio;
	//与评级相关家庭净资产系数
	private String RfamilyNetAssetRadio;
	//抵质押押额度
	private String collateralAmount;
	//推荐额度
	private String recommendAmount;
	//贷款期限
	private String loanTimeLimit;
	//其他银行负债
	private String otherBankDebt;
	//申请产品编号
	private String productCode;
	//是否二手房
	private String isSecondhandHouse;
	//拟购房产类别
	private String houseType;
	//是否取得产权证
	private String isGetCertificate;
	//拟购房屋建筑面积
	private String houseArea;
	//拟购房屋套数
	private String houseCount;
	//业务品种（上层业务种类）
	private String smallProduct;
	//内部评级结果
	private String grade;
	//家庭年净收入系数
	private String famliyNetIncomeRatio;
	//核定额度
	private String checkLimit;
	//抵质押系数
	private String collateralRatio;
	//担保方式
	private String guaranteeType;
	//评分卡最高额度
	private String RateMaxAmount;
	//产品最高限额
	private String productMaxAmount;
	//扩张系数
	private String expandRatio;
	//首付比例
	private String paymentRatio;
	//总房价
	private String housePrice;
	
	public String getPaymentRatio() {
		return paymentRatio;
	}
	public void setPaymentRatio(String paymentRatio) {
		this.paymentRatio = paymentRatio;
	}
	public String getHousePrice() {
		return housePrice;
	}
	public void setHousePrice(String housePrice) {
		this.housePrice = housePrice;
	}
	public String getExpandRatio() {
		return expandRatio;
	}
	public void setExpandRatio(String expandRatio) {
		this.expandRatio = expandRatio;
	}
	public String getProductMaxAmount() {
		return productMaxAmount;
	}
	public void setProductMaxAmount(String productMaxAmount) {
		this.productMaxAmount = productMaxAmount;
	}
	public String getRateMaxAmount() {
		return RateMaxAmount;
	}
	public void setRateMaxAmount(String rateMaxAmount) {
		this.RateMaxAmount = rateMaxAmount;
	}
	public String getGuaranteeType() {
		return guaranteeType;
	}
	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}
	public String getCollateralRatio() {
		return collateralRatio;
	}
	public void setCollateralRatio(String collateralRatio) {
		this.collateralRatio = collateralRatio;
	}
	public String getRfamilyNetAssetRadio() {
		return RfamilyNetAssetRadio;
	}
	public void setRfamilyNetAssetRadio(String rfamilyNetAssetRadio) {
		RfamilyNetAssetRadio = rfamilyNetAssetRadio;
	}
	public String getCheckLimit() {
		return checkLimit;
	}
	public void setCheckLimit(String checkLimit) {
		this.checkLimit = checkLimit;
	}
	public String getFamliyNetIncomeRatio() {
		return famliyNetIncomeRatio;
	}
	public void setFamliyNetIncomeRatio(String famliyNetIncomeRatio) {
		this.famliyNetIncomeRatio = famliyNetIncomeRatio;
	}
	
	public String getSmallProduct() {
		return smallProduct;
	}
	public void setSmallProduct(String smallProduct) {
		this.smallProduct = smallProduct;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getIsSecondhandHouse() {
		return isSecondhandHouse;
	}
	public void setIsSecondhandHouse(String isSecondhandHouse) {
		this.isSecondhandHouse = isSecondhandHouse;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getIsGetCertificate() {
		return isGetCertificate;
	}
	public void setIsGetCertificate(String isGetCertificate) {
		this.isGetCertificate = isGetCertificate;
	}
	public String getHouseArea() {
		return houseArea;
	}
	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}
	public String getHouseCount() {
		return houseCount;
	}
	public void setHouseCount(String houseCount) {
		this.houseCount = houseCount;
	}
	public String getObjType() {
		return objType;
	}
	public void setObjType(String objType) {
		this.objType = objType;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getFamilyDebt() {
		return familyDebt;
	}
	public void setFamilyDebt(String familyDebt) {
		this.familyDebt = familyDebt;
	}
	public String getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
	}
	public String getApplyAmount() {
		return applyAmount;
	}
	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}
	public String getOccupyAmount() {
		return OccupyAmount;
	}
	public void setOccupyAmount(String occupyAmount) {
		OccupyAmount = occupyAmount;
	}
	public String getNeedAmount() {
		return needAmount;
	}
	public void setNeedAmount(String needAmount) {
		this.needAmount = needAmount;
	}
	public String getOwnFundInvest() {
		return ownFundInvest;
	}
	public void setOwnFundInvest(String ownFundInvest) {
		this.ownFundInvest = ownFundInvest;
	}
	public String getOwnFundInvestRadio() {
		return ownFundInvestRadio;
	}
	public void setOwnFundInvestRadio(String ownFundInvestRadio) {
		this.ownFundInvestRadio = ownFundInvestRadio;
	}
	public String getFamilyIncome() {
		return familyIncome;
	}
	public void setFamilyIncome(String familyIncome) {
		this.familyIncome = familyIncome;
	}
	public String getFamilyIncomeRadio() {
		return familyIncomeRadio;
	}
	public void setFamilyIncomeRadio(String familyIncomeRadio) {
		this.familyIncomeRadio = familyIncomeRadio;
	}
	public String getFamilyAsset() {
		return familyAsset;
	}
	public void setFamilyAsset(String familyAsset) {
		this.familyAsset = familyAsset;
	}
	public String getFamilyExpend() {
		return familyExpend;
	}
	public void setFamilyExpend(String familyExpend) {
		this.familyExpend = familyExpend;
	}
	public String getFamilyNetAssetRadio() {
		return familyNetAssetRadio;
	}
	public void setFamilyNetAssetRadio(String familyNetAssetRadio) {
		this.familyNetAssetRadio = familyNetAssetRadio;
	}
	public String getCollateralAmount() {
		return collateralAmount;
	}
	public void setCollateralAmount(String collateralAmount) {
		this.collateralAmount = collateralAmount;
	}
	public String getRecommendAmount() {
		return recommendAmount;
	}
	public void setRecommendAmount(String recommendAmount) {
		this.recommendAmount = recommendAmount;
	}
	public String getLoanTimeLimit() {
		return loanTimeLimit;
	}
	public void setLoanTimeLimit(String loanTimeLimit) {
		this.loanTimeLimit = loanTimeLimit;
	}
	public String getOtherBankDebt() {
		return otherBankDebt;
	}
	public void setOtherBankDebt(String otherBankDebt) {
		this.otherBankDebt = otherBankDebt;
	}
	
	
}
