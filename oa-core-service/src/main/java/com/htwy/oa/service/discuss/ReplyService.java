package com.htwy.oa.service.discuss;

import com.htwy.oa.entity.discuss.Reply;

public interface ReplyService {

    // 保存对象至数据库
    Reply save(Reply reply);

    // 删除一个回复
    void deleteReply(Reply reply);

}
