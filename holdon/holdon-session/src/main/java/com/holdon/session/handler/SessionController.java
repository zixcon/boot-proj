package com.holdon.session.handler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wd on 2018/1/23.
 */
@RestController
public class SessionController {

    @RequestMapping(value = "/admin")
    public Map<String, Object> firstResp (HttpSession httpSession){
        Map<String, Object> map = new HashMap<>();
//      map.put("account", httpSession.getAttribute("account"));
//      map.put("password", httpSession.getAttribute("password"));
        map.put("MAP", httpSession.getAttribute("map"));
        map.put("sessionId", httpSession.getId());
        map.put("AAA", "AAA");
        return map;
    }
}