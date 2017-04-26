<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://www.cvicse.com/tags-common" prefix="common"%>
<%@ taglib uri="http://www.cvicse.com/tags-webui" prefix="ui"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<ui:dojo djConfig="parseOnLoad:true">
</ui:dojo>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>准入规则维护</title>
<script src="<%=request.getContextPath()%>/js/jquery-1.4.2.js"
	language="javascript">
	
</script>
<script type="text/javascript">
	dojo.require("dijit.Menu");
	dojo.require("dijit.MenuItem");
	dojo.require("dijit.tree.ForestStoreModel");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.Tree");
</script>
<body>
	<div id="treeContainer">
		<div dojoType="dojo.data.ItemFileReadStore" jsId="menuContinentStore"
			url="<%=request.getContextPath()%>/st/StLimitIndustry.do?operAtt=queryAllTree"></div>
		<div dojoType="dijit.tree.ForestStoreModel" jsId="menuContinentModel"
			store="menuContinentStore" rootId="identifier" rootLabel='行业选择'
			childrenAttrs="children"></div>
		<div dojoType="dijit.Tree" id="menuTree" model="menuContinentModel"
			showRoot="true" openOnClick="true">
			<script type="dojo/connect">
			var modelcode = $("#modelcode").val();
			var classification = $("#classification").val();
			var handle;
			var tree = dijit.byId("menuTree");
			tree.placeAt(dojo.byId("treeContainer"));
			tree.connect(tree, "onClick",clickTreeNode);
			function clickTreeNode(item,node,e){
				if(window.opener){
					window.opener.callback2(item.code, item.name, item.parentcode);
				}
				window.top.close();
			}
		</script>
		</div>
	</div>
</body>
</html>