package com.htwy.oa.service.system;

import com.htwy.oa.entity.system.SystemStatusList;

public interface StatusService {

    /**
     * 新增和更新方法
     *
     * @param status
     * @return
     */
    SystemStatusList save(SystemStatusList status);

    /**
     * 删除方法
     */
    void deleteStatus(Long statusId);

}
