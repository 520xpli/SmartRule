package com.sdnx.st.sm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sdnx.st.constants.RuleClass;
import com.sdnx.st.constants.RuleState;
import com.sdnx.st.constants.YesNo;
import com.sdnx.st.se.init.StInit;
import com.sdnx.st.sm.dao.StLogInputdataDao;
import com.sdnx.st.sm.dao.StRuleDetailDao;
import com.sdnx.st.sm.dao.StRuleLogDao;
import com.sdnx.st.sm.dao.StStructrueDao;
import com.sdnx.st.sm.model.StLogInputdata;
import com.sdnx.st.sm.model.StModel;
import com.sdnx.st.sm.model.StRateResult;
import com.sdnx.st.sm.model.StRateresultData;
import com.sdnx.st.sm.model.StRuleDetail;
import com.sdnx.st.sm.service.StSystemMaintainService;
import com.sdnx.st.sm.utils.Group;
import com.sdnx.st.sm.utils.GroupDao;
import com.sdnx.st.sm.utils.SmUtil;
import com.sdnx.st.sm.utils.SmUtil.User;
import com.sdnx.st.sm.vo.RateShowVo;
import com.sdnx.st.sm.vo.StLogInputdataVo;
import com.sdnx.st.sm.vo.StModelVo;
import com.sdnx.st.sm.vo.StObjectPropertyVo;
import com.sdnx.st.sm.vo.StOrgSpreadVo;
import com.sdnx.st.sm.vo.StRateResultVo;
import com.sdnx.st.sm.vo.StRateresultDataVo;
import com.sdnx.st.sm.vo.StRuleDetailVo;
import com.sdnx.st.sm.vo.StRuleLogVo;
import com.sdnx.st.sm.vo.StStructrueVo;
import com.sdnx.st.util.StDaoFactory;
import com.sdnx.st.util.StServiceFactory;
import com.sdnx.st.util.StUtil;

public class StSystemMaintain extends DispatchAction {

	private StSystemMaintainService stSystemMaintainService = StServiceFactory.getSystemMaintainService();
	private StRuleLogDao logDao = StDaoFactory.getRuleLogDao();
	private StStructrueDao strucDao = StDaoFactory.getStructrueDao();
	private StRuleDetailDao detailDao = StDaoFactory.getRuleDetailDao();
	private StLogInputdataDao inputdatDao = StDaoFactory.getInputdataDao();
	// 响应编码
	private String responsecode = "application/json;charset=UTF-8";
	private String orgname = "NAME";
	private String result = null;
	private static Logger logger = Logger.getLogger(StSystemMaintain.class);
	// 产品中类查询条件
	private String busyname = "cmiscc.pCustBusiCode";
	private String productco = "000000097642";

