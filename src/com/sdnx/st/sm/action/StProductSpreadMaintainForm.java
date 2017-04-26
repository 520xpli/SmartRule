 package com.sdnx.st.sm.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sdnx.st.sm.base.BaseForm;
import com.sdnx.st.sm.vo.StOrgSpreadVo;
import com.sdnx.st.sm.vo.StProductSpreadVo;

public class StProductSpreadMaintainForm extends BaseForm{
	    /**
	 * 
	 */
	private static final Long serialVersionUID = 1L;
		//���
		private String id;
		//�����˱���
		private String createusercode;
		//����ʱ��
		private Date createtime;
		//��Ʒ����
		private String productcode;
		//��Ʒ����
		private String productname;
		//���
		private BigDecimal limitamount;
		//�Ƿ���
		private String isopen;
		//�Ƿ���Ч
		private String iseffect;
		//����ʱ��
		private Date endtime;
		//�������
		private String orgcode;
		//������ʱ��
		private Date lastopertime;
		//�������� 
		private String orgname;
		//��������
		private StOrgSpreadVo stOrgspreadVo = new StOrgSpreadVo();
		//��Ʒ����
		private String productcentercode;
		private String operAtt;
		private StProductSpreadVo stProductSpreadVo = new StProductSpreadVo();
		private List<StProductSpreadVo> stProductList = new ArrayList<StProductSpreadVo>();
		private String backUrl;
		public String getId() {
			return id;
		}
		public String getOperAtt() {
			return operAtt;
		}
		public void setOperAtt(String operAtt) {
			this.operAtt = operAtt;
		}
		public StProductSpreadVo getStProductSpreadVo() {
			return stProductSpreadVo;
		}
		public void setStProductSpreadVo(StProductSpreadVo stProductSpreadVo) {
			this.stProductSpreadVo = stProductSpreadVo;
		}
		public List<StProductSpreadVo> getStProductList() {
			return stProductList;
		}
		public void setStProductList(List<StProductSpreadVo> stProductList) {
			this.stProductList = stProductList;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCreateusercode() {
			return createusercode;
		}
		public void setCreateusercode(String createusercode) {
			this.createusercode = createusercode;
		}
		public Date getCreatetime() {
			return createtime;
		}
		public void setCreatetime(Date createtime) {
			this.createtime = createtime;
		}
		public String getProductcode() {
			return productcode;
		}
		public void setProductcode(String productcode) {
			this.productcode = productcode;
		}
		public String getProductname() {
			return productname;
		}
		public void setProductname(String productname) {
			this.productname = productname;
		}
		public BigDecimal getLimitamount() {
			return limitamount;
		}
		public void setLimitamount(BigDecimal limitamount) {
			this.limitamount = limitamount;
		}
		public String getIsopen() {
			return isopen;
		}
		public void setIsopen(String isopen) {
			this.isopen = isopen;
		}
		public String getIseffect() {
			return iseffect;
		}
		public void setIseffect(String iseffect) {
			this.iseffect = iseffect;
		}
		public Date getEndtime() {
			return endtime;
		}
		public void setEndtime(Date endtime) {
			this.endtime = endtime;
		}
		public Date getLastopertime() {
			return lastopertime;
		}
		public void setLastopertime(Date lastopertime) {
			this.lastopertime = lastopertime;
		}
		public String getOrgcode() {
			return orgcode;
		}
		public void setOrgcode(String orgcode) {
			this.orgcode = orgcode;
		}
		public String getProductcentercode() {
			return productcentercode;
		}
		public void setProductcentercode(String productcentercode) {
			this.productcentercode = productcentercode;
		}
		public String getOrgname() {
			return orgname;
		}
		public void setOrgname(String orgname) {
			this.orgname = orgname;
		}
		public StOrgSpreadVo getStOrgspreadVo() {
			return stOrgspreadVo;
		}
		public void setStOrgspreadVo(StOrgSpreadVo stOrgspreadVo) {
			this.stOrgspreadVo = stOrgspreadVo;
		}
		public String getBackUrl() {
			return backUrl;
		}
		public void setBackUrl(String backUrl) {
			this.backUrl = backUrl;
		}
}
