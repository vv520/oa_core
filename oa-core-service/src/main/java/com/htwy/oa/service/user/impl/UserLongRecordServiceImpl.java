package com.htwy.oa.service.user.impl;

import com.htwy.oa.dao.user.UserLogRecordDao;
import com.htwy.oa.entity.user.LoginRecord;
import com.htwy.oa.service.user.UserLongRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserLongRecordServiceImpl implements UserLongRecordService {
    @Autowired
    private UserLogRecordDao ulDao;

    public LoginRecord save(LoginRecord lr) {
        return ulDao.save(lr);
    }

}
