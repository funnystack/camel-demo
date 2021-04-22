<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>邮箱管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/camelv/mail/list">邮箱列表</a></li>
		<li><a href="${ctx}/camelv/mail/form">添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="camelvMail" action="${ctx}/camelv/mail/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户名</th>
				<th>账号</th>
				<th>密码</th>
				<th>服务器</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="mail">
				<tr>
					<td>${mail.name}</td>
					<td>${mail.username}</td>
					<td>${mail.password}</td>
					<td>${mail.mailHost}</td>
					<td>
						<a href="${ctx}/camelv/mail/form?id=${mail.id}">修改</a>
						<a href="${ctx}/camelv/mail/delete?id=${mail.id}" onclick="return confirmx('确认要删除该邮箱信息吗？', this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>