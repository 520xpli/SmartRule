package com.sdnx.st.sm.service;

import java.util.List;

import com.sdnx.st.sm.base.BaseService;
import com.sdnx.st.sm.model.StOrgSpread;
import com.sdnx.st.sm.vo.StOrgSpreadVo;

public interface StOrgSpreadService extends BaseService<StOrgSpread>{
	public List<StOrgSpread> queryListByModel(StOrgSpread stOrgSpread) throws Exception;
	public List<StOrgSpreadVo> queryDot(List<String> lists) throws Exception;
	public void updateAll(List<StOrgSpread> lists);
}
