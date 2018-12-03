package com.github.yll.epoch.business.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author luliang_yu
 * @date 2018-12-03
 */
@Controller
public class SpringSessionController {

    @RequestMapping("/putsession")
    @ResponseBody
    public String putSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session.getClass());
        System.out.println(session.getId());
        String name = "yll";
        session.setAttribute("user",name);
        return "hi," + name;
    }

}
