package com.htwy.oa.controller.salary;

import com.github.pagehelper.util.StringUtil;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.htwy.oa.dao.roledao.RoleDao;
import com.htwy.oa.dao.salary.SalaryDao;
import com.htwy.oa.dao.salary.SalaryMapper;
import com.htwy.oa.entity.role.Role;
import com.htwy.oa.entity.salary.Salary;
import com.htwy.oa.service.salary.SalaryService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Vv
 * @title: SalaryController
 * @description: TODO
 * @date 2022/1/251:03 下午
 */

@Controller
@RequestMapping("/")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    @Autowired
    private SalaryDao salaryDao;

    /**
     * 薪资统计页面
     *
     * @return
     */
    @RequestMapping("salarymanage")
    public ModelAndView index(@RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pa = PageRequest.of(page, size);
        Page<Salary> pageSalary = salaryDao.findAll(pa);
        ModelAndView mav = new ModelAndView("salary/salaryMain");
        //List<Salary> lists = salaryService.queryAllSalary(null);
        List<Salary> lists = pageSalary.getContent();
        mav.addObject("page", pageSalary);
        mav.addObject("lists", lists);
        mav.addObject("url", "salaryQuery");
        return mav;
    }

    /**
     * 条件查询
     */
    @RequestMapping("salaryQuery")
    public String salaryQuery(HttpServletRequest req, Model model,
                              @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "size", defaultValue = "10") int size,
                              @RequestParam(value = "search", required = false) String search) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "salaryId"));
        Pageable pa = PageRequest.of(page, size, sort);
        Page<Salary> pageSalary = salaryDao.findAll(pa);
        /*if (StringUtil.isEmpty(salarySearch)) {
            pageSalary =
        } else {
            userspage = udao.findnamelike(usersearch, pa);
        }*/
        List<Salary> lists = pageSalary.getContent();
        model.addAttribute("page", pageSalary);
        model.addAttribute("lists", lists);
        model.addAttribute("url", "salaryQuery");

        return "salary/salarylist";
    }

    @RequestMapping("salaryCalculation")
    public ModelAndView calculation(String month) {
        //薪资计算
        salaryService.calculation(month);

        Pageable pa = PageRequest.of(0, 10);
        Page<Salary> pageSalary = salaryDao.findAll(pa);
        ModelAndView mav = new ModelAndView("salary/salaryMain");
        //List<Salary> lists = salaryService.queryAllSalary(null);
        List<Salary> lists = pageSalary.getContent();
        mav.addObject("page", pageSalary);
        mav.addObject("lists", lists);
        mav.addObject("url", "salaryQuery");
        return mav;
    }

    @RequestMapping(value = "salaryEdit", method = RequestMethod.GET)
    public String edit(@RequestParam(value = "salaryId", required = false) Long salaryId, Model model) {
        if (salaryId != null) {
            Salary salary = salaryDao.getOne(salaryId);
            model.addAttribute("where", "xg");
            model.addAttribute("salary", salary);
        }
        return "salary/editSalary";
    }

    @RequestMapping(value = "salarySave", method = RequestMethod.POST)
    public String salarySave(Salary salary,
                             Model model) throws PinyinException {
        salaryDao.save(salary);
        model.addAttribute("success", 1);
        return "/salarymanage";
    }
}