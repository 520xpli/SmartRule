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
<script src="<%=request.getContextPath()%>/js/jquery-1.4.2.js" language="javascript">
</script>
<script src="<%=request.getContextPath() %>/js/sql2Excel.js" language="javascript"></script>
<TITLE>规则执行结果统计查询(部门)</TITLE>
<common:theme />
</head>
<body>
	<common:form action="/StSystemMaintain.do" method="get">
	<common:hidden property="operAtt" />
	<common:hidden name="stSystemForm" property="orgcodeR" styleId="orgcodeR"/>
	<input type="hidden" id="hd_querySql" value="select * from st_rule_log where id=1"/>
	<input type="hidden" id="hd_mapId" value="autoRuleStatisticsExport" />
	<input type="hidden" id="exportCount" value="100" />
	 <table width="100%">
	  	<TR>
				<TD class="pagehead" >规则执行结果统计查询(部门)</TD>
		</TR>
		<tr>
			<td class="regionhead" colspan="4">查询条件</td>
		</tr>
		<tr>
			<td>
				<table class="free" width="100%">
					<tbody>
						<tr>
							<th>起始日期</th>
							<td align="center">
								<common:canlendarInput empty="true" label="评级起始日"
								name="stSystemForm" style="WIDTH: 225"
								property="dateFrom" styleId="dateFrom"
								validator="date(yyyy-mm-dd)"/>
							</td>
							<th>终止日期</th>
							<td>
								<common:canlendarInput empty="true" label="评级到期日"
								name="stSystemForm" style="WIDTH: 225"
								property="dateTo" styleId="dateTo"
								validator="date(yyyy-mm-dd)" />
							</td>
						</tr> 
						<tr>
							<th>机构</th>
							<td>
								<param:popup name="stSystemForm" style="width:225px"
								clientCondition="true" displayStyleId="ownerGroupName"
								property="stRateResultVo.orgcode" 
								type="rbac.groupselect" displayType="rbac.group"
								empty="true" label="所属机构" validator="text(0,150)"
								styleId="orgcode" condition="&orgcodeR,&orgcodeR" targets="orgcode,ownerGroupName"
								clickImg="/skins/images/search.gif" />
								<font style="color:red">*</font>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align:center">
								<input type="hidden" id="btn_sqj2Excel" />
								<input type="button"   value="导出EXCEL" onclick="downloadExcel()"/>
								<input type="button"   value="重置" 	onclick="resetAll()"/>
							</td>
						</tr>
						</tbody>
				</table>
			</td>
		</tr>
	</table>
	</common:form>
<script type="text/javascript">
	var shadowText = null;
	$(function(){
		if($("#ratepara").val()){
			$("#rateresult").show();
			$("#ratepara").val("")
		}
		$("#custnameDe").val(decodeURI($("#custnameEn").val()));
	})
	function downloadExcel(){
		var mapId = "autoRuleStatisticsExport";
		var orgCode = $("#orgcode").val();
		var dateFrom = $("#dateFrom").val();
		var dateTo = $("#dateTo").val();
		if(orgCode == undefined || orgCode == ""){
			alert("请选择机构");
			return;
		}
		if(dateFrom == undefined || dateFrom == ""){
			alert("请选择起始日期");
			return;
		}
		if(dateTo == undefined || dateTo == "" ){
			alert("请选择终止日期");
			return;
		}
		var beginDate = document.getElementById("dateFrom").value;
		var endDate = document.getElementById("dateTo").value;
		if(beginDate > endDate){
				alert("终止日期不可小于起始日期!");
				document.getElementById("dateTo").value="";	
				return;
		}
		showShadow("正在导出...");
		$.ajax({
			type : "POST",
			url : "/ngcms/st/StSystemMaintain.do?operAtt=ruleStatisticsExport",
			data : {mapId:mapId,orgCode:orgCode,dateFrom:dateFrom,dateTo:dateTo},
			success : function(msg) {
				if (msg.indexOf("ERROR") > -1) {
					alert("导出失败，请联系管理员");
					hideShadow();
				} else {
					showShadow("导出成功");
					window.setTimeout("showShadow('文件下载中...')", 1000);
					window.setTimeout("$('#downloadFrame').attr('src', '/ngcms/pm/pmQueryForDownload.do?operAtt=toDownloadFile&filePath=' + '" + msg + "')", 1000);
				}
			}
		});
	}
	function resetAll(){
		$("#custcode").val("");
		$("#custnameDe").val("");
		$("#idtype").val("");
		$("#dateFrom").val("");
		$("#dateTo").val("");
		$("#ratecode").val("");
		$("#idnumber").val("");
	}
</script>
</body>
</html>
</html:html>