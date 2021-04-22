<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Groovy管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp"%>
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
		<li class="active"><a href="${ctx}/camelv/groovy/list">Groovy列表</a></li>
		<li><a href="${ctx}/camelv/groovy/form">添加</a></li>
	</ul>
	 <form:form id="searchForm" modelAttribute="camelvJdbc" action="${ctx}/camelv/groovy/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form> 
	<tags:message content="${message}"/>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="groovy">
				<tr>
					<td>${groovy.name}</td>
					<td>
						<a href="${ctx}/camelv/groovy/form?id=${groovy.id}">修改</a>
						<a href="${ctx}/camelv/groovy/delete?id=${groovy.id}" onclick="return confirmx('确认要删除Groovy脚本信息吗？', this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>