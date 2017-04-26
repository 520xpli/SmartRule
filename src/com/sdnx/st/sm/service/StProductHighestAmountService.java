package com.sdnx.st.sm.service;

import java.util.List;

import com.sdnx.st.sm.base.BaseService;
import com.sdnx.st.sm.model.StProductHighestAmount;

public interface StProductHighestAmountService extends BaseService<StProductHighestAmount>{
	public StProductHighestAmount queryById(String id) throws Exception;
	public List<StProductHighestAmount> queryListByModel(StProductHighestAmount stProductHighestAmount) throws Exception;
	public List<StProductHighestAmount> queryProductHighestSmaByProBig(String orgcode, String productcode) throws Exception;
	public void updateById(StProductHighestAmount stProductHighestAmount) throws Exception;
}
