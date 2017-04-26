package com.sdnx.st.sm.service.impl;

import java.util.List;

import com.sdnx.st.sm.base.AbstractBaseService;
import com.sdnx.st.sm.dao.StLimitamountSpreadDao;
import com.sdnx.st.sm.model.StLimitamountParm;
import com.sdnx.st.sm.service.StLimitamountService;
import com.sdnx.st.sm.vo.StLimitamountParmVo;
import com.sdnx.st.util.StDaoFactory;

public class StLimitamountServiceImpl extends AbstractBaseService<StLimitamountParm> implements StLimitamountService{
	
	private StLimitamountSpreadDao limitDao = StDaoFactory.getLimitamountDao();
	public List<StLimitamountParmVo> queryListByModel(StLimitamountParm stLimitamountParm) throws Exception {
		return limitDao.queryListByModel(stLimitamountParm, StLimitamountParmVo.class);
	}
	public List<StLimitamountParmVo> queryUnRate() throws Exception {
		return limitDao.queryUnRate();
	}
	public List<StLimitamountParmVo> queryEepandRatio(String guar) throws Exception{
		return limitDao.queryEepandRatio(guar);
	}
	@Override
	public List<StLimitamountParmVo> query(StLimitamountParm stLimitamountParm) throws Exception {
		return limitDao.query(stLimitamountParm);
	}

}
