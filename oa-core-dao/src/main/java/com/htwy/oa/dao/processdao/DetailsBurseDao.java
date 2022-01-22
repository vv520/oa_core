package com.htwy.oa.dao.processdao;

import com.htwy.oa.entity.process.Bursement;
import com.htwy.oa.entity.process.DetailsBurse;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DetailsBurseDao extends PagingAndSortingRepository<DetailsBurse, Long>{

	List<DetailsBurse> findByBurs(Bursement bu);
}
