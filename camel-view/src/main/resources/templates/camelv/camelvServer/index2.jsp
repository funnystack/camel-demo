<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/camelv/camelvServer/head.jsp"%>
<!DOCTYPE html>
<html xmlns:v="urn:schemas-microsoft-com:vml">
<head>
<!-- <meta name="decorator" content="default" /> -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>流程图DEMO</title>
	<!--[if lt IE 9]>
	<?import namespace="v" implementation="#default#VML" ?>
	<![endif]-->
	
	<script type="text/javascript">
		var property={
			toolBtns:["servlet","start round mix","end round","task","node","chat","state","plug","join","fork","complex mix"],
			haveHead:true,
			headLabel:true,
			headBtns:["new","open","save","undo","redo","reload","print"],//如果haveHead=true，则定义HEAD区的按钮
			haveTool:true,
		    haveDashed:true,
			haveGroup:true,
			useOperStack:true
		};
		//取代setNodeRemarks方法，采用更灵活的注释配置
		GooFlow.prototype.remarks.toolBtns={
		        cursor:"选择指针",
		        direct:"结点连线",
		        dashed:"关联虚线",
		        start:"入口结点",
		        "end":"结束结点",
		        "task":"任务结点",
		        node:"自动结点",
		        chat:"决策结点",
		        state:"状态结点",
		        plug:"附加插件",
		        fork:"分支结点",
		        "join":"联合结点",
		        "complex":"复合结点",
		        group:"组织划分框编辑开关",
		        servlet:"servlet入口"
		};
		GooFlow.prototype.remarks.headBtns={
		    "new":"新建流程",
		    open:"打开流程",
		    save:"保存结果",
		    undo:"撤销",
		    redo:"重做",
		    reload:"刷新流程",
		    print:"打印流程图"
		};
		GooFlow.prototype.remarks.extendRight="工作区向右扩展";
		GooFlow.prototype.remarks.extendBottom="工作区向下扩展";
		var demo;
		$(document).ready(function(){
			
			contextPath = "${pageContext.request.contextPath}";
			
			demo=$.createGooFlow($("#demo"),property);
			
			//demo.setNodeRemarks(remark);
			
			var data = '${data}';
			data = eval('('+data+')');
			demo.loadData(data);
			ajaxLodFlag = false;
			
			//demo.reinitSize(1000,520);
			demo.reinitSize(window.innerWidth-15,window.innerHeight-30);
			
		    demo.onItemRightClick=function(id,type){
		        console.log("onItemRightClick: "+id+","+type);
		        
		         $.jBox("iframe:" + contextPath+'/camelv/route/form?id='+id, {
					title : "修改",
					width : 600,
					height : 350,
					persistent : false,
					dragLimit : false,
					buttons : {
						'关闭' : true
					}
				}); 
		        
		        return true;//返回false可以阻止浏览器默认的右键菜单事件
		    }
		    demo.onItemDbClick=function(id,type){
		        console.log("onItemDbClick: "+id+","+type);
		        
		        return true;//返回false可以阻止原组件自带的双击直接编辑事件
		    }
		    demo.onPrintClick=function(){
		        demo.print(0.8);
		    }
		    
		    //页面刷新信息提示
		    var message = '${message}';
			if(typeof(message) != 'undefined' && message != ""){
				if(message.indexOf("成功")!=-1){
					$.jBox.tip(message, "success");
				}else{
					$.jBox.tip(message, "error");
				}
			}
			
		});
		var out;
		function Export(){
		    document.getElementById("result").value=JSON.stringify(demo.exportData());
		}
		function ResetScale(){
		    demo.resetScale( parseFloat(document.getElementById("scaleValue").value) );
		}
		window.onresize=function(){
			log("改变窗口大小:"+(window.innerWidth-15)+","+(window.innerHeight-30));
			demo.reinitSize(window.innerWidth-15,window.innerHeight-30);
		}
		
	</script>
	
	
</head>
<body>
	
	<!-- 隐藏值 -->
	<input type="hidden" id="serverId" value="${serverId }"/>
	
	<div id="demo" style="width:100%; height: 100%;"></div>
		
	<!-- 请输入缩放值：
	<input type="text" id="scaleValue" value="0.5" />
	<input id="scale" type="button" value='缩放' onclick="ResetScale()" />
	<input id="submit" type="button" value='导出结果' onclick="Export()" />
	<input id="submit" type="button" value='清空' onclick="demo.clearData()" />
	<textarea id="result" row="6"></textarea> -->
</body>



</html>



