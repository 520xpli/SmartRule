package com.sdnx.st.sm.action;

import java.util.ArrayList;
import java.util.List;

import com.sdnx.st.sm.vo.StRuleDetailVo;

public class TestObject {
	private String objectCode;

	public String getObjectCode() {
		return objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}
	private List<TestProperty> propertyList = new ArrayDetailList();
	
	public List<TestProperty> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<TestProperty> propertyList) {
		this.propertyList = propertyList;
	}

	private class ArrayDetailList extends ArrayList<TestProperty> {
		public TestProperty get(int index) {
			if (index >= super.size()) {
				int length = index - super.size() + 1;
				for (int i = 0; i < length; i++) {
					super.add(new TestProperty());
				}
			}
			return super.get(index);
		}
	}
}
