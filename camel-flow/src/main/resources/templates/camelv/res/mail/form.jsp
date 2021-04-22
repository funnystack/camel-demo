<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>邮箱管理</title>
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
		<li><a href="${ctx}/camelv/mail/list">邮箱列表</a></li>
		<li class="active"><a href="${ctx}/camelv/mail/form?id=${camelvMail.id}">${not empty camelvMail.id?'修改':'添加'}</a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="camelvMail" action="${ctx}/camelv/mail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
			
		<div class="control-group">
			<label class="control-label" for="name">用户名:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="required"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="username">账号:</label>
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
		
		<div class="control-group">
			<label class="control-label" for="mailHost">服务器:</label>
			<div class="controls">
				<form:input path="mailHost" htmlEscape="false" maxlength="100" class="required"/>
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