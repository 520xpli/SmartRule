package com.sdnx.st.sm.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sdnx.st.constants.RowType;
import com.sdnx.st.constants.RuleClass;
import com.sdnx.st.constants.RuleState;
import com.sdnx.st.constants.YesNo;
import com.sdnx.st.se.init.StInit;
import com.sdnx.st.sm.action.StRuleForm;
import com.sdnx.st.sm.base.AbstractBaseService;
import com.sdnx.st.sm.dao.StModelDao;
import com.sdnx.st.sm.dao.StObjectDao;
import com.sdnx.st.sm.dao.StObjectPropertyDao;
import com.sdnx.st.sm.dao.StProductSpreadDao;
import com.sdnx.st.sm.dao.StRowDao;
import com.sdnx.st.sm.dao.StRuleDao;
import com.sdnx.st.sm.dao.StRuleDetailDao;
import com.sdnx.st.sm.dao.StStructrueDao;
import com.sdnx.st.sm.model.StModel;
import com.sdnx.st.sm.model.StObject;
import com.sdnx.st.sm.model.StObjectProperty;
import com.sdnx.st.sm.model.StProductSpread;
import com.sdnx.st.sm.model.StRow;
import com.sdnx.st.sm.model.StRule;
import com.sdnx.st.sm.model.StRuleDetail;
import com.sdnx.st.sm.model.StStructrue;
import com.sdnx.st.sm.service.StRuleMaintainService;
import com.sdnx.st.sm.utils.GroupDao;
import com.sdnx.st.sm.utils.PPRetMsg;
import com.sdnx.st.sm.utils.ParamPopupBean;
import com.sdnx.st.sm.utils.SmTools;
import com.sdnx.st.sm.vo.StModelVo;
import com.sdnx.st.sm.vo.StObjectPropertyVo;
import com.sdnx.st.sm.vo.StObjectVo;
import com.sdnx.st.sm.vo.StProductSpreadVo;
import com.sdnx.st.sm.vo.StRowVo;
import com.sdnx.st.sm.vo.StRuleDetailVo;
import com.sdnx.st.sm.vo.StRuleVo;
import com.sdnx.st.sm.vo.StStructrueVo;
import com.sdnx.st.util.StDaoFactory;
import com.sdnx.st.util.StServiceFactory;
import com.sdnx.st.util.StUtil;

public class StRuleMaintainServiceImpl extends AbstractBaseService<StStructrue> implements StRuleMaintainService {

	private StModelDao modeldao = StDaoFactory.getModelDao();
	private StStructrueDao ssDao = StDaoFactory.getStructrueDao();
	private StRuleDao ruleDao = StDaoFactory.getRuleDao();
	private StRuleDetailDao ruledetailDao = StDaoFactory.getRuleDetailDao();
	private StRowDao rowDao = StDaoFactory.getRowDao();
	private StObjectDao objectDao = StDaoFactory.getObjectDao();
	private StObjectPropertyDao propertyDao = StDaoFactory.getObjectPropertyDao();
	private StProductSpreadDao productDao = StDaoFactory.getProductSpreadDao();

