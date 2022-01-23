package com.htwy.oa.service.discuss;

import com.htwy.oa.entity.discuss.Comment;

public interface CommentService {
    //保存
    Comment save(Comment comment);

    void deleteComment(Long comment);

}
