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
<TITLE>产品推广维护</TITLE>
<common:theme />
</HEAD>
<body onload="myfunction()">
	<common:form action="/StProductSpreadMaintain.do">
		<common:hidden property="operAtt"></common:hidden>
		<common:hidden name="stProductSpreadForm" property="orgcode" styleId="orgcode"/>
		<common:hidden name="stProductSpreadForm" property="orgname" styleId="orgname"/>
		<common:hidden name="stProductSpreadForm" property="backUrl" styleId="backUrl"/>
		<table class="border">
			<tbody>
				<TR>
					<TD class="pagehead">产品推广</TD>
				</TR>
				<TR>
					<TD>
						<table class="free" width="100%">
							<tbody>
								<tr>
									<td class="regionhead" colspan="4" align="center">机构信息</td>
								</tr>
								<tr>
									<th>机构编号</th>
									<td><common:text empty="true" label="机构编号" size="20"
											validator="text(0,80)" name="stProductSpreadForm"
											property="orgcode" styleId="orgcode" 
											readonly="true" /></td>
									<th>机构名称</th>
									<td><common:text empty="true" label="机构名称" size="20"
											validator="text(0,220)" name="stProductSpreadForm"
											property="orgname" styleId="orgname" style="width:300px"
											readonly="true" /></td>
								</tr>
							</tbody>
						</table>
						<table width="100%" style="margin-top: 10px">
							<tr>
								<td width="100%" align="left" valign="top">
									<table align="left" width="100%" class="grid" id="productBig">
										<tr>
											<th width="5%"></th>
											<th width="60%">产品中类</th>
											<th colspan="2">操作</th>
										</tr>
										<logic:iterate id="stProductSpreadVo" name="stProductSpreadForm"
											property="footer.dataArray" indexId="indexId">
											<tr>
												<td><input type="radio" id="productcodeId"
													onclick="selectR('<bean:write name="stProductSpreadVo" property="productcode"></bean:write>')"
													name="productcode"></td>
												<td><bean:write name="stProductSpreadVo"
														property="productname"></bean:write></td>

												<!-- 判断关闭 -->
												<logic:equal value="0" name="stProductSpreadVo"
													property="isopen">
													<td style="width: 15%"><input type="button"
														name="openpro" style="width: 30px" value="开"
														onclick="openProduct(this,'<bean:write name="stProductSpreadVo" property="productcode"></bean:write>',
														'<bean:write name="stProductSpreadVo" property="id" />','<bean:write name="stProductSpreadVo" property="productname" />')" />
													</td>
													<td><input name="closepro" type="button"
														style="width: 30px" disabled value="关 "
														onclick="closeProduct(this,'<bean:write name="stProductSpreadVo" property="productcode"></bean:write>',
														'<bean:write name="stProductSpreadVo" property="id" />','<bean:write name="stProductSpreadVo" property="productname" />')" />
													</td>
												</logic:equal>
												<!-- 判断开启 -->
												<logic:equal value="1" name="stProductSpreadVo"
													property="isopen">
													<td style="width: 15%"><input name="openpro"
														type="button" style="width: 30px" disabled value="开"
														onclick="openProduct(this,'<bean:write name="stProductSpreadVo" property="productcode" />',
														'<bean:write name="stProductSpreadVo" property="id" />','<bean:write name="stProductSpreadVo" property="productname" />')" /></td>
													<td><input name="closepro" type="button" value="关 "
														style="width: 30px"
														onclick="closeProduct(this,'<bean:write name="stProductSpreadVo" property="productcode" />',
														'<bean:write name="stProductSpreadVo" property="id" />','<bean:write name="stProductSpreadVo" property="productname" />')" />
													</td>
												</logic:equal>

											</tr>
										</logic:iterate>
									</table>
								</td>
							</tr>
							<tr>
								<td width="100%" align="left" valign="top">
									<table align="left" width="100%" class="grid" id="productSmall"
										style="margin-top: 20px">

									</table>
								</td>
							</tr>
							<tr>
								<td width="100%" valign="top" style="text-align: center"><input
									type="button" value="返回" onclick="retuBack()" /></td>
							</tr>
						</table>
					</TD>
				</TR>
			</tbody>
		</table>
	</common:form>
