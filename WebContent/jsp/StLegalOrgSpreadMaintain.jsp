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
<TITLE>�����ƹ����ά��</TITLE>
<common:theme />
</HEAD>
<BODY onload="myfunction()">
	<common:form action="/StOrgSpreadMaintain.do" method="get">
	<common:hidden property="operAtt" />	
	<common:hidden name="stOrgSpreadForm" property="stOrgSpreadVo.orgcode" styleId="orgcodeh"/>
	  <table width="100%">
	  	<TR>
				<TD class="pagehead" >�ƹ����ά�����أ�</TD>
		</TR>
		<tr>
			<td class="regionhead" colspan="4">��ѯ����</td>
		</tr>
		<tr class="regioncomposite">
			<td>���л���     &nbsp;&nbsp;&nbsp;&nbsp;
			<select name="orgcode" style="width:150px">
				<logic:equal value="1" name="stOrgSpreadForm" property="orgNum">
					<logic:iterate id="StOrgSpreadVo" name="stOrgSpreadForm"
								property="stOrgList" indexId="indexId">
						<option value="<bean:write name="StOrgSpreadVo" property="orgcode"></bean:write>"><bean:write name="StOrgSpreadVo" property="orgname"></bean:write></option>	
					</logic:iterate>
				</logic:equal>
				<logic:notEqual value="1" name="stOrgSpreadForm" property="orgNum">
					<option value="-1">-��ѡ��-</option>
					<logic:iterate id="StOrgSpreadVo" name="stOrgSpreadForm"
							property="stOrgList" indexId="indexId">
						<option value="<bean:write name="StOrgSpreadVo" property="orgcode"></bean:write>"><bean:write name="StOrgSpreadVo" property="orgname"></bean:write></option>
					</logic:iterate>
				</logic:notEqual>
			</select><font  color=red >*</font></td>
		</tr>
		<tr>
			<td class="regionfoot">
				<input type="button"   value="��ѯ" 	onclick="query()"/>&nbsp;&nbsp;
				<input type="button" 	value="����" onclick="reset()"/>&nbsp;&nbsp;
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
						<th width="20%">�������</th>
						<th width="40%">��������</th>
						<th colspan="4">����</th>
					</tr>
					<logic:iterate id="stOrgSpreadVo" name="stOrgSpreadForm"
									property="footer.dataArray" indexId="indexId">
						<tr>
							<td><bean:write name="stOrgSpreadVo" property="orgcode"></bean:write></td>
							<td><bean:write name="stOrgSpreadVo" property="orgname"></bean:write></td>
							
								<td width="10%">
								<input type="hidden" id="isopenId" name="isopen" value="<bean:write name="stOrgSpreadVo" property="isopen" />">
								<input id="queryid" name="queryorg" type="button" value="��Ʒ����" iconClass="queryIcon"
								 onclick="queryPro('<bean:write name="stOrgSpreadVo" property="orgcode" />',
								 '<bean:write name="stOrgSpreadVo" property="orgname" />')"/></td>
								 <td width="10%">
								 	<input id="dotid" name="querydot" type="button" value="��������" iconClass="queryIcon"
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
//��ѯ���˻�����ת
function query(){
	if($("select[name='orgcode']").val()=="-1" || $("select[name='orgcode']").val() == ""){
		alert('��ѡ�����');
		return;
	}
	with(document.forms[0]){
		operAtt.value="querylegal";
		submit();
	}
}
//����������ת
function queryDot(orgcode){
	var backUrl = window.location.href;
	backUrl = encodeURIComponent(backUrl);
	var url = '<%=request.getContextPath()%>'+'/st/StOrgSpreadMaintain.do?operAtt=queryDot&orgcode='+orgcode+'&backUrl='+backUrl;
	window.location.href=url;
}
/*
 * ��Ʒ������ת
 */
function queryPro(orgcode,orgname){
	var backUrl = window.location.href;
	backUrl = encodeURIComponent(backUrl);
	var url = '<%=request.getContextPath()%>'+'/st/StProductSpreadMaintain.do?operAtt=queryProductBigByOrg&orgcode='+orgcode+'&orgname='+orgname+'&backUrl='+backUrl;
	window.location.href=url;
}
/**
 * ����
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
					if(data.retu="�����ɹ�"){
						$(p).parent().parent().find("input[name='openo']").attr("disabled",true);
						$(p).parent().parent().find("input[name='closeo']").attr("disabled",false);
						$(p).parent().parent().find("input[name='queryorg']").attr("disabled",false);
						$(p).parent().parent().find("input[name='querydot']").attr("disabled",false);
					}
				}
			})
}
/**
 * �ر�
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
			if(data.retu="�رճɹ�"){
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