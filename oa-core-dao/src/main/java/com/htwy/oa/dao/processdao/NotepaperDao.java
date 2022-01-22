package com.htwy.oa.dao.processdao;


import com.htwy.oa.entity.process.Notepaper;
import com.htwy.oa.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotepaperDao extends JpaRepository<Notepaper, Long> {
	
	//查找
	@Query(nativeQuery=true,value="SELECT * from aoa_notepaper n where n.notepaper_user_id=?1 ORDER BY n.create_time DESC LIMIT 0,5")
	List<Notepaper> findByUserIdOrderByCreateTimeDesc(long userid);
	
	// 根据用户找便签
	Page<Notepaper> findByUserIdOrderByCreateTimeDesc(User user,Pageable pa);

	// 根据用户找便签
	//Page<Notepaper> findByUserIdOrderByCreateTimeDesc(Pageable page);

	/**
	 * 模糊查询
	 * 
	 * @param baseKey
	 * @param page
	 * @return
	 */
	Page<Notepaper> findByTitleLikeOrderByCreateTimeDesc(String baseKey, Pageable page);
}
