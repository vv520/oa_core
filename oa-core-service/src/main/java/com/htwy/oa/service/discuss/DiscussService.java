package com.htwy.oa.service.discuss;

import com.htwy.oa.entity.discuss.Comment;
import com.htwy.oa.entity.discuss.Discuss;
import com.htwy.oa.entity.discuss.Reply;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.util.*;

public interface DiscussService {

    // 保存
    Discuss save(Discuss d);

    //删除讨论区
    void deleteDiscuss(Long discussId);


    //查看，并将访问量+1
    Discuss addOneDiscuss(Long id);


    // 分页处理
    Page<Discuss> paging(int page, String baseKey, Long userId, String type, String time, String visitnum);

    /**
     * 用户自己管理讨论区的分页
     *
     * @return
     */
    Page<Discuss> pagingMe(int page, String baseKey, Long userId, String type, String time, String visitnum);

    //用来显示信息
    void setDiscussMess(Model model, Long num, Long userId, int page, int size);

    //处理讨论区信息
    void discussHandle(Model model, Long num, Long userId, int page, int size, Long selectType, Long selectSort);


    //对回复表进行封装
    List<Map<String, Object>> replyPackaging(List<Reply> replyList, Long userId);

    //对评论表进行封装
    List<Map<String, Object>> commentPackaging(List<Comment> commentList);

    List<Map<String, Object>> packaging(List<Discuss> list);

}
