<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%-- <%@ include file="/WEB-INF/views/include/dialog.jsp"%> --%>
<html>
<head>
	<title>日志管理</title>
	<meta name="decorator" content="default"/>
	<!-- 弹窗导入 -->
	<link href="${pageContext.request.contextPath}/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.css" rel="stylesheet" />
	<script src="${pageContext.request.contextPath}/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/jquery-jbox/2.3/i18n/jquery.jBox-zh-CN.min.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	  	  	return false;
	    }
		
		$("#name").focus();
		
		$(function() {
			
			$(".logDetail").on("click",function(){
				var id = $(this).attr("logId");
				 $.jBox('iframe:${ctx}/camelv/log/detail?id='+id, {
						title : "详情",
						width : 650,
						height : 450,
						persistent : false,
						dragLimit : false,
						buttons : {
							'关闭' : true
						}
				});
			});
		});
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/camelv/log/list">日志列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="camelvLog" action="${ctx}/camelv/log/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div>
			<label>服务名称:</label>
			<form:input path="name" htmlEscape="false" maxlength="50" class="input-small" placeholder="请输入服务名称"/>
			<label>报文体:</label>
			<form:input path="requestBody" htmlEscape="false" maxlength="50" class="input-small" placeholder="请输入报文"/>
			<label>开始时间：</label>
			<input id="startDate"  name="startDate"  type="text" readonly="readonly" maxlength="20" class="input-small Wdate"  value="<fmt:formatDate value="${camelvLog.startDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			<label>结束时间：</label>
			<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate" value="<fmt:formatDate value="${camelvLog.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>服务名称</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="log">
				<tr>
					<td>${log.name}</td>
					<td>${log.startDate}</td>
					<td>${log.endDate}</td>
					<td>
						<c:choose>
							<c:when test="${log.status == 0 }"><span class="label btn btn-success">成功</span></c:when>
							<c:otherwise><span class="label btn btn-danger">失败</span></c:otherwise>
						</c:choose>
					</td>
					<td>
						<a href="javascript:void(0);"  class="logDetail" logId="${log.id }">详情</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>