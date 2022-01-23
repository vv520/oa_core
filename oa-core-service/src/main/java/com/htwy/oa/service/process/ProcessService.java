package com.htwy.oa.service.process;

import com.htwy.oa.entity.process.AubUser;
import com.htwy.oa.entity.process.ProcessList;
import com.htwy.oa.entity.process.Reviewed;
import com.htwy.oa.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

public interface ProcessService {


    /**
     * 写文件 方法
     *
     * @param response
     * @param file
     * @throws IOException
     */
    void writefile(HttpServletResponse response, File file);

    /**
     * 用户封装
     *
     * @param page
     * @param size
     * @return
     */
    void user(int page, int size, Model model);


    Page<AubUser> index(User user, int page, int size, String val, Model model);

    List<Map<String, Object>> index2(Page<AubUser> page, User user);

    /**
     * 审核人封装
     */
    List<Map<String, Object>> index4(ProcessList process);

    /**
     * process数据封装
     */
    Map<String, Object> index3(String name, User user, String typename, ProcessList process);

    /**
     * 公用
     */
    void index6(Model model, Long id, int page, int size);

    /**
     * 存表
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    void index5(ProcessList pro, String val, User lu, MultipartFile filePath, String name) throws IllegalStateException, IOException;

    void index8(ProcessList pro, String val, User lu, String name);

    /**
     * 存主表
     */
    void save(Long proid, User u, Reviewed reviewed, ProcessList pro, User u2);

    /**
     * 存审核表
     */
    void index7(User reuser, ProcessList pro);
}
