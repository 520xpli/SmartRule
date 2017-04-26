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
	//��Ӧ����
	private String responsecode = "application/json;charset=UTF-8";
	//��ȡ��ǰϵͳʱ��
	private String result = "";
	//ҳ�������ת��ʡ���磩
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
	//ҳ�������ת�����ˣ�
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
	 * ��ѯ����������µ����з��˻�����ʡ���磩
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
			//��ȡǰ̨�������Ļ����� 
			String orgcode = stOrgForm.getOrgcode();
			//��ȡ��ǰ�����û�
			User user = SmUtil.getCurrentUser();
			String groupId = user.getGroupId();
			//��ѯ�������ؼ���
			List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
			//���˻�����ѯ�������ؼ���
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
			//��ȡ�ϴ���ҳ��
				int page = stOrgForm.getFooter().getPage();
			//��ȡÿҳ������
				int pagesize = stOrgForm.getFooter().getPageSize();
			//���ݻ����Ų�ѯ�����еķ��˻���,����һ������
			List<String> orgIds = new ArrayList<String>();
		    GroupDao.getInstance().queryOfficeUnitFrGroupList(orgcode,orgIds);
		    stOrgForm.getFooter().setCount(orgIds.size());
		    //��������
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
			//��ת��ַ
			ActionForward forward = mapping.findForward("orgSpread");
		return forward;
	}
	/**
	 * ��ѯ����������µ����з��˻���(����)
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
			//��ȡǰ̨�������Ļ����� 
			String orgcode = stOrgForm.getOrgcode();
			//��ȡ��ǰ�����û�
			User user = SmUtil.getCurrentUser();
			String groupId = user.getGroupId();
			//��ѯ�������ؼ���
			List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
			//���˻�����ѯ�������ؼ���
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
			//��ȡ�ϴ���ҳ��
				int page = stOrgForm.getFooter().getPage();
			//��ȡÿҳ������
				int pagesize = stOrgForm.getFooter().getPageSize();
			//���ݻ����Ų�ѯ�����еķ��˻���,����һ������
			List<String> orgIds = new ArrayList<String>();
			//�ж��Ƿ��˻���
			boolean b = GroupDao.getInstance().isTrueFrFlag(groupId);
			if(b){
				//�Ƿ��˻�����ʾ��ǰ��������
				orgIds.add(groupId);
			}else{
				GroupDao.getInstance().queryOfficeUnitFrGroupList(orgcode,orgIds);
			}
		    stOrgForm.getFooter().setCount(orgIds.size());
		    //��������
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
			//��ת��ַ
			ActionForward forward = mapping.findForward("legalOrgSpread");
		return forward;
	}
	/**
	 * ��ѯ�����¼�����
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
			//��ȡǰ̨�������Ļ����� 
			String orgcode = stOrgForm.getOrgcode();
			//��ȡ�÷��˻����µ���������
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
			//��ת��ַ
			ActionForward forward = mapping.findForward("dotSpread");
		return forward;
	}
	/**
	 * ��ѯ�¼�����
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
			//��ȡǰ̨�������ķ��˻����� 
			String orglegal = stOrgForm.getOrgcode();
			//��ȡ��ѯ����
			String orgcode = stOrgForm.getStOrgSpreadVo().getOrgcode();
			stOrgForm.setOrgCon(orgcode);
			//��ȡ�÷��˻����µ���������
			/*try {
				//��ȡ���˻������¼�����
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
				//��ȡ�¼�����
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
				//��ȡ�ϴ���ҳ��
				int page = stOrgForm.getFooter().getPage();
			//��ȡÿҳ������
				int pagesize = stOrgForm.getFooter().getPageSize();
				List<StOrgSpreadVo> orgVos1 = new ArrayList<StOrgSpreadVo>();
		    //��������
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
			//��ת��ַ
			ActionForward forward = mapping.findForward("dotSpread");
		return forward;
	}
	/**
	 * �رջ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward closeOrg(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
			//��ȡǰ̨�������Ļ����� 
			StOrgSpreadMaintainForm  sf = (StOrgSpreadMaintainForm)form;
			/*try {
				//TransactionManager.beginTransaction();
			//��ѯ�����µ�����
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
			//���ݻ��������ѯ����
			StOrgSpread stOrgSpread = new StOrgSpread();
			stOrgSpread.setOrgcode(sf.getOrgcode());
				List<StOrgSpread> lists = stOrgSpreadService.queryListByModel(stOrgSpread);
				StOrgSpread  stOrg2 = null;
				if(lists.size()>0){
					//�رջ���
					stOrg2= lists.get(0);
					stOrg2.setOrgname(sf.getOrgname());
					stOrg2.setIsopen(YesNo.no.getCode());
					stOrg2.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					stOrgSpreadService.update(stOrg2);
					result = "�رճɹ�";
				}
				else{
					stOrg2= new StOrgSpread();
					stOrg2.setIsopen(YesNo.no.getCode());
					stOrg2.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
					//��ȡ��ǰ��¼��Ա
					RBACRequestContext rbacContext = WebRBACRequestContext
							.getRBACRequestContext(request);
					User user = rbacContext.getRequestUser();
					stOrg2.setCreateusercode(user.getId());
					stOrg2.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					stOrg2.setOrgcode(sf.getOrgcode());
					stOrg2.setOrgname(sf.getOrgname());
					stOrg2.setIseffect(YesNo.yes.getCode());
					stOrgSpreadService.add(stOrg2);
					result = "�رճɹ�";
				}
				TransactionManager.commitTransaction();
			} catch (Exception e) {
				result = "�ر�ʧ��";
				e.printStackTrace();
				logger.error(e);
				try {
					TransactionManager.rollbackTransaction();
				} catch (DataAccessException e1) {
					logger.error(e1);
				}
			}*/
			try {
				//��װ�������ݵ�ǰ̨
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
	 * ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward openOrg(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		//��ȡǰ̨�������Ļ����� 
		StOrgSpreadMaintainForm  sf = (StOrgSpreadMaintainForm)form;
		
		
		//���ݻ��������ѯ����
		StOrgSpread stOrgSpread = new StOrgSpread();
		stOrgSpread.setOrgcode(sf.getOrgcode());
		try {
			List<StOrgSpread> lists = stOrgSpreadService.queryListByModel(stOrgSpread);
			StOrgSpread stOrg2 = null;
			if(lists.size()>0){
				//����
				stOrg2= lists.get(0);
				StOrgSpread st = new StOrgSpread();
				st.setId(stOrg2.getId());
				st.setOrgname(sf.getOrgname());
				st.setIsopen(YesNo.yes.getCode());
				st.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				stOrgSpreadService.update(st);
				result = "�����ɹ�";
			}else{
				stOrg2= new StOrgSpread();
				stOrg2.setIsopen(YesNo.yes.getCode());
				stOrg2.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
				//��ȡ��ǰ��¼��Ա
				User user = SmUtil.getCurrentUser();
				stOrg2.setCreateusercode(user.getId());
				stOrg2.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				stOrg2.setOrgcode(sf.getOrgcode());
				stOrg2.setOrgname(sf.getOrgname());
				stOrg2.setIseffect(YesNo.yes.getCode());
				stOrgSpreadService.add(stOrg2);
				result = "�����ɹ�";
			}
		} catch (Exception e) {
			result = "����ʧ��";
			e.printStackTrace();
		}
		try {
			//��װ�������ݵ�ǰ̨
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
	 * �ر�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward closeDot(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
			//��ȡǰ̨�������Ļ����� 
			StOrgSpreadMaintainForm  sf = (StOrgSpreadMaintainForm)form;
			//���ݻ��������ѯ����
			StOrgSpread stOrgSpread = new StOrgSpread();
			stOrgSpread.setOrgcode(sf.getOrgcode());
			try {
				String name = GroupDao.getInstance().queryValueByAppoint(sf.getOrgcode(), orgname);
				List<StOrgSpread> lists = stOrgSpreadService.queryListByModel(stOrgSpread);
				StOrgSpread  stOrg2 = null;
				if(lists.size()>0){
					//�رջ���
					stOrg2= lists.get(0);
					stOrg2.setOrgname(name);
					stOrg2.setIsopen(YesNo.no.getCode());
					stOrg2.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					stOrgSpreadService.update(stOrg2);
					result = "�رճɹ�";
				}
				else{
					stOrg2= new StOrgSpread();
					stOrg2.setIsopen(YesNo.no.getCode());
					stOrg2.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
					//��ȡ��ǰ��¼��Ա
					User user = SmUtil.getCurrentUser();
					stOrg2.setCreateusercode(user.getId());
					stOrg2.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					stOrg2.setOrgcode(sf.getOrgcode());
					stOrg2.setOrgname(name);
					stOrg2.setIseffect(YesNo.yes.getCode());
					stOrgSpreadService.add(stOrg2);
					result = "�رճɹ�";
				}
			} catch (Exception e) {
				result = "�ر�ʧ��";
				e.printStackTrace();
			}
			try {
				//��װ�������ݵ�ǰ̨
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
	 * ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward openDot(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		//��ȡǰ̨�������Ļ����� 
		StOrgSpreadMaintainForm  sf = (StOrgSpreadMaintainForm)form;
		
		
		//���ݻ��������ѯ����
		StOrgSpread stOrgSpread = new StOrgSpread();
		stOrgSpread.setOrgcode(sf.getOrgcode());
		try {
			String name = GroupDao.getInstance().queryValueByAppoint(sf.getOrgcode(), orgname);
			List<StOrgSpread> lists = stOrgSpreadService.queryListByModel(stOrgSpread);
			StOrgSpread stOrg2 = null;
			if(lists.size()>0){
				//����
				stOrg2= lists.get(0);
				stOrg2.setOrgname(name);
				stOrg2.setIsopen(YesNo.yes.getCode());
				stOrg2.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				stOrgSpreadService.update(stOrg2);
				result = "�����ɹ�";
			}else{
				stOrg2= new StOrgSpread();
				stOrg2.setIsopen(YesNo.yes.getCode());
				stOrg2.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
				//��ȡ��ǰ��¼��Ա
				User user = SmUtil.getCurrentUser();
				stOrg2.setCreateusercode(user.getId());
				stOrg2.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				stOrg2.setOrgcode(sf.getOrgcode());
				stOrg2.setOrgname(name);
				stOrg2.setIseffect(YesNo.yes.getCode());
				stOrgSpreadService.add(stOrg2);
				result = "�����ɹ�";
			}
		} catch (Exception e) {
			result = "����ʧ��";
			e.printStackTrace();
		}
		try {
			//��װ�������ݵ�ǰ̨
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
