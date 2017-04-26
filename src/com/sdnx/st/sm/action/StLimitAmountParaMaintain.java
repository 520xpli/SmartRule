package com.sdnx.st.sm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
import com.sdnx.st.constants.GuaranteeType;
import com.sdnx.st.constants.YesNo;
import com.sdnx.st.sm.dao.StLimitamountSpreadDao;
import com.sdnx.st.sm.model.StLimitamountParm;
import com.sdnx.st.sm.model.StProductHighestAmount;
import com.sdnx.st.sm.model.StProductSpread;
import com.sdnx.st.sm.service.StLimitamountService;
import com.sdnx.st.sm.service.StProductHighestAmountService;
import com.sdnx.st.sm.service.StProductSpreadService;
import com.sdnx.st.sm.utils.Group;
import com.sdnx.st.sm.utils.GroupDao;
import com.sdnx.st.sm.utils.ParamPopupBean;
import com.sdnx.st.sm.utils.SmTools;
import com.sdnx.st.sm.utils.SmUtil;
import com.sdnx.st.sm.utils.SmUtil.User;
import com.sdnx.st.sm.vo.StLimitamountParmVo;
import com.sdnx.st.sm.vo.StOrgSpreadVo;
import com.sdnx.st.sm.vo.StProductHighestAmountVo;
import com.sdnx.st.sm.vo.StProductSpreadVo;
import com.sdnx.st.util.StDaoFactory;
import com.sdnx.st.util.StServiceFactory;

