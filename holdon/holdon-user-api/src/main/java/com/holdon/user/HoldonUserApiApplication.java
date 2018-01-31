package com.holdon.user;

import com.holdon.dao.HoldonDaoApplication;
import com.holdon.session.HoldonSessionApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HoldonUserApiApplication {

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
