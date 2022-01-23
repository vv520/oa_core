package com.htwy.oa.service.note.impl;

import com.htwy.oa.dao.notedao.AttachmentDao;
import com.htwy.oa.service.note.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class AttachServiceImpl implements AttachService {

	@Autowired
    AttachmentDao attachmentDao;
	
	public Integer updateatt(String attname, String attpath, String shu, Long size,String type, Date uptime,Long attid) {
		return attachmentDao.updateatt(attname, attpath, shu, size, type, uptime, attid);
	}
	
	
}
