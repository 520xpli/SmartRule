package com.sdnx.st.sm.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StRateRulerVo {
		//id
		private String id;
		//创建人
		private String createusercode;
		//创建时间
		private Date createtime;
		//评级编码
		private String ratecode;
		//评级上限
		private Integer rateup;
		//评级下限
		private Integer ratedown;
		//是否生效
		private String iseffect;
		//结束时间
		private Date endtime;
		//最后操作时间
		private Date lastopertime;
		private List<StRateRulerVo> rateList = new ArrayDetailList();
		
		// 用于列表数据的提交
		private class ArrayDetailList extends ArrayList<StRateRulerVo> {
			public StRateRulerVo get(int index) {
				if (index >= super.size()) {
					int length = index - super.size() + 1;
					for (int i = 0; i < length; i++) {
						super.add(new StRateRulerVo());
					}
				}
				return super.get(index);
			}
		}
		public String getId() {
			return id;
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
		public String getRatecode() {
			return ratecode;
		}
		public void setRatecode(String ratecode) {
			this.ratecode = ratecode;
		}
		public Integer getRateup() {
			return rateup;
		}
		public void setRateup(Integer rateup) {
			this.rateup = rateup;
		}
		public Integer getRatedown() {
			return ratedown;
		}
		public void setRatedown(Integer ratedown) {
			this.ratedown = ratedown;
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
		public List<StRateRulerVo> getRateList() {
			return rateList;
		}
		public void setRateList(List<StRateRulerVo> rateList) {
			this.rateList = rateList;
		}
		
}
