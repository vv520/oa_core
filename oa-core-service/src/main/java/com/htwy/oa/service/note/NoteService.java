package com.htwy.oa.service.note;

import com.htwy.oa.entity.note.Note;
import org.springframework.data.domain.Page;

public interface NoteService {

    //删除
    int delete(long noteId);

    int updatecollect(long isCollected, long noteId);


    int updatenote(Long catalogId, Long typeId, Long statusId, String title, String content, Long noteId);

    //排序分页
    Page<Note> sortpage(int page, String baseKey, long userid, Long isCollected, Long catalogId, Long typeId, Object type, Object status, Object time);
}
