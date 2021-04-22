var logSwitch = true;// 日志开关，可设置是否打印日志，默认打印
var ajaxLodFlag = true,hideFlag=false;
var contextPath = "";
// 说明：
// 1.将gooflow提供的操作接口进行重写，并结合具体的业务，这样寄能够降低业务代码与gooflow的耦合，同时也能够方便代码的阅读，将具体的业务代码抽离出来放入js中
// 2.这里的所有操作都是实时
// （同步）Ajax请求的，请注意是同步的，并没有采用异步的方式，因为如果采用异步，那么可能会导致的情况如下：页面添加成功，但是Xms时间后，显示操作失败
// 这样的操作会给带来很大的疑惑。
// 3.所有的操作都是实时入库的，通过入库的数据反向解析为gooflow需要的json字符串，然后页面展示，并没有采取页面操作后点击保存时将数据导出入库的方案，这是因为，当N台计算器同时
// 打开同一个页面时，如果一个页面A没有做修改，另一个页面B做了修改，那么当B先保存时，A后保存时，就会出现A将覆盖了B的数据，由于A未做修改，所以下次页面展示时将是未修改的的状态
// 这样的操作会带来很大的疑惑。

// 打印日志，结合logSwitch使用
function log(msg) {
	if (logSwitch) {
		console.log(msg);
	}
}

// 弹出提示: msg是弹出的信息，type用于标识弹框的类型
function alertMsg(msg, type) {
	$.jBox.tip(msg, type);
}


// 格式function(id，type,json)：id是单元的唯一标识ID,type是单元的种类,有"node","line","area"三种取值,json即addNode,addLine或addArea方法的第二个传参json.
function onItemAdd(id, type, json) {
	log("添加 id = " + id + " , type = " + type);
	if (ajaxLodFlag) {
		//从后台读取添加
		return true;
	}
	var serverId = $("#serverId").val();
	if (type == "node") {
		$.ajax({
			url : contextPath + "/camelv/route/save",
			type : "post",
			data : $.param(json) + "&serverId=" + serverId + "&id=" + id,
			success : function(data) {
				if (data.type == "error") {
					window.location = contextPath+"/camelv/server/edit?message="+encodeURI(encodeURI("添加路由失败"))+"&id="+serverId;
					return;
				}
				if (data.type == "success") {
					// 页面不用刷新,直接更新数据
					json.id = data.data;
					demo.transNewId(id, data.data, "node");
				}
			},
			error : function(data) {
				$.jBox.tip("添加路由失败!!!", "error");
			}
		});
	} else if (type == "line") {
		
		//是否存在指向直接的线
		if(json.from == json.to){
			return false;
		}
		//避免两个节点间不能有一条以上同向接连线
		for(var k in demo.$lineData){
			if((json.from==demo.$lineData[k].from&&json.to==demo.$lineData[k].to))
				return false;
		}
		//是否出现双向指向
		for(var k in demo.$lineData){
			if((json.from==demo.$lineData[k].to&&json.to==demo.$lineData[k].from))
				return false;
		}
		log("通过...");
		$.ajax({
			url : contextPath + "/camelv/line/save",
			type : "post",
			data : $.param(json) + "&serverId=" + serverId + "&id=" + id,
			success : function(data) {
				if (data.type == "error") {
					window.location = contextPath+"/camelv/server/edit?message="+encodeURI(encodeURI("添加路由关系失败"))+"&id="+serverId;
					return;
				}
				if (data.type == "success") {
					// 页面不用刷新,直接更新数据
					json.id = data.data;
					demo.transNewId(id, data.data, "line");
				}
			},
			error : function(data) {
				$.jBox.tip("添加路由关系失败!!!", "error");
			}
		});
	}else if(type == "area"){
		$.ajax({
			url : contextPath + "/camelv/area/save",
			type : "post",
			data : $.param(json) + "&serverId=" + serverId + "&id=" + id,
			success : function(data) {
				if (data.type == "error") {
					window.location = contextPath+"/camelv/server/edit?message="+encodeURI(encodeURI("添加工作区失败"))+"&id="+serverId;
					return;
				}
				if (data.type == "success") {
					// 页面不用刷新,直接更新数据
					json.id = data.data;
					demo.transNewId(id, data.data, "area");
				}
			},
			error : function(data) {
				$.jBox.tip("添加工作区失败!!!", "error");
			}
		});
	}

	return true;
}

