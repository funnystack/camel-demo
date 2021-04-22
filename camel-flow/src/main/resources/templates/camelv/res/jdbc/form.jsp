<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>JDBC管理</title>
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
		<li><a href="${ctx}/camelv/jdbc/list">Jdbc列表</a></li>
		<li class="active"><a href="${ctx}/camelv/jdbc/form?id=${camelvJdbc.id}">Jdbc${not empty camelvJdbc.id?'修改':'添加'}</a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="camelvJdbc" action="${ctx}/camelv/jdbc/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label" for="name">名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="driver">类型:</label>
			<div class="controls">
				<form:select path="driver">
					<form:option value="com.mysql.jdbc.Driver">MySQL</form:option>
					<form:option value="oracle.jdbc.driver.OracleDriver">Oracle</form:option>
					<form:option value="org.postgresql.Driver">PostgreSQL</form:option>
					<form:option value="com.microsoft.sqlserver.jdbc.SQLServerDriver">SQLServer</form:option>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="url">URL:</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="100" class="required"/>
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