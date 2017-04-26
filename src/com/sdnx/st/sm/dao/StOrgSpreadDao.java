package com.sdnx.st.sm.dao;

import java.util.List;

import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.model.StOrgSpread;
import com.sdnx.st.sm.vo.StOrgSpreadVo;

public interface StOrgSpreadDao extends BaseDao<StOrgSpread>{

	public List<StOrgSpreadVo> queryDot(List<String> lists) throws Exception;
	public void updateAll(List<StOrgSpread> lists);
	public List<StOrgSpreadVo> querySpreadOrgByDots(String dots) throws Exception;
}
