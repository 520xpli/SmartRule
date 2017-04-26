<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://www.cvicse.com/tags-common" prefix="common"%>
<%@ taglib uri="http://www.cvicse.com/tags-param" prefix="param"%>
<%  response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%@page import="java.util.List"%>
<%@page import="com.sdnx.st.sm.vo.StOrgSpreadVo"%>
<html:html>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GBK">
<META name="GENERATOR" content="IBM WebSphere Studio">
<script src="<%=request.getContextPath()%>/js/jquery-1.4.2.js"
	language="javascript">
	
</script>
<TITLE>�����ֿ������ѯ</TITLE>
<common:theme />
</head>
<body>
	<common:form action="/StSystemMaintain.do" method="get">
	<common:hidden property="operAtt" />
	<common:hidden name="stSystemForm" property="ratepara" styleId="ratepara"/>
	<common:hidden name="stSystemForm" property="orgcodeR" styleId="orgcodeR"/>
	<table width="100%">
	  	<TR>
				<TD class="pagehead" >�����ֿ������ѯ</TD>
		</TR>
		<tr>
			<td class="regionhead" colspan="4">��ѯ����</td>
		</tr>
		<tr>
			<td>
				<table class="free" width="100%">
					<tbody>
						<tr>
							<th>����</th>
							<td colspan="3">
								<param:select style="width:240px" nullStr="-��ѡ��-"
								 label="���� " styleId="custMP" empty="false" type="cmis.custManager" name="stSystemForm" property="custMP"/>
							</td>
						</tr>
						<tr>
							<th>�ͻ����</th>
							<td align="center">
								<input type="text" id="custcode" name="stRateResultVo.custcode" value="<bean:write name="stSystemForm" property="stRateResultVo.custcode"/>" style="width:240px" /> 
							</td>
							<th>�ͻ�����</th>
							<td>
								<common:text empty="true" label="�ͻ�����" size="20"
									validator="text(0,255)" name="stSystemForm" style="width:240px"
											property="stRateResultVo.custname" styleId="custnameDe" />
								<input type="hidden" id="custnameEn" name="custname" value="<bean:write name="stSystemForm" property="custname"/>" style="width:150px" /> 
							</td>
						</tr>
						<tr>
							<th>֤������</th>
							<td align="center">
								<param:select style="width:240px" type="party.identityType" nullStr="-��ѡ��-"
									property="stRateResultVo.idtype"  name="stSystemForm" 
									label="���" styleId="idtype" empty="true"/> 
							</td>
							<th>֤������</th>
							<td>
								<input type="text" id="idnumber" name="stRateResultVo.idnumber" value="<bean:write name="stSystemForm" property="stRateResultVo.idnumber"/>" style="width:240px" /> 
							</td>
						</tr>
						<tr>
							<th>����������</th>
							<td align="center">
								<common:canlendarInput empty="true" label="������ʼ��"
								name="stSystemForm" style="WIDTH: 225"
								property="dateFrom" styleId="dateFrom"
								validator="date(yyyy-mm-dd)" onchange="checkDate()"/>
							</td>
							<th>��������ֹ</th>
							<td>
								<common:canlendarInput empty="true" label="����������"
								name="stSystemForm" style="WIDTH: 225"
								property="dateTo" styleId="dateTo"
								validator="date(yyyy-mm-dd)" onchange="checkDate()" />
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								<input type="hidden" name="stRateResultVo.orgcode" value="<bean:write name="stSystemForm" property="stRateResultVo.orgcode"/>"/> 
								<input type="text" id="orgnameDe" readonly value="<bean:write name="stSystemForm" property="stRateResultVo.orgname"/>" style="width:240px;color:gray" /> 
								<input type="hidden" id="orgnameEn" name="stRateResultVo.orgname" readonly value="<bean:write name="stSystemForm" property="stRateResultVo.orgname"/>" style="width:150px;color:gray" /> 
							</td>
							<th>����</th>
							<td>
								<param:select type="cmis.custLevel10" name="stSystemForm"
									property="stRateResultVo.ratecode" styleId="ratecode" style="width:240px"
									nullStr="--��ѡ��--" ></param:select>
							</td>
						</tr>
						<tr>
							<th>�ͻ�����</th>
							<td colspan="3"> 
								<input type="hidden" id="custmanager" name="stRateResultVo.custmanager" value="<bean:write name="stSystemForm" property="stRateResultVo.custmanager"/>" style="width:150px" /> 
								<input type="text" id="custmanagernameDe" readonly value="<bean:write name="stSystemForm" property="custmanagername"/>" style="width:240px;color:gray" /> 
								<input type="hidden" id="custmanagernameEn" name="custmanagername"  readonly value="<bean:write name="stSystemForm" property="custmanagername"/>" style="width:150px;color:gray" /> 
							</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align:center">
								<input type="button"   value="��ѯ" 	onclick="queryRateResult()"/>
								<input type="button"   value="����" 	onclick="resetAll()"/>
							</td>
						</tr>
						</tbody>
				</table>
			</td>
		</tr>
		<tr id="rateresult" style="display:none">
			<td  colspan="4" width="100%" align="left" valign="top">
				<table align="left" width="100%" class="grid" id="showResult" style="margin-top:20px">
					<tbody>
						<TR align=center>
							<TD class="regioncomposite" colspan="13"><bean:write
							name="stSystemForm" property="footer" filter="false" /></TD>
						</TR>
						<tr>
							<th width="12%">�ͻ����</th>
							<th width="12%">�ͻ�����</th>
							<th width="12%">���ֿ�����</th>
							<th width="12%">��������</th>
							<th width="12%">��������</th>
							<th width="12%"><span id="managerid">�����ͻ�����</span></th>
							<th width="12%">��������</th>
							<th width="12%">�����ȼ�</th>
							<th>����</th>
						</tr>
						<logic:iterate id="stRateResultVo" name="stSystemForm"
									property="footer.dataArray" indexId="index">
							<tr>
								<td>
									<bean:write name="stRateResultVo" property="custcode"/>
								</td>
								<td>
									<bean:write name="stRateResultVo" property="custname"/>
								</td>
								<td>
									<param:display type="cmis.markingCard" name="stRateResultVo" property="cardcode" />
								</td>
								<td>
									<bean:write name="stRateResultVo" property="ratedate"/>
								</td>
								<td>
									<bean:write name="stRateResultVo" property="orgname"/>
								</td>
								<td>
									<bean:write name="stRateResultVo" property="custmanagername"/>
								</td>
								<td>
									<bean:write name="stRateResultVo" property="ratescore"/>
								</td>
								<td>
									<param:display type="cmis.custLevel10" name="stRateResultVo" property="ratecode" />
								</td>
								<td>
									<input type="button" value="���ݲ�ѯ " onclick="queryResultData('<bean:write name="stRateResultVo" property="rateresultcode"/>')"/>
									<%-- <input  type="button" value="���� "  onclick="queryScoreData('<bean:write name="stRateResultVo" property="rateresultcode"/>','<bean:write name="stRateResultVo" property="businesscode"/>')"/>
								 --%></td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
	</common:form>
