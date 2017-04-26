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
<TITLE>额度计算维护</TITLE>
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
				<TD class="pagehead" >产品最高限额维护</TD>
		</TR>
		<tr>
			<td>
				<table width="100%" style="margin-top:5px">
					<TR>
						<TD class="regionhead" style="text-align:center">产品最高限额维护</TD>
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
										<td colspan="4" style="text-align:center;height:25px">
											<input type="button"   value="查询" 	onclick="openLimitAmount()"/>
			 								<input type="button"   value="重置" 	onclick="reset()"/>
			 							</td>
									</tr>
									</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<table width="100%" class="grid">
						<TR>
							<TD class="regionhead" style="text-align:center" colspan="2">产品中类</TD>
							<input type="hidden" id="middleCode" value="<bean:write name="stLimitAmountForm" property="productcentercode"></bean:write>" />
						</TR>
					<logic:iterate id="stProductSpreadVo" name="stLimitAmountForm"
											property="footer.dataArray" indexId="indexId">
											<tr>
												<td width="5%"><input type="radio" id="productcodeId" value="<bean:write name="stProductSpreadVo" property="productcode"></bean:write>"
													onclick="selectR('<bean:write name="stProductSpreadVo" property="productcode"></bean:write>')"
													name="productcode"></td>
												<td><bean:write name="stProductSpreadVo"
														property="productname"></bean:write></td>

											</tr>
										</logic:iterate>
						</table>
					</tr>
					<tr id="proId" style="display:none">
						<td  colspan="4" width="100%" align="left" valign="top">
							<table align="left" width="100%" class="grid" id="showData" style="margin-top:20px">
								<tbody>
									<tr>
										<th width="25%">机构</th>
										<th width="25%">特色产品</th>
										<th width="25%">额度</th>
									</tr>
									<logic:iterate id="stProductHighestAmountVo" name="stLimitAmountForm"
												property="productHAVoList" indexId="index">
										<tr>
											<td>
												<bean:write name="stProductHighestAmountVo" property="orgName"/>
												<input type="hidden" name="saveList[<bean:write name='index' />].id" value="<bean:write name='stProductHighestAmountVo' property='id'/>" />
												<input type="hidden" name="saveList[<bean:write name='index' />].productname" value="<bean:write name='stProductHighestAmountVo' property='productname'/>" />
												<input type="hidden" name="saveList[<bean:write name='index' />].productcode" value="<bean:write name='stProductHighestAmountVo' property='productcode'/>" />
												<input type="hidden" name="saveList[<bean:write name='index' />].orgcode" value="<bean:write name='stProductHighestAmountVo' property='orgcode'/>" />
												<input type="hidden" name="saveList[<bean:write name='index' />].productmiddlecode" value="<bean:write name="stLimitAmountForm" property="productcentercode"></bean:write>" />
											</td>
											<td>
												<bean:write name="stProductHighestAmountVo" property="productname"/>
											</td>
											<td>
												<input type="text"   name="saveList[<bean:write name='index' />].limitamount"  value="<bean:write name="stProductHighestAmountVo" property="limitamount"/>"/>
											</td>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td id="saveid" style="text-align:center;display:none"><input  type="button"  value="保存" onclick="saveLimit()"/></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</common:form>
<script type="text/javascript">
	$(function(){
		selectMiddleProduct();
		var regionOrgCode = $("#regionOrgId").val();
		//默认选中地市机构
		if($("#isqueryid").val()){
			$("#proId").show();
			$("#regionOrg").find("option[value='"+regionOrgCode+"']").attr("selected",true);
			chooseSpLegal(regionOrgCode);
		}
		$("input:visible[type='text']").each(function(){
			if($(this).val()== null)
				$(this).val("");
		})
	})
	//默认选中产品种类
	function selectMiddleProduct(){
		var middleCode = $("#middleCode").val();
		$("input[name='productcode']").each(function(){
			if($(this).val() == middleCode) {
				$(this).attr("checked","checked");
			}
		})
	} 
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
					$("#orgLegal").attr("disabled",false);
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
					if(productcentercode != null &&  productcentercode != ""){
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
				$("input:visible[type='radio']").each(function(){
					if($(this).attr("checked")){
						$("#saveid").show();
					}
				})
			}
		})	
	} 
	//产品额度保存
	function saveLimit(){
		var reg = /^\d+(\.\d+)?$/;
		var check = false;
		$("input:visible[type='text']").each(function(){
			if($(this).val()=="" || $(this).val()==null)
				$(this).val("-1");
			else if(!reg.test($(this).val())){
				alert("输入格式不对,请重新输入");
				$(this).focus();
				check = true;
				return;
			}
		})
		if(check){
			$("input:visible[type='text']").each(function(){
				if($(this).val()=="-1")
					$(this).val("");
			})
			return;
		}
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=insertProduct';
		$.ajax({
			type:"post",
			url:url,
			data:$("form:first").serialize(),
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				$("input:visible[type='text']").each(function(){
					if($(this).val()=="-1")	
						$(this).val("");
				})
				alert(data.result)
				window.location.reload();
			}
		})	
	}
	//保存
	function saveratio(){
		$("#button").attr("disabled",true);
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
	
	function selectR(middleCode){
		var regionOrgCode = $("#regionOrg").val();
		var orgcode = $("#orgLegal").val();
		var productcentercode = middleCode;
		if(regionOrgCode =="-1" | null == regionOrgCode){
			alert("请选择地市机构");
			return;
		}
		if(orgcode =="0" | null == orgcode){
			alert("请选择法人机构");
			return;
		}
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=queryLoanRadio&productcentercode='+productcentercode+'&orgcode='+orgcode+'&regionOrgCode='+regionOrgCode+'&isquery=true';
		window.location.href=url;
	}
	/* 查询 */
	function openLimitAmount(){
		var productcode = $("#productcode").val();
		var regionOrgCode = $("#regionOrg").val();
		var orgcode = $("#orgLegal").val();
		var productcentercode = $("select[name='productcentercode']").val();
		if(regionOrgCode =="-1" | null == regionOrgCode | regionOrgCode == ""){
			alert("请选择地市机构");
			return;
		}
		if(orgcode =="0" | null == orgcode | orgcode == ""){
			alert("请选择法人机构");
			return;
		}
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=queryProductBigByOrg&productcentercode='+productcentercode+'&orgcode='+orgcode+'&regionOrgCode='+regionOrgCode+'&productcode='+productcode+'&isquery=true';
		window.location.href=url;
	}
	//返回
	function reback(){
		var url = '<%=request.getContextPath()%>'+'/st/StLimitAmountParaMaintain.do?operAtt=limitJump';
		window.location.href=url;
	}
</script>
</body>
</html>
</html:html>