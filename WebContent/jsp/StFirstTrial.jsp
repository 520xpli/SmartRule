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
<TITLE>初审维护</TITLE>
<common:theme />
</HEAD>
<script type="text/javascript">
	
</script>
<BODY>
	<h1 style="text-align: center; font-size: 18px">初审<font onclick="window.parent.isShowCode=false">维</font>护</h1>
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
					<td>名称:</td>
					<td><common:text empty="false" label="名称" size="20"
							validator="text(0,80)" name="stSingleRuleForm"
							property="strucVo.strucname" styleId="strucVo.strucname"
							readonly="false" /></td>
					<td>描述:</td>
					<td><common:text empty="false" label="描述" size="20"
							validator="text(0,80)" name="stSingleRuleForm"
							property="strucVo.description" styleId="strucVo.description"
							readonly="false" /></td>
				</tr>
				<tr>
				<td>执行顺序:</td>
				<td><common:text empty="false" label="执行顺序" size="20"
						validator="digit(0,3)" name="stSingleRuleForm"
						property="strucVo.seq" styleId="strucVo.seq" readonly="false" />
				</td>
				<td >拒绝周期（天）:</td>
				<td id="refuseCycle"><common:text empty="false" label="拒绝周期（天）" size="20"
						validator="digit(0,3)" name="stSingleRuleForm"
						property="strucVo.stRuleVo.cycle" styleId="strucVo.cycle"
						readonly="false" /></td>
				</tr>
				<tr>
					<td>提示信息:</td>
					<td ><common:text empty="false" label="描述" size="20" 
						validator="text(0,80)" name="stSingleRuleForm"
						property="strucVo.prompt" styleId="strucVo.prompt"
						readonly="false" /></td>
					<td>适用对象:</td>
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
			<input type="button" value="添加一个具体规则" onClick="addDetail()" />
		</div>
		<div class="position">
			<div id="backupTable">
				<table style="text-align: center; margin-top: 10px"
					class="ruletable grid">
					<input type="hidden" name="strucVo.detailList.preOrRe" value="1" />
					<tr style="background-color:gray">
						<td style="width:10%">行运算</td>
							<td >左括号</td>
							<td style="width:30%">对象属性</td>
							<td >运算方式</td>
							<td >数值</td>
							<td >右括号</td>
							<td>操作</td>
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
								<option value="">请选择</option>
							</select>
						</td>
						<td><select name="strucVo.detailList.rowList.secondcal">
								<option value="">无</option>
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
						<td style="white-space:nowrap"><input type="button" value="添加" onclick="addrow(this)" />
							<input type="button" value="删除全部" onclick="deleteall(this)" /></td>
					</tr>
					<tr class="row">
						<td><select name="strucVo.detailList.rowList.firstcal">
								<option value="">无</option>
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
								<option value="">请选择</option>
						</select></td>
						<td><select name="strucVo.detailList.rowList.secondcal">
								<option value="">无</option>
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
						<td style="white-space:nowrap"><input type="button" value="添加" onclick="addrow(this)" />
							<input type="button" value="删除" onclick="deleterow(this)" /></td>
					</tr>
					<tr>
						<td>具体公式:</td>
						<td id="formulaDetail" colspan="4" style="word-wrap:break-word;word-break:break-all;white-space:normal;width:auto;"></td>
						<td>结果:</td>
						<td ><select name="strucVo.detailList.ruleresult">
								<option>请选择</option>
								<option value="-4">通过</option>
								<option value="-3">分流</option>
								<option value="-2">退回</option>
								<option value="-1">拒绝</option>
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
						<td style="width:10%">行运算</td>
							<td >左括号</td>
							<td style="width:30%">对象属性</td>
							<td >运算方式</td>
							<td >数值</td>
							<td >右括号</td>
							<td>操作</td>
					</tr>
					<logic:iterate id="row" name="detail" property="rowList"
						indexId="rowindexId">
						<tr>
							<td><logic:equal value="0" name="rowindexId">
									<input type="hidden" name="strucVo.detailList.rowList.firstcal" />
								</logic:equal> <logic:notEqual value="0" name="rowindexId">
									<select name="strucVo.detailList.rowList.firstcal">
										<option value="">无</option>
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
									<option value="">请选择</option>
								</select></td>
								<td><select name="strucVo.detailList.rowList.secondcal">
										<option value="">无</option>
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
								<td style="white-space:nowrap"><input type="button" value="添加" onclick="addrow(this)" />
									<logic:equal value="0" name="rowindexId">
										<input type="button" value="删除全部" onclick="deleteall(this)" />
									</logic:equal> <logic:notEqual value="0" name="rowindexId">
										<input type="button" value="删除" onclick="deleterow(this)"
									</logic:notEqual></td>
							</tr>
						</logic:iterate>
						<tr>
							<td class="displayText">具体公式:</td>
							<td id="formulaDetail" colspan="4" style="word-wrap:break-word;word-break:break-all;white-space:normal;width:auto;"></td>
							<td class="displayText">结果:</td>
							<td class="displayText"><select name="strucVo.detailList.ruleresult">
									<option>请选择</option>
									<option value="-4" <logic:equal value="-4" name="detail" property="ruleresult"> selected</logic:equal> >通过</option>
									<option value="-3" <logic:equal value="-3" name="detail" property="ruleresult"> selected</logic:equal> >分流</option>
									<option value="-2" <logic:equal value="-2" name="detail" property="ruleresult"> selected</logic:equal> >退回</option>
									<option value="-1" <logic:equal value="-1" name="detail" property="ruleresult"> selected</logic:equal> >拒绝</option>
							</select></td>
						</tr>
					</table>
				</logic:iterate>
		</div>
		<div style="text-align: center">
			<input type="button" value="提交" onClick="commit()" />
		</div>
	</form>
