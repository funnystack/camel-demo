<table class="table table-hover m-b-sm">
    <thead>
    <tr>
    	<th>Id</th>
    	<th>用户名</th>
        <th>姓名</th>
        <th>部门</th>
        <th>手机号</th>
        <th>工号</th>
        <th>邮箱</th>
        <th>状态</th>
        <th style="text-align: right;">操作</th>
    </tr>
    </thead>
    <tbody>
    <#if (pageInfo??) && (pageInfo.list??) && (pageInfo.list?size>0)>
    	<#list pageInfo.list as user>
    	    <tr>
		        <td>${(user.id!'')?c}</td>
		        <td>${user.userName!''}</td>
		        <td>${user.realName!''}</td>
                <td>${user.departmentName!''}</td>
		        <td>${user.mobile!''}</td>
		        <td>${user.workNo!''}</td>
		        <td>${user.email!''}</td>
                <td>
                    <#if user.userStatus == 1>
                        <span class="label label-success">正常</span>
                    <#elseif user.userStatus == 2>
                        <span class="label label-warning">禁用</span>
                    </#if>
                </td>
		        <td class="project-actions">
		            <a data-toggle="modal" href="#modal-form-edit-user" data-id="${(user.id!'')?c}" class="btn btn-primary btn-sm">
		                <i class="fa fa-edit"></i>编辑
		            </a>

                    <#if user.userStatus == 1>
                        <a class="btn btn-danger btn-sm" onclick="User.lockUser('${user.id!}')">
                            <i class="fa fa-delicious"></i>禁用
                        </a>
                    <#elseif user.userStatus == 2>
                        <a class="btn btn-success btn-sm" onclick="User.unlockUser('${user.id!}')">
                            <i class="fa fa-openid"></i>启用
                        </a>
                    </#if>
		        </td>
		    </tr>
        </#list>
    <#else>
	    <tr>
	        <td colspan="11" style="text-align: center">搜索无结果</td>
	    </tr>
    </#if>   
    </tbody>
</table>

<div class="row m-b-sm">
    <div class="col-md-12">
        <div class="col-md-4 pull-left" style="padding-left: 5px;">
            <p>总共${pageInfo.total} 个记录&nbsp;每页${pageInfo.pageSize}&nbsp;当前${pageInfo.pageNum}/${pageInfo.pages}</p>
        </div>
        <div id="search-pagination" class="col-md-8 pull-right" style="text-align: right;padding-right: 5px;">

        </div>
    </div>
</div>
<script type="text/javascript">
	User.initPagination('${pageInfo.pages!0}', '${pageInfo.pageNum!0}');
</script>