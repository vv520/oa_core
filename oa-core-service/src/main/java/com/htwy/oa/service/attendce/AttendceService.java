package com.htwy.oa.service.attendce;

import com.htwy.oa.entity.attendce.Attends;
import org.springframework.data.domain.Page;
import java.util.Date;
import java.util.List;

public interface AttendceService {

    // 删除
    Integer delete(long aid);

    // 更改考勤时间
    Integer updatetime(Date date, String hourmin, Long statusIdlong, long attid);

    // 更新备注
    Integer updatereamrk(String attendsRemark, long attendsId);

    // 分页
    Page<Attends> paging(int page, String baseKey, List<Long> user, Object type, Object status, Object time);

    // 单个用户分页
    Page<Attends> singlepage(int page, String baseKey, long userid, Object type, Object status, Object time);
}
