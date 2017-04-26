package com.sdnx.st.dp.model;

public class AprovalProcess {
	//对象类型
	private String objType;
	//客户编号
	private String custCode;
	//流程标识
	private String processId;
	//申请机构
	private String applyOrg;
	//贷款产品
	private String productCode;
	//申请额度
	private String applyAmount;
	//客户授信总额
	private String custCreditAmount;
	//分支岗位
	private String branchPost;
	//担保方式
	private String collaterguaranteeType;
	//产品中类
	private String smallProduct;
	//客户类型
	private String custType;
	//授信类型（是否新增授信）
	private String ifNewCust;
	//业务品种小类
	private String littleProduct;
	//机构单户限额
	private String orgSingleLimit;
	//押品类型
	private String keyType;
	//期限
	private String limit;
	//浮动比例
	private String floatRate;
	
	
	public String getLittleProduct() {
		return littleProduct;
	}
	public void setLittleProduct(String littleProduct) {
		this.littleProduct = littleProduct;
	}
	public String getOrgSingleLimit() {
		return orgSingleLimit;
	}
	public void setOrgSingleLimit(String orgSingleLimit) {
		this.orgSingleLimit = orgSingleLimit;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getFloatRate() {
		return floatRate;
	}
	public void setFloatRate(String floatRate) {
		this.floatRate = floatRate;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getIfNewCust() {
		return ifNewCust;
	}
	public void setIfNewCust(String ifNewCust) {
		this.ifNewCust = ifNewCust;
	}
	public String getSmallProduct() {
		return smallProduct;
	}
	public void setSmallProduct(String smallProduct) {
		this.smallProduct = smallProduct;
	}
	public String getCollaterguaranteeType() {
		return collaterguaranteeType;
	}
	public void setCollaterguaranteeType(String collaterguaranteeType) {
		this.collaterguaranteeType = collaterguaranteeType;
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
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getApplyOrg() {
		return applyOrg;
	}
	public void setApplyOrg(String applyOrg) {
		this.applyOrg = applyOrg;
	}
	public String getApplyAmount() {
		return applyAmount;
	}
	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}
	public String getCustCreditAmount() {
		return custCreditAmount;
	}
	public void setCustCreditAmount(String custCreditAmount) {
		this.custCreditAmount = custCreditAmount;
	}
	public String getBranchPost() {
		return branchPost;
	}
	public void setBranchPost(String branchPost) {
		this.branchPost = branchPost;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	
}
