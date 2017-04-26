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
<title>������ϵά������</title>
<common:theme/>
</head>
<body onload="myfunc()">
	<common:form action="/StSystemMaintain.do" method="get">
	<common:hidden property="operAtt" ></common:hidden>
	<!-- ���˻��� -->
	<common:hidden name="stSystemForm" property="stModelVoR.orgcode" styleId="orgcodeF"/>
	<!--���л���  -->
	<common:hidden name="stSystemForm" property="orgcodeR" styleId="orgcodeD"/>
	<!--��� -->
	<common:hidden name="stSystemForm" property="stModelVoR.classification" styleId="classificationh"/>
	<!--״̬  -->
	<common:hidden name="stSystemForm" property="stModelVoR.state" styleId="stateh"/>
	<!--��½����Ϊ���˻���ʱ�Ļ�������  -->
	<common:hidden name="stSystemForm" property="legalOrgCode" styleId="legalOrgCode"/>
	<!--��½����Ϊ���˻���ʱ�Ļ�������  -->
	<common:hidden name="stSystemForm" property="legalOrgName" styleId="legalOrgName"/>
	<!--����-->
	<common:hidden name="stSystemForm" property="dotOrg" styleId="dotOrgcodeid"/>
	<table class="border" width="80%">
			<TR>
				<TD class="pagehead" >������ϵά������</TD>
			</TR>
			<TR>
				<TD>
					<table class="free" width="100%">
						<tbody>
							<tr>
								<td class="regionhead" colspan="4">��ѯ����</td>
							</tr>
							<tr >
								<th>���л��� </th>
								<td >
									<select name="stModelVo.orgcode" style="width:150px" onchange="queryLegal(this)" >
											<option value="-1">-��ѡ��-</option>
										<logic:equal value="1" name="stSystemForm" property="orgnum">
											<logic:iterate id="stOrgSpreadVo" name="stSystemForm"
														property="orgSpreadVoList" indexId="indexId">
												<option value="<bean:write name="stOrgSpreadVo" property="orgcode"></bean:write>"><bean:write name="stOrgSpreadVo" property="orgname"></bean:write></option>	
											</logic:iterate>
										</logic:equal>
										<logic:notEqual value="1" name="stSystemForm" property="orgnum">
											<logic:iterate id="stOrgSpreadVo" name="stSystemForm"
													property="orgSpreadVoList" indexId="indexId">
												<option value="<bean:write name="stOrgSpreadVo" property="orgcode"></bean:write>"><bean:write name="stOrgSpreadVo" property="orgname"></bean:write></option>
											</logic:iterate>
										</logic:notEqual>
									</select><font  color=red >*</font>
								</td>
								<th>���˻���</th>
								<td>
									<select style="width:200px" disabled="disabled"  name="orgcodeJ" id="orgLegal" onchange="findDotByLegal(this)">
									</select>
								</td>
							</tr>
							<tr>
								<th>����</th>	
								<td>
									 <param:select style="width:150px" type="cmis.classification" nullStr="-��ѡ��-"
									property="stModelVo.classification"  name="stSystemForm" onchange="setDot(this)" 
									label="���" styleId="classification" empty="true"  />
								</td>
								<th>״̬</th>
								<td>
									 <param:select style="width:200px" type="cmis.state" nullStr="-��ѡ��-"
									property="stModelVo.state"  name="stSystemForm"
									label="״̬" styleId="state" empty="true"  />
								</td> 
							</tr>
							<tr>
								<th>����</th>
								<td colspan="3">
									<select style="width:300px" disabled="disabled"  name="dotOrgcode" id="dotOrgcode">
									</select>
								</td>
							</tr>
							<tr>
								<td class="regionfoot" colspan="4">
									<input type="button"   value="��ѯ" 	onclick="query()"/>&nbsp;&nbsp;
									<input type="button" 	value="����" onclick="reset()"/>
								</td>
							</tr>
						</tbody>	
					</table>
					<table  width="100%" class="grid" id="productBig">
						<tbody>
						<TR align=center>
							<TD class="regioncomposite" colspan="12"><bean:write
							name="stSystemForm" property="footer" filter="false" /></TD>
						</TR>
						<tr>
							<th width="8%">��������</th>
							<th width="8%">��������</th>
							<th width="8%">����</th>
							<th width="8%">�汾��</th>
							<th width="8%">����ʱ��</th>
							<th width="8%">�޸�ʱ��</th>
							<th width="8%">��Чʱ��</th>
							<th width="8%">״̬</th>
							<th colspan="4">����</th>
						</tr>
						<logic:iterate id="stModelVo" name="stSystemForm"
							property="footer.dataArray" indexId="indexId">
							<tr>
								<td><bean:write name="stModelVo" property="orgcode"/></td>
								<td><bean:write name="stModelVo" property="orgname"/></td>
								<td>
									<param:display type="cmis.classification" name="stModelVo" property="classification" />
								</td>
								<td><bean:write name="stModelVo" property="version"/></td>
								<td><bean:write name="stModelVo" property="createtime"/></td>
								<td><bean:write name="stModelVo" property="modifytime"/></td>
								<td><bean:write name="stModelVo" property="effecttime"/></td>
								<td>
									<param:display type="cmis.state" name="stModelVo"  property="state"/>
								</td>
									<logic:equal value="5" name="stModelVo" property="state">
										<td width="4%"><input type="button"   value="��������" onclick="checkApprove('<bean:write name="stModelVo" property="state"/>','<bean:write name="stModelVo" property="id"/>')"/></td>
										<td width="7%"><input type="button"   value="�ܾ�" onclick="refuse('<bean:write name="stModelVo" property="state"/>','<bean:write name="stModelVo" property="id"/>')"/></td>
									</logic:equal>
								<td width="8%"><input type="button"   value="����鿴" onclick="ruleJump('<bean:write name="stModelVo" property="state"/>','<bean:write name="stModelVo" property="modelcode"/>'
										,'<bean:write name="stModelVo" property="classification"/>','<bean:write name="stModelVo" property="modelname"/>')"/></td>
									<logic:equal value="6" name="stModelVo" property="state">
										<td width="6%"><input type="button"   value="��Ч" onclick="haveEffect('<bean:write name="stModelVo" property="state"/>','<bean:write name="stModelVo" property="id"/>',
										'<bean:write name="stModelVo" property="classification"/>','<bean:write name="stModelVo" property="orgcode"/>')"/></td>
									</logic:equal>
								</td>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
				</TD>
			</TR>
		</table>
	</common:form>
