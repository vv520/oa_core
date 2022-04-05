<div class="bgc-w box box-primary">
    <!--盒子头-->
    <div class="box-header">
        <div class="box-title">
            <div class="input-group" style="width: 150px;">
                <input type="text" class="form-control input-sm search"
                       placeholder="年月查询"/>
                <div class="input-group-btn">
                    <a class="btn btn-sm btn-default"><span
                            class="glyphicon glyphicon-search queryProjectReportClick"></span></a>
                </div>
            </div>
        </div>
    </div>
    <!--盒子身体-->
    <div class="box-body no-padding">
        <div class="table-responsive">
            <table class="table table-hover table-striped">
                <tr>
                    <th scope="col">年月</th>
                    <th scope="col">项目款</th>
                    <th scope="col">实收金额</th>
                    <th scope="col">专票费用</th>
                    <th scope="col">工资</th>
                    <th scope="col">个税</th>
                    <th scope="col">五险</th>
                    <th scope="col">公积金</th>
                    <th scope="col">毛利润</th>
                </tr>
				<#list lists as item>
					<tr>
                        <td><span>${(item.month)!''}</span></td>
                        <td><span>${(item.project_amount)!''}</span></td>
                        <td><span>${(item.paid_in_amount)!''}</span></td>
                        <td><span>${(item.special_ticket_cost)!''}</span></td>
                        <td><span>${(item.net_salary)!''}</span></td>
                        <td><span>${(item.personal_income_tax)!''}</span></td>
                        <td><span>${(item.insurance)!''}</span></td>
                        <td><span>${(item.provident_fund)!''}</span></td>
                        <td><span>${(item.income)!''}</span></td>
                    </tr>
                </#list>

            </table>
        </div>
    </div>
    <!--盒子尾-->
	<#include "/common/pagingmybatis.ftl">
</div>
