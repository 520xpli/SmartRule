package com.sdnx.st.sm.action;

import java.util.ArrayList;
import java.util.List;

import com.sdnx.st.sm.base.BaseForm;
import com.sdnx.st.sm.vo.RateShowVo;
import com.sdnx.st.sm.vo.StModelVo;
import com.sdnx.st.sm.vo.StOrgSpreadVo;
import com.sdnx.st.sm.vo.StRateResultVo;
import com.sdnx.st.sm.vo.StRateresultDataVo;
import com.sdnx.st.sm.vo.StStructrueVo;

public class StSystemMaintainForm extends BaseForm {
	//�Ƿ��˻���
	private String isleagal;
	//�Ƿ���л���
	private String isCity;
	//�Ƿ�����
	private String isDot;
	//�ύ����
	private String dotOrgcode;
	//����ǰ̨����
	private String dotOrg;
	//��������(���У����ˣ�����)
	private String orgType;
	// ������Ϣ
	private StModelVo stModelVo = new StModelVo();
	private StModelVo stModelVoR = new StModelVo();
	private String operAtt;
	private List<StModelVo> ModelVoList = new ArrayList<StModelVo>();
	// ������Ϣ
	private List<StOrgSpreadVo> orgSpreadVoList = new ArrayList<StOrgSpreadVo>();
	private StOrgSpreadVo stOrgSpreadVo = new StOrgSpreadVo();
	// ��������
	private String orgcodeJ;
	private String orgcodeR;
	private int orgnum;
	// �ж��Ƿ�ɱ༭(�ɱ༭true�����ɱ༭false)
	private String showtext;
	// ���ڴ���ie����
	private Long currentTime;
	//���ڵ�ǰ��½��ԱΪ���˻���ʱ�ķ��˻�������
	private String legalOrgCode;
	//���ڵ�ǰ��½��ԱΪ���˻���ʱ�ķ��˻�������
	private String legalOrgName;
	//����url
	private String backUrl;
	//�ͻ���������
	private String custmanagername;
	//�����������
	private StRateresultDataVo stRateresultDataVo = new StRateresultDataVo();
	private List<StRateresultDataVo> stRateresultDataList = new ArrayList<StRateresultDataVo>();
	//�������
	private StRateResultVo stRateResultVo = new StRateResultVo();
	private List<StRateResultVo> stRateResultVoList = new ArrayList<StRateResultVo>();
	//����������
	private String dateFrom;
	//��������ֹ
	private String dateTo;
	//��������
	private String ratepara;
	//�������ݲ���
	private String rateDataPara;
	//����������ʾ��
	private RateShowVo rsv = new RateShowVo();
	//�ͻ��������
	private String custMP;
	//�ͻ�����
	private String custname;
	private List<RateShowVo> rsList = new ArrayList<RateShowVo>();
	//������ѯ������Ϣ
	private List<StStructrueVo> rateList;
	//������ѯ�����Ϣ
	private List<StStructrueVo> amountList;
	//�Ƿ���ʾ����
	private String isShowCode = "true";

	public String getIsShowCode() {
		return isShowCode;
	}

	public void setIsShowCode(String isShowCode) {
		this.isShowCode = isShowCode;
	}

	public List<StStructrueVo> getRateList() {
		return rateList;
	}

	public void setRateList(List<StStructrueVo> rateList) {
		this.rateList = rateList;
	}

	public List<StStructrueVo> getAmountList() {
		return amountList;
	}

	public void setAmountList(List<StStructrueVo> amountList) {
		this.amountList = amountList;
	}

	public List<RateShowVo> getRsList() {
		return rsList;
	}

	public void setRsList(List<RateShowVo> rsList) {
		this.rsList = rsList;
	}

	public RateShowVo getRsv() {
		return rsv;
	}

	public void setRsv(RateShowVo rsv) {
		this.rsv = rsv;
	}

	public String getRatepara() {
		return ratepara;
	}

	public void setRatepara(String ratepara) {
		this.ratepara = ratepara;
	}

	public String getRateDataPara() {
		return rateDataPara;
	}

	public void setRateDataPara(String rateDataPara) {
		this.rateDataPara = rateDataPara;
	}

	public List<StRateresultDataVo> getStRateresultDataList() {
		return stRateresultDataList;
	}

