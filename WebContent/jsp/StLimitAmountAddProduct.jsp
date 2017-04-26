<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://www.cvicse.com/tags-common" prefix="common"%>
<%@ taglib uri="http://www.cvicse.com/tags-param" prefix="param"%>
<html:html>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GBK">
<META name="GENERATOR" content="IBM WebSphere Studio">
<script src="<%=request.getContextPath()%>/js/jquery-1.4.2.js"
	language="javascript">
	
</script>
<TITLE>额度计算维护产品增加</TITLE>
<common:theme />
</HEAD>
<script type="text/javascript">
var productPosition;
function popupNotExistProduct(p){
	var modelcode = $("input[name='modelcode']").val();
	var treeUrl = "<%=request.getContextPath()%>/st/StRuleMaintain.do?operAtt=queryNotExistProduct&modelcode=" + modelcode;
	var url = "<%=request.getContextPath()%>" + "/st/StRuleMaintain.do?operAtt=popupProduct&modelcode=" + modelcode + "&treeUrl=" + treeUrl;
	window.open(url, 'newwindow', 'width=800,height=600,scrollbars=yes,resizable=yes,left=300,top=100');
	productPosition = $(p).parent();
}
function popupExistProduct(p){
	var modelcode = $("input[name='modelcode']").val();
	var treeUrl = "<%=request.getContextPath()%>/st/StRuleMaintain.do?operAtt=queryExistProduct&modelcode=" + modelcode;
	var url = "<%=request.getContextPath()%>" + "/st/StRuleMaintain.do?operAtt=popupProduct&modelcode=" + modelcode + "&treeUrl=" + treeUrl;
	window.open(url, 'newwindow', 'width=800,height=600,scrollbars=yes,resizable=yes,left=300,top=100');
	productPosition = $(p).parent();
}
function callback(code,name){
	$(productPosition).find("#productName").val(name);
	$(productPosition).find("#productCode").val(code);
}
function commit(){
	$.ajax({
		type:"post",
		datatype:"json",
		data:$("#form").serialize(),
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		url:"<%=request.getContextPath()%>/st/StRuleMaintain.do?operAtt=limitAmountAddProduct",
		success:function(data){
			alert(data.result);
			window.location.reload();
		}
	})
}
</script>
<BODY>
<form id="form">
<h1 style="text-align:center">额度计算新产品增加</h1>
<input type="hidden" name="modelcode"
			value="<bean:write name='stSystemForm' property='stModelVo.modelcode'/>" />
	<table class="grid" style="text-align:center ">
		<tr>
			<td>新产品</td>
			<td>沿用旧产品</td>
		</tr>
		<tr>
			<td><input type='text' readonly id='productName' class='value'
				value="" name="strucVo.strucname"><img
				src='<%=request.getContextPath()%>/skins/images/search.gif'
				onclick='popupNotExistProduct(this)' /><input type='hidden'
				id='productCode' name='strucVo.relatecode'
				class='value' value=""></td>
			<td><input type='text' readonly id='productName' class='value'
				value=""><img
				src='<%=request.getContextPath()%>/skins/images/search.gif'
				onclick='popupExistProduct(this)' /><input type='hidden'
				id='productCode' name='strucVo.struccode'
				class='value' value=""></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center"><input type="button" value="提交" onClick="commit()"/></td>
		</tr>
	</table>
</form>
</BODY>
</html>
</html:html>