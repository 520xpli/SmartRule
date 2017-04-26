package com.sdnx.st.sm.service.impl;

import java.util.List;

import com.sdnx.st.sm.base.AbstractBaseService;
import com.sdnx.st.sm.dao.StOrgSpreadDao;
import com.sdnx.st.sm.model.StOrgSpread;
import com.sdnx.st.sm.service.StOrgSpreadService;
import com.sdnx.st.sm.vo.StOrgSpreadVo;
import com.sdnx.st.util.StDaoFactory;

public class StOrgSpreadServiceImpl extends AbstractBaseService<StOrgSpread> implements StOrgSpreadService{
	private StOrgSpreadDao stOrgDao = StDaoFactory.getOrgSpreadDao();

	public List<StOrgSpread> queryListByModel(StOrgSpread stOrgSpread) throws Exception {
		return stOrgDao.queryListByModel(stOrgSpread,StOrgSpread.class );
	}
	public List<StOrgSpreadVo> queryDot(List<String> lists) throws Exception{
		return stOrgDao.queryDot(lists);
	}
	public void updateAll(List<StOrgSpread> lists){
		stOrgDao.updateAll(lists);
	}
		
}