	public void setStRateresultDataList(List<StRateresultDataVo> stRateresultDataList) {
		this.stRateresultDataList = stRateresultDataList;
	}
	public StRateresultDataVo getStRateresultDataVo() {
		return stRateresultDataVo;
	}

	public void setStRateresultDataVo(StRateresultDataVo stRateresultDataVo) {
		this.stRateresultDataVo = stRateresultDataVo;
	}

	public StRateResultVo getStRateResultVo() {
		return stRateResultVo;
	}

	public void setStRateResultVo(StRateResultVo stRateResultVo) {
		this.stRateResultVo = stRateResultVo;
	}
	public List<StRateResultVo> getStRateResultVoList() {
		return stRateResultVoList;
	}

	public void setStRateResultVoList(List<StRateResultVo> stRateResultVoList) {
		this.stRateResultVoList = stRateResultVoList;
	}
	
	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getLegalOrgCode() {
		return legalOrgCode;
	}

	public void setLegalOrgCode(String legalOrgCode) {
		this.legalOrgCode = legalOrgCode;
	}

	public String getLegalOrgName() {
		return legalOrgName;
	}

	public void setLegalOrgName(String legalOrgName) {
		this.legalOrgName = legalOrgName;
	}

	public Long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}

	public String getOperAtt() {
		return operAtt;
	}

	public void setOperAtt(String operAtt) {
		this.operAtt = operAtt;
	}

	public StModelVo getStModelVo() {
		return stModelVo;
	}

	public void setStModelVo(StModelVo stModelVo) {
		this.stModelVo = stModelVo;
	}

	public List<StModelVo> getModelVoList() {
		return ModelVoList;
	}

	public void setModelVoList(List<StModelVo> modelVoList) {
		ModelVoList = modelVoList;
	}

	public List<StOrgSpreadVo> getOrgSpreadVoList() {
		return orgSpreadVoList;
	}

	public void setOrgSpreadVoList(List<StOrgSpreadVo> orgSpreadVoList) {
		this.orgSpreadVoList = orgSpreadVoList;
	}

	public StOrgSpreadVo getStOrgSpreadVo() {
		return stOrgSpreadVo;
	}

	public void setStOrgSpreadVo(StOrgSpreadVo stOrgSpreadVo) {
		this.stOrgSpreadVo = stOrgSpreadVo;
	}

	public int getOrgnum() {
		return orgnum;
	}

	public void setOrgnum(int orgnum) {
		this.orgnum = orgnum;
	}

	public String getOrgcodeJ() {
		return orgcodeJ;
	}

	public void setOrgcodeJ(String orgcodeJ) {
		this.orgcodeJ = orgcodeJ;
	}

	public String getOrgcodeR() {
		return orgcodeR;
	}

	public void setOrgcodeR(String orgcodeR) {
		this.orgcodeR = orgcodeR;
	}

	public StModelVo getStModelVoR() {
		return stModelVoR;
	}

	public void setStModelVoR(StModelVo stModelVoR) {
		this.stModelVoR = stModelVoR;
	}

	public String getShowtext() {
		return showtext;
	}

	public void setShowtext(String showtext) {
		this.showtext = showtext;
	}

	public String getIsleagal() {
		return isleagal;
	}

	public void setIsleagal(String isleagal) {
		this.isleagal = isleagal;
	}

	public String getDotOrgcode() {
		return dotOrgcode;
	}

	public void setDotOrgcode(String dotOrgcode) {
		this.dotOrgcode = dotOrgcode;
	}

	public String getDotOrg() {
		return dotOrg;
	}

	public void setDotOrg(String dotOrg) {
		this.dotOrg = dotOrg;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getIsCity() {
		return isCity;
	}

	public void setIsCity(String isCity) {
		this.isCity = isCity;
	}

	public String getIsDot() {
		return isDot;
	}

	public void setIsDot(String isDot) {
		this.isDot = isDot;
	}

	public String getCustmanagername() {
		return custmanagername;
	}

	public void setCustmanagername(String custmanagername) {
		this.custmanagername = custmanagername;
	}

	public String getCustMP() {
		return custMP;
	}

	public void setCustMP(String custMP) {
		this.custMP = custMP;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

}
