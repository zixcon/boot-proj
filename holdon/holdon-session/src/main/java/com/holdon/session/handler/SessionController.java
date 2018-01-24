package com.holdon.session.handler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wd on 2018/1/23.
 */
@RestController
public class SessionController {

    @RequestMapping(value = "/admin")
    public Map<String, Object> firstResp(HttpSession httpSession) {
        Map<String, Object> map = new HashMap<>();
        map.put("account", httpSession.getAttribute("account"));
        map.put("password", httpSession.getAttribute("password"));
        map.put("MAP", httpSession.getAttribute("account"));
        map.put("sessionId", httpSession.getId());
        map.put("AAA", "AAA");
        return map;
    }

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public Map<String, Object> firstResp(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        request.getSession().setAttribute("request Url", request.getRequestURL());
        System.out.println(request.getSession().getId());
        map.put("request Url", request.getRequestURL());
        return map;
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Object sessions(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("map"));
        System.out.println(request.getSession().getId());
        return map;
    }

    @GetMapping("/ss")
    public String uid(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String uid = (String) session.getAttribute("uid");
        if (uid == null) {
            uid = "1234";
        }
        session.setAttribute("uid", uid);
        return uid.toString() + " : " + session.getId();
    }

    @GetMapping("/ssu")
    public String uids(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String uid = (String) session.getAttribute("uid");
        return uid + " : " + session.getId();
    }
}