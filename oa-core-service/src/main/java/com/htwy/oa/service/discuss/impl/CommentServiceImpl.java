package com.htwy.oa.service.discuss.impl;

import com.htwy.oa.dao.discuss.CommentDao;
import com.htwy.oa.entity.discuss.Comment;
import com.htwy.oa.service.discuss.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    //保存
    public Comment save(Comment comment) {
        return commentDao.save(comment);
    }

    public void deleteComment(Long comment) {
        commentDao.deleteById(comment);
    }

}
