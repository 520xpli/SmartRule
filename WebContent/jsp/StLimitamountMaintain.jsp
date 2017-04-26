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
<TITLE>额度参数维护</TITLE>
<common:theme />
</head>
<body>
	<common:form action="/StLimitAmountParaMaintain.do">
	<common:hidden property="operAtt" />
	<!-- 地市机构 -->
	<common:hidden name="stLimitAmountForm" property="regionOrgCode" styleId="regionOrgId"/>
	<!--法人机构机构  -->
	<common:hidden name="stLimitAmountForm" property="orgcode" styleId="orgcodeId"/>
	<!--产品中类-->
	<common:hidden name="stLimitAmountForm" property="productcentercode" styleId="productcentercodeId"/>
	<!--特色产品 -->
	<common:hidden name="stLimitAmountForm" property="productcode" styleId="productcodeId"/>
	<!--是否查询 -->
	<common:hidden name="stLimitAmountForm" property="isquery" styleId="isqueryid"/>
	
	 <table width="100%">
	  	<TR>
				<TD class="pagehead" >额度计算参数维护</TD>
		</TR>
		<tr>
			<td class="regionhead" colspan="4">查询条件</td>
		</tr>
		<tr>
			<td>
				<table class="free" width="100%">
					<tbody>
						<tr>
							<th>地市机构</th>
							<td align="center">
								<select id="regionOrg" style="width:150px" onchange="chooseLegal(this)">
									<logic:equal value="1" name="stLimitAmountForm" property="orgnum">
										<option value="-1">-请选择-</option>
										<logic:iterate id="StOrgSpreadVo" name="stLimitAmountForm"
													property="orgVoList" indexId="indexId">
											<option value="<bean:write name="StOrgSpreadVo" property="orgcode"></bean:write>"><bean:write name="StOrgSpreadVo" property="orgname"></bean:write></option>	
										</logic:iterate>
									</logic:equal>
									<logic:notEqual value="1" name="stLimitAmountForm" property="orgnum">
										<option value="-1">-请选择-</option>
										<logic:iterate id="StOrgSpreadVo" name="stLimitAmountForm"
												property="orgVoList" indexId="indexId">
											<option value="<bean:write name="StOrgSpreadVo" property="orgcode"></bean:write>"><bean:write name="StOrgSpreadVo" property="orgname"></bean:write></option>
										</logic:iterate>
									</logic:notEqual>
								</select><font  color=red >*</font>
							</td>
							<th>法人机构</th>
							<td>
								<select name="orgcode" style="width:200px" disabled="disabled"  id="orgLegal" onchange="chooseProduct(this)">
								</select><font  color=red >*</font>
							</td>
						</tr>
						<tr>
							<th>产品中类</th>
							<td align="center">
								<select name="productcentercode" style="width:150px" onchange="chooseProductcenter(this)">
									<option value="-1">-请选择-</option>
									<logic:iterate id="StproductSpreadVo" name="stLimitAmountForm"
											property="productVoList" indexId="indexId">
										<option value="<bean:write name="StproductSpreadVo" property="productcode"></bean:write>"><bean:write name="StproductSpreadVo" property="productname" /></option>
									</logic:iterate>
								</select><font  color=red >*</font>
							</td>
							<th>特色产品</th>
							<td>
								<select style="width:200px" disabled  name="productcode" id="productcode" onchange="productchange()">
								</select><font  color=red >*</font>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align:center">
								<input type="button"   value="查询" 	onclick="openLimitAmount()"/>
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
							<td class="regionhead" colspan="5">授信期限参数</td>
						</tr>
						<tr>
							<th width="10%">授信期限</th>
							<th width="20%">家庭年收入系数</th>
							<th width="20%">家庭净收入系数</th>
							<th width="20%">家庭净资产系数</th>
							<th>自有资金投入系数</th>
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
							<td class="regionhead" colspan="5">评级等级参数</td>
						</tr>
						<tr>
							<th width="10%">评级等级</th>
							<th width="20%">家庭年收入系数</th>
							<th width="20%">家庭净收入系数</th>
							<th width="20%">家庭净资产系数</th>
							<th>自有资金投入系数</th>
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
				<input type="button"   value="保存" 	onclick="save()"/>&nbsp;&nbsp;
				<input type="button" 	value="返回" onclick="reback()"/>
			</td>
		</tr>
	</table>
	</common:form>
