package com.htwy.oa.service.salary;

import com.htwy.oa.entity.salary.Salary;

import java.util.List;
import java.util.Map;

/**
 * @author Vv
 * @title: SalaryService
 * @description: TODO
 * @date 2022/1/252:23 下午
 */
public interface SalaryService {
    List<Salary> queryAllSalary(Map param);

    void calculation(String month);
}
