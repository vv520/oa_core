package com.htwy.oa.dao.processdao;

import com.htwy.oa.entity.process.Holiday;
import com.htwy.oa.entity.process.ProcessList;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HolidayDao extends PagingAndSortingRepository<Holiday, Long>{

	Holiday findByProId(ProcessList pro);

}
