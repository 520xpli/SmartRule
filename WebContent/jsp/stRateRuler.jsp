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
<html:html>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GBK">
<META name="GENERATOR" content="IBM WebSphere Studio">
<script src="<%=request.getContextPath()%>/js/jquery-1.4.2.js"
	language="javascript">
</script>
<title>���������ά��</title>
<common:theme/>
</head>
<body>
	<common:form action="/StRateRulerMaintain.do">
	<common:hidden property="operAtt" />
	<table class="border" width="100%">
			<TR>
				<TD class="pagehead" >���������ά��</TD>
			</TR>
			<TR>
				<TD>
					<table  width="100%" class="grid" id="rateid">
						<tbody>
						<tr>
							<th width="20%">�����ȼ�</th>
							<th width="30%">��������</th>
							<th>��������</th>
						</tr>
						<logic:iterate id="stRateRulerVo" name="stRateRulerMaintainForm"
							property="stRateRulerVo.rateList" indexId="indexId">
							<tr>
								<td>
									<input type="hidden"  name="stRateRulerVo.rateList[<bean:write name="indexId"/>].ratecode" value="<bean:write name="stRateRulerVo" property="ratecode"/>" style="width:200px"/>
									<bean:write name="stRateRulerVo" property="ratecode"/></td>
								<td>
									<input type="text"  name="stRateRulerVo.rateList[<bean:write name="indexId"/>].ratedown" value="<bean:write name="stRateRulerVo" property="ratedown"/>" style="width:200px"/>
								</td>
								<td>
									<input type="text" onblur="rateco(this)"  name="stRateRulerVo.rateList[<bean:write name="indexId"/>].rateup" value="<bean:write name="stRateRulerVo" property="rateup"/>" style="width:200px"/>
								</td>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					<tr>
							<td colspan="3" style="text-align:center">
								<input type="button" value="����" onclick="commit()" />
							</td>
					</tr>
				</TD>
			</TR>
		</table>
	</common:form>
<script type="text/javascript">
$(function(){
	$("#rateid").find("tr").each(function(){
		$(this).find("input:visible:first").attr("disabled",true)
	})
	$("#rateid").find("tr:last").find("td:eq(2)").find("input").val("")
	$("#rateid").find("tr:last").find("td:eq(2)").find("input").attr("disabled",true)
})
//�ı����ȡ�����¼�
function rateco(p){
	var reg = /^[0-9]*$/;
	var curr = $(p).parent().parent().find("td:eq(2)").find("input").val();
	if(!reg.test(curr)){
		alert("�����ʽ��������������")
		return;
	}
	var nextVal = $(p).parent().parent().next().find("td:eq(2)").find("input").val();
	var lastVal = $(p).parent().parent().find("td:eq(1)").find("input").val();
	if(parseInt(curr) >= parseInt(nextVal) || parseInt(curr) <= parseInt(lastVal)){
		$(p).parent().parent().find("td:eq(2)").find("span").remove();
		$(p).parent().parent().find("td:eq(2)").append("<span style='color:red'>����������Ϲ���!</span>")
	}
	if(parseInt(curr) > parseInt(lastVal)){
		$(p).parent().parent().prev().find("td:eq(2)").find("span").remove();
	}
	if(parseInt(curr) > parseInt(lastVal) && parseInt(curr) < parseInt(nextVal)){
		$(p).parent().parent().find("td:eq(2)").find("span").remove();
	}
	$(p).parent().parent().next().find("input:visible:first").val($(p).val());
}
function commit(){
	var rateCheck = false;
	for(var i=1;i<=9;i++){
		var curr1 = $("#rateid").find("tr:eq("+i+")").find("td:eq(2)").find("input");
		var next1 = $("#rateid").find("tr:eq("+i+")").next().find("td:eq(1)").find("input");
		if(curr1.val() >= 10000){
			alert("����������󣬲�����Ҫ������������");
			curr1.val("");
			next1.val("");
			return;
		}
	}
	for(var i=1;i<9;i++){
		var curr = $("#rateid").find("tr:eq("+i+")").find("td:eq(2)").find("input").val();
		var reg = /^[0-9]*$/;
		if(!reg.test(curr)){
			alert("����������д�����޷����棡")
			return;
		}
		var lastVal = $("#rateid").find("tr:eq("+i+")").find("td:eq(1)").find("input").val();
		var nextVal = $("#rateid").find("tr:eq("+i+")").next().find("td:eq(2)").find("input").val();
		if(parseInt(curr) <= parseInt(lastVal) || parseInt(curr) >= parseInt(nextVal)){
			alert("����������д�����޷����棡")
			return;
		}
	}
	if(rateCheck)
		return;
	//����Ϊ�ɱ༭
	$("#rateid").find("tr").each(function(){
		$(this).find("input:visible:first").attr("disabled",false);
	})
	$("#rateid").find("tr:eq(1)").find("td:eq(1)").find("input").val("");
	var url = '<%=request.getContextPath()%>'+'/st/StRateRulerMaintain.do?operAtt=insert';
	$.ajax({
		type:"post",
		url:url,
		data:$("form:eq(0)").serialize(),
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		success:function(data){
			alert(data.result)
			window.location.reload();
		}
	})		
}
</script>
</body>
</html>
</html:html>