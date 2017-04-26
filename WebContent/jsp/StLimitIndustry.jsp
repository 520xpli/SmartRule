<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://www.cvicse.com/tags-common" prefix="common"%>
<%@ taglib uri="http://www.cvicse.com/tags-webui" prefix="ui"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%">
<ui:dojo djConfig="parseOnLoad:true">
</ui:dojo>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta HTTP-EQUIV="paragma" content="no-cache">
<meta HTTP-EQUIV="Cache-Control" content="no-cache, must-revalidate">
<meta HTTP-EQUIV="expired" content="0">
<title>限控行业维护</title>
<script src="<%=request.getContextPath()%>/js/jquery-1.4.2.js"
	language="javascript">
	
</script>
<script type="text/javascript">
	dojo.require("dijit.Menu");
	dojo.require("dijit.MenuItem");
	dojo.require("dijit.tree.ForestStoreModel");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.Tree");
	var addItem;
	var deleteItem;
	function addNode(){
		if(addItem == undefined || addItem == null || addItem.children != undefined){
			alert("请选中一个具体的行业");
			return;
		}
		$.ajax({
			type:"post",
			data:{code:addItem.code},
			url:"<%=request.getContextPath()%>/st/StLimitIndustry.do?operAtt=add",
			success:function(data){
				var limitTree = dijit.byId("limitTree");
				refreshTree();
			}
		})
	}
	function deleteNode(){
		if(deleteItem == undefined || deleteItem == null || deleteItem.children != undefined){
			alert("请选中一个具体的限控行业");
			return;
		}
		$.ajax({
			type:"post",
			data:{code:deleteItem.code},
			url:"<%=request.getContextPath()%>/st/StLimitIndustry.do?operAtt=delete",
			success:function(data){
				var limitTree = dijit.byId("limitTree");
				refreshTree();
			}
		})
	}
	function refreshTree(){
		   dijit.byId("limitTree").dndController.selectNone();
	       dijit.byId("limitTree").model.store.clearOnClose = true;
	       dijit.byId("limitTree").model.store.url = "<%=request.getContextPath()%>/st/StLimitIndustry.do?operAtt=queryLimitIndustryTree&currentTime=" + new Date().getTime();
	       dijit.byId("limitTree").model.store.close();
	       dijit.byId("limitTree").itemNodesMap = {};
	       dijit.byId("limitTree").rootNode.state = "UNCHECKED";
	       dijit.byId("limitTree").model.root.children = null;
	       dijit.byId("limitTree").rootNode.destroyRecursive();
	       dijit.byId("limitTree").model.constructor(dijit.byId("limitTree").model);
	       dijit.byId("limitTree").postMixInProperties();
	       dijit.byId("limitTree")._load();
		}
	
