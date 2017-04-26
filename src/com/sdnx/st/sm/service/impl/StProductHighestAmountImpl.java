package com.sdnx.st.sm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sdnx.st.sm.base.AbstractBaseService;
import com.sdnx.st.sm.dao.StProductHighestAmountDao;
import com.sdnx.st.sm.dao.StProductSpreadDao;
import com.sdnx.st.sm.model.StProductHighestAmount;
import com.sdnx.st.sm.model.StProductSpread;
import com.sdnx.st.sm.service.StProductHighestAmountService;
import com.sdnx.st.sm.service.StProductSpreadService;
import com.sdnx.st.sm.utils.PPRetMsg;
import com.sdnx.st.sm.vo.StProductSpreadVo;
import com.sdnx.st.util.StDaoFactory;

public class StProductHighestAmountImpl extends AbstractBaseService<StProductHighestAmount> implements StProductHighestAmountService{
	private StProductHighestAmountDao sh = StDaoFactory.getProductHighestAmountDao();
	
	public StProductHighestAmount queryById(String id) throws Exception {
		StProductHighestAmount st = (StProductHighestAmount)sh.queryById(id, StProductHighestAmount.class, "ST_PRODUCT_HIGHESTAMOUNT");
		return st;
	}
	
	public List<StProductHighestAmount> queryListByModel(StProductHighestAmount stProductHighestAmount) throws Exception {
		List<StProductHighestAmount> lists = sh.queryListByModel(stProductHighestAmount, StProductHighestAmount.class);
		return lists;
	}
	public void updateById(StProductHighestAmount stProductHighestAmount) throws Exception{
		sh.updateById(stProductHighestAmount);
	}
	/**
	 * ��ѯ��ɫ��Ʒ(��Ʒ����޶�)
	 */
	public List<StProductHighestAmount> queryProductHighestSmaByProBig(String orgcode, String productcode) throws Exception{
		//�����������÷���
		List<PPRetMsg> list = queryProB(productcode,orgcode);
		//�½�һ���µļ��� 
		List<StProductHighestAmount> lists = new ArrayList<StProductHighestAmount>();
		//�õ���ѯ������ɫ��Ʒ�Ĳ�Ʒ��
		int size = list.size();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				PPRetMsg msg = list.get(i);
				StProductHighestAmount sps = new StProductHighestAmount();
				sps.setProductcode(msg.getCode());
				sps.setOrgcode(orgcode);
				sps.setProductmiddlecode(productcode);
				List<StProductHighestAmount> stPros = queryListByModel(sps);
				
				
				StProductHighestAmount pv = new StProductHighestAmount();
				pv.setProductcode(msg.getCode());
				pv.setProductname(msg.getName());
				if(stPros.size()>0){
					pv.setId(stPros.get(0).getId());
					pv.setLimitamount(stPros.get(0).getLimitamount());
					lists.add(pv);
				}else{
					lists.add(pv);
				}
			}
		}
		return lists;
	}
	/**
	 * ��ѯ��ɫ��Ʒ
	 * @param tcode  ��Ʒ���
	 * @param groupId  �������
	 * @param busiCode ҵ��Ʒ��
	 * @return
	 */
	public List<PPRetMsg> queryProB(String pcode,String groupId){
	/*	Map<String, String> map = new HashMap<String, String>();
		//ҵ����
		map.put("tCode", PPConstant.T_CODE_TS_BUSI_TYPE);
		//�������
		map.put("groupId", groupId);
		//��Ʒ���
		map.put(PPConstant.DIMENSION_BUSI_CODE, pcode);
		//���������ϲ������Ӧ����ɫ��Ʒ
		map.put(PPConstant.IS_FIND_BEFORE_GROUP, PPConstant.IS_FIND_BEFORE_GROUP_0);		
		List<PPRetMsg> list = RuleProcessService.execute(map);
		return list;*/
		return new ArrayList<>();
		
	}
	/*public static void main(String[] arg0){
		Map<String, String> map = new HashMap<String, String>();
		map.put("tCode", PPConstant.T_CODE_TS_BUSI_TYPE);
		map.put("groupId", "02000A");
		map.put(PPConstant.DIMENSION_BUSI_CODE, "");
		List<PPRetMsg> list = RuleProcessService.execute(map);
		int size = list.size();
		System.out.println(size);
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				PPRetMsg msg = list.get(i);
				System.out.println(msg.getCode() + "--" + msg.getName());
			}
		}

	}*/

}
