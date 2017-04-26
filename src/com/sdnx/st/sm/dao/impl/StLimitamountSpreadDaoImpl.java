package com.sdnx.st.sm.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.dao.StLimitamountSpreadDao;
import com.sdnx.st.sm.model.StLimitamountParm;
import com.sdnx.st.sm.vo.StLimitamountParmVo;

public class StLimitamountSpreadDaoImpl extends AbstractBaseDao<StLimitamountParm> implements StLimitamountSpreadDao {
	private static Logger logger = Logger.getLogger(AbstractBaseDao.class);

	public List<StLimitamountParmVo> queryUnRate() throws Exception {
		// ������ѯ���
		String sql = "select * from ST_LIMITAMOUNT_PARM where unRatefamilynetassetratio is not null and iseffect=1";
		List<StLimitamountParmVo> lists = queryListBySql(sql, StLimitamountParmVo.class);
		return lists;
	}

	public List<StLimitamountParmVo> queryEepandRatio(String guar) throws Exception {
		// ������ѯ���
		String sql = "select * from ST_LIMITAMOUNT_PARM where expandratio is not null and iseffect=1"
				+ " and GUARANTEETYPE='" + guar + "'";
		List<StLimitamountParmVo> lists = queryListBySql(sql, StLimitamountParmVo.class);
		return lists;
	}
	// ��ѯ��������ؼ�ͥ���ʲ�ϵ��
	@Override
	public List<StLimitamountParmVo> queryfamilyRate(String grade, String guar) throws Exception {
		String sql = "select * from ST_LIMITAMOUNT_PARM where RATERALATEFAMILYNETASSETRATIO is not null and iseffect=1"
				+ " and gradecode='" + grade + "' and GUARANTEETYPE='" + guar + "'";
		List<StLimitamountParmVo> lists = queryListBySql(sql, StLimitamountParmVo.class);
		return lists;
	}
	
	//��ѯϵ��
	public List<StLimitamountParmVo> query(StLimitamountParm stLimitamountParm) throws Exception {
		String sql = "select * from  ST_LIMITAMOUNT_PARM  where expandratio is  null and iseffect="+stLimitamountParm.getIseffect()+" and guaranteetype='"+stLimitamountParm.getGuaranteetype()+"'";
		List<StLimitamountParmVo> lists = queryListBySql(sql, StLimitamountParmVo.class);
		return lists;
	}

	// ��ѯ��ͥ������ϵ��
	@Override
	public List<StLimitamountParmVo> queryfamilyIncomeRate(String grade, String guar) throws Exception {
		String sql = "select * from ST_LIMITAMOUNT_PARM where FAMILYNETINRATIO is not null and iseffect=1"
				+ " and gradecode='" + grade + "' and GUARANTEETYPE='" + guar + "'";
		List<StLimitamountParmVo> lists = queryListBySql(sql, StLimitamountParmVo.class);
		return lists;
	}

	// ��ѯ����Ѻϵ��
	@Override
	public List<StLimitamountParmVo> queryCollRate(String grade, String guar) throws Exception {
		String sql = "select * from ST_LIMITAMOUNT_PARM where COLLATERALRATIO is not null and iseffect=1"
				+ " and gradecode='" + grade + "' and GUARANTEETYPE='" + guar + "'";
		List<StLimitamountParmVo> lists = queryListBySql(sql, StLimitamountParmVo.class);
		return lists;
	}

	@Override
	public List<StLimitamountParmVo> queryExpand(String guaranteeType) throws Exception {
		String sql = "select * from st_limitamount_parm where expandratio is not null and guaranteetype='" + guaranteeType + "'";
		return queryListBySql(sql, StLimitamountParmVo.class);
	}
}
