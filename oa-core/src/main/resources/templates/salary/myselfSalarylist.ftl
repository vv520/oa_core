<div class="bgc-w box box-primary">
    <!--盒子头-->
    <div class="box-header">
        <h3 class="box-title">
            <a href="##" class="label label-success" onclick="queryMyselfSalary();" style="padding: 5px;margin-left:5px;">
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
                            <div class="col-md-6 control-label" for="select">月份</div>
                            <div class="col-md-6">
                                <input type="text" class="form-control" runat="server" id="month"
                                       name="month"/>
                            </div>
                        </div>
                    </div>
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
