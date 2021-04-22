<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>ssh管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate();
			
			jQuery.validator.addMethod("ipValidate", function(value, element) { 
				return this.optional(element) || /^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/.test(value);    
		    }, "请填写正确的IP地址");
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/camelv/host/list">Host列表</a></li>
		<li class="active"><a href="${ctx}/camelv/host/form?id=${camelvHost.id}">Host${not empty camelvHost.id?'修改':'添加'}</a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="camelvHost" action="${ctx}/camelv/host/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label" for="type">类型:</label>
			<div class="controls">
				<form:select path="type">
					<form:option value="ssh">SSH</form:option>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="ip">ip:</label>
			<div class="controls">
				<form:input path="ip" htmlEscape="false" maxlength="100" class="required ipValidate"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="port">port:</label>
			<div class="controls">
				<form:input path="port" htmlEscape="false" maxlength="100" class="required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="username">用户名:</label>
			<div class="controls">
				<form:input path="username" htmlEscape="false" maxlength="100" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="password">密码:</label>
			<div class="controls">
				<form:input path="password" htmlEscape="false" maxlength="100" class="required"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:dict:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>