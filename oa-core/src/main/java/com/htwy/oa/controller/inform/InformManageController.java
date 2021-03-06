package com.htwy.oa.controller.inform;

import com.htwy.oa.common.formValid.BindingResultVOUtil;
import com.htwy.oa.common.formValid.MapToList;
import com.htwy.oa.common.formValid.ResultEnum;
import com.htwy.oa.common.formValid.ResultVO;
import com.htwy.oa.dao.notice.NoticeMapper;
import com.htwy.oa.dao.informdao.InformDao;
import com.htwy.oa.dao.informdao.InformRelationDao;
import com.htwy.oa.dao.system.StatusDao;
import com.htwy.oa.dao.system.TypeDao;
import com.htwy.oa.dao.user.DeptDao;
import com.htwy.oa.dao.user.UserDao;
import com.htwy.oa.entity.notice.NoticeUserRelation;
import com.htwy.oa.entity.notice.NoticesList;
import com.htwy.oa.entity.system.SystemStatusList;
import com.htwy.oa.entity.system.SystemTypeList;
import com.htwy.oa.entity.user.User;
import com.htwy.oa.service.inform.InformRelationService;
import com.htwy.oa.service.inform.InformService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/")
public class InformManageController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private StatusDao statusDao;

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private InformDao informDao;

    @Autowired
    private InformService informService;

    @Autowired
    private UserDao uDao;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private InformRelationDao informrelationDao;

    @Autowired
    private InformRelationService informrelationservice;

    @Autowired
    private NoticeMapper nm;

    /**
     * ??????????????????
     *
     * @return
     */
    @RequestMapping("infrommanage")
    public String inform(@RequestParam(value = "page", defaultValue = "0") int page, @SessionAttribute("userId") Long userId, Model model) {
        Page<NoticesList> page2 = informService.pageThis(page, userId);
        List<NoticesList> noticeList = page2.getContent();
        List<Map<String, Object>> list = informService.fengZhuang(noticeList);
        model.addAttribute("list", list);
        model.addAttribute("page", page2);
        //?????????????????????load???url???
        model.addAttribute("url", "infrommanagepaging");
        return "inform/informmanage";
    }

    @RequestMapping("forwardother")
    public String forwardOther(@SessionAttribute("userId") Long userId, @RequestParam(value = "noticeId") Long noticeId) {
        List<User> users = uDao.findByFatherId(userId);
        NoticesList nl = informDao.getOne(noticeId);
        List<NoticeUserRelation> nurs = new ArrayList<>();
        for (User user : users) {
            nurs.add(new NoticeUserRelation(nl, user, false));
        }
        informrelationservice.saves(nurs);
        return "redirect:/infromlist";
    }

    // demo
//	@RequestMapping("cccc")
//	public @ResponseBody Page<NoticesList> ddd(@RequestParam(value = "page", defaultValue = "0") int page,
//			@RequestParam(value = "size", defaultValue = "10") int size,
//			@RequestParam(value = "baseKey", required = false) String baseKey, @SessionAttribute("userId") Long userId,
//			Model model) {
//		Page<NoticesList> page2 = informService.pageThis(page, size, userId,baseKey,null,null,null);
//		List<NoticesList> noticeList=page2.getContent();
//		Long sum=page2.getTotalElements();
//		int size2=page2.getSize();
//		int pages=page2.getTotalPages();
//		int number=page2.getNumber();
//		model.addAttribute("list", noticeList);
//		model.addAttribute("page", page2);
//		return page2;

    // List<NoticesList> noticeList=informDao.findByUserId(userId);
    // List<NoticesList>
    // noticeList=informDao.findByUserIdAndTopOrderByModifyTimeDesc(userId,
    // true);
    // List<NoticesList>
    // noticeList2=informDao.findByUserIdAndTopOrderByModifyTimeDesc(userId,
    // false);
    // noticeList.addAll(noticeList2);
    // List<Map<String, Object>> list=informService.fengZhuang(noticeList);
    // model.addAttribute("list",list);
