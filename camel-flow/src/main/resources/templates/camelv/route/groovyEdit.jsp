<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/dialog.jsp"%>
<head>
	<meta name="decorator" content="default" />
	<title>groovy编辑查看</title>
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
			if (validatorResult) {
				//ajax提交
				$.ajax({
					url : "${ctx}/camelv/route/update",
					type : "post",
					data : serialize,
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
			<div class="controls">
				<a href="javascript;void(0);" class="editGroovy">编辑脚本</a>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" />
		</div>
</form:form>