<script type="text/javascript">
	function myfunction(){
		$("input[name='closepro']").each(function(){
			if($(this).attr('disabled')){
				$(this).parent().parent().find("input[name='productcode']").attr("disabled",true);
			}
		})
	}
	function retuBack(){
		window.location.href=$("#backUrl").val();
	}
	
	function selectR(productcode){
		var orgcode = $("input[name='orgcode']").val();
		var url = '<%=request.getContextPath()%>'+'/st/StProductSpreadMaintain.do?operAtt=queryProductSmaByProBig';
		$.ajax({
			type:"post",
			url:url,
			data:{productcode:productcode,orgcode:orgcode},
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				//移除
				var trs = $("#productSmall").children().remove();
				var obj = data.datas;
				var proS = "<tr><th width='30%'>特色产品</th><th width='35%'>额度(元)</th><th  colspan='3' >操作</th></tr>";
				for(var i = 0; i < obj.length;i++){
					var la = obj[i].limitamount
					if(la == null)
						la="";
					var proSma = null;
					if(obj[i].isopen == 0){
						var productname = obj[i].productname;
						productname = productname.replace(" ", "");
						proSma = "<tr><td>"+obj[i].productname+"</td><td><input style='width:150px' disabled type='text' name='limitamount' value="+la+" ></td>"
						+ "<td><input name='openp' style='width:30px' type='button' value='开' onclick=openP(this,'"+obj[i].productcode+"','"+obj[i].id+"','"+productname+"','"+productcode+"') ></td>"
						+"<td><input name='closep' style='width:30px' type='button' disabled value='关' onclick=closeP(this,'"+obj[i].productcode+"','"+obj[i].id+"','"+productname+"','"+productcode+"')></td>"
						+ "<td><input name='save' disabled='true' type='button' style='width:35px' value='保存' onclick=savePro('"+productname+"','"+obj[i].productcode+"',this,'"+productcode+"','"+obj[i].id+"')></td></tr>"
					}else{
						var productname = obj[i].productname;
						productname = productname.replace(" ", "");
						proSma = "<tr><td>"+obj[i].productname+"</td><td><input style='width:150px' type='text' name='limitamount' value="+la+" ></td>"
						+"<td><input name='openp' style='width:30px' disabled type='button' value='开' onclick=openP(this,'"+obj[i].productcode+"','"+obj[i].id+"','"+productname+"','"+productcode+"') ></td>"
						+"<td><input name='closep' style='width:30px' type='button' value='关' onclick=closeP(this,'"+obj[i].productcode+"','"+obj[i].id+"','"+productname+"','"+productcode+"')></td>"
						+"<td><input name='save'  type='button' style='width:35px' value='保存' onclick=savePro('"+productname+"','"+obj[i].productcode+"',this,'"+productcode+"','"+obj[i].id+"')></td></tr>"
					}
					proS += proSma;
				}
					$("#productSmall").append("<tbody>"+proS+"</tbody>");
			}
		})
	}
	/* 保存数据 */
	function savePro(productname,productcode,p,productcentercode,id){
		var limitamount = $(p).parent().parent().find("input[name='limitamount']").val();
		var isopen = "";
		if($(p).parent().parent().find("input[name='openp']").attr("disabled")){
			isopen = "1";
		}
		if($(p).parent().parent().find("input[name='closep']").attr("disabled")){
			isopen = "0";
		}
		var orgcode = $("input[name='orgcode']").val();
		var reg = /^[0-9]*$/;
			if(reg.test(limitamount)){
				if(limitamount=="" || limitamount==null)
					limitamount = -1;
				if(limitamount>=5000000){
					alert("产品额度输入不能超过或等于5000000,请重新输入！");
					$(p).parent().parent().find("input[name='limitamount']").val("");
					return;
				}
				var url = '<%=request.getContextPath()%>'+'/st/StProductSpreadMaintain.do?operAtt=savePro';
				$.ajax({
					type:"post",
					url:url,
					data:{productcentercode:productcentercode,orgcode:orgcode,id:id,isopen:isopen,limitamount:limitamount,productname:productname,productcode:productcode},
					contentType:"application/x-www-form-urlencoded;charset=utf-8",
					success:function(data){
							alert(data.retu)
					}
				})
			}else{
				alert("输入格式错误，请重新输入！");
				$(p).parent().parent().find("input[name='limitamount']").val("");
			}

	}
	/**
	 * 开启
	 */
	function openProduct(p,productcode,proid,productname){
		var orgcode = $("input[name='orgcode']").val();
		var url = '<%=request.getContextPath()%>'+'/st/StProductSpreadMaintain.do?operAtt=openProduct';
		$.ajax({
			type:"post",
			url:url,
			data:{productcode:productcode,orgcode:orgcode,id:proid,productname:productname},
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				alert(data.retu);
				if(data.retu="开启成功"){
					$(p).parent().parent().find("input[name='closepro']").attr("disabled",false);
					$(p).parent().parent().find("input[name='openpro']").attr("disabled",true);
					$(p).parent().parent().find("input[name='productcode']").attr("checked",true);
					$(p).parent().parent().find("input[name='productcode']").attr("disabled",false);
					selectR(productcode);
				}
			}
		})
	}
	/**
	 * 关闭
	 */
	function closeProduct(p,productcode,proid,productname){
		var orgcode = $("input[name='orgcode']").val();
		var url = '<%=request.getContextPath()%>'+'/st/StProductSpreadMaintain.do?operAtt=closeProduct';
		$.ajax({
			type:"post",
			url:url,
			data:{productcode:productcode,orgcode:orgcode,id:proid,productname:productname},
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				alert(data.retu);
				if(data.retu="关闭成功"){
					$(p).parent().parent().find("input[name='closepro']").attr("disabled",true);
					$(p).parent().parent().find("input[name='openpro']").attr("disabled",false);
					$(p).parent().parent().find("input[name='productcode']").attr("checked",false);
					$(p).parent().parent().find("input[name='productcode']").attr("disabled",true);
					$("#productSmall").children().remove();
				}
			}
		})
	}
	/**
	 * 特色产品开启
	 */
	function openP(p,productcode,proid,productname,productcentercode){
		var orgcode = $("input[name='orgcode']").val();
		var limitamount = $(p).parent().parent().find("input[name='limitamount']").val();
		if(limitamount=="")
			limitamount = -1;
		var url = '<%=request.getContextPath()%>'+'/st/StProductSpreadMaintain.do?operAtt=openPro';
		$.ajax({
			type:"post",
			url:url,
			data:{productcentercode:productcentercode,limitamount : limitamount,productcode:productcode,orgcode:orgcode,id:proid,productname:productname},
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				alert(data.retu)
				if(data.retu="开启成功"){
					$(p).parent().parent().find("input[name='openp']").attr("disabled",true);
					$(p).parent().parent().find("input[name='closep']").attr("disabled",false);
					$(p).parent().parent().find("input[name='save']").attr("disabled",false);
					$(p).parent().parent().find("input[name='limitamount']").attr("disabled",false);
				}
			}
		})
	}
	/**
	 * 特色产品关闭
	 */
	function closeP(p,productcode,proid,productname,productcentercode){
		var orgcode = $("input[name='orgcode']").val();
		var limitamount = $(p).parent().parent().find("input[name='limitamount']").val();
		if(limitamount=="")
			limitamount = -1;
		var url = '<%=request.getContextPath()%>'+ '/st/StProductSpreadMaintain.do?operAtt=closePro';
		$.ajax({
				type : "post",
				url : url,
				data : {
					productcentercode:productcentercode,
							limitamount : limitamount,
							productcode : productcode,
							orgcode:orgcode,
							id : proid,
							productname : productname
				},
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				success : function(data) {
					alert(data.retu)
					if(data.retu="关闭成功"){
						$(p).parent().parent().find("input[name='openp']").attr("disabled",false);
						$(p).parent().parent().find("input[name='closep']").attr("disabled",true);
						$(p).parent().parent().find("input[name='save']").attr("disabled",true);
						$(p).parent().parent().find("input[name='limitamount']").attr("disabled",true);
					}
				}
		})
	}
	</script>
</body>
</html>
</html:html>