// 格式function(id，type)：id是单元的唯一标识ID,type是单元的种类,有"node","line","area"三种取值
function onItemDel(id, type) {
	log("删除 id = " + id + " , type = " + type);
	
	if (type == "node") {
		$.ajax({
			url : contextPath + "/camelv/route/delete",
			type : "post",
			data : {
				id : id
			},
			success : function(data) {
				if (data.type == "error") {
					window.location = contextPath+"/camelv/server/edit?message="+encodeURI(encodeURI("删除路由失败"))+"&id="+$("#serverId").val();
					return;
				}
			},
			error : function(data) {
				$.jBox.tip("删除路由失败!!!", "error");
			}
		});
	} else if (type == "line") {
		$.ajax({
			url : contextPath + "/camelv/line/delete",
			type : "post",
			data : {
				id : id
			},
			success : function(data) {
				if (data.type == "error") {
					window.location = contextPath+"/camelv/server/edit?message="+encodeURI(encodeURI("删除路由关系失败"))+"&id="+$("#serverId").val();
					return;
				}
			},
			error : function(data) {
				$.jBox.tip("删除路由关系失败!!!", "error");
			}
		});
	}else if(type == "area"){
		$.ajax({
			url : contextPath + "/camelv/area/delete",
			type : "post",
			data : {
				id : id
			},
			success : function(data) {
				if (data.type == "error") {
					window.location = contextPath+"/camelv/server/edit?message="+encodeURI(encodeURI("删除工作区失败"))+"&id="+$("#serverId").val();
					return;
				}
			},
			error : function(data) {
				$.jBox.tip("删除工作区失败!!!", "error");
			}
		});
	}
	return true;
}

// 格式function(id，type,left,top)：id是单元的唯一标识ID,type是单元的种类,有"node","area"两种取值，线line不支持移动,left是新的左边距坐标，top是新的顶边距坐标
function onItemMove(id, type, left, top) {
	log("移动 id = " + id + " , type = " + type + " , top = " + top);
	var serverId = $("#serverId").val();
	if (type == "node") {
		$.ajax({
			url : contextPath + "/camelv/route/move",
			type : "post",
			data : {
				id : id,
				left : left,
				top : top
			},
			success : function(data) {
				if (data.type == "error") {
					window.location = contextPath+"/camelv/server/edit?message="+encodeURI(encodeURI("移动路由位置失败"))+"&id="+$("#serverId").val();
					return;
				}
			},
			error : function(data) {
				$.jBox.tip("移动路由位置失败!!!", "error");
			}
		});
	}else if(type == "area"){
		$.ajax({
			url : contextPath + "/camelv/area/move",
			type : "post",
			data : {
				id : id,
				left : left,
				top : top
			},
			success : function(data) {
				if (data.type == "error") {
					window.location = contextPath+"/camelv/server/edit?message="+encodeURI(encodeURI("移动工作区位置失败"))+"&id="+$("#serverId").val();
					return;
				}
			},
			error : function(data) {
				$.jBox.tip("移动工作区位置失败!!!", "error");
			}
		});
	}
	return true;
}

