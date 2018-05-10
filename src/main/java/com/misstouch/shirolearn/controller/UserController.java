package com.misstouch.shirolearn.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author hechao
 * @date create in 17:20 2018/5/8/008
 */
@Controller
@RequestMapping("/userInfo")
public class UserController {

    @GetMapping("/userList")
    @RequiresPermissions("user:query")
    public ModelAndView getUser(){
        return new ModelAndView("userList");
    }


}
