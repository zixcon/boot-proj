package com.holdon.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HoldonUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoldonUserApplication.class, args);
        // 这里增加session 项目一起启动
//        Object[] resources = {HoldonSessionApplication.class, HoldonUserApplication.class};
//        SpringApplication.run(resources, args);
    }
}
