package com.misstouch.shirolearn.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.util.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author hechao
 * @date create in 17:24 2018/5/8/008
 */
@Controller
public class HomeController {

    @RequestMapping({"/","/index"})
    public String index(){
        return"/index";
    }

    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest request, String username,
                              String password) throws Exception{
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
         if (username!=null) {
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()) {
//                token.setRememberMe(true);
                subject.login(token);
                Session session = subject.getSession(false);
                if (session!=null) {
                    SavedRequest savedRequest = (SavedRequest) session.getAttribute("shiroSavedRequest");
                    String url = savedRequest.getRequestUrl();
                    String[] reduris = url.split("/");
                    String reduri = reduris[reduris.length-1];
                    return new ModelAndView(reduri);
                }

//                if (subject.isPermitted("user:query")) {
//                    return "userInfo";
//                }
            }
        }

        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";

        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        } else {

        }

//        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return new ModelAndView("login");
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }

}
