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
<TITLE>��ȼ���ά��</TITLE>
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
			<td>
				<%-- <table width="100%" style="margin-top:5px">
					<TR>
						<TD class="regionhead" style="text-align:center">��Ʒ���ά��</TD>
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
											<select style="width:200px" disabled  name="productcode" id="productcode">
											</select><font  color=red >*</font>
										</td>
									</tr>
									<tr>
										<td colspan="4" style="text-align:center;height:25px">
											<input type="button"   value="��ѯ" 	onclick="openLimitAmount()"/>
			 								<input type="button"   value="����" 	onclick="reset()"/>
			 							</td>
									</tr>
									</tbody>
							</table>
						</td>
					</tr>
					<tr id="proId" style="display:none">
						<td  colspan="4" width="100%" align="left" valign="top">
							<table align="left" width="100%" class="grid" id="showData" style="margin-top:20px">
								<tbody>
									<tr>
										<th width="25%">����</th>
										<th width="25%">��ɫ��Ʒ</th>
										<th width="25%">���</th>
										<th>����</th>
									</tr>
									<logic:iterate id="stProductSpreadVo" name="stLimitAmountForm"
												property="proList" indexId="index">
										<tr>
											<td>
												<bean:write name="stProductSpreadVo" property="orgName"/>
											</td>
											<td>
												<bean:write name="stProductSpreadVo" property="productname"/>
											</td>
											<td>
												<input type="text"   value="<bean:write name="stProductSpreadVo" property="limitamount"/>"/>
											</td>
											<td>
												<input type="button"  value="����" onclick="saveLimit('<bean:write name="stProductSpreadVo" property="productname"/>','<bean:write name="stProductSpreadVo" property="productcode"/>','<bean:write name="stProductSpreadVo" property="productcentercode"/>',
												'<bean:write name="stProductSpreadVo" property="orgcode"/>',this,'<bean:write name="stProductSpreadVo" property="id"/>')"/>
											</td>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
						</td>
					</tr>
				</table> --%>
			</td>
		</tr>
		<tr>
			<td>
				<table  width="100%" style="margin-top:20px">
					<tr>
						<td>
							<table align="left" width="100%" class="grid" id="showData" >
								<tbody>
									<tr>
										<th style="width:10%">
											�����޹ؾ��ʲ�ϵ��
										</th>
										<logic:iterate id="StLimitamountParmVo" name="stLimitAmountForm"
											property="limitAmountVo.limitAmountVoList" indexId="index">
											<td>
												<input type="text" style="width:100px" name="limitAmountVo.limitAmountVoList[<bean:write  name="index"/>].unratefamilynetassetratio" value="<bean:write name="StLimitamountParmVo" property="unratefamilynetassetratio"/>"/>
											</td>
										</logic:iterate>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
					<tr id="collAterId" >
						<td  colspan="4"  width="100%" align="left" valign="top" >
							<table align="left" width="100%" class="grid" id="showData" style="margin-top:20px">
								<tbody>
									<tr>
										<td class="regionhead" colspan="11">����Ѻ����</td>
									</tr>
									<tr>
										<th>
											����ϵ��
										</th>
										<logic:iterate id="StLimitamountParmVo" name="stLimitAmountForm"
											property="limitAmountVoCollExpand.limitAmountVoList" indexId="index">
											<td>
												<input type="text" style="width:100px" name="limitAmountVoCollExpand.limitAmountVoList[<bean:write  name="index"/>].expandratio" value="<bean:write name="StLimitamountParmVo" property="expandratio"/>"/>
											</td> 
										</logic:iterate>
									</tr>
									<tr>
										<th></th>
										<logic:iterate id="StLimitamountParmVo" name="stLimitAmountForm"
											property="limitAmountVoColl.limitAmountVoList" indexId="index">
											<th style="width:10%">
												<input type="hidden" name="limitAmountVoColl.limitAmountVoList[<bean:write  name="index"/>].gradecode" value="<bean:write name="StLimitamountParmVo" property="gradecode"/>">
												<bean:write name="StLimitamountParmVo" property="gradecode"/>
											</th>
										</logic:iterate>
									</tr>
									<tr>
										<th>
											����Ѻϵ��
										</th>
										<logic:iterate id="StLimitamountParmVo" name="stLimitAmountForm"
											property="limitAmountVoColl.limitAmountVoList" indexId="index">
											<td>
												<input type="text" style="width:50px" name="limitAmountVoColl.limitAmountVoList[<bean:write  name="index"/>].collateralratio" value="<bean:write name="StLimitamountParmVo" property="collateralratio"/>"/>
											</td>
										</logic:iterate>
									</tr>		
								</tbody>
							</table>
						</td>
					</tr>
					<tr id="guaranteeId" >
						<td  width="100%" align="left" valign="top">
							<table align="left" width="100%" class="grid" id="showData" style="margin-top:20px">
								<tbody>
									<tr>
										<td class="regionhead" colspan="11">��֤�����</td>
									</tr>
									<tr>
										<th>
											����ϵ��
										</th>
										<logic:iterate id="StLimitamountParmVo" name="stLimitAmountForm"
											property="limitAmountVoGuaExpand.limitAmountVoList" indexId="index">
											<td>
												<input type="text" style="width:100px" name="limitAmountVoGuaExpand.limitAmountVoList[<bean:write  name="index"/>].expandratio" value="<bean:write name="StLimitamountParmVo" property="expandratio"/>"/>
											</td> 
										</logic:iterate>
									</tr>
									<tr>
										<th></th>
										<logic:iterate id="StLimitamountParmVo" name="stLimitAmountForm"
											property="limitAmountVoGua.limitAmountVoList" indexId="index">
											<th style="width:10%">
												<input type="hidden" name="limitAmountVoGua.limitAmountVoList[<bean:write  name="index"/>].gradecode" value="<bean:write name="StLimitamountParmVo" property="gradecode"/>">
												<bean:write name="StLimitamountParmVo" property="gradecode"/>
											</th>
										</logic:iterate>
									</tr>
									<tr>
											<th>
												������ϵ��
											</th>
											<logic:iterate id="StLimitamountParmVo" name="stLimitAmountForm"
											property="limitAmountVoGua.limitAmountVoList" indexId="index">
												<td>
													<input type="text" style="width:50px" name="limitAmountVoGua.limitAmountVoList[<bean:write  name="index"/>].familynetinratio" value="<bean:write name="StLimitamountParmVo" property="familynetinratio"/>"/>
												</td>
											</logic:iterate>
										</tr>	
										<tr>
											<th>
												���ʲ�ϵ��
											</th>
											<logic:iterate id="StLimitamountParmVo" name="stLimitAmountForm"
											property="limitAmountVoGua.limitAmountVoList" indexId="index">
												<td>
													<input type="text" style="width:50px" name="limitAmountVoGua.limitAmountVoList[<bean:write  name="index"/>].rateralatefamilynetassetratio" value="<bean:write name="StLimitamountParmVo" property="rateralatefamilynetassetratio"/>"/>
												</td>
											</logic:iterate>
										</tr>	
								</tbody>
							</table>
						</td>
					</tr>
					
					<tr id="creditId">
						<td  colspan="4"  width="100%" align="left" valign="top">
							<table align="left" width="100%" class="grid" id="showData" style="margin-top:20px">
								<tbody>
									<tr>
										<td class="regionhead" colspan="11">���������</td>
									</tr>
									<tr>
										<th>
											����ϵ��
										</th>
										<logic:iterate id="StLimitamountParmVo" name="stLimitAmountForm"
											property="limitAmountVoRateExpand.limitAmountVoList" indexId="index">
											<td>
												<input type="text" style="width:100px" name="limitAmountVoRateExpand.limitAmountVoList[<bean:write  name="index"/>].expandratio" value="<bean:write name="StLimitamountParmVo" property="expandratio"/>"/>
											</td> 
										</logic:iterate>
									</tr>
									<tr>
										<th></th>
										<logic:iterate id="StLimitamountParmVo" name="stLimitAmountForm"
											property="limitAmountVoRate.limitAmountVoList" indexId="index">
											<th style="width:10%">
												<input type="hidden" name="limitAmountVoRate.limitAmountVoList[<bean:write  name="index"/>].gradecode" value="<bean:write name="StLimitamountParmVo" property="gradecode"/>">
												<bean:write name="StLimitamountParmVo" property="gradecode"/>
											</th>
										</logic:iterate>
									</tr>
									<tr>
										<th>
											������ϵ��
										</th>
										<logic:iterate id="StLimitamountParmVo" name="stLimitAmountForm"
										property="limitAmountVoRate.limitAmountVoList" indexId="index">
											<td>
												<input type="text" style="width:50px" name="limitAmountVoRate.limitAmountVoList[<bean:write  name="index"/>].familynetinratio" value="<bean:write name="StLimitamountParmVo" property="familynetinratio"/>"/>
											</td>
										</logic:iterate>
									</tr>		
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align:center;height:25px">
							<input type="button" id="button"  value="����" 	onclick="saveratio()"/>
						 </td>
					</tr>
				<table>
			</td>
		</tr>
	</table>
	</common:form>
