package com.sdnx.st.sm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sdnx.st.sm.base.AbstractBaseService;
import com.sdnx.st.sm.dao.StLimitIndustryDao;
import com.sdnx.st.sm.model.StLimitIndustry;
import com.sdnx.st.sm.service.StLimitIndustryService;
import com.sdnx.st.sm.utils.ParamPopupBean;
import com.sdnx.st.sm.utils.SmTools;
import com.sdnx.st.sm.utils.SmUtil;
import com.sdnx.st.sm.utils.SmUtil.User;
import com.sdnx.st.sm.vo.StLimitIndustryVo;
import com.sdnx.st.util.StDaoFactory;

public class StLimitIndustryServiceImpl extends AbstractBaseService<StLimitIndustry> implements StLimitIndustryService{

	private StLimitIndustryDao industryDao = StDaoFactory.getLimitIndustryDao();
	private static Logger logger = Logger.getLogger(StLimitIndustryServiceImpl.class);
	//查询行业树
	@Override
	public String queryAllTree() {
		List<ParamPopupBean> list = SmTools.getParamsDisplay("party.industry",null,null);
		JsonObject all = new JsonObject();
		if(list != null && list.size() > 0){
			all.addProperty("identifier", "id");
			all.addProperty("label", "name");
			all.add("items", createIndustryTree(list, "-1"));
		}
		return all.toString();
	}
	//构造行业树json
	private JsonArray createIndustryTree(List<ParamPopupBean> list, String parentId){
		JsonArray ja = new JsonArray();
		if(list != null && list.size() > 0){
			for(ParamPopupBean pp : list){
				JsonObject o = new JsonObject();
				o.addProperty("id", pp.getId());
				o.addProperty("name", pp.getName());
				o.addProperty("code", pp.getCode());
				o.addProperty("parentid", parentId);
				JsonArray tmp = createIndustryTree(SmTools.getParamsDisplay("party.industry",pp.getId(),null), pp.getId());
				if(tmp != null){
					o.add("children", tmp);
				}
				ja.add(o);
			}
		}else{
			return null;
		}
		return ja;
	}
	//查询限控行业树
	@Override
	public String queryLimitIndustryTree() {
		List<ParamPopupBean> list = SmTools.getParamsDisplay("party.industry",null,null);
		StLimitIndustry sli = new StLimitIndustry();
		Map<String, StLimitIndustryVo> limitIndustryMap = new HashMap<String, StLimitIndustryVo>();
		try {
			List<StLimitIndustryVo> industryList = (List<StLimitIndustryVo>)(List)industryDao.queryListByModel(sli, StLimitIndustryVo.class);
			//将限控行业list转为map方便查询
			if(industryList != null && industryList.size() > 0){
				for(StLimitIndustryVo industry : industryList){
					limitIndustryMap.put(industry.getIndustrycode(), industry);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "";
		}
		JsonObject all = new JsonObject();
		if(list != null && list.size() > 0){
			all.addProperty("identifier", "id");
			all.addProperty("label", "name");
			all.add("items", createLimitIndustryTree(list, limitIndustryMap));
		}
		return all.toString();
	}
	
	//构建限控行业树
	public JsonArray createLimitIndustryTree(List<ParamPopupBean> list, Map<String, StLimitIndustryVo> limitIndustryMap){
		JsonArray ja = new JsonArray();
		if(list != null && list.size() > 0){
			for(ParamPopupBean pp : list){
				JsonObject o = new JsonObject();
				o.addProperty("id", pp.getId());
				o.addProperty("name", pp.getName());
				o.addProperty("code", pp.getCode());
				List<ParamPopupBean> ppList = SmTools.getParamsDisplay("party.industry",pp.getId(),null);
				if(ppList != null && ppList.size() != 0){
					JsonArray tmp = createLimitIndustryTree(ppList, limitIndustryMap);
					if(tmp.size() > 0){
						o.add("children", createLimitIndustryTree(ppList, limitIndustryMap));
						ja.add(o);
					}
				}
				else{
					StLimitIndustry sli = new StLimitIndustry();
					sli.setIndustrycode(pp.getCode());
					try {
						StLimitIndustryVo industry = limitIndustryMap.get(pp.getCode());
						if(industry != null){
							ja.add(o);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return ja;
	}

	@Override
	public String add(String code, HttpServletRequest request) {
		Date now = new Date();
		User user = SmUtil.getCurrentUser();
		StLimitIndustry sl = new StLimitIndustry();
		sl.setIndustrycode(code);
		sl.setCreatetime(new java.sql.Timestamp(now.getTime()));
		sl.setCreateusercode(user.getId());
		try {
			industryDao.add(sl);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return "添加失败";
		}
		return "添加成功";
	}

	@Override
	public String delete(String code) {
		try {
			industryDao.deleteByIndustryCode(code);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "删除失败";
		}
		return "删除成功";
	}
}