// 格式function(id,name,type)：id是单元的唯一标识ID,type是单元的种类,有"node","line","area"三种取值,name是新的名称
function onItemRename(id, name, type) {
	log("重命名：" + id + " , name = " + name + " , type = " + type);
	if (type == "node") {
		$.ajax({
			url : contextPath + "/camelv/route/rename",
			type : "post",
			data : {
				id : id,
				name : name
			},
			success : function(data) {
				if (data.type == "error") {
					window.location = contextPath+"/camelv/server/edit?message="+encodeURI(encodeURI("路由重命名失败"))+"&id="+$("#serverId").val();
					return;
				}
			},
			error : function(data) {
				$.jBox.tip("重命名失败!!!", "error");
			}
		});
	} else if (type == "line") {
		$.ajax({
			url : contextPath + "/camelv/line/rename",
			type : "post",
			data : {
				id : id,
				name : name
			},
			success : function(data) {
				if (data.type == "error") {
					window.location = contextPath+"/camelv/server/edit?message="+encodeURI(encodeURI("条件添加或修改失败"))+"&id="+$("#serverId").val();
					return;
				}
			},
			error : function(data) {
				$.jBox.tip("条件添加或修改失败!!!", "error");
			}
		});
		
	}else if (type == "area") {
		$.ajax({
			url : contextPath + "/camelv/area/rename",
			type : "post",
			data : {
				id : id,
				name : name
			},
			success : function(data) {
				if (data.type == "error") {
					window.location = contextPath+"/camelv/server/edit?message="+encodeURI(encodeURI("工作区修改名称失败"))+"&id="+$("#serverId").val();
					return;
				}
			},
			error : function(data) {
				$.jBox.tip("工作区修改名称失败!!!", "error");
			}
		});
	}
	return true;
}

// 格式function(id，newStart,newEnd)：id是连线单元的唯一标识ID,newStart,newEnd分别是起始结点的ID和到达结点的ID
function onLinePointMove(id, newStartId, newEndId) {
	log("移动线 id = " + id + " , newStartId = " + newStartId + " , newEndId = "+ newEndId);
	$.ajax({
		url : contextPath + "/camelv/line/move",
		type : "post",
		data : {
			id : id,
			newFrom : newStartId,
			newTo : newEndId
		},
		success : function(data) {
			if (data.type == "error") {
				window.location = contextPath+"/camelv/server/edit?message="+encodeURI(encodeURI("移动路由关系失败"))+"&id="+$("#serverId").val();
				return;
			}
		},
		error : function(data) {
			$.jBox.tip("移动路由关系失败!!!", "error");
		}
	});
	return true;
}

//当操作某个单元（结点/分组块）被重定义大小或造型时，触发的方法，返回FALSE可阻止重定大小/造型事件的发生
//格式function(id，type,width,height)：id是单元的唯一标识ID,type是单元的种类,有"node","line","area"三种取值;width是新的宽度,height是新的高度
function onItemResize(id,type,width,height){
	log("调整大小: id = "+id+" , type = "+type+" , width = "+width+" , height = "+height);
	if(type == "node"){
		$.ajax({
			url : contextPath + "/camelv/route/resize",
			type : "post",
			data : {
				id : id,
				width : width,
				height : height
			},
			success : function(data) {
				if (data.type == "error") {
					window.location = contextPath+"/camelv/server/edit?message="+encodeURI(encodeURI("路由图形大小修改失败"))+"&id="+$("#serverId").val();
					return;
				}
			},
			error : function(data) {
				$.jBox.tip("路由图形大小修改失败!!!", "error");
			}
		});
	}else if(type == "area"){
		$.ajax({
			url : contextPath + "/camelv/area/resize",
			type : "post",
			data : {
				id : id,
				width : width,
				height : height
			},
			success : function(data) {
				if (data.type == "error") {
					window.location = contextPath+"/camelv/server/edit?message="+encodeURI(encodeURI("工作区大小调整失败"))+"&id="+$("#serverId").val();
					return;
				}
			},
			error : function(data) {
				$.jBox.tip("工作区大小调整失败!!!", "error");
			}
		});
	}
	return true;
}

//当变换某条连接线的类型，触发的方法，返回FALSE可阻止重定大小/造型事件的发生
//格式function(id，type)：id是单元的唯一标识ID,type是连接线的新类型,"sl":直线,"lr":中段可左右移动的折线,"tb":中段可上下移动的折线
function onLineSetType(id,type){
	log("修改线的类型,id = "+id+" , type = "+type);
	$.ajax({
		url : contextPath + "/camelv/line/setType",
		type : "post",
		data : {
			id : id,
			type:type
		},
		success : function(data) {
			if (data.type == "error") {
				window.location = contextPath+"/camelv/server/edit?message="+encodeURI(encodeURI("修改路由连线类型失败"))+"&id="+$("#serverId").val();
				return;
			}
		},
		error : function(data) {
			$.jBox.tip("路由之间连线类型修改失败!!!", "error");
		}
	});
	return true;
}

