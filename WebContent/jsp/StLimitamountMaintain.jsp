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
<TITLE>��Ȳ���ά��</TITLE>
<common:theme />
</head>
<body>
	<common:form action="/StLimitAmountParaMaintain.do">
	<common:hidden property="operAtt" />
	<!-- ���л��� -->
	<common:hidden name="stLimitAmountForm" property="regionOrgCode" styleId="regionOrgId"/>
	<!--���˻�������  -->
	<common:hidden name="stLimitAmountForm" property="orgcode" styleId="orgcodeId"/>
	<!--��Ʒ����-->
	<common:hidden name="stLimitAmountForm" property="productcentercode" styleId="productcentercodeId"/>
	<!--��ɫ��Ʒ -->
	<common:hidden name="stLimitAmountForm" property="productcode" styleId="productcodeId"/>
	<!--�Ƿ��ѯ -->
	<common:hidden name="stLimitAmountForm" property="isquery" styleId="isqueryid"/>
	
	 <table width="100%">
	  	<TR>
				<TD class="pagehead" >��ȼ������ά��</TD>
		</TR>
		<tr>
			<td class="regionhead" colspan="4">��ѯ����</td>
		</tr>
		<tr>
			<td>
				<table class="free" width="100%">
					<tbody>
						<tr>
							<th>���л���</th>
							<td align="center">
								<select id="regionOrg" style="width:150px" onchange="chooseLegal(this)">
									<logic:equal value="1" name="stLimitAmountForm" property="orgnum">
										<option value="-1">-��ѡ��-</option>
										<logic:iterate id="StOrgSpreadVo" name="stLimitAmountForm"
													property="orgVoList" indexId="indexId">
											<option value="<bean:write name="StOrgSpreadVo" property="orgcode"></bean:write>"><bean:write name="StOrgSpreadVo" property="orgname"></bean:write></option>	
										</logic:iterate>
									</logic:equal>
									<logic:notEqual value="1" name="stLimitAmountForm" property="orgnum">
										<option value="-1">-��ѡ��-</option>
										<logic:iterate id="StOrgSpreadVo" name="stLimitAmountForm"
												property="orgVoList" indexId="indexId">
											<option value="<bean:write name="StOrgSpreadVo" property="orgcode"></bean:write>"><bean:write name="StOrgSpreadVo" property="orgname"></bean:write></option>
										</logic:iterate>
									</logic:notEqual>
								</select><font  color=red >*</font>
							</td>
							<th>���˻���</th>
							<td>
								<select name="orgcode" style="width:200px" disabled="disabled"  id="orgLegal" onchange="chooseProduct(this)">
								</select><font  color=red >*</font>
							</td>
						</tr>
						<tr>
							<th>��Ʒ����</th>
							<td align="center">
								<select name="productcentercode" style="width:150px" onchange="chooseProductcenter(this)">
									<option value="-1">-��ѡ��-</option>
									<logic:iterate id="StproductSpreadVo" name="stLimitAmountForm"
											property="productVoList" indexId="indexId">
										<option value="<bean:write name="StproductSpreadVo" property="productcode"></bean:write>"><bean:write name="StproductSpreadVo" property="productname" /></option>
									</logic:iterate>
								</select><font  color=red >*</font>
							</td>
							<th>��ɫ��Ʒ</th>
							<td>
								<select style="width:200px" disabled  name="productcode" id="productcode" onchange="productchange()">
								</select><font  color=red >*</font>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align:center">
								<input type="button"   value="��ѯ" 	onclick="openLimitAmount()"/>
							</td>
						</tr>
						</tbody>
				</table>
			</td>
		</tr>
		<tr id="termId" style="display:none">
			<td  colspan="4" width="100%" align="left" valign="top">
				<table align="left" width="100%" class="grid" id="showData" style="margin-top:20px">
					<tbody>
						<tr>
							<td class="regionhead" colspan="5">�������޲���</td>
						</tr>
						<tr>
							<th width="10%">��������</th>
							<th width="20%">��ͥ������ϵ��</th>
							<th width="20%">��ͥ������ϵ��</th>
							<th width="20%">��ͥ���ʲ�ϵ��</th>
							<th>�����ʽ�Ͷ��ϵ��</th>
						</tr>
						<logic:iterate id="StLimitamountParmVo" name="stLimitAmountForm"
									property="limitAmountVo.limitAmountVoList" indexId="index">
							<tr>
								<td>
									<input type="hidden" name="limitAmountVo.limitAmountVoList[<bean:write name="index"/>].termcode" value="<bean:write name="StLimitamountParmVo" property="termcode"/>" />
									<bean:write name="StLimitamountParmVo" property="termname"/>
								</td>
								<td>
									<input type="text" name="limitAmountVo.limitAmountVoList[<bean:write name="index"/>].rateRalatefamilynetassetratio" value="<bean:write name="StLimitamountParmVo" property="rateRalatefamilynetassetratio"/>" />
								</td>
								<td>
									<input type="text" name="limitAmountVo.limitAmountVoList[<bean:write   name="index"/>].familynetinratio" value="<bean:write name="StLimitamountParmVo" property="familynetinratio"/>"/>
								</td>
								<td>
									<input type="text" name="limitAmountVo.limitAmountVoList[<bean:write  name="index"/>].unRatefamilynetassetratio" value="<bean:write name="StLimitamountParmVo" property="unRatefamilynetassetratio"/>"/>
								</td>
								<td>
									<input type="text" name="limitAmountVo.limitAmountVoList[<bean:write  name="index"/>].collateralRatio" value="<bean:write name="StLimitamountParmVo" property="collateralRatio"/>"/>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</td>
		</tr>
		<tr id="gradeId" style="display:none">
			<td  colspan="4"  width="100%" align="left" valign="top">
				<table align="left" width="100%" class="grid" id="showData" style="margin-top:20px">
					<tbody>
						<tr>
							<td class="regionhead" colspan="5">�����ȼ�����</td>
						</tr>
						<tr>
							<th width="10%">�����ȼ�</th>
							<th width="20%">��ͥ������ϵ��</th>
							<th width="20%">��ͥ������ϵ��</th>
							<th width="20%">��ͥ���ʲ�ϵ��</th>
							<th>�����ʽ�Ͷ��ϵ��</th>
						</tr>
						<logic:iterate id="StLimitamountParmVo" name="stLimitAmountForm"
									property="limitAmountVoRate.limitAmountVoList" indexId="index">
							<tr>
								<td>
									<input type="hidden" name="limitAmountVoRate.limitAmountVoList[<bean:write  name="index"/>].gradecode" value="<bean:write name="StLimitamountParmVo" property="gradecode"/>" />
									<bean:write name="StLimitamountParmVo" property="gradecode"/>
								</td>
								<td>
									<input type="text" name="limitAmountVoRate.limitAmountVoList[<bean:write name="index"/>].rateRalatefamilynetassetratio" value="<bean:write name="StLimitamountParmVo" property="rateRalatefamilynetassetratio"/>" />
								</td>
								<td>
									<input type="text" name="limitAmountVoRate.limitAmountVoList[<bean:write   name="index"/>].familynetinratio" value="<bean:write name="StLimitamountParmVo" property="familynetinratio"/>"/>
								</td>
								<td>
									<input type="text" name="limitAmountVoRate.limitAmountVoList[<bean:write  name="index"/>].unRatefamilynetassetratio" value="<bean:write name="StLimitamountParmVo" property="unRatefamilynetassetratio"/>"/>
								</td>
								<td>
									<input type="text" name="limitAmountVoRate.limitAmountVoList[<bean:write  name="index"/>].collateralRatio" value="<bean:write name="StLimitamountParmVo" property="collateralRatio"/>"/>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td id="button" class="regionfoot" style="display:none">
				<input type="button"   value="����" 	onclick="save()"/>&nbsp;&nbsp;
				<input type="button" 	value="����" onclick="reback()"/>
			</td>
		</tr>
	</table>
	</common:form>
