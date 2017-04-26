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
<title>������ϵά��</title>
</head>
<body onload="myfunc()" style="margin:0 auto;text-align:center">
	<form action="/SmartRule/StSystemMaintain.do" method="get">  
	<input type="hidden" name="operAtt" value="${operAtt }"></input>
	<!-- ���˻��� -->
	<input type="hidden" value="${stSystemForm.stModelVoR.orgcode}" name="orgcodeF"/>
	<!--���л���  -->
	<input type="hidden" value="${stSystemForm.orgcodeR}" name="orgcodeD"/>
	<!--��� -->
	<input type="hidden" value="${stSystemForm.stModelVoR.classification}" name="classificationh"/>
	<!--״̬  -->
	<input type="hidden" value="${stSystemForm.stModelVoR.state}" name="stateh"/>
	<!--��½����Ϊ���˻���ʱ�Ļ�������  -->
	<input type="hidden" value="${stSystemForm.legalOrgCode}" name="legalOrgCode"/>
	<!--��½����Ϊ���˻���ʱ�Ļ�������  -->
	<input type="hidden" value="${stSystemForm.legalOrgName}" name="legalOrgName"/>
	<!--�Ƿ��˻��� -->
	<input type="hidden" value="${stSystemForm.isleagal}" name="isleagal"/>
	<!--�Ƿ���л��� -->
	<input type="hidden" value="${stSystemForm.isCity}" name="isCity"/>
	<!--�Ƿ����� -->
	<input type="hidden" value="${stSystemForm.isDot}" name="isDot"/>
	<!--����-->
	<input type="hidden" value="${stSystemForm.dotOrg}" name="dotOrgcodeid"/>
	<div class="page-header" align="center">
		<h1>������ϵά��</h1>
	</div>
	<table width="80%" align="center">
			<TR>
				<TD>
					<table class="table table-condensed table-bordered" width="100%">
						<tbody>
							<tr>
								<td class="regionhead" colspan="4" style="text-align:center">��ѯ����</td>
							</tr>
							<tr >
								<th>���л��� </th>
								<td >
									<select name="stModelVo.orgcode" onchange="queryLegal(this)" >
										<option value="02000A">����</option>
									</select><font  color=red >*</font>
								</td>
								<th>���˻���</th>
								<td>
									<select>
										<option value="02000A">����ũ����</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>����</th>	
								<td>
									<select name="stModelVo.classification">
										<option value="">��ѡ��</option>
										<option value="1" <logic:equal value="1" name="stSystemForm" property="stModelVo.classification">selected</logic:equal>>׼��</option>
										<option value="2" <logic:equal value="2" name="stSystemForm" property="stModelVo.classification">selected</logic:equal>>��ȼ���</option>
										<option value="4" <logic:equal value="4" name="stSystemForm" property="stModelVo.classification">selected</logic:equal>>����</option>
										<option value="5" <logic:equal value="5" name="stSystemForm" property="stModelVo.classification">selected</logic:equal>>����</option>
									</select>
								</td>
								<th>״̬</th>
								<td>
									<select name="stModelVo.state">
										<option value="">��ѡ��</option>
										<option value="1" <logic:equal value="1" name="stSystemForm" property="stModelVo.state">selected</logic:equal>>��Ч��</option>
										<option value="2" <logic:equal value="2" name="stSystemForm" property="stModelVo.state">selected</logic:equal>>��ʧЧ</option>
										<option value="4" <logic:equal value="4" name="stSystemForm" property="stModelVo.state">selected</logic:equal>>����</option>
										<option value="5" <logic:equal value="5" name="stSystemForm" property="stModelVo.state">selected</logic:equal>>������</option>
										<option value="6" <logic:equal value="6" name="stSystemForm" property="stModelVo.state">selected</logic:equal>>�������</option>
									</select>
								</td> 
							</tr>
							<tr>
								<td class="regionfoot" colspan="4" align="center" style="text-align:center">
									<input type="button" class="btn"  value="��ѯ" 	onclick="query()"/>&nbsp;&nbsp;
									<input type="button" class="btn"	value="����" onclick="reset()"/>
								</td>
							</tr>
						</tbody>	
					</table>
					<table  width="100%" class="table table-condensed table-bordered table-striped" id="productBig">
						<tbody>
						<tr>
							<th width="8%">��������</th>
							<th width="8%">��������</th>
							<th width="8%">����</th>
							<th width="8%">�汾��</th>
							<th width="8%">����ʱ��</th>
							<th width="8%">�޸�ʱ��</th>
							<th width="8%">��Чʱ��</th>
							<th width="8%">״̬</th>
							<th colspan="5">����</th>
						</tr>
						<logic:iterate id="stModelVo" name="stSystemForm"
							property="footer.dataArray" indexId="indexId">
							<tr>
								<td><bean:write name="stModelVo" property="orgcode"/></td>
								<td><bean:write name="stModelVo" property="orgname"/></td>
								<td>
									<logic:equal value="1" name="stModelVo" property="classification">׼��</logic:equal>
									<logic:equal value="2" name="stModelVo" property="classification">��ȼ���</logic:equal>
									<logic:equal value="4" name="stModelVo" property="classification">����</logic:equal>
									<logic:equal value="5" name="stModelVo" property="classification">����</logic:equal>
								</td>
								<td>
									<bean:write name="stModelVo" property="version"/>
								</td>
								<td class="createtime"><bean:write name="stModelVo" property="createtime" format="yyyy-MM-dd HH:mm:ss" /></td>
								<td class="modifytime"><bean:write name="stModelVo" property="modifytime" format="yyyy-MM-dd HH:mm:ss" /></td>
								<td class="effecttime"><bean:write name="stModelVo" property="effecttime" format="yyyy-MM-dd HH:mm:ss" /></td>
								<td>
									<logic:equal value="1" name="stModelVo" property="state">��Ч��</logic:equal>
									<logic:equal value="2" name="stModelVo" property="state">��ʧЧ</logic:equal>
									<logic:equal value="4" name="stModelVo" property="state">����</logic:equal>
									<logic:equal value="5" name="stModelVo" property="state">������</logic:equal>
									<logic:equal value="6" name="stModelVo" property="state">�������</logic:equal>
								</td>
								<logic:notEqual value="true" name="stSystemForm" property="isDot">
									<logic:equal value="4" name="stModelVo" property="state">
										<td width="4%"><input type="button"  class="btn"  value="ɾ��" 	onclick="deleteV('<bean:write name="stModelVo" property="id"/>',this)"/></td>
										<td width="7%"><input type="button"  class="btn" value="�༭" 	onclick="editState('<bean:write name="stModelVo" property="id"/>')"/></td>
										<td width="8%"><input type="button"  class="btn" value="����ά��" 	onclick="ruleEditJump('<bean:write name="stModelVo" property="state"/>','<bean:write name="stModelVo" property="modelcode"/>'
										,'<bean:write name="stModelVo" property="classification"/>','<bean:write name="stModelVo" property="modelname"/>')"/></td>
										<td width="5%"><input type="button"  class="btn" value="�ύ" 	onclick="commitState('<bean:write name="stModelVo" property="state"/>','<bean:write name="stModelVo" property="id"/>')"/></td>
									</logic:equal>
									<logic:notEqual value="4" name="stModelVo" property="state">
										<logic:notEqual value="5" name="stModelVo" property="state">
											<logic:notEqual value="6" name="stModelVo" property="state">
												<td width="6%"><input type="button" class="btn" value="����" 	onclick="upgradeState('<bean:write name="stModelVo" property="id"/>',
												'<bean:write name="stModelVo" property="version"/>','<bean:write name="stModelVo" property="modelname"/>',
												'<bean:write name="stModelVo" property="classification"/>','<bean:write name="stModelVo" property="orgcode"/>',this)"/></td>
											</logic:notEqual>
										</logic:notEqual>
										<td width="8%"><input type="button" class="btn"  value="����鿴" 	onclick="ruleJump('<bean:write name="stModelVo" property="state"/>','<bean:write name="stModelVo" property="modelcode"/>'
										,'<bean:write name="stModelVo" property="classification"/>','<bean:write name="stModelVo" property="modelname"/>')"/></td>
									</logic:notEqual>
								</td>
								<logic:notEqual value="7" name="stModelVo"  property="classification">
									<logic:notEqual value="true" name="stSystemForm"  property="isleagal">
										<td width="8%"><input type="button" class="btn"  value="����" onclick="copyModel('<bean:write name="stModelVo" property="id"/>','<bean:write name="stModelVo" property="classification"/>')"/></td>
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal value="7" name="stModelVo"  property="classification">
									<td width="8%"><input type="button" class="btn"  value="����" onclick="copyProcess('<bean:write name="stModelVo" property="id"/>','<bean:write name="stModelVo" property="classification"/>')"/></td>
								</logic:equal>
							</logic:notEqual>
							<logic:equal value="true" name="stSystemForm" property="isDot">
								<td width="8%"><input type="button"  class="btn" value="����鿴" 	onclick="ruleJump('<bean:write name="stModelVo" property="state"/>','<bean:write name="stModelVo" property="modelcode"/>'
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
				if(codeF.val() != null && codeF.val() != ""){
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
		var isDot = $("#isDot").val();
		var url = '<%=request.getContextPath()%>/StSystemMaintain.do?operAtt=queryCorpOrg'
		$.ajax({
			type:"post",
			url:url,
			data:{orgcodeJ:data.value,legalOrgName:legalOrgName,legalOrgCode:legalOrgCode,isDot:isDot},
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				//�Ƴ�option
				$("#orgLegal").children().remove();
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
	function resetDot(p){
		if($(p).val() == "0"){
			$("#dotOrgcodeid").val("")
		}
	}
	//�������ѡ��·����������
	function setDot(p){
		if($(p).val() == 7 && $("select[name='orgcodeJ']").val() != 0 & $("select[name='orgcodeJ']").val() != null){
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
		if(legalOrgName != null & legalOrgName != ""  & legalOrgCode != null & legalOrgCode != ""){
			if(orgF == "0" || orgF == null){
				alert("��ѡ���˻���")
				return;
			}
		}
		with(document.forms[0]){
			operAtt.value="queryModel";
			submit();
		}
	}
	//�༭
	function editState(id){
		 var url='<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=editModel&stModelVo.id='+id;
		 window.open(url, 'newwindow', 'width=500,height=500,scrollbars=yes,resizable=yes,left=300,top=100');
	}
	/* ���ƹ���ģ�� */
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
	//����������
	function copyProcess(id,classification) {
		var orgD = $("select[name='stModelVo.orgcode']").val();
		var legalOrg = $("#orgLegal").val();
		var dotOrg = $("#dotOrgcode").val();
			if(legalOrg == "0"){
				//���л���
				var url='<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=copyModel&stModelVo.id='+id+'&stModelVo.classification='+classification+'&orgcodeJ='+orgD+'&orgType=inst&isleagal='+$("#isleagal").val()+'&orgType=dot&isCity='+$("#isCity").val();
				 window.open(url, 'newwindow', 'width=500,height=500,scrollbars=yes,resizable=yes,left=300,top=100');
			}else if(dotOrg == "0"){
				//���˻���
				var url='<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=copyModel&stModelVo.id='+id+'&stModelVo.classification='+classification+'&orgcodeJ='+legalOrg+'&orgType=legal&isleagal='+$("#isleagal").val()+'&orgType=dot&isCity='+$("#isCity").val();
				 window.open(url, 'newwindow', 'width=500,height=500,scrollbars=yes,resizable=yes,left=300,top=100');
			}else {
				//����
				var url='<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=copyModel&stModelVo.id='+id+'&stModelVo.classification='+classification+'&orgcodeJ='+dotOrg+'&orgType=dot&isleagal='+$("#isleagal").val()+'&orgType=dot&isCity='+$("#isCity").val();
				 window.open(url, 'newwindow', 'width=500,height=500,scrollbars=yes,resizable=yes,left=300,top=100');
			}
	}
	//�ύ�°汾
	function commitState(state,id){
		var mes = confirm("�Ƿ��ύ�ð汾��");
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
	//�����汾
	function upgradeState(id,version,name,classification,orgcode,p){
		var mes = confirm("ȷ�Ͻ��汾��Ϊ"+version+",����Ϊ"+name+"�Ĺ�����ϵ����������");
		if(mes==true){
			$(p).val("�����У����Ժ�");
			$("input:visible[value='����']").each(function(){
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
	//ɾ���汾
	function deleteV(id,p){
		var mes = confirm("�Ƿ�ɾ���ð汾��");
		if(mes==true){
			$(p).val("ɾ���У����Ժ�");
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
	//����ά��
	function ruleEditJump(state,modelcode,classification,modelname){
		var backUrl = window.location.href;
		backUrl = backUrl.replace(/\&/g,"%26");
		var url = '<%=request.getContextPath()%>'+'/StSystemMaintain.do?operAtt=ruleJump&stModelVo.modelcode='+modelcode+'&stModelVo.state='+state+'&stModelVo.classification='+classification+'&stModelVo.modelname='+modelname+'&showtext=false' + '&backUrl=' + backUrl;
		window.location.href = url;
	}
	//����鿴
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