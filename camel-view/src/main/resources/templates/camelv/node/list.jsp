<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节点管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	   	 	return false;
	    }
		
		//自动刷新页面
		setTimeout(function() {
			window.location.reload(true);
		}, 5000);
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/camelv/node/list">节点列表</a></li>
	</ul>
	<%-- <form:form id="searchForm" modelAttribute="camelvNode" action="${ctx}/camelv/node/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form> --%>
	<tags:message content="${message}"/>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>节点地址</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="node" varStatus="st">
				<tr>
					<td>${st.count}</td>
					<td>${node.id}</td>
					<td>${node.updateDate}</td>
					<td>
						<a href="${ctx}/camelv/node/delete?id=${node.id}" onclick="return confirmx('确认要删除该节点信息吗？', this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>