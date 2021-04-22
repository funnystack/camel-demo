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
		
		//去除默认的右击事件
		document.oncontextmenu = function() {
			return true;
		}; 
		
		var property={
			width:915,
			height:540,
			toolBtns:["start round mix","end round","task","node","chat","state","plug","join","fork","complex mix"],
			consumerComponent:["jetty"],
// 			producerComponent:["groovy","http","jdbc","exception"],
			producerComponent:["groovy","http"],
			haveHead:true,
			headLabel:true,
// 			headBtns:["new","open","save","undo","redo","reload"],//如果haveHead=true，则定义HEAD区的按钮
			headBtns:["home","reload","fullScreen"],//如果haveHead=true，则定义HEAD区的按钮
			haveTool:true,
			haveGroup:true,
			useOperStack:true,
			initLabelText:"${server.name}"
		};
		var remark={
			cursor:"选择指针",
			direct:"结点连线",
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
			show:"显示",
			hide:"隐藏",
			servlet:"servlet入口",
			groovy:"groovy脚本路由",
			http:"HTTP路由",
			jdbc:"JDBC路由",
			jetty:"Jetty入口",
			exception:"异常路由"
		};
		var demo;
		$(document).ready(function(){
			demo=$.createGooFlow($("#demo"),property);
			contextPath = "${pageContext.request.contextPath}";
			var data = '${data}';
			data = eval('('+data+')');
			demo.loadData(data);
			ajaxLodFlag = false;
			demo.setNodeRemarks(remark);
			//初始化窗口大小
			demo.reinitSize(window.innerWidth-100,window.innerHeight-100);
			
		    demo.onItemRightClick=function(id,type){
		        console.log("onItemRightClick: "+id+","+type);
				
		        return true;//返回false可以阻止浏览器默认的右键菜单事件
		    }
		    
		    demo.onItemDbClick=function(id,type){
		        console.log("onItemDbClick: "+id+","+type);
		        
		        return true;//返回false可以阻止原组件自带的双击直接编辑事件
		    };
		    
		  	//当操作某个单元（结点/线）被由不选中变成选中时，触发的方法，返回FALSE可阻止选中事件的发生
			//格式function(id,type)：id是单元的唯一标识ID,type是单元的种类,有"node","line"两种取值,"area"不支持被选中
		    demo.onItemFocus = function(id,type){
		   	 	log("onItemFocus  id = " + id + " , type = " + type);
		   	 	if(type == "node"){
		   	 		$("#routeFrame").attr("src",contextPath+"/camelv/route/form?id="+id);
		   	 		//自动打开
		   	 		var type = $("#routeFrameCloseText").attr("v");
		   	 		if(type == "open"){
		   	 			$("#routeFrameCloseText").click();
		   	 		}
		   	 	}
		   	 	return true;
		    };
		    
		    demo.onPrintClick=function(){
		        demo.print(0.8);
		    };
					    
		    //页面刷新信息提示
		    var message = '${message}';
			if(typeof(message) != 'undefined' && message != ""){
				if(message.indexOf("成功")!=-1){
					$.jBox.tip(message, "success");
				}else{
					$.jBox.tip(message, "error");
				}
			}
			$("#routeFrameCloseText").on("click",function(){
				var v = $(this).attr("v");
				if(v == "hide"){//当前处于隐藏
					//隐藏起来，显示打开提示
					$(this).attr("v","open");
					$("#routeFrameDiv").animate({
// 						'right':'-23.8%'
						'height':'-1%'
					},200);
					$("#routeFrameDiv").fadeToggle(10);
					$(this).text("打开");
				}else{
					//打开，显示隐藏提示
					$(this).attr("v","hide");
					$(this).text("隐藏");
					$("#routeFrameDiv").fadeToggle(10);
					$("#routeFrameDiv").animate({
// 						'right':'0.6%'
						'height':'80%'
					},200);
					
				}
			});
			$("#routeFrameDiv").hide();
		});
		
		window.onresize=function(){
			log("改变窗口大小:"+(window.innerWidth-15)+","+(window.innerHeight-30));
			demo.reinitSize(window.innerWidth-50,window.innerHeight-30);
		}
		
		var out;
		function Export(){
		    document.getElementById("result").value=JSON.stringify(demo.exportData());
		}
	</script>
	
	
	<style type="text/css">
		#routeFrameDiv{
			position: absolute;
			top: 18%;
			right: 0.6%;
			height: 80%;
			border: 2px solid #C0CCDA;
			border-radius: 3px;
		}
		#routeFrameCloseText{
			background-color: #C0CCDA;
			position: absolute;
		    top: 75%;
		    right: 4%;
		    z-index: 100;
		    border-radius: 25px;
		    height: 50px;
		    width: 50px;
		    opacity:0.6;
		}
		
		/* 以下是渐变色,不兼容时可去除，http://www.runoob.com/css3/css3-gradients.html */
		/*颜色代码参考 https://www.cnblogs.com/xpvincent/archive/2012/12/17/2821665.html */
		.groovy {
		  background: -webkit-linear-gradient(left, yellow ,red); /* Safari 5.1 - 6.0 */
		  background: -o-linear-gradient(right, yellow ,red); /* Opera 11.1 - 12.0 */
		  background: -moz-linear-gradient(right, yellow ,red); /* Firefox 3.6 - 15 */
		  background: linear-gradient(to right, Linen,LightSkyBlue); /* 标准的语法 */
		}
		
		.jetty {
		  background: -webkit-linear-gradient(left, yellow ,green); /* Safari 5.1 - 6.0 */
		  background: -o-linear-gradient(right, yellow ,green); /* Opera 11.1 - 12.0 */
		  background: -moz-linear-gradient(right, yellow ,green); /* Firefox 3.6 - 15 */
		  background: linear-gradient(to right,LightYellow ,PaleGreen); /* 标准的语法 */
		}
		
		.http {
		  background: -webkit-linear-gradient(left, AntiqueWhite ,Aquamarine); /* Safari 5.1 - 6.0 */
		  background: -o-linear-gradient(right, AntiqueWhite ,Aquamarine); /* Opera 11.1 - 12.0 */
		  background: -moz-linear-gradient(right, AntiqueWhite ,Aquamarine); /* Firefox 3.6 - 15 */
		  background: linear-gradient(to right,AntiqueWhite ,CornflowerBlue); /* 标准的语法 */
		}
		
	</style>
</head>
<body>
	
	<!-- 隐藏值 -->
	<input type="hidden" id="serverId" value="${server.id }"/>
	
	<div id="demo" style="width: 100%;height: 100%"></div>
	<button  id="routeFrameCloseText" v="open">打开</button>
	<div id="routeFrameDiv">
		<iframe src="" width="100%" height="100%" id="routeFrame" style="" frameborder="no" border="0" />
	</div>
		
</body>


</html>



