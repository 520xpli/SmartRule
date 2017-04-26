package com.sdnx.st.sm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sdnx.st.sm.base.AbstractBaseService;
import com.sdnx.st.sm.dao.StProductSpreadDao;
import com.sdnx.st.sm.model.StProductSpread;
import com.sdnx.st.sm.service.StProductSpreadService;
import com.sdnx.st.sm.utils.PPRetMsg;
import com.sdnx.st.util.StDaoFactory;

public class StProductSpreadImpl extends AbstractBaseService<StProductSpread> implements StProductSpreadService{
	private StProductSpreadDao sd = StDaoFactory.getProductSpreadDao();
	
	public StProductSpread queryById(String id) throws Exception {
		StProductSpread st = (StProductSpread)sd.queryById(id, StProductSpread.class, "ST_PRODUCT_SPREAD");
		return st;
	}
	
	public List<StProductSpread> queryListByModel(StProductSpread stProductSpread) throws Exception {
		
		return sd.queryListByModel(stProductSpread, StProductSpread.class);
	}
	public void updateById(StProductSpread stProductSpread) throws Exception{
		sd.updateById(stProductSpread);
	}
	/**
	 * ��ѯ��ɫ��Ʒ
	 */
	public List<StProductSpread> queryProductSmaByProBig(String orgcode, String productcode) throws Exception{
		//�����������÷���
		List<PPRetMsg> list = queryProB(productcode,orgcode);
		//�½�һ���µļ��� 
		List<StProductSpread> lists = new ArrayList<StProductSpread>();
		//�õ���ѯ������ɫ��Ʒ�Ĳ�Ʒ��
		int size = list.size();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				PPRetMsg msg = list.get(i);
				StProductSpread sps = new StProductSpread();
				sps.setProductcode(msg.getCode());
				sps.setOrgcode(orgcode);
				sps.setProductcentercode(productcode);
				List<StProductSpread> stPros = queryListByModel(sps);
				StProductSpread pv = new StProductSpread();
				pv.setProductcode(msg.getCode());
				pv.setProductname(msg.getName());
				if(stPros.size()>0){
					pv.setId(stPros.get(0).getId());
					pv.setLimitamount(stPros.get(0).getLimitamount());
					pv.setIsopen(stPros.get(0).getIsopen());
					lists.add(pv);
				}else{
					pv.setIsopen("0");
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
		/*Map<String, String> map = new HashMap<String, String>();
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
