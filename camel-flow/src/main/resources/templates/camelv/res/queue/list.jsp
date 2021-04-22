<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>队列管理</title>
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
		<li class="active"><a href="${ctx}/camelv/queue/list">队列列表</a></li>
		<li><a href="${ctx}/camelv/queue/form">添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="camelvJdbc" action="${ctx}/camelv/queue/list" method="post" class="breadcrumb form-search">
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
				<th>队列名</th>
				<th>vHost</th>
				<th>类型</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="queue">
				<tr>
					<td>${queue.name}</td>
					<td>${queue.ip}</td>
					<td>${queue.port}</td>
					<td>${queue.userName}</td>
					<td>${queue.passWord}</td>
					<td>${queue.queueName}</td>
					<td>${queue.vHost}</td>
					<td>${queue.type}</td>
					<td>
						<a href="${ctx}/camelv/queue/form?id=${queue.id}">修改</a>
						<a href="${ctx}/camelv/queue/delete?id=${queue.id}" onclick="return confirmx('确认要删除该队列信息吗？', this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>