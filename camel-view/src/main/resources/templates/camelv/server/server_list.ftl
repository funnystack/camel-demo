<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>员工管理</title>
    <link rel="shortcut icon" href="/favicon.ico">
    <link href="/css/bootstrap.min.css?v=3.3.7" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">


    <link href="/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="/plugins/layui/css/layui.css" rel="stylesheet">
	<link href="/plugins/sweetalert/sweetalert.css" rel="stylesheet">
	<link href="/plugins/multiselect/multi-select.css" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
	    <!-- begin查询条件 -->
	    <div class="row">
	        <div class="col-sm-12">
	            <div class="ibox">
	                <div class="ibox-title">
	                    <h5>查询条件</h5>
	                    <div class="ibox-tools">
	
	                    </div>
	                </div>
	                <div class="ibox-content">
	                    <form class="form-horizontal" method="post" id="form-search">
	                        <input type="hidden" id="page-num" name="pageNum" value="1"/>
	                        <input type="hidden" id="page-size" name="pageSize" value="10"/>
	                        <div class="row">
								<div class="col-sm-3">
									<div class="form-group">
										<label class="col-sm-4 control-label" style="padding-left: 5px;padding-right: 5px;">用户名：</label>
										<div class="col-sm-8">
											<input placeholder="用户名" class="form-control input-sm" name="userName" >
										</div>
									</div>
								</div>

								<div class="col-sm-3">
									<div class="form-group">
										<label class="col-sm-4 control-label" style="padding-left: 5px;padding-right: 5px;">姓名：</label>
										<div class="col-sm-8">
											<input placeholder="姓名" class="form-control input-sm"  name="realName" >
										</div>
									</div>
								</div>

								<div class="col-sm-3">
	                                <div class="form-group">
	                                    <label class="col-sm-4 control-label" style="padding-left: 5px;padding-right: 5px;">手机：</label>
	                                    <div class="col-sm-8">
	                                        <input placeholder="手机" class="form-control input-sm" name="mobile" >
	                                    </div>
	                                </div>
	                            </div>

								<div class="col-sm-3">
									<div class="form-group">
										<label class="col-sm-4 control-label" style="padding-left: 5px;padding-right: 5px;">工号：</label>
										<div class="col-sm-8">
											<input placeholder="工号" class="form-control input-sm"  name="workNo" >
										</div>
									</div>
								</div>

	                        </div>


							<div class="row">
								<div class="col-sm-3">
									<div class="form-group">
										<label class="col-sm-4 control-label" style="padding-left: 5px;padding-right: 5px;">邮箱：</label>
										<div class="col-sm-8">
											<input placeholder="邮箱" class="form-control input-sm"  name="email" >
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label class="col-sm-4 control-label" style="padding-left: 5px;padding-right: 5px;">用户状态：</label>
										<div class="col-sm-8">
											<select data-placeholder="用户状态" class="chosen-select" name="userStatus">
												<option value="">全部</option>
												<option value="1">正常</option>
												<option value="2">禁用</option>
											</select>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label class="col-sm-4 control-label" style="padding-left: 5px;padding-right: 5px;">部门：</label>
										<div class="col-sm-8">
											<select data-placeholder="部门" class="chosen-select" name="departmentId">
                                                <option value="">全部</option>
												<#if (departmentEntityList??) && (departmentEntityList?size>0)>
													<#list departmentEntityList as depart>
														<option value="${depart.id}">${depart.departName!""}</option>
													</#list>
												</#if>
											</select>
										</div>
									</div>
								</div>
							</div>

	                        <div class="row">
								<div class="col-sm-3" style="text-align: right;">
									<button id="btn-search" type="button" class="btn btn-primary btn-sm " style="min-width:50px">查询</button>
									<button id="btn-reset" type="button" class="btn btn-primary btn-sm " style="min-width:50px">重置</button>
								</div>
	                        </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>
        <!-- end查询条件 -->
        <!-- begin数据表格 -->
        <div class="row">
   			<div class="col-sm-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5 id="header-role-user" >用户列表</h5>
                    <div class="ibox-tools" style="margin-top: -7px;">
						<a data-toggle="modal" href="#modal-form-add-user" data-id="" class="btn btn-primary btn-sm" style="min-width:100px;color: #fff;">
							<i class="fa fa-plus"></i> 新建用户
						</a>
                    </div>
                </div>
                <div id="search-content" class="ibox-content" style="padding:5px;">

                </div>
            </div>
   			</div>
   		</div>
  		<!-- end数据表格 -->
		<div id="modal-form-add-user" class="modal fade" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">新建用户</h4>
					</div>
					<div class="modal-body">
						<form id="form-add-user" role="form" method="post" class="form-horizontal">
							<div class="row">
								<div class="form-group">
									<label class="col-sm-3 control-label" style="padding-left: 5px;padding-right: 5px;">用户名：</label>
									<div class="col-sm-7">
										<input placeholder="用户名" class="form-control input-sm" type="text" id="add-user-name" name="userName" maxlength="10" required="true" aria-required="true">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-3 control-label" style="padding-left: 5px;padding-right: 5px;">真实姓名：</label>
									<div class="col-sm-7">
										<input placeholder="真实姓名" class="form-control input-sm" type="text" id="add-real-name" name="realName" maxlength="10" required="true" aria-required="true">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-3 control-label" style="padding-left: 5px;padding-right: 5px;">手机号：</label>
									<div class="col-sm-7">
										<input placeholder="手机号" class="form-control input-sm" type="text" id="add-mobile" name="mobile" maxlength="11" required="true" mobile="true" aria-required="true">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-3 control-label" style="padding-left: 5px;padding-right: 5px;">邮箱：</label>
									<div class="col-sm-7">
										<input placeholder="邮箱" class="form-control input-sm" type="text" id="add-email" name="email" maxlength="20" required="true"  email="true"  aria-required="true">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-3 control-label" style="padding-left: 5px;padding-right: 5px;">工号：</label>
									<div class="col-sm-7">
										<input placeholder="工号" class="form-control input-sm" type="text" id="add-work-no" name="workNo" maxlength="10" required="true" aria-required="true">
									</div>
								</div>
							</div>

							<div class="row">
								<div class="form-group">
									<label class="col-sm-3 control-label" style="padding-left: 5px;padding-right: 5px;">部门：</label>
									<div class="col-sm-7">
										<select data-placeholder="部门" class="chosen-select" id="add-depart" name="departmentId">

											<#if (departmentEntityList??) && (departmentEntityList?size>0)>
											<#list departmentEntityList as depart>
												<option value="${depart.id}">${depart.departName!""}</option>
											</#list>
											</#if>
										</select>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="form-group">
									<label class="col-sm-3 control-label" style="padding-left: 5px;padding-right: 5px;">角色：</label>
									<div class="col-sm-7">
										<select data-placeholder="角色" multiple="multiple" id="add-role-select" name="roleIds">

											<#if (allRoles??) && (allRoles?size>0)>
												<#list allRoles as role>
													<option value="${role.id}">${role.roleName!""}</option>
												</#list>
											</#if>
										</select>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button id="btn-add-user" type="button" class="btn btn-primary">保存</button>
					</div>
				</div>
			</div>
		</div>

	    <div id="modal-form-edit-user" class="modal fade" aria-hidden="true">
	        <div class="modal-dialog">
	            <div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">编辑用户</h4>
					</div>
	                <div class="modal-body">
						<form id="form-edit-user" role="form" method="post" class="form-horizontal">
							<input type="hidden" name="userId" id="edit-user-id">
							<div class="row">
								<div class="form-group">
									<label class="col-sm-3 control-label" style="padding-left: 5px;padding-right: 5px;">用户名：</label>
									<div class="col-sm-7">
										<input placeholder="用户名" class="form-control input-sm" type="text"  id="edit-user-name" name="userName" maxlength="10" required="true" aria-required="true">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-3 control-label" style="padding-left: 5px;padding-right: 5px;">真实姓名：</label>
									<div class="col-sm-7">
										<input placeholder="真实姓名" class="form-control input-sm" type="text" id="edit-real-name" name="realName" maxlength="10" required="true" aria-required="true">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-3 control-label" style="padding-left: 5px;padding-right: 5px;">手机号：</label>
									<div class="col-sm-7">
										<input placeholder="手机号" class="form-control input-sm" type="text" id="edit-mobile" name="mobile" maxlength="11" required="true" mobile="true" aria-required="true">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-3 control-label" style="padding-left: 5px;padding-right: 5px;">邮箱：</label>
									<div class="col-sm-7">
										<input placeholder="邮箱" class="form-control input-sm" type="text" id="edit-email" name="email" maxlength="20" required="true" email="true" aria-required="true">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-3 control-label" style="padding-left: 5px;padding-right: 5px;">工号：</label>
									<div class="col-sm-7">
										<input placeholder="工号" class="form-control input-sm" type="text" id="edit-work-no" name="workNo" maxlength="10" required="true" aria-required="true">
									</div>
								</div>
							</div>

							<div class="row">
								<div class="form-group">
									<label class="col-sm-3 control-label" style="padding-left: 5px;padding-right: 5px;">部门：</label>
									<div class="col-sm-7">
										<select data-placeholder="部门" class="chosen-select" id="edit-depart" name="departmentId">

											<#if (departmentEntityList??) && (departmentEntityList?size>0)>
												<#list departmentEntityList as depart>
													<option value="${depart.id}">${depart.departName!""}</option>
												</#list>
											</#if>
										</select>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="form-group">
									<label class="col-sm-3 control-label" style="padding-left: 5px;padding-right: 5px;">角色：</label>
									<div class="col-sm-7">
										<select data-placeholder="角色" multiple="multiple" id="edit-role-select" name="roleIds">
											<#if (allRoles??) && (allRoles?size>0)>
												<#list allRoles as role>
													<option value="${role.id}">${role.roleName!""}</option>
												</#list>
											</#if>
										</select>
									</div>
								</div>
							</div>

						</form>
	                </div>
					<div class="modal-footer ">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button id="btn-edit-user" type="button" class="btn btn-primary">保存</button>
					</div>
	            </div>
	        </div>
	    </div>
    </div>
    <!-- 全局js -->
    <script src="/js/jquery.min.js?v=2.1.4"></script>
    <script src="/js/bootstrap.min.js?v=3.3.7"></script>
	<script src="/js/jquery.form.js"></script>
    <!-- 自定义js -->
    <script src="/js/menu.js?v=1.0.0"></script>


    <!-- iCheck -->
    <script src="/plugins/iCheck/icheck.min.js"></script>
    
    <!-- jsTree plugin javascript -->
    <script src="/plugins/jsTree/jstree.min.js"></script>
    
    <script src="/plugins/chosen/chosen.jquery.js"></script>
    <script src="/plugins/jquery-bootpag/jquery.bootpag.min.js"></script>
    <script src="/plugins/validate/jquery.validate.min.js"></script>
    <script src="/plugins/validate/messages_zh.min.js"></script>
    <script src="/plugins/toastr/toastr.min.js"></script>
    <script src="/plugins/layui/layui.all.js"></script>
	<script src="/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="/plugins/multiselect/jquery.multi-select.js"></script>
	<script src="/js/module/user.js?v=1.0.011"></script>
    <script>
    	User.init();
    </script>

</body>

</html>
