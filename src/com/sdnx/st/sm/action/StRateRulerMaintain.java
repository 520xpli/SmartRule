package com.sdnx.st.sm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.JsonObject;
import com.sdnx.st.util.StServiceFactory;
import com.sdnx.st.constants.YesNo;
import com.sdnx.st.sm.model.StRateRuler;
import com.sdnx.st.sm.service.StRateRulerService;
import com.sdnx.st.sm.utils.SmUtil;
import com.sdnx.st.sm.utils.SmUtil.User;
import com.sdnx.st.sm.vo.StRateRulerVo;

public class StRateRulerMaintain extends DispatchAction{
	private StRateRulerService stRateRulerService = StServiceFactory.getRateRulerService();
	private String result=null;
	private static Logger logger = Logger.getLogger(StRateRulerMaintain.class);
	/**
	 * 评级标尺维护入口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward jump(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		StRateRulerMaintainForm rate = (StRateRulerMaintainForm)form;
		StRateRuler rr = new StRateRuler();
		rr.setIseffect(YesNo.yes.getCode());
		try {
			List<StRateRulerVo> ratevolist = stRateRulerService.queryListByModel(rr);
			if(ratevolist.size() > 0 &null != ratevolist){
				rate.getStRateRulerVo().setRateList(ratevolist);
			}else{
				//初始化数据
				StRateRulerVo ratevo1 = new StRateRulerVo();
				ratevo1.setRatecode("10");
				StRateRulerVo ratevo2 = new StRateRulerVo();
				ratevo2.setRatecode("9");
				StRateRulerVo ratevo3 = new StRateRulerVo();
				ratevo3.setRatecode("8");
				StRateRulerVo ratevo4 = new StRateRulerVo();
				ratevo4.setRatecode("7");
				StRateRulerVo ratevo5 = new StRateRulerVo();
				ratevo5.setRatecode("6");
				StRateRulerVo ratevo6 = new StRateRulerVo();
				ratevo6.setRatecode("5");
				StRateRulerVo ratevo7 = new StRateRulerVo();
				ratevo7.setRatecode("4");
				StRateRulerVo ratevo8 = new StRateRulerVo();
				ratevo8.setRatecode("3");
				StRateRulerVo ratevo9 = new StRateRulerVo();
				ratevo9.setRatecode("2");
				StRateRulerVo ratevo10 = new StRateRulerVo();
				ratevo10.setRatecode("1");
				rate.getStRateRulerVo().getRateList().add(ratevo1);rate.getStRateRulerVo().getRateList().add(ratevo2);rate.getStRateRulerVo().getRateList().add(ratevo3);
				rate.getStRateRulerVo().getRateList().add(ratevo4);rate.getStRateRulerVo().getRateList().add(ratevo5);rate.getStRateRulerVo().getRateList().add(ratevo6);
				rate.getStRateRulerVo().getRateList().add(ratevo7);rate.getStRateRulerVo().getRateList().add(ratevo8);rate.getStRateRulerVo().getRateList().add(ratevo9);
				rate.getStRateRulerVo().getRateList().add(ratevo10);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("stRateRuler");
	}
	/**
	 * 保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward insert(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		StRateRulerMaintainForm rate = (StRateRulerMaintainForm)form;
		//设置响应编码
		response.setContentType("application/json;charset=UTF-8");
		//获取当前登陆用户
		User user = SmUtil.getCurrentUser();
		//获取上传的数据
		List<StRateRulerVo> ratevolist = rate.getStRateRulerVo().getRateList();
		try {
			//TransactionManager.beginTransaction();
			if(null != ratevolist && ratevolist.size()>0){
				for(StRateRulerVo ratevo:ratevolist){
					StRateRuler sr = new StRateRuler();
					sr.setRatecode(ratevo.getRatecode());
					sr.setIseffect(YesNo.yes.getCode());
						//获取数据库中的数据
						List<StRateRulerVo> rateList = stRateRulerService.queryListByModel(sr);
							if(rateList.size()>0 & null != rateList){
								StRateRuler sra = new StRateRuler();
								sra.setId(rateList.get(0).getId());
								sra.setIseffect(YesNo.no.getCode());
								sra.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
								sra.setEndtime(new java.sql.Timestamp(new Date().getTime()));
								sra.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
								stRateRulerService.update(sra);
							}
							//添加数据
							StRateRuler srr = new StRateRuler();
							srr.setRatecode(ratevo.getRatecode());
							srr.setIseffect(YesNo.yes.getCode());
							srr.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
							srr.setCreateusercode(user.getId());
							srr.setEndtime(new java.sql.Timestamp(new Date().getTime()));
							srr.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
							srr.setRateup(ratevo.getRateup());
							srr.setRatedown(ratevo.getRatedown());
							stRateRulerService.add(srr);
							result="保存成功";
					}
				}
			//TransactionManager.commitTransaction();
			JsonObject o = new JsonObject();
			o.addProperty("result",result);
			PrintWriter out = response.getWriter();
			out.write(o.toString());
			out.flush();
			} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
					try {
						//TransactionManager.rollbackTransaction();
					} catch (Exception e1) {
						logger.error(e1);
					}
				}
		return mapping.findForward("stRateRuler");
	}
}
