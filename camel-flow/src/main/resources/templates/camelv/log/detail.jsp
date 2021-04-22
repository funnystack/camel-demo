<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>日志详情</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	
</script>
</head>
<body>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<tr>
			<th>服务名称</th>
			<td>${camelvLog.name }</td>
		</tr>
		<tr>
			<th>开始时间</th>
			<td>${camelvLog.startDate }</td>
		</tr>
		<tr>
			<th>结束时间</th>
			<td>${camelvLog.endDate }</td>
		</tr>
		<tr>
			<th>状态</th>
			<td>
				<c:choose>
					<c:when test="${camelvLog.status == 0 }"><span class="label btn btn-success">成功</span></c:when>
					<c:otherwise><span class="label btn btn-danger">失败</span></c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th>请求头</th>
			<td><textarea rows="10" cols="10" style="width: 95%">${camelvLog.requestHeader }</textarea></td>
		</tr>
		<tr>
			<th>请求体</th>
			<td><textarea rows="10" cols="10" style="width: 95%">${camelvLog.requestBody }</textarea></td>
		</tr>
		<tr>
			<th>响应头</th>
			<td><textarea rows="10" cols="10" style="width: 95%">${camelvLog.responseHeader }</textarea></td>
		</tr>
		<tr>
			<th>响应体</th>
			<td><textarea rows="10" cols="10" style="width: 95%">${camelvLog.responseBody }</textarea></td>
		</tr>
	</table>
</body>
</html>