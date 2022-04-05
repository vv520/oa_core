<#include "/common/commoncss.ftl">
<style type="text/css">
    a {
        color: black;
    }

    a:hover {
        text-decoration: none;
    }

    .bgc-w {
        background-color: #fff;
    }
</style>
<div class="row" style="padding-top: 10px;">
    <div class="col-md-2">
        <h1 style="font-size: 24px; margin: 0;" class="">项目款</h1>
    </div>
    <div class="col-md-10 text-right">
        <a href="##"><span class="glyphicon glyphicon-home"></span> 首页</a> >
        <a disabled="disabled">项目款</a>
    </div>
</div>
<div class="row" style="padding-top: 15px;">
    <div class="col-md-12">
        <div class="bgc-w box">
            <form action="projectFundsSave" method="post" onsubmit="return check();">
                <!--盒子头-->
                <div class="box-header">
                    <h3 class="box-title">
                        <a href="javascript:history.back();" class="label label-default" style="padding: 5px;">
                            <i class="glyphicon glyphicon-chevron-left"></i> <span>返回</span>
                        </a>
                    </h3>
                </div>
                <!--盒子身体-->
                <div class="box-body no-padding">
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissable" role="alert"
                             style="display: none;">
                            错误信息:
                            <button class="close thisclose" type="button">&times;</button>
                            <span class="error-mess"></span>
                        </div>
                        <div class="row">
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>合同名称</span></label>
                                <input name="contractName" class="form-control" value="${(projectFunds.contractName)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>技术人员</span></label>
                                <select class="selectpicker show-tick form-control" data-live-search="true"  name="userId">
                                    <#if projectFunds??>
                                        <option value="${(projectFunds.user.userId)!''}">${projectFunds.user.realName}</option>
                                    </#if>
                                    <#list users as user>
                                        <option value="${user.userId}">${user.realName}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>人天月份</span></label>
                                <input name="month" class="form-control" value="${(projectFunds.month)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>人天</span></label>
                                <input name="workDays" class="form-control" value="${(projectFunds.workDays)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>单价</span></label>
                                <input name="price" class="form-control" value="${(projectFunds.price)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>项目款</span></label>
                                <input name="projectAmount" class="form-control" value="${(projectFunds.projectAmount)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>实收金额</span></label>
                                <input name="paidInAmount" class="form-control" value="${(projectFunds.paidInAmount)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>开票金额</span></label>
                                <input name="invoiceAmount" class="form-control" value="${(projectFunds.invoiceAmount)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>专票费用</span></label>
                                <input name="specialTicketCost" class="form-control" value="${(projectFunds.specialTicketCost)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>已收款金额</span></label>
                                <input name="receivedAmount" class="form-control" value="${(projectFunds.receivedAmount)!''}"/>
                            </div>
                            <input type="hidden" name="projectFundsId" value="${(projectFunds.projectFundsId)!''}"/>
                        </div>
                    </div>
                </div>
                <!--盒子尾-->
                <div class="box-footer">
                    <input class="btn btn-primary" id="save" type="submit" value="保存"/>
                    <input class="btn btn-default" id="cancel" type="submit" value="取消"
                           onclick="window.history.back();"/>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="plugins/My97DatePicker/WdatePicker.js"></script>
<#include "/common/modalTip.ftl"/>
<script type="text/javascript">
    //表单提交前执行的onsubmit()方法；返回false时，执行相应的提示信息；返回true就提交表单到后台校验与执行
    function check() {
        return true;
    }
</script>