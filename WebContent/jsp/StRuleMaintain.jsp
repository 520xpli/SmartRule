<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>规则维护</title>

<script src="<%=request.getContextPath()%>/js/jquery.min.js"
	language="javascript"></script>
	
<script src="<%=request.getContextPath()%>/js/dojo-release-1.6.5/dojo/dojo.js" type="text/javascript" djConfig="parseOnLoad:true"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/js/dojo-release-1.6.5/dojo/resources/dojo.css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/dojo-release-1.6.5/dijit/themes/claro/claro.css"></link>
<script type="text/javascript">
	dojo.require("dijit.Menu");
	dojo.require("dijit.MenuItem");
	dojo.require("dijit.tree.ForestStoreModel");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.Tree");
	function resetHeight(){
		var ifm = document.getElementById("iframe");
		var subWeb = document.frames ? document.frames["iframe"].document : ifm.contentDocument;
		$("#iframeContainer").css("height",subWeb.body.scrollHeight + 200 + "px");
	}
	function backPrev(){
		window.location.href = $("#backUrl").val();
	}
	var isShowCode="<bean:write name='stSystemForm' property='isShowCode'/>";
</script>
</head>
<body style="height:100%;margin:0;padding:0;">
	<div id="treeContainer" style="width:25%;float:left;height:95%">
		<div style="height:95%;width:100%;overflow-y:scroll">
		<input
			type="hidden"  id="backUrl"
			value="<bean:write name='stSystemForm' property='backUrl'/>" />
		<input type="hidden" name="modelcode" id="modelcode"
			value="<bean:write name='stSystemForm' property='stModelVo.modelcode'/>" />
		<input
			type="hidden" name="showtext" id="showtext"
			value="<bean:write name='stSystemForm' property='showtext'/>" />
		 <input
			type="hidden" name="classification" id="classification"
			value="<bean:write name='stSystemForm' property='stModelVo.classification'/>" />
		<ul dojoType="dijit.Menu" id="treeMenu" style="display: none">
			<li dojoType="dijit.MenuItem" id="addItem">增加规则</li>
			<li dojoType="dijit.MenuItem" id="deleteItem">删除规则</li>
		</ul>
		<div dojoType="dojo.data.ItemFileReadStore" jsId="menuContinentStore"
			url="<%=request.getContextPath()%>/StRuleMaintain.do?operAtt=querySubTree&modelcode=<bean:write name='stSystemForm' property='stModelVo.modelcode'/>&isShowCode=<bean:write name='stSystemForm' property='isShowCode'/>"></div>
		<div dojoType="dijit.tree.ForestStoreModel" jsId="menuContinentModel"
			store="menuContinentStore" rootId="identifier" rootLabel="<bean:write name='stSystemForm' property='stModelVo.modelname'/>"
			childrenAttrs="children"></div>
		<div dojoType="dijit.Tree" id="menuTree" model="menuContinentModel"
			showRoot="false" openOnClick="true" style="overflow:visible">
			<script type="dojo/connect">
			var menu = dijit.byId("treeMenu");
			var addItem = dijit.byId("addItem");
			var deleteItem = dijit.byId("deleteItem");
			var showtext = $("#showtext").val();
			if(showtext != 'true')
				menu.bindDomNode(this.domNode);
			var modelcode = $("#modelcode").val();
			var classification = $("#classification").val();
			var handle;
			dojo.connect(menu,"_openMyself",this,function(e){
				var tn = dijit.getEnclosingWidget(e.target);
				if(tn.item.children == undefined){
					addItem.connect(addItem,"onClick",function(e){
						$("#iframe").attr("src","<%=request.getContextPath()%>/StRuleMaintain.do?operAtt=editJump&classification="+classification+"&strucVo.parentstruccode="+tn.item.struccode+"&modelcode="+modelcode+"&isShowCode="+isShowCode);
					})
					if(handle != null && handle != undefined){
						deleteItem.disconnect(handle);
					}
					handle = deleteItem.connect(deleteItem,"onClick",function(e){
						$.ajax({
							type:"post",
							datatype:"json",
							data:{struccode:tn.item.struccode},
							url:"<%=request.getContextPath()%>/st/StRuleMaintain.do?operAtt=delete",
							success:function(data){
								alert(data.result);
								location.reload();
							}
						})
					})
					dojo.style(dojo.byId("deleteItem"),"display","");
					dojo.style(dojo.byId("addItem"),"display","none");
				}
				else{
					addItem.connect(addItem,"onClick",function(e){
						$("#iframe").attr("src","<%=request.getContextPath()%>/StRuleMaintain.do?operAtt=editJump&classification="+classification+"&strucVo.parentstruccode="+tn.item.struccode+"&modelcode="+modelcode + "&strucVo.classification=" + tn.item.classification + "&showtext=" + showtext+"&isShowCode="+isShowCode);
					})
					if(handle != null && handle != undefined){
						deleteItem.disconnect(handle);
					}
					dojo.style(dojo.byId("deleteItem"),"display","none");
					dojo.style(dojo.byId("addItem"),"display","");
				}
				console.debug(tn);
				console.debug(tn.item);
			})
			var tree = dijit.byId("menuTree");
			tree.connect(tree, "onClick",clickTreeNode);
			function clickTreeNode(item,node,e){
				if(item.isleaf == 1){
					var currentTime = new Date().getTime();
					$("#iframe").attr("src","<%=request.getContextPath()%>/StRuleMaintain.do?operAtt=editJump&classification="+classification+"&strucVo.parentstruccode="+item.parentcode+"&struccode="+item.struccode+"&modelcode="+modelcode + "&strucVo.classification=" + item.classification + "&showtext=" + showtext + "&currentTime=" + currentTime+"&isShowCode="+isShowCode);
				}
			}
		</script>
		</div>
		</div>
		<div style="text-align:left;margin-left:20px;height:10%">
			<input type="button" name="back" value="返回 " onClick="backPrev()" />
		</div>
	</div>
	<div style="width:75%;float:left;height:1000px;">
		<iframe src="" id="iframe" name="iframe" frameborder="0" scrolling="no" style="width:100%;height:100%;" ></iframe>
	</div>
	<div style="clear:both"></div>
</body>
</html>