package com.htwy.oa.dao.salary;

import com.htwy.oa.entity.salary.Salary;
import com.htwy.oa.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Vv
 * @title: SalaryDao
 * @description: TODO
 * @date 2022/1/279:20 上午
 */
public interface SalaryDao extends JpaRepository<Salary, Long> {

    @Query(value = "from Salary u where 1 = 1 " +
            "and u.userName = coalesce(?1, u.userName) " +
            "and u.month = coalesce(?2, u.month)"
    )
    Page<Salary> querySalaryPage(String userName, String month, Pageable pa);
}
