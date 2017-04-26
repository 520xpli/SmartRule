package com.sdnx.st.sm.action;

import java.util.ArrayList;
import java.util.List;

import com.sdnx.st.sm.base.BaseForm;
import com.sdnx.st.sm.vo.StLimitamountParmVo;
import com.sdnx.st.sm.vo.StOrgSpreadVo;
import com.sdnx.st.sm.vo.StProductHighestAmountVo;
import com.sdnx.st.sm.vo.StProductSpreadVo;

public class StLimitAmountParaMaintainForm extends BaseForm{

	private String operAtt;
	private String isquery;
	//�����޹صĶ���
	private StLimitamountParmVo limitAmountVo = new StLimitamountParmVo();
	//����Ѻ����
	private StLimitamountParmVo limitAmountVoColl = new StLimitamountParmVo();
	private StLimitamountParmVo limitAmountVoCollExpand = new StLimitamountParmVo();
	//��֤�����
	private StLimitamountParmVo limitAmountVoGua = new StLimitamountParmVo();
	private StLimitamountParmVo limitAmountVoGuaExpand = new StLimitamountParmVo();
	//���������
	private StLimitamountParmVo limitAmountVoRate = new StLimitamountParmVo();
	private StLimitamountParmVo limitAmountVoRateExpand = new StLimitamountParmVo();
	
	public StLimitamountParmVo getLimitAmountVoCollExpand() {
		return limitAmountVoCollExpand;
	}
	public void setLimitAmountVoCollExpand(StLimitamountParmVo limitAmountVoCollExpand) {
		this.limitAmountVoCollExpand = limitAmountVoCollExpand;
	}
	public StLimitamountParmVo getLimitAmountVoGuaExpand() {
		return limitAmountVoGuaExpand;
	}
	public void setLimitAmountVoGuaExpand(StLimitamountParmVo limitAmountVoGuaExpand) {
		this.limitAmountVoGuaExpand = limitAmountVoGuaExpand;
	}
	public StLimitamountParmVo getLimitAmountVoRateExpand() {
		return limitAmountVoRateExpand;
	}
	public void setLimitAmountVoRateExpand(StLimitamountParmVo limitAmountVoRateExpand) {
		this.limitAmountVoRateExpand = limitAmountVoRateExpand;
	}
	public StLimitamountParmVo getLimitAmountVoRate() {
		return limitAmountVoRate;
	}
	public void setLimitAmountVoRate(StLimitamountParmVo limitAmountVoRate) {
		this.limitAmountVoRate = limitAmountVoRate;
	}
	//��Ʒ����
	private String productcentercode;
	//��Ȳ������� 
	private List<StLimitamountParmVo> limitAmountVoList = new ArrayList<StLimitamountParmVo>();
	//�������󼯺�
	private List<StOrgSpreadVo> orgVoList = new ArrayList<StOrgSpreadVo>();
	//��Ʒ���󼯺�
	private List<StProductSpreadVo> productVoList = new ArrayList<StProductSpreadVo>();
	private StProductSpreadVo proVo = new StProductSpreadVo();
	private List<StProductSpreadVo> proList= new ArrayList<StProductSpreadVo>();
	private List<StProductHighestAmountVo> saveList = new ArrayDetailList();
	//��Ʒ����޶����
	private List<StProductHighestAmountVo> productHAVoList = new ArrayList<StProductHighestAmountVo>();
	private StProductHighestAmountVo proHAVo = new StProductHighestAmountVo();
	//���˻�������
	private String orgcode;
	public List<StProductHighestAmountVo> getProductHAVoList() {
		return productHAVoList;
	}
	public void setProductHAVoList(List<StProductHighestAmountVo> productHAVoList) {
		this.productHAVoList = productHAVoList;
	}
	public StProductHighestAmountVo getProHAVo() {
		return proHAVo;
	}
	public void setProHAVo(StProductHighestAmountVo proHAVo) {
		this.proHAVo = proHAVo;
	}
	//���л�������
	private String regionOrgCode;
	//��Ʒ����
	private String productcode;
	
	// �����б����ݵ��ύ
	private class ArrayDetailList extends ArrayList<StProductHighestAmountVo> {
		public StProductHighestAmountVo get(int index) {
			if (index >= super.size()) {
				int length = index - super.size() + 1;
				for (int i = 0; i < length; i++) {
					super.add(new StProductHighestAmountVo());
				}
			}
			return super.get(index);
		}
	}
	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getProductcode() {
		return productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}

	//������
	private int orgnum;
	public String getOperAtt() {
		return operAtt;
	}

	public void setOperAtt(String operAtt) {
		this.operAtt = operAtt;
	}

	public StLimitamountParmVo getLimitAmountVo() {
		return limitAmountVo;
	}

	public void setLimitAmountVo(StLimitamountParmVo limitAmountVo) {
		this.limitAmountVo = limitAmountVo;
	}

	public List<StLimitamountParmVo> getLimitAmountVoList() {
		return limitAmountVoList;
	}

	public void setLimitAmountVoList(List<StLimitamountParmVo> limitAmountVoList) {
		this.limitAmountVoList = limitAmountVoList;
	}

	public int getOrgnum() {
		return orgnum;
	}

	public void setOrgnum(int orgnum) {
		this.orgnum = orgnum;
	}

	public List<StOrgSpreadVo> getOrgVoList() {
		return orgVoList;
	}

	public void setOrgVoList(List<StOrgSpreadVo> orgVoList) {
		this.orgVoList = orgVoList;
	}

	public List<StProductSpreadVo> getProductVoList() {
		return productVoList;
	}

	public void setProductVoList(List<StProductSpreadVo> productVoList) {
		this.productVoList = productVoList;
	}
	public String getProductcentercode() {
		return productcentercode;
	}
	public void setProductcentercode(String productcentercode) {
		this.productcentercode = productcentercode;
	}
	public String getRegionOrgCode() {
		return regionOrgCode;
	}
	public void setRegionOrgCode(String regionOrgCode) {
		this.regionOrgCode = regionOrgCode;
	}
	public String getIsquery() {
		return isquery;
	}
	public void setIsquery(String isquery) {
		this.isquery = isquery;
	}
	public List<StProductSpreadVo> getProList() {
		return proList;
	}
	public void setProList(List<StProductSpreadVo> proList) {
		this.proList = proList;
	}
	public StProductSpreadVo getProVo() {
		return proVo;
	}
	public void setProVo(StProductSpreadVo proVo) {
		this.proVo = proVo;
	}
	public StLimitamountParmVo getLimitAmountVoGua() {
		return limitAmountVoGua;
	}
	public void setLimitAmountVoGua(StLimitamountParmVo limitAmountVoGua) {
		this.limitAmountVoGua = limitAmountVoGua;
	}
	public StLimitamountParmVo getLimitAmountVoColl() {
		return limitAmountVoColl;
	}
	public void setLimitAmountVoColl(StLimitamountParmVo limitAmountVoColl) {
		this.limitAmountVoColl = limitAmountVoColl;
	}
	public List<StProductHighestAmountVo> getSaveList() {
		return saveList;
	}
	public void setSaveList(List<StProductHighestAmountVo> saveList) {
		this.saveList = saveList;
	}
}
