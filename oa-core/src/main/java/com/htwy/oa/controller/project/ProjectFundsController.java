package com.htwy.oa.controller.project;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.util.StringUtil;
import com.htwy.oa.dao.project.ProjectFundsDao;
import com.htwy.oa.dao.user.UserDao;
import com.htwy.oa.entity.project.ProjectFunds;
import com.htwy.oa.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Vv
 * @version 1.0
 * @description: TODO
 * @date 2022/2/21 0021 16:37
 */
@Controller
@RequestMapping("/")
public class ProjectFundsController {

    @Autowired
    private ProjectFundsDao projectFundsDao;

    @Autowired
    UserDao userDao;

    /**
     * 项目款页面
     *
     * @return
     */
    @RequestMapping("projectFundsManage")
    public ModelAndView projectFundsManage(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pa = PageRequest.of(page, size);
        Page<ProjectFunds> pageProjectFunds = projectFundsDao.findAll(pa);
        ModelAndView mav = new ModelAndView("project/projectMain");
        List<ProjectFunds> lists = pageProjectFunds.getContent();
        mav.addObject("page", pageProjectFunds);
        mav.addObject("lists", lists);
        mav.addObject("url", "projectFundsQuery");
        return mav;
    }

    /**
     * 条件查询
     */
    @RequestMapping("projectFundsQuery")
    public String projectFundsQuery(HttpServletRequest req, Model model,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                    @RequestParam(value = "search", required = false) String projectFundsSearch) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "projectFundsId"));
        Pageable pa = PageRequest.of(page, size, sort);
        Page<ProjectFunds> pageProjectFunds = null;
        if (StringUtil.isEmpty(projectFundsSearch)) {
            pageProjectFunds = projectFundsDao.findAll(pa);
        } else {
            pageProjectFunds = projectFundsDao.queryProjectFundsPage("".equals(projectFundsSearch) ? null : projectFundsSearch, null, pa);
        }
        List<ProjectFunds> lists = pageProjectFunds.getContent();
        model.addAttribute("page", pageProjectFunds);
        model.addAttribute("lists", lists);
        model.addAttribute("url", "projectFundsQuery");

        return "project/projectList";
    }

    /**
     * 删除
     *
     * @param projectFundsId
     * @param model
     * @return
     */
    @RequestMapping("deleteProjectFunds")
    public String deleteProjectFunds(@RequestParam("projectFundsId") Long projectFundsId, Model model) {
        projectFundsDao.deleteById(projectFundsId);
        model.addAttribute("success", 1);
        return "/projectFundsManage";
    }

    /**
     * 新增修改
     *
     * @param projectFundsId
     * @param model
     * @return
     */
    @RequestMapping(value = "projectFundsEdit", method = RequestMethod.GET)
    public String projectFundsEdit(@RequestParam(value = "projectFundsId", required = false) Long projectFundsId, Model model) {
        if (projectFundsId != null) {
            ProjectFunds projectFunds = projectFundsDao.getOne(projectFundsId);
            model.addAttribute("where", "xg");
            model.addAttribute("projectFunds", projectFunds);
        }

        List<User> users = userDao.findAll();
        model.addAttribute("users", users);
        return "project/editProjectFunds";
    }

    /**
     * 保存
     *
     * @param projectFunds
     * @param model
     * @return
     */
    @RequestMapping(value = "projectFundsSave", method = RequestMethod.POST)
    public String projectFundsSave(ProjectFunds projectFunds,
                                   @RequestParam("userId") Long userId,
                                   Model model) {
        User user = userDao.findById(userId).get();
        projectFunds.setUser(user);
        projectFundsDao.save(projectFunds);
        model.addAttribute("success", 1);
        return "/projectFundsManage";
    }
}
