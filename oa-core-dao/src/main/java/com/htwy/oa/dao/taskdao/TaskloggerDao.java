package com.htwy.oa.dao.taskdao;

import com.htwy.oa.entity.task.Tasklogger;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskloggerDao extends PagingAndSortingRepository<Tasklogger, Long>{
	
	@Query("select tl from Tasklogger tl where tl.taskId.taskId=:id")
	List<Tasklogger> findByTaskId(@Param("id")Long id);
}
