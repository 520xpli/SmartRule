package com.sdnx.st.sm.base;

import org.apache.struts.action.ActionForm;

public class BaseForm extends ActionForm{

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 7250984000017741219L;
	
	/**
	 * 分页数据
	 */
	private PageListData footer = new PageListData();

	public PageListData getFooter() {
		return footer;
	}

	public void setFooter(PageListData footer) {
		this.footer = footer;
	}
	
}
