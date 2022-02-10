package com.htwy.oa.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import com.htwy.oa.dao.user.DeptDao;
import com.htwy.oa.entity.user.Dept;
import com.htwy.oa.service.user.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vv
 * @version 1.0
 * @description: TODO
 * @date 2022/2/10 0010 17:44
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptDao deptdao;

    @Override
    public List<String> getDept() {
        List<Dept> depts = (List<Dept>) deptdao.findAll();
        List<String> deptList = new ArrayList<>();
        for(Dept dept : depts){
            deptList.add(dept.getDeptName());
        }
        return deptList;
    }
}
