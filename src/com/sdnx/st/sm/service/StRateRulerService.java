package com.sdnx.st.sm.service;

import java.util.List;

import com.sdnx.st.sm.base.BaseService;
import com.sdnx.st.sm.model.StRateRuler;
import com.sdnx.st.sm.vo.StRateRulerVo;

public interface StRateRulerService extends BaseService<StRateRuler>{

	public List<StRateRulerVo> queryListByModel(StRateRuler stRateRuler) throws Exception;
}
