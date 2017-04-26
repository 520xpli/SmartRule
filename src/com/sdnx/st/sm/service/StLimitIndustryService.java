package com.sdnx.st.sm.service;

import javax.servlet.http.HttpServletRequest;

import com.sdnx.st.sm.base.BaseService;
import com.sdnx.st.sm.model.StLimitIndustry;

public interface StLimitIndustryService extends BaseService<StLimitIndustry>{

	String queryAllTree();

	String queryLimitIndustryTree();

	String delete(String code);

	String add(String code, HttpServletRequest request);

}
