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
		//编号
		private String id;
		//创建人编码
		private String createusercode;
		//创建时间
		private Date createtime;
		//产品编码
		private String productcode;
		//产品名称
		private String productname;
		//额度
		private BigDecimal limitamount;
		//是否开启
		private String isopen;
		//是否生效
		private String iseffect;
		//结束时间
		private Date endtime;
		//机构编号
		private String orgcode;
		//最后操作时间
		private Date lastopertime;
		//机构名称 
		private String orgname;
		//机构对象
		private StOrgSpreadVo stOrgspreadVo = new StOrgSpreadVo();
		//产品中类
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
