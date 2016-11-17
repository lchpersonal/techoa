package com.tech.oa.controller.login;

import com.baitian.fourb.common.result.Result;
import org.activiti.engine.IdentityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by chengli on 2016/11/16.
 */
@Controller
public class LoginController {
    @Autowired
    private IdentityService identityService;

    @RequestMapping("/loginPage")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("/login")
    public Result<?> doLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return new Result<Object>(-1, "用户名密码不能为空");
        }
        if (identityService.checkPassword(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("login", "true");
            session.setAttribute("username", username);
            return new Result<>(0);
        } else {
            return new Result<Object>(-2, "用户名密码错误");
        }
    }
}
