package com.htwy.oa.dao.processdao;

import com.htwy.oa.entity.process.ProcessList;
import com.htwy.oa.entity.process.Regular;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RegularDao extends PagingAndSortingRepository<Regular, Long>{

	Regular findByProId(ProcessList pro);

}
