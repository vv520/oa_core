package com.htwy.oa.controller.user;

import com.htwy.oa.dao.user.DeptDao;
import com.htwy.oa.dao.user.PositionDao;
import com.htwy.oa.entity.user.Dept;
import com.htwy.oa.entity.user.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class PossionController {

    @Autowired
    PositionDao pdao;
    @Autowired
    DeptDao ddao;

    @RequestMapping("positionmanage")
    public String positionmanage(Model model) {

        List<Position> positions = (List<Position>) pdao.findAll();

        model.addAttribute("positions", positions);

        return "user/positionmanage";
    }

    @RequestMapping(value = "positionedit", method = RequestMethod.GET)
    public String positioneditget(@RequestParam(value = "positionid", required = false) Long positionid, Model model) {
        if (positionid != null) {

            Position position = pdao.findById(positionid).get();
            System.out.println(position);
            Dept dept = ddao.findById(position.getDeptid()).get();
            model.addAttribute("positiondept", dept);
            model.addAttribute("position", position);
        }
        List<Dept> depts = (List<Dept>) ddao.findAll();
        model.addAttribute("depts", depts);
        return "user/positionedit";
    }

    @RequestMapping(value = "positionedit", method = RequestMethod.POST)
    public String positioneditpost(Position position, Model model) {
        System.out.println(position);

        Position psition2 = pdao.save(position);

        if (psition2 != null) {
            model.addAttribute("success", 1);
            return "/positionmanage";
        }

        model.addAttribute("errormess", "数据插入失败");
        return "user/positionedit";
    }


    @RequestMapping("removeposition")
    public String removeposition(@RequestParam("positionid") Long positionid, Model model) {
        pdao.deleteById(positionid);
        model.addAttribute("success", 1);
        return "/positionmanage";
    }


}
