package com.htwy.oa.service.inform;

import com.htwy.oa.dao.informdao.InformDao;
import com.htwy.oa.dao.informdao.InformRelationDao;
import com.htwy.oa.dao.system.StatusDao;
import com.htwy.oa.dao.system.TypeDao;
import com.htwy.oa.dao.user.UserDao;
import com.htwy.oa.entity.notice.NoticeUserRelation;
import com.htwy.oa.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class InformRelationService {
	@Autowired
	private InformRelationDao informRelationdao;

	@Autowired
	private StatusDao statusDao;

	@Autowired
	private TypeDao typeDao;
	@Autowired
	private InformDao informDao;

	@Autowired
	private UserDao uDao;

	// 保存一个对象
	public NoticeUserRelation save(NoticeUserRelation noticeRelation) {
		return informRelationdao.save(noticeRelation);
	}

	// 保存多个
	public List<NoticeUserRelation> saves(List<NoticeUserRelation> noticeUser) {
		return (List<NoticeUserRelation>) informRelationdao.saveAll(noticeUser);
	}

	// 删除一个中间表
	public void deleteOne(NoticeUserRelation noticeRelation) {
		informRelationdao.delete(noticeRelation);
	}

	// 封装对象，将List<Map<String, Object>>中的值进行封装，例如type_id封装成相对应的名字
	public List<Map<String, Object>> setList(List<Map<String, Object>> list) {
		for (Map<String, Object> map : list) {
			map.put("status", statusDao.findById((Long) map.get("status_id")).get().getStatusName());
			map.put("type", typeDao.findById((Long) map.get("type_id")).get().getTypeName());
			map.put("statusColor", statusDao.findById((Long) map.get("status_id")).get().getStatusColor());
			map.put("userName", uDao.getOne((Long) map.get("user_id")).getUserName());
			map.put("deptName", uDao.getOne((Long) map.get("user_id")).getDept().getDeptName());
			map.put("contain",this.isForward((Long)map.get("relatin_notice_id"), (Long)map.get("relatin_user_id")));
		}
		return list;
	}
	
	private int isForward(Long noticeId,Long userId){
		int count=1;
		if(uDao.findByFatherId(userId).size()>0){
		List<User> users=uDao.findByFatherId(userId);
		if(informRelationdao.findByNoticeId(informDao.getOne(noticeId))!=null){
			List<NoticeUserRelation> nul=informRelationdao.findByNoticeId(informDao.getOne(noticeId));
			for (NoticeUserRelation noticeUserRelation : nul) {
				if(users.contains(noticeUserRelation.getUserId())){
					count=2;
				}
			}
			if(count!=2){
				count=3;
			}
		}
		
		}
		return count;
	}
	
	
}