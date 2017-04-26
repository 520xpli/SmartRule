package com.sdnx.st.sm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.sdnx.st.constants.YesNo;
import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.dao.StRateRulerDao;
import com.sdnx.st.sm.model.StRateRuler;
import com.sdnx.st.sm.vo.StRateRulerVo;
import com.sdnx.st.util.STConnectionManager;
import com.sdnx.st.util.STDBManager;

public class StRateRulerDaoImpl extends AbstractBaseDao<StRateRuler> implements StRateRulerDao{

	@Override
	public String getRateByScore(String score) throws Exception {
		if(score == null || score.equals("")) return null;
		String sql = "select * from st_rate_ruler where ("
				+ score + " < rateup or rateup is null) and "
				+ score + " >= ratedown and iseffect='" + YesNo.yes.getCode() + "'";
		List<StRateRulerVo> list = queryListBySql(sql, StRateRulerVo.class);
		if(list.size() == 0) return null;
		return list.get(list.size() - 1).getRatecode();
	}

}
