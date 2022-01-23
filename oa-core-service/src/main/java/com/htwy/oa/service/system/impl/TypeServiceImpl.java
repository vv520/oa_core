package com.htwy.oa.service.system.impl;

import com.htwy.oa.dao.system.TypeDao;
import com.htwy.oa.entity.system.SystemTypeList;
import com.htwy.oa.service.system.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    /**
     * 新增和更新
     *
     * @param list
     * @return
     */
    public SystemTypeList save(SystemTypeList list) {
        return typeDao.save(list);
    }

    /**
     * 删除方法
     */
    public void deleteType(Long typeId) {
        typeDao.deleteById(typeId);
    }


}
