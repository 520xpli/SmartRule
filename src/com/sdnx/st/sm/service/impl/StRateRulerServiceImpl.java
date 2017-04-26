package com.sdnx.st.sm.service.impl;

import java.util.List;

import com.sdnx.st.sm.base.AbstractBaseService;
import com.sdnx.st.sm.dao.StRateRulerDao;
import com.sdnx.st.sm.model.StRateRuler;
import com.sdnx.st.sm.service.StRateRulerService;
import com.sdnx.st.sm.vo.StRateRulerVo;
import com.sdnx.st.util.StDaoFactory;

public class StRateRulerServiceImpl extends AbstractBaseService<StRateRuler> implements StRateRulerService{

	private StRateRulerDao rateDao = StDaoFactory.getRateRulerDao();

	public List<StRateRulerVo> queryListByModel(StRateRuler stRateRuler) throws Exception {
		return rateDao.queryListByModel(stRateRuler, StRateRulerVo.class);
	}
}
