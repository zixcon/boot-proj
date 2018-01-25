package com.holdon.user;

import com.holdon.session.HoldonSessionApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HoldonUserApplication {

    public static void main(String[] args) {
//        SpringApplication.run(HoldonUserApplication.class, args);
        Object[] resources = {HoldonSessionApplication.class, HoldonUserApplication.class};
        SpringApplication.run(resources, args);
    }
}
