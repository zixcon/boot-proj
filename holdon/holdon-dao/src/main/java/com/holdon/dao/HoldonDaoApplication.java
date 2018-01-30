package com.holdon.dao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.holdon.dao.mapper")
public class HoldonDaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoldonDaoApplication.class, args);
    }
}
