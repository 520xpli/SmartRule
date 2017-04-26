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
<TITLE>评级结果查询</TITLE>
<common:theme />
</head>
<body>
	<common:form action="/StSystemMaintain.do" method="get">
	<common:hidden property="operAtt" />
	<common:hidden name="stSystemForm" property="backUrl" styleId="backUrl"/>
	 <table width="100%">
	  	<TR>
				<TD class="pagehead" >评级详细信息查询</TD>
		</TR>
		<tr id="gradeId">
			<td  colspan="4"  width="100%" align="left" valign="top">
				<table align="left" width="100%" class="grid" id="showData" style="margin-top:20px">
					<tbody>
						<tr>
							<td class="regionhead" style="text-align:center" colspan="4">评级详细信息</td>
						</tr>
						<logic:iterate id="RateShowVo" name="stSystemForm"
									property="rsList" indexId="index">
							<tr>
								<td style="width:25%">
									<bean:write name="RateShowVo" property="srd1.dataname"/>
								</td>
								<td class="value" style="width:25%">
									<bean:write name="RateShowVo" property="srd1.datavalue"/>
								</td>
								<td style="width:25%">
									<bean:write name="RateShowVo" property="srd2.dataname"/>
								</td>
								<td class="value">
									<bean:write name="RateShowVo" property="srd2.datavalue"/>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td id="button" class="regionfoot">
				<input type="button" 	value="返回" onclick="backPrev()"/>
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
</script>
</body>
</html>
</html:html>