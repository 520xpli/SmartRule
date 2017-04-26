<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%  response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<html:html>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GBK">
<META name="GENERATOR" content="IBM WebSphere Studio">
<script src="<%=request.getContextPath()%>/js/jquery.js"
	language="javascript">
</script>
<script src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.min.js"></script>
<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"></link>
<title>策略体系维护</title>
</head>
<body onload="myfunc()" style="margin:0 auto;text-align:center">
	<form action="/SmartRule/StSystemMaintain.do" method="get">  
	<input type="hidden" name="operAtt" value="${operAtt }"></input>
	<!-- 法人机构 -->
	<input type="hidden" value="${stSystemForm.stModelVoR.orgcode}" name="orgcodeF"/>
	<!--地市机构  -->
	<input type="hidden" value="${stSystemForm.orgcodeR}" name="orgcodeD"/>
	<!--类别 -->
	<input type="hidden" value="${stSystemForm.stModelVoR.classification}" name="classificationh"/>
	<!--状态  -->
	<input type="hidden" value="${stSystemForm.stModelVoR.state}" name="stateh"/>
	<!--登陆机构为法人机构时的机构编码  -->
	<input type="hidden" value="${stSystemForm.legalOrgCode}" name="legalOrgCode"/>
	<!--登陆机构为法人机构时的机构名称  -->
	<input type="hidden" value="${stSystemForm.legalOrgName}" name="legalOrgName"/>
	<!--是否法人机构 -->
	<input type="hidden" value="${stSystemForm.isleagal}" name="isleagal"/>
	<!--是否地市机构 -->
	<input type="hidden" value="${stSystemForm.isCity}" name="isCity"/>
	<!--是否网点 -->
	<input type="hidden" value="${stSystemForm.isDot}" name="isDot"/>
	<!--网点-->
	<input type="hidden" value="${stSystemForm.dotOrg}" name="dotOrgcodeid"/>
	<div class="page-header" align="center">
		<h1>策略体系维护</h1>
	</div>
	<table width="80%" align="center">
			<TR>
				<TD>
					<table class="table table-condensed table-bordered" width="100%">
						<tbody>
							<tr>
								<td class="regionhead" colspan="4" style="text-align:center">查询条件</td>
							</tr>
							<tr >
								<th>地市机构 </th>
								<td >
									<select name="stModelVo.orgcode" onchange="queryLegal(this)" >
										<option value="02000A">滨州</option>
									</select><font  color=red >*</font>
								</td>
								<th>法人机构</th>
								<td>
									<select>
										<option value="02000A">滨州农商行</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>类型</th>	
								<td>
									<select name="stModelVo.classification">
										<option value="">请选择</option>
										<option value="1" <logic:equal value="1" name="stSystemForm" property="stModelVo.classification">selected</logic:equal>>准入</option>
										<option value="2" <logic:equal value="2" name="stSystemForm" property="stModelVo.classification">selected</logic:equal>>额度计算</option>
										<option value="4" <logic:equal value="4" name="stSystemForm" property="stModelVo.classification">selected</logic:equal>>评级</option>
										<option value="5" <logic:equal value="5" name="stSystemForm" property="stModelVo.classification">selected</logic:equal>>初审</option>
									</select>
								</td>
								<th>状态</th>
								<td>
									<select name="stModelVo.state">
										<option value="">请选择</option>
										<option value="1" <logic:equal value="1" name="stSystemForm" property="stModelVo.state">selected</logic:equal>>生效中</option>
										<option value="2" <logic:equal value="2" name="stSystemForm" property="stModelVo.state">selected</logic:equal>>已失效</option>
										<option value="4" <logic:equal value="4" name="stSystemForm" property="stModelVo.state">selected</logic:equal>>新增</option>
										<option value="5" <logic:equal value="5" name="stSystemForm" property="stModelVo.state">selected</logic:equal>>审批中</option>
										<option value="6" <logic:equal value="6" name="stSystemForm" property="stModelVo.state">selected</logic:equal>>审批完成</option>
									</select>
								</td> 
							</tr>
							<tr>
								<td class="regionfoot" colspan="4" align="center" style="text-align:center">
									<input type="button" class="btn"  value="查询" 	onclick="query()"/>&nbsp;&nbsp;
									<input type="button" class="btn"	value="重置" onclick="reset()"/>
								</td>
							</tr>
						</tbody>	
					</table>
					<table  width="100%" class="table table-condensed table-bordered table-striped" id="productBig">
						<tbody>
						<tr>
							<th width="8%">机构编码</th>
							<th width="8%">机构名称</th>
							<th width="8%">类型</th>
							<th width="8%">版本号</th>
							<th width="8%">创建时间</th>
							<th width="8%">修改时间</th>
							<th width="8%">生效时间</th>
							<th width="8%">状态</th>
							<th colspan="5">操作</th>
						</tr>
						<logic:iterate id="stModelVo" name="stSystemForm"
							property="footer.dataArray" indexId="indexId">
							<tr>
								<td><bean:write name="stModelVo" property="orgcode"/></td>
								<td><bean:write name="stModelVo" property="orgname"/></td>
								<td>
									<logic:equal value="1" name="stModelVo" property="classification">准入</logic:equal>
									<logic:equal value="2" name="stModelVo" property="classification">额度计算</logic:equal>
									<logic:equal value="4" name="stModelVo" property="classification">评级</logic:equal>
									<logic:equal value="5" name="stModelVo" property="classification">初审</logic:equal>
								</td>
								<td>
									<bean:write name="stModelVo" property="version"/>
								</td>
								<td class="createtime"><bean:write name="stModelVo" property="createtime" format="yyyy-MM-dd HH:mm:ss" /></td>
								<td class="modifytime"><bean:write name="stModelVo" property="modifytime" format="yyyy-MM-dd HH:mm:ss" /></td>
								<td class="effecttime"><bean:write name="stModelVo" property="effecttime" format="yyyy-MM-dd HH:mm:ss" /></td>
								<td>
									<logic:equal value="1" name="stModelVo" property="state">生效中</logic:equal>
									<logic:equal value="2" name="stModelVo" property="state">已失效</logic:equal>
									<logic:equal value="4" name="stModelVo" property="state">新增</logic:equal>
									<logic:equal value="5" name="stModelVo" property="state">审批中</logic:equal>
									<logic:equal value="6" name="stModelVo" property="state">审批完成</logic:equal>
								</td>
								<logic:notEqual value="true" name="stSystemForm" property="isDot">
									<logic:equal value="4" name="stModelVo" property="state">
										<td width="4%"><input type="button"  class="btn"  value="删除" 	onclick="deleteV('<bean:write name="stModelVo" property="id"/>',this)"/></td>
										<td width="7%"><input type="button"  class="btn" value="编辑" 	onclick="editState('<bean:write name="stModelVo" property="id"/>')"/></td>
										<td width="8%"><input type="button"  class="btn" value="规则维护" 	onclick="ruleEditJump('<bean:write name="stModelVo" property="state"/>','<bean:write name="stModelVo" property="modelcode"/>'
										,'<bean:write name="stModelVo" property="classification"/>','<bean:write name="stModelVo" property="modelname"/>')"/></td>
										<td width="5%"><input type="button"  class="btn" value="提交" 	onclick="commitState('<bean:write name="stModelVo" property="state"/>','<bean:write name="stModelVo" property="id"/>')"/></td>
									</logic:equal>
									<logic:notEqual value="4" name="stModelVo" property="state">
										<logic:notEqual value="5" name="stModelVo" property="state">
											<logic:notEqual value="6" name="stModelVo" property="state">
												<td width="6%"><input type="button" class="btn" value="升级" 	onclick="upgradeState('<bean:write name="stModelVo" property="id"/>',
												'<bean:write name="stModelVo" property="version"/>','<bean:write name="stModelVo" property="modelname"/>',
												'<bean:write name="stModelVo" property="classification"/>','<bean:write name="stModelVo" property="orgcode"/>',this)"/></td>
											</logic:notEqual>
										</logic:notEqual>
										<td width="8%"><input type="button" class="btn"  value="规则查看" 	onclick="ruleJump('<bean:write name="stModelVo" property="state"/>','<bean:write name="stModelVo" property="modelcode"/>'
										,'<bean:write name="stModelVo" property="classification"/>','<bean:write name="stModelVo" property="modelname"/>')"/></td>
									</logic:notEqual>
								</td>
								<logic:notEqual value="7" name="stModelVo"  property="classification">
									<logic:notEqual value="true" name="stSystemForm"  property="isleagal">
										<td width="8%"><input type="button" class="btn"  value="复制" onclick="copyModel('<bean:write name="stModelVo" property="id"/>','<bean:write name="stModelVo" property="classification"/>')"/></td>
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal value="7" name="stModelVo"  property="classification">
									<td width="8%"><input type="button" class="btn"  value="复制" onclick="copyProcess('<bean:write name="stModelVo" property="id"/>','<bean:write name="stModelVo" property="classification"/>')"/></td>
								</logic:equal>
							</logic:notEqual>
							<logic:equal value="true" name="stSystemForm" property="isDot">
								<td width="8%"><input type="button"  class="btn" value="规则查看" 	onclick="ruleJump('<bean:write name="stModelVo" property="state"/>','<bean:write name="stModelVo" property="modelcode"/>'
										,'<bean:write name="stModelVo" property="classification"/>','<bean:write name="stModelVo" property="modelname"/>')"/></td>
							</logic:equal>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
				</TD>
			</TR>
		</table>
		<bean:write name="stSystemForm" property="footer" filter="false" />
	</form>
