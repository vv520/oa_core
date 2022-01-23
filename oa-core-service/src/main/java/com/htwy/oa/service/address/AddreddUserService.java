package com.htwy.oa.service.address;

import com.htwy.oa.entity.note.DirectorUser;

import java.util.List;

public interface AddreddUserService {

    //保存一个通讯录联系人对象
    DirectorUser save(DirectorUser directorUser);

    //保存通讯录联系的集合
    List<DirectorUser> savaList(List<DirectorUser> dus);

    //删除一个通讯录联系人对象
    void deleteObj(DirectorUser directorUser);
}
