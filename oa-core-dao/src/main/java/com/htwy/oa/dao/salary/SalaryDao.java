package com.htwy.oa.dao.salary;

import com.htwy.oa.entity.salary.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vv
 * @title: SalaryDao
 * @description: TODO
 * @date 2022/1/279:20 上午
 */
public interface SalaryDao extends JpaRepository<Salary, Long> {
}
