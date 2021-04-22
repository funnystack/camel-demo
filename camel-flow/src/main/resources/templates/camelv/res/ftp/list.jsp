<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>ftp管理</title>
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
		<li class="active"><a href="${ctx}/camelv/ftp/list">FTP列表</a></li>
		<li><a href="${ctx}/camelv/ftp/form">添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="camelvFtp" action="${ctx}/sys/dict/" method="post" class="breadcrumb form-search">
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
				<th>类型</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="ftp">
				<tr>
					<td>${ftp.name}</td>
					<td>${ftp.ip}</td>
					<td>${ftp.port}</td>
					<td>${ftp.username}</td>
					<td>${ftp.password}</td>
					<td>${ftp.type}</td>
					<td>
						<a href="${ctx}/camelv/ftp/form?id=${ftp.id}">修改</a>
						<a href="${ctx}/camelv/ftp/delete?id=${ftp.id}" onclick="return confirmx('确认要删除该FTP信息吗？', this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>