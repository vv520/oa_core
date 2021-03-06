package com.htwy.oa.service.address.impl;

import com.htwy.oa.dao.address.AddressUserDao;
import com.htwy.oa.entity.note.DirectorUser;
import com.htwy.oa.service.address.AddreddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddreddUserServiceImpl implements AddreddUserService {

    @Autowired
    AddressUserDao addressUserDao;

    //保存一个通讯录联系人对象
    public DirectorUser save(DirectorUser directorUser) {
        return addressUserDao.save(directorUser);
    }

    //保存通讯录联系的集合
    public List<DirectorUser> savaList(List<DirectorUser> dus) {
        return addressUserDao.saveAll(dus);
    }

    //删除一个通讯录联系人对象
    public void deleteObj(DirectorUser directorUser) {
        addressUserDao.delete(directorUser);

    }
}
