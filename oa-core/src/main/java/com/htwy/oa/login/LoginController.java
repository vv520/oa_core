package com.htwy.oa.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vv
 * @version 1.0
 * @description: TODO
 * @date 2022/1/21 0021 10:44
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/logins")
    public String login() {
        return "login/login";
    }
}
