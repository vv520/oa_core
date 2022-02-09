package com.htwy.oa.dao.user;

import com.htwy.oa.entity.user.User;

import java.util.List;

/**
 * @author Vv
 * @title: UserMapper
 * @description: TODO
 * @date 2022/1/255:22 下午
 */
public interface UserMapper {
    List<User> queryUserList();
}
