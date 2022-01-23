package com.htwy.oa.service.system;

import com.htwy.oa.entity.system.SystemTypeList;

public interface TypeService {

    /**
     * 新增和更新
     *
     * @param list
     * @return
     */
    SystemTypeList save(SystemTypeList list);

    /**
     * 删除方法
     */
    void deleteType(Long typeId);


}
