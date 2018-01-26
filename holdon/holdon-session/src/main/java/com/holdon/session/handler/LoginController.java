//package com.holdon.session.handler;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpSession;
//
///**
// * Created by wd on 2018/1/23.
// */
//@Controller
//public class LoginController {
//
//    @RequestMapping("/")
//    public String index(ModelMap map, HttpSession httpSession) {
//        String account = (String) httpSession.getAttribute("account");
//        String password = (String) httpSession.getAttribute("password");
//        System.out.println("1--index--账户是：\t" + account);
//        System.out.println("1--index--密码是：\t" + password);
//        if (account == null || password == null) {
//            return "thymeleaf/login";
//        }
//        return "thymeleaf/index";
//    }
//
//    @RequestMapping("/login")
//    public String doLogin(ModelMap map, HttpSession httpSession, String account, String password) {
//        System.out.println("1--login--账户是：\t" + account);
//        System.out.println("1--login--密码是：\t" + password);
//        httpSession.setAttribute("account", account);
//        httpSession.setAttribute("password", password);
//        map.addAttribute("account", account);
//        return "thymeleaf/index";
//    }
//
//    @RequestMapping("/logout")
//    public String doLogin(HttpSession httpSession) {
//        String account = (String) httpSession.getAttribute("account");
//        String password = (String) httpSession.getAttribute("password");
//        System.out.println("1--logout--账户是：\t" + account);
//        System.out.println("1--logout--密码是：\t" + password);
//        httpSession.removeAttribute("account");
//        httpSession.removeAttribute("password");
//        return "thymeleaf/login";
//    }
//}