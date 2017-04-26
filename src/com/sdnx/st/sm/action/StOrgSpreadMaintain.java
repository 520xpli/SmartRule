package com.sdnx.st.sm.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.JsonObject;

import com.sdnx.st.constants.YesNo;
import com.sdnx.st.sm.model.StOrgSpread;
import com.sdnx.st.sm.service.StOrgSpreadService;
import com.sdnx.st.sm.utils.Group;
import com.sdnx.st.sm.utils.GroupDao;
import com.sdnx.st.sm.utils.SmUtil;
import com.sdnx.st.sm.utils.SmUtil.User;
import com.sdnx.st.sm.vo.StOrgSpreadVo;
import com.sdnx.st.util.StServiceFactory;

public class StOrgSpreadMaintain extends DispatchAction{
	private StOrgSpreadService stOrgSpreadService = StServiceFactory.getOrgSpreadService();
	private String orgname = "NAME";
	private static Logger logger = Logger.getLogger(StSystemMaintain.class);
	//响应编码
	private String responsecode = "application/json;charset=UTF-8";
	//获取当前系统时间
	private String result = "";
	//页面入口跳转（省联社）
	public ActionForward jump(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String forward = "orgSpread";
		StOrgSpreadMaintainForm sf = (StOrgSpreadMaintainForm) form;
		User user = SmUtil.getCurrentUser();
		String groupId = user.getGroupId();
		List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
		try {
			List<Group> groups = GroupDao.getInstance().queryCityGroupList(groupId);
			if(groups.size()>0){
				for(int i=0;i<groups.size();i++){
					String id = groups.get(i).getId();
					String name = GroupDao.getInstance().queryValueByAppoint(id, orgname);
					StOrgSpreadVo orgs = new StOrgSpreadVo();
					orgs.setOrgcode(id);
					orgs.setOrgname(name);
					orgVos.add(orgs);
				}
			}
			int orgNum = orgVos.size();
			sf.setOrgNum(orgNum);
			sf.setStOrgList(orgVos);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}
	//页面入口跳转（法人）
	public ActionForward legaljump(ActionMapping mapping,ActionForm form,
				HttpServletRequest request, HttpServletResponse response){
			String forward = "legalOrgSpread";
			StOrgSpreadMaintainForm sf = (StOrgSpreadMaintainForm) form;
			User user = SmUtil.getCurrentUser();
			String groupId = user.getGroupId();
			List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
			try {
				List<Group> groups = GroupDao.getInstance().queryCityGroupList(groupId);
				if(groups.size()>0){
					for(int i=0;i<groups.size();i++){
						String id = groups.get(i).getId();
						String name = GroupDao.getInstance().queryValueByAppoint(id, orgname);
						StOrgSpreadVo orgs = new StOrgSpreadVo();
						orgs.setOrgcode(id);
						orgs.setOrgname(name);
						orgVos.add(orgs);
					}
				}
				int orgNum = orgVos.size();
				sf.setOrgNum(orgNum);
				sf.setStOrgList(orgVos);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mapping.findForward(forward);
		}
	/**
	 * 查询市联社机构下的所有法人机构（省联社）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 */
	public ActionForward queryCorpOrgByOfficeId(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
			ActionErrors errors = new ActionErrors();
			StOrgSpreadMaintainForm stOrgForm = (StOrgSpreadMaintainForm) form;
			//获取前台传上来的机构号 
			String orgcode = stOrgForm.getOrgcode();
			//获取当前操作用户
			User user = SmUtil.getCurrentUser();
			String groupId = user.getGroupId();
			//查询条件返回集合
			List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
			//法人机构查询条件返回集合
			List<StOrgSpreadVo> orgVos1 = new ArrayList<StOrgSpreadVo>();
			try {
				List<Group> groups = GroupDao.getInstance().queryCityGroupList(groupId);
				if(groups.size()>0){
					for(int i=0;i<groups.size();i++){
						String id = groups.get(i).getId();
						String name = GroupDao.getInstance().queryValueByAppoint(id, orgname);
						StOrgSpreadVo orgs = new StOrgSpreadVo();
						orgs.setOrgcode(id);
						orgs.setOrgname(name);
						orgVos.add(orgs);
					}
				}
			//获取上传的页数
				int page = stOrgForm.getFooter().getPage();
			//获取每页的条数
				int pagesize = stOrgForm.getFooter().getPageSize();
			//根据机构号查询出所有的法人机构,返回一个集合
			List<String> orgIds = new ArrayList<String>();
		    GroupDao.getInstance().queryOfficeUnitFrGroupList(orgcode,orgIds);
		    stOrgForm.getFooter().setCount(orgIds.size());
		    //数据上限
			int up = page * pagesize > orgIds.size() ? orgIds.size() : page * pagesize;
				if(orgIds.size()>0){
					for(int i=(page-1)*pagesize;i<up;i++){
						String id=orgIds.get(i);
						String name = GroupDao.getInstance().queryValueByAppoint(id, orgname);
						StOrgSpreadVo orgs = new StOrgSpreadVo();
						orgs.setOrgcode(id);
						orgs.setOrgname(name);
						StOrgSpread sts = new StOrgSpread();
						sts.setOrgcode(id);
						List<StOrgSpread> lists =stOrgSpreadService.queryListByModel(sts);
						if(lists.size()>0){
							orgs.setId(lists.get(0).getId());
							orgs.setIsopen(lists.get(0).getIsopen());
						}else{
							orgs.setIsopen(YesNo.no.getCode());
						}
						orgVos1.add(orgs);
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!errors.isEmpty()) {
				saveErrors(request, errors);
			}
			stOrgForm.getStOrgSpreadVo().setOrgcode(stOrgForm.getOrgcode());
			int orgNum = orgVos.size();
			stOrgForm.setOrgNum(orgNum);
			stOrgForm.getFooter().setDataArray(orgVos1);
			stOrgForm.setStOrgList(orgVos);
			//跳转地址
			ActionForward forward = mapping.findForward("orgSpread");
		return forward;
	}
	/**
	 * 查询市联社机构下的所有法人机构(法人)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 */
	public ActionForward querylegal(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
			ActionErrors errors = new ActionErrors();
			StOrgSpreadMaintainForm stOrgForm = (StOrgSpreadMaintainForm) form;
			//获取前台传上来的机构号 
			String orgcode = stOrgForm.getOrgcode();
			//获取当前操作用户
			User user = SmUtil.getCurrentUser();
			String groupId = user.getGroupId();
			//查询条件返回集合
			List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
			//法人机构查询条件返回集合
			List<StOrgSpreadVo> orgVos1 = new ArrayList<StOrgSpreadVo>();
			try {
				List<Group> groups = GroupDao.getInstance().queryCityGroupList(groupId);
				if(groups.size()>0){
					for(int i=0;i<groups.size();i++){
						String id = groups.get(i).getId();
						String name = GroupDao.getInstance().queryValueByAppoint(id, orgname);
						StOrgSpreadVo orgs = new StOrgSpreadVo();
						orgs.setOrgcode(id);
						orgs.setOrgname(name);
						orgVos.add(orgs);
					}
				}
			//获取上传的页数
				int page = stOrgForm.getFooter().getPage();
			//获取每页的条数
				int pagesize = stOrgForm.getFooter().getPageSize();
			//根据机构号查询出所有的法人机构,返回一个集合
			List<String> orgIds = new ArrayList<String>();
			//判断是否法人机构
			boolean b = GroupDao.getInstance().isTrueFrFlag(groupId);
			if(b){
				//是法人机构显示当前操作机构
				orgIds.add(groupId);
			}else{
				GroupDao.getInstance().queryOfficeUnitFrGroupList(orgcode,orgIds);
			}
		    stOrgForm.getFooter().setCount(orgIds.size());
		    //数据上限
			int up = page * pagesize > orgIds.size() ? orgIds.size() : page * pagesize;
				if(orgIds.size()>0){
					for(int i=(page-1)*pagesize;i<up;i++){
						String id=orgIds.get(i);
						String name = GroupDao.getInstance().queryValueByAppoint(id, orgname);
						StOrgSpreadVo orgs = new StOrgSpreadVo();
						orgs.setOrgcode(id);
						orgs.setOrgname(name);
						StOrgSpread sts = new StOrgSpread();
						sts.setOrgcode(id);
						List<StOrgSpread> lists =stOrgSpreadService.queryListByModel(sts);
						if(lists.size()>0){
							orgs.setId(lists.get(0).getId());
							orgs.setIsopen(lists.get(0).getIsopen());
						}else{
							orgs.setIsopen(YesNo.no.getCode());
						}
						orgVos1.add(orgs);
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!errors.isEmpty()) {
				saveErrors(request, errors);
			}
			stOrgForm.getStOrgSpreadVo().setOrgcode(stOrgForm.getOrgcode());
			int orgNum = orgVos.size();
			stOrgForm.setOrgNum(orgNum);
			stOrgForm.getFooter().setDataArray(orgVos1);
			stOrgForm.setStOrgList(orgVos);
			//跳转地址
			ActionForward forward = mapping.findForward("legalOrgSpread");
		return forward;
	}
	/**
	 * 查询法人下级机构
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 */
	public ActionForward queryDot(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
			ActionErrors errors = new ActionErrors();
			StOrgSpreadMaintainForm stOrgForm = (StOrgSpreadMaintainForm) form;
			//获取前台传上来的机构号 
			String orgcode = stOrgForm.getOrgcode();
			//获取该法人机构下的所有网点
/*			try {
				Group group =  BaseGroupManager.getInstance().getGroup(orgcode);;
				Iterator lists;
				lists = BaseGroupManager.getInstance().getChildGroupsByTypes(group, new String[]{"UNION"});
				List<StOrgSpreadVo> orgIds = new ArrayList<StOrgSpreadVo>();
				while(lists.hasNext()) {
					Group gr = (Group)lists.next();
					StOrgSpreadVo sosv = new StOrgSpreadVo();
					sosv.setOrgcode(gr.getId());
					sosv.setOrgname(gr.getName());
					orgIds.add(sosv);
				}
				stOrgForm.setStOrgList(orgIds);
			} catch (DataAccessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			if (!errors.isEmpty()) {
				saveErrors(request, errors);
			}
			//跳转地址
			ActionForward forward = mapping.findForward("dotSpread");
		return forward;
	}
	/**
	 * 查询下级网点
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 */
	public ActionForward queryNextDot(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
			ActionErrors errors = new ActionErrors();
			StOrgSpreadMaintainForm stOrgForm = (StOrgSpreadMaintainForm) form;
			//获取前台传上来的法人机构号 
			String orglegal = stOrgForm.getOrgcode();
			//获取查询条件
			String orgcode = stOrgForm.getStOrgSpreadVo().getOrgcode();
			stOrgForm.setOrgCon(orgcode);
			//获取该法人机构下的所有网点
			/*try {
				//获取法人机构的下级机构
				Group grouplegal =  BaseGroupManager.getInstance().getGroup(orglegal);;
				Iterator listsl;
				listsl = BaseGroupManager.getInstance().getChildGroupsByTypes(grouplegal, new String[]{"UNION"});
				List<StOrgSpreadVo> orgIdvs = new ArrayList<StOrgSpreadVo>();
				while(listsl.hasNext()) {
					Group gr = (Group)listsl.next();
					StOrgSpreadVo sosv = new StOrgSpreadVo();
					sosv.setOrgcode(gr.getId());
					sosv.setOrgname(gr.getName());
					orgIdvs.add(sosv);
				}
				//获取下级机构
				Group group =  BaseGroupManager.getInstance().getGroup(orgcode);;
				Iterator lists;
				lists = BaseGroupManager.getInstance().getChildGroups(group, false);
				List<StOrgSpreadVo> orgIds = new ArrayList<StOrgSpreadVo>();
				while(lists.hasNext()) {
					Group gr = (Group)lists.next();
					if(!gr.getChildren().hasNext()){
						StOrgSpreadVo sosv = new StOrgSpreadVo();
						sosv.setOrgcode(gr.getId());
						sosv.setOrgname(gr.getName());
						orgIds.add(sosv);
					}
				}
				//获取上传的页数
				int page = stOrgForm.getFooter().getPage();
			//获取每页的条数
				int pagesize = stOrgForm.getFooter().getPageSize();
				List<StOrgSpreadVo> orgVos1 = new ArrayList<StOrgSpreadVo>();
		    //数据上限
			int up = page * pagesize > orgIds.size() ? orgIds.size() : page * pagesize;
				if(orgIds.size()>0){
					for(int i=(page-1)*pagesize;i<up;i++){
						String code=orgIds.get(i).getOrgcode();
						String name = GroupDao.getInstance().queryValueByAppoint(code, orgname);
						StOrgSpreadVo orgs = new StOrgSpreadVo();
						orgs.setOrgcode(code);
						orgs.setOrgname(name);
						StOrgSpread sts = new StOrgSpread();
						sts.setOrgcode(code);
						List<StOrgSpread> list =stOrgSpreadService.queryListByModel(sts);
						if(list.size()>0){
							orgs.setId(list.get(0).getId());
							orgs.setIsopen(list.get(0).getIsopen());
						}else{
							orgs.setIsopen(YesNo.no.getCode());
						}
						orgVos1.add(orgs);
						
					}
				}
				stOrgForm.getFooter().setCount(orgIds.size()); 
				stOrgForm.getFooter().setDataArray(orgVos1);
				stOrgForm.setStOrgList(orgIdvs);
			} catch (DataAccessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			if (!errors.isEmpty()) {
				saveErrors(request, errors);
			}
			//跳转地址
			ActionForward forward = mapping.findForward("dotSpread");
		return forward;
	}
	/**
	 * 关闭机构
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward closeOrg(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
			//获取前台传上来的机构号 
			StOrgSpreadMaintainForm  sf = (StOrgSpreadMaintainForm)form;
			/*try {
				//TransactionManager.beginTransaction();
			//查询机构下的网点
			Group group =  BaseGroupManager.getInstance().getGroup(sf.getOrgcode());
			Iterator list;
			list = BaseGroupManager.getInstance().getChildGroups(group, false);
			List<String> orgIds = new ArrayList<String>();
			while(list.hasNext()) {
				Group gr = (Group)list.next();
				orgIds.add(gr.getId());
			}
			List<StOrgSpreadVo> dots = stOrgSpreadService.queryDot(orgIds);
			List<StOrgSpread> orgs = new ArrayList<StOrgSpread>();
			if(null != dots && dots.size() > 0){
				for(StOrgSpreadVo ssv : dots){
					StOrgSpread sos = new StOrgSpread();
					sos.setId(ssv.getId());
					sos.setIsopen(YesNo.no.getCode());
					sos.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					sos.setEndtime(new java.sql.Timestamp(new Date().getTime()));
					orgs.add(sos);
				}
				stOrgSpreadService.updateAll(orgs);
			}
			//根据机构编码查询数据
			StOrgSpread stOrgSpread = new StOrgSpread();
			stOrgSpread.setOrgcode(sf.getOrgcode());
				List<StOrgSpread> lists = stOrgSpreadService.queryListByModel(stOrgSpread);
				StOrgSpread  stOrg2 = null;
				if(lists.size()>0){
					//关闭机构
					stOrg2= lists.get(0);
					stOrg2.setOrgname(sf.getOrgname());
					stOrg2.setIsopen(YesNo.no.getCode());
					stOrg2.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					stOrgSpreadService.update(stOrg2);
					result = "关闭成功";
				}
				else{
					stOrg2= new StOrgSpread();
					stOrg2.setIsopen(YesNo.no.getCode());
					stOrg2.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
					//获取当前登录人员
					RBACRequestContext rbacContext = WebRBACRequestContext
							.getRBACRequestContext(request);
					User user = rbacContext.getRequestUser();
					stOrg2.setCreateusercode(user.getId());
					stOrg2.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					stOrg2.setOrgcode(sf.getOrgcode());
					stOrg2.setOrgname(sf.getOrgname());
					stOrg2.setIseffect(YesNo.yes.getCode());
					stOrgSpreadService.add(stOrg2);
					result = "关闭成功";
				}
				TransactionManager.commitTransaction();
			} catch (Exception e) {
				result = "关闭失败";
				e.printStackTrace();
				logger.error(e);
				try {
					TransactionManager.rollbackTransaction();
				} catch (DataAccessException e1) {
					logger.error(e1);
				}
			}*/
			try {
				//封装传递数据到前台
				response.setContentType(responsecode);
				PrintWriter out;
				out = response.getWriter();
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
	 * 开启机构
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward openOrg(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		//获取前台传上来的机构号 
		StOrgSpreadMaintainForm  sf = (StOrgSpreadMaintainForm)form;
		
		
		//根据机构编码查询数据
		StOrgSpread stOrgSpread = new StOrgSpread();
		stOrgSpread.setOrgcode(sf.getOrgcode());
		try {
			List<StOrgSpread> lists = stOrgSpreadService.queryListByModel(stOrgSpread);
			StOrgSpread stOrg2 = null;
			if(lists.size()>0){
				//开启
				stOrg2= lists.get(0);
				StOrgSpread st = new StOrgSpread();
				st.setId(stOrg2.getId());
				st.setOrgname(sf.getOrgname());
				st.setIsopen(YesNo.yes.getCode());
				st.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				stOrgSpreadService.update(st);
				result = "开启成功";
			}else{
				stOrg2= new StOrgSpread();
				stOrg2.setIsopen(YesNo.yes.getCode());
				stOrg2.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
				//获取当前登录人员
				User user = SmUtil.getCurrentUser();
				stOrg2.setCreateusercode(user.getId());
				stOrg2.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				stOrg2.setOrgcode(sf.getOrgcode());
				stOrg2.setOrgname(sf.getOrgname());
				stOrg2.setIseffect(YesNo.yes.getCode());
				stOrgSpreadService.add(stOrg2);
				result = "开启成功";
			}
		} catch (Exception e) {
			result = "开启失败";
			e.printStackTrace();
		}
		try {
			//封装传递数据到前台
			response.setContentType(responsecode);
			PrintWriter out;
			out = response.getWriter();
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
	 * 关闭网点
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward closeDot(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
			//获取前台传上来的机构号 
			StOrgSpreadMaintainForm  sf = (StOrgSpreadMaintainForm)form;
			//根据机构编码查询数据
			StOrgSpread stOrgSpread = new StOrgSpread();
			stOrgSpread.setOrgcode(sf.getOrgcode());
			try {
				String name = GroupDao.getInstance().queryValueByAppoint(sf.getOrgcode(), orgname);
				List<StOrgSpread> lists = stOrgSpreadService.queryListByModel(stOrgSpread);
				StOrgSpread  stOrg2 = null;
				if(lists.size()>0){
					//关闭机构
					stOrg2= lists.get(0);
					stOrg2.setOrgname(name);
					stOrg2.setIsopen(YesNo.no.getCode());
					stOrg2.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					stOrgSpreadService.update(stOrg2);
					result = "关闭成功";
				}
				else{
					stOrg2= new StOrgSpread();
					stOrg2.setIsopen(YesNo.no.getCode());
					stOrg2.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
					//获取当前登录人员
					User user = SmUtil.getCurrentUser();
					stOrg2.setCreateusercode(user.getId());
					stOrg2.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					stOrg2.setOrgcode(sf.getOrgcode());
					stOrg2.setOrgname(name);
					stOrg2.setIseffect(YesNo.yes.getCode());
					stOrgSpreadService.add(stOrg2);
					result = "关闭成功";
				}
			} catch (Exception e) {
				result = "关闭失败";
				e.printStackTrace();
			}
			try {
				//封装传递数据到前台
				response.setContentType(responsecode);
				PrintWriter out;
				out = response.getWriter();
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
	 * 开启网点
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward openDot(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		//获取前台传上来的机构号 
		StOrgSpreadMaintainForm  sf = (StOrgSpreadMaintainForm)form;
		
		
		//根据机构编码查询数据
		StOrgSpread stOrgSpread = new StOrgSpread();
		stOrgSpread.setOrgcode(sf.getOrgcode());
		try {
			String name = GroupDao.getInstance().queryValueByAppoint(sf.getOrgcode(), orgname);
			List<StOrgSpread> lists = stOrgSpreadService.queryListByModel(stOrgSpread);
			StOrgSpread stOrg2 = null;
			if(lists.size()>0){
				//开启
				stOrg2= lists.get(0);
				stOrg2.setOrgname(name);
				stOrg2.setIsopen(YesNo.yes.getCode());
				stOrg2.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				stOrgSpreadService.update(stOrg2);
				result = "开启成功";
			}else{
				stOrg2= new StOrgSpread();
				stOrg2.setIsopen(YesNo.yes.getCode());
				stOrg2.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
				//获取当前登录人员
				User user = SmUtil.getCurrentUser();
				stOrg2.setCreateusercode(user.getId());
				stOrg2.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				stOrg2.setOrgcode(sf.getOrgcode());
				stOrg2.setOrgname(name);
				stOrg2.setIseffect(YesNo.yes.getCode());
				stOrgSpreadService.add(stOrg2);
				result = "开启成功";
			}
		} catch (Exception e) {
			result = "开启失败";
			e.printStackTrace();
		}
		try {
			//封装传递数据到前台
			response.setContentType(responsecode);
			PrintWriter out;
			out = response.getWriter();
			JsonObject allObject = new JsonObject();
			allObject.addProperty("retu", result);
			out.print(allObject.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
