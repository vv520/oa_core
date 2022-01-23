package com.htwy.oa.service.daymanage.impl;

import com.htwy.oa.dao.daymanagedao.DaymanageDao;
import com.htwy.oa.dao.user.UserDao;
import com.htwy.oa.entity.schedule.ScheduleList;
import com.htwy.oa.entity.user.User;
import com.htwy.oa.service.daymanage.DaymanageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DaymanageServicesImpl implements DaymanageServices {
    @Autowired
    UserDao udao;
    @Autowired
    DaymanageDao daydao;


    public List<ScheduleList> aboutmeschedule(Long userId) {

        User user = udao.getOne(userId);
        List<User> users = new ArrayList<>();
        users.add(user);
        List<ScheduleList> aboutmerc = new ArrayList<>();

        List<ScheduleList> myschedule = daydao.findByUser(user);
        List<ScheduleList> otherschedule = daydao.findByUsersIn(users);

        for (ScheduleList scheduleList : myschedule) {
            aboutmerc.add(scheduleList);
        }

        for (ScheduleList scheduleList : otherschedule) {
            aboutmerc.add(scheduleList);
        }

//		aboutmerc.addAll(myschedule);
//		aboutmerc.addAll(otherschedule);

        for (ScheduleList scheduleList : aboutmerc) {
            User user1 = scheduleList.getUser();
            scheduleList.setUsername(user1.getRealName());

        }

        return aboutmerc;
    }
}
