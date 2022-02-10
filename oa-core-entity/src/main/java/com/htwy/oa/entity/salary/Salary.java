package com.htwy.oa.entity.salary;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Vv
 * @title: Salary
 * @description: TODO
 * @date 2022/1/253:37 下午
 */
@Data
@Entity
@Table(name = "aoa_salary")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_id")
    private Long salaryId; //主键
    @Column(name = "user_id")
    private Long userId; // '员工用户id
    @Column(name = "user_name")
    private String userName; //员工姓名
    @Column(name = "month")
    private String month; // 月份
    @Column(name = "work_days")
    private Long workDays; // '上班天数
    @Column(name = "leave_days")
    private Long leaveDays; // 请假天数
    @Column(name = "price")
    private Long price; // '单价',
    @Column(name = "base_salary")
    private double baseSalary; // '基本工资',
    @Column(name = "travel_allowance")
    private double travelAllowance; // '出差补贴',
    @Column(name = "fee_reimburse")
    private double feeReimburse; // '费用报销',
    @Column(name = "personal_endowment_insurance")
    private double personalEndowmentInsurance; // '个人养老保险',
    @Column(name = "personal_provident_fund")
    private double personalProvidentFund; // '个人公积金',
    @Column(name = "personal_medical_insurance")
    private double personalMedicalInsurance; // '个人医保',
    @Column(name = "company_endowment_insurance")
    private double companyEndowmentInsurance; // '企业养老保险',
    @Column(name = "company_provident_fund")
    private double companyProvidentFund; // '企业公积金',
    @Column(name = "company_medical_insurance")
    private double companyMedicalInsurance; // '企业医保',
    @Column(name = "other_fee")
    private double otherFee; // '其他扣除项',
    @Column(name = "net_salary")
    private double netSalary; // '实发工资',
    @Column(name = "employee_income")
    private double employeeIncome; // '员工产出',
    @Column(name = "net_income")
    private double netIncome; // '公司净收益',
    @Column(name = "remark")
    private String remark; // '备注',
}