package com.htwy.oa.dao.processdao;

import com.htwy.oa.entity.process.EvectionMoney;
import com.htwy.oa.entity.process.ProcessList;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EvectionMoneyDao extends PagingAndSortingRepository<EvectionMoney, Long>{

	EvectionMoney findByProId(ProcessList pro);
}
