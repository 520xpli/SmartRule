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
<title>������ϵ�༭</title>
<common:theme/>
</head>
<body>
	<common:form action="/StSystemMaintain.do">
	<common:hidden property="operAtt" ></common:hidden>
	<common:hidden name="stSystemForm" property="stModelVo.id" styleId="deptId"/>
	<table width="100%">
			<TR>
				<TD>
					<table class="free" >
						<tbody>
							<TR>
								<TD colspan="2" class="pagehead" >������ϵ�༭</TD>
							</TR>
							<tr>
								<th width="200px">ģ������</th>
								<td width="200px">
										<common:text empty="true" label="ģ������" size="20"
									validator="text(0,80)" name="stSystemForm" style="width:152px"
									property="stModelVo.modelname" styleId="modelname" readonly="false"/>
								</td>
							</tr>
							<tr>
								<th>ģ�ͱ���</th>
								<td>
										<common:text empty="true" label="ģ�ͱ���" size="20"
									validator="text(0,80)" name="stSystemForm"
									property="stModelVo.modelcode" styleId="modelcode" readonly="true"/>
								</td>
							</tr>
							<tr>
								<th>�������</th>
								<td>
										<common:text empty="true" label="�������" size="20"
									validator="text(0,80)" name="stSystemForm"
									property="stModelVo.orgcode" styleId="orgcode" readonly="true"/>
								</td>
							</tr>
							<tr>
								<th>�汾��</th>
								<td>
										<common:text empty="true" label="�汾��" size="20"
									validator="text(0,80)" name="stSystemForm"
									property="stModelVo.version" styleId="version" readonly="true"/>
								</td>
							</tr>
							<tr>
								<th>���</th>
								<td>
										<input type="text" name="stModelVo.classification" value="<param:display type="cmis.classification" name="stSystemForm" property="stModelVo.classification"/>"
										readonly="readonly" style="width:152px"/>
								</td>
							</tr>
							<tr>
								<th>״̬</th>
								<td> 
									<input type="text" name="stModelVo.state" value="<param:display type="cmis.state" name="stSystemForm" property="stModelVo.state"/>"
										readonly="readonly" style="width:152px"/> 
								</td>
							</tr>
							<tr>
								<td class="regionfoot" colspan="2">
									<input type="button"  value="����" 	onclick="save()"/>&nbsp;&nbsp;
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
		function save(){
			with(document.forms[0]){
				operAtt.value="saveModel";
				submit();
				alert("����ɹ�");
			}
		}
	</script>
</body>
</html>
</html:html>