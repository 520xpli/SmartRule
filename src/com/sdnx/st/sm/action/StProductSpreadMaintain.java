package com.sdnx.st.sm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sdnx.st.constants.YesNo;
import com.sdnx.st.sm.model.StProductSpread;
import com.sdnx.st.sm.service.StProductSpreadService;
import com.sdnx.st.sm.utils.GroupDao;
import com.sdnx.st.sm.utils.ParamPopupBean;
import com.sdnx.st.sm.utils.SmTools;
import com.sdnx.st.sm.utils.SmUtil;
import com.sdnx.st.sm.utils.SmUtil.User;
import com.sdnx.st.sm.vo.StProductSpreadVo;
import com.sdnx.st.util.StServiceFactory;

public class StProductSpreadMaintain extends DispatchAction {
	private StProductSpreadService stProductSpreadService = StServiceFactory.getProductSpreadService();
	//响应编码
	private String responsecode = "application/json;charset=UTF-8";
	//产品中类查询条件
	private String busyname = "cmiscc.pCustBusiCode";
	private String productco = "000000097642";
	private String result = "";
	private static Logger logger = Logger.getLogger(StProductSpreadMaintain.class);
	/**
	 * 获取机构下的产品大类
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ActionForward queryProductBigByOrg(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
			//获取前台传上来的机构号 
			StProductSpreadMaintainForm of = (StProductSpreadMaintainForm)form;
				try {
					String name = GroupDao.getInstance().queryValueByAppoint(of.getOrgcode(), "NAME");
					of.setOrgname(name);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			//获取产品中类详情,返回一个集合
			List<StProductSpreadVo> productBig = new ArrayList<StProductSpreadVo>();
			//查询产品中类
			List<ParamPopupBean> lists =new SmTools().getParamsDisplay(busyname,productco, null);
			if(lists.size()>0){
				try {
					for(int i=0;i<lists.size();i++){
						ParamPopupBean ppb = (ParamPopupBean)lists.get(i);
						StProductSpread sps = new StProductSpread();
						sps.setProductcode(ppb.getCode());
						sps.setOrgcode(of.getOrgcode());
						StProductSpreadVo stp = new StProductSpreadVo();
						stp.setProductcode(ppb.getCode());
						stp.setProductname(ppb.getName());
						//产品表查数据(sps)
						List<StProductSpread> stPs = stProductSpreadService.queryListByModel(sps);
						if(stPs.size()>0){
							stp.setId(stPs.get(0).getId());
							stp.setIsopen(stPs.get(0).getIsopen());
						}else{
							stp.setIsopen(YesNo.no.getCode());
						}
						productBig.add(stp);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//拿到集合,传递到前台
			of.getFooter().setCount(productBig.size());
			of.getFooter().setDataArray(productBig);
			ActionForward forward = mapping.findForward("productSpread");
		return forward;
	}
	/**
	 * 获取产品大类下的所有产品小类
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward queryProductSmaByProBig(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 获取前台传上来的产品大类号
		StProductSpreadMaintainForm pf = (StProductSpreadMaintainForm) form;
		try {
			List<StProductSpread> sts = stProductSpreadService.queryProductSmaByProBig(pf.getOrgcode(),
					pf.getProductcode());
			// 封装传递数据到前台
			response.setContentType(responsecode);
			PrintWriter out = response.getWriter();
			JsonObject allObject = new JsonObject();
			JsonArray ja = new JsonArray();
			for (StProductSpread ss : sts) {
				JsonObject o = new JsonObject();
				o.addProperty("id", ss.getId());
				o.addProperty("productcode", ss.getProductcode());
				o.addProperty("productname", ss.getProductname());
				o.addProperty("limitamount", ss.getLimitamount());
				o.addProperty("isopen", ss.getIsopen());
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

	/**
	 * 产品推广保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward savePro(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取前台传上来的产品号
		StProductSpreadMaintainForm stpF = (StProductSpreadMaintainForm) form;
		// 根据条件查询数据库，查询是否有数据存在
		StProductSpread stProductSpread = new StProductSpread();
		stProductSpread.setProductcode(stpF.getProductcode());
		stProductSpread.setOrgcode(stpF.getOrgcode());
		stProductSpread.setProductcentercode(stpF.getProductcentercode());
		try {
			List<StProductSpread> os =stProductSpreadService.queryListByModel(stProductSpread);
			StProductSpread stp = new StProductSpread();
			if (os != null && os.size() > 0) {
				stp.setId(os.get(0).getId());
				if(stpF.getLimitamount().intValue() == -1)
					stp.setLimitamount(null);
				else
					stp.setLimitamount(stpF.getLimitamount());
				stp.setIsopen(stpF.getIsopen());
				stp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
				stp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				stProductSpreadService.updateById(stp);
				result = "保存成功";
			} else {
				stp.setIsopen(stpF.getIsopen());
				stp.setProductname(stpF.getProductname());
				stp.setProductcode(stpF.getProductcode());
				if(stpF.getLimitamount().intValue() == -1)
					stp.setLimitamount(null);
				else
					stp.setLimitamount(stpF.getLimitamount());
				stp.setOrgcode(stpF.getOrgcode());
				stp.setProductcentercode(stpF.getProductcentercode()); 
 				stp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
				//获取当前登录人员
				User user = SmUtil.getCurrentUser();
				stp.setCreateusercode(user.getId());
				stp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				stp.setIsopen(stpF.getIsopen());
				stProductSpreadService.add(stp);
				result = "保存成功";
			}
		} catch (Exception e1) {
			result = "保存失败";
			e1.printStackTrace();
		}
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
	 * 特色产品关闭
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward closePro(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取前台传上来的产品号
		StProductSpreadMaintainForm sf = (StProductSpreadMaintainForm) form;
		StProductSpread stProductS = new StProductSpread();
		stProductS.setProductcode(sf.getProductcode());
		stProductS.setProductcentercode(sf.getProductcentercode());
		stProductS.setOrgcode(sf.getOrgcode()); 
		try {
			List<StProductSpread> os = stProductSpreadService.queryListByModel(stProductS);
			if (os != null && os.size() > 0) {
				StProductSpread stpro = new StProductSpread();
				stpro.setId(os.get(0).getId());
				stpro.setIsopen(YesNo.no.getCode());
				if(sf.getLimitamount().intValue() == -1)
					stpro.setLimitamount(null);
				else
					stpro.setLimitamount(sf.getLimitamount());
				stpro.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				stProductSpreadService.updateById(stpro);
				result = "关闭成功";
			} else {
				//添加数据
				StProductSpread stProductSpread = new StProductSpread();
				stProductSpread.setProductcode(sf.getProductcode());
				stProductSpread.setIsopen(YesNo.no.getCode());
				stProductSpread.setOrgcode(sf.getOrgcode());
				if(sf.getLimitamount().intValue() == -1)
					stProductSpread.setLimitamount(null);
				else
					stProductSpread.setLimitamount(sf.getLimitamount());
				stProductSpread.setProductcentercode(sf.getProductcentercode());
				stProductSpread.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
				//获取当前登录人员
				User user = SmUtil.getCurrentUser();
				stProductSpread.setCreateusercode(user.getId());
				stProductSpread.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				stProductSpread.setProductname(sf.getProductname());
				stProductSpread.setIseffect(YesNo.yes.getCode());
				stProductSpreadService.add(stProductSpread);
				result = "关闭成功";
			}
		} catch (Exception e) {
			result = "关闭失败";
			e.printStackTrace();
		}
		try {
			// 封装传递数据到前台
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
	 * 特色产品开启
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward openPro(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取前台传上来的产品号
		StProductSpreadMaintainForm sf = (StProductSpreadMaintainForm) form;
		try {
			StProductSpread sps = new StProductSpread();
			sps.setProductcode(sf.getProductcode());
			sps.setProductcentercode(sf.getProductcentercode());
			sps.setOrgcode(sf.getOrgcode());
			List<StProductSpread> lists = stProductSpreadService.queryListByModel(sps);
			if(null != lists && lists.size()>0){
				StProductSpread stpro = new StProductSpread();
				stpro.setId(lists.get(0).getId());
				stpro.setIsopen(YesNo.yes.getCode());
				if(sf.getLimitamount().intValue() == -1)
					stpro.setLimitamount(null);
				else
					stpro.setLimitamount(sf.getLimitamount());
				stpro.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				stProductSpreadService.updateById(stpro);
				result = "开启成功";
			} else {
				StProductSpread stProductSpread = new StProductSpread();
				stProductSpread.setProductcode(sf.getProductcode());
				stProductSpread.setIsopen(YesNo.yes.getCode());
				stProductSpread.setOrgcode(sf.getOrgcode());
				stProductSpread.setProductcentercode(sf.getProductcentercode());
				if(sf.getLimitamount().intValue() == -1)
					stProductSpread.setLimitamount(null);
				else
					stProductSpread.setLimitamount(sf.getLimitamount());
				stProductSpread.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
				//获取当前登录人员
				User user = SmUtil.getCurrentUser();
				stProductSpread.setCreateusercode(user.getId());
				stProductSpread.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				stProductSpread.setEndtime(new java.sql.Timestamp(new Date().getTime()));
				stProductSpread.setProductname(sf.getProductname());
				stProductSpread.setIseffect(YesNo.yes.getCode());
				stProductSpreadService.add(stProductSpread);
				result = "开启成功";
			}
		} catch (Exception e) {
			result = "开启失败";
			e.printStackTrace();
		}
		try {
			// 封装传递数据到前台
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
	 * 产品中类关闭
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward closeProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取前台传上来的产品号
		StProductSpreadMaintainForm sf = (StProductSpreadMaintainForm) form;
		try {
			//TransactionManager.beginTransaction();
			//关闭产品中类
			StProductSpread stpro = stProductSpreadService.queryById(sf.getId());
			stpro.setIsopen(YesNo.no.getCode());
			stpro.setLimitamount(sf.getLimitamount()); 
			stpro.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
			stProductSpreadService.update(stpro);
			//通过机构与产品获取特色产品并关闭 
			List<StProductSpread> sts = stProductSpreadService.queryProductSmaByProBig(sf.getOrgcode(),
			sf.getProductcode());
			if(sts.size() > 0 && sts != null){
				for(StProductSpread sps:sts){
					//根据条件查询数据库是否有数据
					StProductSpread stp = new StProductSpread();
					stp.setOrgcode(sf.getOrgcode());
					stp.setProductcentercode(sf.getProductcode());
					stp.setProductcode(sps.getProductcode());
					//调用查询方法，返回集合
					List<StProductSpread> stsvo = stProductSpreadService.queryListByModel(stp);
					//判断数据库是否有数据
					if(stsvo.size()>0 && stsvo !=null){
						for(StProductSpread sp : stsvo){
							//设置更新条件
							StProductSpread ss = new StProductSpread();
							ss.setId(sp.getId());
							ss.setIsopen(YesNo.no.getCode());
							stProductSpreadService.update(ss);
						}
					}
				}
				result = "关闭成功";
			}
			//TransactionManager.commitTransaction();
		} catch (Exception e) {
			result = "关闭失败";
			e.printStackTrace();
			logger.error(e);
			try {
				//TransactionManager.rollbackTransaction();
			} catch (Exception e1) {
				logger.error(e1);
			}
		}
		try {
			// 封装传递数据到前台
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
	 * 产品中类开启
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward openProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取前台传上来的产品号
		StProductSpreadMaintainForm sf = (StProductSpreadMaintainForm) form;
		try {
			StProductSpread sps = new StProductSpread();
			sps.setProductcode(sf.getProductcode());
			sps.setOrgcode(sf.getOrgcode()); 
			List<StProductSpread> lists = stProductSpreadService.queryListByModel(sps);
			if(null != lists && lists.size()>0){
				StProductSpread stpro = stProductSpreadService.queryById(sf.getId());
				stpro.setIsopen(YesNo.yes.getCode());
				stpro.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				stProductSpreadService.update(stpro);
				result = "开启成功";
			} else {
				StProductSpread stProductSpread = new StProductSpread();
				stProductSpread.setProductcode(sf.getProductcode());
				stProductSpread.setIsopen(YesNo.yes.getCode());
				stProductSpread.setOrgcode(sf.getOrgcode());
				stProductSpread.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
				//获取当前登录人员
				User user = SmUtil.getCurrentUser();
				stProductSpread.setCreateusercode(user.getId());
				stProductSpread.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				stProductSpread.setProductname(sf.getProductname());
				stProductSpread.setIseffect(YesNo.yes.getCode());
				stProductSpreadService.add(stProductSpread);
				result = "开启成功";
			}
		} catch (Exception e) {
			result = "开启失败";
			e.printStackTrace();
		}
		try {
			// 封装传递数据到前台
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