</BODY>
<script type="text/javascript">
	//缓存规则行数据
	var row = $(".row").html();
	//缓存对象属性数据
	var propertyArray;
	//缓存产品数据
	var productData = null;
	//保存产品数据
	$(".row").remove();
	//保存选择产品的位置
	var productPosition;
	//缓存具体规则表数据
	var ruletable = $(".position").find("#backupTable").html();
	//用于获取对象select onchange之前的值
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
	//设置拒绝周期初始值
	function initRefuseCycle(){
		if($("#refuseCycle").find("input").val() == "")
			$("#refuseCycle").find("input").val("0");
	}
	//产品选择回调函数
	function callback(code,name){
		$(productPosition).find("#productName").val(name);
		$(productPosition).find("#productCode").val(code);
	}
	//选中对象
	function selectObject(){
		var objectCode = $("#objectcode").val();
		$("#object").find("option").each(function(){
			if($(this).val() == objectCode)
				$(this).attr("selected",true);
		})
	}
	//查看
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
	//添加一个具体规则
	function addDetail() {
		$(".position").append(ruletable);
		resetSingleProperty($(".position").find(".ruletable:last").find(".property"));
		window.parent.resetHeight();
	}
	//添加一行
	function addrow(p) {
		$(p).parent().parent().after("<tr>" + row + "</tr>");
		resetSingleProperty($(p).parent().parent().next().find(".property"));
		window.parent.resetHeight();
	}
	//删除一行
	function deleterow(p) {
		$(p).parent().parent().remove();
	}
	//删除一个具体规则
	function deleteall(p) {
		$(p).parent().parent().parent().parent().remove();
	}
	//对象变更
	function objectChange() {
		var ruleIsEmpty = $(".position").find(".ruletable").length == 0;
		if (!ruleIsEmpty) {
			if (confirm("改变对象将清空所有规则，确认改变")) {
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
	//提交表单
	function commit(){
		var name = $("[id='strucVo.strucname']");
		var desc = $("[id='strucVo.description']");
		var seq =  $("[id='strucVo.seq']");
		if(name.val() == ""){
			alert("请输入名称");
			$(name).focus();
			return;
		}
		if(desc.val() == ""){
			alert("请输入描述");
			$(desc).focus();
			return;
		}
		if(seq.val() == ""){
			alert("请输入顺序");
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
					alert("条件数值输入框不能输入非数字字符!");	
					$(this).focus();
				}
			}
		})
		if(!flag)
			return;
		$("input[name='strucVo.detailList.ruleresult']").each(function(){
			if($(this).val() == "" | $(this).val() == null){
				flag = false;
				alert("请输入结果");	
				$(this).focus();
			}
			if(!reg.test($(this).val())){
				flag = false;
				alert("输入结果格式不对");	
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
					alert("登陆超时，请重新登陆");
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
	//重置所有对象属性
	function resetProperty(){
		$(".property").each(function(){
			var code = $(this).find(".propertyinput").val();
			var appendStr = "<option value=''>请选择</option>";
			for(var i = 0, j = propertyArray.length; i < j; i++){
				if(code == propertyArray[i].propertykey)
					appendStr += "<option value='" + propertyArray[i].propertykey + "' selected>" + propertyArray[i].propertyname + "</option>";
				else appendStr += "<option value='" + propertyArray[i].propertykey + "'>" + propertyArray[i].propertyname + "</option>";
			}
			$(this).find("select").empty().append(appendStr);
			$(this).find("select").css("width",$(this).find("select").width());
		})
	}
	//重置单个对象属性
	function resetSingleProperty(p){
		var code = $(p).find(".propertyinput").val();
		var appendStr = "<option value=''>请选择</option>";
		for(var i = 0, j = propertyArray.length; i < j; i++){
			if(code == propertyArray[i].propertykey)
				appendStr += "<option value='" + propertyArray[i].propertykey + "' selected>" + propertyArray[i].propertyname + "</option>";
			else appendStr += "<option value='" + propertyArray[i].propertykey + "'>" + propertyArray[i].propertyname + "</option>";
		}
		$(p).find("select").empty().append(appendStr);
	}
	
	//获取对象属性数据
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
	//显示具体规则公式
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
				formula = formula.replace(/无/g,'');
				formula = formula.replace(/请选择/g,'');
				$(this).find("#formulaDetail").html(formula);
			})
	}
	//重置所有属性后面值
	function resetPropertyValue(){
		$(".property").find("select").each(function(){
			propertyChange(this);
		})
	}
	//弹出产品选择页面
	function popupProduct(p){
		var modelcode = $("input[name='modelcode']").val();
		var treeUrl = "<%=request.getContextPath()%>/st/StRuleMaintain.do?operAtt=queryProduct&modelcode=" + modelcode;
		var url = "<%=request.getContextPath()%>" + "/st/StRuleMaintain.do?operAtt=popupProduct&modelcode=" + modelcode + "&treeUrl=" + treeUrl;
		window.open(url, 'newwindow', 'width=800,height=600,scrollbars=yes,resizable=yes,left=300,top=100');
		productPosition = $(p).parent();
	}
	//属性改变，动态修改输入样式
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
						var appendStr = "<select name='strucVo.detailList.rowList.value' class='value'><option>请选择</option>";
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
							value = $(property).val().replace(/请选择/g,'');
						var appendStr = "<input type='text' name='strucVo.detailList.rowList.value' id='inputFlag' class='value' value=" + value +" >";
						$(p).parent().parent().find(".value").parent().empty().append(appendStr);
					}
				}
			}
		}
	}
	//重命名所有input,select以满足sturts表单提交要求
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