<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/dialog.jsp"%>
<head>
	<meta name="decorator" content="default" />
		
	<style type="text/css">
		.form-horizontal .control-label {
		    float: left;
		    width: 15%;
		    padding-top: 5px;
		    text-align: right;
		}	
		
		.form-horizontal .controls {
		    margin-left: 17%;
		}
		
		.form-horizontal .form-actions {
		    padding-left: 43%;
		}
	</style>
	
	<script type="text/javascript">
	
	$(document).ready(function() {
		
		//获取校验器
		var validator = $("#inputForm").validate();
		//表单提交
		$("#btnSubmit").on("click", function() {
			//手动执行校验
			var validatorResult = validator.form();
			//参数序列化
			var serialize = $("#inputForm").serialize();
			var param = "";
	        console.log(serialize);
	        var option="",flag=true;
	        //获取option参数
	        $(".option").each(function(){
	        		//遍历获取name
	        		var name = $.trim($(this).children("input").eq(0).val());
	        		//遍历获取value
	        		var value = $.trim($(this).children("input").eq(1).val());
	        		if(name=="" && value !=""){
	        			flag=false;
	        		}
	        		if(name != ""){
	        			option += ""+name+"->"+value+",";
	        		}
	        });
	        if(!flag){
	       	 	parent.$.jBox.tip("请将参数名填写完整", "error");
	        		return ;
	        }
	        if(option.length>0){
	          	option = option.substring(0,option.length-1);
	        }
			if (validatorResult) {
				//ajax提交
				$.ajax({
					url : "${ctx}/camelv/route/update",
					type : "post",
					data : serialize+"&option="+option,
					success : function(data) {
						if(data.type == "error"){
							parent.$.jBox.tip("保存失败", "error");
							return ;
						}
						if(data.type == "success"){
							parent.window.location = "${ctx}/camelv/server/edit?id=${route.serverId}&message="+encodeURI(encodeURI("保存成功"));
						}
					},
					error : function(data) {
						parent.$.jBox.tip("保存失败!!!", "error");
					}
				});
			}
		});
		
		//添加更多按钮
		var MaxInputs       = 1000; //maximum input boxes allowed  
		var InputsWrapper   = $("#InputsWrapper"); //Input boxes wrapper ID  
		var AddButton       = $("#AddMoreFileBox"); //Add button ID  
		var x = InputsWrapper.length; //initlal text box count
		x = ${fn:length(optionList)};
		var FieldCount=x; //to keep track of text box added  
		
		//on add input button click  
		$(AddButton).click(function (e) { 
	        if(x <= MaxInputs){//max input box allowed   
	            FieldCount++; //text box added increment  
	            //add input box  
	            $(InputsWrapper).append('<div><div class="controls option"><input type="text" style="width:20%;"  placeholder="key" />=<input type="text" style="width:45%;"  placeholder="value '+ FieldCount +'" /><a href="#" class="removeclass">&nbsp;&nbsp;移除</a></div></div>');  
	            x++; //text box increment  
	        }
	        //自动更新参数列表
			return false;
		});  
		
		$("body").on("click",".removeclass", function(e){ //user click on remove text  
	        if( x >= 1 ) {  
	                $(this).parent('div').remove(); //remove text box  
	                x--; //decrement textbox  
	        }  
			return false;  
		});
		
	});
	
	</script>
	
	
</head>

<br />
<form:form  id="inputForm" modelAttribute="route" method="post" class="form-horizontal gform">
		<form:hidden path="id"/>
		<form:hidden path="type"/>
		<div class="control-group">
			<label class="control-label" for="name">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="uri">URI：</label>
			<div class="controls">
				<form:input path="uri" htmlEscape="false" class="required" />
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<a href="javascript;void(0);" id="AddMoreFileBox">添加更多参数</a>
			</div>
		</div>
		<div id="InputsWrapper">
			<c:forEach items="${optionList }" var="option" varStatus="st">
				<div  class="controls option">
					<input type="text" style="width:20%;" value="${option.name }"/>=<input type="text" style="width:40%;" value="${option.value }"/>
					<a href="#" class="removeclass">&nbsp;&nbsp;移除</span></a>
				</div>  
			</c:forEach>
		</div> 
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" />
		</div>
</form:form>