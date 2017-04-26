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
<TITLE>��������ά��</TITLE>
<common:theme />
</HEAD>
<script type="text/javascript">
	
</script>
<BODY>
	<h1 style="text-align: center; font-size: 18px">��������ά��</h1>
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
				<td>���ö���:</td>
				<td colspan="3"><select name="strucVo.stRuleVo.objectcode"
					onchange="objectChange()" id="object">
					<logic:iterate id="object" name="stSingleRuleForm"
					property="objectList" indexId="indexId">
						<option value="<bean:write name='object' property='objectcode'/>" 
						<logic:equal name="object" property="objectcode" value="1">
							selected
						</logic:equal>
						>
						<bean:write name='object' property='objectname'/>
						</option>
					</logic:iterate>
				</select></td>
			</tr>
		</table>
		</div>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<div style="text-align: right ;height : 30px">
			<input type="button" value="���һ���������" onClick="addDetail()" />
		</div>
		<div class="position" style="display:none">
			<div id="backupTable">
				<table style="text-align: center;margin-top:15px" class="ruletable grid">
					<input type="hidden" name="strucVo.detailList.preOrRe"
						value="1" />
					<tr>
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
							<input type="hidden" class="propertyinput" />
							<select name="strucVo.detailList.rowList.propertycode" onchange="propertyChange(this)">
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
						<td style="white-space:nowrap"><input type="text"
							name="strucVo.detailList.rowList.value" class="value" id="inputFlag"/></td>
						<td>
							<select name="strucVo.detailList.rowList.isright">
								<option value=""> </option>
								<option value=")">)</option>
							</select>
						</td>
						<td><input type="button" value="���" onclick="addrow(this)" />
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
						<td class="property">
							<input type="hidden" class="propertyinput" />
							<select name="strucVo.detailList.rowList.propertycode"  onchange="propertyChange(this)">
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
						<td style="white-space:nowrap"><input type="text"
							name="strucVo.detailList.rowList.value" class="value" id="inputFlag"/></td>
						<td>
							<select name="strucVo.detailList.rowList.isright">
								<option value=""> </option>
								<option value=")">)</option>
							</select>
						</td>
						<td><input type="button" value="���" onclick="addrow(this)" />
							<input type="button" value="ɾ��" onclick="deleterow(this)" /></td>
					</tr>
					<tr>
						<td>���幫ʽ:</td>
						<td id="formulaDetail" colspan="3"></td>
						<td>��λȨ��</td>
						<td colspan="2" class="result">
						 	<param:select style="width:150px" type="cmiscc.postPower" nullStr="-��ѡ��-"
								property="struccode"  name="stSingleRuleForm"
								label="��λȨ��" styleId="" empty="true"  />
						</td>
					</tr>
				</table>
			</div>
							<logic:iterate id="detail" name="stSingleRuleForm"
					property="strucVo.detailList" indexId="indexId">
					<table style="text-align: center;margin-top:10px;" class="ruletable grid">
						<input type="hidden" name="strucVo.detailList.preOrRe"
							value="1" />
						<tr>
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
										<input type="hidden"
											name="strucVo.detailList.rowList.firstcal" />
									</logic:equal> <logic:notEqual value="0" name="rowindexId">
										<select name="strucVo.detailList.rowList.firstcal">
											<option value="">��</option>
											<option value=">" <logic:equal value=">" name="row" property="firstcal">selected</logic:equal>>></option>
											<option value=">=" <logic:equal value=">=" name="row" property="firstcal">selected</logic:equal>>>=</option>
											<option value="<" <logic:equal value="<" name="row" property="firstcal">selected</logic:equal>><</option>
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
								<td class="property">
									<input type="hidden" class="propertyinput" value="<bean:write name='row' property='propertycode'/>" />
								<select name="strucVo.detailList.rowList.propertycode"  onchange="propertyChange(this)">
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
									name="strucVo.detailList.rowList.value" class="value" id="inputFlag"/>
									</td>
								<td>
									<select name="strucVo.detailList.rowList.isright">
										<option value=""> </option>
										<option value=")" <logic:equal value=")" name="row" property="isright">selected</logic:equal>>)</option>
									</select>
								</td>
								<td><input type="button" value="���" onclick="addrow(this)" />
									<logic:equal value="0" name="rowindexId">
										<input type="button" value="ɾ��ȫ��" onclick="deleteall(this)" />
									</logic:equal> <logic:notEqual value="0" name="rowindexId">
										<input type="button" value="ɾ��" onclick="deleterow(this)"
									</logic:notEqual></td>
							</tr>
						</logic:iterate>
						<tr>
							<td style="width:10%">���幫ʽ:</td>
							<td id="formulaDetail" style="word-wrap:break-word;word-break:break-all;white-space:normal;width:auto;" colspan="3"></td>
							<td>��λȨ��</td>
						<td colspan="2" class="result">
						 	<param:select style="width:150px" type="cmiscc.postPower" nullStr="-��ѡ��-"
								property="ruleresult"  name="detail"
								label="��λȨ��" styleId="" indexed="true"/>
						</td>
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
	//�����λȨ��
	var post = new Array();
	$("#backupTable").find(".result").find("select").find("option").each(function(index){
		var tem = {};
		var val = $(this).val();
		var text = $(this).html();
		tem[0] = val;
		tem[1] = text;
		post[index] = tem;
	})
	//�������������
	var row = $(".row").html();
	//���������������
	var propertyArray;
	//�����Ʒ����
	var productData = null;
	//�����ƷС������
	var productLittleData = null;
	//�����Ʒ����
	$(".row").remove();
	//����ѡ���Ʒ��λ��
	var productPosition;
	//���������������
	var ruletables;
	//���ڻ�ȡ����select onchange֮ǰ��ֵ
	var objectOldVal = $("#object").val();
	var showText = $("#showText").val();
	$(function(){
		resetResultName();
		ruletable = $(".position").find("#backupTable").html();
		$(".ruletable:first").remove();
		selectObject();
		getPropertyData();
		if(showText != true && showText != 'true'){
			setInterval(function(){
				displayFormula();
			},100);
		}
		
	})
	//����result����
	function resetResultName(){
		$(".result").each(function(){
			$(this).find("select").attr("name","strucVo.detailList.ruleresult");
		})
	}
	//��Ʒѡ��ص�����
	function callback(code,name,parentcode,parentname){
		if(parentname == "" || parentname == null){
			$(productPosition).find("#productLittleName").val(name);
		}
		else{
			$(productPosition).find("#productName").val(parentname + "-" + name);
		}
		if(parentcode == "" || parentcode == null){
			$(productPosition).find("#productLittleCode").val(code);
		}
		else{
			$(productPosition).find("#productCode").val(parentcode + "$$" + code);
		}
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
	}
	//���һ���������
	function addDetail() {
		$(".position").append(ruletable);
		resetSingleProperty($(".position").find(".ruletable:last").find(".property"));
		window.parent.resetHeight();
		resetResultName();
	}
	//���һ��
	function addrow(p) {
		$(p).parent().parent().after("<tr>" + row + "</tr>");
		resetSingleProperty($(p).parent().parent().next().find(".property"));
		window.parent.resetHeight();
	}
	//ɾ��һ��
	function deleterow(p) {
		var tmp = $(p).parent().parent().parent();
		$(p).parent().parent().remove();
		$(tmp).find(".value").each(function(){
			if($(this).val() == "zdh_zbkhjl"){
				powerRelation(this);
			}else if( $(this).val() == "zdh_zhfxjl"){
				powerRelation(this);
			}else if( $(this).val() == "zdh_fxjl" ){
				powerRelation(this);
			}
		})
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
		var flag = true;
		var reg = /^[0-9]*$/;
		$("select[name='strucVo.detailList.ruleresult']").each(function(){
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
		if(objectcode != null && objectcode != ''){
			$.ajax({
				type:"POST",
				datatype:"json",
				data:{"strucVo.stRuleVo.objectcode":objectcode},
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
							$.ajax({
								type:"POST",
								datatype:"json",
								url:"<%=request.getContextPath()%>/st/StRuleMaintain.do?operAtt=queryLittleProduct",
								data:{modelcode:modelcode},
								success:function(data){
									productLittleData = data;
									resetPropertyValue();
									if(showText == true | showText == 'true')
										displayText();
									$(".position").show();
								}
							})
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
	//������ƷС��ѡ��ҳ��
	function popupLittleProduct(p){
		var modelcode = $("input[name='modelcode']").val();
		var treeUrl = "<%=request.getContextPath()%>/st/StRuleMaintain.do?operAtt=queryLittleProduct";
		var url = "<%=request.getContextPath()%>" + "/st/StRuleMaintain.do?operAtt=popupProduct&modelcode=" + modelcode + "&treeUrl=" + treeUrl;
		window.open(url, 'newwindow', 'width=800,height=600,scrollbars=yes,resizable=yes,left=300,top=100');
		productPosition = $(p).parent();
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
		//���Ըı䣬��̬�޸�������ʽ
	function propertyChange(p){
		var property = $(p).parent().parent().find(".value");
		if($(p).val() == "productCode"){
			var productName = "";
			var proValue = $(property).val().split("\$\$");
			if(property.length != 0 && proValue != null && proValue != ""){
				var productHItems = productData.items;
				for(var i = 0, j = productHItems.length; i < j; i++){
					var productItems = productHItems[i].children;
					if(productHItems[i].code == proValue[0]){
						productName += productHItems[i].name;
						for(var k = 0, l = productItems.length; k < l; k++){
							if(productItems[k].code == proValue[1]){
								productName += "-" + productItems[k].name;
								break;
							}
						}
					}
				}
			}
			var appendStr = "<input type='text' readonly id='productName' class='value' value=" + productName + ">" +
							"<img src='<%=request.getContextPath()%>/skins/images/search.gif' onclick='popupProduct(this)' />" +
							"<input type='hidden' id='productCode' name='strucVo.detailList.rowList.value' class='value' value=" + $(property).val() +">";
			$(p).parent().parent().find(".selectClass,.value").parent().empty().append(appendStr);
		}
		else if($(p).val() == "littleProduct"){
			var productName = "";
			var proValue = $(property).val();
			if(property.length != 0 && proValue != null && proValue != ""){
				var productHItems = productLittleData.items;
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
			var appendStr = "<input type='text' readonly id='productLittleName' class='value' value=" + productName + ">" +
							"<img src='<%=request.getContextPath()%>/skins/images/search.gif' onclick='popupLittleProduct(this)' />" +
							"<input type='hidden' id='productLittleCode' name='strucVo.detailList.rowList.value' class='value' value=" + proValue +">";
			$(p).parent().parent().find(".selectClass,.value").parent().empty().append(appendStr);
		}
		else{
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
	//Ȩ�޹���
	function powerRelation(p) {
		var leng = 0;
		$(p).parent().parent().parent().find("select[name='strucVo.detailList.rowList.propertycode']").each(function(){
			if($(this).val() == "branchPost"){
				leng = leng + 1;
			}
		})
	    if(leng==1){
			if($(p).val() == "zdh_zbkhjl"){
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").children().remove();
				for(var i=0 ; i<post.length ; i++){
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").append("<option value='"+post[i][0]+"'>"+post[i][1]+"</option>")
				} 
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='201']").remove();
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='301']").remove();
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='302']").remove();
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='303']").remove();
			}else if($(p).val() == "zdh_zhfxjl"){
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").children().remove();
				for(var i=0 ; i<post.length ; i++){
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").append("<option value='"+post[i][0]+"'>"+post[i][1]+"</option>")
				} 
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='101']").remove();
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='102']").remove();
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='301']").remove();
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='302']").remove();
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='303']").remove();
			}else if($(p).val() == "zdh_fxjl"){
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").children().remove();
				for(var i=0 ; i<post.length ; i++){
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").append("<option value='"+post[i][0]+"'>"+post[i][1]+"</option>")
				} 
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='101']").remove();
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='102']").remove();
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='201']").remove();
			}else{
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").children().remove();
				for(var i=0 ; i<post.length ; i++){
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").append("<option value='"+post[i][0]+"'>"+post[i][1]+"</option>")
				} 
			}
	    }else if(leng==2){
	    	var ar = new Array() 
	    	var inde = 0;
	    	$(p).parent().parent().parent().find("select[name='strucVo.detailList.rowList.propertycode']").each(function(){
				if($(this).val() == "branchPost"){
					ar[inde] = $(this).parent().parent().find("td:eq(4)").find("select").val();
					inde = inde + 1;
				}
			})
	    	if(ar[0] == ar[1]){
	    		if(ar[0] == "zdh_zbkhjl"){
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").children().remove();
					for(var i=0 ; i<post.length ; i++){
						$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").append("<option value='"+post[i][0]+"'>"+post[i][1]+"</option>")
					} 
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='201']").remove();
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='301']").remove();
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='302']").remove();
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='303']").remove();
				}else
				if(ar[0] == "zdh_zhfxjl"){
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").children().remove();
					for(var i=0 ; i<post.length ; i++){
						$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").append("<option value='"+post[i][0]+"'>"+post[i][1]+"</option>")
					} 
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='101']").remove();
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='102']").remove();
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='301']").remove();
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='302']").remove();
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='303']").remove();
				}else
				if(ar[0] == "zdh_fxjl"){
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").children().remove();
					for(var i=0 ; i<post.length ; i++){
						$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").append("<option value='"+post[i][0]+"'>"+post[i][1]+"</option>")
					} 
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='101']").remove();
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='102']").remove();
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").find("option[value='201']").remove();
				}else{
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").children().remove();
					for(var i=0 ; i<post.length ; i++){
						$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").append("<option value='"+post[i][0]+"'>"+post[i][1]+"</option>")
					} 
				}
	    	}else{
	    		$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").children().remove();
				for(var i=0 ; i<post.length ; i++){
					$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").append("<option value='"+post[i][0]+"'>"+post[i][1]+"</option>")
				} 
	    	}
	    }else if(leng > 2){
	    	$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").children().remove();
			for(var i=0 ; i<post.length ; i++){
				$(p).parent().parent().parent().find("tr:last").find("td:last").find("select").append("<option value='"+post[i][0]+"'>"+post[i][1]+"</option>")
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