	@Override
	public void querySingleRule(Long strucId) {
		StModel test = new StModel();
		try {
			List<StModel> list = modeldao.queryListByModel(test, StModel.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String querySubTree(String modelCode, String isShowCode) {
		StModel sm = new StModel();
		sm.setModelcode(modelCode);
		StStructrue ss = new StStructrue();
		ss.setModelcode(modelCode);
		ss.getPageListData().setPageSize(Integer.MAX_VALUE);
		try {
			// ����modelcode��ѯ�����еĽṹ��Ϣ
			List<StStructrueVo> list = ssDao.queryListByModel(ss, StStructrueVo.class);
			if (null == list)
				return null;
			if (list.size() == 0)
				return null;
			List<StModelVo> modelList = modeldao.queryListByModel(sm, StModelVo.class);
			StModelVo smv = modelList.get(0);
			// ��listת��Ϊmap,�����γ����ṹʱ�ı���
			Map<String, List<StStructrueVo>> map = new HashMap<String, List<StStructrueVo>>();
			for (StStructrueVo ssv : list) {
				if (null == map.get(ssv.getParentstruccode())) {
					List<StStructrueVo> listVo = new ArrayList<StStructrueVo>();
					listVo.add(ssv);
					map.put(ssv.getParentstruccode(), listVo);
				} else {
					map.get(ssv.getParentstruccode()).add(ssv);
				}
			}
			// ��ȡ���ڵ�
			StStructrueVo root = map.get("0").get(0);
			// �������ڵ�json��Ϣ
			JsonObject obj = new JsonObject();	
			obj.addProperty("identifier", "id");
			obj.addProperty("label", "name");
		/*	if (smv.getClassification().equals(RuleClass.limitAmount.getCode()) && smv.getState().equals(RuleState.add.getCode())) {
				obj.add("items", createLimitDojoTree(root.getStruccode(), map, smv.getOrgcode(), list));
			} else {*/
				// �ݹ鹹����json
			JsonArray ja = createDojoTree(root.getStruccode(), map);
			if(RuleClass.rate.getCode().equals(smv.getClassification()) && "true".equals(isShowCode)){
				Iterator<JsonElement> it = ja.iterator();
				while(it.hasNext()){
					JsonObject jo = (JsonObject) it.next();
//					jo.addProperty("name", StUtil.strAddOne(StUtil.PinYin2Abbreviation.cn2py(jo.get("name").getAsString())));
					jo.addProperty("name", jo.get("name").getAsString());
				}
			}
			obj.add("items", ja);
			/*}*/
			return obj.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ������ȼ������ṹ
	private JsonArray createLimitDojoTree(String struccode, Map<String, List<StStructrueVo>> map, String orgcode,
			List<StStructrueVo> list) throws Exception {
		JsonArray ja = new JsonArray();
		StProductSpread product = new StProductSpread();
		product.setOrgcode(orgcode);
		product.setIsopen(YesNo.yes.getCode());
		List<StProductSpreadVo> productList = productDao.queryListByModel(product,
				StProductSpreadVo.class);
		// ʹ��map���洢list���ݣ�������ң����ٱ���
		Map<String, StProductSpreadVo> productMap = new HashMap<String, StProductSpreadVo>();
		Map<String, StStructrueVo> strucMap = new HashMap<String, StStructrueVo>();
		if (productList != null && productList.size() != 0) {
			for (StProductSpreadVo productVo : productList) {
				productMap.put(productVo.getProductcode(), productVo);
			}
		}
		if (list != null && list.size() != 0) {
			for (StStructrueVo struc : list) {
				strucMap.put(struc.getRelatecode(), struc);
			}
		}
		String busyname = "cmiscc.pCustBusiCode";
		String productco = "000000097642";
		List<ParamPopupBean> lists = new SmTools().getParamsDisplay(busyname, productco, null);
		int flag = 0;
		for (ParamPopupBean pp : lists) {
			JsonObject o = new JsonObject();
			o.addProperty("id", ++flag);
			o.addProperty("name", pp.getName());
			o.addProperty("code", pp.getCode());
			List<PPRetMsg> ppmList = queryProB(pp.getCode(), orgcode);
			JsonArray jar = new JsonArray();
			for (PPRetMsg ppm : ppmList) {
				if (strucMap.get(ppm.getCode()) != null) {
					JsonObject jo = new JsonObject();
					jo.addProperty("id", ++flag);
					jo.addProperty("name", ppm.getName());
					jo.addProperty("code", ppm.getCode());
					JsonArray tmp = createDojoTree(strucMap.get(ppm.getCode()).getStruccode(), map);
					if(tmp != null && tmp.size() > 0)
						jo.add("children", createDojoTree(strucMap.get(ppm.getCode()).getStruccode(), map));
					jar.add(jo);
				}
			}
			o.add("children", jar);
			ja.add(o);
		}
		return ja;
	}

	/**
	 * �ݹ��γɹ������ṹ
	 * 
	 * @param parentCode
	 * @param map
	 * @return
	 */
	private JsonArray createDojoTree(String parentCode, Map<String, List<StStructrueVo>> map) {
		JsonArray ja = new JsonArray();
		List<StStructrueVo> list = map.get(parentCode);
		if (null == list)
			return null;
		for (StStructrueVo ss : list) {
			JsonObject jo = new JsonObject();
			jo.addProperty("id", ss.getId());
			jo.addProperty("name", ss.getStrucname());
			jo.addProperty("struccode", ss.getStruccode());
			jo.addProperty("parentcode", parentCode);
			jo.addProperty("classification", ss.getClassification());
			jo.addProperty("isleaf", ss.getIsleaf());
			JsonArray tmp = createDojoTree(ss.getStruccode(), map);
			if (null != tmp)
				jo.add("children", tmp);
			else if(ss.getIsleaf() == null || ss.getIsleaf().equals(YesNo.no.getCode())){
				jo.add("children", new JsonArray());
			}
			ja.add(jo);
		}
		return ja;
	}

	// ��ѯ��ȼ������в�Ʒ�б�
	private List<StStructrueVo> queryLimitAmountProduct(String modelcode) {
		StStructrue ss = new StStructrue();
		ss.setModelcode(modelcode);
		ss.setParentstruccode("0");
		try {
			List<StStructrueVo> root = ssDao.queryListByModel(ss, StStructrueVo.class);
			ss.setParentstruccode(root.get(0).getStruccode());
			return ssDao.queryListByModel(ss, StStructrueVo.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ��ѯ���������Ϣ
	@Override
	public void queryStrucInfo(StRuleForm srf) {
		// ��ѯ������Ϣ
		if (srf.getModelcode() != null) {
			StObject so = new StObject();
			so.getPageListData().setPageSize(Integer.MAX_VALUE);
			so.setModelcode(srf.getModelcode());
			try {
				List<StObjectVo> objectList = objectDao.queryListByModel(so,
						StObjectVo.class);
				srf.setObjectList(objectList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (null == srf.getStruccode())
			return;
		StStructrue ss = new StStructrue();
		ss.setStruccode(srf.getStruccode());
		ss.getPageListData().setPageSize(Integer.MAX_VALUE);
		try {
			// ���ݽṹ�����ѯ�ṹ��Ϣ
			List<StStructrueVo> strucList = ssDao.queryListByModel(ss,
					StStructrueVo.class);
			if (null == strucList || strucList.size() == 0)
				return;
			StStructrueVo ssv = strucList.get(0);
			// ���ݽṹ�����ѯ������ɫ��Ϣ
			StRule rule = new StRule();
			rule.setStruccode(ssv.getStruccode());
			rule.getPageListData().setPageSize(Integer.MAX_VALUE);
			List<StRuleVo> ruleList = ruleDao.queryListByModel(rule, StRuleVo.class);
			StRuleVo ruleVo;
			if (null == ruleList || ruleList.size() == 0)
				ruleVo = new StRuleVo();
			else ruleVo = ruleList.get(ruleList.size() - 1);
			ssv.setStRuleVo(ruleVo);
			// ���ݽṹ�����ѯ���������Ϣ
			StRuleDetail ruledetail = new StRuleDetail();
			ruledetail.getPageListData().setPageSize(Integer.MAX_VALUE);
			ruledetail.setStruccode(ssv.getStruccode());
			List<StRuleDetailVo> detailList = ruledetailDao.queryListByModel(ruledetail,
					StRuleDetailVo.class);
			if (null == detailList || detailList.size() == 0) {
				srf.setStrucVo(ssv);
				return;
			}
			ssv.setDetailList(detailList);
			// ���ݾ��������Ϣ��ѯ����������
			for (StRuleDetailVo detailVo : detailList) {
				List<StRowVo> rowList = rowDao.queryBydetailCode(detailVo.getDetailcode());
				detailVo.setRowList(rowList);
			}
			// ������ѯ����Ϣ�����
			srf.setStrucVo(ssv);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String deleteRule(StRuleForm srf) {
		if (null == srf.getStruccode())
			return "ɾ��ʧ��";
		try {
			ssDao.deleteByCode(srf.getStruccode());
			return "ɾ���ɹ�";
		} catch (Exception e) {
			e.printStackTrace();
			return "ɾ��ʧ��";
		}
	}

	@Override
	public String[] insertRule(StRuleForm srf) {
		Map test = SmTools.getParamListInfo("party.personalType");
		String[] result = new String[2];
		result[0] = YesNo.yes.getCode();
		result[1] = "����ɹ�";
		Map map = SmTools.getParamListInfo("party.married");
		java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
		// ���ṹvoתΪmodel
		StStructrueVo ssv = srf.getStrucVo();
		StStructrue ss = new StStructrue();
		String struccode = ssv.getStruccode();
		ss.setId(ssv.getId());
		ss.setModelcode(srf.getModelcode());
		// ss.setStruccode(struccode);
		ss.setParentstruccode(ssv.getParentstruccode());
		ss.setLevel(ssv.getLevel());
		ss.setStrucname(ssv.getStrucname());
		ss.setDescription(ssv.getDescription());
		ss.setSeq(ssv.getSeq() == null ? null : ssv.getSeq().shortValue());
		ss.setObjectrule(ssv.getObjectrule());
		ss.setPrompt(ssv.getPrompt());
		ss.setLastopertime(now);
		ss.setIsleaf(YesNo.yes.getCode());
		ss.setClassification(ssv.getClassification());
		// ��������ɫ��ϢvoתΪmodel
		StRuleVo srv = ssv.getStRuleVo();
		StRule sr = new StRule();
		sr.setId(srv.getId());
		sr.setRulecode(srv.getRulecode());
		sr.setStruccode(srv.getStruccode());
		sr.setCycle(srv.getCycle());
		sr.setParentcode(srv.getParentcode());
		sr.setOrgcode(srv.getOrgcode());
		sr.setObjectcode(srv.getObjectcode());
		sr.setLastopertime(now);
		try {
			// ���û��id�����������Ҫ��������,�����������
			if (null == ss.getId() || ss.getId().equals(0l) || "".equals(ss.getId()))
				struccode = ssDao.codeGenerator("StStructrue");
			// ��ȡ��������б�
			List<StRuleDetailVo> detailList = ssv.getDetailList();
			// ���ڴ洢�������������������
			List<StRuleDetail> detailmodelList = new LinkedList<StRuleDetail>();
			// ���ڴ洢����������������ݱ�����
			List<StRow> rowmodelList = new LinkedList<StRow>();
			// �����ж��Ƿ��д���Ĺ�������
			if (null != detailList) {
				if (detailList.size() != 0) {
					for(int k = 0 , l = detailList.size(); k < l; k++){
						StRuleDetailVo detailVo = detailList.get(k);
						// ������ṹvoתΪmodel
						String detailcode = ruledetailDao.codeGenerator("StRuleDetail");
						StRuleDetail detail = new StRuleDetail();
						detail.setId(detailVo.getId());
						detail.setDetailcode(detailcode);
						detail.setStruccode(struccode);
						String condition = "";
						List<StRowVo> rowList = detailVo.getRowList();
						//����У������Ƿ���ȷ
						Map<String, Object> variable = new HashMap<String, Object>();
						//�������Ա���
						String propertyCode = "";
						if (null != rowList) {
							if (rowList.size() != 0) {
								for (int i = 0, j = rowList.size(); i < j; i++) {
									// ������������voתΪmodel
									StRowVo rowVo = rowList.get(i);
									StRow row = new StRow();
									row.setId(rowVo.getId());
									row.setDetailcode(detailcode);
									row.setRowcode(rowDao.codeGenerator("StRow"));
									row.setRowtype(detailVo.getPreOrRe());
									row.setFirstcal(rowVo.getFirstcal().replaceAll(" ", ""));
									row.setIsleft(rowVo.getIsleft());
									row.setPropertycode(rowVo.getPropertycode());
									row.setSecondcal(rowVo.getSecondcal().replaceAll(" ", ""));
									row.setValue(rowVo.getValue());
									row.setIsright(rowVo.getIsright());
									row.setRowcount((i + 1));
									row.setLastopertime(now);
									rowmodelList.add(row);
									// ������������ַ���
									if (row.getFirstcal() != null && !row.getFirstcal().equals("") && !row.getFirstcal().equals(" "))
										condition += row.getFirstcal().replaceAll(" ", "");
									if (row.getIsleft() != null)
										condition += row.getIsleft();
									if (row.getPropertycode() != null && !row.getPropertycode().equals("") && !row.getPropertycode().equals(" ")){
										condition += "#{" + row.getPropertycode().replaceAll(" ", "") + "}";
										if(propertyCode != null && variable.get(propertyCode) == null){
											variable.put(propertyCode, "1");
										}
										propertyCode = row.getPropertycode().replaceAll(" ", "");
									}
									if (row.getSecondcal() != null && !row.getSecondcal().equals("") && !row.getSecondcal().equals(" "))
										condition += row.getSecondcal().replaceAll(" ", "");
									if (row.getValue() != null && !row.getValue().equals("")){
										String[] tmp = row.getValue().split("\\$\\$");
										if(tmp.length > 1){
											condition += "'" + tmp[1] + "'&&#{smallProduct}=='" + tmp[0] + "'";
											variable.put(propertyCode, "'a'");
											variable.put("smallProduct", "'a'");
										}
										else if(row.getValue().matches(".*[a-zA-Z].*") || !StUtil.strIsDouble(row.getValue())){
											condition += "'" + row.getValue() + "'";
											variable.put(propertyCode, "'a'");
										}
										else{
											condition += row.getValue();
											variable.put(propertyCode, "1");
										}
									}
									if (row.getIsright() != null)
										condition += row.getIsright();
								}
							}
						}
						if(propertyCode != null && variable.get(propertyCode) == null){
							variable.put(propertyCode, "1");
						}
						// ����������������ھ������ǰ��������ֵǰ������,��Ȼ��ֵ���
						if (detailVo.getPreOrRe().equals(RowType.precondition.getCode())) {
							detail.setPrecondition(condition);
							detail.setRuleresult(detailVo.getRuleresult());
						} else {
							detail.setRuleresult(condition);
							detail.setPrecondition(detailVo.getPrecondition());
						}
						detail.setResulttype(detailVo.getResulttype());
						detail.setLastopertime(now);
						if(condition.equals("")){
							result[0] = YesNo.no.getCode();
							result[1] = "�������벻����������";
							return result;
						}
						else if(StUtil.checkExpression(condition, variable)){
							detailmodelList.add(detail);
						}
						else {
							result[0] = YesNo.no.getCode();
							result[1] = "��" + (k + 1) + "�����������������";
							return result;
						}
					}
				}
			}
			// ���û��id�����������Ҫ��������,�����������
			if (null == ss.getId() || ss.getId().equals(0l) || "".equals(ss.getId())) {
				ss.setStruccode(struccode);
				ssDao.add(ss);
			} else {
				ssDao.update(ss);
			}
			// ���û��id�����������Ҫ�������ݣ������������
			if (null == sr.getId() || sr.getId().equals(0l)) {
				sr.setRulecode(ruleDao.codeGenerator("StRule"));
			} else {
				ruleDao.deleteById(sr.getId(), "st_rule");
			}
			sr.setStruccode(struccode);
			ruleDao.add(sr);
			// ͨ���ṹ����ɾ�������������
			ruledetailDao.deleteByStruccode(struccode);
			// ������������������
			ruledetailDao.batchInsert(detailmodelList);
			// �����������������
			rowDao.batchInsert(rowmodelList);

		} catch (Exception e) {
			e.printStackTrace();
			result[0] = YesNo.no.getCode();
			result[1] = "���ݿ��쳣";
			return result;
		}
		return result;
	}

	// ���Ʋ�Ʒ��Ӧ��ȼ��������Ϣ
	public String copyProductLimitAmount(StStructrueVo ssv) {
		StSystemMaintainServiceImpl systemService = StServiceFactory.getSystemMaintainService();
		String result = "�����ɹ�";
		if (ssv == null)
			return "����ʧ��";
		StStructrue ss = new StStructrue();
		ss.setStruccode(ssv.getStruccode());
		try {
			List<StStructrueVo> oldStrucList = ssDao.queryListByModel(ss,
					StStructrueVo.class);
			StStructrueVo oldStruc = oldStrucList.get(0);
			oldStruc.setRelatecode(ssv.getRelatecode());
			oldStruc.setStrucname(ssv.getStrucname());
			oldStruc.setDescription(ssv.getStrucname());
			systemService.copyStruct(oldStruc.getModelcode(), oldStruc.getModelcode(), oldStruc,
					oldStruc.getParentstruccode());
		} catch (Exception e) {
			e.printStackTrace();
			return "����ʧ��";
		}
		return result;
	}

	// ��ѯ���������б�
	@Override
	public String queryPorperty(String objectcode, String isShowCode) {
		StObjectProperty sop = new StObjectProperty();
		sop.setObjectcode(objectcode);
		String result = "";
		try {
			List<StObjectPropertyVo> propertyList = propertyDao.queryPropertyAndDatatype(objectcode);
			JsonObject all = new JsonObject();
			JsonArray ja = new JsonArray();
			if (propertyList.size() != 0) {
				Collections.sort(propertyList, new Comparator<StObjectPropertyVo>() {

					@Override
					public int compare(StObjectPropertyVo arg0, StObjectPropertyVo arg1) {
						if(arg0.getId().compareTo(arg1.getId()) > 0) return 1;
						else return -1;
					}
				});
				for (StObjectPropertyVo propertyVo : propertyList) {
					JsonObject jo = new JsonObject();
					jo.addProperty("propertykey", propertyVo.getPropertykey());
					if(!objectcode.contains("Approval") && "true".equals(isShowCode)){
//						jo.addProperty("propertyname", StUtil.strAddOne(propertyVo.getPropertykey()));
						jo.addProperty("propertyname", propertyVo.getPropertyname());
					}
					else{
						jo.addProperty("propertyname", propertyVo.getPropertyname());
					}
					jo.addProperty("isenum", propertyVo.getIsenum());
					if (propertyVo.getIsenum() != null && propertyVo.getIsenum().equals("1")) {
						JsonArray edata = new JsonArray();
						try {
							Map map = SmTools.getParamListInfo(propertyVo.getDictcode());
							Iterator it = map.entrySet().iterator();
							while (it.hasNext()) {
								Map.Entry entry = (Map.Entry) it.next();
								JsonObject o = new JsonObject();
								o.addProperty("code", (String) entry.getKey());
								o.addProperty("name", (String) entry.getValue());
								edata.add(o);
							}
						} catch (Exception e) {
							List<ParamPopupBean> list = SmTools.getParamsDisplay(propertyVo.getDictcode(),null,null);
							if(list != null && list.size() > 0){
								for(ParamPopupBean pp : list){
									JsonObject o = new JsonObject();
									o.addProperty("code", pp.getCode());
									o.addProperty("name", pp.getName());
									edata.add(o);
								}
							}
						}
						jo.add("edata", edata);
					}
					ja.add(jo);
				}
			}
			all.add("propertyData", ja);
			result = all.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<StStructrueVo> queryListByModel(StStructrue structrue) {
		List<StStructrueVo> lists = null;
		try {
			lists = ssDao.queryListByModel(structrue, StStructrueVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}
	
	//��ѯ��ƷС����
	@Override
	public String queryLittleProduct(){
		String result = "";
		JsonObject all = new JsonObject();
		all.addProperty("identifier", "id");
		all.addProperty("label", "name");
		JsonArray ja = new JsonArray();
		String busyname = "cmiscc.pCustBusiCode";
		String productco = "000000097642";
		List<ParamPopupBean> lists = new SmTools().getParamsDisplay(busyname, productco, null);
		for (ParamPopupBean pp : lists) {
			JsonObject o = new JsonObject();
			o.addProperty("id", pp.getId());
			o.addProperty("name", pp.getName());
			o.addProperty("code", pp.getCode());
			List<ParamPopupBean> subList = new SmTools().getParamsDisplay(busyname, pp.getId(), null);
			JsonArray jar = new JsonArray();
			for (ParamPopupBean subPP : subList) {
				JsonObject jo = new JsonObject();
				jo.addProperty("id", subPP.getId());
				jo.addProperty("name", subPP.getName());
				jo.addProperty("code", subPP.getCode());
				jo.addProperty("parentcode", "");
				jo.addProperty("parentname", "");
				jar.add(jo);
			}
			o.add("children", jar);
			ja.add(o);
		}
		all.add("items", ja);
		result = all.toString();
		return result;
	}

	@Override
	public String queryProduct(String modelcode) {
		StModel m = new StModel();
		m.setModelcode(modelcode);
		String result = "";
		try {
			List<StModelVo> list = modeldao.queryListByModel(m, StModelVo.class);
			StModelVo mv = list.get(0);
			GroupDao groupDao = GroupDao.getInstance();
			JsonObject all = new JsonObject();
			all.addProperty("identifier", "id");
			all.addProperty("label", "name");
			JsonArray ja = new JsonArray();
			String busyname = "cmiscc.pCustBusiCode";
			String productco = "000000097642";
			List<ParamPopupBean> lists = new SmTools().getParamsDisplay(busyname, productco, null);
			int flag = 0;
			for (ParamPopupBean pp : lists) {
				JsonObject o = new JsonObject();
				o.addProperty("id", ++flag);
				o.addProperty("name", pp.getName());
				o.addProperty("code", pp.getCode());
				List<PPRetMsg> ppmList = queryProB(pp.getCode(), mv.getOrgcode());
				JsonArray jar = new JsonArray();
				for (PPRetMsg ppm : ppmList) {
					JsonObject jo = new JsonObject();
					jo.addProperty("id", ++flag);
					jo.addProperty("name", ppm.getName());
					jo.addProperty("code", ppm.getCode());
					jo.addProperty("parentcode", pp.getCode());
					jo.addProperty("parentname", pp.getName());
					jar.add(jo);
				}
				o.add("children", jar);
				ja.add(o);
			}
			all.add("items", ja);
			result = all.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * ��ѯ��ɫ��Ʒ
	 * 
	 * @param tcode
	 *            ��Ʒ���
	 * @param groupId
	 *            �������
	 * @param busiCode
	 *            ҵ��Ʒ��
	 * @return
	 */
	public List<PPRetMsg> queryProB(String pcode, String groupId) {
		return new ArrayList<>();

	}

	@Override
	public String queryExistProduct(String modelcode) {
		StModel m = new StModel();
		m.setModelcode(modelcode);
		StStructrue ss = new StStructrue();
		ss.setModelcode(modelcode);
		String result = "";
		try {
			List<StStructrueVo> strucList = ssDao.queryListByModel(ss,
					StStructrueVo.class);
			if (null == strucList)
				return null;
			if (strucList.size() == 0)
				return null;
			// ��listת��Ϊmap,�����γ����ṹʱ�ı���
			Map<String, StStructrueVo> map = new HashMap<String, StStructrueVo>();
			for (StStructrueVo ssv : strucList) {
				map.put(ssv.getRelatecode(), ssv);
			}
			List<StModelVo> list = modeldao.queryListByModel(m, StModelVo.class);
			StModelVo mv = list.get(0);
			JsonObject all = new JsonObject();
			all.addProperty("identifier", "id");
			all.addProperty("label", "name");
			JsonArray ja = new JsonArray();
			String busyname = "cmiscc.pCustBusiCode";
			String productco = "000000097642";
			List<ParamPopupBean> lists = new SmTools().getParamsDisplay(busyname, productco, null);
			int flag = 0;
			for (ParamPopupBean pp : lists) {
				JsonObject o = new JsonObject();
				List<PPRetMsg> ppmList = queryProB(pp.getCode(), mv.getOrgcode());
				JsonArray jar = new JsonArray();
				for (PPRetMsg ppm : ppmList) {
					if (map.get(ppm.getCode()) != null) {
						JsonObject jo = new JsonObject();
						jo.addProperty("id", ++flag);
						jo.addProperty("name", ppm.getName());
						jo.addProperty("code", map.get(ppm.getCode()).getStruccode());
						jar.add(jo);
					}
				}
				if (jar.size() != 0) {
					o.addProperty("id", ++flag);
					o.addProperty("name", pp.getName());
					o.addProperty("code", pp.getCode());
					o.add("children", jar);
					ja.add(o);
				}
			}
			all.add("items", ja);
			result = all.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public String queryNotExistProduct(String modelcode) {
		StModel m = new StModel();
		m.setModelcode(modelcode);
		StStructrue ss = new StStructrue();
		ss.setModelcode(modelcode);
		String result = "";
		try {
			List<StStructrueVo> strucList = ssDao.queryListByModel(ss,
					StStructrueVo.class);
			if (null == strucList)
				return null;
			if (strucList.size() == 0)
				return null;
			List<StModelVo> modelList = modeldao.queryListByModel(m, StModelVo.class);
			StModelVo smv = modelList.get(0);
			StProductSpread product = new StProductSpread();
			product.setOrgcode(smv.getOrgcode());
			product.setIsopen(YesNo.yes.getCode());
			List<StProductSpreadVo> productList = productDao.queryListByModel(product,
					StProductSpreadVo.class);
			// ʹ��map���洢list���ݣ�������ң����ٱ���
			Map<String, StProductSpreadVo> productMap = new HashMap<String, StProductSpreadVo>();
			if (productList != null && productList.size() != 0) {
				for (StProductSpreadVo productVo : productList) {
					productMap.put(productVo.getProductcode(), productVo);
				}
			}
			// ��listת��Ϊmap,�����γ����ṹʱ�ı���
			Map<String, StStructrueVo> map = new HashMap<String, StStructrueVo>();
			for (StStructrueVo ssv : strucList) {
				map.put(ssv.getRelatecode(), ssv);
			}
			List<StModelVo> list = modeldao.queryListByModel(m, StModelVo.class);
			StModelVo mv = list.get(0);
			JsonObject all = new JsonObject();
			all.addProperty("identifier", "id");
			all.addProperty("label", "name");
			JsonArray ja = new JsonArray();
			String busyname = "cmiscc.pCustBusiCode";
			String productco = "000000097642";
			List<ParamPopupBean> lists = new SmTools().getParamsDisplay(busyname, productco, null);
			int flag = 0;
			for (ParamPopupBean pp : lists) {
				JsonObject o = new JsonObject();
				List<PPRetMsg> ppmList = queryProB(pp.getCode(), mv.getOrgcode());
				JsonArray jar = new JsonArray();
				for (PPRetMsg ppm : ppmList) {
					if (map.get(ppm.getCode()) == null) {
						JsonObject jo = new JsonObject();
						jo.addProperty("id", ++flag);
						jo.addProperty("name", ppm.getName());
						jo.addProperty("code", ppm.getCode());
						jar.add(jo);
					}
				}
				if (jar.size() != 0) {
					o.addProperty("id", ++flag);
					o.addProperty("name", pp.getName());
					o.addProperty("code", pp.getCode());
					o.add("children", jar);
					ja.add(o);
				}
			}
			all.add("items", ja);
			result = all.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean hasNewProduct(String modelcode) {
		StModel m = new StModel();
		m.setModelcode(modelcode);
		StStructrue ss = new StStructrue();
		ss.setModelcode(modelcode);
		try {
			List<StStructrueVo> strucList = ssDao.queryListByModel(ss,
					StStructrueVo.class);
			if (null == strucList)
				return false;
			if (strucList.size() == 0)
				return false;
			// ��listת��Ϊmap,�����γ����ṹʱ�ı���
			Map<String, StStructrueVo> map = new HashMap<String, StStructrueVo>();
			for (StStructrueVo ssv : strucList) {
				map.put(ssv.getRelatecode(), ssv);
			}
			List<StModelVo> list = modeldao.queryListByModel(m, StModelVo.class);
			StModelVo mv = list.get(0);
			String busyname = "cmiscc.pCustBusiCode";
			String productco = "000000097642";
			List<ParamPopupBean> lists = new SmTools().getParamsDisplay(busyname, productco, null);
			for (ParamPopupBean pp : lists) {
				List<PPRetMsg> ppmList = queryProB(pp.getCode(), mv.getOrgcode());
				for (PPRetMsg ppm : ppmList) {
					if (map.get(ppm.getCode()) == null)
						return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
