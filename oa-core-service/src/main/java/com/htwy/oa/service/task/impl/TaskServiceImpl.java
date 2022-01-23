package com.htwy.oa.service.task.impl;

import com.github.pagehelper.util.StringUtil;
import com.htwy.oa.dao.system.StatusDao;
import com.htwy.oa.dao.system.TypeDao;
import com.htwy.oa.dao.taskdao.TaskDao;
import com.htwy.oa.dao.taskdao.TaskloggerDao;
import com.htwy.oa.dao.taskdao.TaskuserDao;
import com.htwy.oa.dao.user.DeptDao;
import com.htwy.oa.dao.user.UserDao;
import com.htwy.oa.entity.system.SystemStatusList;
import com.htwy.oa.entity.system.SystemTypeList;
import com.htwy.oa.entity.task.Tasklist;
import com.htwy.oa.entity.task.Tasklogger;
import com.htwy.oa.entity.user.User;
import com.htwy.oa.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao tdao;
    @Autowired
    private TaskuserDao tudao;
    @Autowired
    private TaskloggerDao tldao;
    @Autowired
    private UserDao udao;
    @Autowired
    private StatusDao sdao;
    @Autowired
    private TypeDao tydao;
    @Autowired
    private DeptDao ddao;

    public Tasklist save(Tasklist task) {
        return tdao.save(task);
    }

    //修改任务表里面的状态
    public int updateStatusid(Long taskid, Integer statusid) {
        int s = tdao.update(taskid, statusid);
        return s;

    }

    //修改任务表中间表的任务状态
    public int updateUStatusid(Long taskid, Integer statusid) {
        int s = tudao.updatestatus(taskid, statusid);
        return s;

    }

    //删除任务中间表
    public int delete(Long pkid) {
        int i = 0;
        if (!Objects.isNull(pkid)) {
            tudao.deleteById(pkid);
            i = 1;
        }
        return i;

    }

    //删除任务
    public void deteletask(Tasklist task) {
        tdao.delete(task);
    }

    //删除日志表
    public int detelelogger(Long taskid) {
        int i = 0;
        List<Tasklogger> taskLogger = tldao.findByTaskId(taskid);
        if (taskLogger.size() != 0) {
            for (Tasklogger tasklogger2 : taskLogger) {
                tldao.delete(tasklogger2);
            }
            i = 1;
        }
        return i;
    }


    public Page<Tasklist> index(int page, int size, String val, User tu) {
        Page<Tasklist> tasklist = null;
        List<Order> orders = new ArrayList<>();
        Pageable pa = PageRequest.of(page, size);
        if (StringUtil.isEmpty(val)) {
            // 根据发布人id查询任务
            orders.addAll(Arrays.asList(new Order(Direction.DESC, "top"), new Order(Direction.DESC, "modifyTime")));
            Sort sort = Sort.by(orders);
            pa = PageRequest.of(page, size, sort);
            tasklist = tdao.findByUsersId(tu, pa);

        } else if (("类型").equals(val)) {
            tasklist = tdao.findByUsersIdOrderByTypeId(tu, pa);
        } else if (("状态").equals(val)) {
            orders.addAll(Arrays.asList(new Order(Direction.ASC, "cancel"), new Order(Direction.ASC, "statusId")));
            Sort sort = Sort.by(orders);
            pa = PageRequest.of(page, size, sort);
            tasklist = tdao.findByUsersId(tu, pa);
        } else if (("发布时间").equals(val)) {
            tasklist = tdao.findByUsersIdOrderByPublishTimeDesc(tu, pa);
        } else {
            tasklist = tdao.findByTitleLikeAndUsersId(val, tu, pa);
        }
        return tasklist;

    }

    public List<Map<String, Object>> index2(Page<Tasklist> tasklist, User user) {
        String username = user.getUserName();
        String deptname = ddao.findname(user.getDept().getDeptId());
        List<Map<String, Object>> list = new ArrayList<>();
        List<Tasklist> task = tasklist.getContent();
        for (int i = 0; i < task.size(); i++) {
            Map<String, Object> result = new HashMap<>();
            Long statusid = task.get(i).getStatusId().longValue();
            result.put("taskid", task.get(i).getTaskId());
            result.put("typename", tydao.findname(task.get(i).getTypeId()));
            result.put("statusname", sdao.findname(statusid));
            result.put("statuscolor", sdao.findcolor(statusid));
            result.put("title", task.get(i).getTitle());
            result.put("publishtime", task.get(i).getPublishTime());
            result.put("zhiding", task.get(i).getTop());
            result.put("cancel", task.get(i).getCancel());
            result.put("username", username);
            result.put("deptname", deptname);
            list.add(result);
        }
        return list;

    }

    public Page<Tasklist> index3(Long userid, String title, int page, int size) {
        Pageable pa = PageRequest.of(page, size);
        List<Order> orders = new ArrayList<>();
        Page<Tasklist> tasklist = null;
        // 根据接收人id查询任务id
        List<Long> taskid = tudao.findByUserId(userid);
        // 类型
        SystemTypeList type = tydao.findByTypeModelAndTypeName("aoa_task_list", title);
        // 状态
        SystemStatusList status = sdao.findByStatusModelAndStatusName("aoa_task_list", title);
        // 找用户
        User user = udao.findByUserName(title);

        if (StringUtil.isEmpty(title)) {
            orders.addAll(Arrays.asList(new Order(Direction.ASC, "cancel"), new Order(Direction.ASC, "statusId")));
            Sort sort = Sort.by(orders);
            pa = PageRequest.of(page, size, sort);
            if (taskid.size() > 0) {

                tasklist = tdao.findTaskByTaskIds(taskid, pa);
            }
        } else if (!Objects.isNull(type)) {

            tasklist = tdao.findtaskTypeIdAndTaskId(type.getTypeId(), taskid, pa);

        } else if (!Objects.isNull(status)) {
            // Long转换成Integer
            Integer statusid = Integer.parseInt(status.getStatusId().toString());
            // 根据找出的taskid和状态id查找任务
            tasklist = tdao.findtaskStatusIdAndCancelAndTaskId(statusid, taskid, pa);

        } else if (("已取消").equals(title)) {
            tasklist = tdao.findtaskCancelAndTaskId(true, taskid, pa);

        } else if (!Objects.isNull(user)) {

            tasklist = tdao.findtaskUsersIdAndTaskId(user, taskid, pa);

        } else {
            // 根据title和taskid进行模糊查询
            tasklist = tdao.findtaskByTitleLikeAndTaskId(taskid, title, pa);


        }

        return tasklist;
    }

    public List<Map<String, Object>> index4(Page<Tasklist> tasklist, Long userid) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (tasklist != null) {

            List<Tasklist> task = tasklist.getContent();

            for (int i = 0; i < task.size(); i++) {
                Map<String, Object> result = new HashMap<>();
                // 查询任务id
                Long tid = task.get(i).getTaskId();

                // 查询接收人的任务状态id
                Long statusid = tudao.findByuserIdAndTaskId(userid, tid);

                // 查询发布人
                User ptu = udao.getOne(task.get(i).getUsersId().getUserId());
                String username = ptu.getUserName();
                String deptname = ddao.findname(ptu.getDept().getDeptId());

                result.put("taskid", tid);
                result.put("typename", tydao.findname(task.get(i).getTypeId()));
                result.put("statusname", sdao.findname(statusid));
                result.put("statuscolor", sdao.findcolor(statusid));
                result.put("title", task.get(i).getTitle());
                result.put("publishtime", task.get(i).getPublishTime());
                result.put("zhiding", task.get(i).getTop());
                result.put("cancel", task.get(i).getCancel());
                result.put("username", username);
                result.put("deptname", deptname);

                list.add(result);
            }
        }

        return list;
    }

}
