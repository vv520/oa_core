package com.htwy.oa.service.system.impl;

import com.htwy.oa.dao.system.StatusDao;
import com.htwy.oa.entity.system.SystemStatusList;
import com.htwy.oa.service.system.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusDao statusDao;

    /**
     * 新增和更新方法
     *
     * @param status
     * @return
     */
    public SystemStatusList save(SystemStatusList status) {
        return statusDao.save(status);
    }

    /**
     * 删除方法
     */
    public void deleteStatus(Long statusId) {
        statusDao.deleteById(statusId);
    }

}
