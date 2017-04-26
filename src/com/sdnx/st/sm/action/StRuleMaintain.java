package com.sdnx.st.sm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.JsonObject;
import com.sdnx.st.constants.RuleClass;
import com.sdnx.st.constants.YesNo;
import com.sdnx.st.sm.base.AbstractBaseDao;
import com.sdnx.st.sm.model.StStructrue;
import com.sdnx.st.sm.service.StRuleMaintainService;
import com.sdnx.st.sm.vo.StStructrueVo;
import com.sdnx.st.util.StServiceFactory;

public class StRuleMaintain extends DispatchAction{
	
	private StRuleMaintainService ruleMaintainService = StServiceFactory.getRuleMaintainService();
	private static Logger logger = Logger.getLogger(StRuleMaintain.class);
	
	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		StRuleForm srf = (StRuleForm) form;
		//���÷��ض���
		String[] result = new String[2];
		try {
//			TransactionManager.beginTransaction();
			if(srf.getStrucVo().getSeq() != null){
				//�ж�id�Ƿ�Ϊ��
				if(srf.getStrucVo().getId()!=null && !"".equals(srf.getStrucVo().getId())){
					StStructrue ss = new StStructrue();
					ss.setParentstruccode(srf.getStrucVo().getParentstruccode());
					ss.setSeq(srf.getStrucVo().getSeq().shortValue());
					ss.setStruccode(srf.getStrucVo().getStruccode());
					List<StStructrueVo> lists = ruleMaintainService.queryListByModel(ss);
					//����ִ��˳�򣬸����ṹ���ϴ�id�ж�seq�Ƿ�ı�
					if(lists.size()>0 && lists != null){
						result = ruleMaintainService.insertRule(srf);
					}else{
						StStructrue sst = new StStructrue();
						sst.setParentstruccode(srf.getStrucVo().getParentstruccode());
						sst.setSeq(srf.getStrucVo().getSeq().shortValue());
						List<StStructrueVo> ssvo = ruleMaintainService.queryListByModel(sst);
						//�ж��޸ĺ��ִ��˳���Ƿ��ͻ
						if(ssvo.size()>0 && ssvo != null){
							result[0] = YesNo.no.getCode();
							result[1] = "ִ��˳���ͻ����������д";
						}else{
							result = ruleMaintainService.insertRule(srf);
						}
					}
				}
				//��������
				else{
					StStructrue structrue = new StStructrue();
					structrue.setParentstruccode(srf.getStrucVo().getParentstruccode());
					if(srf.getStrucVo().getSeq().shortValue()>0){
						structrue.setSeq(srf.getStrucVo().getSeq().shortValue());
						List<StStructrueVo> sts = ruleMaintainService.queryListByModel(structrue);
						//ͨ�������ṹ���ϴ���ִ��˳���ж�ִ��˳���Ƿ��ͻ
						if(sts.size()>0 && sts != null){
							result[0] = YesNo.no.getCode();
							result[1] = "ִ��˳���ͻ����������д";
						}else{
							result = ruleMaintainService.insertRule(srf);
						}
					}else{
						result[0] = YesNo.no.getCode();
						result[1] = "ִ��˳����Ϊ0";
					}
				}
			}else{
				result = ruleMaintainService.insertRule(srf);
			}
//			TransactionManager.commitTransaction();
		} catch (Exception e) {
			result[0] = YesNo.no.getCode();
			result[1] = "���ݿ�����쳣";
			logger.error(e);
			try {
//				TransactionManager.rollbackTransaction();
			} catch (Exception e1) {
				logger.error(e1);
			}
		}
		JsonObject o = new JsonObject();
		o.addProperty("success", result[0]);
		o.addProperty("result", result[1]);
		PrintWriter out = response.getWriter();
		out.write(o.toString());
		out.flush();
		return null;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		StRuleForm srf = (StRuleForm) form;
		String result = ruleMaintainService.deleteRule(srf);
		JsonObject o = new JsonObject();
		o.addProperty("result", result);
		PrintWriter out = response.getWriter();
		out.write(o.toString());
		out.flush();
		return null;
	}
	
	public ActionForward editJump(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionForward forward = null;
		StRuleForm srf = (StRuleForm) form;
		if(srf.getClassification().equals(RuleClass.admit.getCode())){
			forward = mapping.findForward("admit");
		}
		else if(srf.getClassification().equals(RuleClass.limitAmount.getCode())){
			forward = mapping.findForward("limitAmount");
		}
		else if(srf.getClassification().equals(RuleClass.rate.getCode())){
			forward = mapping.findForward("rate");
		}
		else if(srf.getClassification().equals(RuleClass.firstTrial.getCode())){
			forward = mapping.findForward("firstTrial");
		}
		else if(srf.getClassification().equals(RuleClass.lastTrial.getCode())){
			forward = mapping.findForward("lastTrial");
		}
		else if(srf.getClassification().equals(RuleClass.ProcessTrend.getCode())){
			forward = mapping.findForward("process");
		}
		ruleMaintainService.queryStrucInfo(srf);
		return forward;
	}
	
	public ActionForward querySubTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		StRuleForm srf = (StRuleForm) form;
		String json = ruleMaintainService.querySubTree(srf.getModelcode(), srf.getIsShowCode());
		PrintWriter out = response.getWriter();