<script type="text/javascript">
	$(function(){
		var regionOrgCode = $("#regionOrgId").val();
		//默认选中地市机构
		if($("#isqueryid").val()){
			$("#button").show();
			$("#termId").show();
			$("#gradeId").show();
			$("#regionOrg").attr("disabled",true)
			$("#regionOrg").find("option[value='"+regionOrgCode+"']").attr("selected",true);
			chooseSpLegal(regionOrgCode);
		}
	})
	/* 产品选择后返回后调用选择法人机构 */
	function chooseSpLegal(regionOrgCode){
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=queryCorpOrg';
		$.ajax({
			type:"post",
			url:url,
			data:{orgcode:regionOrgCode},
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				//移除option
				$("#orgLegal").children().remove()
				var obj = data.datas;
				if(obj.length>0){
					$("#orgLegal").attr("disabled",true);
				}
				$("#orgLegal").append("<option value='0'>-请选择-</option>")
				for(var i=0,j=obj.length;i<j;i++){
					$("#orgLegal").append("<option value='"+obj[i].orgcode+"'>"+obj[i].orgname+"</option>")
				}
				//获取返回后法人机构的值
				var orgcode = $("#orgcodeId").val();
				//获取返回后产品中类的值
				var productcentercode = $("#productcentercodeId").val();
				//默认选中法人机构
				if(orgcode != null && orgcode != ""){
					$("#orgLegal").find("option[value='"+orgcode+"']").attr("selected",true);
					//默认选中产品中类
					if(productcentercode != null &&　productcentercode　!= ""){
						$("select[name='productcentercode']").find("option[value='"+productcentercode+"']").attr("selected",true);
						chooseSpProduct(orgcode,productcentercode)
					}
				}
			}
		})	
	} 
	/* 产品选择后返回调用产品查询方法 */
	function chooseSpProduct(orgcode,productcode){
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=queryProduct';
		$.ajax({
			type:"post",
			url:url,
			data:{orgcode:orgcode,productcode:productcode},
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				//移除option
				var option = $("#productcode").children().remove();
				var obj = data.datas;
				if(obj.length>0){
					$("#productcode").attr("disabled",false);
				}
				$("#productcode").append("<option value='0'>-请选择-</option>")
				for(var i=0,j=obj.length;i<j;i++){
					$("#productcode").append("<option value='"+obj[i].productcode+"'>"+obj[i].productname+"</option>")
				}
				var productcode = $("#productcodeId").val();
				//默认选中特色产品
				if(productcode != null && productcode != ""){
					$("#productcode").find("option[value='"+productcode+"']").attr("selected",true);
				}
			}
		})	
	} 
	//保存
	function save(){
		var procode = $("select[name='productcode']").val();
		if(procode == null | procode == "0"){
			alert("请选择产品");
			return;
		}
		 var reg = /^\d+(\.\d+)?$/;
		 var check = false;
		//验证期限参数输入
		$("input:visible[type='text']").each(function(){
			if(!reg.test($(this).val())){
				alert("输入格式不对,请重新输入");
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
	/* 选择法人机构 */
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
				//移除option
				$("#orgLegal").children().remove()
				var obj = data.datas;
				if(obj.length>0){
					$("#orgLegal").attr("disabled",false);
				}
				$("#orgLegal").append("<option value='0'>-请选择-</option>")
				for(var i=0,j=obj.length;i<j;i++){
					$("#orgLegal").append("<option value='"+obj[i].orgcode+"'>"+obj[i].orgname+"</option>")
				}
			}
		})	
	} 
	/* 法人机构选择产品 */
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
				//移除option
				var option = $("#productcode").children().remove();
				var obj = data.datas;
				if(obj.length>0){
					$("#productcode").attr("disabled",false);
				}
				$("#productcode").append("<option value='0'>-请选择-</option>")
				for(var i=0,j=obj.length;i<j;i++){
					$("#productcode").append("<option value='"+obj[i].productcode+"'>"+obj[i].productname+"</option>")
				}
			}
		})	
	} 
	/* 产品中类选择产品 */
	function chooseProductcenter(data){
		if($("#isqueryid").val()){
			var choose = confirm("数据已更改，是否保存?")
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
				//移除option
				var option = $("#productcode").children().remove()
				var obj = data.datas;
				if(obj.length>0){
					$("#productcode").attr("disabled",false);
				}
				$("#productcode").append("<option value='0'>-请选择-</option>")
				for(var i=0,j=obj.length;i<j;i++){
					$("#productcode").append("<option value='"+obj[i].productcode+"'>"+obj[i].productname+"</option>")
				}
			}
		})	
	} 
	/* 特色产品改变 */
	function productchange(){
		if($("#isqueryid").val()){
			var choose = confirm("数据已更改，是否保存?")
				$("#button").hide();
				$("#termId").hide();
				$("#gradeId").hide();
				$("#isqueryid").val("");
			if(choose){
				save();
			}
		}
	}
	/* 查询 */
	function openLimitAmount(){
		var productcode = $("#productcode").val();
		var regionOrgCode = $("#regionOrg").val();
		var orgcode = $("#orgLegal").val();
		var productcentercode = $("select[name='productcentercode']").val();
		if(regionOrgCode =="-1" | null == regionOrgCode){
			alert("请选择地市机构");
			return;
		}
		if(orgcode =="0" | null == orgcode){
			alert("请选择法人机构");
			return;
		}
		if(productcentercode =="-1" | null == productcentercode){
			alert("请选择产品中类");
			return;
		}
		if(productcode =="0" | null == productcode){
			alert("请选择特色产品");
			return;
		}
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=query&productcentercode='+productcentercode+'&orgcode='+orgcode+'&regionOrgCode='+regionOrgCode+'&productcode='+productcode+'&isquery=true';
		window.location.href=url;
	}
	//返回
	function reback(){
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=jump';
		window.location.href=url;
	}
</script>
</body>
</html>
</html:html>