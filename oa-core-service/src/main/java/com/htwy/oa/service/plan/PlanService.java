package com.htwy.oa.service.plan;

import com.htwy.oa.entity.plan.Plan;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface PlanService {

    //删除
    Integer delete(long pid);

    //分页
    Page<Plan> paging(int page, String baseKey, long userid, Object type, Object status, Object time);

    Integer updateplan(long typeId, long statusId, Date startTime, Date endTime,
                       String title, String label, String planContent, String planSummary, long pid);
}
