<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>ssh管理</title>
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
		<li class="active"><a href="${ctx}/camelv/host/list">Host列表</a></li>
		<li><a href="${ctx}/camelv/host/form">添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="camelvHost" action="${ctx}/sys/dict/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>ip</th>
				<th>port</th>
				<th>用户名</th>
				<th>密码</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="ssh">
				<tr>
					<td>${ssh.name}</td>
					<td>${ssh.ip}</td>
					<td>${ssh.port}</td>
					<td>${ssh.username}</td>
					<td>${ssh.password}</td>
					<td>
						<a href="${ctx}/camelv/host/form?id=${ssh.id}">修改</a>
						<a href="${ctx}/camelv/host/delete?id=${ssh.id}" onclick="return confirmx('确认要删除该主机信息吗？', this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>