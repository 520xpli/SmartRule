package com.sdnx.st.dp.model;

public class LimitAmount {
	//��������
	private String objType;
	//��߶��
	private String maxAmount;
	//������
	private String applyAmount;
	//��ռ�ö��
	private String OccupyAmount;
	//ʵ���ʽ�������
	private String needAmount;
	//�����ʽ�Ͷ��
	private String ownFundInvest;
	//�����ʽ�Ͷ��ϵ��
	private String ownFundInvestRadio;
	//��ͥ������
	private String familyIncome;
	//��ͥ������ϵ��
	private String familyIncomeRadio;
	//��ͥ���ʲ�
	private String familyAsset;
	//��ͥ�ܸ�ծ
	private String familyDebt;
	//��ͥ��֧��
	private String familyExpend;
	//�������޹ؼ�ͥ���ʲ�ϵ��
	private String familyNetAssetRadio;
	//��������ؼ�ͥ���ʲ�ϵ��
	private String RfamilyNetAssetRadio;
	//����ѺѺ���
	private String collateralAmount;
	//�Ƽ����
	private String recommendAmount;
	//��������
	private String loanTimeLimit;
	//�������и�ծ
	private String otherBankDebt;
	//�����Ʒ���
	private String productCode;
	//�Ƿ���ַ�
	private String isSecondhandHouse;
	//�⹺�������
	private String houseType;
	//�Ƿ�ȡ�ò�Ȩ֤
	private String isGetCertificate;
	//�⹺���ݽ������
	private String houseArea;
	//�⹺��������
	private String houseCount;
	//ҵ��Ʒ�֣��ϲ�ҵ�����ࣩ
	private String smallProduct;
	//�ڲ��������
	private String grade;
	//��ͥ�꾻����ϵ��
	private String famliyNetIncomeRatio;
	//�˶����
	private String checkLimit;
	//����Ѻϵ��
	private String collateralRatio;
	//������ʽ
	private String guaranteeType;
	//���ֿ���߶��
	private String RateMaxAmount;
	//��Ʒ����޶�
	private String productMaxAmount;
	//����ϵ��
	private String expandRatio;
	//�׸�����
	private String paymentRatio;
	//�ܷ���
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
