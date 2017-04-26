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
<title>������ϵ����</title>
<common:theme/>
</head>
<body>
	<common:form action="/StSystemMaintain.do">
	<common:hidden property="operAtt" ></common:hidden>
	<!--��½����Ϊ���˻���ʱ�Ļ�������  -->
	<common:hidden name="stSystemForm" property="stModelVo.id" styleId="idId"/>
	<common:hidden name="stSystemForm" property="stModelVo.classification" styleId="classificationid"/>
	<table width="100%">
			<TR>
				<TD>
					<table class="free" >
						<tbody>
							<TR>
								<TD colspan="2" class="pagehead" >����ѡ��</TD>
							</TR>
							<tr>
								<th>����</th>
								<td>
									<select style="width:200px"  name="orgcodeJ" id="orgLegal">
										<option value="-1">-��ѡ��-</option>
										<logic:iterate id="stOrgSpreadVo" name="stSystemForm"
												property="orgSpreadVoList" indexId="indexId">
											<option value="<bean:write name="stOrgSpreadVo" property="orgcode"></bean:write>"><bean:write name="stOrgSpreadVo" property="orgname"></bean:write></option>	
										</logic:iterate>
									</select>
								</td>
							</tr>
							<tr>
								<td class="regionfoot" colspan="2">
									<input type="button" id="sureid"  value="ȷ��" 	onclick="sureCopy(this)"/>&nbsp;&nbsp;
									<input type="button" class="button" value="�ر�" onClick="window.close();">
								</td>
							</tr>
						</tbody>	
					</table>
				</TD>
			</TR>
		</table>
	</common:form>
	<script type="text/javascript">
		function sureCopy(p){
			var orgcode = $("#orgLegal").val();
			$(".button").attr("disabled",true);
			if(orgcode == "-1"){
				alert("��ѡ�����")
				return;
			}
			$("#sureid").attr("disabled",true);
			var classification = $("#classificationid").val();
			var id = $("#idId").val()
			var url = '<%=request.getContextPath()%>/st/StSystemMaintain.do?operAtt=copymodel&legalOrgCode='+orgcode+'&stModelVo.id='+id+'&stModelVo.classification='+classification;
			$(p).val("���ڸ����У����Ե�");
			$.ajax({
				type:"post",
				url:url,
				contentType:"application/x-www-form-urlencoded;charset=utf-8",
				success:function(data){
					alert(data.retu)
					window.close();
				}
			})
		}
	</script>
</body>
</html>
</html:html>