//		String json = "{identifier:'name',label:'name',items:[{name:'person',children:[{name:'hehe',children:[]},{name:'haha'}]},{name:'location'}]}";
		out.write(json);
		out.flush();
		return null;
	}
	
	public ActionForward queryExistProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		StRuleForm srf = (StRuleForm) form;
		String json = ruleMaintainService.queryExistProduct(srf.getModelcode());
		PrintWriter out = response.getWriter();
//		String json = "{identifier:'name',label:'name',items:[{name:'person',children:[{name:'hehe'},{name:'haha'}]},{name:'location'}]}";
		out.write(json);
		out.flush();
		return null;
	}
	
	public ActionForward queryNotExistProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		StRuleForm srf = (StRuleForm) form;
		String json = ruleMaintainService.queryNotExistProduct(srf.getModelcode());
		PrintWriter out = response.getWriter();
//		String json = "{identifier:'name',label:'name',items:[{name:'person',children:[{name:'hehe'},{name:'haha'}]},{name:'location'}]}";
		out.write(json);
		out.flush();
		return null;
	}
	
	public ActionForward queryProerty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		StRuleForm srf = (StRuleForm) form;
		System.out.println(request.getParameter("operAtt"));
		System.out.println(request.getParameter("isShowCode"));
		String json = ruleMaintainService.queryPorperty(srf.getStrucVo().getStRuleVo().getObjectcode(), srf.getIsShowCode());
		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();
		return null;
	}
	
	
	public ActionForward popupProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		StRuleForm srf = (StRuleForm) form;
		srf.setTreeUrl(srf.getTreeUrl() + "&modelcode=" + srf.getModelcode());
		return mapping.findForward("product");
	}
	
	public ActionForward queryProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		StRuleForm srf = (StRuleForm) form;
		response.setContentType("application/json;charset=UTF-8");
		String	productJson = ruleMaintainService.queryProduct(srf.getModelcode());
		PrintWriter out = response.getWriter();
		out.write(productJson);
		out.flush();
		return null;
	}
	
	public ActionForward queryLittleProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		String	productJson = ruleMaintainService.queryLittleProduct();
		PrintWriter out = response.getWriter();
		out.write(productJson);
		out.flush();
		return null;
	}
	
	public ActionForward limitAmountAddProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		StRuleForm srf = (StRuleForm) form;
		response.setContentType("application/json;charset=UTF-8");
		String result = ruleMaintainService.copyProductLimitAmount(srf.getStrucVo());
		PrintWriter out = response.getWriter();
		out.write(result);
		out.flush();
		return null;
	}
	
	public ActionForward update(StStructrue stStructrue){
		return null;
	}
	
	public ActionForward test(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		System.out.println("testSuccess");
//		ruleMaintainService.querySingleRule(2l);
		return mapping.findForward("success");
	}
}
