package com.sdnx.st.sm.model;

import java.util.Date;

import com.sdnx.st.sm.base.BaseModel;

public class StLogInputdataHi extends BaseModel{
	//���
		private String id;
		//�������ݱ���
		private String inputdatacode;
		//�������
		private String objectcode;
		//����������
		private String propertykey;
		//��������ֵ
		private String propertyvalue;
		//������ʱ��
		private Date lastopertime;
		//���б��
		private String instcitycode;
		
		public String getInstcitycode() {
			return instcitycode;
		}
		public void setInstcitycode(String instcitycode) {
			this.instcitycode = instcitycode;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getInputdatacode() {
			return inputdatacode;
		}
		public void setInputdatacode(String inputdatacode) {
			this.inputdatacode = inputdatacode;
		}
		public String getObjectcode() {
			return objectcode;
		}
		public void setObjectcode(String objectcode) {
			this.objectcode = objectcode;
		}

		public String getPropertykey() {
			return propertykey;
		}
		public void setPropertykey(String propertykey) {
			this.propertykey = propertykey;
		}
		public String getPropertyvalue() {
			return propertyvalue;
		}
		public void setPropertyvalue(String propertyvalue) {
			this.propertyvalue = propertyvalue;
		}
		public Date getLastopertime() {
			return lastopertime;
		}
		public void setLastopertime(Date lastopertime) {
			this.lastopertime = lastopertime;
		}
}