<script type="text/javascript">
//��ʼ��ʱ��
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
		var url = '<%=request.getContextPath()%>'+'/st/StSystemMaintain.do?operAtt=queryCorpOrg';
		$.ajax({
			type:"post",
			url:url,
			data:{orgcodeJ:orgD,legalOrgName:legalOrgName,legalOrgCode:legalOrgCode},
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				//�Ƴ�option
				var option = $("#orgLegal").find("option")
				for(var i=0;i<option.length;i++){
					option[i].remove();
				}
				var obj = data.datas;
				if(obj.length>0){
					$("#orgLegal").attr("disabled",false);
				}
				$("#orgLegal").append("<option value='0'>-��ѡ��-</option>")
				for(var i=0,j=obj.length;i<j;i++){
					$("#orgLegal").append("<option value='"+obj[i].orgcode+"'>"+obj[i].orgname+"</option>")
				}
				var codeF = $("#orgcodeF");
				if(codeF.val()!=null && codeF.val()!=""){
					$("#orgLegal").find("option[value='"+codeF.val()+"']").attr("selected",true);
					findDotByLegal(codeF);
				}
			}
		})
	}
	//��ѯ���˻���
	function queryLegal(data){
		var legalOrgName = $("#legalOrgName").val();
		var legalOrgCode = $("#legalOrgCode").val();
		var url = '<%=request.getContextPath()%>'+'/st/StSystemMaintain.do?operAtt=queryCorpOrg';
		$.ajax({
			type:"post",
			url:url,
			data:{orgcodeJ:data.value,legalOrgName:legalOrgName,legalOrgCode:legalOrgCode},
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				//�Ƴ�option
				var option = $("#orgLegal").find("option")
				for(var i=0;i<option.length;i++){
					option[i].remove();
				}
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
	/* ��ѯ���˻����µ����� */
	function findDotByLegal(p){
			var url = '<%=request.getContextPath()%>'+'/st/StSystemMaintain.do?operAtt=queryDotByLegal&orgcodeJ='+$(p).val();
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
					//�Ƴ�option
					$("#dotOrgcode").children().remove();
					var obj = data.datas;
					$("#dotOrgcode").append("<option value='0'>-��ѡ��-</option>")
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
	//�������ѡ��·����������
	function setDot(p){
		if($(p).val() == 7 && $("#orgLegal").val() != "0" & $("#orgLegal").val() != null){
			$("#dotOrgcode").attr("disabled",false)
		}else {
			$("#dotOrgcode").find("option[value='0']").attr("selected",true);
			$("#dotOrgcode").attr("disabled",true)
		}
	}
	//��ѯ
	function query(){
		var legalOrgName = $("#legalOrgName").val();
		var legalOrgCode = $("#legalOrgCode").val();
		var orgF = $("select[name='orgcodeJ']").val();
		var org = $("select[name='stModelVo.orgcode']").val();
		if(org == "-1" || org == null || org == ""){
			alert("��ѡ�����");
			return;
		}
		if(legalOrgName != null && legalOrgName != "" && legalOrgCode != null && legalOrgCode != ""){
			if(orgF == "0" || orgF == null){
				alert("��ѡ���˻���")
				return;
			}
		}
		with(document.forms[0]){
			operAtt.value="queryModelCheck";
			submit();
		}
	}
	
	//��������
	function checkApprove(state,id){
		var mes = confirm("ȷ���ð汾����ͨ���� ��");
		if(mes==true){
			var url = '<%=request.getContextPath()%>'+'/st/StSystemMaintain.do?operAtt=updateStatues&stModelVo.state='+state+'&stModelVo.id='+id;
			$.ajax({
				type:"post",
				url:url,
				contentType:"application/x-www-form-urlencoded;charset=utf-8",
				success:function(data){
					window.location.reload();
				}
			})
		}
	}
	//�ܾ�
	function refuse(state,id){
		var mes = confirm("ȷ���˻ظð汾��");
		if(mes==true){
			var url = '<%=request.getContextPath()%>'+'/st/StSystemMaintain.do?operAtt=refuseCommit&stModelVo.state='+state+'&stModelVo.id='+id;
			$.ajax({
				type:"post",
				url:url,
				contentType:"application/x-www-form-urlencoded;charset=utf-8",
				success:function(data){
					window.location.reload();
				}
			})
		}
	}
	//��Ч
	function haveEffect(state,id,classification,orgcode){
		var mes = confirm("ȷ��ʹ�ð汾��Ч��");
		if(mes==true){
			var url = '<%=request.getContextPath()%>'+'/st/StSystemMaintain.do?operAtt=updateStatues&stModelVo.state='+state+'&stModelVo.id='+id+'&stModelVo.classification='+classification+'&stModelVo.orgcode='+orgcode;
			$.ajax({
				type:"post",
				url:url,
				contentType:"application/x-www-form-urlencoded;charset=utf-8",
				success:function(data){
					window.location.reload();
				}
			})
		}
	}
	//����鿴
	function ruleJump(state,modelcode,classification,modelname){
		var backUrl = window.location.href;
		backUrl = backUrl.replace(/\&/g,"%26");
		var url = '<%=request.getContextPath()%>'+'/st/StSystemMaintain.do?operAtt=ruleJump&stModelVo.modelcode='+modelcode+'&stModelVo.state='+state+'&stModelVo.classification='+classification+'&stModelVo.modelname='+modelname+'&showtext=true' + '&backUrl=' + backUrl;
		window.location.href = url;
	}
</script>
</body>
</html>
</html:html>