package com.sdnx.st.sm.dao;

import java.util.List;

import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.model.StRow;
import com.sdnx.st.sm.vo.StRowVo;

public interface StRowDao extends BaseDao<StRow>{

	void deleteByDetailCode(String detailcode) throws Exception;
	List<StRowVo> queryBydetailCode(String detailCode) throws Exception;

}