//当移动某条折线中段的位置，触发的方法，返回FALSE可阻止重定大小/造型事件的发生
//格式function(id，M)：id是单元的唯一标识ID,M是中段的新X(或Y)的坐标
function onLineMove(id,M){
	log("onLineMove id = "+id+" , M = "+M);
	$.ajax({
		url : contextPath + "/camelv/line/setM",
		type : "post",
		data : {
			id : id,
			M:M
		},
		success : function(data) {
			if (data.type == "error") {
				window.location = contextPath+"/camelv/server/edit?message="+encodeURI(encodeURI("修改路由连线类型折点失败"))+"&id="+$("#serverId").val();
				return;
			}
		},
		error : function(data) {
			$.jBox.tip("路由之间连线类型折点修改失败!!!", "error");
		}
	});
	return true;
}

//隐藏菜单
function onMenuHide(){
	hideFlag = true;
	$(".GooFlow_tool_div").hide();
	$(".ico_hide").addClass("ico_show");
	$(".ico_hide").removeClass("ico_hide");
	$("#routeFrameDiv").css({top:"5.6%"});
	demo.reinitSize(window.innerWidth-100,window.innerHeight-100);
}
//显示菜单
function onMenuShow(){
	hideFlag = false;
	$(".GooFlow_tool_div").show();
	$(".ico_show").addClass("ico_hide");
	$(".ico_show").removeClass("ico_show");
	$("#routeFrameDiv").css({top:"18%"});
	demo.reinitSize(window.innerWidth-100,window.innerHeight-100);
}
//返回到首页
function onHome(){
	window.location = contextPath+"/camelv/server/list";
}
//刷新
function onReload(){
	window.location.reload(true);
}

//全屏
var fullScreen = false;
//插件地址:https://github.com/martinaglv/jQuery-FullScreen
function onFullScreen(){
	if(!$.support.fullscreen){
		$.jBox.tip("该浏览器不支持全屏", "error");
		return;
	}
	if(!fullScreen){
		//全屏
		$(".ico_fullScreen").attr("class","ico_quitFullScreen");
		fullScreen = true;
		$('body').fullScreen();
	}else{
		//退出全屏
		$(".ico_quitFullScreen").attr("class","ico_fullScreen");
		fullScreen = false;
		$('body').fullScreen();
	}
}


// 当用重色标注某个结点/转换线时触发的方法，返回FALSE可阻止重定大小/造型事件的发生
// 格式function(id，type，mark)：id是单元的唯一标识ID,type是单元类型（"node"结点,"line"转换线），mark为布尔值,表示是要标注TRUE还是取消标注FALSE
function onItemMark(id, type, mark) {
	log("onItemMark  id = " + id + " , type = " + type + " , mark = " + mark);

	return true;
}


// 根据id获取节点信息
function getNodeById(id) {
	log("查询node id = " + id);
	var node = demo.$nodeData[id];
	if (typeof (node) == 'undefined') {
		return null;
	}
	log(node);
	return node;
}

// 根据id获取连线信息
function getLineById(id) {
	log("查询line id = " + id);
	var line = demo.$lineData[id];
	if (typeof (line) == 'undefined') {
		return null;
	}
	log(line);
	return line;
}

// 获取未保存的节点
function getNotSavedNode() {
	var notSaveObj = null;
	// 遍历
	for ( var i in demo.$nodeData) {
		var node = demo.$nodeData[i];
		if (node.save == false) {
			notSaveObj = node;
		}
	}
	return notSaveObj;
}
// 获取未保存的节点，除了指定的id除外
function getNotSavedButId(id) {
	var notSaveObj = null;
	// 遍历
	for ( var i in demo.$nodeData) {
		var node = demo.$nodeData[i];
		if (node.save == false && node.id != id) {
			notSaveObj = node;
		}
	}
	return notSaveObj;
}
/** **********************************service操作结束*********************************************************************** */

