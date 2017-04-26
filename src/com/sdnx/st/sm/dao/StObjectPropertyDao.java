package com.sdnx.st.sm.dao;

import java.util.List;

import com.sdnx.st.sm.base.BaseDao;
import com.sdnx.st.sm.model.StObjectProperty;
import com.sdnx.st.sm.vo.StObjectPropertyVo;

public interface StObjectPropertyDao extends BaseDao<StObjectProperty>{

	List<StObjectPropertyVo> queryPropertyAndDatatype(String objectcode) throws Exception;

}
