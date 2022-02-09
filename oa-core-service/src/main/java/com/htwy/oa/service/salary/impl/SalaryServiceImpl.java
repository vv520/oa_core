package com.htwy.oa.service.salary.impl;

import cn.hutool.core.date.DateUtil;
import com.htwy.oa.dao.salary.SalaryMapper;
import com.htwy.oa.dao.user.UserDao;
import com.htwy.oa.entity.salary.Salary;
import com.htwy.oa.entity.user.User;
import com.htwy.oa.service.salary.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vv
 * @title: SalaryServiceImpl
 * @description: TODO
 * @date 2022/1/252:24 下午
 */
@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryMapper salaryMapper;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Salary> queryAllSalary(Map param) {
        return salaryMapper.queryAllSalary(param);
    }

    @Override
    public void calculation(String month) {
        if (month == null) {
            month = DateUtil.format(new Date(), "yyyy-MM");
        }
        //查找当月是否存在数据（已计算）
        Map<String, Object> param = new HashMap<>();
        param.put("month", month);
        List<Salary> list = salaryMapper.queryAllSalary(param);
        if(list != null && list.size() > 0){
            throw new RuntimeException("当月已计算");
        }
        //salaryMapper.deleteByMonth(month);
        //查找计算用户
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            double insuranceBase = user.getSocialSecurityBase(); //个人参保基数
            double fundbase = user.getFundBase(); //个人公积金基数
            Salary salary = new Salary();
            salary.setUserId(user.getUserId());
            salary.setUserName(user.getUserName());
            salary.setMonth(month);  //月份
            salary.setWorkDays(21L); //工作天数
            salary.setLeaveDays(0L); //请假天数
            salary.setPrice(1300L);  //单价
            salary.setBaseSalary(user.getSalary()); //基本工资
            salary.setTravelAllowance(0);  //出差补贴
            salary.setFeeReimburse(0); //费用报销
            salary.setPersonalEndowmentInsurance(insuranceBase * 0.08); //个人养老保险 = 个人参保基数 * 0.08
            salary.setPersonalProvidentFund(fundbase * 0.05); //个人公积金 = 个人公积金基数 * 0.05
            salary.setPersonalMedicalInsurance(insuranceBase * 0.02);//个人医保 = 个人参保基数 * 0.02
            salary.setCompanyEndowmentInsurance(insuranceBase * 0.16);//企业养老保险 = 个人参保基数 * 0.16
            salary.setCompanyProvidentFund(fundbase * 0.05);//企业公积金 = 个人公积金基数 * 0.05
            salary.setCompanyMedicalInsurance(insuranceBase * 0.06);//企业医保 = 个人参保基数 * 0.06
            salary.setOtherFee(0);//其他扣除项
            //实发工资 = 基本工资 + 出差补贴 + 费用报销 - 个人养老保险 - 个人公积金 - 个人医保 - 个人所得税
            double netSalary = salary.getBaseSalary() + salary.getTravelAllowance() + salary.getFeeReimburse()
                    - salary.getPersonalEndowmentInsurance() - salary.getPersonalProvidentFund() - salary.getPersonalMedicalInsurance();
            salary.setNetSalary(netSalary);
            salary.setEmployeeIncome(salary.getWorkDays() * salary.getPrice());//员工产出 = 工作天数 * 单价
            salary.setNetIncome(0);//公司净收益 = 员工产出 * 0.87 - （基本工资 + 出差补贴 + 费用报销） - 企业五险一金
            salary.setRemark(""); //备注
            salaryMapper.insert(salary);
        }
    }
}
