package com.sdnx.st.sm.service;

import java.util.List;

import com.sdnx.st.sm.base.BaseService;
import com.sdnx.st.sm.model.StLimitamountParm;
import com.sdnx.st.sm.vo.StLimitamountParmVo;

public interface StLimitamountService extends BaseService<StLimitamountParm>{

	public List<StLimitamountParmVo> queryListByModel(StLimitamountParm stLimitamountParm) throws Exception;
	public List<StLimitamountParmVo> queryUnRate() throws Exception;
	public List<StLimitamountParmVo> queryEepandRatio(String guar) throws Exception;
	public List<StLimitamountParmVo> query(StLimitamountParm stLimitamountParm) throws Exception;
}
