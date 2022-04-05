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
        <h1 style="font-size: 24px; margin: 0;" class="">薪资统计</h1>
    </div>
    <div class="col-md-10 text-right">
        <a href="##"><span class="glyphicon glyphicon-home"></span> 首页</a> >
        <a disabled="disabled">薪资统计</a>
    </div>
</div>
<div class="row" style="padding-top: 15px;">
    <div class="col-md-12">
        <div class="bgc-w box">
            <form action="salarySave" method="post" onsubmit="return check();">
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
                            <#if where??>
                                <div class="col-md-6 form-group">
                                    <label class="control-label"><span>姓名</span></label>
                                    <input name="userName" readonly="readonly" class="form-control" value="${(salary.userName)!''}"/>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label class="control-label"><span>月份</span></label>
                                    <input name="month" readonly="readonly" class="form-control" value="${(salary.month)!''}"/>
                                </div>
                            <#else>
                            <#--<div class="col-md-6 form-group">
                                <label class="control-label"><span>姓名</span></label>
                                <input name="userName" class="form-control" value="${(salary.userName)!''}"/>
                            </div>-->
                                <div class="col-md-6 form-group">
                                    <label class="control-label"><span>姓名</span></label>
                                    <select class="selectpicker show-tick form-control" data-live-search="true" name="userId" id="userId">
                                        <#list users as user>
                                            <option value="${user.userId}">${user.realName}</option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label class="control-label"><span>月份</span></label>
                                    <input name="month" class="form-control" value="${(salary.month)!''}"/>
                                </div>
                            </#if>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>上班天数</span></label>
                                <input id="workDays" name="workDays" type="number" step="0.1" class="form-control" value="${(salary.workDays)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>请假天数</span></label>
                                <input id="leaveDays" name="leaveDays" type="number" step="0.1" class="form-control" value="${(salary.leaveDays)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>单价</span></label>
                                <input id="price" name="price" type="number" step="0.01" class="form-control" value="${(salary.price)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>基本工资</span></label>
                                <input id="baseSalary" name="baseSalary" type="number" step="0.01" class="form-control" value="${(salary.baseSalary)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>出差补贴</span></label>
                                <input id="travelAllowance" name="travelAllowance" type="number" step="0.01" class="form-control" value="${(salary.travelAllowance)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>报销费用</span></label>
                                <input id="feeReimburse" name="feeReimburse" type="number" step="0.01" class="form-control" value="${(salary.feeReimburse)!''}"/>
                            </div>

                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>个人公积金</span></label>
                                <input id="personalProvidentFund" name="personalProvidentFund" type="number" step="0.01" class="form-control" value="${(salary.personalProvidentFund)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"> <span>企业公积金</span></label>
                                <input id="companyProvidentFund" name="companyProvidentFund" type="number" step="0.01" class="form-control" value="${(salary.companyProvidentFund)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>个人养老保险</span></label>
                                <input id="personalEndowmentInsurance" name="personalEndowmentInsurance" type="number" step="0.01" class="form-control"
                                       value="${(salary.personalEndowmentInsurance)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"> <span>企业养老保险</span></label>
                                <input id="companyEndowmentInsurance" name="companyEndowmentInsurance" type="number" step="0.01" class="form-control" value="${(salary.companyEndowmentInsurance)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"> <span>个人医疗保险</span></label>
                                <input id="personalMedicalInsurance" name="personalMedicalInsurance" type="number" step="0.01" class="form-control" value="${(salary.personalMedicalInsurance)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"> <span>企业医疗保险</span></label>
                                <input id="companyMedicalInsurance" name="companyMedicalInsurance" type="number" step="0.01" class="form-control" value="${(salary.companyMedicalInsurance)!''}"/>
                            </div>

                            <div class="col-md-6 form-group">
                                <label class="control-label"> <span>个人失业保险</span></label>
                                <input id="personalUnemploymentInsurance" name="personalUnemploymentInsurance" type="number" step="0.01" class="form-control"
                                       value="${(salary.personalUnemploymentInsurance)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"> <span>企业失业保险</span></label>
                                <input id="companyUnemploymentInsurance" name="companyUnemploymentInsurance" type="number" step="0.01" class="form-control"
                                       value="${(salary.companyUnemploymentInsurance)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"> <span>个人工伤保险</span></label>
                                <input id="personalInjuryInsurance" name="personalInjuryInsurance" type="number" step="0.01" class="form-control" value="${(salary.personalInjuryInsurance)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"> <span>企业工伤保险</span></label>
                                <input id="companyInjuryInsurance" name="companyInjuryInsurance" type="number" step="0.01" class="form-control" value="${(salary.companyInjuryInsurance)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"> <span>个人大病医疗</span></label>
                                <input id="personalIllnessInsurance" name="personalIllnessInsurance" type="number" step="0.01" class="form-control" value="${(salary.personalIllnessInsurance)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"> <span>企业大病医疗</span></label>
                                <input id="companyIllnessInsurance" name="companyIllnessInsurance" type="number" step="0.01" class="form-control" value="${(salary.companyIllnessInsurance)!''}"/>
                            </div>

                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>个人所得税</span></label>
                                <input id="personalIncomeTax" name="personalIncomeTax" type="number" step="0.01" class="form-control" value="${(salary.personalIncomeTax)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>其他扣除项</span></label>
                                <input id="otherFee" name="otherFee" type="number" step="0.01" class="form-control" value="${(salary.otherFee)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>实发工资</span></label>
                                <input id="netSalary" name="netSalary" type="number" step="0.01" class="form-control" readonly="readonly" value="${(salary.netSalary)!}"/>
                            </div><#--value="${(salary.netSalary)!}"-->
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>备注</span></label>
                                <input name="remark" class="form-control" value="${(salary.remark)!''}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>员工产出</span></label>
                                <input id="employeeIncome" name="employeeIncome" type="number" step="0.01" readonly="readonly" class="form-control" value="${(salary.employeeIncome)!}"/>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label"><span>公司净收益</span></label>
                                <input id="netIncome" name="netIncome" type="number" step="0.01" readonly="readonly" class="form-control" value="${(salary.netIncome)!}"/>
                            </div>
                            <input type="hidden" name="salaryId" value="${(salary.salaryId)!''}"/>
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
        var baseSalary = Number($("#baseSalary").val());
        var travelAllowance = Number($("#travelAllowance").val());
        var feeReimburse = Number($("#feeReimburse").val());
        var personalProvidentFund = Number($("#personalProvidentFund").val());
        var personalEndowmentInsurance = Number($("#personalEndowmentInsurance").val());
        var personalMedicalInsurance = Number($("#personalMedicalInsurance").val());
        var personalUnemploymentInsurance = Number($("#personalUnemploymentInsurance").val());
        var personalInjuryInsurance = Number($("#personalInjuryInsurance").val());
        var personalIllnessInsurance = Number($("#personalIllnessInsurance").val());
        var personalIncomeTax = Number($("#personalIncomeTax").val());
        var companyProvidentFund = Number($("#companyProvidentFund").val());
        var companyIllnessInsurance = Number($("#companyIllnessInsurance").val());
        var companyEndowmentInsurance = Number($("#companyEndowmentInsurance").val());
        var companyMedicalInsurance = Number($("#companyMedicalInsurance").val());
        var companyUnemploymentInsurance = Number($("#companyUnemploymentInsurance").val());
        var companyInjuryInsurance = Number($("#companyInjuryInsurance").val());
        var workDays = Number($("#workDays").val());
        var price = Number($("#price").val());
        //实发工资 = 基本工资 + 出差补贴 + 报销费用 - 个人五险一金 - 个税;
        var netSalary = baseSalary + travelAllowance + feeReimburse -
                personalProvidentFund - personalEndowmentInsurance - personalMedicalInsurance - personalUnemploymentInsurance -
                personalInjuryInsurance - personalIllnessInsurance - personalIncomeTax;
        console.log(netSalary);
        $("#netSalary").val(netSalary);
        //员工产出 = 天数 * 单价
        var employeeIncome = workDays * price;
        $("#employeeIncome").val(employeeIncome);
        console.log(employeeIncome);
        //公司净收益= 员工产出 - 基本工资 - 出差补贴 - 报销费用 - 企业五险一金;
        var netIncome = employeeIncome - baseSalary - travelAllowance - feeReimburse - companyProvidentFund - companyIllnessInsurance -
                companyEndowmentInsurance - companyMedicalInsurance - companyUnemploymentInsurance - companyInjuryInsurance;
        $("#netIncome").val(netIncome);
        return true;
    }
</script>