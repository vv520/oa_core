package com.htwy.oa.service.user;

import com.htwy.oa.entity.user.UserLog;
import org.springframework.data.domain.Page;

public interface UserLogService {

    Page<UserLog> ulogpaging(int page, String basekey, Long userid, Object time);
}
