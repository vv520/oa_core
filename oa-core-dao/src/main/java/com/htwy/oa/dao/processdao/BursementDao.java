package com.htwy.oa.dao.processdao;

import com.htwy.oa.entity.process.Bursement;
import com.htwy.oa.entity.process.ProcessList;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BursementDao extends PagingAndSortingRepository<Bursement, Long>{

	Bursement findByProId(ProcessList process);
	
	

}
