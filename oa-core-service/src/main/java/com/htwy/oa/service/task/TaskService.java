package com.htwy.oa.service.task;

import com.htwy.oa.entity.task.Tasklist;
import com.htwy.oa.entity.user.User;
import org.springframework.data.domain.Page;

import java.util.*;

public interface TaskService {
    Tasklist save(Tasklist task);

    //修改任务表里面的状态
    int updateStatusid(Long taskid, Integer statusid);

    //修改任务表中间表的任务状态
    int updateUStatusid(Long taskid, Integer statusid);

    //删除任务中间表
    int delete(Long pkid);

    //删除任务
    void deteletask(Tasklist task);

    //删除日志表
    int detelelogger(Long taskid);

    Page<Tasklist> index(int page, int size, String val, User tu);

    List<Map<String, Object>> index2(Page<Tasklist> tasklist, User user);

    Page<Tasklist> index3(Long userid, String title, int page, int size);

    List<Map<String, Object>> index4(Page<Tasklist> tasklist, Long userid);

}
