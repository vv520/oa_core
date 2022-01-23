package com.htwy.oa.service.mail;

import com.htwy.oa.entity.mail.Inmaillist;
import com.htwy.oa.entity.mail.Mailnumber;
import com.htwy.oa.entity.mail.Pagemail;
import com.htwy.oa.entity.note.Attachment;
import com.htwy.oa.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public interface MailServices {

    //@PostConstruct
    void UserpanelController();

    /**
     * 收件箱
     */
    Page<Pagemail> recive(int page, int size, User tu, String val, String title);

    /**
     * 封装json
     */
    List<Map<String, Object>> mail(Page<Pagemail> mail);

    /**
     * 发件箱
     */
    Page<Inmaillist> inmail(int page, int size, User tu, String val, String title);

    /**
     * 发件箱封装
     */
    List<Map<String, Object>> maillist(Page<Inmaillist> mail);

    /**
     * 账号
     *
     * @param page
     * @param size
     * @param tu
     * @param val
     * @return
     */
    Page<Mailnumber> index(int page, int size, User tu, String val, Model model);

    List<Map<String, Object>> up(Page<Mailnumber> num);

    /**
     * 上传附件
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    Attachment upload(MultipartFile file, User mu) throws IllegalStateException, IOException;

    /**
     * 删除账号
     */
    void dele(Long id);

    /**
     * 校验中文
     *
     * @param str
     * @return
     */
    boolean isContainChinese(String str);

    /**
     * 发外部邮件
     *
     * @return
     */
    void pushmail(String account, String password, String reciver,
                  String name, String title, String content, String affix, String filename);


}