<script type="text/javascript">
	$(function(){
		var regionOrgCode = $("#regionOrgId").val();
		//Ĭ��ѡ�е��л���
		if($("#isqueryid").val()){
			$("#button").show();
			$("#termId").show();
			$("#gradeId").show();
			$("#regionOrg").attr("disabled",true)
			$("#regionOrg").find("option[value='"+regionOrgCode+"']").attr("selected",true);
			chooseSpLegal(regionOrgCode);
		}
	})
	/* ��Ʒѡ��󷵻غ����ѡ���˻��� */
	function chooseSpLegal(regionOrgCode){
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=queryCorpOrg';
		$.ajax({
			type:"post",
			url:url,
			data:{orgcode:regionOrgCode},
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				//�Ƴ�option
				$("#orgLegal").children().remove()
				var obj = data.datas;
				if(obj.length>0){
					$("#orgLegal").attr("disabled",true);
				}
				$("#orgLegal").append("<option value='0'>-��ѡ��-</option>")
				for(var i=0,j=obj.length;i<j;i++){
					$("#orgLegal").append("<option value='"+obj[i].orgcode+"'>"+obj[i].orgname+"</option>")
				}
				//��ȡ���غ��˻�����ֵ
				var orgcode = $("#orgcodeId").val();
				//��ȡ���غ��Ʒ�����ֵ
				var productcentercode = $("#productcentercodeId").val();
				//Ĭ��ѡ�з��˻���
				if(orgcode != null && orgcode != ""){
					$("#orgLegal").find("option[value='"+orgcode+"']").attr("selected",true);
					//Ĭ��ѡ�в�Ʒ����
					if(productcentercode != null &&��productcentercode��!= ""){
						$("select[name='productcentercode']").find("option[value='"+productcentercode+"']").attr("selected",true);
						chooseSpProduct(orgcode,productcentercode)
					}
				}
			}
		})	
	} 
	/* ��Ʒѡ��󷵻ص��ò�Ʒ��ѯ���� */
	function chooseSpProduct(orgcode,productcode){
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=queryProduct';
		$.ajax({
			type:"post",
			url:url,
			data:{orgcode:orgcode,productcode:productcode},
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				//�Ƴ�option
				var option = $("#productcode").children().remove();
				var obj = data.datas;
				if(obj.length>0){
					$("#productcode").attr("disabled",false);
				}
				$("#productcode").append("<option value='0'>-��ѡ��-</option>")
				for(var i=0,j=obj.length;i<j;i++){
					$("#productcode").append("<option value='"+obj[i].productcode+"'>"+obj[i].productname+"</option>")
				}
				var productcode = $("#productcodeId").val();
				//Ĭ��ѡ����ɫ��Ʒ
				if(productcode != null && productcode != ""){
					$("#productcode").find("option[value='"+productcode+"']").attr("selected",true);
				}
			}
		})	
	} 
	//����
	function save(){
		var procode = $("select[name='productcode']").val();
		if(procode == null | procode == "0"){
			alert("��ѡ���Ʒ");
			return;
		}
		 var reg = /^\d+(\.\d+)?$/;
		 var check = false;
		//��֤���޲�������
		$("input:visible[type='text']").each(function(){
			if(!reg.test($(this).val())){
				alert("�����ʽ����,����������");
				$(this).focus();
				check = true;
				return;
			}
		})
		if(check)
			return;
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=insert';
		$.ajax({
			type:"post",
			url:url,
			data:$("form:first").serialize(),
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				$("#isqueryid").val("")
				alert(data.result)
			}
		})		
	}
	/* ѡ���˻��� */
	function chooseLegal(data){
		if(data.value=="-1"){
			$("#termId").hide();
			$("#gradeId").hide();
			$("#orgLegal").attr("disabled",true);
			$("#productcode").children().remove();
			$("#productcode").attr("disabled",true);
		}
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=queryCorpOrg';
		$.ajax({
			type:"post",
			url:url,
			data:{orgcode:data.value},
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				//�Ƴ�option
				$("#orgLegal").children().remove()
				var obj = data.datas;
				if(obj.length>0){
					$("#orgLegal").attr("disabled",false);
				}
				$("#orgLegal").append("<option value='0'>-��ѡ��-</option>")
				for(var i=0,j=obj.length;i<j;i++){
					$("#orgLegal").append("<option value='"+obj[i].orgcode+"'>"+obj[i].orgname+"</option>")
				}
			}
		})	
	} 
	/* ���˻���ѡ���Ʒ */
	function chooseProduct(data){
		var procenter = $("select[name='productcentercode']").val();
		if($("select[name='productcentercode']").val()=="-1"){
			return;
		}
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=queryProduct';
		$.ajax({
			type:"post",
			url:url,
			data:{orgcode:data.value,productcode:procenter},
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				//�Ƴ�option
				var option = $("#productcode").children().remove();
				var obj = data.datas;
				if(obj.length>0){
					$("#productcode").attr("disabled",false);
				}
				$("#productcode").append("<option value='0'>-��ѡ��-</option>")
				for(var i=0,j=obj.length;i<j;i++){
					$("#productcode").append("<option value='"+obj[i].productcode+"'>"+obj[i].productname+"</option>")
				}
			}
		})	
	} 
	/* ��Ʒ����ѡ���Ʒ */
	function chooseProductcenter(data){
		if($("#isqueryid").val()){
			var choose = confirm("�����Ѹ��ģ��Ƿ񱣴�?")
				$("#button").hide();
				$("#termId").hide();
				$("#gradeId").hide();
				$("#isqueryid").val("")
			if(choose){
				save();
			}
		}
		var orgcode = $("#orgLegal").val();
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=queryProduct';
		$.ajax({
			type:"post",
			url:url,
			data:{productcode:data.value,orgcode:orgcode},
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				//�Ƴ�option
				var option = $("#productcode").children().remove()
				var obj = data.datas;
				if(obj.length>0){
					$("#productcode").attr("disabled",false);
				}
				$("#productcode").append("<option value='0'>-��ѡ��-</option>")
				for(var i=0,j=obj.length;i<j;i++){
					$("#productcode").append("<option value='"+obj[i].productcode+"'>"+obj[i].productname+"</option>")
				}
			}
		})	
	} 
	/* ��ɫ��Ʒ�ı� */
	function productchange(){
		if($("#isqueryid").val()){
			var choose = confirm("�����Ѹ��ģ��Ƿ񱣴�?")
				$("#button").hide();
				$("#termId").hide();
				$("#gradeId").hide();
				$("#isqueryid").val("");
			if(choose){
				save();
			}
		}
	}
	/* ��ѯ */
	function openLimitAmount(){
		var productcode = $("#productcode").val();
		var regionOrgCode = $("#regionOrg").val();
		var orgcode = $("#orgLegal").val();
		var productcentercode = $("select[name='productcentercode']").val();
		if(regionOrgCode =="-1" | null == regionOrgCode){
			alert("��ѡ����л���");
			return;
		}
		if(orgcode =="0" | null == orgcode){
			alert("��ѡ���˻���");
			return;
		}
		if(productcentercode =="-1" | null == productcentercode){
			alert("��ѡ���Ʒ����");
			return;
		}
		if(productcode =="0" | null == productcode){
			alert("��ѡ����ɫ��Ʒ");
			return;
		}
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=query&productcentercode='+productcentercode+'&orgcode='+orgcode+'&regionOrgCode='+regionOrgCode+'&productcode='+productcode+'&isquery=true';
		window.location.href=url;
	}
	//����
	function reback(){
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=jump';
		window.location.href=url;
	}
</script>
</body>
</html>
</html:html>