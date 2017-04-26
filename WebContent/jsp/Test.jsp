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
<TITLE>数据测试</TITLE>
<common:theme />
</HEAD>
<script type="text/javascript">
	
</script>
<BODY>
<form>
	<table>
		<tr>
			<td>选择规则类型</td>
			<td>
				<select name="modelcode" onchange="ruleTypeChange()" id="ruleType">
					<option value="">请选择</option>
					<option value="ST2016070115">准入</option>
					<option value="ST2016070416">初审</option>
					<option value="ST2016070417">终审</option>
					<option value="ST201606281">额度计算</option>
					<option value="ST201606298">评分卡</option>
				</select>
			</td>
		</tr>
	</table>
	<input type="button" value="添加一条数据" onclick="addData()"/>
	<table id="amount" style="text-align: center;margin-top:10px;display:none" class="ruletable grid">
		<tr>
			<td>担保方式</td>
			<td>
				<select name="guaranteeType">
					<option value="C100">信用</option> 
					<option value="C101">保证</option> 
					<option value="C102">抵押</option> 
					<option value="C103">质押</option> 
				</select>
			</td>
		</tr>
	</table>
	<table id="rate" style="text-align: center;margin-top:10px;display:none" class="ruletable grid">
		<tr>
			<td>行业类型</td>
			<td><input type="hidden" name="industry" /><input type="text" readonly id="industryName" /><input type="button" value="选择" onclick="popup()"/></td>
			<td>申请金额</td>
			<td><input type="text" name="applyLimit" /></td>
		</tr>
	</table>
	<div id="position">
	<table id="backup" style="text-align: center;margin-top:10px" class="ruletable grid">
		<tr>
			<td>
				选择一个对象
			</td>
			<td colspan="3">
				<select name="objectCode" class="objectCode" onchange="objectChange(this)">
					
				</select>
			</td>
			<td><input type="button" value="删除全部" onclick="deleteAll(this)"/></td>
		</tr>
		<tr id="row">
			<td>选择对象属性</td>
			<td>
				<select name="key" class="property" onchange="propertyChange(this)" >
				
				</select>
			</td>
			<td>值</td>
			<td><input type="text" name="value" class="value" id="inputFlag"/></td>
			<td><input type="button" value="增加" onclick="addRow(this)"/><input type="button" value="删除" onclick="deleteRow(this)"/></td>
		</tr>
	</table>
	</div>
</form>
	<input type="button" value="提交" onclick="commit()" />
