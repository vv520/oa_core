package com.htwy.oa.service.inform;

import com.htwy.oa.entity.notice.NoticesList;
import org.springframework.data.domain.Page;

import java.util.*;

public interface InformService {
    // 保存通知
    NoticesList save(NoticesList noticelist);

    // 删除通知
    void deleteOne(Long noticeId);

    // 封装
    List<Map<String, Object>> fengZhuang(List<NoticesList> noticelist);

    Page<NoticesList> pageThis(int page, Long userId);

    Page<NoticesList> pageThis(int page, Long userId, String baseKey, Object type, Object status, Object time);

}
