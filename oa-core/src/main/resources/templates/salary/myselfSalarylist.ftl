<div class="bgc-w box box-primary">
    <!--盒子头-->
    <div class="box-header">
        <div class="box-title">
            <div class="input-group" style="width: 150px;">
                <input type="text" class="form-control input-sm search"
                       placeholder="月份查询"/>
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
                    <th scope="col">姓名</th>
                    <th scope="col">月份</th>
                    <th scope="col">上班天数</th>
                    <th scope="col">请假天数</th>
                    <th scope="col">基本工资</th>
                    <th scope="col">出差补贴</th>
                    <th scope="col">报销费用</th>
                    <th scope="col">个人养老保险</th>
                    <th scope="col">个人公积金</th>
                    <th scope="col">个人医保</th>
                    <th scope="col">其他扣除项</th>
                    <th scope="col">实发工资</th>
                    <th scope="col">备注</th>
                </tr>
                <#list lists as item>
                    <tr>
                        <td><span>${(item.userName)!''}</span></td>
                        <td><span>${(item.month)!''}</span></td>
                        <td><span>${(item.workDays)!''}</span></td>
                        <td><span>${(item.leaveDays)!''}</span></td>
                        <td><span>${(item.baseSalary)!''}</span></td>
                        <td><span>${(item.travelAllowance)!''}</span></td>
                        <td><span>${(item.feeReimburse)!''}</span></td>
                        <td><span>${(item.personalEndowmentInsurance)!''}</span></td>
                        <td><span>${(item.personalProvidentFund)!''}</span></td>
                        <td><span>${(item.personalMedicalInsurance)!''}</span></td>
                        <td><span>${(item.otherFee)!''}</span></td>
                        <td><span>${(item.netSalary)!''}</span></td>
                        <td><span>${(item.remark)!''}</span></td>
                    </tr>
                </#list>
            </table>
        </div>
    </div>
    <!--盒子尾-->
    <#include "/common/paging.ftl"/>
</div>
