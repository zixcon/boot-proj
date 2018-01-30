package com.holdon.user;

import com.holdon.dao.HoldonDaoApplication;
import com.holdon.session.HoldonSessionApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class HoldonUserApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
//        SpringApplication.run(HoldonUserApplication.class, args);
        // 这里增加session 项目一起启动
        Object[] resources = {
                HoldonSessionApplication.class,
                HoldonDaoApplication.class,
                HoldonUserApiApplication.class};
        SpringApplication.run(resources, args);
    }
}