<script type="text/javascript">
	$(function(){
		if($("#ratepara").val()){
			$("#rateresult").show();
			if($("#custMP").val()==0)
				$("#managerid").html("����ͻ�����");
			else
				$("#managerid").html("�����ͻ�����");
			$("#ratepara").val("")
		}
		$("#url").val(location.href);
		$("#custmanagernameDe").val(decodeURI($("#custmanagernameEn").val()));
		$("#orgnameDe").val(decodeURI($("#orgnameEn").val()));
		$("#custnameDe").val(decodeURI($("#custnameEn").val()));
	})
	function resetAll(){
		$("#custMP").val("");
		$("#custcode").val("");
		$("#custnameDe").val("");
		$("#idtype").val("");
		$("#dateFrom").val("");
		$("#dateTo").val("");
		$("#ratecode").val("");
		$("#idnumber").val("");
	}
	function queryRateResult(){
		if($("#custMP").val() == null || "" == $("#custMP").val()){
			alert("��ѡ������");
			return;
		}
		if(ividtype()){
			$("#orgname").attr("disabled",false);
			$("#custmanagername").attr("disabled",false);
			with(document.forms[0]){
				operAtt.value="rateQueryByM";
				$("#custmanagernameEn").val(encodeURI($("#custmanagernameDe").val()));
				$("#orgnameEn").val(encodeURI($("#orgnameDe").val()));
				$("#custnameEn").val(encodeURI($("#custnameDe").val()));
				submit();
			}
		}
	}
	function queryResultData(rateresultcode){
		var locurl = window.location.href; 
		locurl = encodeURIComponent(locurl);
		var url = '<%=request.getContextPath()%>'+'/st/StSystemMaintain.do?operAtt=rateDataQuery&stRateresultDataVo.rateresultcode=' + rateresultcode+'&backUrl='+locurl;
		window.location.href=url;
	}
	function queryScoreData(rateresultcode,businesscode){
		var locurl = window.location.href; 
		locurl = encodeURIComponent(locurl);
		var url = '<%=request.getContextPath()%>'+'/st/StSystemMaintain.do?operAtt=rateScoreQuery&stRateResultVo.rateresultcode=' + rateresultcode+'&stRateResultVo.businesscode=' + businesscode +'&backUrl='+locurl;
		window.location.href=url;
	}
	function checkDate(){
		var beginDate=document.getElementById("dateFrom").value;
		var endDate=document.getElementById("dateTo").value;
		if(beginDate>endDate){
				alert("�����������ڲ���С������������!");
				document.getElementById("dateTo").value="";	
			}
	
	
	}
	function ividtype(){
		var idtype=document.getElementById("idtype").value;
		var idnumber=document.getElementById("idnumber").value;
		if(idtype!="" && idnumber==""){
			alert("֤������Ϊ�գ�");
			return false;
		}else if(idnumber!="" && idtype==""){
			alert("��ѡ��֤�����ͣ�");
			return false;
		}
		return true;
		
	}
</script>
</body>
</html>
</html:html>