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
<script src="<%=request.getContextPath() %>/js/sql2Excel.js" language="javascript"></script>
<TITLE>���������ѯ</TITLE>
<common:theme />
</head>
<body>
	<common:form action="/StSystemMaintain.do" method="get">
	<common:hidden property="operAtt" />
	<common:hidden name="stSystemForm" property="backUrl" styleId="backUrl"/>
	<common:hidden name="stSystemForm" property="stRateResultVo.businesscode" styleId="businesscode"/>
	<common:hidden name="stSystemForm" property="stRateResultVo.rateresultcode" styleId="rateresultcode"/>
	<input type="hidden" id="hd_querySql" value="select * from st_rule_log where id=1"/>	
	 <table width="100%">
	  	<TR>
				<TD class="pagehead" >����������ϸ��Ϣ</TD>
		</TR>
		<tr id="gradeId">
			<td  colspan="4"  width="100%" align="left" valign="top">
				<table align="left" width="100%" class="grid" id="showData" style="margin-top:20px">
					<tbody>
						<%-- <tr>
							<th>��������</th>
							<td colspan="3"> 
								<bean:write name="stSystemForm" property="stRateResultVo.ratescore"/>
							</td>
						</tr> --%>
						<tr>
							<td class="regionhead" style="text-align:center" colspan="4">����������ϸ��Ϣ</td>
						</tr>
						<tr>
							<th style="width:25%">��������</th>
							<th style="width:50%">��������</th>
							<th style="width:25%">ִ�н��</th>
							
						</tr>
						<logic:iterate id="rate" name="stSystemForm"
									property="rateList" indexId="index">
							<tr>
								<td >
									<bean:write name="rate" property="strucname"/>
								</td>
								<td class="value">
									<bean:write name="rate" property="ruleData"/>
								</td>
								<td>
									<bean:write name="rate" property="ruleresult"/>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
	<table width="100%">
	  	<TR>
				<TD class="pagehead" >��ȼ�����ϸ��Ϣ</TD>
		</TR>
		<tr id="gradeId">
			<td  colspan="4"  width="100%" align="left" valign="top">
				<table align="left" width="100%" class="grid" id="showData" style="margin-top:20px">
					<tbody>
						<%-- <tr>
							<th>��������</th>
							<td colspan="3"> 
								<bean:write name="stSystemForm" property="stRateResultVo.ratescore"/>
							</td>
						</tr> --%>
						<tr>
							<td class="regionhead" style="text-align:center" colspan="4">��ȼ�����ϸ��Ϣ</td>
						</tr>
						<tr>
							<th style="width:25%">��������</th>
							<th style="width:50%">��������</th>
							<th style="width:25%">ִ�н��</th>
							
						</tr>
						<logic:iterate id="amount" name="stSystemForm"
									property="amountList" indexId="index">
							<tr>
								<td >
									<bean:write name="amount" property="strucname"/>
								</td>
								<td class="value">
									<bean:write name="amount" property="ruleData"/>
								</td>
								<td>
									<bean:write name="amount" property="ruleresult"/>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td id="button" class="regionfoot">
				<input type="hidden" id="btn_sqj2Excel" />
				<input type="button"   value="����EXCEL" onclick="downloadExcel()"/>
				<input type="button" 	value="����" onclick="backPrev()"/>
			</td>
		</tr>
	</table>
	</common:form>
<script type="text/javascript">
	$(function(){
		$("#gradeId").find("td:visible[class='value']").each(function(){
			if($(this).html()=="null " || $(this).html() == "null")
				$(this).html("");
		})
	})
	function backPrev(){
		var backUrl = $("#backUrl").val();
		window.location.href=backUrl;
	}
	function downloadExcel(){
		var businesscode =  $("#businesscode").val();
		var rateresultcode = $("#rateresultcode").val();
		var url = '<%=request.getContextPath()%>'+'/st/StSystemMaintain.do?operAtt=rateScoreExport&stRateResultVo.businesscode=' + businesscode + '&stRateresultDataVo.rateresultcode='+rateresultcode;
		showShadow("���ڵ���...");
		$.ajax({
			type:"post",
			url:url,
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(msg){
				if (msg.indexOf("ERROR") > -1) {
					alert("����ʧ�ܣ�����ϵ����Ա");
					hideShadow();
				} else {
					showShadow("�����ɹ�");
					window.setTimeout("showShadow('�ļ�������...')", 1000);
					window.setTimeout("$('#downloadFrame').attr('src', '/ngcms/pm/pmQueryForDownload.do?operAtt=toDownloadFile&filePath=' + '" + msg + "')", 1000);
				}
			}
		})
	}
</script>
</body>
</html>
</html:html>