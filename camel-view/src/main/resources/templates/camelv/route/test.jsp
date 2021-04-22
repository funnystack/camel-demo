<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${route.name }</title>
<meta name="decorator" content="default" />
<style type="text/css">
.disNone {
	display: none;
}

.help {
	float: right;
	margin-right: 10px;
}

.form-horizontal .control-label {
	float: left;
	width: 10%!important;
	padding-top: 5px;
	text-align: right;
}

.form-horizontal .controls {
	margin-left: 15% !important;
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
			if (!validatorResult) {
				return;
			}
			//ajax提交
			$.ajax({
				url : "${ctx}/camelv/route/update",
				type : "post",
				data : serialize,
				success : function(data) {
					if (data == "success") {
						//关闭窗口
						setTimeout(function() {
							parent.$.jBox.close(true);
						}, 2000);
					}else if (data == "published") {
						var message = "流程已发布";
						parent.$.jBox.tip(message, "error");
					} else if (data == "nameRepeat") {
						var message = "名称已存在";
						parent.$.jBox.tip(message, "error");
					} else if (data == "error") {
						var message = "添加失败";
						parent.$.jBox.tip(message, "error");
					} else if (data == "urlRepeat") {
						var message = "地址重复";
						parent.$.jBox.tip(message, "error");
					}else {
						var message = "未知异常 [ " + data + " ]";
						parent.$.jBox.tip(message, "error");
					}
				},
				error : function(data) {
					$.jBox.tip("保存失败!!!", "error");
				}
			});
			
		});
	});
</script>

</head>
	
<body>
	<br />
	<form:form id="inputForm" modelAttribute="route" class="form-horizontal" style="width:100px;">
		<form:hidden path="id" />
		<form:hidden path="type" />
		<a href="javascript:;" class="help">帮助</a>
		<div class="control-group">
			<label class="control-label" for="name">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" class="required" />
			</div>
		</div>
			
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" />
		</div>
	</form:form>
</body>
</html>
