package com.htwy.oa.service.role;

import com.htwy.oa.entity.role.Role;
import com.htwy.oa.entity.role.Rolepowerlist;
import com.htwy.oa.entity.system.SystemMenu;

import java.util.List;

public interface RoleService {

    void index(List<SystemMenu> menulist, Role rolep);

    //保存一个对象；
    Rolepowerlist sava(Rolepowerlist rolepower);

}