</BODY>
<script type="text/javascript">
	var table = $("#position").html();
	var row = $("#row").html();
	var productData;
	var objectArray;
	var productPosition;
	function popup(){
		var url = "<%=request.getContextPath()%>" + "/st/StTest.do?operAtt=industry";
		window.open(url, 'newwindow', 'width=800,height=600,scrollbars=yes,resizable=yes,left=300,top=100');
	}
	function callback2(code,name,parentcode){
		$("input[name='industry']").val(code);
		$("#industryName").val(name);
	}
	function callback(code,name,parentcode){
		$(productPosition).find("#productName").val(name);
		$(productPosition).find("#productCode").val(code);
		$(productPosition).append("<input type='hidden' name='key' value='smallProduct' class='property'/>");
		$(productPosition).append("<input type='hidden' name='value' value='" + parentcode + "' class='value'/>");
		
	}
	function commit(){
		resetName();
		$.ajax({
			type:"POST",
			datatype:"json",
			data:$("form:first").serialize(),
			url:"<%=request.getContextPath()%>/st/StTest.do?operAtt=cal",
			success:function(data){
				alert("结果：" + data.result + ";编码：" + data.code + "描述：" + data.desc);
				if(data.checkLimit != undefined) 
					alert("核定额度:" + data.checkLimit)
			}
		})
	}
	function resetName(){
		$("#position").find("table").each(function(index){
			$(this).find(".objectCode").attr("name","objectList[" + index + "].objectCode");
			$(this).find(".property").each(function(trIndex){
				$(this).attr("name","objectList[" + index + "].propertyList[" + trIndex + "].key");
			})
			$(this).find(".value").each(function(trIndex){
				$(this).attr("name","objectList[" + index + "].propertyList[" + trIndex + "].value");
			})
		})
	}
	function ruleTypeChange(){
		var modelcode = $("#ruleType").val();
		if(modelcode == "ST201606298"){
			$("#rate").show();
			$("#amount").hide();
		}
		else if(modelcode == "ST201606281"){
			$("#rate").hide();
			$("#amount").show();
			
		}
		else{
			$("#amount").hide();
			$("#rate").hide();
		}
		$.ajax({
			type:"POST",
			datatype:"json",
			data:{modelcode:modelcode},
			url:"<%=request.getContextPath()%>/st/StTest.do?operAtt=queryObject",
			success:function(data){
				objectArray = data.result;
				$("#position").empty();
				addData();
				var str = "<option value=''>请选择</option>";
				for(var i = 0, j = objectArray.length; i < j; i++){
					str += "<option value='" + objectArray[i].objectcode + "'>" + objectArray[i].objectname + "</option>";
				}
				$(".objectCode").empty().append(str);
				
			}
		})
	}
	function objectChange(p){
		var objectcode = $(p).val();
		var modelcode = $("#ruleType").val();
		$.ajax({
			type:"POST",
			datatype:"json",
			data:{"strucVo.stRuleVo.objectcode":objectcode},
			url:"<%=request.getContextPath()%>/st/StRuleMaintain.do?operAtt=queryProerty",
			success:function(data){
				var propertyArray = data.propertyData;
				var modelcode = $("input[name='modelcode']").val();
				var str = "<option value=''>请选择</option>";
				for(var i = 0, j = propertyArray.length; i < j; i++){
					str += "<option value='" + propertyArray[i].propertykey + "'>" + propertyArray[i].propertyname + "</option>";
				}
				$(p).parent().parent().parent().find(".property:last").empty().append(str);
				$(p).parent().parent().parent().find(".property:last").css("width",$(p).parent().parent().parent().find(".property:first").width());
				$.ajax({
					type:"POST",
					datatype:"json",
					url:"<%=request.getContextPath()%>/st/StRuleMaintain.do?operAtt=queryProduct",
					data:{modelcode:modelcode},
					success:function(data){
						productData = data;
					}
				})
			}
		})
	}
	function propertyChange(p){
		var propertyArray;
		var objectcode = $(p).parent().parent().parent().find(".objectCode").val();
		$.ajax({
			type:"POST",
			datatype:"json",
			data:{"strucVo.stRuleVo.objectcode":objectcode},
			url:"<%=request.getContextPath()%>/st/StRuleMaintain.do?operAtt=queryProerty",
			success:function(data){
				propertyArray = data.propertyData;
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
					var appendStr = "<input type='text' readonly id='productName' value=" + productName + ">" +
									"<img src='<%=request.getContextPath()%>/skins/images/search.gif' onclick='popupProduct(this)' />" +
									"<input type='hidden' id='productCode' name='strucVo.detailList.rowList.value' class='value' value=" + proValue +">";
					$(p).parent().parent().find(".selectClass,.value").parent().empty().append(appendStr);
				}else{
					for(var i = 0, j = propertyArray.length; i < j; i++){
						if(propertyArray[i].propertykey == $(p).val()){
							if(propertyArray[i].isenum == 1){
								if(property.length == 0)
									break;
								var appendStr = "<select name='strucVo.detailList.rowList.value' class='value'><option value=''>请选择</option>";
								var edata = propertyArray[i].edata;
								for(var k = 0, l = edata.length; k < l; k++){
									if(edata[k].code == $(property).val())
										appendStr += "<option selected value=" + edata[k].code + ">" + edata[k].name + "</option>";
									else appendStr += "<option value=" + edata[k].code + ">" + edata[k].name + "</option>";
								}
								appendStr += "</select>";
								$(property).parent().empty().append(appendStr);
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
		})
		
	}
	function deleteAll(p){
		$(p).parent().parent().parent().parent().remove();
	}
	function deleteRow(p){
		$(p).parent().parent().remove();
	}
	function addRow(p){
		$(p).parent().parent().parent().append("<tr>" + row + "</tr>");
		objectChange($(p).parent().parent().parent().find(".objectCode"));
	}
	function addData(){
		$("#position").append(table);
		var str = "<option value=''>请选择</option>";
		for(var i = 0, j = objectArray.length; i < j; i++){
			str += "<option value='" + objectArray[i].objectcode + "'>" + objectArray[i].objectname + "</option>";
		}
		$("#position").find("table:last").find(".objectCode").empty().append(str);
	}
	function popupProduct(p){
		var modelcode = $("#ruleType").val();
		var treeUrl = "<%=request.getContextPath()%>/st/StRuleMaintain.do?operAtt=queryProduct&modelcode=" + modelcode;
		var url = "<%=request.getContextPath()%>" + "/st/StRuleMaintain.do?operAtt=popupProduct&modelcode=" + modelcode + "&treeUrl=" + treeUrl;
		window.open(url, 'newwindow', 'width=800,height=600,scrollbars=yes,resizable=yes,left=300,top=100');
		productPosition = $(p).parent();
	}
</script>
</html>
</html:html>