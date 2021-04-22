<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典管理</title>
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
		<li class="active"><a href="${ctx}/camelv/http/list">Http列表</a></li>
		<li><a href="${ctx}/camelv/http/form">添加</a></li>
	</ul>
	<%-- <form:form id="searchForm" modelAttribute="camelvHttp" action="${ctx}/sys/dict/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form> --%>
	<tags:message content="${message}"/>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>URL</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="http">
				<tr>
					<td>${http.name}</td>
					<td>${http.url}</td>
					<td>
						<a href="${ctx}/camelv/http/form?id=${http.id}">修改</a>
						<a href="${ctx}/camelv/http/delete?id=${http.id}" onclick="return confirmx('确认要删除该HTTP信息吗？', this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>