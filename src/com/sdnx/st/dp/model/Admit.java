package com.sdnx.st.dp.model;


public class Admit {
	//��������
	private String objType;
	//�ͻ����
	private String custCode;
	//������������
	private String applyTerm;
	//����
	private String age;
	//����״��
	private String marrige;
	//��ͥ���ʲ�
	private String familyAsset;
	//��ͥ�ܸ�ծ
	private String familyDebt;
	//������
	private String applyAmount;
	//��������Ƿ���ͬ
	private String isOrgSame;
	//�����ҵ�Ƿ���ڴ������
	private String isEtpHaveCredit;
	//�Ƿ���ڱ�������,ǷϢ
	private String isOverdue;
	//�ͻ�δ�����������弶������̬
	private String worstFiveClass;
	//�Ƿ���ڱ��ⲻ��
	private String isUnsettledOutSheetBad;
	//��ȥ�����Ƿ�����ѽ�����ⲻ��
	private String isSettledOutSheetBad;
	//�ͻ���12�����Ƿ����չ��
	private String isExend;
	//�ͻ��Ƿ���ڶ��ⵣ������δ���������Ϣ
	private String isGuaranteInSheetBad;
	//�ͻ��Ƿ���ڶ��ⵣ������δ���������Ϣ
	private String isGuaranteOutSheetBad;
	//�Ƿ��ⲿ����
	private String isOutList;
	//�Ƿ������
	private String isBlackList;
	//�Ƿ��й�����
	private String isFreeze;
	//��ǰ������
	private String crOverdueCount;
	//���е�ǰ����弶������̬
	private String crOtherWorstFiveClass;
	//���ⵣ���������
	private String crGuranteeAmount;
	//����˻�����
	private String crLoanOrgCount;
	//δ�������ÿ�����������
	private String crCreditCardOrgCount;
	//12�����ۼ���������
	private String crOverdueCountIn12M;
	//36����������߸����ڴ���
	private String crOverdueSerialIn36m;
	//��12���²�ѯ����
	private String crQueryCountIn12M;
	//�����Ʒ���
	private String productCode;
	//�ϲ�ҵ��Ʒ��
	private String smallProduct;
	//���ⵣ����������弶������̬
	private String crGuaranteWorstFiveClass;
	//δ�������ǿ���׼���ǿ����6����ƽ��ʹ����
	private String withoutCreditAndSemiLateUseRatioIn6M;
	//�Ƿ���������Ϣ
	private String isHaveCreditInfo;
	//�Ƿ������ͻ�
	private String isNewCust;
	//�ͻ�����
	private String custName;
	//���Ų�ѯ״̬
	private String creditStatus;
	//�Ա�
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