	/**
	 * 规则体系升级
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward upgrade(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取表单对象
		StSystemMaintainForm ruleForm = (StSystemMaintainForm) form;
		User user = SmUtil.getCurrentUser();
		String retu = "";
		try {
			// 查询是否有新增状态的数据
			StModel smo = new StModel();
			smo.setOrgcode(ruleForm.getStModelVo().getOrgcode());
			smo.setClassification(ruleForm.getStModelVo().getClassification());
			smo.setState(RuleState.add.getCode());
			List<StModelVo> lists = stSystemMaintainService.queryListByModel(smo);
			if (lists.size() > 0) {
				retu = "该规则模块存在未提交的版本";
			} else {
				retu = stSystemMaintainService.upgradeModel(ruleForm.getStModelVo().getId(), user, ruleForm);
			}
		} catch (Exception e) {
			retu = "升级失败";
			e.printStackTrace();
		}
		try {
			// 封装传递数据到前台
			response.setContentType(responsecode);
			PrintWriter out = response.getWriter();
			JsonObject allObject = new JsonObject();
			allObject.addProperty("retu", retu);
			out.print(allObject.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward delete(Long modelId) {
		return null;
	}

	/**
	 * 跳转进入规则维护页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ruleJump(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		StSystemMaintainForm systemForm = (StSystemMaintainForm) form;
		/*
		 * if(systemForm.getStModelVo().getClassification().equals(RuleClass.
		 * 额度计算.getCode()) && !"true".equals(systemForm.getShowtext())){
		 * if(ruleService.hasNewProduct(systemForm.getStModelVo().getModelcode()
		 * )){ return mapping.findForward("stLimitAmountAddProduct"); } }
		 */
		systemForm.setCurrentTime(new Date().getTime());
		return mapping.findForward("stRule");
	}

	/**
	 * 规则页面入口
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * 
	 * @param response
	 * @return
	 */
	public ActionForward jump(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取表单对象
		StSystemMaintainForm ruleForm = (StSystemMaintainForm) form;
		// 获取当前登录用户
		User user = SmUtil.getCurrentUser();
		// 获取当前机构
		String groupId = GroupDao.ORG_CODE;
		// 不是法人机构
		String islegal = "false";
		// 不是地市机构
		String isCity = "false";
		// 不是网点
		String isDot = "false";
		List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
		try {
			// 获取当前机构的地市机构
			List<Group> groups = GroupDao.getInstance().queryCityGroupList(groupId);
			// 获取上层法人机构
			String dot = GroupDao.getInstance().queryFrflagGroupId(groupId);
			// 判断该机构是否法人机构
			if (GroupDao.getInstance().isTrueFrFlag(groupId)) {
				String name = GroupDao.getInstance().queryValueByAppoint(groupId, orgname);
				ruleForm.setLegalOrgCode(groupId);
				ruleForm.setLegalOrgName(name);
				islegal = "true";
			}
			if (islegal.equals("false") && dot == null && groups.size() <= 1) {
				isCity = "true";
			}
			if (groups.size() <= 1 & islegal.equals("false") & isCity.equals("false")) {
				String name = GroupDao.getInstance().queryValueByAppoint(groupId, orgname);
				ruleForm.setLegalOrgCode(groupId);
				ruleForm.setLegalOrgName(name);
				isDot = "true";
			}
			if (groups.size() > 0) {
				for (int i = 0; i < groups.size(); i++) {
					String id = groups.get(i).getId();
					// 获取地市机构
					String name = GroupDao.getInstance().queryValueByAppoint(id, orgname);
					StOrgSpreadVo orgs = new StOrgSpreadVo();
					orgs.setOrgcode(id);
					orgs.setOrgname(name);
					orgVos.add(orgs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ruleForm.setIsDot(isDot);
		ruleForm.setIsCity(isCity);
		ruleForm.setIsleagal(islegal);
		ruleForm.setOrgnum(orgVos.size());
		ruleForm.setOrgSpreadVoList(orgVos);
		return mapping.findForward("stSystem");
	}

	/**
	 * 规则复核页面入口
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward jumpCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取表单对象
		StSystemMaintainForm ruleForm = (StSystemMaintainForm) form;
		// 获取当前登录用户
		User user = SmUtil.getCurrentUser();
		// 获取当前机构
		String groupId = user.getGroupId();
		List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
		try {
			List<Group> groups = GroupDao.getInstance().queryCityGroupList(groupId);
			// 判断当前机构是否法人机构
			if (GroupDao.getInstance().isTrueFrFlag(groupId)) {
				String name = GroupDao.getInstance().queryValueByAppoint(groupId, orgname);
				ruleForm.setLegalOrgCode(groupId);
				ruleForm.setLegalOrgName(name);
			}
			if (groups.size() > 0) {
				for (int i = 0; i < groups.size(); i++) {
					String id = groups.get(i).getId();
					// 地市机构对象
					String name = GroupDao.getInstance().queryValueByAppoint(id, orgname);
					StOrgSpreadVo orgs = new StOrgSpreadVo();
					orgs.setOrgcode(id);
					orgs.setOrgname(name);
					orgVos.add(orgs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * ruleForm.getFooter().setCount(stmodelVos.size());
		 * ruleForm.getFooter().setDataArray(stmodelVos);
		 */
		ruleForm.setOrgnum(orgVos.size());
		ruleForm.setOrgSpreadVoList(orgVos);

		return mapping.findForward("stSystemCheck");
	}

	/**
	 * 法人机构查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward queryCorpOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取表单对象
		StSystemMaintainForm ruleForm = (StSystemMaintainForm) form;
		// 得到机构编号
		String orgcode = ruleForm.getOrgcodeJ();
		// 操作人员机构编号
		String legalOrgCode = ruleForm.getLegalOrgCode();
		// 操作人员机构名称
		String legalOrgName = ruleForm.getLegalOrgName();
		// 获取法人机构
		List<String> orgIds = new ArrayList<String>();
		// 查询法人机构返回前台集合
		List<StOrgSpreadVo> orgList = new ArrayList<StOrgSpreadVo>();
		try {
			if (legalOrgCode != null && !legalOrgCode.equals("")) {
				StOrgSpreadVo org = new StOrgSpreadVo();
				org.setOrgcode(legalOrgCode);
				org.setOrgname(legalOrgName);
				orgList.add(org);
			} else {
				GroupDao.getInstance().queryOfficeUnitFrGroupList(orgcode, orgIds);
				if (orgIds.size() > 0) {
					for (int i = 0; i < orgIds.size(); i++) {
						String id = orgIds.get(i);
						String name = GroupDao.getInstance().queryValueByAppoint(id, orgname);
						StOrgSpreadVo orgs = new StOrgSpreadVo();
						orgs.setOrgcode(id);
						orgs.setOrgname(name);
						orgList.add(orgs);
					}
				}
			}
			// 封装传递数据到前台
			response.setContentType(responsecode);
			PrintWriter out = response.getWriter();
			JsonObject allObject = new JsonObject();
			JsonArray ja = new JsonArray();
			for (StOrgSpreadVo ss : orgList) {
				JsonObject o = new JsonObject();
				o.addProperty("orgcode", ss.getOrgcode());
				o.addProperty("orgname", ss.getOrgname());
				ja.add(o);
			}
			allObject.add("datas", ja);
			out.print(allObject.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

/*	*//**
	 * 查询网点
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 *//*
	public ActionForward queryDotByLegal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取表单对象
		StSystemMaintainForm ruleForm = (StSystemMaintainForm) form;
		List<StOrgSpreadVo> orgList = null;
		try {
			// 得到法人机构编号
			String orgcode = ruleForm.getOrgcodeJ();
			// 获取该法人机构下的所有网点
			Group group = BaseGroupManager.getInstance().getGroup(orgcode);
			;
			Iterator lists = BaseGroupManager.getInstance().getChildGroups(group, false);
			orgList = new ArrayList<StOrgSpreadVo>();
			while (lists.hasNext()) {
				Group gr = (Group) lists.next();
				StOrgSpreadVo sosv = new StOrgSpreadVo();
				sosv.setOrgcode(gr.getId());
				sosv.setOrgname(gr.getName());
				orgList.add(sosv);
			}
			// 封装传递数据到前台
			response.setContentType(responsecode);
			PrintWriter out = null;
			;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			JsonObject allObject = new JsonObject();
			JsonArray ja = new JsonArray();
			for (StOrgSpreadVo ss : orgList) {
				JsonObject o = new JsonObject();
				o.addProperty("orgcode", ss.getOrgcode());
				o.addProperty("orgname", ss.getOrgname());
				ja.add(o);
			}
			allObject.add("datas", ja);
			out.print(allObject.toString());
			out.flush();
		} catch (DataAccessException e1) {
			e1.printStackTrace();
		}
		return null;
	}*/

	/**
	 * 规则体系查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward queryModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取表单对象
		StSystemMaintainForm ruleForm = (StSystemMaintainForm) form;
		// 获取当前登录用户
		User user = SmUtil.getCurrentUser();
		// 获取地市机构
		String groupId = user.getGroupId();
		List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
		// 规则体系集合创建
		List<StModelVo> modelVoList = new ArrayList<StModelVo>();
		// 查询条件返回类初始化
		StModelVo stv = null;
		try {
			// 地市机构返回
			List<Group> groups = GroupDao.getInstance().queryCityGroupList(groupId);
			if (groups.size() > 0) {
				for (int i = 0; i < groups.size(); i++) {
					String id = groups.get(i).getId();
					String name = GroupDao.getInstance().queryValueByAppoint(id, orgname);
					StOrgSpreadVo orgs = new StOrgSpreadVo();
					orgs.setOrgcode(id);
					orgs.setOrgname(name);
					orgVos.add(orgs);
				}
			}
			stv = new StModelVo();
			// 查询条件
			StModel stmodel = new StModel();
			// 判断类别是否为空
			if (judgeIsNull(ruleForm.getStModelVo().getClassification())) {
				stv.setClassification(ruleForm.getStModelVo().getClassification());
				stmodel.setClassification(ruleForm.getStModelVo().getClassification());
			}
			// 判断生效状态是佛为空
			if (judgeIsNull(ruleForm.getStModelVo().getState())) {
				stv.setState(ruleForm.getStModelVo().getState());
				stmodel.setState(ruleForm.getStModelVo().getState());
			}
			if (ruleForm.getStModelVo().getClassification().equals(RuleClass.ProcessTrend.getCode())) {
				// 判断网点是否为空
				if (judgeIsNull(ruleForm.getDotOrgcode())) {
					ruleForm.setDotOrg(ruleForm.getDotOrgcode());
					stv.setOrgcode(ruleForm.getOrgcodeJ());
					stmodel.setOrgcode(ruleForm.getDotOrgcode());
				} else
				// 判断法人机构是否为空
				if (judgeIsNull(ruleForm.getOrgcodeJ())) {
					stv.setOrgcode(ruleForm.getOrgcodeJ());
					stmodel.setOrgcode(ruleForm.getOrgcodeJ());
				} else {
					stmodel.setOrgcode(ruleForm.getStModelVo().getOrgcode());
				}

			} else {
				// 判断法人机构是否为空
				if (judgeIsNull(ruleForm.getOrgcodeJ())) {
					stv.setOrgcode(ruleForm.getOrgcodeJ());
					stmodel.setOrgcode(ruleForm.getOrgcodeJ());
				} else {
					stmodel.setOrgcode(ruleForm.getStModelVo().getOrgcode());
				}
			}
			// 获取上传的页数
			int page = ruleForm.getFooter().getPage();
			// 获取每页的条数
			int pagesize = ruleForm.getFooter().getPageSize();
			// 根据条件查询规则数据
			stmodel.getPageListData().setPageSize(Integer.MAX_VALUE);
			List<StModelVo> stModellist = stSystemMaintainService.queryListByModel(stmodel);
			
			Collections.sort(stModellist, new Comparator<StModelVo>() {

				@Override
				public int compare(StModelVo arg0, StModelVo arg1) {
					return arg0.getVersion().compareTo(arg1.getVersion());
				}
				
			});

			ruleForm.getFooter().setCount(stModellist.size());
			// 数据上限
			int up = page * pagesize > stModellist.size() ? stModellist.size() : page * pagesize;
			String name = "NAME";
			for (int i = (page - 1) * pagesize; i < up; i++) {
				String orgname = GroupDao.getInstance().queryValueByAppoint(stModellist.get(i).getOrgcode(), name);
				stModellist.get(i).setOrgname(orgname);
				modelVoList.add(stModellist.get(i));

			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 查询条件返回
		ruleForm.setOrgcodeR(ruleForm.getStModelVo().getOrgcode());
		ruleForm.setStModelVoR(stv);
		// 当前登陆人员的地市机构数目
		ruleForm.setOrgnum(orgVos.size());
		ruleForm.setOrgSpreadVoList(orgVos);
		// 分页对象设置
		ruleForm.getFooter().setDataArray(modelVoList);
		return mapping.findForward("stSystem");
	}

	/**
	 * 复核页面规则体系查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward queryModelCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取表单对象
		StSystemMaintainForm ruleForm = (StSystemMaintainForm) form;
		// 获取当前登录用户
		User user = SmUtil.getCurrentUser();
		// 获取地市机构
		String groupId = user.getGroupId();
		List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
		// 规则体系集合创建
		List<StModelVo> modelVoList = new ArrayList<StModelVo>();
		// 查询条件返回类初始化
		StModelVo stv = null;
		try {
			// 地市机构返回
			List<Group> groups = GroupDao.getInstance().queryCityGroupList(groupId);
			if (groups.size() > 0) {
				for (int i = 0; i < groups.size(); i++) {
					String id = groups.get(i).getId();
					String name = GroupDao.getInstance().queryValueByAppoint(id, orgname);
					StOrgSpreadVo orgs = new StOrgSpreadVo();
					orgs.setOrgcode(id);
					orgs.setOrgname(name);
					orgVos.add(orgs);
				}
			}
			stv = new StModelVo();
			// 查询条件
			StModel stmodel = new StModel();
			// 判断类别是否为空
			if (judgeIsNull(ruleForm.getStModelVo().getClassification())) {
				stv.setClassification(ruleForm.getStModelVo().getClassification());
				stmodel.setClassification(ruleForm.getStModelVo().getClassification());
			}
			// 判断生效状态是佛为空
			if (judgeIsNull(ruleForm.getStModelVo().getState())) {
				stv.setState(ruleForm.getStModelVo().getState());
				stmodel.setState(ruleForm.getStModelVo().getState());
			}
			if (ruleForm.getStModelVo().getClassification().equals(RuleClass.ProcessTrend.getCode())) {
				// 判断网点是否为空
				if (judgeIsNull(ruleForm.getDotOrgcode())) {
					ruleForm.setDotOrg(ruleForm.getDotOrgcode());
					stv.setOrgcode(ruleForm.getOrgcodeJ());
					stmodel.setOrgcode(ruleForm.getDotOrgcode());
				} else
				// 判断法人机构是否为空
				if (judgeIsNull(ruleForm.getOrgcodeJ())) {
					stv.setOrgcode(ruleForm.getOrgcodeJ());
					stmodel.setOrgcode(ruleForm.getOrgcodeJ());
				} else {
					stmodel.setOrgcode(ruleForm.getStModelVo().getOrgcode());
				}

			} else {
				// 判断法人机构是否为空
				if (judgeIsNull(ruleForm.getOrgcodeJ())) {
					stv.setOrgcode(ruleForm.getOrgcodeJ());
					stmodel.setOrgcode(ruleForm.getOrgcodeJ());
				} else {
					stmodel.setOrgcode(ruleForm.getStModelVo().getOrgcode());
				}
			}
			// 获取上传的页数
			int page = ruleForm.getFooter().getPage();
			// 获取每页的条数
			int pagesize = ruleForm.getFooter().getPageSize();
			// 根据条件查询规则数据
			List<StModelVo> stModellist = stSystemMaintainService.queryListByModel(stmodel);

			ruleForm.getFooter().setCount(stModellist.size());
			// 数据上限
			int up = page * pagesize > stModellist.size() ? stModellist.size() : page * pagesize;
			String name = "NAME";
			for (int i = (page - 1) * pagesize; i < up; i++) {
				String orgname = GroupDao.getInstance().queryValueByAppoint(stModellist.get(i).getOrgcode(), name);
				stModellist.get(i).setOrgname(orgname);
				modelVoList.add(stModellist.get(i));

			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 查询条件返回
		ruleForm.setOrgcodeR(ruleForm.getStModelVo().getOrgcode());
		ruleForm.setStModelVoR(stv);
		// 当前登陆人员的地市机构数目
		ruleForm.setOrgnum(orgVos.size());
		ruleForm.setOrgSpreadVoList(orgVos);
		// 分页对象设置
		ruleForm.getFooter().setDataArray(modelVoList);
		return mapping.findForward("stSystemCheck");
	}

	/**
	 * 规则体系删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取表单对象
		StSystemMaintainForm ruleForm = (StSystemMaintainForm) form;
		try {
//			TransactionManager.beginTransaction();
			stSystemMaintainService.deleteModel(ruleForm.getStModelVo().getId());
			result = "删除成功";
//			TransactionManager.commitTransaction();
		} catch (Exception e) {
			result = "删除失败";
			e.printStackTrace();
			logger.error(e);
			try {
//				TransactionManager.rollbackTransaction();
			} catch (Exception e1) {
				logger.error(e1);
			}
		}
		try {
			// 封装传递数据到前台
			response.setContentType(responsecode);
			PrintWriter out = response.getWriter();
			JsonObject allObject = new JsonObject();
			allObject.addProperty("retu", result);
			out.print(allObject.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 规则体系编辑
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取表单对象
		StSystemMaintainForm ruleForm = (StSystemMaintainForm) form;
		StModelVo stModelVo = null;
		try {
			stModelVo = stSystemMaintainService.queryById(ruleForm.getStModelVo().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ruleForm.setStModelVo(stModelVo);
		return mapping.findForward("stModelEdit");
	}

/*	*//**
	 * 跳转复制页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 *//*
	public ActionForward copyModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取表单对象
		StSystemMaintainForm ruleForm = (StSystemMaintainForm) form;
		List<String> orgVos = new ArrayList<String>();
		// 查询法人机构返回前台集合
		List<StOrgSpreadVo> orgList = new ArrayList<StOrgSpreadVo>();
		try {
			// 法人机构复制流程
			if (ruleForm.getOrgType().equals("legal")) {
				// 得到法人机构编号
				String orgcode = ruleForm.getOrgcodeJ();
				Group group = BaseGroupManager.getInstance().getGroup(orgcode);
				;
				// 获取该法人机构下的所有网点
				Iterator lists = BaseGroupManager.getInstance().getChildGroups(group, false);
				orgList = new ArrayList<StOrgSpreadVo>();
				while (lists.hasNext()) {
					Group gr = (Group) lists.next();
					StOrgSpreadVo sosv = new StOrgSpreadVo();
					sosv.setOrgcode(gr.getId());
					sosv.setOrgname(gr.getName());
					orgList.add(sosv);
				}
			} else if (ruleForm.getOrgType().equals("inst")) {
				// 得到地市机构编号
				String orgcode = ruleForm.getOrgcodeJ();
				// 获取法人机构
				List<String> orgIds = new ArrayList<String>();
				GroupDao.getInstance().queryOfficeUnitFrGroupList(orgcode, orgIds);
				if (orgIds.size() > 0) {
					for (int i = 0; i < orgIds.size(); i++) {
						String id = orgIds.get(i);
						String name = GroupDao.getInstance().queryValueByAppoint(id, orgname);
						StOrgSpreadVo orgs = new StOrgSpreadVo();
						orgs.setOrgcode(id);
						orgs.setOrgname(name);
						orgList.add(orgs);
					}
				}
			}
			// 网点
			else if (ruleForm.getOrgType().equals("dot")) {
				// 得到网点编号
				String orgcode = ruleForm.getOrgcodeJ();
				// 获取上层法人机构
				String dot = GroupDao.getInstance().queryFrflagGroupId(orgcode);
				// 获取该法人机构下的所有网点
				Group group = BaseGroupManager.getInstance().getGroup(dot);
				;
				Iterator lists = BaseGroupManager.getInstance().getChildGroups(group, false);
				orgList = new ArrayList<StOrgSpreadVo>();
				while (lists.hasNext()) {
					Group gr = (Group) lists.next();
					if (!gr.getId().equals(orgcode)) {
						StOrgSpreadVo sosv = new StOrgSpreadVo();
						sosv.setOrgcode(gr.getId());
						sosv.setOrgname(gr.getName());
						orgList.add(sosv);
					}
				}
			} else {
				List<Group> groups = GroupDao.getInstance().queryCityGroupList(ruleForm.getOrgcodeJ());
				if (groups.size() > 0) {
					for (int i = 0; i < groups.size(); i++) {
						String id = groups.get(i).getId();
						GroupDao.getInstance().queryOfficeUnitFrGroupList(id, orgVos);
						if (orgVos.size() > 0) {
							for (int k = 0; k < orgVos.size(); k++) {
								String orgid = orgVos.get(k);
								if (!ruleForm.getOrgcodeJ().equals(orgid)) {
									String name = GroupDao.getInstance().queryValueByAppoint(orgid, orgname);
									StOrgSpreadVo orgs = new StOrgSpreadVo();
									orgs.setOrgcode(orgid);
									orgs.setOrgname(name);
									orgList.add(orgs);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ruleForm.setOrgSpreadVoList(orgList);
		return mapping.findForward("StModelCopy");
	}*/

	/**
	 * 规则体系复制实现
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward copymodel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取表单对象
		StSystemMaintainForm ruleForm = (StSystemMaintainForm) form;
		User user = SmUtil.getCurrentUser();
		String retu = "";
		try {
//			TransactionManager.beginTransaction();
			// 查询是否有新增状态的数据
			StModel smo = new StModel();
			smo.setClassification(ruleForm.getStModelVo().getClassification());
			smo.setOrgcode(ruleForm.getLegalOrgCode());
			smo.setState(RuleState.add.getCode());
			List<StModelVo> lists = stSystemMaintainService.queryListByModel(smo);
			if (lists.size() > 0) {
				retu = "该机构存在未提交的版本";
			} else {
				retu = stSystemMaintainService.copyModel(ruleForm.getStModelVo().getId(), ruleForm.getLegalOrgCode(),
						user, ruleForm);
			}
//			TransactionManager.commitTransaction();
		} catch (Exception e) {
			retu = "复制失败";
			e.printStackTrace();
			logger.error(e);
			try {
//				TransactionManager.rollbackTransaction();
			} catch (Exception e1) {
				logger.error(e1);
			}
		}
		try {
			// 封装传递数据到前台
			response.setContentType(responsecode);
			PrintWriter out = response.getWriter();
			JsonObject allObject = new JsonObject();
			allObject.addProperty("retu", retu);
			out.print(allObject.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 规则体系保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取表单对象
		StSystemMaintainForm ruleForm = (StSystemMaintainForm) form;
		StModel stm = new StModel();
		stm.setId(ruleForm.getStModelVo().getId());
		stm.setModelname(ruleForm.getStModelVo().getModelname());
		stm.setModifytime(new java.sql.Timestamp(new Date().getTime()));
		stm.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
		// 更新数据
		stSystemMaintainService.update(stm);
		return mapping.findForward("stModelEdit");
	}

	/**
	 * 提交，复核审批，生效
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updateStatues(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取表单对象
		StSystemMaintainForm ruleForm = (StSystemMaintainForm) form;
		// 获取提交模型id
		String id = ruleForm.getStModelVo().getId();
		// 获取提交模型状态
		String state = ruleForm.getStModelVo().getState();
		// 初始化模型对象
		StModel sm = new StModel();
		sm.setModifytime(new java.sql.Timestamp(new Date().getTime()));
		sm.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
		// 提交
		if (state.equals(RuleState.add.getCode())) {
			sm.setId(id);
			sm.setState(RuleState.approving.getCode());
			result = "提交成功";
		}
		// 复核审批
		else if (state.equals(RuleState.approving.getCode())) {
			sm.setId(id);
			sm.setState(RuleState.approved.getCode());
		}
		// 拒绝
		else if (state.equals(RuleState.approving.getCode())) {
			sm.setId(id);
			sm.setState(RuleState.add.getCode());
		}
		// 生效
		else if (state.equals(RuleState.approved.getCode())) {
			// 更新旧版本
			StModel stm = new StModel();
			stm.setClassification(ruleForm.getStModelVo().getClassification());
			stm.setOrgcode(ruleForm.getStModelVo().getOrgcode());
			stm.setState(RuleState.effect.getCode());
			try {
				// 使之前版本失效
				List<StModelVo> lists = stSystemMaintainService.queryListByModel(stm);
				if (lists.size() > 0 && lists != null) {
					for (StModelVo svo : lists) {
						StModel smo = new StModel();
						smo.setId(svo.getId());
						smo.setState(RuleState.invalid.getCode());
						smo.setModifytime(new java.sql.Timestamp(new Date().getTime()));
						smo.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
						stSystemMaintainService.update(smo);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 新版本生效
			sm.setId(id);
			sm.setState(RuleState.effect.getCode());
			sm.setEffecttime(new java.sql.Timestamp(new Date().getTime()));
		}
		stSystemMaintainService.update(sm);
		StInit.resetRuleData();
		// 封装传递数据到前台
		response.setContentType(responsecode);
		PrintWriter out;
		try {
			out = response.getWriter();
			JsonObject allObject = new JsonObject();
			allObject.addProperty("retu", result);
			out.print(allObject.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 拒绝/退回
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refuseCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取表单对象
		StSystemMaintainForm ruleForm = (StSystemMaintainForm) form;
		String id = ruleForm.getStModelVo().getId();
		String state = ruleForm.getStModelVo().getState();
		StModel sm = new StModel();
		sm.setModifytime(new java.sql.Timestamp(new Date().getTime()));
		sm.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
		String retu = "";
		// 拒绝
		if (state.equals(RuleState.approving.getCode())) {
			sm.setId(id);
			sm.setState(RuleState.add.getCode());
			retu = "已成功退回";
		}
		stSystemMaintainService.update(sm);
		// 封装传递数据到前台
		response.setContentType(responsecode);
		PrintWriter out;
		try {
			out = response.getWriter();
			JsonObject allObject = new JsonObject();
			allObject.addProperty("retu", retu);
			out.print(allObject.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断提交的数据是否为空
	 * 
	 * @param appointVariable
	 * @return
	 */
	public boolean judgeIsNull(String appointVariable) {
		boolean b = false;
		if (appointVariable != null && !appointVariable.equals("0") && !appointVariable.equals("")) {
			b = true;
		}
		return b;
	}

	/**
	 * 评级结果查询跳转（客户经理）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public ActionForward rateJumpByM(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		StSystemMaintainForm sysf = (StSystemMaintainForm) form;
		// 获取当前登录用户
		User user = SmUtil.getCurrentUser();
		try {
			String ne = user.getGroupId();
			String name = GroupDao.getInstance().queryValueByAppoint(user.getGroupId(), "NAME");
			sysf.getStRateResultVo().setOrgname(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sysf.getStRateResultVo().setOrgcode(user.getGroupId());
		sysf.setCustmanagername(user.getName());
		sysf.getStRateResultVo().setCustmanager(user.getId());
		return mapping.findForward("RateResultQueryByManager");
	}

	/**
	 * 评级结果查询跳转(机构)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward rateJump(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		StSystemMaintainForm sysf = (StSystemMaintainForm) form;
		// 获取当前登录用户
		User user = SmUtil.getCurrentUser();
		try {
			String instCode = GroupDao.ORG_CODE;
			sysf.setOrgcodeR(instCode);
			sysf.getStRateResultVo().setOrgcode(instCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("RateResultQuery");
	}

	/**
	 * 评级结果查询跳转(部门)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward rateJumpByDept(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		StSystemMaintainForm sysf = (StSystemMaintainForm) form;
		// 获取当前登录用户
		User user = SmUtil.getCurrentUser();
		sysf.setOrgcodeR(user.getGroupId());
		sysf.getStRateResultVo().setOrgcode(user.getGroupId());
		return mapping.findForward("RateResultQueryByDept");
	}

	/**
	 * 评级查询(机构)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward rateQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		StSystemMaintainForm systemForm = (StSystemMaintainForm) form;
		// 起止日期
		String dateFrom = systemForm.getDateFrom();
		if (judgeIsNull(systemForm.getStRateResultVo().getCustname())) {
			try {
				String name = URLDecoder.decode(systemForm.getCustname(), "UTF-8");
				systemForm.getStRateResultVo().setCustname(name.trim());
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		// 结尾日期
		String dateTo = systemForm.getDateTo();
		systemForm.getStRateResultVo().setIdnumber(systemForm.getStRateResultVo().getIdnumber().trim());
		systemForm.getStRateResultVo().setCustcode(systemForm.getStRateResultVo().getCustcode().trim());
		StRateResult stRateResult = new StRateResult();
		stRateResult.setCustcode(systemForm.getStRateResultVo().getCustcode().trim());
		stRateResult.setCustname(systemForm.getStRateResultVo().getCustname().trim());
		stRateResult.setIdtype(systemForm.getStRateResultVo().getIdtype());
		stRateResult.setIdnumber(systemForm.getStRateResultVo().getIdnumber().trim());
		stRateResult.setRatecode(systemForm.getStRateResultVo().getRatecode());
		try {
			List<String> orgs = new ArrayList<String>();
			// 得到机构编号
			String orgcode = systemForm.getStRateResultVo().getOrgcode();
			// 获取该机构下的所有子机构并判断机构编号是否为空
			if (judgeIsNull(orgcode)) {
				orgs.add(orgcode);
/*				Group group = BaseGroupManager.getInstance().getGroup(orgcode);
				Iterator lists = BaseGroupManager.getInstance().getChildGroups(group, false);
				while (lists.hasNext()) {
					Group gr = (Group) lists.next();
					orgs.add(gr.getId());
				}*/
			}
			List<StRateResultVo> srlist = stSystemMaintainService.querybymodel(stRateResult, orgs, dateFrom, dateTo);
			if (null != srlist && srlist.size() > 0) {
				for (StRateResultVo srv : srlist) {
					// 获取评级机构名
					srv.setOrgname(GroupDao.getInstance().queryValueByAppoint(srv.getOrgcode(), "NAME"));
					// 获取评级客户经理名
					srv.setCustmanagername(SmUtil.getCurrentUser().getName());
				}
			}
			// 返回前台集合
			List<StRateResultVo> srlists = new ArrayList<StRateResultVo>();
			systemForm.setRatepara("true");
			if (null != srlist && srlist.size() > 0) {
				// 获取上传的页数
				int page = systemForm.getFooter().getPage();
				// 获取每页的条数
				int pagesize = systemForm.getFooter().getPageSize();
				// 数据上限
				int up = page * pagesize > srlist.size() ? srlist.size() : page * pagesize;
				for (int i = (page - 1) * pagesize; i < up; i++) {
					srlists.add(srlist.get(i));

				}
				systemForm.getFooter().setCount(srlist.size());
				systemForm.getFooter().setDataArray(srlists);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("RateResultQuery");
	}

	/**
	 * 评级查询(部门)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward rateQueryBydept(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		StSystemMaintainForm systemForm = (StSystemMaintainForm) form;
		// 起止日期
		String dateFrom = systemForm.getDateFrom();
		if (judgeIsNull(systemForm.getStRateResultVo().getCustname())) {
			try {
				String name = URLDecoder.decode(systemForm.getCustname(), "UTF-8");
				systemForm.getStRateResultVo().setCustname(name.trim());
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		// 结尾日期
		String dateTo = systemForm.getDateTo();
		systemForm.getStRateResultVo().setIdnumber(systemForm.getStRateResultVo().getIdnumber().trim());
		systemForm.getStRateResultVo().setCustcode(systemForm.getStRateResultVo().getCustcode().trim());
		StRateResult stRateResult = new StRateResult();
		stRateResult.setCustcode(systemForm.getStRateResultVo().getCustcode().trim());
		stRateResult.setCustname(systemForm.getStRateResultVo().getCustname().trim());
		stRateResult.setIdtype(systemForm.getStRateResultVo().getIdtype());
		stRateResult.setIdnumber(systemForm.getStRateResultVo().getIdnumber().trim());
		stRateResult.setRatecode(systemForm.getStRateResultVo().getRatecode());
		try {
			List<String> orgs = new ArrayList<String>();
			// 得到机构编号
			String orgcode = systemForm.getStRateResultVo().getOrgcode();
			// 获取该机构下的所有子机构并判断机构编号是否为空
			if (judgeIsNull(orgcode)) {
				orgs.add(orgcode);
/*				Group group = BaseGroupManager.getInstance().getGroup(orgcode);
				Iterator lists = BaseGroupManager.getInstance().getChildGroups(group, false);
				while (lists.hasNext()) {
					Group gr = (Group) lists.next();
					orgs.add(gr.getId());
				}*/
			}
			List<StRateResultVo> srlist = stSystemMaintainService.querybymodel(stRateResult, orgs, dateFrom, dateTo);
			if (null != srlist && srlist.size() > 0) {
				for (StRateResultVo srv : srlist) {
					// 获取评级机构名
					srv.setOrgname(GroupDao.getInstance().queryValueByAppoint(srv.getOrgcode(), "NAME"));
					// 获取评级客户经理名
					srv.setCustmanagername(SmUtil.getCurrentUser().getName());
				}
			}
			// 返回前台集合
			List<StRateResultVo> srlists = new ArrayList<StRateResultVo>();
			systemForm.setRatepara("true");
			if (null != srlist && srlist.size() > 0) {
				// 获取上传的页数
				int page = systemForm.getFooter().getPage();
				// 获取每页的条数
				int pagesize = systemForm.getFooter().getPageSize();
				// 数据上限
				int up = page * pagesize > srlist.size() ? srlist.size() : page * pagesize;
				for (int i = (page - 1) * pagesize; i < up; i++) {
					srlists.add(srlist.get(i));

				}
				systemForm.getFooter().setCount(srlist.size());
				systemForm.getFooter().setDataArray(srlists);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("RateResultQueryByDept");
	}

	/**
	 * 评级查询（客户经理）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward rateQueryByM(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		StSystemMaintainForm systemForm = (StSystemMaintainForm) form;
		// 获取当前登录用户
		User user = SmUtil.getCurrentUser();
		// 起止日期
		String dateFrom = systemForm.getDateFrom();
		if (judgeIsNull(systemForm.getStRateResultVo().getCustname())) {
			try {
				String name = URLDecoder.decode(systemForm.getCustname(), "UTF-8");
				systemForm.getStRateResultVo().setCustname(name.trim());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		// 结尾日期
		String dateTo = systemForm.getDateTo();
		systemForm.getStRateResultVo().setIdnumber(systemForm.getStRateResultVo().getIdnumber().trim());
		systemForm.getStRateResultVo().setCustcode(systemForm.getStRateResultVo().getCustcode().trim());
		StRateResult stRateResult = new StRateResult();
		stRateResult.setCustcode(systemForm.getStRateResultVo().getCustcode());
		stRateResult.setCustname(systemForm.getStRateResultVo().getCustname());
		stRateResult.setIdtype(systemForm.getStRateResultVo().getIdtype());
		stRateResult.setIdnumber(systemForm.getStRateResultVo().getIdnumber());
		stRateResult.setRatecode(systemForm.getStRateResultVo().getRatecode());
		try {
			List<StRateResultVo> srlist = null;
			List<String> orgs = new ArrayList<String>();
			if (systemForm.getCustMP().equals(YesNo.no.getCode())) {
				// 查询主办客户经理的客户明细
				orgs.add(SmUtil.getCurrentUser().getId());
				srlist = stSystemMaintainService.querybyM(stRateResult, orgs, dateFrom, dateTo);
			} else if (systemForm.getCustMP().equals(YesNo.yes.getCode())) {
				stRateResult.setCustmanager(user.getId());
				srlist = stSystemMaintainService.querybyM(stRateResult, orgs, dateFrom, dateTo);
			}
			if (null != srlist && srlist.size() > 0) {
				for (StRateResultVo srv : srlist) {
					// 获取评级机构名
					srv.setOrgname(GroupDao.getInstance().queryValueByAppoint(srv.getOrgcode(), "NAME"));
					String custma = srv.getCustmanager();
					// 获取评级客户经理名
					srv.setCustmanagername(SmUtil.getCurrentUser().getName());
				}
			}
			// 返回前台集合
			List<StRateResultVo> srlists = new ArrayList<StRateResultVo>();
			systemForm.setRatepara("true");
			if (null != srlist && srlist.size() > 0) {
				// 获取上传的页数
				int page = systemForm.getFooter().getPage();
				// 获取每页的条数
				int pagesize = systemForm.getFooter().getPageSize();
				// 数据上限
				int up = page * pagesize > srlist.size() ? srlist.size() : page * pagesize;
				for (int i = (page - 1) * pagesize; i < up; i++) {
					srlists.add(srlist.get(i));

				}
				systemForm.getFooter().setCount(srlist.size());
				systemForm.getFooter().setDataArray(srlists);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("RateResultQueryByManager");
	}

/*	*//**
	 * 评级数据查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 *//*
	public ActionForward rateDataQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		StSystemMaintainForm systemForm = (StSystemMaintainForm) form;
		// 查询评级数据
		StRateresultData sr = new StRateresultData();
		sr.setRateresultcode(systemForm.getStRateresultDataVo().getRateresultcode());
		sr.getPageListData().setPageSize(Integer.MAX_VALUE);
		try {
			List<StRateresultDataVo> rvlist = stSystemMaintainService.queryListByModel(sr);
			if (null != rvlist && rvlist.size() > 0) {
				List<StRateresultDataVo> rvlists = new ArrayList<StRateresultDataVo>();
				for (StRateresultDataVo srv : rvlist) {
					if ("1".equals(srv.getIsenum())) {
						try {
							Map map = SmTools.getParamListInfo(srv.getDictcode());
							Iterator it = map.entrySet().iterator();
							while (it.hasNext()) {
								Map.Entry entry = (Map.Entry) it.next();
								if (entry.getKey().equals(srv.getDatavalue())) {
									srv.setDatavalue((String) entry.getValue());
								}
							}
						} catch (Exception e) {
							List<ParamPopupBean> list = SmTools.getParamsDisplay(srv.getDictcode(), null, null);
							if (list != null && list.size() > 0) {
								for (ParamPopupBean pp : list) {
									if (srv.getDatavalue().equals(pp.getCode()))
										srv.setDatavalue(pp.getName());
								}
							}
						}

					}
					if (StUtil.judgeIsNull(srv.getDatavalue()) && "industry".equals(srv.getDatakey())) {
						String name = SmTools.getParamName(null, srv.getDatavalue());
						srv.setDatavalue(name);
					}
					if (StUtil.judgeIsNull(srv.getDatavalue()) && "productCode".equals(srv.getDatakey())) {
						DictEntryVo dev = DictEntryDao.getInstance().getDictEntryVo("tsBusiType", srv.getDatavalue());
						srv.setDatavalue(dev.getDeName());
					}
					if (StUtil.judgeIsNull(srv.getDatavalue()) && "smallProduct".equals(srv.getDatakey())) {
						// 查询产品中类
						List<ParamPopupBean> lists = new SmTools().getParamsDisplay(busyname, productco, null);
						for (ParamPopupBean ppb : lists) {
							if (ppb.getCode().equals(srv.getDatavalue())) {
								srv.setDatavalue(ppb.getName());
							}
						}
					}
					if (StUtil.strIsDouble(srv.getDatavalue())) {
						srv.setDatavalue(StUtil.formatAmount(srv.getDatavalue()));
					}
					rvlists.add(srv);
				}
				List<RateShowVo> rvs = new ArrayList<RateShowVo>();
				
				 * for(int i=0 ; i<rvlists.size() ; i = i+2 ){ RateShowVo rs =
				 * new RateShowVo(); rs.setSrd1(rvlists.get(i)); if(i+1 <
				 * rvlists.size()){ rs.setSrd2(rvlists.get(i+1)); } rvs.add(rs);
				 * }
				 
				for (int i = rvlists.size() - 1; i >= 0; i = i - 2) {
					RateShowVo rs = new RateShowVo();
					rs.setSrd1(rvlists.get(i));
					if (i - 1 >= 0) {
						rs.setSrd2(rvlists.get(i - 1));
					}
					rvs.add(rs);
				}
				systemForm.setRsList(rvs);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("RateDetailInfo");
	}*/

	/**
	 * 评级分数详细查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward rateScoreQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		StSystemMaintainForm systemForm = (StSystemMaintainForm) form;
		systemForm.getStRateresultDataVo().setRateresultcode(systemForm.getStRateResultVo().getRateresultcode());
		try {
			stSystemMaintainService.rateDetailQuery(systemForm);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("RateScoreDetailInfo");
	}

	/**
	 * 评级分数详细数据导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward rateScoreExport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StSystemMaintainForm systemForm = (StSystemMaintainForm) form;

		stSystemMaintainService.rateScoreExport(request, response,systemForm);

		/*
		 * response.setContentType(responsecode); PrintWriter out =
		 * response.getWriter(); JsonObject allObject = new JsonObject();
		 * allObject.addProperty("retu", "导出成功");
		 * out.print(allObject.toString()); out.flush();
		 */
		return null;
	}

	/**
	 * 规则统计数据页面跳转,按机构
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ruleStatisticsJumpByOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		StSystemMaintainForm sysf = (StSystemMaintainForm) form;
		// 获取当前登录用户
		User user = SmUtil.getCurrentUser();
		try {
			String instCode = GroupDao.ORG_CODE;
			sysf.setOrgcodeR(instCode);
			sysf.getStRateResultVo().setOrgcode(instCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("ruleStatisticsByOrg");
	}

	/**
	 * 规则统计数据页面跳转,按部门
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ruleStatisticsJumpByDept(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		StSystemMaintainForm sysf = (StSystemMaintainForm) form;
		// 获取当前登录用户
		User user = SmUtil.getCurrentUser();
		sysf.setOrgcodeR(user.getGroupId());
		sysf.getStRateResultVo().setOrgcode(user.getGroupId());
		return mapping.findForward("ruleStatisticsByDept");
	}

	/**
	 * 规则统计数据excel导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ruleStatisticsExport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		stSystemMaintainService.ruleStatisticsExport(request, response);
		return null;
	}

}
