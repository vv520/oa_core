package com.htwy.oa.service.user;

import com.htwy.oa.entity.user.LoginRecord;
import org.springframework.data.domain.Page;

public interface UserLogRecordService {

    Page<LoginRecord> ulogpaging(int page, String basekey, Long userid, Object time);
}
