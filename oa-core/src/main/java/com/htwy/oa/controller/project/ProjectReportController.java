package com.htwy.oa.controller.project;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htwy.oa.dao.project.ProjectReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Vv
 * @version 1.0
 * @description: TODO
 * @date 2022/2/27 0027 14:50
 */
@Controller
@RequestMapping("/")
public class ProjectReportController {

    @Autowired
    private ProjectReportMapper projectReportMapper;

    /**
     * 项目款汇总页面
     *
     * @return
     */
    @RequestMapping("projectReportManage")
    public ModelAndView projectReportManage(@RequestParam(value = "pageNum", defaultValue = "1") int page,
                                            @RequestParam(value = "size", defaultValue = "10") int size) {
        PageHelper.startPage(page, 10);
        List<Map<String, Object>> pageProjectReport = projectReportMapper.findPeojectReport(null, page, size);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(pageProjectReport);
        ModelAndView mav = new ModelAndView("project/projectReportMain");
        mav.addObject("page", pageInfo);
        mav.addObject("lists", pageProjectReport);
        mav.addObject("url", "projectReportQuery");
        return mav;
    }

    /**
     * 条件查询
     */
    @RequestMapping("projectReportQuery")
    public String projectReportQuery(HttpServletRequest req, Model model,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size,
                                     @RequestParam(value = "search", required = false) String projectReportSearch) {
        PageHelper.startPage(page, 10);
        List<Map<String, Object>> pageProjectReport = projectReportMapper.findPeojectReport(projectReportSearch, page, size);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(pageProjectReport);
        model.addAttribute("page", pageInfo);
        model.addAttribute("lists", pageProjectReport);
        model.addAttribute("url", "projectReportQuery");

        return "project/projectReportList";
    }
}
