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
<html:html>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GBK">
<META name="GENERATOR" content="IBM WebSphere Studio">
<script src="<%=request.getContextPath()%>/js/jquery-1.4.2.js"
	language="javascript">
	
</script>
<TITLE>����ά��</TITLE>
<common:theme />
</HEAD>
<script type="text/javascript">
	
</script>
<BODY>
	<h1 style="text-align: center; font-size: 18px">����<font onclick="window.parent.isShowCode=false">ά</font>��</h1>
	<form action="/StOrgSpreadMaintain.do">
		<div>
			<input type="hidden" name="strucVo.id"
			value="<bean:write name='stSingleRuleForm' property='strucVo.id' />" />
		<input type="hidden" id="showText"
			value="<bean:write name='stSingleRuleForm' property='showtext' />" />
		<input type="hidden" name="strucVo.classification" 
			value="<bean:write name='stSingleRuleForm' property='strucVo.classification' />" />
		<input type="hidden" name="strucVo.struccode" id="struccode"
			value="<bean:write name='stSingleRuleForm' property='struccode' />" />
		<input type="hidden" name="modelcode"
			value="<bean:write name='stSingleRuleForm' property='modelcode' />" />
		<input type="hidden" name="strucVo.parentstruccode"
			value="<bean:write name='stSingleRuleForm' property='strucVo.parentstruccode' />" />
		<input type="hidden" name="strucVo.stRuleVo.id"
			value="<bean:write name='stSingleRuleForm' property='strucVo.stRuleVo.id' />" />
		<input type="hidden" name="strucVo.stRuleVo.rulecode"
			value="<bean:write name='stSingleRuleForm' property='strucVo.stRuleVo.rulecode' />" />
		<input type="hidden" id="objectcode"
			value="<bean:write name='stSingleRuleForm' property='strucVo.stRuleVo.objectcode' />" />
		<input type="hidden" id="isShowCode"
			value="<bean:write name='stSingleRuleForm' property='isShowCode' />" />
			<table align="left" width="100%" class="grid">
				<tr>
					<td>����:</td>
					<td><common:text empty="false" label="����" size="20"
							validator="text(0,80)" name="stSingleRuleForm"
							property="strucVo.strucname" styleId="strucVo.strucname"
							readonly="false" /></td>
					<td>����:</td>
					<td><common:text empty="false" label="����" size="20"
							validator="text(0,80)" name="stSingleRuleForm"
							property="strucVo.description" styleId="strucVo.description"
							readonly="false" /></td>
				</tr>
				<tr>
				<td>ִ��˳��:</td>
				<td><common:text empty="false" label="ִ��˳��" size="20"
						validator="digit(0,3)" name="stSingleRuleForm"
						property="strucVo.seq" styleId="strucVo.seq" readonly="false" />
				</td>
				<td >�ܾ����ڣ��죩:</td>
				<td id="refuseCycle"><common:text empty="false" label="�ܾ����ڣ��죩" size="20"
						validator="digit(0,3)" name="stSingleRuleForm"
						property="strucVo.stRuleVo.cycle" styleId="strucVo.cycle"
						readonly="false" /></td>
				</tr>
				<tr>
					<td>��ʾ��Ϣ:</td>
					<td ><common:text empty="false" label="����" size="20" 
						validator="text(0,80)" name="stSingleRuleForm"
						property="strucVo.prompt" styleId="strucVo.prompt"
						readonly="false" /></td>
					<td>���ö���:</td>
					<td><select name="strucVo.stRuleVo.objectcode"
						onchange="objectChange()" id="object">
							<logic:iterate id="object" name="stSingleRuleForm"
								property="objectList" indexId="indexId">
								<option
									value="<bean:write name='object' property='objectcode'/>"
									<logic:equal name="object" property="objectcode" value="1">
							selected
						</logic:equal>>
									<bean:write name='object' property='objectname' />
								</option>
							</logic:iterate>
					</select></td>
				</tr>
			</table>
		</div>
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<div style="text-align: right">
			<input type="button" value="���һ���������" onClick="addDetail()" />
		</div>
		<div class="position">
			<div id="backupTable">
				<table style="text-align: center; margin-top: 10px"
					class="ruletable grid">
					<input type="hidden" name="strucVo.detailList.preOrRe" value="1" />
					<tr style="background-color:gray">
						<td style="width:10%">������</td>
							<td >������</td>
							<td style="width:30%">��������</td>
							<td >���㷽ʽ</td>
							<td >��ֵ</td>
							<td >������</td>
							<td>����</td>
					</tr>
					<tr>
						<td><input type="hidden"
							name="strucVo.detailList.rowList.firstcal" /></td>
						<td>
							<select name="strucVo.detailList.rowList.isleft">
								<option value=""> </option>
								<option value="(">(</option>
							</select>
						</td>
						<td class="property">
							<input type="hidden"
							class="propertyinput" /> 
							<select name="strucVo.detailList.rowList.propertycode" onchange="propertyChange(this)">
								<option value="">��ѡ��</option>
							</select>
						</td>
						<td><select name="strucVo.detailList.rowList.secondcal">
								<option value="">��</option>
								<option value=">">></option>
								<option value=">=">>=</option>
								<option value="<"><</option>
								<option value="<="><=</option>
								<option value="==">==</option>
								<option value="||">||</option>
								<option value="&&">&&</option>
								<option value="+">+</option>
								<option value="-">-</option>
								<option value="*">*</option>
								<option value="/ ">/</option>
						</select></td>
						<td><input type="text"
							name="strucVo.detailList.rowList.value" class="value" id="inputFlag"/></td>
						<td>
							<select name="strucVo.detailList.rowList.isright">
								<option value=""> </option>
								<option value=")">)</option>
							</select>
						</td>
						<td style="white-space:nowrap"><input type="button" value="���" onclick="addrow(this)" />
							<input type="button" value="ɾ��ȫ��" onclick="deleteall(this)" /></td>
					</tr>
					<tr class="row">
						<td><select name="strucVo.detailList.rowList.firstcal">
								<option value="">��</option>
								<option value=">">></option>
								<option value=">=">>=</option>
								<option value="<"><</option>
								<option value="<="><=</option>
								<option value="==">==</option>
								<option value="||">||</option>
								<option value="&&">&&</option>
								<option value="+">+</option>
								<option value="-">-</option>
								<option value="*">*</option>
								<option value="/ ">/</option>
						</select></td>
						<td>
							<select name="strucVo.detailList.rowList.isleft">
								<option value=""> </option>
								<option value="(">(</option>
							</select>
						</td>
						<td class="property"><input type="hidden"
							class="propertyinput" /> <select
							name="strucVo.detailList.rowList.propertycode" onchange="propertyChange(this)">
								<option value="">��ѡ��</option>
						</select></td>
						<td><select name="strucVo.detailList.rowList.secondcal">
								<option value="">��</option>
								<option value=">">></option>
								<option value=">=">>=</option>
								<option value="<"><</option>
								<option value="<="><=</option>
								<option value="==">==</option>
								<option value="||">||</option>
								<option value="&&">&&</option>
								<option value="+">+</option>
								<option value="-">-</option>
								<option value="*">*</option>
								<option value="/ ">/</option>
						</select></td>
						<td ><input type="text"
							name="strucVo.detailList.rowList.value" class="value" id="inputFlag"/></td>
						<td>
							<select name="strucVo.detailList.rowList.isright">
								<option value=""> </option>
								<option value=")">)</option>
							</select>
						</td>
						<td style="white-space:nowrap"><input type="button" value="���" onclick="addrow(this)" />
							<input type="button" value="ɾ��" onclick="deleterow(this)" /></td>
					</tr>
					<tr>
						<td>���幫ʽ:</td>
						<td id="formulaDetail" colspan="4" style="word-wrap:break-word;word-break:break-all;white-space:normal;width:auto;"></td>
						<td>���:</td>
						<td ><select name="strucVo.detailList.ruleresult">
								<option>��ѡ��</option>
								<option value="-4">ͨ��</option>
								<option value="-3">����</option>
								<option value="-2">�˻�</option>
								<option value="-1">�ܾ�</option>
						</select></td>
					</tr>
				</table>
			</div>
			<logic:iterate id="detail" name="stSingleRuleForm"
				property="strucVo.detailList" indexId="indexId">
				<table style="text-align: center; margin-top: 10px"
					class="ruletable grid">
					<input type="hidden" name="strucVo.detailList.preOrRe" value="1" />
					<tr style="background-color:gray">
						<td style="width:10%">������</td>
							<td >������</td>
							<td style="width:30%">��������</td>
							<td >���㷽ʽ</td>
							<td >��ֵ</td>
							<td >������</td>
							<td>����</td>
					</tr>
					<logic:iterate id="row" name="detail" property="rowList"
						indexId="rowindexId">
						<tr>
							<td><logic:equal value="0" name="rowindexId">
									<input type="hidden" name="strucVo.detailList.rowList.firstcal" />
								</logic:equal> <logic:notEqual value="0" name="rowindexId">
									<select name="strucVo.detailList.rowList.firstcal">
										<option value="">��</option>
										<option value=">"
											<logic:equal value=">" name="row" property="firstcal">selected</logic:equal>>></option>
										<option value=">="
											<logic:equal value=">=" name="row" property="firstcal">selected</logic:equal>>>=</option>
										<option value="<" <logic:equal value="<" name="row"
											property="firstcal">selected
											</logic:equal>><
										</option>
											<option value="<=" <logic:equal value="<=" name="row" property="firstcal">selected</logic:equal>><=</option>
											<option value="==" <logic:equal value="==" name="row" property="firstcal">selected</logic:equal>>==</option>
											<option value="||" <logic:equal value="||" name="row" property="firstcal">selected</logic:equal>>||</option>
											<option value="&&" <logic:equal value="&&" name="row" property="firstcal">selected</logic:equal>>&&</option>
											<option value="+" <logic:equal value="+" name="row" property="firstcal">selected</logic:equal>>+</option>
											<option value="-" <logic:equal value="-" name="row" property="firstcal">selected</logic:equal>>-</option>
											<option value="*" <logic:equal value="*" name="row" property="firstcal">selected</logic:equal>>*</option>
											<option value="/" <logic:equal value="/" name="row" property="firstcal">selected</logic:equal>>/</option>
										</select>
									</logic:notEqual></td>
								<td>
									<select name="strucVo.detailList.rowList.isleft">
										<option value=""> </option>
										<option value="(" <logic:equal value="(" name="row" property="isleft">selected</logic:equal>>(</option>
									</select>
								</td>
								<td class="property" style="word-break:keep-all">
									<input type="hidden" class="propertyinput" value="<bean:write name='row' property='propertycode'/>" />
								<select name="strucVo.detailList.rowList.propertycode" onchange="propertyChange(this)" >
									<option value="">��ѡ��</option>
								</select></td>
								<td><select name="strucVo.detailList.rowList.secondcal">
										<option value="">��</option>
											<option value=">" <logic:equal value=">" name="row" property="secondcal">selected</logic:equal>>></option>
											<option value=">=" <logic:equal value=">=" name="row" property="secondcal">selected</logic:equal>>>=</option>
											<option value="<" <logic:equal value="<" name="row" property="secondcal">selected</logic:equal>><</option>
											<option value="<=" <logic:equal value="<=" name="row" property="secondcal">selected</logic:equal>><=</option>
											<option value="==" <logic:equal value="==" name="row" property="secondcal">selected</logic:equal>>==</option>
											<option value="||" <logic:equal value="||" name="row" property="secondcal">selected</logic:equal>>||</option>
											<option value="&&" <logic:equal value="&&" name="row" property="secondcal">selected</logic:equal>>&&</option>
											<option value="+" <logic:equal value="+" name="row" property="secondcal">selected</logic:equal>>+</option>
											<option value="-" <logic:equal value="-" name="row" property="secondcal">selected</logic:equal>>-</option>
											<option value="*" <logic:equal value="*" name="row" property="secondcal">selected</logic:equal>>*</option>
											<option value="/" <logic:equal value="/" name="row" property="secondcal">selected</logic:equal>>/</option>
								</select></td>
								<td><input type="text" value="<bean:write name='row' property='value' />"
									name="strucVo.detailList.rowList.value" class="value" id="inputFlag"/></td>
								<td>
							<select name="strucVo.detailList.rowList.isright">
								<option value=""> </option>
								<option value=")" <logic:equal value=")" name="row" property="isright">selected</logic:equal>>)</option>
							</select>
						</td>
								<td style="white-space:nowrap"><input type="button" value="���" onclick="addrow(this)" />
									<logic:equal value="0" name="rowindexId">
										<input type="button" value="ɾ��ȫ��" onclick="deleteall(this)" />
									</logic:equal> <logic:notEqual value="0" name="rowindexId">
										<input type="button" value="ɾ��" onclick="deleterow(this)"
									</logic:notEqual></td>
							</tr>
						</logic:iterate>
						<tr>
							<td class="displayText">���幫ʽ:</td>
							<td id="formulaDetail" colspan="4" style="word-wrap:break-word;word-break:break-all;white-space:normal;width:auto;"></td>
							<td class="displayText">���:</td>
							<td class="displayText"><select name="strucVo.detailList.ruleresult">
									<option>��ѡ��</option>
									<option value="-4" <logic:equal value="-4" name="detail" property="ruleresult"> selected</logic:equal> >ͨ��</option>
									<option value="-3" <logic:equal value="-3" name="detail" property="ruleresult"> selected</logic:equal> >����</option>
									<option value="-2" <logic:equal value="-2" name="detail" property="ruleresult"> selected</logic:equal> >�˻�</option>
									<option value="-1" <logic:equal value="-1" name="detail" property="ruleresult"> selected</logic:equal> >�ܾ�</option>
							</select></td>
						</tr>
					</table>
				</logic:iterate>
		</div>
		<div style="text-align: center">
			<input type="button" value="�ύ" onClick="commit()" />
		</div>
	</form>
