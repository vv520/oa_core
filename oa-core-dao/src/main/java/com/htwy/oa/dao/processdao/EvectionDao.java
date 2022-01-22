package com.htwy.oa.dao.processdao;

import com.htwy.oa.entity.process.Evection;
import com.htwy.oa.entity.process.ProcessList;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EvectionDao extends PagingAndSortingRepository<Evection, Long> {

	Evection findByProId(ProcessList process);

}
