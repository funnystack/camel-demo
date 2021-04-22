<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>API使用</title>
<meta name="decorator" content="default" />
<%@include file="/WEB-INF/views/include/treeview.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	var setting = {
			check:{enable:false,nocheckInherit:true},
			view:{selectedMulti:false},
			data:{
				simpleData:{enable:true}
			},
			callback:{
				onClick:showApi
			}
		};
		
		// 类别节点
		var zNodes=[
			{
				name:"API",
				id:"0",
				open:true,
				children: [
			    		{
		    				name:"如何自定义组件图标?",
		    				id:"addComponent",
		    				open:true,
			    		},{
		    				name:"如何自定义组件背景色?",
		    				id:"addComponent",
		    				open:true,
			    		},{
		    				name:"如何添加自定义组件?",
		    				id:"addComponent",
		    				open:true,
			    		},{
		    				name:"如何进行自定义持久化?",
		    				id:"addComponent",
		    				open:true,
			    		},{
		    				name:"如何改为分布式接入zookeeper?",
		    				id:"addComponent",
		    				open:true,
			    		},{
		    				name:"如何自定义路由模板?",
		    				id:"addComponent",
		    				open:true,
			    		},{
		    				name:"如何快速改造为满足需求的项目?",
		    				id:"addComponent",
		    				open:true,
			    		}
				]
			}];
		// 初始化树结构
		var tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);
		// 默认展开全部节点
		tree.expandAll(true);
		
		function showApi(event, treeId, treeNode){
			if(treeNode.id == "addComponent"){
				$("#procFrame").attr("src","${ctx }/camelv/api/addComponent");
			}
		}
});
</script>
</head>
<body>
	<div style="display:flex;" class="tree_wrap">
		<div id="menuTree" class="ztree" style="margin-top:3px;display:flex;"></div>
		<div style="display:flex; flex:1; margin-left: 10px;">
			<iframe id="procFrame" name="procFrame" src="" style="overflow: visible;margin-left: 10px;"
			scrolling="yes" frameborder="no" width="100%" height="680"></iframe>
		</div>
	</div>
</body>
</html>