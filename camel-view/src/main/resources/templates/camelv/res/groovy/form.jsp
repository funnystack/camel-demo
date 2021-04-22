<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Groovy管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/camelv/codemirror-5.2/lib/codemirror.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/camelv//codemirror-5.2/theme/eclipse.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/camelv//codemirror-5.2/theme/3024-day.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/camelv//codemirror-5.2/theme/3024-nigtht.css" />
	<link type="text/css" rel="stylesheet" href="${ctxStatic}/modules/camelv/codemirror-5.2/addon/hint/show-hint.css" />
	
	<script type="text/javascript" src="${ctxStatic}/modules/camelv/codemirror-5.2/lib/codemirror.js" />
	<script type="text/javascript" src="${ctxStatic}/modules/camelv/codemirror-5.2/mode/groovy/groovy.js" />
	<script type="text/javascript" src="${ctxStatic}/modules/camelv/codemirror-5.2/mode/javascript/javascript.js" />
	<script type="text/javascript" src="${ctxStatic}/modules/camelv/codemirror-5.2/addon/hint/show-hint.js"></script>
	<script type="text/javascript" src="${ctxStatic}/modules/camelv/codemirror-5.2/addon/hint/groovy-hint.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#name").focus();
			$("#inputForm").validate();
			
			/* CodeMirror.keywords = "abstract as assert boolean break byte case catch char class const continue def default " +
		    "do double else enum extends final finally float for goto if implements import in " +
		    "instanceof int interface long native new package private protected public return " +
		    "short static strictfp super switch synchronized threadsafe throw throws transient " +
		    "try void volatile while "+
		    "orchmap exchange String Exchange";
			CodeMirror.tableKeywords = "server.ip server.cache software.conf software.version software.tags.count";  
			CodeMirror.tableKeywords = "get getBody getIn getHeader";   */
			
			var myTextarea = document.getElementById('editor');
				var CodeMirrorEditor = CodeMirror.fromTextArea(myTextarea, {
					mode : "text/x-groovy",
					lineNumbers : true,
					matchBrackets : true,
					fullScreen : true,
					theme : "3024-night",
					extraKeys : {
						  "Ctrl": "autocomplete"  
					}
				});
// 				CodeMirrorEditor.setSize("auto", "220");//auto
				CodeMirrorEditor.on('keypress', function() {
// 					 CodeMirrorEditor.showHint(); 
				});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/camelv/groovy/list">Groovy列表</a></li>
		<li class="active"><a href="${ctx}/camelv/groovy/form?id=${camelvGroovy.id}">${not empty camelvGroovy.id?'修改':'添加'}</a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="camelvGroovy" action="${ctx}/camelv/groovy/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label" for="name">名称</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="script">脚本</label>
			<div class="controls">
				<textarea id="editor" name="script">${fn:escapeXml(camelvGroovy.script )}</textarea>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="sys:dict:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>