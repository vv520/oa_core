package com.htwy.oa.dao.plandao;

import com.htwy.oa.entity.process.EvectionMoney;
import com.htwy.oa.entity.process.Traffic;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TrafficDao extends PagingAndSortingRepository<Traffic, Long>{

	List<Traffic> findByEvection(EvectionMoney money);
}
