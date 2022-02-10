<div class="bgc-w box box-primary">
    <!--盒子头-->
    <div class="box-header">
        <h3 class="box-title">
            <a href="useredit" class="label label-success" style="padding: 5px;">
                <span class="glyphicon glyphicon-plus"></span> 新增
            </a>
            <a href="##" class="label label-success" onclick="queryUser();" style="padding: 5px;">
                <span class="glyphicon glyphicon-search"></span> 查询</a>
        </h3>
        <div class="panel-body">
            <div class="row">
                <div class="col-lg-3 col-sm-3 col-xs-3 col-xxs-3">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-6 control-label" for="select">用户名</div>
                            <div class="col-md-6">
                                <input type="text" class="form-control" runat="server" id="username"
                                       name="username"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-3 col-xs-3 col-xxs-3">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-6 control-label" for="select">部门</div>
                            <div class="col-md-6">
                                <select id="dept" name="dept" runat="server" AutoPostBack="True" class="form-control">
                                <option value="">---请选择---</option>
                                    <#list depts as dept>
                                        <option value="${dept.deptName}">${dept.deptName}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-3 col-xs-3 col-xxs-3">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-6 control-label" for="select">日期</div>
                            <div class="col-md-6">
                                <input type="text" id="txtfrom" runat="server" class="form-control"
                                       placeholder="选择开始日期" onfocus="WdatePicker({dateFmt:'yyyy/MM/dd'})">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-3 col-xs-3 col-xxs-3">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-6 control-label" for="select">角色</div>
                            <div class="col-md-6">
                                <select ID="CustomersType" runat="server" AutoPostBack="True"
                                        class="form-control">
                                    <option Value="0">全部</option>
                                    <option Value="1">预付用户</option>
                                    <option Value="2">推荐用户</option>
                                    <option Value="3">普通用户</option>
                                    <option Value="4">定额合约后付费商户</option>
                                    <option Value="5">非定额后付费合约商户</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            <#--<div class="col-lg-2 col-sm-2 col-xs-2 col-xxs-2">
                <div class="form-horizontal">
                    <div class="form-group">
                        <div class="input-group-btn">
                            <a class="btn btn-sm btn-default"><span
                                    class="glyphicon glyphicon-search usersearchgo">查询</span></a>
                        </div>
                    </div>
                </div>
            </div>-->
            </div>
            <!--查询 按钮-->
        </div>
    <#--<div class="box-tools">
        <div class="input-group" style="width: 150px;">
            <input type="text" class="form-control input-sm usersearch"
                placeholder="查找..." />
            <div class="input-group-btn">
                <a class="btn btn-sm btn-default"><span
                    class="glyphicon glyphicon-search usersearchgo"></span></a>
            </div>
        </div>
    </div>-->
    </div>
    <!--盒子身体-->
    <div class="box-body no-padding">
        <div class="table-responsive">
            <table class="table table-hover table-striped">
                <tr>

                    <th scope="col">&nbsp;</th>
                    <th scope="col">部门</th>
                    <th scope="col">真实姓名</th>
                    <th scope="col">用户名</th>
                    <th scope="col">角色</th>
                    <th scope="col">电话</th>
                    <th scope="col">工资</th>
                    <th scope="col">操作</th>
                </tr>
				<#list users as user>
					<tr>
                        <td>
                            <!-- <img src="images/handsome.jpg" class="img-circle"
                                style="width: 25px; height: 25px;" /> -->
							<#if user.imgPath?? && user.imgPath!=''  >
								<img style="width: 25px;height: 25px;"
                                     class="profile-user-img img-responsive img-circle"
                                     src="/image/${user.imgPath}"/>
                            <#else>
								<img style="width: 25px;height: 25px;"
                                     class="profile-user-img img-responsive img-circle"
                                     src="images/timg.jpg" alt="images"/>
                            </#if>
                        </td>
                        <td><span>${(user.dept.deptName)!''}</span></td>
                        <td><span>${(user.realName)!''}</span></td>
                        <td><span>${(user.userName)!''}</span></td>
                        <td><span>${(user.role.roleName)!''}</span></td>
                        <td><span>${(user.userTel)!''}</span></td>
                        <td><span>${(user.salary)!''}</span></td>
                        <td><a href="useredit?userid=${user.userId}" class="label xiugai"><span
                                class="glyphicon glyphicon-edit"></span> 修改</a> <a
                                onclick="{return confirm('删除该记录将不能恢复，确定删除吗？');};"
                                href="deleteuser?userid=${user.userId}" class="label shanchu"><span
                                class="glyphicon glyphicon-remove"></span> 删除</a></td>
                    </tr>

                </#list>

            </table>
        </div>
    </div>
    <!--盒子尾-->
	<#include "/common/paging.ftl"/>
</div>
