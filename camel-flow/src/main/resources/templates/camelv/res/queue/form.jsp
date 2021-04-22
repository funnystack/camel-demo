<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>队列管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate();
			
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/camelv/queue/list">队列列表</a></li>
		<li class="active"><a href="${ctx}/camelv/queue/form?id=${camelvQueue.id}">队列${not empty camelvJdbc.id?'修改':'添加'}</a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="camelvQueue" action="${ctx}/camelv/queue/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label" for="type">类型:</label>
			<div class="controls">
				<form:select path="type">
					<form:option value="ActiveMQ">ActiveMQ</form:option>
					<form:option value="RabbitMQ">RabbitMQ</form:option>
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
				<form:input path="ip" htmlEscape="false" maxlength="100" class="required"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="port">port:</label>
			<div class="controls">
				<form:input path="port" htmlEscape="false" maxlength="100" class="required"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="userName">用户名:</label>
			<div class="controls">
				<form:input path="userName" htmlEscape="false" maxlength="100" class="required"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="passWord">密码:</label>
			<div class="controls">
				<form:input path="passWord" htmlEscape="false" maxlength="100" class="required"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="queueName">队列名:</label>
			<div class="controls">
				<form:input path="queueName" htmlEscape="false" maxlength="100" class="required"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="vHost">vHost:</label>
			<div class="controls">
				<form:input path="vHost" htmlEscape="false" maxlength="100" class="required"/>
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