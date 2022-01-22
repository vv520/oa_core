package com.htwy.oa.service.role;

import com.htwy.oa.dao.roledao.RolepowerlistDao;
import com.htwy.oa.entity.role.Role;
import com.htwy.oa.entity.role.Rolepowerlist;
import com.htwy.oa.entity.system.SystemMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleService {
	@Autowired
	private RolepowerlistDao rldao;
	
	public void index(List<SystemMenu>  menulist,Role rolep){
		
		for (SystemMenu systemMenu : menulist) {
			
			rldao.save(new Rolepowerlist(rolep,systemMenu));
		}
	}
	//保存一个对象；	
	public Rolepowerlist sava(Rolepowerlist rolepower){
		return rldao.save(rolepower);
	}

}
