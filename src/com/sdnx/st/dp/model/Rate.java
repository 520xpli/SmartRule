package com.sdnx.st.dp.model;

public class Rate {
	//��������
	private String objType;
	//�ͻ����
	private String custcode;
	//�ͻ�����
	private String custname;
	//֤������
	private String idtype;
	//֤������
	private String idnum;
	//������
	private String applyAmount;
	//��ҵ
	private String industry;
	//��������
	private String applyDate;
	//����״��
	private String marriage;
	//���Ǽ����
	private String marriageYear;
	//������Ů
	private String havaChild;
	//����
	private String age;
	//�Ա�
	private String gender;
	//ѧ��
	private String edu;
	//ְҵ
	private String profession;
	//����λ��ʼ���
	private String workYear;
	//ҽ�Ʊ��������������ܶ��ͥ�ܶ
	private String miAmount;
	//�Ƿ��򳵿�
	private String isBuyGarage;
	//�׸�����
	private String downpayment;
	//��ͥ�ܸ�ծ
	private String familyDebt;
	//��ͥ���и�ծ
	private String familyPossibleDebt;
	//��ͥ���ʲ�
	private String familyAsset;
	//����˼���ͥ��Ա�������
	private String workStatus;
	//����Ŀǰ��ͥ����
	private String familyIncomeThisYear;
	//ȥ���ͥ����
	private String familyIncomeLastYear;
	//ǰ���ͥ����
	private String familyIncomeBeforeLastYear;
	//����Ŀǰ��֧ͥ��
	private String familyExpendThisYear;
	//ȥ���ͥ��֧��
	private String familyExpendLatsYear;
	//ǰ���ͥ��֧��
	private String familyExpendBeforeLastYear;
	//��ֲĶ��
	private String muSum;
	//��ֳ/��ֲ/��Ӫ����
	private String plantYear;
	//�Ƿ��ڳ���������ס���÷�
	private String isBuyHouse;
	//��Ӫ��Ŀ�̶��ʲ�
	private String projectAssets;
	//Ӫ�˳�������
	private String carCount;
	//����Ⱦ�Ӫ֧��
	private String projectExpendLastYear;
	//����Ⱦ�Ӫ������
	private String projectIncomeLastYear;
	//������Ӫ���
	private String projectArea;
	//�����������˺����ջ��˱��յ�Ա������
	private String insuranceStaffCount;
	//ְ������
	private String staffCount;
	//���ɷ�������
	private String ownHouseCount;
	//pos���Ѵ���
	private String posConsumeCount;
	//���12����ATM��pos���״���֮��
	private String ATMPOSCountIn12M;
	//��6�����վ������
	private String dailyDepositAmountIn6M;
	//��9�����վ������
	private String dailyDepositAmountIn9M;
	//��12�����վ������
	private String dailyDepositAmountIn12M;
	//���һ����Ϣ���ھ������ڵ�����(����)
	private String rateOverdueMonthCount;
	//���һ�α������ھ������ڵ�����(����)
	private String moneyOverdueMonthCount;
	//�������6���±�Ϣ�����������
	private String overdueCountIn6M;
	//�������12���±�Ϣ�����������
	private String overdueCountIn12M;
	//�������24���±�Ϣ�����������
	private String overdueCountIn24M;
	//�������36���±�Ϣ�����������
	private String overdueCountIn36M;
	//�������һ�α�Ϣ���ھ������ڵ�����
	private String overdueMonthCount;
	//���6�������ű����ѯ����
	private String crQueryCountIn6M;
	//���12�������ű����ѯ����
	private String crQueryCountIn12M;
	//���24�������ű����ѯ����
	private String crQueryCountIn24M;
	//���36�������ű����ѯ����
	private String crQueryCountIn36M;
	//�������6���±�Ϣ�����������
	private String crOverdueCountIn6M;
	//�������12���±�Ϣ�����������
	private String crOverdueCountIn12M;
	//�������24���±�Ϣ�����������
	private String crOverdueCountI24M;
	//�������36���±�Ϣ�����������
	private String crOverdueCountIn36M;
	//�������һ�α�Ϣ���ھ������ڵ�����
	private String crOverdueMonthCount;
	//�������12���´��ǿ���������
	private String crCreditcardOpenCount;
	//��������ʻ��������
	private String crEarly;
	//����δ�������������
	private String crLoanbalancesum;
	//����δ�������ǿ�������
	private String crCreditcardbalancesum;
	//����δ����׼���ǿ�������
	private String crQuasicreditcardbalancesum;
	//���ż�ͥ���и�ծ
	private String crFamily;
	//����ʱ���ʻ�����δ���壩
	private String accountAcount;
	//��������ʱ�㳨����
	private String exposureCount;
	//���һ�δ������
	private String loanPayDate;
	//��������½���
	private String upOrDown;
	//�����Ʒ���
	private String productCode;
	//�����ƷС��
	private String smallProduct;
	//�������
	private String greenhoustArea;
	//������Ӫ�����ܽ������
	private String projectBuildArea;
	//�⹺��������
	private String wantHouseCount;
	//���ⵣ����������ͥ��
	private String faOutGuarAmount;
	//���ⵣ�����������ţ�
	private String crOutGuarAmount;
	//�����οͻ�ƽ����������
	private String averateCooYear;
	//�Ƿ��ڹ̶��շ��Ź���
	private String isFixedWages;
	//�����꾭Ӫ����
	private String last3yearOperateIncome;
	//�����꾭Ӫ֧��
	private String last3yearOperateOutcome;
	//��Ӫ��ĿӦ���˿�
	private String projectShouldIncome;
	//��Ӫ��Ŀ��ծ
	private String projectDebt;
	//�Ƿ������ű���
	private String isCredit;
	//���ÿ�����
	private String creditCardCount;
	//�������6���±�Ϣ�����������
	private String crOverdueMaxCountIn6M;
	//�������24���±�Ϣ�����������
	private String crOverdueMaxCountIn24M;
	//�������6���±�Ϣ�����������
	private String overdueMaxCountIn6M;
	//�������24���±�Ϣ�����������
	private String overdueMaxCountIn24M;
	//��6���£����+��ƣ��վ���
	private String dailyFinAndDepAmountIn6M;
	//��9���£����+��ƣ��վ���
	private String dailyFinAndDepAmountIn9M;
	//��12���£����+��ƣ��վ���
	private String dailyFinAndDepAmountIn12M;
	//���12����POS���״�����ҹ�䣩
	private String posNightCountIn12M;
	//����ʱ���������ڸ���
	private String otherExposureCount;
	//�����н���ʱ��
	private String transactionDuration;
	//�������12������Ϣ�����������
	private String rateOverdueMaxCountIn12M;
	//���д������
	private String ourBackLoanBal;
	//���д������
	private String otherBackLoanBal;
	//���ǿ����ö��
	private String cardUserLimit;
	//׼���ǿ�͸֧���
	private String cardBeyondLimit;
	//�������
	private String stockCount;
	//������ʵ�ʿ����˱�����
	private String actualControllerChangeIn3Y;
	//�Ƿ������п�
	private String isHaveBankCard;
	//��Ч���Ŷ��
	private String effectCreditAmount;
	//�Ƿ��б������п�
	private String isHaveOurBankCard;
	//�Ƿ��б��д洢�ʻ�
	private String isHaveOurStorageAcc;
	//�⹺�������
	private String wantHouseType;
	//�������12������Ϣ�����������
	private String crOverdueMaxCountIn12M;
	//��ֿ�ӳ���߼�������
	private String applyLimit;
	//�ͻ�����
	private String custType;
	//�����Ƿ�����Ϣ����
	private String isHaveOverdue;
	//�����Ƿ��б�Ϣ����
	private String ourIsOverdueInAndC;
	//�����Ƿ��б�Ϣ���� 
	private String creIsOverdueInAndC;
	//�����Ƿ��б�������
	private String ourIsOverdueCap;
	//����������������
	private String applyMoney;
	//�Ƿ��й̶�����
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
