package com.sdnx.st.sm.dao.impl;

import java.util.List;

import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.dao.StObjectPropertyDao;
import com.sdnx.st.sm.model.StObjectProperty;
import com.sdnx.st.sm.vo.StObjectPropertyVo;

public class StObjectPropertyDaoImpl extends AbstractBaseDao<StObjectProperty> implements StObjectPropertyDao{

	@Override
	public List<StObjectPropertyVo> queryPropertyAndDatatype(String objectcode) throws Exception {
		String sql = "select p.*,d.datatypecode,d.datatypename,d.isenum,d.dictcode from st_object_property p "
				   + "left join st_datatype d "
				   + "on p.datatypecode=d.datatypecode "
				   + "where p.objectcode='" + objectcode + "' order by p.id";
		List<StObjectPropertyVo> result = queryListBySql(sql, StObjectPropertyVo.class);
		return result;
	}

}
