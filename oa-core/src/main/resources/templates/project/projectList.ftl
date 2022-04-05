<div class="bgc-w box box-primary">
    <!--盒子头-->
    <div class="box-header">
        <h3 class="box-title">
            <a href="projectFundsEdit" class="label label-success" style="padding: 5px;">
                <span class="glyphicon glyphicon-plus"></span> 新增
            </a>
        <#--<a href="##" class="label label-success" onclick="queryProjectFunds();" style="padding: 5px;margin-left:5px;">
            <span class="glyphicon glyphicon-search"></span> 查询</a>-->
        </h3>
        <div class="box-tools">
            <div class="input-group" style="width: 150px;">
                <input type="text" class="form-control input-sm search"
                       placeholder="合同查询"/>
                <div class="input-group-btn">
                    <a class="btn btn-sm btn-default"><span
                            class="glyphicon glyphicon-search queryProjectFundsClick"></span></a>
                </div>
            </div>
        </div>
    </div>
    <!--盒子身体-->
    <div class="box-body no-padding">
        <div class="table-responsive">
            <table class="table table-hover table-striped">
                <tr>
                    <th scope="col">合同名称</th>
                    <th scope="col">技术人员</th>
                    <th scope="col">单价</th>
                    <th scope="col">人天月份</th>
                    <th scope="col">人天</th>
                    <th scope="col">项目款</th>
                    <th scope="col">实收金额</th>
                    <th scope="col">开票金额</th>
                    <th scope="col">专票费用</th>
                    <th scope="col">已收款金额</th>
                    <th scope="col">操作</th>
                </tr>
				<#list lists as item>
					<tr>
                        <td><span>${(item.contractName)!''}</span></td>
                        <td><span>${(item.user.realName)!''}</span></td>
                        <td><span>${(item.price)!''}</span></td>
                        <td><span>${(item.month)!''}</span></td>
                        <td><span>${(item.workDays)!''}</span></td>
                        <td><span>${(item.projectAmount)!''}</span></td>
                        <td><span>${(item.paidInAmount)!''}</span></td>
                        <td><span>${(item.invoiceAmount)!''}</span></td>
                        <td><span>${(item.specialTicketCost)!''}</span></td>
                        <td><span>${(item.receivedAmount)!''}</span></td>
                        <td><a href="projectFundsEdit?projectFundsId=${item.projectFundsId}" class="label xiugai"><span
                                class="glyphicon glyphicon-edit"></span> 修改</a> <a
                                onclick="{return confirm('删除该记录将不能恢复，确定删除吗？');};"
                                href="deleteProjectFunds?projectFundsId=${item.projectFundsId}" class="label shanchu"><span
                                class="glyphicon glyphicon-remove"></span> 删除</a></td>
                    </tr>

                </#list>

            </table>
        </div>
    </div>
    <!--盒子尾-->
	<#include "/common/paging.ftl"/>
</div>
