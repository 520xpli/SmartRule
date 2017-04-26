package com.sdnx.st.sm.service;

import java.util.List;

import com.sdnx.st.sm.base.BaseService;
import com.sdnx.st.sm.model.StProductSpread;

public interface StProductSpreadService extends BaseService<StProductSpread>{
	public StProductSpread queryById(String id) throws Exception;
	public List<StProductSpread> queryListByModel(StProductSpread stProductSpread) throws Exception;
	public List<StProductSpread> queryProductSmaByProBig(String orgcode,String productcode) throws Exception;
	public void updateById(StProductSpread stProductSpread) throws Exception;
}
