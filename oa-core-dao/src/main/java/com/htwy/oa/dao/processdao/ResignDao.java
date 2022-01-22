package com.htwy.oa.dao.processdao;

import com.htwy.oa.entity.process.ProcessList;
import com.htwy.oa.entity.process.Resign;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResignDao extends PagingAndSortingRepository<Resign, Long>{

	Resign findByProId(ProcessList process);

}