<script type="text/javascript">
	function myfunc(){
		$(".createtime").each(function(){
			$(this).html($(this).html().split(".")[0])
		})
		$(".modifytime").each(function(){
			$(this).html($(this).html().split(".")[0])
		})
		$(".effecttime").each(function(){
			$(this).html($(this).html().split(".")[0])
		})
	}
	$(document).ready(function(){
		var codeD = $("#orgcodeD").val();
		var classification = $("#classification").val();
		var state = $("#stateh").val();
		if(codeD!=null && codeD!=""){
			$("select[name='stModelVo.orgcode']").find("option[value='"+codeD+"']").attr("selected",true);
			queryOrgF(codeD);
		}
		if(classification!=null && classification!=""){
			$("#classificationh").find("option[value='"+classification+"']").attr("selected",true);
		}
		if(state!=null && state!=""){
			$("#state").find("option[value='"+state+"']").attr("selected",true);
		}
	})
	function queryOrgF(orgD){
		var legalOrgName = $("#legalOrgName").val();
		var legalOrgCode = $("#legalOrgCode").val();
		var url = '<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=queryCorpOrg';
		$.ajax({
			type:"post",
			url:url,
			data:{orgcodeJ:orgD,legalOrgName:legalOrgName,legalOrgCode:legalOrgCode},
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				//移除option
				var option = $("#orgLegal").find("option")
				for(var i=0;i<option.length;i++){
					option[i].remove();
				}
				var obj = data.datas;
				if(obj.length>0){
					$("#orgLegal").attr("disabled",false);
				}
				$("#orgLegal").append("<option value='0'>-请选择-</option>")
				for(var i=0,j=obj.length;i<j;i++){
					$("#orgLegal").append("<option value='"+obj[i].orgcode+"'>"+obj[i].orgname+"</option>")
				}
				var codeF = $("#orgcodeF");
				if(codeF.val() != null && codeF.val() != ""){
					$("#orgLegal").find("option[value='"+codeF.val()+"']").attr("selected",true);
					findDotByLegal(codeF);
				}
			}
		})
	}
	//查询法人机构
	function queryLegal(data){
		var legalOrgName = $("#legalOrgName").val();
		var legalOrgCode = $("#legalOrgCode").val();
		var isDot = $("#isDot").val();
		var url = '<%=request.getContextPath()%>/StSystemMaintain.do?operAtt=queryCorpOrg'
		$.ajax({
			type:"post",
			url:url,
			data:{orgcodeJ:data.value,legalOrgName:legalOrgName,legalOrgCode:legalOrgCode,isDot:isDot},
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				//移除option
				$("#orgLegal").children().remove();
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
	/* 查询法人机构下的网点 */
	function findDotByLegal(p){
			var url = '<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=queryDotByLegal&orgcodeJ='+$(p).val();
			if($(p).val() == "0" || $(p).val() == null){
				$("#dotOrgcode").children().remove();	
				$("#dotOrgcode").attr("disabled",true);
				return
			}
			$.ajax({
				type:"post",
				url:url,
				contentType:"application/x-www-form-urlencoded;charset=utf-8",
				success:function(data){
					//移除option
					$("#dotOrgcode").children().remove();
					var obj = data.datas;
					$("#dotOrgcode").append("<option value='0'>-请选择-</option>")
					for(var i=0,j=obj.length;i<j;i++){
						$("#dotOrgcode").append("<option value='"+obj[i].orgcode+"'>"+obj[i].orgname+"</option>")
					}
					if($("#classification").val() == 7){
						$("#dotOrgcode").attr("disabled",false);
					}else {
						$("#dotOrgcode").attr("disabled",true);
					}
					var dot = $("#dotOrgcodeid").val()
					if(dot != null & dot != ""){
						$("#dotOrgcode").find("option[value='"+dot+"']").attr("selected",true);
					}
				}
			})
	}
	function resetDot(p){
		if($(p).val() == "0"){
			$("#dotOrgcodeid").val("")
		}
	}
	//规则类别选择路程走向后控制
	function setDot(p){
		if($(p).val() == 7 && $("select[name='orgcodeJ']").val() != 0 & $("select[name='orgcodeJ']").val() != null){
			$("#dotOrgcode").attr("disabled",false)
		}else {
			$("#dotOrgcode").find("option[value='0']").attr("selected",true);
			$("#dotOrgcode").attr("disabled",true)
		}
	}
	//查询
	function query(){
		var legalOrgName = $("#legalOrgName").val();
		var legalOrgCode = $("#legalOrgCode").val();
		var orgF = $("select[name='orgcodeJ']").val();
		var org = $("select[name='stModelVo.orgcode']").val();
		if(org == "-1" || org == null || org == ""){
			alert("请选择机构");
			return;
		}
		if(legalOrgName != null & legalOrgName != ""  & legalOrgCode != null & legalOrgCode != ""){
			if(orgF == "0" || orgF == null){
				alert("请选择法人机构")
				return;
			}
		}
		with(document.forms[0]){
			operAtt.value="queryModel";
			submit();
		}
	}
	//编辑
	function editState(id){
		 var url='<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=editModel&stModelVo.id='+id;
		 window.open(url, 'newwindow', 'width=500,height=500,scrollbars=yes,resizable=yes,left=300,top=100');
	}
	/* 复制规则模型 */
	function copyModel(id,classification){
		var orgD = $("select[name='stModelVo.orgcode']").val();
		var legalOrg = $("#orgLegal").val();
		if(legalOrg == "0"){
			 var url='<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=copyModel&stModelVo.id='+id+'&stModelVo.classification='+classification+'&orgcodeJ='+orgD+'&orgType=model&isleagal=t&isCity=t';
		}else{
			var url='<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=copyModel&stModelVo.id='+id+'&stModelVo.classification='+classification+'&orgcodeJ='+legalOrg+'&orgType=model&isleagal=t&isCity=t';
		}
		 window.open(url, 'newwindow', 'width=500,height=500,scrollbars=yes,resizable=yes,left=300,top=100');
	}
	//流程走向复制
	function copyProcess(id,classification) {
		var orgD = $("select[name='stModelVo.orgcode']").val();
		var legalOrg = $("#orgLegal").val();
		var dotOrg = $("#dotOrgcode").val();
			if(legalOrg == "0"){
				//地市机构
				var url='<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=copyModel&stModelVo.id='+id+'&stModelVo.classification='+classification+'&orgcodeJ='+orgD+'&orgType=inst&isleagal='+$("#isleagal").val()+'&orgType=dot&isCity='+$("#isCity").val();
				 window.open(url, 'newwindow', 'width=500,height=500,scrollbars=yes,resizable=yes,left=300,top=100');
			}else if(dotOrg == "0"){
				//法人机构
				var url='<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=copyModel&stModelVo.id='+id+'&stModelVo.classification='+classification+'&orgcodeJ='+legalOrg+'&orgType=legal&isleagal='+$("#isleagal").val()+'&orgType=dot&isCity='+$("#isCity").val();
				 window.open(url, 'newwindow', 'width=500,height=500,scrollbars=yes,resizable=yes,left=300,top=100');
			}else {
				//网点
				var url='<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=copyModel&stModelVo.id='+id+'&stModelVo.classification='+classification+'&orgcodeJ='+dotOrg+'&orgType=dot&isleagal='+$("#isleagal").val()+'&orgType=dot&isCity='+$("#isCity").val();
				 window.open(url, 'newwindow', 'width=500,height=500,scrollbars=yes,resizable=yes,left=300,top=100');
			}
	}
	//提交新版本
	function commitState(state,id){
		var mes = confirm("是否提交该版本？");
		if(mes==true){
			var url = '<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=updateStatues&stModelVo.state='+state+'&stModelVo.id='+id;
			$.ajax({
				type:"post",
				url:url,
				contentType:"application/x-www-form-urlencoded;charset=utf-8",
				success:function(data){
					alert(data.retu)
					window.location.reload();
				}
			})
		}
	}
	//升级版本
	function upgradeState(id,version,name,classification,orgcode,p){
		var mes = confirm("确认将版本号为"+version+",类型为"+name+"的规则体系进行升级吗？");
		if(mes==true){
			$(p).val("升级中，请稍后");
			$("input:visible[value='升级']").each(function(){
				$(this).attr("disabled",true);
			})
			$(p).attr("disabled",true);
			var url = '<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=upgrade&stModelVo.id='+id+'&stModelVo.classification='+classification+'&stModelVo.orgcode='+orgcode;
			$.ajax({
				type:"post",
				url:url,
				contentType:"application/x-www-form-urlencoded;charset=utf-8",
				success:function(data){
					alert(data.retu)
					window.location.reload();
				}
			})
		}
	}
	//删除版本
	function deleteV(id,p){
		var mes = confirm("是否删除该版本？");
		if(mes==true){
			$(p).val("删除中，请稍后");
			$(p).attr("disabled",true);
			var url = '<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=deleteModel&stModelVo.id='+id;
			$.ajax({
				type:"post",
				url:url,
				contentType:"application/x-www-form-urlencoded;charset=utf-8",
				success:function(data){
					alert(data.retu)
					window.location.reload();
				}
			})
		}
	}
	//规则维护
	function ruleEditJump(state,modelcode,classification,modelname){
		var backUrl = window.location.href;
		backUrl = backUrl.replace(/\&/g,"%26");
		var url = '<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=ruleJump&stModelVo.modelcode='+modelcode+'&stModelVo.state='+state+'&stModelVo.classification='+classification+'&stModelVo.modelname='+modelname+'&showtext=false' + '&backUrl=' + backUrl;
		window.location.href = url;
	}
	//规则查看
	function ruleJump(state,modelcode,classification,modelname){
		var backUrl = window.location.href;
		backUrl = backUrl.replace(/\&/g,"%26");
		var url = '<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=ruleJump&stModelVo.modelcode='+modelcode+'&stModelVo.state='+state+'&stModelVo.classification='+classification+'&stModelVo.modelname='+modelname+'&showtext=true' + '&backUrl=' + backUrl;
		window.location.href = url;
	}
</script>
</body>
</html>
</html:html>