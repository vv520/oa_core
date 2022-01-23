package com.htwy.oa.service.user;

import com.htwy.oa.entity.user.User;
import org.springframework.data.domain.Page;

public interface UserService {
    //找到该管理员下面的所有用户并且分页
    Page<User> findmyemployuser(int page, String baseKey, long parentid);
}
