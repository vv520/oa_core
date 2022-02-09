package com.htwy.oa.dao.salary;

import com.htwy.oa.entity.role.Role;
import com.htwy.oa.entity.salary.Salary;
import com.htwy.oa.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

/**
 * @author Vv
 * @title: SalaryMapper
 * @description: TODO
 * @date 2022/1/252:12 下午
 */
@Mapper
public interface SalaryMapper {
    /**
     * 查找薪资
     *
     * @param param
     * @return
     */
    List<Salary> queryAllSalary(Map param);

    void insert(Salary salary);

    void deleteByMonth(String month);

    List<Salary> selectByMonth(String month);
}
