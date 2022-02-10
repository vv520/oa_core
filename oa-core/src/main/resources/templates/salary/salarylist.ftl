<div class="bgc-w box box-primary">
    <!--盒子头-->
    <div class="box-header">
        <#--<h3 class="box-title">
            <a href="useredit" class="label label-success" style="padding: 5px;">
                <span class="glyphicon glyphicon-plus"></span> 新增
            </a>
        </h3>-->
        <#--<div class="  label  label-back last">
            <span class="input-group-addon" id="basic-addon1">月份</span>
            <input type="text" class="form-control" placeholder="month" aria-describedby="basic-addon1">
        </div>-->
        <h3 class="box-title">
            <a href="salaryCalculation" class="label label-success" style="padding: 5px;">
                <span class="glyphicon glyphicon-pencil"></span> 计算
            </a>
        </h3>
        <div class="box-tools">
            <div class="input-group" style="width: 150px;">
                <input type="text" class="form-control input-sm search"
                       placeholder="月份，姓名查询"/>
                <div class="input-group-btn">
                    <a class="btn btn-sm btn-default"><span
                                class="glyphicon glyphicon-search searchClick"></span></a>
                </div>
            </div>
        </div>
    </div>
    <!--盒子身体-->
    <div class="box-body no-padding">
        <div class="table-responsive">
            <table class="table text-nowrap">
                <tr>
                    <th scope="col">操作</th>
                    <th scope="col">姓名</th>
                    <th scope="col">月份</th>
                    <th scope="col">上班天数</th>
                    <th scope="col">请假天数</th>
                    <th scope="col">单价</th>
                    <th scope="col">基本工资</th>
                    <th scope="col">出差补贴</th>
                    <th scope="col">报销费用</th>
                    <th scope="col">个人养老保险</th>
                    <th scope="col">个人公积金</th>
                    <th scope="col">个人医保</th>
                    <th scope="col">企业养老保险</th>
                    <th scope="col">企业公积金</th>
                    <th scope="col">企业医保</th>
                    <th scope="col">其他扣除项</th>
                    <th scope="col">实发工资</th>
                    <th scope="col">备注</th>
                    <th scope="col">员工产出</th>
                    <th scope="col">公司净收益</th>
                </tr>
                <#list lists as item>
                    <tr>
                        <td><a href="salaryEdit?salaryId=${item.salaryId}" class="label xiugai"><span
                                        class="glyphicon glyphicon-edit"></span> 修改</a></td>
                        <td><span>${(item.userName)!''}</span></td>
                        <td><span>${(item.month)!''}</span></td>
                        <td><span>${(item.workDays)!''}</span></td>
                        <td><span>${(item.leaveDays)!''}</span></td>
                        <td><span>${(item.price)!''}</span></td>
                        <td><span>${(item.baseSalary)!''}</span></td>
                        <td><span>${(item.travelAllowance)!''}</span></td>
                        <td><span>${(item.feeReimburse)!''}</span></td>
                        <td><span>${(item.personalEndowmentInsurance)!''}</span></td>
                        <td><span>${(item.personalProvidentFund)!''}</span></td>
                        <td><span>${(item.personalMedicalInsurance)!''}</span></td>
                        <td><span>${(item.companyEndowmentInsurance)!''}</span></td>
                        <td><span>${(item.companyProvidentFund)!''}</span></td>
                        <td><span>${(item.companyMedicalInsurance)!''}</span></td>
                        <td><span>${(item.otherFee)!''}</span></td>
                        <td><span>${(item.netSalary)!''}</span></td>
                        <td><span>${(item.remark)!''}</span></td>
                        <td><span>${(item.employeeIncome)!''}</span></td>
                        <td><span>${(item.netIncome)!''}</span></td>
                    </tr>
                </#list>
            </table>
        </div>
    </div>
    <!--盒子尾-->
    <#include "/common/paging.ftl"/>
</div>