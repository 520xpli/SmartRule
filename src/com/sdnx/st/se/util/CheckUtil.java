package com.sdnx.st.se.util;

import java.util.List;

import com.sdnx.st.constants.YesNo;

public class CheckUtil {
	
	/**
	 * �ж�str�Ƿ�ΪT
	 * @param str
	 * @return
	 */
	public static final boolean strIsYes(String str){
		return str != null && str.equals(YesNo.yes.getCode());
	}
	
	/**
	 * �ж�list�Ƿ�Ϊ��
	 * @param list
	 * @return
	 */
	public static final boolean listIsNull(List<?> list){
		return list == null || list.isEmpty();
	}
}
