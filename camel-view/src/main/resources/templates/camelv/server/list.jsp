<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>服务管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp"%>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	   	 	return false;
	    }
		$(function(){
			$(".showUrl").on("click",function(){
				var uri = $(this).text();
				$.ajax({
					url : "${ctx}/camelv/node/getAll",
					type : "post",
					data : {},
					success : function(data) {
						var str = "<table id='treeTable' class='table table-striped table-bordered table-condensed'>";
						str+="<tr><th>序号</th><th>地址</th></tr>";
						var count = 1;
						for(var i in data){
							str+="<tr><td>"+(count++)+"</td><td>http://"+data[i].id+"/"+uri+"</td></tr>";
						}
						str+="</table>";
						$.jBox(str, {
						    title: "访问地址",
						    width: 600,
						    height: 300,
						    buttons: { '关闭': true }
						});
						
					},
					error : function(data) {
						$.jBox.tip("获取节点失败", "error");
					}
				});
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/camelv/server/list">服务列表</a></li>
		<li><a href="${ctx}/camelv/server/form">添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="camelvServer" action="${ctx}/camelv/server/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div>
			<label>服务名称:</label>
			<form:input path="name" htmlEscape="false" maxlength="50" class="input-small" placeholder="请输入服务名称"/>
			<label>服务类型:</label>
			<form:select path="type">
				<form:option value="http">HTTP</form:option>
				<form:option value="activemq">ActiveMQ</form:option>
				<form:option value="rabbitmq">RabbitMQ</form:option>
			</form:select>
			&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>更新者</th>
				<th>类型</th>
				<th>访问地址</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="server">
				<tr>
					<td>${server.name}</td>
					<td>${server.updateBy.name}</td>
					<td>${server.type }</td>
					<td>
						<a href="javascript:void(0);" class="showUrl" title="查看调用地址">${server.url }</a>
					</td>
					<td>
						<a href="${ctx}/camelv/server/form?id=${server.id}">修改</a>
						<a href="${ctx}/camelv/server/edit?id=${server.id}">服务编排</a>
						<a href="${ctx}/camelv/server/delete?id=${server.id}" onclick="return confirmx('确认要删除该服务信息吗？', this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>