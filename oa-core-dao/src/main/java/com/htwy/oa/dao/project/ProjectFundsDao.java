package com.htwy.oa.dao.project;

import com.htwy.oa.entity.project.ProjectFunds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * description
 *
 * @author Administrator 2022/02/21 16:34
 */
public interface ProjectFundsDao extends JpaRepository<ProjectFunds, Long> {

    @Query(value = "from ProjectFunds u where 1 = 1 " +
            "and u.contractName = coalesce(?1, u.contractName) " +
            "and u.month = coalesce(?2, u.month)"
    )
    Page<ProjectFunds> queryProjectFundsPage(String contractName, String month, Pageable pa);
}
