package com.htwy.oa.service.discuss.impl;

import com.htwy.oa.dao.discuss.ReplyDao;
import com.htwy.oa.entity.discuss.Reply;
import com.htwy.oa.service.discuss.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyDao replyDao;

    // 保存对象至数据库
    public Reply save(Reply reply) {
        return replyDao.save(reply);
    }

    // 删除一个回复
    public void deleteReply(Reply reply) {
        replyDao.delete(reply);
    }

}
