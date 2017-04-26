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
<TITLE>机构推广维护</TITLE>
<common:theme />
</HEAD>
<BODY>
	<common:form action="/StOrgSpreadMaintain.do" method="get">
	<common:hidden property="operAtt" />	
	<common:hidden name="stOrgSpreadForm" property="backUrl" styleId="backUrl"/>
	<common:hidden name="stOrgSpreadForm" property="orgcode" styleId="orgcode"/>
	<common:hidden name="stOrgSpreadForm" property="orgCon" styleId="orgcon"/>
	  <table width="100%">
	  	<TR>
				<TD class="pagehead" >网点维护</TD>
		</TR>
		<tr>
			<td class="regionhead" colspan="4">查询条件</td>
		</tr>
		<tr class="regioncomposite">
			<td align="center">
			<select id="orgid" name="stOrgSpreadVo.orgcode" style="width:250px">
				<option value="-1">-请选择-</option>
				<logic:iterate id="StOrgSpreadVo" name="stOrgSpreadForm"
						property="stOrgList" indexId="indexId">
					<option value="<bean:write name="StOrgSpreadVo" property="orgcode"></bean:write>"><bean:write name="StOrgSpreadVo" property="orgname"></bean:write></option>
				</logic:iterate>
			</select></td>
		</tr>
		<tr>
			<td class="regionfoot" >
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
						<th colspan="3">操作</th>
					</tr>
					<logic:iterate id="stOrgSpreadVo" name="stOrgSpreadForm"
									property="footer.dataArray" indexId="indexId">
						<tr>
							<td><bean:write name="stOrgSpreadVo" property="orgcode" /></td>
							<td><bean:write name="stOrgSpreadVo" property="orgname" /></td>
								<!-- 判断机构是否关闭 -->
								<logic:equal value="0" name="stOrgSpreadVo" property="isopen">
								<td width="15%"><input name="openo" type="button" style="width:30px" value="开" onclick="openOrg(this,'<bean:write name="stOrgSpreadVo" property="orgcode" />')" /></td>
									
								<td><input name="closeo" type="button" style="width:30px" disabled value="关 " onclick="closeOrg(this,'<bean:write name="stOrgSpreadVo" property="orgcode" />')" /></td>
								</logic:equal>
								<!-- 判断机构是否开启 -->
								<logic:equal value="1" name="stOrgSpreadVo" property="isopen">
									<td width="15%"><input name="openo" style="width:30px" type="button" disabled value="开" onclick="openOrg(this,'<bean:write name="stOrgSpreadVo" property="orgcode" />')" /></td>
									
									<td><input name="closeo" type="button"  style="width:30px"  value="关 " onclick="closeOrg(this,'<bean:write name="stOrgSpreadVo" property="orgcode" />')" /></td>
								</logic:equal>
							</td>
						</tr>
					</logic:iterate>
					<tr>
							<td style="text-align:center" colspan="4">
								<input type="button" style="width:50px" value="返回 " onclick="backlast()" />
							</td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
	</table>
</common:form>
<script type="text/javascript">

$(function(){
	if($("#orgcon").val() != null)
		$("#orgid").val($("#orgcon").val())
})
//查询 
function query(){
	if($("#orgid").val()=="-1" || $("#orgid").val() == ""){
		alert('请选择机构');
		return;
	}
	with(document.forms[0]){
		operAtt.value="queryNextDot";
		submit();
	}
}
//返回
function backlast(){
	window.location.href = $("#backUrl").val();
}
/**
 * 开启
 */
function openOrg(p,orgcode){
	var url = '<%=request.getContextPath()%>'+'/st/StOrgSpreadMaintain.do?operAtt=openDot';
	$.ajax({
		type:"post",
		url:url,
		data:{orgcode:orgcode},
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		success:function(data){
			alert(data.retu)
			if(data.retu="开启成功"){
				$(p).parent().parent().find("input[name='openo']").attr("disabled",true);
				$(p).parent().parent().find("input[name='closeo']").attr("disabled",false);
			}
		}
	})
}
/**
 * 关闭
 */
function closeOrg(p,orgcode){
	var ajaxUrl = '<%=request.getContextPath()%>'+'/st/StOrgSpreadMaintain.do?operAtt=closeDot';
	$.ajax({
		type:"post",
		url:ajaxUrl,
		data:{orgcode:orgcode},
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		success:function(data){
			alert(data.retu)
			if(data.retu="关闭成功"){
				$(p).parent().parent().find("input[name='closeo']").attr("disabled",true);
				$(p).parent().parent().find("input[name='openo']").attr("disabled",false);
			}
		}
	})
}
</script>
</BODY>
</html>
</html:html>