//	}

    /**
     * ??????????????????
     */
    @RequestMapping("infromdelete")
    public String infromDelete(HttpSession session, HttpServletRequest req) {
        Long noticeId = Long.parseLong(req.getParameter("id"));
        Long userId = Long.parseLong(session.getAttribute("userId") + "");
        NoticesList notice = informDao.getOne(noticeId);
        if (!Objects.equals(userId, notice.getUserId())) {
            System.out.println("??????????????????????????????");
            return "redirect:/notlimit";
        }
        System.out.println(noticeId);
        informService.deleteOne(noticeId);
        return "redirect:/infrommanage";

    }

    /**
     * ??????????????????
     */
    @RequestMapping("informlistdelete")
    public String informListDelete(HttpServletRequest req, HttpSession session) {
        Long userId = Long.parseLong(session.getAttribute("userId") + "");
        Long noticeId = Long.parseLong(req.getParameter("id"));
        NoticeUserRelation relation = informrelationDao.findByUserIdAndNoticeId(uDao.getOne(userId),
                informDao.getOne(noticeId));
        if (Objects.isNull(relation)) {
            System.out.println("??????????????????????????????");
            return "redirect:/notlimit";
        }
        informrelationservice.deleteOne(relation);
        return "forward:/infromlist";
    }

    /**
     * ????????????
     *
     * @return
     */
    @RequestMapping("infromlist")
    public String infromList(HttpSession session, HttpServletRequest req, Model model,
                             @RequestParam(value = "pageNum", defaultValue = "1") int page) {
        Long userId = Long.parseLong(session.getAttribute("userId") + "");
        PageHelper.startPage(page, 10);
        List<Map<String, Object>> list = nm.findMyNotice(userId);
        PageInfo<Map<String, Object>> pageinfo = new PageInfo<Map<String, Object>>(list);
        List<Map<String, Object>> list2 = informrelationservice.setList(list);
        for (Map<String, Object> map : list2) {
            System.out.println(map);
        }
        model.addAttribute("url", "informlistpaging");
        model.addAttribute("list", list2);
        model.addAttribute("page", pageinfo);
        System.out.println(pageinfo);
        return "inform/informlist";
    }

    /**
     * ??????????????????
     */
    @RequestMapping("informedit")
    public String infromEdit(HttpServletRequest req, HttpSession session, Model model) {
        session.removeAttribute("noticeId");
        List<SystemTypeList> typeList = typeDao.findByTypeModel("inform");
        List<SystemStatusList> statusList = statusDao.findByStatusModel("inform");
        if (!StringUtils.isEmpty(req.getAttribute("errormess"))) {
            req.setAttribute("errormess", req.getAttribute("errormess"));
        }
        if (!StringUtils.isEmpty(req.getAttribute("success"))) {
            req.setAttribute("success", "??????????????????");
        }
        req.setAttribute("typeList", typeList);
        req.setAttribute("statusList", statusList);
        if (!StringUtils.isEmpty(req.getParameter("id"))) {
            Long noticeId = Long.parseLong(req.getParameter("id"));
            NoticesList noticeList = informDao.getOne(noticeId);
            model.addAttribute("noticeList", noticeList);
            model.addAttribute("typeName", typeDao.findById(noticeList.getTypeId()).get().getTypeName());
            model.addAttribute("statusName", statusDao.findById(noticeList.getStatusId()).get().getStatusName());
            session.setAttribute("noticeId", noticeId);
        }

        return "inform/informedit";
    }

    /**
     * ??????????????????
     */
    @RequestMapping("informshow")
    public String informShow(HttpServletRequest req, Model model) {
        Long noticeId = Long.parseLong(req.getParameter("id"));
        if (!StringUtils.isEmpty(req.getParameter("read"))) {
            if (("0").equals(req.getParameter("read"))) {
                Long relationId = Long.parseLong(req.getParameter("relationid"));
                NoticeUserRelation relation = informrelationDao.findById(relationId).get();
                relation.setRead(true);
                informrelationservice.save(relation);
            }
        }
        NoticesList notice = informDao.getOne(noticeId);
        User user = uDao.getOne(notice.getUserId());
        model.addAttribute("notice", notice);
        model.addAttribute("userName", user.getUserName());
        return "inform/informshow";
    }

    /**
     * ????????????????????????
     *
     * @param req
     * @param menu
     * @param br   ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     * @return
     */
    @RequestMapping("informcheck")
    public String testMess(HttpServletRequest req, @Valid NoticesList menu, BindingResult br) {
        HttpSession session = req.getSession();
        Long menuId = null;
        req.setAttribute("menuObj", menu);
        Long userId = Long.parseLong(session.getAttribute("userId") + "");
        menu.setUserId(userId);

        // ????????????ResultVO??????????????????????????????ResultEnum.SUCCESS.getCode()???????????????200??????????????????????????????
        ResultVO res = BindingResultVOUtil.hasErrors(br);
        // ????????????
        if (!ResultEnum.SUCCESS.getCode().equals(res.getCode())) {
            List<Object> list = new MapToList<>().mapToList(res.getData());
            req.setAttribute("errormess", list.get(0).toString());
            // ??????????????????????????????????????????????????????
            System.out.println("list???????????????????????????" + menu);
            System.out.println("list????????????:" + list);
            System.out.println("list???????????????:" + list.get(0));
            System.out.println("?????????????????????????????????" + list.get(0).toString());
            // ?????????info?????????????????????????????????
            log.info("getData:{}", res.getData());
            log.info("getCode:{}", res.getCode());
            log.info("getMsg:{}", res.getMsg());
        }
        // ?????????????????????????????????????????????
        else {
            // ????????????????????????????????????????????????"session.setAttribute("getId",getId);",?????????????????????remove??????
            if (!StringUtils.isEmpty(session.getAttribute("noticeId"))) {
                menuId = (Long) session.getAttribute("noticeId"); // ???????????????????????????menuID???
                NoticesList inform = informDao.getOne(menuId);
                menu.setNoticeTime(inform.getNoticeTime());
                menu.setNoticeId(menuId);
                session.removeAttribute("noticeId");
                informService.save(menu);
            } else {
                menu.setNoticeTime(new Date());
                menu.setUserId(userId);
                NoticesList noticeList = informService.save(menu);
                List<User> userList = uDao.findByFatherId(userId);
                for (User user : userList) {
                    informrelationservice.save(new NoticeUserRelation(noticeList, user, false));
                }
            }
            // ??????????????????
            System.out.println("?????????????????????");
            req.setAttribute("success", "??????????????????");
        }
        System.out.println("???????????????????????????????????????" + menu);
        return "forward:/informedit";
    }

}
