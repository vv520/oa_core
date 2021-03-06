package com.htwy.oa.controller.user;

import com.htwy.oa.dao.roledao.RoleDao;
import com.htwy.oa.dao.user.DeptDao;
import com.htwy.oa.dao.user.PositionDao;
import com.htwy.oa.dao.user.UserDao;
import com.htwy.oa.entity.role.Role;
import com.htwy.oa.entity.user.Dept;
import com.htwy.oa.entity.user.Position;
import com.htwy.oa.entity.user.User;
import com.github.pagehelper.util.StringUtil;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    UserDao udao;
    @Autowired
    DeptDao ddao;
    @Autowired
    PositionDao pdao;
    @Autowired
    RoleDao rdao;

    @RequestMapping("userlogmanage")
    public String userlogmanage() {
        return "user/userlogmanage";
    }

    @RequestMapping("usermanage")
    public String usermanage(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Sort sort = Sort.by(new Order(Direction.ASC, "dept"));
        List<Dept> depts = (List<Dept>) ddao.findAll();
        List<Role> roles = (List<Role>) rdao.findAll();
        Pageable pa = PageRequest.of(page, size, sort);
        Page<User> userspage = udao.findByIsLock(0, pa);
        List<User> users = userspage.getContent();
        model.addAttribute("depts", depts);
        model.addAttribute("roles", roles);
        model.addAttribute("users", users);
        model.addAttribute("page", userspage);
        model.addAttribute("url", "usermanagepaging");
        return "user/usermanage";
    }

    @RequestMapping("usermanagepaging")
    public String userPaging(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size,
                             @RequestParam(value = "username", required = false) String username,
                             @RequestParam(value = "deptId", required = false) Long deptId,
                             @RequestParam(value = "roleId", required = false) Long roleId
    ) {
        Sort sort = Sort.by(new Order(Direction.ASC, "dept"));
        List<Dept> depts = (List<Dept>) ddao.findAll();
        List<Role> roles = (List<Role>) rdao.findAll();
        Pageable pa = PageRequest.of(page, size, sort);
        Page<User> userspage = udao.queryUserPage(username, deptId, roleId, pa);
        /*if (StringUtil.isEmpty(username)) {
            userspage = udao.findByIsLock(0, pa);
        } else {
            System.out.println(username);
            userspage = udao.findnamelike(username, pa);
        }*/
        List<User> users = userspage.getContent();
        model.addAttribute("depts", depts);
        model.addAttribute("roles", roles);
        model.addAttribute("users", users);
        model.addAttribute("page", userspage);
        model.addAttribute("url", "usermanagepaging");

        return "user/usermanagepaging";
    }


    @RequestMapping(value = "useredit", method = RequestMethod.GET)
    public String usereditget(@RequestParam(value = "userid", required = false) Long userid, Model model) {
        if (userid != null) {
            User user = udao.getOne(userid);
            model.addAttribute("where", "xg");
            model.addAttribute("user", user);
        }

        List<Dept> depts = (List<Dept>) ddao.findAll();
        List<Position> positions = (List<Position>) pdao.findAll();
        List<Role> roles = (List<Role>) rdao.findAll();

        model.addAttribute("depts", depts);
        model.addAttribute("positions", positions);
        model.addAttribute("roles", roles);
        return "user/edituser";
    }

    @RequestMapping(value = "useredit", method = RequestMethod.POST)
    public String usereditpost(User user,
                               @RequestParam("deptid") Long deptid,
                               @RequestParam("positionid") Long positionid,
                               @RequestParam("roleid") Long roleid,
                               @RequestParam(value = "isbackpassword", required = false) boolean isbackpassword,
                               Model model) throws PinyinException {
        Dept dept = ddao.findById(deptid).get();
        Position position = pdao.findById(positionid).get();
        Role role = rdao.getOne(roleid);
        if (user.getUserId() == null) {
            String pinyin = PinyinHelper.convertToPinyinString(user.getUserName(), "", PinyinFormat.WITHOUT_TONE);
            user.setPinyin(pinyin);
            user.setPassword("123456");
            user.setDept(dept);
            user.setRole(role);
            user.setPosition(position);
            user.setFatherId(dept.getDeptmanager());
            udao.save(user);
        } else {
            User user2 = udao.getOne(user.getUserId());
            user2.setUserTel(user.getUserTel());
            user2.setRealName(user.getRealName());
            user2.setEamil(user.getEamil());
            user2.setAddress(user.getAddress());
            user2.setUserEdu(user.getUserEdu());
            user2.setSchool(user.getSchool());
            user2.setIdCard(user.getIdCard());
            user2.setBank(user.getBank());
            user2.setThemeSkin(user.getThemeSkin());
            user2.setSalary(user.getSalary());
            user2.setFundBase(user.getFundBase());
            user2.setMedicalSecurityBase(user.getMedicalSecurityBase());
            user2.setSocialSecurityBase(user.getSocialSecurityBase());
            user2.setFatherId(dept.getDeptmanager());
            if (isbackpassword) {
                user2.setPassword("123456");
            }
            user2.setDept(dept);
            user2.setRole(role);
            user2.setPosition(position);
            udao.save(user2);
        }

        model.addAttribute("success", 1);
        return "/usermanage";
    }


    @RequestMapping("deleteuser")
    public String deleteuser(@RequestParam("userid") Long userid, Model model) {
        User user = udao.getOne(userid);

        user.setIsLock(1);

        udao.save(user);

        model.addAttribute("success", 1);
        return "/usermanage";

    }

    @RequestMapping("useronlyname")
    public @ResponseBody
    boolean useronlyname(@RequestParam("username") String username) {
        System.out.println(username);
        User user = udao.findByUserName(username);
        System.out.println(user);
        if (user == null) {
            return true;
        }
        return false;
    }

    @RequestMapping("selectdept")
    public @ResponseBody
    List<Position> selectdept(@RequestParam("selectdeptid") Long deptid) {

        return pdao.findByDeptidAndNameNotLike(deptid, "%??????");
    }


}