public class StLimitAmountParaMaintain extends DispatchAction{
	private StProductSpreadService stProductSpreadService = StServiceFactory.getProductSpreadService();
	private StLimitamountService stLimitamountService = StServiceFactory.getLimitamountService();
	private StProductHighestAmountService productHighestAmountService = StServiceFactory.getProductHighestAmount();
	//��ʼ����Ȳ���
	private BigDecimal num = new BigDecimal("1.000");
	//��Ʒ�����ѯ����
	private String busyname = "cmiscc.pCustBusiCode";
	private String productco = "000000097642";
	private String orgname = "NAME" ; 
	private static Logger logger = Logger.getLogger(StRuleMaintain.class);
	//�¶�ȼ���ά�����
	public ActionForward limitJump(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String forward = "stLimitCalculator";
		StLimitAmountParaMaintainForm sf = (StLimitAmountParaMaintainForm) form;
		//��ȡ��½�û�
	
		User user = SmUtil.getCurrentUser();
		//���ػ�������
		List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
		//��ȡ���л���
		List<Group> groups = GroupDao.getInstance().queryCityGroupList(user.getGroupId());
		try{
			if(groups.size()>0){
				for(int i=0;i<groups.size();i++){
						String id = groups.get(i).getId();
						//��ѯ��������
						String name = GroupDao.getInstance().queryValueByAppoint(id, "NAME");
						//��װ��������
						StOrgSpreadVo orgs = new StOrgSpreadVo();
						orgs.setOrgcode(id);
						orgs.setOrgname(name);
						//����������ӽ�����
						orgVos.add(orgs);
					}
			}
			//��ȡ��Ʒ��������,����һ������
			List<StProductSpreadVo> productBig = new ArrayList<StProductSpreadVo>();
			List<ParamPopupBean> lists =new SmTools().getParamsDisplay(busyname,productco, null);
			if(lists.size()>0){
				for(int i=0;i<lists.size();i++){
					ParamPopupBean ppb = (ParamPopupBean)lists.get(i);
					StProductSpreadVo stp = new StProductSpreadVo();
					stp.setProductcode(ppb.getCode());
					stp.setProductname(ppb.getName());
					productBig.add(stp);
				}
			}
			
			StLimitamountParm slps = new StLimitamountParm();
			slps.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
			slps.setIseffect(YesNo.yes.getCode());
			 //StLimitamountSpreadDao limitDao = StDaoFactory.getLimitamountDao();
			
			//��ѯ����Ѻ�����µ�����ϵ��
			List<StLimitamountParmVo> limitListsExpand =stLimitamountService.queryEepandRatio(GuaranteeType.collaterLoan.getCode());
			if(limitListsExpand.size() > 0 && null != limitListsExpand){
				sf.getLimitAmountVoCollExpand().setLimitAmountVoList(limitListsExpand);
			}else {
				StLimitamountParmVo slv4 = new StLimitamountParmVo();
				slv4.setExpandratio(num);
				sf.getLimitAmountVoCollExpand().getLimitAmountVoList().add(slv4);
			}
			
			//��ѯ����Ѻ�����µĲ���
			List<StLimitamountParmVo> limitLists = stLimitamountService.query(slps);
				//List<StLimitamountParmVo> limitLists =limitDao.queryListByModel(slps, StLimitamountParmVo.class);
			if(limitLists.size()>0 && null != limitLists){
				for(StLimitamountParmVo svo:limitLists){
					sf.getLimitAmountVoColl().getLimitAmountVoList().add(svo);
				}
			}else{
				List<StLimitamountParmVo> slpvrs = new ArrayList<StLimitamountParmVo>();
				//��ʼ������
				StLimitamountParmVo slv4 = new StLimitamountParmVo();
				slv4.setGradecode("1");
				slv4.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
				slv4.setCollateralratio(num);
				StLimitamountParmVo slv5 = new StLimitamountParmVo();
				slv5.setGradecode("2");
				slv5.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
				slv5.setCollateralratio(num);
				StLimitamountParmVo slv6 = new StLimitamountParmVo();
				slv6.setGradecode("3");
				slv6.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
				slv6.setCollateralratio(num);
				StLimitamountParmVo slv7  = new StLimitamountParmVo();
				slv7.setGradecode("4");
				slv7.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
				slv7.setCollateralratio(num);
				StLimitamountParmVo slv8 = new StLimitamountParmVo();
				slv8.setGradecode("5");
				slv8.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
				slv8.setCollateralratio(num);
				StLimitamountParmVo slv9 = new StLimitamountParmVo();
				slv9.setGradecode("6");
				slv9.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
				slv9.setCollateralratio(num);
				StLimitamountParmVo slv10 = new StLimitamountParmVo();
				slv10.setGradecode("7");
				slv10.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
				slv10.setCollateralratio(num);
				StLimitamountParmVo slv11 = new StLimitamountParmVo();
				slv11.setGradecode("8");
				slv11.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
				slv11.setCollateralratio(num);
				StLimitamountParmVo slv12 = new StLimitamountParmVo();
				slv12.setGradecode("9");
				slv12.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
				slv12.setCollateralratio(num);
				StLimitamountParmVo slv13 = new StLimitamountParmVo();
				slv13.setGradecode("10");
				slv13.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
				slv13.setCollateralratio(num);
				slpvrs.add(slv4);slpvrs.add(slv5);slpvrs.add(slv6);slpvrs.add(slv7);
				slpvrs.add(slv8);slpvrs.add(slv9);slpvrs.add(slv10);slpvrs.add(slv11);
				slpvrs.add(slv12);slpvrs.add(slv13);
				sf.getLimitAmountVoColl().setLimitAmountVoList(slpvrs);
			}
			//��ѯ�����޹صľ��ʲ�ϵ��
		//	List<StLimitamountParmVo> limitListsUnRate = stLimitamountService.queryUnRate();
			 StLimitamountSpreadDao limitDao = StDaoFactory.getLimitamountDao();
				List<StLimitamountParmVo> limitListsUnRate =limitDao.queryUnRate();
			if(limitListsUnRate.size() > 0 && null != limitListsUnRate){
				sf.getLimitAmountVo().setLimitAmountVoList(limitListsUnRate);
			}else {
				StLimitamountParmVo slv4 = new StLimitamountParmVo();
				slv4.setUnratefamilynetassetratio(num);
				sf.getLimitAmountVo().getLimitAmountVoList().add(slv4);
			}
			//ͨ���ṩ������������ѯ��������ѯ��������
			StLimitamountParm slpGua = new StLimitamountParm();
			slpGua.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
			slpGua.setIseffect(YesNo.yes.getCode());
			
			//��ѯ��֤������µ�����ϵ��
			List<StLimitamountParmVo> limitListsGuaExpand =stLimitamountService.queryEepandRatio(GuaranteeType.guaranteeLoan.getCode());
			if(limitListsGuaExpand.size() > 0 && null != limitListsGuaExpand){
				sf.getLimitAmountVoGuaExpand().setLimitAmountVoList(limitListsGuaExpand);
			}else {
				StLimitamountParmVo slv4 = new StLimitamountParmVo();
				slv4.setExpandratio(num);
				sf.getLimitAmountVoGuaExpand().getLimitAmountVoList().add(slv4);
			}
			
			//��ѯ��֤������µĲ���
			List<StLimitamountParmVo> limitListsGua = stLimitamountService.query(slpGua);
			if(limitListsGua.size()>0 && null != limitListsGua){
				for(StLimitamountParmVo svo:limitListsGua){
					sf.getLimitAmountVoGua().getLimitAmountVoList().add(svo);
				}
			}else{
				List<StLimitamountParmVo> slpvrs = new ArrayList<StLimitamountParmVo>();
				//��ʼ������
				StLimitamountParmVo slv4 = new StLimitamountParmVo();
				slv4.setGradecode("1");
				slv4.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
				slv4.setRateralatefamilynetassetratio(num);
				slv4.setFamilynetinratio(num);
				StLimitamountParmVo slv5 = new StLimitamountParmVo();
				slv5.setGradecode("2");
				slv5.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
				slv5.setRateralatefamilynetassetratio(num);
				slv5.setFamilynetinratio(num);
				StLimitamountParmVo slv6 = new StLimitamountParmVo();
				slv6.setGradecode("3");
				slv6.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
				slv6.setRateralatefamilynetassetratio(num);
				slv6.setFamilynetinratio(num);
				StLimitamountParmVo slv7  = new StLimitamountParmVo();
				slv7.setGradecode("4");
				slv7.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
				slv7.setRateralatefamilynetassetratio(num);
				slv7.setFamilynetinratio(num);
				StLimitamountParmVo slv8 = new StLimitamountParmVo();
				slv8.setGradecode("5");
				slv8.setRateralatefamilynetassetratio(num);
				slv8.setFamilynetinratio(num);
				slv8.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
				StLimitamountParmVo slv9 = new StLimitamountParmVo();
				slv9.setGradecode("6");
				slv9.setRateralatefamilynetassetratio(num);
				slv9.setFamilynetinratio(num);
				slv9.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
				StLimitamountParmVo slv10 = new StLimitamountParmVo();
				slv10.setGradecode("7");
				slv10.setRateralatefamilynetassetratio(num);
				slv10.setFamilynetinratio(num);
				slv10.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
				StLimitamountParmVo slv11 = new StLimitamountParmVo();
				slv11.setGradecode("8");
				slv11.setRateralatefamilynetassetratio(num);
				slv11.setFamilynetinratio(num);
				slv11.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
				StLimitamountParmVo slv12 = new StLimitamountParmVo();
				slv12.setGradecode("9");
				slv12.setRateralatefamilynetassetratio(num);
				slv12.setFamilynetinratio(num);
				slv12.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
				StLimitamountParmVo slv13 = new StLimitamountParmVo();
				slv13.setGradecode("10");
				slv13.setRateralatefamilynetassetratio(num);
				slv13.setFamilynetinratio(num);
				slv13.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
				slpvrs.add(slv4);slpvrs.add(slv5);slpvrs.add(slv6);slpvrs.add(slv7);
				slpvrs.add(slv8);slpvrs.add(slv9);slpvrs.add(slv10);slpvrs.add(slv11);
				slpvrs.add(slv12);slpvrs.add(slv13);
				sf.getLimitAmountVoGua().setLimitAmountVoList(slpvrs);
			}
			
			//ͨ���ṩ������������ѯ��������ѯ��������
			StLimitamountParm slpsCre = new StLimitamountParm();
			slpsCre.setGuaranteetype(GuaranteeType.creditLoan.getCode());
			slpsCre.setIseffect(YesNo.yes.getCode());
			
			//��ѯ����������µ�����ϵ��
			List<StLimitamountParmVo> limitListsCreExpand =stLimitamountService.queryEepandRatio(GuaranteeType.guaranteeLoan.getCode());
			if(limitListsCreExpand.size() > 0 && null != limitListsCreExpand){
				sf.getLimitAmountVoRateExpand().setLimitAmountVoList(limitListsCreExpand);
			}else {
				StLimitamountParmVo slv4 = new StLimitamountParmVo();
				slv4.setExpandratio(num);
				sf.getLimitAmountVoRateExpand().getLimitAmountVoList().add(slv4);
			}
			
			//��ѯ����������µĲ���
			List<StLimitamountParmVo> limitListsCre = stLimitamountService.query(slpsCre);
			if(limitListsCre.size()>0 && null != limitListsCre){
				for(StLimitamountParmVo svo:limitListsCre){
					sf.getLimitAmountVoRate().getLimitAmountVoList().add(svo);
				}
			}else{
				List<StLimitamountParmVo> slpvrs = new ArrayList<StLimitamountParmVo>();
				//��ʼ������
				StLimitamountParmVo slv4 = new StLimitamountParmVo();
				slv4.setGradecode("1");
				slv4.setFamilynetinratio(num);
				slv4.setGuaranteetype(GuaranteeType.creditLoan.getCode());
				StLimitamountParmVo slv5 = new StLimitamountParmVo();
				slv5.setGradecode("2");
				slv5.setFamilynetinratio(num);
				slv5.setGuaranteetype(GuaranteeType.creditLoan.getCode());
				StLimitamountParmVo slv6 = new StLimitamountParmVo();
				slv6.setGradecode("3");
				slv6.setFamilynetinratio(num);
				slv6.setGuaranteetype(GuaranteeType.creditLoan.getCode());
				StLimitamountParmVo slv7  = new StLimitamountParmVo();
				slv7.setGradecode("4");
				slv7.setFamilynetinratio(num);
				slv7.setGuaranteetype(GuaranteeType.creditLoan.getCode());
				StLimitamountParmVo slv8 = new StLimitamountParmVo();
				slv8.setGradecode("5");
				slv8.setFamilynetinratio(num);
				slv8.setGuaranteetype(GuaranteeType.creditLoan.getCode());
				StLimitamountParmVo slv9 = new StLimitamountParmVo();
				slv9.setGradecode("6");
				slv9.setFamilynetinratio(num);
				slv9.setGuaranteetype(GuaranteeType.creditLoan.getCode());
				StLimitamountParmVo slv10 = new StLimitamountParmVo();
				slv10.setGradecode("7");
				slv10.setFamilynetinratio(num);
				slv10.setGuaranteetype(GuaranteeType.creditLoan.getCode());
				StLimitamountParmVo slv11 = new StLimitamountParmVo();
				slv11.setGradecode("8");
				slv11.setFamilynetinratio(num);
				slv11.setGuaranteetype(GuaranteeType.creditLoan.getCode());
				StLimitamountParmVo slv12 = new StLimitamountParmVo();
				slv12.setGradecode("9");
				slv12.setFamilynetinratio(num);
				slv12.setGuaranteetype(GuaranteeType.creditLoan.getCode());
				StLimitamountParmVo slv13 = new StLimitamountParmVo();
				slv13.setGradecode("10");
				slv13.setFamilynetinratio(num);
				slv13.setGuaranteetype(GuaranteeType.creditLoan.getCode());
				slpvrs.add(slv4);slpvrs.add(slv5);slpvrs.add(slv6);slpvrs.add(slv7);
				slpvrs.add(slv8);slpvrs.add(slv9);slpvrs.add(slv10);slpvrs.add(slv11);
				slpvrs.add(slv12);slpvrs.add(slv13);
				sf.getLimitAmountVoRate().setLimitAmountVoList(slpvrs);
			}
			//��ȡ�������ϵĴ�С
			int orgNum = orgVos.size();
			//set��������
			sf.setProductVoList(productBig);
			sf.setOrgnum(orgNum);
			sf.setOrgVoList(orgVos);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}
	//�¶�ȼ���ά�����
		public ActionForward productlimitJump(ActionMapping mapping,ActionForm form,
				HttpServletRequest request, HttpServletResponse response){
			String forward = "stProductLimit";
			StLimitAmountParaMaintainForm sf = (StLimitAmountParaMaintainForm) form;
			//��ȡ��½�û�
			
			User user = SmUtil.getCurrentUser();
			//���ػ�������
			List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
			//��ȡ���л���
			List<Group> groups = GroupDao.getInstance().queryCityGroupList(user.getGroupId());
			try{
				if(groups.size()>0){
					for(int i=0;i<groups.size();i++){
							String id = groups.get(i).getId();
							//��ѯ��������
							String name = GroupDao.getInstance().queryValueByAppoint(id, "NAME");
							//��װ��������
							StOrgSpreadVo orgs = new StOrgSpreadVo();
							orgs.setOrgcode(id);
							orgs.setOrgname(name);
							//����������ӽ�����
							orgVos.add(orgs);
						}
				}
				//��ȡ��Ʒ��������,����һ������
				List<StProductSpreadVo> productBig = new ArrayList<StProductSpreadVo>();
				List<ParamPopupBean> lists =new SmTools().getParamsDisplay(busyname,productco, null);
				if(lists.size()>0){
					for(int i=0;i<lists.size();i++){
						ParamPopupBean ppb = (ParamPopupBean)lists.get(i);
						StProductSpreadVo stp = new StProductSpreadVo();
						stp.setProductcode(ppb.getCode());
						stp.setProductname(ppb.getName());
						productBig.add(stp);
					}
				}
				
				StLimitamountParm slps = new StLimitamountParm();
				slps.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
				slps.setIseffect(YesNo.yes.getCode());
				 //StLimitamountSpreadDao limitDao = StDaoFactory.getLimitamountDao();
				
				//��ѯ����Ѻ�����µ�����ϵ��
				List<StLimitamountParmVo> limitListsExpand =stLimitamountService.queryEepandRatio(GuaranteeType.collaterLoan.getCode());
				if(limitListsExpand.size() > 0 && null != limitListsExpand){
					sf.getLimitAmountVoCollExpand().setLimitAmountVoList(limitListsExpand);
				}else {
					StLimitamountParmVo slv4 = new StLimitamountParmVo();
					slv4.setExpandratio(num);
					sf.getLimitAmountVoCollExpand().getLimitAmountVoList().add(slv4);
				}
				
				
				//��ѯ����Ѻ�����µĲ���
				List<StLimitamountParmVo> limitLists = stLimitamountService.query(slps);
					//List<StLimitamountParmVo> limitLists =limitDao.queryListByModel(slps, StLimitamountParmVo.class);
				if(limitLists.size()>0 && null != limitLists){
					for(StLimitamountParmVo svo:limitLists){
						sf.getLimitAmountVoColl().getLimitAmountVoList().add(svo);
					}
				}else{
					List<StLimitamountParmVo> slpvrs = new ArrayList<StLimitamountParmVo>();
					//��ʼ������
					StLimitamountParmVo slv4 = new StLimitamountParmVo();
					slv4.setGradecode("1");
					slv4.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
					slv4.setCollateralratio(num);
					StLimitamountParmVo slv5 = new StLimitamountParmVo();
					slv5.setGradecode("2");
					slv5.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
					slv5.setCollateralratio(num);
					StLimitamountParmVo slv6 = new StLimitamountParmVo();
					slv6.setGradecode("3");
					slv6.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
					slv6.setCollateralratio(num);
					StLimitamountParmVo slv7  = new StLimitamountParmVo();
					slv7.setGradecode("4");
					slv7.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
					slv7.setCollateralratio(num);
					StLimitamountParmVo slv8 = new StLimitamountParmVo();
					slv8.setGradecode("5");
					slv8.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
					slv8.setCollateralratio(num);
					StLimitamountParmVo slv9 = new StLimitamountParmVo();
					slv9.setGradecode("6");
					slv9.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
					slv9.setCollateralratio(num);
					StLimitamountParmVo slv10 = new StLimitamountParmVo();
					slv10.setGradecode("7");
					slv10.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
					slv10.setCollateralratio(num);
					StLimitamountParmVo slv11 = new StLimitamountParmVo();
					slv11.setGradecode("8");
					slv11.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
					slv11.setCollateralratio(num);
					StLimitamountParmVo slv12 = new StLimitamountParmVo();
					slv12.setGradecode("9");
					slv12.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
					slv12.setCollateralratio(num);
					StLimitamountParmVo slv13 = new StLimitamountParmVo();
					slv13.setGradecode("10");
					slv13.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
					slv13.setCollateralratio(num);
					slpvrs.add(slv4);slpvrs.add(slv5);slpvrs.add(slv6);slpvrs.add(slv7);
					slpvrs.add(slv8);slpvrs.add(slv9);slpvrs.add(slv10);slpvrs.add(slv11);
					slpvrs.add(slv12);slpvrs.add(slv13);
					sf.getLimitAmountVoColl().setLimitAmountVoList(slpvrs);
				}
				//��ѯ�����޹صľ��ʲ�ϵ��
			//	List<StLimitamountParmVo> limitListsUnRate = stLimitamountService.queryUnRate();
				 StLimitamountSpreadDao limitDao = StDaoFactory.getLimitamountDao();
					List<StLimitamountParmVo> limitListsUnRate =limitDao.queryUnRate();
				if(limitListsUnRate.size() > 0 && null != limitListsUnRate){
					sf.getLimitAmountVo().setLimitAmountVoList(limitListsUnRate);
				}else {
					StLimitamountParmVo slv4 = new StLimitamountParmVo();
					slv4.setUnratefamilynetassetratio(num);
					sf.getLimitAmountVo().getLimitAmountVoList().add(slv4);
				}
				//ͨ���ṩ������������ѯ��������ѯ��������
				StLimitamountParm slpGua = new StLimitamountParm();
				slpGua.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
				slpGua.setIseffect(YesNo.yes.getCode());
				
				//��ѯ��֤������µ�����ϵ��
				List<StLimitamountParmVo> limitListsGuaExpand =stLimitamountService.queryEepandRatio(GuaranteeType.guaranteeLoan.getCode());
				if(limitListsGuaExpand.size() > 0 && null != limitListsGuaExpand){
					sf.getLimitAmountVoGuaExpand().setLimitAmountVoList(limitListsGuaExpand);
				}else {
					StLimitamountParmVo slv4 = new StLimitamountParmVo();
					slv4.setExpandratio(num);
					sf.getLimitAmountVoGuaExpand().getLimitAmountVoList().add(slv4);
				}
				
				//��ѯ��֤������µĲ���
				List<StLimitamountParmVo> limitListsGua = stLimitamountService.query(slpGua);
				if(limitListsGua.size()>0 && null != limitListsGua){
					for(StLimitamountParmVo svo:limitListsGua){
						sf.getLimitAmountVoGua().getLimitAmountVoList().add(svo);
					}
				}else{
					List<StLimitamountParmVo> slpvrs = new ArrayList<StLimitamountParmVo>();
					//��ʼ������
					StLimitamountParmVo slv4 = new StLimitamountParmVo();
					slv4.setGradecode("1");
					slv4.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
					slv4.setRateralatefamilynetassetratio(num);
					slv4.setFamilynetinratio(num);
					StLimitamountParmVo slv5 = new StLimitamountParmVo();
					slv5.setGradecode("2");
					slv5.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
					slv5.setRateralatefamilynetassetratio(num);
					slv5.setFamilynetinratio(num);
					StLimitamountParmVo slv6 = new StLimitamountParmVo();
					slv6.setGradecode("3");
					slv6.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
					slv6.setRateralatefamilynetassetratio(num);
					slv6.setFamilynetinratio(num);
					StLimitamountParmVo slv7  = new StLimitamountParmVo();
					slv7.setGradecode("4");
					slv7.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
					slv7.setRateralatefamilynetassetratio(num);
					slv7.setFamilynetinratio(num);
					StLimitamountParmVo slv8 = new StLimitamountParmVo();
					slv8.setGradecode("5");
					slv8.setRateralatefamilynetassetratio(num);
					slv8.setFamilynetinratio(num);
					slv8.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
					StLimitamountParmVo slv9 = new StLimitamountParmVo();
					slv9.setGradecode("6");
					slv9.setRateralatefamilynetassetratio(num);
					slv9.setFamilynetinratio(num);
					slv9.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
					StLimitamountParmVo slv10 = new StLimitamountParmVo();
					slv10.setGradecode("7");
					slv10.setRateralatefamilynetassetratio(num);
					slv10.setFamilynetinratio(num);
					slv10.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
					StLimitamountParmVo slv11 = new StLimitamountParmVo();
					slv11.setGradecode("8");
					slv11.setRateralatefamilynetassetratio(num);
					slv11.setFamilynetinratio(num);
					slv11.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
					StLimitamountParmVo slv12 = new StLimitamountParmVo();
					slv12.setGradecode("9");
					slv12.setRateralatefamilynetassetratio(num);
					slv12.setFamilynetinratio(num);
					slv12.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
					StLimitamountParmVo slv13 = new StLimitamountParmVo();
					slv13.setGradecode("10");
					slv13.setRateralatefamilynetassetratio(num);
					slv13.setFamilynetinratio(num);
					slv13.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
					slpvrs.add(slv4);slpvrs.add(slv5);slpvrs.add(slv6);slpvrs.add(slv7);
					slpvrs.add(slv8);slpvrs.add(slv9);slpvrs.add(slv10);slpvrs.add(slv11);
					slpvrs.add(slv12);slpvrs.add(slv13);
					sf.getLimitAmountVoGua().setLimitAmountVoList(slpvrs);
				}
				
				//ͨ���ṩ������������ѯ��������ѯ��������
				StLimitamountParm slpsCre = new StLimitamountParm();
				slpsCre.setGuaranteetype(GuaranteeType.creditLoan.getCode());
				slpsCre.setIseffect(YesNo.yes.getCode());
				
				//��ѯ����������µ�����ϵ��
				List<StLimitamountParmVo> limitListsCreExpand =stLimitamountService.queryEepandRatio(GuaranteeType.guaranteeLoan.getCode());
				if(limitListsCreExpand.size() > 0 && null != limitListsCreExpand){
					sf.getLimitAmountVoRateExpand().setLimitAmountVoList(limitListsCreExpand);
				}else {
					StLimitamountParmVo slv4 = new StLimitamountParmVo();
					slv4.setExpandratio(num);
					sf.getLimitAmountVoRateExpand().getLimitAmountVoList().add(slv4);
				}
				
				//��ѯ����������µĲ���
				List<StLimitamountParmVo> limitListsCre = stLimitamountService.query(slpsCre);
				if(limitListsCre.size()>0 && null != limitListsCre){
					for(StLimitamountParmVo svo:limitListsCre){
						sf.getLimitAmountVoRate().getLimitAmountVoList().add(svo);
					}
				}else{
					List<StLimitamountParmVo> slpvrs = new ArrayList<StLimitamountParmVo>();
					//��ʼ������
					StLimitamountParmVo slv4 = new StLimitamountParmVo();
					slv4.setGradecode("1");
					slv4.setFamilynetinratio(num);
					slv4.setGuaranteetype(GuaranteeType.creditLoan.getCode());
					StLimitamountParmVo slv5 = new StLimitamountParmVo();
					slv5.setGradecode("2");
					slv5.setFamilynetinratio(num);
					slv5.setGuaranteetype(GuaranteeType.creditLoan.getCode());
					StLimitamountParmVo slv6 = new StLimitamountParmVo();
					slv6.setGradecode("3");
					slv6.setFamilynetinratio(num);
					slv6.setGuaranteetype(GuaranteeType.creditLoan.getCode());
					StLimitamountParmVo slv7  = new StLimitamountParmVo();
					slv7.setGradecode("4");
					slv7.setFamilynetinratio(num);
					slv7.setGuaranteetype(GuaranteeType.creditLoan.getCode());
					StLimitamountParmVo slv8 = new StLimitamountParmVo();
					slv8.setGradecode("5");
					slv8.setFamilynetinratio(num);
					slv8.setGuaranteetype(GuaranteeType.creditLoan.getCode());
					StLimitamountParmVo slv9 = new StLimitamountParmVo();
					slv9.setGradecode("6");
					slv9.setFamilynetinratio(num);
					slv9.setGuaranteetype(GuaranteeType.creditLoan.getCode());
					StLimitamountParmVo slv10 = new StLimitamountParmVo();
					slv10.setGradecode("7");
					slv10.setFamilynetinratio(num);
					slv10.setGuaranteetype(GuaranteeType.creditLoan.getCode());
					StLimitamountParmVo slv11 = new StLimitamountParmVo();
					slv11.setGradecode("8");
					slv11.setFamilynetinratio(num);
					slv11.setGuaranteetype(GuaranteeType.creditLoan.getCode());
					StLimitamountParmVo slv12 = new StLimitamountParmVo();
					slv12.setGradecode("9");
					slv12.setFamilynetinratio(num);
					slv12.setGuaranteetype(GuaranteeType.creditLoan.getCode());
					StLimitamountParmVo slv13 = new StLimitamountParmVo();
					slv13.setGradecode("10");
					slv13.setFamilynetinratio(num);
					slv13.setGuaranteetype(GuaranteeType.creditLoan.getCode());
					slpvrs.add(slv4);slpvrs.add(slv5);slpvrs.add(slv6);slpvrs.add(slv7);
					slpvrs.add(slv8);slpvrs.add(slv9);slpvrs.add(slv10);slpvrs.add(slv11);
					slpvrs.add(slv12);slpvrs.add(slv13);
					sf.getLimitAmountVoRate().setLimitAmountVoList(slpvrs);
				}
				//��ȡ�������ϵĴ�С
				int orgNum = orgVos.size();
				//set��������
				sf.setProductVoList(productBig);
				sf.setOrgnum(orgNum);
				sf.setOrgVoList(orgVos);
			}catch(Exception e){
				e.printStackTrace();
			}
			return mapping.findForward(forward);
		}
		
		public ActionForward queryProductBigByOrg(ActionMapping mapping,ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
				//��ȡǰ̨�������Ļ����� 
				StLimitAmountParaMaintainForm of = (StLimitAmountParaMaintainForm)form;
				//���ػ�������
				List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
				//��ȡ��½�û�
				
				User user = SmUtil.getCurrentUser();
				//��ȡ���л���
				List<Group> groups = GroupDao.getInstance().queryCityGroupList(user.getGroupId());
					
				//��ȡ��Ʒ��������,����һ������
				List<StProductSpreadVo> productBig = new ArrayList<StProductSpreadVo>();
				//��ѯ��Ʒ����
				List<ParamPopupBean> lists =new SmTools().getParamsDisplay(busyname,productco, null);
				if(lists.size()>0){
					try {
						if(groups.size()>0){
							for(int i=0;i<groups.size();i++){
									String id = groups.get(i).getId();
									//��ѯ��������
									String name = GroupDao.getInstance().queryValueByAppoint(id, "NAME");
									//��װ��������
									StOrgSpreadVo orgs = new StOrgSpreadVo();
									orgs.setOrgcode(id);
									orgs.setOrgname(name);
									//����������ӽ�����
									orgVos.add(orgs);
							}
						}
						for(int i=0;i<lists.size();i++){
							ParamPopupBean ppb = (ParamPopupBean)lists.get(i);
							StProductSpread sps = new StProductSpread();
							sps.setProductcode(ppb.getCode());
							sps.setOrgcode(of.getOrgcode());
							StProductSpreadVo stp = new StProductSpreadVo();
							stp.setProductcode(ppb.getCode());
							stp.setProductname(ppb.getName());
							//��Ʒ�������(sps)
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
				//�õ�����,���ݵ�ǰ̨
				of.getFooter().setCount(productBig.size());
				of.getFooter().setDataArray(productBig);
				//��ȡ�������ϵĴ�С
				int orgNum = orgVos.size();
				//set��������
				of.setProductVoList(productBig);
				of.setOrgnum(orgNum);
				of.setOrgVoList(orgVos);
				ActionForward forward = mapping.findForward("stProductLimit");
			return forward;
		}
	/**
	 * ��ѯ����Ѻ,��֤�࣬���������ϵ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward queryLoanRadio(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String forward = "stProductLimit";
		StLimitAmountParaMaintainForm sf = (StLimitAmountParaMaintainForm) form;
		//��ȡ��½�û�
		User user = SmUtil.getCurrentUser();
		//���ػ�������
		List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
		//��ȡ���л���
		List<Group> groups = GroupDao.getInstance().queryCityGroupList(user.getGroupId());
		try{
			if(groups.size()>0){
				for(int i=0;i<groups.size();i++){
					String id = groups.get(i).getId();
					//��ѯ��������
					String name = GroupDao.getInstance().queryValueByAppoint(id, "NAME");
					//��װ��������
					StOrgSpreadVo orgs = new StOrgSpreadVo();
					orgs.setOrgcode(id);
					orgs.setOrgname(name);
					//����������ӽ�����
					orgVos.add(orgs);
					}
				}
					//��ȡ��Ʒ��������,����һ������
					List<StProductSpreadVo> productBig = new ArrayList<StProductSpreadVo>();
					List<ParamPopupBean> lists =new SmTools().getParamsDisplay(busyname,productco, null);
					if(lists.size()>0){
							for(int i=0;i<lists.size();i++){
								ParamPopupBean ppb = (ParamPopupBean)lists.get(i);
								StProductSpreadVo stp = new StProductSpreadVo();
								stp.setProductcode(ppb.getCode());
								stp.setProductname(ppb.getName());
								productBig.add(stp);
							}
					}
					//��ѯ�������
					String code = sf.getOrgcode();
					//��ѯ��Ʒ������
					String procode = sf.getProductcentercode();
					//��ѯ��Ʒ
					List<StProductHighestAmountVo> proVoList = new ArrayList<StProductHighestAmountVo>();
					if(sf.getProductcode() == null || sf.getProductcode() .equals("0")){
						List<StProductHighestAmount> proList = productHighestAmountService.queryProductHighestSmaByProBig(code,procode);
						if(proList.size() > 0){
							for(StProductHighestAmount sp : proList) {
								StProductHighestAmountVo sps = new StProductHighestAmountVo();
								sps.setId(sp.getId());
								sps.setProductcode(sp.getProductcode());
								sps.setProductname(sp.getProductname());
								sps.setLimitamount(sp.getLimitamount());
								sps.setProductmiddlecode(sf.getProductcentercode());
								sps.setOrgcode(sf.getOrgcode());
								String name = GroupDao.getInstance().queryValueByAppoint(sf.getOrgcode(), orgname);
								sps.setOrgName(name);
								proVoList.add(sps);
							}
						}
					}else{
						//��ѯ�����Ʒ
						StProductHighestAmount spss = new StProductHighestAmount();
						spss.setProductcode(sf.getProductcode());
						spss.setProductmiddlecode(sf.getProductcentercode());
						spss.setOrgcode(sf.getOrgcode());
						List<StProductHighestAmount> proList = productHighestAmountService.queryListByModel(spss);
						//�ж����ݿ����Ƿ�������
						if(proList.size() > 0){
							StProductHighestAmountVo sps = new StProductHighestAmountVo();
							sps.setId(proList.get(0).getId());
							sps.setProductcode(proList.get(0).getProductcode());
							sps.setProductname(proList.get(0).getProductname());
							sps.setLimitamount(proList.get(0).getLimitamount());
							sps.setProductmiddlecode(sf.getProductcentercode());
							sps.setOrgcode(sf.getOrgcode());
							String name = GroupDao.getInstance().queryValueByAppoint(sf.getOrgcode(), orgname);
							sps.setOrgName(name);
							proVoList.add(sps);
						} else {
							List<StProductHighestAmount> proLists = productHighestAmountService.queryProductHighestSmaByProBig(code,procode);
							if(proLists.size() > 0 && null != proLists){
								for(StProductHighestAmount sp : proLists) {
									if(sp.getProductcode().equals(sf.getProductcode())){
										StProductHighestAmountVo sps = new StProductHighestAmountVo();
										sps.setProductcode(sp.getProductcode());
										sps.setProductname(sp.getProductname());
										sps.setLimitamount(sp.getLimitamount());
										sps.setProductmiddlecode(sf.getProductcentercode());
										sps.setOrgcode(sf.getOrgcode());
										String name = GroupDao.getInstance().queryValueByAppoint(sf.getOrgcode(), orgname);
										sps.setOrgName(name);
										proVoList.add(sps);
									}
								}
							}
						}
					}
					//�õ�����,���ݵ�ǰ̨
					sf.getFooter().setCount(productBig.size());
					sf.getFooter().setDataArray(productBig);
					sf.setProductHAVoList(proVoList);
					//��ȡ�������ϵĴ�С
					int orgNum = orgVos.size();
					//set��������
					sf.setProductVoList(productBig);
					sf.setOrgnum(orgNum);
					sf.setOrgVoList(orgVos);
				}catch(Exception e){
					e.printStackTrace();
				}
		return mapping.findForward(forward);
	}
	/**
	 * �����Ʒ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward insertProduct(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		response.setContentType("application/json;charset=UTF-8");
		String result = "����ɹ�" ;
		StLimitAmountParaMaintainForm limit = (StLimitAmountParaMaintainForm)form;
		//��ȡ��½�û�
		User user = SmUtil.getCurrentUser();
		//�ϴ�����
		List<StProductHighestAmountVo> list = limit.getSaveList();
		for(StProductHighestAmountVo spsv : list){
			if(spsv.getId() != null && !"".equals(spsv.getId())){
				StProductHighestAmount sps = new StProductHighestAmount();
				sps.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				sps.setId(spsv.getId());
				if(spsv.getLimitamount().intValue() == -1)
					sps.setLimitamount(null);
				else
					sps.setLimitamount(spsv.getLimitamount());
				try {
					productHighestAmountService.updateById(sps);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				StProductHighestAmount sps = new StProductHighestAmount();
				sps.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
				sps.setCreateusercode(user.getId());
				sps.setEndtime(new java.sql.Timestamp(new Date().getTime()));
				sps.setIseffect(YesNo.yes.getCode());
				sps.setOrgcode(spsv.getOrgcode());
				sps.setProductmiddlecode(spsv.getProductmiddlecode());
				sps.setProductcode(spsv.getProductcode());
				sps.setProductname(spsv.getProductname());
				sps.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
				if(spsv.getLimitamount().intValue() == -1)
					sps.setLimitamount(null);
				else
					sps.setLimitamount(spsv.getLimitamount());
				productHighestAmountService.add(sps);
			} 
		}
		JsonObject o = new JsonObject();
		o.addProperty("result",result);
		try {
			PrintWriter out = response.getWriter();
			out.write(o.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward insertRateRatio(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		response.setContentType("application/json;charset=UTF-8");
		StLimitAmountParaMaintainForm limit = (StLimitAmountParaMaintainForm)form;
		//��ȡ��½�û�
		User user = SmUtil.getCurrentUser();
		//��ȡ�����ϴ�����
		String result="����ʧ��";
		try {
			//TransactionManager.beginTransaction();
			
			//����Ѻ��������ϵ������
			List<StLimitamountParmVo> CollExpand = limit.getLimitAmountVoCollExpand().getLimitAmountVoList();
			List<StLimitamountParmVo> limitListsCollExpand = stLimitamountService.queryEepandRatio(GuaranteeType.collaterLoan.getCode());
			if(limitListsCollExpand.size() >0 & null != limitListsCollExpand ){
				for(StLimitamountParmVo svo:limitListsCollExpand){
					StLimitamountParm st = new StLimitamountParm();
					st.setId(svo.getId());
					st.setIseffect(YesNo.no.getCode());
					st.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					stLimitamountService.update(st);
				}
				for(StLimitamountParmVo svo:CollExpand){
					//���һ��������
					StLimitamountParm slp = new StLimitamountParm();
					slp.setExpandratio(svo.getExpandratio());
					slp.setCreateusercode(user.getId());
					slp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
					slp.setIseffect(YesNo.yes.getCode());
					slp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
					slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					slp.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
					stLimitamountService.add(slp);
					result="����ɹ�";
				}
			}else{
				for(StLimitamountParmVo svo:CollExpand){
					//���һ��������
					StLimitamountParm slp = new StLimitamountParm();
					slp.setExpandratio(svo.getExpandratio());
					slp.setCreateusercode(user.getId());
					slp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
					slp.setIseffect(YesNo.yes.getCode());
					slp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
					slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					slp.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
					stLimitamountService.add(slp);
					result="����ɹ�";
				}
			}
			
			//����Ѻ����
			List<StLimitamountParmVo> collaters = limit.getLimitAmountVoColl().getLimitAmountVoList();
			//����Ѻ��ѯ����
			StLimitamountParm slpsColl = new StLimitamountParm();
			slpsColl.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
			slpsColl.setIseffect(YesNo.yes.getCode());
			//��ѯ����������µĲ���
			List<StLimitamountParmVo> limitListsColl;
			try {
				limitListsColl = stLimitamountService.query(slpsColl);
				//�ж����ݿ��Ƿ�������
				if(limitListsColl.size() >0 & null != limitListsColl ){
					for(StLimitamountParmVo svo:limitListsColl){
						StLimitamountParm st = new StLimitamountParm();
						st.setId(svo.getId());
						st.setIseffect(YesNo.no.getCode());
						st.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
						stLimitamountService.update(st);
					}
					for(StLimitamountParmVo svo:collaters){
						//���һ��������
						StLimitamountParm slp = new StLimitamountParm();
						slp.setGradecode(svo.getGradecode());
						slp.setCollateralratio(svo.getCollateralratio());
						slp.setCreateusercode(user.getId());
						slp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
						slp.setIseffect(YesNo.yes.getCode());
						slp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
						slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
						slp.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
						stLimitamountService.add(slp);
						result="����ɹ�";
					}
				}else{
					for(StLimitamountParmVo svo:collaters){
						//���һ��������
						StLimitamountParm slp = new StLimitamountParm();
						slp.setGradecode(svo.getGradecode());
						slp.setCollateralratio(svo.getCollateralratio());
						slp.setCreateusercode(user.getId());
						slp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
						slp.setIseffect(YesNo.yes.getCode());
						slp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
						slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
						slp.setGuaranteetype(GuaranteeType.collaterLoan.getCode());
						stLimitamountService.add(slp);
						result="����ɹ�";
					}
				}
			} catch (Exception e) {
				result="����ʧ��";
				e.printStackTrace();
			}
			//�����޹صĲ�������
			List<StLimitamountParmVo> unrates = limit.getLimitAmountVo().getLimitAmountVoList();
			List<StLimitamountParmVo> limitListsUnRate = stLimitamountService.queryUnRate();
			if(limitListsUnRate.size() > 0 & null != limitListsUnRate){
				for(StLimitamountParmVo slpv : limitListsUnRate){
					StLimitamountParm slp = new StLimitamountParm();
					slp.setId(slpv.getId());
					slp.setIseffect(YesNo.no.getCode());
					slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()) );
					stLimitamountService.update(slp);
				}
				for(StLimitamountParmVo slpv : unrates){
					StLimitamountParm slp = new StLimitamountParm();
					slp.setGradecode(slpv.getGradecode());
					slp.setUnratefamilynetassetratio(slpv.getUnratefamilynetassetratio());
					slp.setCreateusercode(user.getId());
					slp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
					slp.setIseffect(YesNo.yes.getCode());
					slp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
					slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					stLimitamountService.add(slp);
					result="����ɹ�";
				}
			}else {
				for(StLimitamountParmVo slpv : unrates){
					StLimitamountParm slp = new StLimitamountParm();
					slp.setGradecode(slpv.getGradecode());
					slp.setUnratefamilynetassetratio(slpv.getUnratefamilynetassetratio());
					slp.setCreateusercode(user.getId());
					slp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
					slp.setIseffect(YesNo.yes.getCode());
					slp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
					slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					stLimitamountService.add(slp);
					result="����ɹ�";
				}
			}
			//��֤�����
			//��֤���������ϵ������
			List<StLimitamountParmVo> GuaExpand = limit.getLimitAmountVoGuaExpand().getLimitAmountVoList();
			List<StLimitamountParmVo> limitListsGuaExpand = stLimitamountService.queryEepandRatio(GuaranteeType.guaranteeLoan.getCode());
			if(limitListsGuaExpand.size() >0 & null != limitListsGuaExpand ){
				for(StLimitamountParmVo svo:limitListsGuaExpand){
					StLimitamountParm st = new StLimitamountParm();
					st.setId(svo.getId());
					st.setIseffect(YesNo.no.getCode());
					st.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					stLimitamountService.update(st);
				}
				for(StLimitamountParmVo svo:GuaExpand){
					//���һ��������
					StLimitamountParm slp = new StLimitamountParm();
					slp.setExpandratio(svo.getExpandratio());
					slp.setCreateusercode(user.getId());
					slp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
					slp.setIseffect(YesNo.yes.getCode());
					slp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
					slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					slp.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
					stLimitamountService.add(slp);
					result="����ɹ�";
				}
			}else{
				for(StLimitamountParmVo svo:GuaExpand){
					//���һ��������
					StLimitamountParm slp = new StLimitamountParm();
					slp.setExpandratio(svo.getExpandratio());
					slp.setCreateusercode(user.getId());
					slp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
					slp.setIseffect(YesNo.yes.getCode());
					slp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
					slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					slp.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
					stLimitamountService.add(slp);
					result="����ɹ�";
				}
			}
			
			List<StLimitamountParmVo> guarantees = limit.getLimitAmountVoGua().getLimitAmountVoList();
			//��֤���ѯ����
			StLimitamountParm slpsGua = new StLimitamountParm();
			slpsGua.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
			slpsGua.setIseffect(YesNo.yes.getCode());
			List<StLimitamountParmVo> limitListsGua = stLimitamountService.query(slpsGua);
			if(limitListsGua.size() > 0 & null != limitListsGua){
				for(StLimitamountParmVo slpv : limitListsGua){
					StLimitamountParm slp = new StLimitamountParm();
					slp.setId(slpv.getId());
					slp.setIseffect(YesNo.no.getCode());
					slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()) );
					stLimitamountService.update(slp);
				}
				for(StLimitamountParmVo slpv : guarantees){
					StLimitamountParm slp = new StLimitamountParm();
					slp.setGradecode(slpv.getGradecode());
					slp.setFamilynetinratio(slpv.getFamilynetinratio());
					slp.setRateralatefamilynetassetratio(slpv.getRateralatefamilynetassetratio());
					slp.setCreateusercode(user.getId());
					slp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
					slp.setIseffect(YesNo.yes.getCode());
					slp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
					slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					slp.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
					stLimitamountService.add(slp);
					result="����ɹ�";
				}
			}else {
				for(StLimitamountParmVo slpv : guarantees){
					StLimitamountParm slp = new StLimitamountParm();
					slp.setGradecode(slpv.getGradecode());
					slp.setFamilynetinratio(slpv.getFamilynetinratio());
					slp.setRateralatefamilynetassetratio(slpv.getRateralatefamilynetassetratio());
					slp.setCreateusercode(user.getId());
					slp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
					slp.setIseffect(YesNo.yes.getCode());
					slp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
					slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					slp.setGuaranteetype(GuaranteeType.guaranteeLoan.getCode());
					stLimitamountService.add(slp);
					result="����ɹ�";
				}
			}
			//���������
			//�������������ϵ������
			List<StLimitamountParmVo> CreExpand = limit.getLimitAmountVoRateExpand().getLimitAmountVoList();
			List<StLimitamountParmVo> limitListsCreExpand = stLimitamountService.queryEepandRatio(GuaranteeType.creditLoan.getCode());
			if(limitListsCreExpand.size() >0 & null != limitListsCreExpand ){
				for(StLimitamountParmVo svo:limitListsCreExpand){
					StLimitamountParm st = new StLimitamountParm();
					st.setId(svo.getId());
					st.setIseffect(YesNo.no.getCode());
					st.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					stLimitamountService.update(st);
				}
				for(StLimitamountParmVo svo:CreExpand){
					//���һ��������
					StLimitamountParm slp = new StLimitamountParm();
					slp.setExpandratio(svo.getExpandratio());
					slp.setCreateusercode(user.getId());
					slp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
					slp.setIseffect(YesNo.yes.getCode());
					slp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
					slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					slp.setGuaranteetype(GuaranteeType.creditLoan.getCode());
					stLimitamountService.add(slp);
					result="����ɹ�";
				}
			}else{
				for(StLimitamountParmVo svo:CreExpand){
					//���һ��������
					StLimitamountParm slp = new StLimitamountParm();
					slp.setExpandratio(svo.getExpandratio());
					slp.setCreateusercode(user.getId());
					slp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
					slp.setIseffect(YesNo.yes.getCode());
					slp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
					slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					slp.setGuaranteetype(GuaranteeType.creditLoan.getCode());
					stLimitamountService.add(slp);
					result="����ɹ�";
				}
			}
			
			List<StLimitamountParmVo> credits =limit.getLimitAmountVoRate().getLimitAmountVoList();
			//��֤���ѯ����
					StLimitamountParm slpsCre= new StLimitamountParm();
					slpsCre.setGuaranteetype(GuaranteeType.creditLoan.getCode());
					slpsCre.setIseffect(YesNo.yes.getCode());
					List<StLimitamountParmVo> limitListsCre = stLimitamountService.query(slpsCre);
					if(limitListsCre.size() > 0 & null != limitListsCre){
						for(StLimitamountParmVo slpv : limitListsCre){
							StLimitamountParm slp = new StLimitamountParm();
							slp.setId(slpv.getId());
							slp.setIseffect(YesNo.no.getCode());
							slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()) );
							stLimitamountService.update(slp);
						}
						for(StLimitamountParmVo slpv : credits){
							StLimitamountParm slp = new StLimitamountParm();
							slp.setGradecode(slpv.getGradecode());
							slp.setFamilynetinratio(slpv.getFamilynetinratio());
							slp.setCreateusercode(user.getId());
							slp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
							slp.setIseffect(YesNo.yes.getCode());
							slp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
							slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
							slp.setGuaranteetype(GuaranteeType.creditLoan.getCode());
							stLimitamountService.add(slp);
							result="����ɹ�";
						}
					}else {
						for(StLimitamountParmVo slpv : credits){
							StLimitamountParm slp = new StLimitamountParm();
							slp.setGradecode(slpv.getGradecode());
							slp.setFamilynetinratio(slpv.getFamilynetinratio());
							slp.setCreateusercode(user.getId());
							slp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
							slp.setIseffect(YesNo.yes.getCode());
							slp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
							slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
							slp.setGuaranteetype(GuaranteeType.creditLoan.getCode());
							stLimitamountService.add(slp);
							result="����ɹ�";
						}
					}		
					//TransactionManager.commitTransaction();
		} catch (Exception e) {
			result="����ʧ��";
			e.printStackTrace();
			logger.error(e);
			try {
				//TransactionManager.rollbackTransaction();
			} catch (Exception e1) {
				logger.error(e1);
			}
		}
		JsonObject o = new JsonObject();
		o.addProperty("result",result);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(o.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	//��Ȳ���ά�����
	public ActionForward jump(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String forward = "stLimitamount";
		StLimitAmountParaMaintainForm sf = (StLimitAmountParaMaintainForm) form;
		//��ȡ��½�û�
		User user = SmUtil.getCurrentUser();
		//���ػ�������
		List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
		//��ȡ���л���
		List<Group> groups = GroupDao.getInstance().queryCityGroupList(user.getGroupId());
		try{
			if(groups.size()>0){
				for(int i=0;i<groups.size();i++){
					String id = groups.get(i).getId();
					//��ѯ��������
					String name = GroupDao.getInstance().queryValueByAppoint(id, "NAME");
					//��װ��������
					StOrgSpreadVo orgs = new StOrgSpreadVo();
					orgs.setOrgcode(id);
					orgs.setOrgname(name);
					//����������ӽ�����
					orgVos.add(orgs);
				}
			}
			//��ȡ��Ʒ��������,����һ������
			List<StProductSpreadVo> productBig = new ArrayList<StProductSpreadVo>();
			@SuppressWarnings("unchecked")
			List<ParamPopupBean> lists =new SmTools().getParamsDisplay(busyname,productco, null);
			if(lists.size()>0){
					for(int i=0;i<lists.size();i++){
						ParamPopupBean ppb = (ParamPopupBean)lists.get(i);
						StProductSpreadVo stp = new StProductSpreadVo();
						stp.setProductcode(ppb.getCode());
						stp.setProductname(ppb.getName());
						productBig.add(stp);
					}
			}
			//��ȡ�������ϵĴ�С
			int orgNum = orgVos.size();
			//set��������
			sf.setProductVoList(productBig);
			sf.setOrgnum(orgNum);
			sf.setOrgVoList(orgVos);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}
	
	/**
	 * ��ѯ���ޣ���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward query(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String forward = "stLimitamount";
		StLimitAmountParaMaintainForm sf = (StLimitAmountParaMaintainForm) form;
		//��ȡ��½�û�
		User user = SmUtil.getCurrentUser();
		//���ػ�������
		List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
		//��ȡ���л���
		List<Group> groups = GroupDao.getInstance().queryCityGroupList(user.getGroupId());
		try{
			if(groups.size()>0){
				for(int i=0;i<groups.size();i++){
					String id = groups.get(i).getId();
					//��ѯ��������
					String name = GroupDao.getInstance().queryValueByAppoint(id, "NAME");
					//��װ��������
					StOrgSpreadVo orgs = new StOrgSpreadVo();
					orgs.setOrgcode(id);
					orgs.setOrgname(name);
					//����������ӽ�����
					orgVos.add(orgs);
					}
				}
					//��ȡ��Ʒ��������,����һ������
					List<StProductSpreadVo> productBig = new ArrayList<StProductSpreadVo>();
					@SuppressWarnings("unchecked")
					List<ParamPopupBean> lists =new SmTools().getParamsDisplay(busyname,productco, null);
					if(lists.size()>0){
							for(int i=0;i<lists.size();i++){
								ParamPopupBean ppb = (ParamPopupBean)lists.get(i);
								StProductSpreadVo stp = new StProductSpreadVo();
								stp.setProductcode(ppb.getCode());
								stp.setProductname(ppb.getName());
								productBig.add(stp);
							}
					}
					//ͨ���ṩ�����޲�ѯ��������ѯ��������
					StLimitamountParm slp = new StLimitamountParm();
					slp.setGradecode("0");
					slp.setOrgcode(sf.getOrgcode());
					slp.setProductcentercode(sf.getProductcentercode());
					slp.setProductcode(sf.getProductcode());
					//ͨ��������ţ���Ʒ���࣬��Ʒ����Լ����ޱ�Ų�ѯ���ݿ�
					List<StLimitamountParmVo> limitList = stLimitamountService.queryListByModel(slp);
					if(limitList.size()>0 && null != limitList){
						for(StLimitamountParmVo svo:limitList){
							if(svo.getTermcode().equals("1")){
								svo.setTermname("�������� ");
							}
							if(svo.getTermcode().equals("2")){
								svo.setTermname("���굽һ�� ");
							}
							if(svo.getTermcode().equals("3")){
								svo.setTermname("һ�굽���� ");
							}
							if(svo.getTermcode().equals("4")){
								svo.setTermname("���굽���� ");
							}
							if(svo.getTermcode().equals("5")){
								svo.setTermname("�������� ");
							}
							sf.getLimitAmountVo().getLimitAmountVoList().add(svo);
						}
					}else{
						List<StLimitamountParmVo> slpvs = new ArrayList<StLimitamountParmVo>();
						StLimitamountParmVo slv1 = new StLimitamountParmVo();
						slv1.setTermcode("1");
						slv1.setTermname("��������");
						slv1.setUnratefamilynetassetratio(num);
						slv1.setRateralatefamilynetassetratio(num);
						slv1.setFamilynetinratio(num);
						slv1.setCollateralratio(num);
						StLimitamountParmVo slv2 = new StLimitamountParmVo();
						slv2.setTermcode("2");
						slv2.setTermname("���굽һ��");
						slv2.setUnratefamilynetassetratio(num);
						slv2.setRateralatefamilynetassetratio(num);
						slv2.setFamilynetinratio(num);
						slv2.setCollateralratio(num);
						StLimitamountParmVo slv3 = new StLimitamountParmVo();
						slv3.setTermcode("3");
						slv3.setTermname("һ�굽����");
						slv3.setUnratefamilynetassetratio(num);
						slv3.setRateralatefamilynetassetratio(num);
						slv3.setFamilynetinratio(num);
						slv3.setCollateralratio(num);
						StLimitamountParmVo slv4 = new StLimitamountParmVo();
						slv4.setTermcode("4");
						slv4.setTermname("���굽����");
						slv4.setUnratefamilynetassetratio(num);
						slv4.setRateralatefamilynetassetratio(num);
						slv4.setFamilynetinratio(num);
						slv4.setCollateralratio(num);
						StLimitamountParmVo slv5 = new StLimitamountParmVo();
						slv5.setTermcode("4");
						slv5.setTermname("��������");
						slv5.setUnratefamilynetassetratio(num);
						slv5.setRateralatefamilynetassetratio(num);
						slv5.setFamilynetinratio(num);
						slv5.setCollateralratio(num);
						slpvs.add(slv1);slpvs.add(slv2);slpvs.add(slv3);slpvs.add(slv4);slpvs.add(slv5);
						sf.getLimitAmountVo().setLimitAmountVoList(slpvs);
					}
					
					//ͨ���ṩ������������ѯ��������ѯ��������
					StLimitamountParm slps = new StLimitamountParm();
					slps.setTermcode("0");
					slps.setOrgcode(sf.getOrgcode());
					slps.setProductcentercode(sf.getProductcentercode());
					slps.setProductcode(sf.getProductcode());
					//ͨ��������ţ���Ʒ���࣬��Ʒ����Լ�������Ų�ѯ���ݿ�
					List<StLimitamountParmVo> limitLists = stLimitamountService.queryListByModel(slps);
					if(limitLists.size()>0 && null != limitLists){
						for(StLimitamountParmVo svo:limitLists){
							sf.getLimitAmountVoRate().getLimitAmountVoList().add(svo);
						}
					}else{
						List<StLimitamountParmVo> slpvrs = new ArrayList<StLimitamountParmVo>();
						//��ʼ������
						StLimitamountParmVo slv4 = new StLimitamountParmVo();
						slv4.setGradecode("10");
						slv4.setUnratefamilynetassetratio(num);
						slv4.setRateralatefamilynetassetratio(num);
						slv4.setFamilynetinratio(num);
						slv4.setCollateralratio(num);
						StLimitamountParmVo slv5 = new StLimitamountParmVo();
						slv5.setGradecode("9");
						slv5.setUnratefamilynetassetratio(num);
						slv5.setRateralatefamilynetassetratio(num);
						slv5.setFamilynetinratio(num);
						slv5.setCollateralratio(num);
						StLimitamountParmVo slv6 = new StLimitamountParmVo();
						slv6.setGradecode("8");
						slv6.setUnratefamilynetassetratio(num);
						slv6.setRateralatefamilynetassetratio(num);
						slv6.setFamilynetinratio(num);
						slv6.setCollateralratio(num);
						StLimitamountParmVo slv7  = new StLimitamountParmVo();
						slv7.setGradecode("7");
						slv7.setUnratefamilynetassetratio(num);
						slv7.setRateralatefamilynetassetratio(num);
						slv7.setFamilynetinratio(num);
						slv7.setCollateralratio(num);
						StLimitamountParmVo slv8 = new StLimitamountParmVo();
						slv8.setGradecode("6");
						slv8.setUnratefamilynetassetratio(num);
						slv8.setRateralatefamilynetassetratio(num);
						slv8.setFamilynetinratio(num);
						slv8.setCollateralratio(num);
						StLimitamountParmVo slv9 = new StLimitamountParmVo();
						slv9.setGradecode("5");
						slv9.setUnratefamilynetassetratio(num);
						slv9.setRateralatefamilynetassetratio(num);
						slv9.setFamilynetinratio(num);
						slv9.setCollateralratio(num);
						StLimitamountParmVo slv10 = new StLimitamountParmVo();
						slv10.setGradecode("4");
						slv10.setUnratefamilynetassetratio(num);
						slv10.setRateralatefamilynetassetratio(num);
						slv10.setFamilynetinratio(num);
						slv10.setCollateralratio(num);
						StLimitamountParmVo slv11 = new StLimitamountParmVo();
						slv11.setGradecode("3");
						slv11.setUnratefamilynetassetratio(num);
						slv11.setRateralatefamilynetassetratio(num);
						slv11.setFamilynetinratio(num);
						slv11.setCollateralratio(num);
						StLimitamountParmVo slv12 = new StLimitamountParmVo();
						slv12.setGradecode("2");
						slv12.setUnratefamilynetassetratio(num);
						slv12.setRateralatefamilynetassetratio(num);
						slv12.setFamilynetinratio(num);
						slv12.setCollateralratio(num);
						StLimitamountParmVo slv13 = new StLimitamountParmVo();
						slv13.setGradecode("1");
						slv13.setUnratefamilynetassetratio(num);
						slv13.setRateralatefamilynetassetratio(num);
						slv13.setFamilynetinratio(num);
						slv13.setCollateralratio(num);
						slpvrs.add(slv4);slpvrs.add(slv5);slpvrs.add(slv6);slpvrs.add(slv7);
						slpvrs.add(slv8);slpvrs.add(slv9);slpvrs.add(slv10);slpvrs.add(slv11);
						slpvrs.add(slv12);slpvrs.add(slv13);
						sf.getLimitAmountVoRate().setLimitAmountVoList(slpvrs);
					}
					//��ȡ�������ϵĴ�С
					int orgNum = orgVos.size();
					//set��������
					sf.setProductVoList(productBig);
					sf.setOrgnum(orgNum);
					sf.setOrgVoList(orgVos);
				}catch(Exception e){
					e.printStackTrace();
				}
		return mapping.findForward(forward);
	}
	/**
	 * ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward insert(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		response.setContentType("application/json;charset=UTF-8");
		StLimitAmountParaMaintainForm limit = (StLimitAmountParaMaintainForm)form;
		//��ȡ��½�û�
		User user = SmUtil.getCurrentUser();
		//��ȡ�����ϴ�����
		String result="����ʧ��";
		try {
			//��ȡ�ύ�����޲�������
		List<StLimitamountParmVo> terms = limit.getLimitAmountVo().getLimitAmountVoList();
		for(StLimitamountParmVo sv:terms){
			//���ò�ѯ����
			StLimitamountParm stlimit = new StLimitamountParm();
			stlimit.setTermcode(sv.getTermcode());
			stlimit.setOrgcode(limit.getOrgcode());
			stlimit.setProductcentercode(limit.getProductcentercode());
			stlimit.setProductcode(limit.getProductcode());
			try {
				//��ѯ���ݿ��Ƿ�������
				List<StLimitamountParmVo> lists = stLimitamountService.queryListByModel(stlimit);
				//�ж��Ƿ� ������
				if(lists.size() >0 && null != lists){
					for(StLimitamountParmVo svo:lists){
						StLimitamountParm st = new StLimitamountParm();
						st.setId(svo.getId());
						st.setUnratefamilynetassetratio(sv.getUnratefamilynetassetratio());
						st.setRateralatefamilynetassetratio(sv.getRateralatefamilynetassetratio());
						st.setFamilynetinratio(sv.getFamilynetinratio());
						st.setCollateralratio(sv.getCollateralratio());
						st.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
						stLimitamountService.update(st);
						result="����ɹ�";
					}
				}else{
					//���һ��������
					StLimitamountParm slp = new StLimitamountParm();
					slp.setOrgcode(limit.getOrgcode());
					slp.setProductcentercode(limit.getProductcentercode());
					slp.setProductcode(limit.getProductcode());
					slp.setTermcode(sv.getTermcode());
					slp.setGradecode("0");
					slp.setUnratefamilynetassetratio(sv.getUnratefamilynetassetratio());
					slp.setRateralatefamilynetassetratio(sv.getRateralatefamilynetassetratio());
					slp.setFamilynetinratio(sv.getFamilynetinratio());
					slp.setCollateralratio(sv.getCollateralratio());
					slp.setCreateusercode(user.getId());
					slp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
					slp.setIseffect("1");
					slp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
					slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					stLimitamountService.add(slp);
					result="����ɹ�";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//��ȡ�����ϴ�����
		List<StLimitamountParmVo> grades = limit.getLimitAmountVoRate().getLimitAmountVoList();
		for(StLimitamountParmVo sv:grades){
			//���ò�ѯ����
			StLimitamountParm stlimit = new StLimitamountParm();
			stlimit.setGradecode(sv.getGradecode());
			stlimit.setOrgcode(limit.getOrgcode());
			stlimit.setProductcentercode(limit.getProductcentercode());
			stlimit.setProductcode(limit.getProductcode());
			//��ѯ���ݿ��Ƿ�������
			List<StLimitamountParmVo> lists;
			try {
				lists = stLimitamountService.queryListByModel(stlimit);
				//�ж��Ƿ� ������
				if(lists.size() >0 && null != lists){
					for(StLimitamountParmVo svo:lists){
						StLimitamountParm st = new StLimitamountParm();
						st.setId(svo.getId());
						st.setUnratefamilynetassetratio(sv.getUnratefamilynetassetratio());
						st.setRateralatefamilynetassetratio(sv.getRateralatefamilynetassetratio());
						st.setFamilynetinratio(sv.getFamilynetinratio());
						st.setCollateralratio(sv.getCollateralratio());
						st.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
						stLimitamountService.update(st);
						result="����ɹ�";
					}
				}else{
					//���һ��������
					StLimitamountParm slp = new StLimitamountParm();
					slp.setOrgcode(limit.getOrgcode());
					slp.setProductcentercode(limit.getProductcentercode());
					slp.setProductcode(limit.getProductcode());
					slp.setGradecode(sv.getGradecode());
					slp.setTermcode("0");
					slp.setUnratefamilynetassetratio(sv.getUnratefamilynetassetratio());
					slp.setRateralatefamilynetassetratio(sv.getRateralatefamilynetassetratio());
					slp.setFamilynetinratio(sv.getFamilynetinratio());
					slp.setCollateralratio(sv.getCollateralratio());
					slp.setCreateusercode(user.getId());
					slp.setCreatetime(new java.sql.Timestamp(new Date().getTime()));
					slp.setIseffect("1");
					slp.setEndtime(new java.sql.Timestamp(new Date().getTime()));
					slp.setLastopertime(new java.sql.Timestamp(new Date().getTime()));
					stLimitamountService.add(slp);
					result="����ɹ�";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JsonObject o = new JsonObject();
		o.addProperty("result",result);
		PrintWriter out;
		out = response.getWriter();
		out.write(o.toString());
		out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//stLimitamountService
		return null;
	}
	
	/**
	 * ��ѯ���˻���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward queryCorpOrg(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		StLimitAmountParaMaintainForm sf = (StLimitAmountParaMaintainForm) form;
		List<StOrgSpreadVo> orgVos = new ArrayList<StOrgSpreadVo>();
		//���ݻ����Ų�ѯ�����еķ��˻���,����һ������
		List<String> orgIds = new ArrayList<String>();
	    try {
			GroupDao.getInstance().queryOfficeUnitFrGroupList(sf.getOrgcode(),orgIds);
			if(orgIds.size()>0){
				for(int i=0;i<orgIds.size();i++){
					String id=orgIds.get(i);
					String name = GroupDao.getInstance().queryValueByAppoint(id, "NAME");
					StOrgSpreadVo orgs = new StOrgSpreadVo();
					orgs.setOrgcode(id);
					orgs.setOrgname(name);
					orgVos.add(orgs);
				}
			}
			// ��װ�������ݵ�ǰ̨
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			JsonObject allObject = new JsonObject();
			JsonArray ja = new JsonArray();
			for (StOrgSpreadVo ssv : orgVos) {
				JsonObject o = new JsonObject();
				o.addProperty("orgcode", ssv.getOrgcode());
				o.addProperty("orgname", ssv.getOrgname());
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
	 * ��ѯ��ɫ��Ʒ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward queryProduct(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		StLimitAmountParaMaintainForm sf = (StLimitAmountParaMaintainForm) form;
		try {
			List<StProductSpread> sts = stProductSpreadService.queryProductSmaByProBig(sf.getOrgcode(),
					sf.getProductcode());
			// ��װ�������ݵ�ǰ̨
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			JsonObject allObject = new JsonObject();
			JsonArray ja = new JsonArray();
			for (StProductSpread ss : sts) {
				JsonObject o = new JsonObject();
				o.addProperty("productcode", ss.getProductcode());
				o.addProperty("productname", ss.getProductname());
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
}
