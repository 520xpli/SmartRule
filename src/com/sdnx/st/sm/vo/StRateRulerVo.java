package com.sdnx.st.sm.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StRateRulerVo {
		//id
		private String id;
		//������
		private String createusercode;
		//����ʱ��
		private Date createtime;
		//��������
		private String ratecode;
		//��������
		private Integer rateup;
		//��������
		private Integer ratedown;
		//�Ƿ���Ч
		private String iseffect;
		//����ʱ��
		private Date endtime;
		//������ʱ��
		private Date lastopertime;
		private List<StRateRulerVo> rateList = new ArrayDetailList();
		
		// �����б����ݵ��ύ
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