</script>
<body style="height:100%">
	<div id="allIndustryContainer" style="width:40%;float:left;height:100%;overflow:scroll;border:1px solid gray">
		<div style="text-align:left;font-size:14px;margin-left:40px;margin-top:10px">所有行业</div>
		<div dojoType="dojo.data.ItemFileReadStore" jsId="allStore"
			url="<%=request.getContextPath()%>/st/StLimitIndustry.do?operAtt=queryAllTree&currentTime=<bean:write name='stLimitIndustryForm' property='currentTime'/>"></div>
		<div dojoType="dijit.tree.ForestStoreModel" jsId="allModel"
			store="allStore" rootId="identifier" rootLabel=""
			childrenAttrs="children"></div>
		<div dojoType="dijit.Tree" id="allTree" model="allModel"
			showRoot="false" openOnClick="true">
			<script type="dojo/connect">
			var allTree = dijit.byId("allTree");
			allTree.connect(allTree, "onClick",clickTreeNode);
			function clickTreeNode(item,node,e){
				addItem = item;
			}
			function addNode(){
				$.ajax({
					type:"post",
					data:{code:item.code},
					url:"<%=request.getContextPath()%>/st/StLimitIndustry.do?operAtt=add",
					success:function(data){
						var limitTree = dijit.byId("limitTree");
						refreshTree();
						selectNode(item.id);
					}
				})
			}
	function refreshTree(){
		dijit.byId("limitTree").dndController.selectNone();
		dijit.byId("limitTree").model.store.clearOnClose = true;
		dijit.byId("limitTree").model.store.url = "<%=request.getContextPath()%>/st/StLimitIndustry.do?operAtt=queryLimitIndustryTree&currentTime=" + new Date().getTime();
		dijit.byId("limitTree").model.store.close();
		dijit.byId("limitTree").itemNodesMap = {};
		dijit.byId("limitTree").rootNode.state = "UNCHECKED";
		dijit.byId("limitTree").model.root.children = null;
		dijit.byId("limitTree").rootNode.destroyRecursive();
		dijit.byId("limitTree").model.constructor(dijit.byId("limitTree").model);
		dijit.byId("limitTree").postMixInProperties();
		dijit.byId("limitTree")._load();
	}
	function recursiveHunt(lookfor, model, buildme, item){
		var id = model.getIdentity(item);
		if(id == lookfor){
			return bulidme;
		}
		for(var idx in item.children){
			var buildmebranch = buildme.slice(0);
			var r = recursiveHunt(lookfor, model, buildmebranch, item.children[idx]);
			if(r){ return r;}
		}
		return undefined;
	}
	function selectTreeNodeById(tree, lookfor){
		var buildme = new Array();
		var result = recursiveHunt(lookfor, tree.model, buildme, tree.model.root);
		if(result && result.length > 0)
			tree.attr('path', result);
	}
	function selectNode(lookfor){
		var limitTree = dijit.byId("limitTree");
		selectTreeNodeById(limitTree, lookfor);
	}
	
	function getParentItemById(item, id){
		var limitTree = dijit.byId("limitTree");
		var model = limitTree.model;
		if(model.getIdentity(item) == id)
			return item;
		if(item != null){
			for(var idx in item.children){
				var r = getParentItemById(item.children[idx], id);
				if(r){return r;}
			}
		}
		return undefined;
	}
	
		</script>
		</div>
	</div>
	<div style="float:left;width:20%">
		<input type="button" onclick="addNode()" value="增加" style="position:absolute;top:40%;left:43%;width:5%"/>
		<input type="button" onclick="deleteNode()" value="删除" style="position:absolute;top:50%;left:43%;width:5%"/>
	</div>
	<div id="limitIndustryContainer" style="float:left;height:100%;overflow:scroll;width:40%;margin-left:10%;border:1px solid gray">
		<div dojoType="dojo.data.ItemFileReadStore" jsId="limitStore"
			url="<%=request.getContextPath()%>/st/StLimitIndustry.do?operAtt=queryLimitIndustryTree&currentTime=<bean:write name='stLimitIndustryForm' property='currentTime'/>"></div>
		<div style="text-align:left;font-size:14px;margin-left:40px;margin-top:10px">限控行业</div>
		<div dojoType="dijit.tree.ForestStoreModel" jsId="limitModel"
			store="limitStore" rootId="identifier" rootLabel=""
			childrenAttrs="children"></div>
		<div dojoType="dijit.Tree" id="limitTree" model="limitModel"
			showRoot="false" openOnClick="true">
			<script type="dojo/connect">
			var limitTree = dijit.byId("limitTree");
			limitTree.connect(limitTree, "onClick",clickNode);
			function clickNode(item,node,e){
				deleteItem = item;
			}
			function refreshTree(){
			   dijit.byId("limitTree").dndController.selectNone();
		       dijit.byId("limitTree").model.store.clearOnClose = true;
		       dijit.byId("limitTree").model.store.url = "<%=request.getContextPath()%>/st/StLimitIndustry.do?operAtt=queryLimitIndustryTree&currentTime=" + new Date().getTime();
		       dijit.byId("limitTree").model.store.close();
		       dijit.byId("limitTree").itemNodesMap = {};
		       dijit.byId("limitTree").rootNode.state = "UNCHECKED";
		       dijit.byId("limitTree").model.root.children = null;
		       dijit.byId("limitTree").rootNode.destroyRecursive();
		       dijit.byId("limitTree").model.constructor(dijit.byId("limitTree").model);
		       dijit.byId("limitTree").postMixInProperties();
		       dijit.byId("limitTree")._load();
			}
		</script>
		</div>
	</div>
</body>
</html>