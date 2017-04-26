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
<TITLE>机构推广参数维护</TITLE>
<common:theme />
</HEAD>
<BODY onload="myfunction()">
	<common:form action="/StOrgSpreadMaintain.do" method="get">
	<common:hidden property="operAtt" />	
	<common:hidden name="stOrgSpreadForm" property="stOrgSpreadVo.orgcode" styleId="orgcodeh"/>
	  <table width="100%">
	  	<TR>
				<TD class="pagehead" >推广参数维护（县）</TD>
		</TR>
		<tr>
			<td class="regionhead" colspan="4">查询条件</td>
		</tr>
		<tr class="regioncomposite">
			<td>地市机构     &nbsp;&nbsp;&nbsp;&nbsp;
			<select name="orgcode" style="width:150px">
				<logic:equal value="1" name="stOrgSpreadForm" property="orgNum">
					<logic:iterate id="StOrgSpreadVo" name="stOrgSpreadForm"
								property="stOrgList" indexId="indexId">
						<option value="<bean:write name="StOrgSpreadVo" property="orgcode"></bean:write>"><bean:write name="StOrgSpreadVo" property="orgname"></bean:write></option>	
					</logic:iterate>
				</logic:equal>
				<logic:notEqual value="1" name="stOrgSpreadForm" property="orgNum">
					<option value="-1">-请选择-</option>
					<logic:iterate id="StOrgSpreadVo" name="stOrgSpreadForm"
							property="stOrgList" indexId="indexId">
						<option value="<bean:write name="StOrgSpreadVo" property="orgcode"></bean:write>"><bean:write name="StOrgSpreadVo" property="orgname"></bean:write></option>
					</logic:iterate>
				</logic:notEqual>
			</select><font  color=red >*</font></td>
		</tr>
		<tr>
			<td class="regionfoot">
				<input type="button"   value="查询" 	onclick="query()"/>&nbsp;&nbsp;
				<input type="button" 	value="重置" onclick="reset()"/>&nbsp;&nbsp;
			</td>
		</tr>
		<TR align=center>
				<TD class="regioncomposite"><bean:write
					name="stOrgSpreadForm" property="footer" filter="false" />
				</TD>
		</TR>
		<tr>
			<td width="100%" align="left" valign="top">
			<table align="left" width="100%" class="grid" id="showData" style="">
				<tbody>
					<tr>
						<th width="20%">机构编号</th>
						<th width="40%">机构名称</th>
						<th colspan="4">操作</th>
					</tr>
					<logic:iterate id="stOrgSpreadVo" name="stOrgSpreadForm"
									property="footer.dataArray" indexId="indexId">
						<tr>
							<td><bean:write name="stOrgSpreadVo" property="orgcode"></bean:write></td>
							<td><bean:write name="stOrgSpreadVo" property="orgname"></bean:write></td>
							
								<td width="10%">
								<input type="hidden" id="isopenId" name="isopen" value="<bean:write name="stOrgSpreadVo" property="isopen" />">
								<input id="queryid" name="queryorg" type="button" value="产品配置" iconClass="queryIcon"
								 onclick="queryPro('<bean:write name="stOrgSpreadVo" property="orgcode" />',
								 '<bean:write name="stOrgSpreadVo" property="orgname" />')"/></td>
								 <td width="10%">
								 	<input id="dotid" name="querydot" type="button" value="机构配置" iconClass="queryIcon"
								 onclick="queryDot('<bean:write name="stOrgSpreadVo" property="orgcode" />')"/>
								 </td>
							</td>
						</tr>
					</logic:iterate>
				</tbody>
			</table>
			</td>
		</tr>
	</table>
</common:form>
<script type="text/javascript">
function myfunction(){
	$("input[name='isopen']").each(function(){
		var isopen = $(this).val();
		if(isopen=="0"){
			$(this).parent().parent().find("input[name='queryorg']").attr("disabled",true);
			$(this).parent().parent().find("input[name='querydot']").attr("disabled",true);
		}else{
			$(this).parent().parent().find("input[name='queryorg']").attr("disabled",false);
			$(this).parent().parent().find("input[name='querydot']").attr("disabled",false);
		}
	})
}

$(document).ready(function(){
	var code = $("#orgcodeh").val();
	if(code!=null && code!=""){
		$("select[name='orgcode']").find("option[value='"+code+"']").attr("selected",true);
	}
})
//查询法人机构跳转
function query(){
	if($("select[name='orgcode']").val()=="-1" || $("select[name='orgcode']").val() == ""){
		alert('请选择机构');
		return;
	}
	with(document.forms[0]){
		operAtt.value="querylegal";
		submit();
	}
}
//机构配置跳转
function queryDot(orgcode){
	var backUrl = window.location.href;
	backUrl = encodeURIComponent(backUrl);
	var url = '<%=request.getContextPath()%>'+'/st/StOrgSpreadMaintain.do?operAtt=queryDot&orgcode='+orgcode+'&backUrl='+backUrl;
	window.location.href=url;
}
/*
 * 产品配置跳转
 */
function queryPro(orgcode,orgname){
	var backUrl = window.location.href;
	backUrl = encodeURIComponent(backUrl);
	var url = '<%=request.getContextPath()%>'+'/st/StProductSpreadMaintain.do?operAtt=queryProductBigByOrg&orgcode='+orgcode+'&orgname='+orgname+'&backUrl='+backUrl;
	window.location.href=url;
}
/**
 * 开启
 */
function openOrg(p,orgcode,id,orgname){
			var url = '<%=request.getContextPath()%>'+'/st/StOrgSpreadMaintain.do?operAtt=openOrg';
			$.ajax({
				type:"post",
				url:url,
				data:{orgcode:orgcode,id:id,orgname:orgname},
				contentType:"application/x-www-form-urlencoded;charset=utf-8",
				success:function(data){
					alert(data.retu);
					if(data.retu="开启成功"){
						$(p).parent().parent().find("input[name='openo']").attr("disabled",true);
						$(p).parent().parent().find("input[name='closeo']").attr("disabled",false);
						$(p).parent().parent().find("input[name='queryorg']").attr("disabled",false);
						$(p).parent().parent().find("input[name='querydot']").attr("disabled",false);
					}
				}
			})
}
/**
 * 关闭
 */
function closeOrg(p,orgcode,id,orgname){
	var ajaxUrl = '<%=request.getContextPath()%>'+'/st/StOrgSpreadMaintain.do?operAtt=closeOrg';
	$.ajax({
		type:"post",
		url:ajaxUrl,
		data:{orgcode:orgcode,id:id,orgname:orgname},
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		success:function(data){
			alert(data.retu);
			if(data.retu="关闭成功"){
				$(p).parent().parent().find("input[name='closeo']").attr("disabled",true);
				$(p).parent().parent().find("input[name='openo']").attr("disabled",false);
				$(p).parent().parent().find("input[name='queryorg']").attr("disabled",true);
				$(p).parent().parent().find("input[name='querydot']").attr("disabled",true);
			}
		}
	})
}
</script>
</BODY>
</html>
</html:html>