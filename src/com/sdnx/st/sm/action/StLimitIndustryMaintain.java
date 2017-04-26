package com.sdnx.st.sm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.JsonObject;
import com.sdnx.st.sm.model.StLimitIndustry;
import com.sdnx.st.sm.service.StLimitIndustryService;
import com.sdnx.st.util.StServiceFactory;

public class StLimitIndustryMaintain extends DispatchAction{
	
	private StLimitIndustryService industryService = StServiceFactory.getLimiIndustyService();
	
	public ActionForward jump(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		StLimitIndustryForm slf = (StLimitIndustryForm) form;
		slf.setCurrentTime(String.valueOf(new Date().getTime()));
		return mapping.findForward("stLimitIndustry");
	}
	
	public ActionForward query(StLimitIndustry limitIndustry){
		return null;
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		StLimitIndustryForm slf = (StLimitIndustryForm) form;
		String result = industryService.add(slf.getCode(), request);
		JsonObject o = new JsonObject();
		o.addProperty("result", result);
		PrintWriter out = response.getWriter();
		out.print(o.toString());
		out.flush();
		return null;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		StLimitIndustryForm slf = (StLimitIndustryForm) form;
		String result = industryService.delete(slf.getCode());
		JsonObject o = new JsonObject();
		o.addProperty("result", result);
		PrintWriter out = response.getWriter();
		out.print(o.toString());
		out.flush();
		return null;
	}
	
	//查询所有行业树
	public ActionForward queryAllTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		String json = industryService.queryAllTree();
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		return null;
	}
	
	//查询限控行业
	public ActionForward queryLimitIndustryTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		String json = industryService.queryLimitIndustryTree();
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		return null;
	}
}