<script type="text/javascript">
	$(function(){
		var regionOrgCode = $("#regionOrgId").val();
		//Ĭ��ѡ�е��л���
		if($("#isqueryid").val()){
			$("#proId").show();
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
					$("#orgLegal").attr("disabled",false);
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
					if(productcentercode != null && productcentercode != ""){
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
	//��Ʒ��ȱ���
	function saveLimit(productname,productcode,productcentercode,orgcode,p,id){
		var limit = $(p).parent().prev().find("input").val();
		var reg = /^\d+(\.\d+)?$/;
		if(!reg.test(limit)){
			alert("�����ʽ�������������룡");
			return;
		}
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=insertProduct&proVo.limitamount='+limit+'&proVo.id='+id+'&proVo.productname='+productname+'&proVo.productcode='+productcode+'&proVo.productcentercode='+productcentercode+'&proVo.orgcode='+orgcode+'&isquery=true';
		$.ajax({
			type:"post",
			url:url,
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				alert(data.result)
			}
		})	
	}
	//����
	function saveratio(){
		$("#button").attr("disabled",true);
		 var reg = /^\d+(\.\d+)?$/;
		 var check = false;
		//��֤���޲�������
		$("input:visible[type='text']").each(function(){
			if(!reg.test($(this).val())){
				alert("�����ʽ����,����������");
				$(this).focus();
				check = true;
			}
				return;
		})
		if(check){
			$("#button").attr("disabled",false);
			return;
		}
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=insertRateRatio';
		$.ajax({
			type:"post",
			url:url,
			data:$("form:first").serialize(),
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				$("#isqueryid").val("")
				alert(data.result)
				$("#button").attr("disabled",false);
				if(data.result=="����ɹ�")
					window.location.reload();
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
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=queryLoanRadio&productcentercode='+productcentercode+'&orgcode='+orgcode+'&regionOrgCode='+regionOrgCode+'&productcode='+productcode+'&isquery=true';
		window.location.href=url;
	}
	//����
	function reback(){
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=limitJump';
		window.location.href=url;
	}
</script>
</body>
</html>
</html:html>