package com.sdnx.st.se.util;

import java.util.List;

import com.sdnx.st.constants.YesNo;

public class CheckUtil {
	
	/**
	 * ÅĞ¶ÏstrÊÇ·ñÎªT
	 * @param str
	 * @return
	 */
	public static final boolean strIsYes(String str){
		return str != null && str.equals(YesNo.yes.getCode());
	}
	
	/**
	 * ÅĞ¶ÏlistÊÇ·ñÎª¿Õ
	 * @param list
	 * @return
	 */
	public static final boolean listIsNull(List<?> list){
		return list == null || list.isEmpty();
	}
}
