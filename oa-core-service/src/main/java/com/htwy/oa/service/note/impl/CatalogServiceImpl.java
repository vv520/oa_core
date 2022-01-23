package com.htwy.oa.service.note.impl;

import com.htwy.oa.dao.notedao.CatalogDao;
import com.htwy.oa.service.note.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    CatalogDao catalogDao;

    //删除
    public int delete(long catalogId) {
        return catalogDao.delete(catalogId);
    }


}
