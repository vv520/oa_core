package com.htwy.oa.service.inform;

import com.htwy.oa.entity.notice.NoticeUserRelation;

import java.util.List;
import java.util.Map;

public interface InformRelationService {

    // 保存一个对象
    NoticeUserRelation save(NoticeUserRelation noticeRelation);

    // 保存多个
    List<NoticeUserRelation> saves(List<NoticeUserRelation> noticeUser);

    // 删除一个中间表
    void deleteOne(NoticeUserRelation noticeRelation);

    // 封装对象，将List<Map<String, Object>>中的值进行封装，例如type_id封装成相对应的名字
    List<Map<String, Object>> setList(List<Map<String, Object>> list);


}