</BODY>
<script type="text/javascript">
	//�������������
	var row = $(".row").html();
	//���������������
	var propertyArray;
	//�����Ʒ����
	var productData = null;
	//�����Ʒ����
	$(".row").remove();
	//����ѡ���Ʒ��λ��
	var productPosition;
	//���������������
	var ruletable = $(".position").find("#backupTable").html();
	//���ڻ�ȡ����select onchange֮ǰ��ֵ
	var objectOldVal = $("#object").val();
	$(".ruletable:first").remove();
	var showText = $("#showText").val();
	$(function(){
		initRefuseCycle();
		selectObject();
		getPropertyData();
		if(showText != true && showText != 'true'){
			setInterval(function(){
				displayFormula();
			},100);
		}
		
	})
	//���þܾ����ڳ�ʼֵ
	function initRefuseCycle(){
		if($("#refuseCycle").find("input").val() == "")
			$("#refuseCycle").find("input").val("0");
	}
	//��Ʒѡ��ص�����
	function callback(code,name){
		$(productPosition).find("#productName").val(name);
		$(productPosition).find("#productCode").val(code);
	}
	//ѡ�ж���
	function selectObject(){
		var objectCode = $("#objectcode").val();
		$("#object").find("option").each(function(){
			if($(this).val() == objectCode)
				$(this).attr("selected",true);
		})
	}
	//�鿴
	function displayText(){
		displayFormula();
		$("input[type='button']").remove();
		$("input:visible").each(function(){
			$(this).parent().html($(this).val());
		})
		$(".position").find(".ruletable").each(function(){
			var trLength = $(this).find("tr").length;
			$(this).find("tr").each(function(index){
				if(index < trLength - 1)
					$(this).remove();
				else{
					$(this).find("select").parent().html($(this).find("select option:selected").html());
				}
			})
		})
		$("select").parent().html($("select").find("option:selected").html());
		$(".displayText").css("width","30px");
	}
	//���һ���������
	function addDetail() {
		$(".position").append(ruletable);
		resetSingleProperty($(".position").find(".ruletable:last").find(".property"));
		window.parent.resetHeight();
	}
	//���һ��
	function addrow(p) {
		$(p).parent().parent().after("<tr>" + row + "</tr>");
		resetSingleProperty($(p).parent().parent().next().find(".property"));
		window.parent.resetHeight();
	}
	//ɾ��һ��
	function deleterow(p) {
		$(p).parent().parent().remove();
	}
	//ɾ��һ���������
	function deleteall(p) {
		$(p).parent().parent().parent().parent().remove();
	}
	//������
	function objectChange() {
		var ruleIsEmpty = $(".position").find(".ruletable").length == 0;
		if (!ruleIsEmpty) {
			if (confirm("�ı����������й���ȷ�ϸı�")) {
				$(".position").empty();
				getPropertyData();
				objectOldVal = $("#object").val();
			}
			else{
				$("#object").val(objectOldVal);
			}
		}else getPropertyData();
		addDetail();
	}
	//�ύ��
	function commit(){
		var name = $("[id='strucVo.strucname']");
		var desc = $("[id='strucVo.description']");
		var seq =  $("[id='strucVo.seq']");
		if(name.val() == ""){
			alert("����������");
			$(name).focus();
			return;
		}
		if(desc.val() == ""){
			alert("����������");
			$(desc).focus();
			return;
		}
		if(seq.val() == ""){
			alert("������˳��");
			$(seq).focus();
			return;
		}
		var flag = true;
		var reg = /^[0-9]*$/;
		var regExp = /^\d+(\.\d+)?$/;
		$("input[name='strucVo.detailList.rowList.value']").each(function(){
			if($(this).val() != null & $(this).val() != ""){
				if(!regExp.test($(this).val())){
					flag = false;
					alert("������ֵ�����������������ַ�!");	
					$(this).focus();
				}
			}
		})
		if(!flag)
			return;
		$("input[name='strucVo.detailList.ruleresult']").each(function(){
			if($(this).val() == "" | $(this).val() == null){
				flag = false;
				alert("��������");	
				$(this).focus();
			}
			if(!reg.test($(this).val())){
				flag = false;
				alert("��������ʽ����");	
				$(this).focus();
			}
		})
		if(!flag)
			return;
		resetName();
		$.ajax({
			type:"POST",
			datatype:"json",
			data:$("form:first").serialize(),
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			url:"<%=request.getContextPath()%>/st/StRuleMaintain.do?operAtt=insert",
			success:function(data){
				if(data.result == undefined){
					alert("��½��ʱ�������µ�½");
					return;
				}
				alert(data.result);
				var check = $("#struccode").val();
				if(data.success == 1 && (check == "" || check == null)){
					window.parent.location.reload();
				}
			},
			error:function(data){
			}
		})
	}
	//�������ж�������
	function resetProperty(){
		$(".property").each(function(){
			var code = $(this).find(".propertyinput").val();
			var appendStr = "<option value=''>��ѡ��</option>";
			for(var i = 0, j = propertyArray.length; i < j; i++){
				if(code == propertyArray[i].propertykey)
					appendStr += "<option value='" + propertyArray[i].propertykey + "' selected>" + propertyArray[i].propertyname + "</option>";
				else appendStr += "<option value='" + propertyArray[i].propertykey + "'>" + propertyArray[i].propertyname + "</option>";
			}
			$(this).find("select").empty().append(appendStr);
			$(this).find("select").css("width",$(this).find("select").width());
		})
	}
	//���õ�����������
	function resetSingleProperty(p){
		var code = $(p).find(".propertyinput").val();
		var appendStr = "<option value=''>��ѡ��</option>";
		for(var i = 0, j = propertyArray.length; i < j; i++){
			if(code == propertyArray[i].propertykey)
				appendStr += "<option value='" + propertyArray[i].propertykey + "' selected>" + propertyArray[i].propertyname + "</option>";
			else appendStr += "<option value='" + propertyArray[i].propertykey + "'>" + propertyArray[i].propertyname + "</option>";
		}
		$(p).find("select").empty().append(appendStr);
	}
	
	//��ȡ������������
	function getPropertyData(){
		var objectcode = $("#object").val();
		var isShowCode = $("#isShowCode").val();
		if(objectcode != null && objectcode != ''){
			$.ajax({
				type:"POST",
				datatype:"json",
				data:{"strucVo.stRuleVo.objectcode":objectcode, isShowCode:isShowCode},
				url:"<%=request.getContextPath()%>/st/StRuleMaintain.do?operAtt=queryProerty",
				success:function(data){
					propertyArray = data.propertyData;
					var modelcode = $("input[name='modelcode']").val();
					resetProperty();
					$.ajax({
						type:"POST",
						datatype:"json",
						url:"<%=request.getContextPath()%>/st/StRuleMaintain.do?operAtt=queryProduct",
						data:{modelcode:modelcode},
						success:function(data){
							productData = data;
							resetPropertyValue();
							if(showText == true | showText == 'true')
								displayText();
							$(".position").show();
						}
					})
				}
			})
		}
	}
	//��ʾ�������ʽ
	function displayFormula(){
			$(".position").find(".ruletable").each(function(index){
				var formula = "";
				var trLength = $(this).find("tr").length;
				$(this).find("tr").each(function(trIndex){
					if(trIndex > 0 && trIndex < trLength - 1){
						$(this).find("input[type='text'],select option:selected").each(function(){
							if($(this).html() == undefined | $(this).html() == '')
								formula += $(this).val();
							else formula += $(this).html();
						})
					}
				})
				formula = formula.replace(/��/g,'');
				formula = formula.replace(/��ѡ��/g,'');
				$(this).find("#formulaDetail").html(formula);
			})
	}
	//�����������Ժ���ֵ
	function resetPropertyValue(){
		$(".property").find("select").each(function(){
			propertyChange(this);
		})
	}
	//������Ʒѡ��ҳ��
	function popupProduct(p){
		var modelcode = $("input[name='modelcode']").val();
		var treeUrl = "<%=request.getContextPath()%>/st/StRuleMaintain.do?operAtt=queryProduct&modelcode=" + modelcode;
		var url = "<%=request.getContextPath()%>" + "/st/StRuleMaintain.do?operAtt=popupProduct&modelcode=" + modelcode + "&treeUrl=" + treeUrl;
		window.open(url, 'newwindow', 'width=800,height=600,scrollbars=yes,resizable=yes,left=300,top=100');
		productPosition = $(p).parent();
	}
	//���Ըı䣬��̬�޸�������ʽ
	function propertyChange(p){
		var property = $(p).parent().parent().find(".value");
		if($(p).val() == "productCode"){
			var productName = "";
			var proValue = $(property).val();
			if(property.length != 0 && proValue != null && proValue != ""){
				var productHItems = productData.items;
				for(var i = 0, j = productHItems.length; i < j; i++){
					var productItems = productHItems[i].children;
					for(var k = 0, l = productItems.length; k < l; k++){
						if(productItems[k].code == proValue){
							productName = productItems[k].name;
							break;
						}
					}
				}
			}
			var appendStr = "<input type='text' readonly id='productName' class='value' value=" + productName + ">" +
							"<img src='<%=request.getContextPath()%>/skins/images/search.gif' onclick='popupProduct(this)' />" +
							"<input type='hidden' id='productCode' name='strucVo.detailList.rowList.value' class='value' value=" + proValue +">";
			$(p).parent().parent().find(".selectClass,.value").parent().empty().append(appendStr);
		}else{
			for(var i = 0, j = propertyArray.length; i < j; i++){
				if(propertyArray[i].propertykey == $(p).val()){
					if(propertyArray[i].isenum == 1){
						if(property.length == 0)
							break;
						var appendStr = "<select name='strucVo.detailList.rowList.value' class='value'><option>��ѡ��</option>";
						var edata = propertyArray[i].edata;
						for(var k = 0, l = edata.length; k < l; k++){
							if(edata[k].code == $(property).val())
								appendStr += "<option selected value=" + edata[k].code + ">" + edata[k].name + "</option>";
							else appendStr += "<option value=" + edata[k].code + ">" + edata[k].name + "</option>";
						}
						appendStr += "</select>";
						$(property).parent().empty().append(appendStr);
						if($(property).val() == null | $(property).val() == "");
							$(p).parent().parent().find("select[name='strucVo.detailList.rowList.secondcal']").find("option[value='==']").attr("selected",true);
				}
					else{
						var value = "";
						if(property.length != 0 && $(property.attr("id") == "inputFlag"))
							value = $(property).val().replace(/��ѡ��/g,'');
						var appendStr = "<input type='text' name='strucVo.detailList.rowList.value' id='inputFlag' class='value' value=" + value +" >";
						$(p).parent().parent().find(".value").parent().empty().append(appendStr);
					}
				}
			}
		}
	}
	//����������input,select������sturts���ύҪ��
	function resetName(){
		$(".position").find(".ruletable").each(function(index){
			$(this).find("input[type='text'],input[type='hidden'],select").each(function(){
				var name = $(this).attr("name");
				var nameArray = name.split(".");
				name = "";
				nameArray[1] = "detailList[" + index + "]";
				for(var i = 0, j = nameArray.length; i < j; i++){
					if(i != j - 1)
						name += nameArray[i] + ".";
					else name += nameArray[i];
				}
				$(this).attr("name", name);
			})
			var trLength = $(this).find("tr").length;
			$(this).find("tr").each(function(trIndex){
				if(trIndex > 0 && trIndex < trLength - 1){
					$(this).find("input[type='text'],input[type='hidden'],select").each(function(){
						var name = $(this).attr("name");
						var nameArray = name.split(".");
						name = "";
						nameArray[2] = "rowList[" + (trIndex - 1) + "]";
						for(var i = 0, j = nameArray.length; i < j; i++){
							if(i != j - 1)
								name += nameArray[i] + ".";
							else name += nameArray[i];
						}
						$(this).attr("name", name);
					})
				}
			})
		})
	}
</script>
</html>
</html:html>