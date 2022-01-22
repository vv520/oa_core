package com.htwy.oa.dao.processdao;

import com.htwy.oa.entity.process.Overtime;
import com.htwy.oa.entity.process.ProcessList;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OvertimeDao extends PagingAndSortingRepository<Overtime, Long>{

	Overtime findByProId(ProcessList pro);

}
