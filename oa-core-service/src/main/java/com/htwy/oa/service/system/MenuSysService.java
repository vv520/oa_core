package com.htwy.oa.service.system;

import com.htwy.oa.entity.system.SystemMenu;
import com.htwy.oa.entity.user.User;

import javax.servlet.http.HttpServletRequest;

public interface MenuSysService {
    //	新增与修改菜单管理的内容
    SystemMenu save(SystemMenu menu);

    //	1、上移下移按钮先改变其他的排序值
    int changeSortId(Integer sortId, Integer arithNum, Long parentId);

    //	2、上移下移按钮先改变自己的排序值
    int changeSortId2(Integer sortId, Integer arithNum, Long menuId);

    void findMenuSys(HttpServletRequest req, User user);

    void findAllMenuSys(HttpServletRequest req);

    /**
     * 在service层执行删除方法
     */
    int deleteThis(Long menuId);

}
