package com.htwy.oa.dao.scheduledao;

import com.htwy.oa.entity.schedule.ScheduleList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleDao extends JpaRepository<ScheduleList, Long>{

	@Query("from ScheduleList s where s.user.userId=?1")
	List<ScheduleList> findstart(long userid);
}
