package com.htwy.oa.dao.processdao;

import com.htwy.oa.entity.process.EvectionMoney;
import com.htwy.oa.entity.process.Stay;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StayDao extends PagingAndSortingRepository<Stay, Long>{
 
	List<Stay> findByEvemoney(EvectionMoney money);
}
