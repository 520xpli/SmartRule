package com.sdnx.st.sm.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sdnx.st.sm.base.BaseForm;
import com.sdnx.st.sm.vo.StOrgSpreadVo;
import com.sdnx.st.sm.vo.StProductSpreadVo;

public class StOrgSpreadMaintainForm extends BaseForm{
		//���
		private Long id;
		//�����˱���
		private String createusercode;
		//����ʱ��
		private Date createtime;
		//��������
		private String orgcode;
		//��������
		private String orgname;
		//�Ƿ���
		private String isopen;
		//�Ƿ���Ч
		private String iseffect;
		//����ʱ��
		private Date endtime;
		//������ʱ��
		private Date lastopertime;
		private String operAtt;
		private int orgNum;
		private StOrgSpreadVo stOrgSpreadVo = new StOrgSpreadVo();
		private List<StOrgSpreadVo> stOrgList = new ArrayList<StOrgSpreadVo>(); 
		private List<StProductSpreadVo> stProductList = new ArrayList<StProductSpreadVo>();
		//��ǰҳ���ַ
		private String backUrl;
		//����ǰ̨������ѯ����
		private String orgCon;
		public String getOperAtt() {
			return operAtt;
		}
		public void setOperAtt(String operAtt) {
			this.operAtt = operAtt;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
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
		public String getOrgcode() {
			return orgcode;
		}
		public void setOrgcode(String orgcode) {
			this.orgcode = orgcode;
		}
		public String getOrgname() {
			return orgname;
		}
		public void setOrgname(String orgname) {
			this.orgname = orgname;
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
		public StOrgSpreadVo getStOrgSpreadVo() {
			return stOrgSpreadVo;
		}
		public void setStOrgSpreadVo(StOrgSpreadVo stOrgSpreadVo) {
			this.stOrgSpreadVo = stOrgSpreadVo;
		}
		public List<StOrgSpreadVo> getStOrgList() {
			return stOrgList;
		}
		public void setStOrgList(List<StOrgSpreadVo> stOrgList) {
			this.stOrgList = stOrgList;
		}
		public List<StProductSpreadVo> getStProductList() {
			return stProductList;
		}
		public void setStProductList(List<StProductSpreadVo> stProductList) {
			this.stProductList = stProductList;
		}
		public int getOrgNum() {
			return orgNum;
		}
		public void setOrgNum(int orgNum) {
			this.orgNum = orgNum;
		}
		public String getBackUrl() {
			return backUrl;
		}
		public void setBackUrl(String backUrl) {
			this.backUrl = backUrl;
		}
		public String getOrgCon() {
			return orgCon;
		}
		public void setOrgCon(String orgCon) {
			this.orgCon = orgCon;
		}
}
