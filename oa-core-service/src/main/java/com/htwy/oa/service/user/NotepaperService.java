package com.htwy.oa.service.user;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface NotepaperService {

    void delete(Long id);

    //@PostConstruct
    void userpanelController();

    /**
     * 上传头像
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    String upload(MultipartFile file) throws IllegalStateException, IOException;
}
